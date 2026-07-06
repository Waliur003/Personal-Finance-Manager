package insights;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Formats, prints, and exports the final insights report.
 *
 * @author Waliur Sun
 */
public class InsightReport {

    /**
     * Creates a readable text summary of the insights.
     *
     * @param result calculated insight result
     * @return formatted report text
     * @author Waliur Sun
     */
    public String generateSummary(InsightResult result) {
        validateResult(result);

        StringBuilder summary = new StringBuilder();

        summary.append("===== Financial Insights for ").append(result.year()).append(" =====\n\n");
        summary.append("Total Income: $").append(result.totalIncome()).append("\n");
        summary.append("Total Expenses: $").append(result.totalExpenses()).append("\n");
        summary.append("Net Balance: $").append(result.netBalance()).append("\n");
        summary.append("Budget Status: ").append(result.budgetStatus()).append("\n");
        summary.append("Average Monthly Spending: $")
                .append(String.format("%.2f", result.averageMonthlySpending()))
                .append("\n\n");

        summary.append("Monthly Net Totals:\n");
        for (Map.Entry<Integer, Integer> entry : result.monthlyTotals().entrySet()) {
            summary.append("Month ").append(entry.getKey())
                    .append(": $").append(entry.getValue()).append("\n");
        }

        summary.append("\nExpense Category Totals and Percentages:\n");
        for (Map.Entry<String, Integer> entry : result.categoryTotals().entrySet()) {
            double percentage = result.categoryPercentages().getOrDefault(entry.getKey(), 0.0);
            summary.append(entry.getKey())
                    .append(": $").append(entry.getValue())
                    .append(" (").append(String.format("%.2f", percentage)).append("%)\n");
        }

        summary.append("\nRecommendations:\n");
        for (String recommendation : result.recommendations()) {
            summary.append("- ").append(recommendation).append("\n");
        }

        return summary.toString();
    }

    /**
     * Prints the insight report to the console.
     *
     * @param result calculated insight result
     * @author Waliur Sun
     */
    public void printReport(InsightResult result) {
        System.out.println(generateSummary(result));
    }

    /**
     * Saves the insight report to a CSV file.
     *
     * @param result calculated insight result
     * @param filePath destination file path
     * @throws IOException if the file cannot be written
     * @author Waliur Sun
     */
    public void saveReportToCSV(InsightResult result, String filePath) throws IOException {
        validateResult(result);
        if (filePath == null || filePath.isBlank()) {
            throw new IllegalArgumentException("File path cannot be blank.");
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println("Section,Name,Amount,Percentage");
            writer.println("Summary,Total Income," + result.totalIncome() + ",");
            writer.println("Summary,Total Expenses," + result.totalExpenses() + ",");
            writer.println("Summary,Net Balance," + result.netBalance() + ",");
            writer.println("Summary,Budget Status," + result.budgetStatus() + ",");
            writer.println("Summary,Average Monthly Spending," + String.format("%.2f", result.averageMonthlySpending()) + ",");

            for (Map.Entry<Integer, Integer> entry : result.monthlyTotals().entrySet()) {
                writer.println("Monthly,Month " + entry.getKey() + "," + entry.getValue() + ",");
            }

            for (Map.Entry<String, Integer> entry : result.categoryTotals().entrySet()) {
                double percentage = result.categoryPercentages().getOrDefault(entry.getKey(), 0.0);
                writer.println("Category," + entry.getKey() + "," + entry.getValue() + ","
                        + String.format("%.2f", percentage));
            }

            for (String recommendation : result.recommendations()) {
                writer.println("Recommendation,\"" + recommendation.replace("\"", "\"\"") + "\",,");
            }
        }
    }

    /**
     * Validates that the result is not null.
     *
     * @param result calculated insight result
     * @author Waliur Sun
     */
    private void validateResult(InsightResult result) {
        if (result == null) {
            throw new IllegalArgumentException("Insight result cannot be null.");
        }
    }
}