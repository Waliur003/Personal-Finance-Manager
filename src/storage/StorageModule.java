package storage;

import accounts.Account;
import accounts.AccountService;
import integration.AppModule;
import integration.MenuUtil;
import validation.Validation;

import java.nio.file.Path;
import java.util.List;

/**
 * Entry point for the Storage module. Implements {@link AppModule} so the
 * Integration layer can select and run this module without knowing about
 * its internals. Owns its own submenu and delegates all real work to the
 * existing storage classes ({@link BudgetStorage}, {@link CsvImporter},
 * {@link CsvExporter}, {@link FileUtil}).
 *
 * <p>Account creation/login is owned by the {@code accounts} package —
 * this module only reads the already-logged-in user from
 * {@link AccountService.SessionManager}.</p>
 *
 * @author Mohammed, Ayub, Fuad
 */
public class StorageModule implements AppModule {

    /**
     * Registered module name, kept lowercase per the Integration team's
     * naming convention. Integration's lookup (in {@code registerModule}
     * / {@code dispatchSelection}) needs to match on this lowercase form
     * rather than the raw {@code MenuOptions} constant name.
     */
    private static final String MODULE_NAME = "storage";

    /**
     * Sentinel returned by {@link #promptForYear()} to signal the user
     * cancelled instead of entering a year. {@code 0} is a safe choice
     * since {@link Validation#MIN_YEAR} is 1900, so it can never collide
     * with a real budget year.
     */
    private static final int CANCEL_YEAR = 0;

    private BudgetStorage budgetStorage;
    private CsvImporter csvImporter;
    private CsvExporter csvExporter;

    /**
     * Constructs a new {@code StorageModule}. No heavy setup here —
     * that belongs in {@link #initialize()}.
     *
     * @author Mohammed, Ayub, Fuad
     */
    public StorageModule() {
    }

    /**
     * {@inheritDoc}
     *
     * @return the module name used by Integration's registry
     * @author Mohammed, Ayub, Fuad
     */
    @Override
    public String getModuleName() {
        return MODULE_NAME;
    }

    /**
     * Performs one-time setup: instantiates the helper classes this module
     * depends on and makes sure the data directory exists. Console input is
     * read through {@link MenuUtil}'s shared scanner rather than a
     * module-local one, so there is no {@code Scanner} to set up here.
     *
     * @author Mohammed, Ayub, Fuad
     */
    @Override
    public void initialize() {
        budgetStorage = new BudgetStorage();
        csvImporter = new CsvImporter();
        csvExporter = new CsvExporter();
        FileUtil fileUtil = new FileUtil();

        fileUtil.ensureDataDirectoryExists();
    }

    /**
     * Runs the storage module's submenu loop. Called by Integration when
     * the user selects this module from the main menu. Requires that a
     * user is already logged in via the Accounts module.
     *
     * @author Mohammed, Ayub, Fuad
     */
    @Override
    public void handleSelection() {
        Account currentUser = AccountService.SessionManager.getCurrentUser();
        if (currentUser == null) {
            System.out.println("You must be logged in to use the Storage module.");
            return;
        }
        String username = currentUser.getUsername();

        boolean running = true;
        while (running) {
            String choice = MenuUtil.promptChoice("Storage Module",
                    "1. List budget years",
                    "2. View a budget",
                    "3. Import transactions from CSV",
                    "4. Export transactions to CSV",
                    "5. Delete a budget year",
                    "0. Back to main menu");

            switch (choice) {
                case "1" -> handleListBudgetYears(username);
                case "2" -> handleViewBudget(username);
                case "3" -> handleImportCsv(username);
                case "4" -> handleExportCsv(username);
                case "5" -> handleDeleteBudget(username);
                case "0" -> running = false;
                default -> System.out.println("Invalid option, please try again.");
            }
        }
    }

    // ---- submenu actions ------------------------------------------------

    /**
     * Looks up and prints all budget years on file for the given user.
     *
     * @param username the logged-in user's username
     * @author Mohammed, Ayub, Fuad
     */
    private void handleListBudgetYears(String username) {
        List<Integer> years = budgetStorage.listYearsForUser(username);
        if (years == null || years.isEmpty()) {
            System.out.println("No budgets found for " + username + ".");
            return;
        }
        System.out.println("Years with budgets: " + years);
    }

    /**
     * Prompts for a year and prints every transaction in that year's
     * budget, if one exists.
     *
     * @param username the logged-in user's username
     * @author Mohammed, Ayub, Fuad
     */
    private void handleViewBudget(String username) {
        int year = promptForYear();
        if (year == CANCEL_YEAR) {
            System.out.println("Cancelled.");
            return;
        }

        if (!budgetStorage.yearExists(username, year)) {
            System.out.println("No budget found for " + year + ".");
            return;
        }

        Budget budget = budgetStorage.readBudget(username, year);
        List<Transaction> transactions = budget.getTransactions();
        System.out.println("Budget for " + year + " (" + transactions.size() + " transactions):");
        for (Transaction t : transactions) {
            System.out.println("  " + t.date() + " | " + t.category() + " | " + t.amount());
        }
    }

