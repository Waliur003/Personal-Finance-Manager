package reports;

/**
 * Displays financial reports to the console for the Personal Finance Manager
 * (PFM) application.
 *
 * <p>
 * This class is responsible for presenting formatted report information to the
 * user through the console interface. Actual formatting and printing logic will
 * be implemented during development.
 * </p>
 *
 * @author Alyssa Johnson
 * @version 1.0
 * @since 1.0
 */
public class ConsoleReport {

    /**
     * Constructs a new ConsoleReport object.
     */
    public ConsoleReport() {

        // No setup is needed yet because this class only prints placeholder data.
        // TODO: Add fields here later if ReportManager passes prepared report data.

    }

    /**
     * Prints the annual financial report to the console.
     *
     * <p>
     * Displays yearly income, expenses, category totals,
     * and overall budget performance.
     * </p>
     */
    public void printAnnualReport() {

        // TODO: Replace this placeholder once ReportManager provides real report data.
        printReportHeader();

        // For now we print sample output so the class can be tested independently.
        System.out.println("ANNUAL FINANCIAL REPORT - SAMPLE YEAR 2026");
        System.out.println("------------------------------------------");
        System.out.println("Total Income:           $52,000.00");
        System.out.println("Total Expenses:         $39,750.00");
        System.out.println("Net Savings:            $12,250.00");
        System.out.println("Savings Rate:                23.6%");
        System.out.println();

        // These sample lines show the kind of category information a full report may include.
        System.out.println("Top Categories");
        System.out.println("  Income  - Paycheck:   $48,000.00");
        System.out.println("  Expense - Housing:    $18,000.00");
        System.out.println("  Expense - Food:        $6,200.00");
        System.out.println();

        // Future implementation should retrieve formatted data from the reports module.
        System.out.println("Budget Result: Surplus of $12,250.00");
        System.out.println("==========================================");

    }

    /**
     * Prints the monthly financial summary.
     *
     * <p>
     * Displays income, expenses, and net balance
     * for a selected month.
     * </p>
     */
    public void printMonthlySummary() {

        // TODO: Replace this placeholder once monthly data is available from Storage.
        printReportHeader();

        // The month is hard-coded temporarily so this method has visible behavior.
        System.out.println("MONTHLY SUMMARY - SAMPLE MONTH JANUARY 2026");
        System.out.println("-------------------------------------------");
        System.out.println("Income:                 $4,250.00");
        System.out.println("Expenses:               $3,180.00");
        System.out.println("Net Balance:            $1,070.00");
        System.out.println();

        // This short table demonstrates how a real monthly summary might be formatted.
        System.out.println("Category Breakdown");
        System.out.println("  Housing:              $1,500.00");
        System.out.println("  Groceries:              $520.00");
        System.out.println("  Transportation:         $260.00");
        System.out.println("  Entertainment:          $175.00");
        System.out.println("===========================================");

    }

    /**
     * Prints yearly totals for each income and expense category.
     *
     * <p>
     * Displays organized category totals in a readable format.
     * </p>
     */
    public void printCategoryTotals() {

        // TODO: Replace this placeholder once category totals are calculated by ReportManager.
        printReportHeader();

        // This sample table keeps columns aligned so the console output is easy to read.
        System.out.println("YEARLY CATEGORY TOTALS - SAMPLE DATA");
        System.out.println("------------------------------------");
        System.out.println("Category             Type        Total");
        System.out.println("------------------------------------");
        System.out.println("Paycheck             Income      $48,000.00");
        System.out.println("Freelance            Income       $4,000.00");
        System.out.println("Housing              Expense     $18,000.00");
        System.out.println("Food                 Expense      $6,200.00");
        System.out.println("Utilities            Expense      $2,400.00");
        System.out.println("Transportation       Expense      $3,150.00");
        System.out.println("====================================");

    }

    /**
     * Prints the overall budget performance summary.
     *
     * <p>
     * Displays whether the user finished the year
     * with a surplus or deficit.
     * </p>
     */
    public void printBudgetSummary() {

        // TODO: Replace this placeholder once budget goals are provided by the budget module.
        printReportHeader();

        // These values are temporary examples to show the intended report layout.
        System.out.println("BUDGET PERFORMANCE SUMMARY - SAMPLE DATA");
        System.out.println("----------------------------------------");
        System.out.println("Planned Annual Budget:  $42,000.00");
        System.out.println("Actual Annual Spending: $39,750.00");
        System.out.println("Difference:              $2,250.00 under budget");
        System.out.println();

        // This explanation is helpful for classmates testing the class before integration.
        System.out.println("Status: Great job! The sample user finished under budget.");
        System.out.println("TODO: Connect this message to real budget performance logic.");
        System.out.println("========================================");

    }

    /**
     * Prints the report title and section headings.
     *
     * <p>
     * Creates a consistent header for all report types.
     * </p>
     */
    public void printReportHeader() {

        // This shared header gives every console report a consistent starting point.
        System.out.println();
        System.out.println("==========================================");
        System.out.println("       Personal Finance Manager Report");
        System.out.println("==========================================");
        System.out.println("NOTE: Placeholder data is shown for now.");
        System.out.println("TODO: Replace sample values after Integration connects real data.");
        System.out.println();

    }

}
