package reports;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Exports financial reports to CSV files for the Personal Finance Manager
 * (PFM) application.
 *
 * <p>
 * This class is responsible for creating CSV files containing report data.
 * The exported files can be opened using spreadsheet applications for further
 * analysis or record keeping.
 * </p>
 *
 * @author Alyssa Johnson
 * @version 1.0
 * @since 1.0
 */
public class CsvReportExporter {

    /**
     * Constructs a new CsvReportExporter object.
     */
    public CsvReportExporter() {

        // No setup is needed yet because each method writes a small placeholder file.
        // TODO: Add file path configuration later if the Integration module requires it.

    }

    /**
     * Exports the annual financial report to a CSV file.
     *
     * <p>
     * The exported report contains yearly income, expenses,
     * category totals, and overall budget performance.
     * </p>
     */
    public void exportAnnualReport() {

        // TODO: Replace this placeholder once ReportManager provides real annual data.
        String fileName = "annual_report_placeholder.csv";

        // For now, write simple sample rows so classmates can test CSV output.
        String[] csvLines = {
            "Section,Description,Amount",
            "Income,Total Income,52000.00",
            "Expense,Total Expenses,39750.00",
            "Savings,Net Savings,12250.00",
            "Budget Result,Surplus,12250.00"
        };

        writeLinesToCsv(fileName, csvLines);

    }

    /**
     * Exports a monthly financial summary to a CSV file.
     *
     * <p>
     * The exported report contains monthly income,
     * expenses, and net balance information.
     * </p>
     */
    public void exportMonthlySummary() {

        // TODO: Replace this placeholder once Storage provides transactions by month.
        String fileName = "monthly_summary_placeholder.csv";

        // The sample values demonstrate the structure of a future monthly summary export.
        String[] csvLines = {
            "Month,Income,Expenses,Net Balance",
            "January 2026,4250.00,3180.00,1070.00"
        };

        writeLinesToCsv(fileName, csvLines);

    }

    /**
     * Exports yearly category totals to a CSV file.
     *
     * <p>
     * The exported report lists totals for each
     * income and expense category.
     * </p>
     */
    public void exportCategoryTotals() {

        // TODO: Replace this placeholder once category totals are calculated automatically.
        String fileName = "category_totals_placeholder.csv";

        // Future implementation should retrieve formatted data from the reports module.
        String[] csvLines = {
            "Category,Type,Total",
            "Paycheck,Income,48000.00",
            "Freelance,Income,4000.00",
            "Housing,Expense,18000.00",
            "Food,Expense,6200.00",
            "Utilities,Expense,2400.00",
            "Transportation,Expense,3150.00"
        };

        writeLinesToCsv(fileName, csvLines);

    }

    /**
     * Exports the overall budget performance summary to a CSV file.
     *
     * <p>
     * The exported report summarizes the user's
     * financial performance for the selected year.
     * </p>
     */
    public void exportBudgetSummary() {

        // TODO: Replace this placeholder once the budget module provides actual goals.
        String fileName = "budget_summary_placeholder.csv";

        // These rows make the export useful for testing before full project integration.
        String[] csvLines = {
            "Metric,Amount,Notes",
            "Planned Annual Budget,42000.00,Sample target budget",
            "Actual Annual Spending,39750.00,Sample spending total",
            "Difference,2250.00,Under budget"
        };

        writeLinesToCsv(fileName, csvLines);

    }

    /**
     * Creates a new CSV file for report output.
     *
     * <p>
     * Creates the destination file that will store
     * the exported report data.
     * </p>
     */
    public void createCsvFile() {

        // TODO: Replace this default file with a user-selected report destination later.
        String fileName = "pfm_report_placeholder.csv";

        // Creating the writer also creates the file if it does not already exist.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            // This small header makes it clear that the file was created successfully.
            writer.write("Report,Status");
            writer.newLine();
            writer.write("Placeholder Report,Created");
            writer.newLine();

            // A console message helps beginners see where the file was written.
            System.out.println("Created CSV file: " + fileName);

        } catch (IOException exception) {

            // For now, print the message instead of throwing a custom project exception.
            // TODO: Replace this with the team's validation/error handling approach.
            System.out.println("Unable to create CSV file: " + exception.getMessage());

        }

    }

    /**
     * Writes formatted report data to the CSV file.
     *
     * <p>
     * Handles writing report content into the
     * output file in CSV format.
     * </p>
     */
    public void writeReportData() {

        // TODO: Replace this placeholder once ReportManager chooses which data to export.
        String fileName = "pfm_report_data_placeholder.csv";

        // This generic data lets the method be called directly during early testing.
        String[] csvLines = {
            "Report Name,Generated By,Status",
            "Sample Finance Report,CsvReportExporter,Placeholder data written"
        };

        writeLinesToCsv(fileName, csvLines);

    }

    /**
     * Writes the provided lines to a CSV file.
     *
     * @param fileName the name of the CSV file to create
     * @param csvLines the CSV rows to write to the file
     */
    private void writeLinesToCsv(String fileName, String[] csvLines) {

        // try-with-resources closes the writer automatically, even if writing fails.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            // Each string is already formatted as a simple CSV row for this placeholder.
            for (String csvLine : csvLines) {

                // Write one row at a time so the file is easy to understand and debug.
                writer.write(csvLine);
                writer.newLine();

            }

            // This message confirms successful export without requiring another class.
            System.out.println("Exported CSV file: " + fileName);

        } catch (IOException exception) {

            // Keep error handling beginner-friendly until the project has shared utilities.
            // TODO: Replace this with Validation or Integration error handling later.
            System.out.println("Unable to export CSV file: " + exception.getMessage());

        }

    }

}
