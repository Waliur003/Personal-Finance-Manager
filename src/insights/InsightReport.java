package insights;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Creates, prints, and exports the financial insight report.
 *
 * @author Waliur Sun
 */
public class InsightReport {

    /**
     * Generates a formatted report.
     *
     * @param result completed insight result
     * @return formatted report as a String
     */
    public String generateSummary(InsightResult result) {

        validateResult(result);

        StringBuilder report = new StringBuilder();

        report.append("=====================================\n");
        report.append(" Financial Insights Report\n");
        report.append("=====================================\n\n");

        report.append("Year: ")
              .append(result.year())
              .append("\n");

        report.append("Total Income: $")
              .append(result.totalIncome())
              .append("\n");

        report.append("Total Expenses: $")
              .append(result.totalExpenses())
              .append("\n");

        report.append("Net Balance: $")
              .append(result.netBalance())
              .append("\n");

        report.append("Budget Status: ")
              .append(result.budgetStatus())
              .append("\n");

        report.append("Average Monthly Spending: $")
              .append(String.format("%.2f",
                      result.averageMonthlySpending()))
              .append("\n\n");

        report.append("Monthly Totals\n");
        report.append("-----------------------------\n");

        for (Map.Entry<Integer, Integer> entry
                : result.monthlyTotals().entrySet()) {

            report.append("Month ")
                  .append(entry.getKey())
                  .append(": $")
                  .append(entry.getValue())
                  .append("\n");
        }

        report.append("\nExpense Categories\n");
        report.append("-----------------------------\n");

        for (Map.Entry<String, Integer> entry
                : result.categoryTotals().entrySet()) {

            double percent =
                    result.categoryPercentages()
                          .getOrDefault(entry.getKey(), 0.0);

            report.append(entry.getKey())
                  .append(": $")
                  .append(entry.getValue())
                  .append(" (")
                  .append(String.format("%.2f", percent))
                  .append("%)\n");
        }

        report.append("\nRecommendations\n");
        report.append("-----------------------------\n");

        for (String recommendation
                : result.recommendations()) {

            report.append("- ")
                  .append(recommendation)
                  .append("\n");
        }

        return report.toString();
    }

    /**
     * Prints the report to the console.
     *
     * @param result completed insight result
     */
    public void printReport(InsightResult result) {

        System.out.println(generateSummary(result));
    }

    /**
     * Saves the report to a CSV file.
     *
     * @param result completed insight result
     * @param filePath destination CSV file
     * @throws IOException if writing fails
     */
    public void saveReportToCSV(
            InsightResult result,
            String filePath)
            throws IOException {

        validateResult(result);

        if (filePath == null || filePath.isBlank()) {
            throw new IllegalArgumentException(
                    "File path cannot be empty.");
        }

        try (PrintWriter writer =
                     new PrintWriter(
                             new FileWriter(filePath))) {

            writer.println("Section,Name,Value,Percentage");

            writer.println("Summary,Total Income,"
                    + result.totalIncome() + ",");

            writer.println("Summary,Total Expenses,"
                    + result.totalExpenses() + ",");

            writer.println("Summary,Net Balance,"
                    + result.netBalance() + ",");

            writer.println("Summary,Budget Status,"
                    + result.budgetStatus() + ",");

            writer.println("Summary,Average Monthly Spending,"
                    + String.format("%.2f",
                    result.averageMonthlySpending())
                    + ",");

            for (Map.Entry<Integer, Integer> entry
                    : result.monthlyTotals().entrySet()) {

                writer.println("Month,"
                        + entry.getKey()
                        + ","
                        + entry.getValue()
                        + ",");
            }

            for (Map.Entry<String, Integer> entry
                    : result.categoryTotals().entrySet()) {

                writer.println("Category,"
                        + entry.getKey()
                        + ","
                        + entry.getValue()
                        + ","
                        + String.format("%.2f",
                        result.categoryPercentages()
                                .get(entry.getKey())));
            }

            for (String recommendation
                    : result.recommendations()) {

                writer.println("Recommendation,\""
                        + recommendation.replace("\"", "\"\"")
                        + "\",,");
            }
        }
    }

    /**
     * Ensures the result object exists.
     *
     * @param result insight result
     */
    private void validateResult(InsightResult result) {

        if (result == null) {
            throw new IllegalArgumentException(
                    "InsightResult cannot be null.");
        }
    }
}