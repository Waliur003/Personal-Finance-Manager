package storage;

import accounts.Account;
import accounts.AccountService;
import integration.AppModule;
import integration.MenuUtil;

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
     * Prompts for a CSV file path and target year, parses and filters the
     * file's transactions, then merges them into that year's budget
     * (creating the budget if it doesn't already exist).
     *
     * @param username the logged-in user's username
     * @author Mohammed, Ayub, Fuad
     */
    private void handleImportCsv(String username) {
        String filePath = MenuUtil.promptString("Path to CSV file");

        // NOTE: once Validation.isValidFileName()/isValidCsvFile() are
        // implemented, gate the import behind those checks, e.g.:
        //   if (!Validation.isValidCsvFile(filePath)) { ... reject ... }
        // Both are currently stubbed to always return false, so wiring
        // them in now would block every import.

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

        int year = promptForYear();

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
     * Prompts the user to enter a year and parses it as an integer.
     *
     * @return the year entered by the user
     * @author Mohammed, Ayub, Fuad
     */
    private int promptForYear() {
        String input = MenuUtil.promptString("Enter year");
        return Integer.parseInt(input);
    }
}