    /**
     * Prompts for a CSV file path, parses and filters its transactions,
     * then merges them into the budget for the year encoded in the file
     * name (creating the budget if it doesn't already exist).
     *
     * @bug Previously prompted for the year separately from the file
     * path, even though the app already requires budget CSV files to be
     * named {@code YYYY.csv} (see {@link Validation#isValidFileName}).
     * That redundant prompt was also the entry point for the "0 means
     * cancel" data-corruption bug documented on {@link #promptForYear()}
     * — the year is now read directly from the file name instead.
     *
     * @param username the logged-in user's username
     * @author Mohammed, Ayub, Fuad
     */
    private void handleImportCsv(String username) {
        String filePath = MenuUtil.promptString("Path to CSV file");

        if (!Validation.isValidCsvFile(filePath)) {
            System.out.println("'" + filePath + "' is not a valid budget CSV file. "
                    + "It must be named YYYY.csv, be readable, and start with the header \""
                    + Validation.VALID_HEADER + "\".");
            return;
        }

        List<Transaction> parsed = csvImporter.parseCsvFile(filePath);
        if (parsed == null) {
            System.out.println("Could not parse " + filePath + ".");
            return;
        }

        List<Transaction> valid = csvImporter.filterInvalidRecords(parsed);
        if (valid == null) {
            System.out.println("Could not validate the parsed transactions.");
            return;
        }

        String baseName = Path.of(filePath).getFileName().toString();
        int year = Integer.parseInt(baseName.substring(0, 4));

        Budget budget = budgetStorage.yearExists(username, year)
                ? budgetStorage.readBudget(username, year)
                : new Budget(year);

        for (Transaction t : valid) {
            budget.addTransaction(t);
        }

        if (budgetStorage.yearExists(username, year)) {
            budgetStorage.updateBudget(username, budget);
        } else {
            budgetStorage.createBudget(username, budget);
        }

        System.out.println("Imported " + valid.size() + " transactions into " + year + ".");
    }

    /**
     * Prompts for a year and destination file path, then writes that
     * year's budget out to a CSV file.
     *
     * @param username the logged-in user's username
     * @author Mohammed, Ayub, Fuad
     */
    private void handleExportCsv(String username) {
        int year = promptForYear();
        if (year == CANCEL_YEAR) {
            System.out.println("Cancelled.");
            return;
        }

        if (!budgetStorage.yearExists(username, year)) {
            System.out.println("No budget found for " + year + ".");
            return;
        }

        Budget budget = budgetStorage.readBudget(username, year);
        String filePath = MenuUtil.promptString("Destination file path");

        csvExporter.writeReportToCsv(budget.getTransactions(), filePath);
        System.out.println("Exported to " + filePath + ".");
    }

    /**
     * Prompts for a year and, after confirmation, permanently deletes
     * that year's budget.
     *
     * @param username the logged-in user's username
     * @author Mohammed, Ayub, Fuad
     */
    private void handleDeleteBudget(String username) {
        int year = promptForYear();
        if (year == CANCEL_YEAR) {
            System.out.println("Cancelled.");
            return;
        }

        if (!budgetStorage.yearExists(username, year)) {
            System.out.println("No budget found for " + year + ".");
            return;
        }

        boolean confirmed = MenuUtil.promptYesNo(
                "Delete budget for " + year + "? This cannot be undone");
        if (confirmed) {
            budgetStorage.deleteBudget(username, year);
            System.out.println("Deleted budget for " + year + ".");
        } else {
            System.out.println("Cancelled.");
        }
    }

    /**
     * Prompts the user to enter a year, or {@code 0} to cancel.
     *
     * @bug Previously parsed whatever the user typed as a literal year
     * with no way to back out. A user backing out of the (since removed)
     * year prompt in {@link #handleImportCsv(String)} by entering
     * {@code 0} — mirroring the "0 = back" convention used everywhere
     * else in the app's menus — had it silently create and persist a
     * budget for year 0, which then showed up in
     * {@link #handleListBudgetYears(String)}. {@code 0} is now treated
     * as an explicit cancel signal instead of a literal year.
     *
     * @return the year entered by the user, or {@link #CANCEL_YEAR} if
     *         the user cancelled or entered something unparseable
     * @author Mohammed, Ayub, Fuad
     */
    private int promptForYear() {
        String input = MenuUtil.promptString("Enter year (or 0 to cancel)");
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid year entered.");
            return CANCEL_YEAR;
        }
    }
}