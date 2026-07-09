package insights;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Main controller for the Insights module.
 *
 * <p>This class coordinates all calculations performed by the
 * Insights module and serves as the entry point used by the
 * Integration Team.</p>
 *
 * @author Waliur Sun
 */
public class InsightsManager {

    /** Performs overall budget calculations. */
    private final BudgetStatistics budgetStatistics;

    /** Performs spending analysis. */
    private final SpendingAnalyzer spendingAnalyzer;

    /** Generates financial recommendations. */
    private final RecommendationEngine recommendationEngine;

    /** Formats and exports reports. */
    private final InsightReport insightReport;

    /**
     * Creates a new InsightsManager.
     */
    public InsightsManager() {

        budgetStatistics = new BudgetStatistics();
        spendingAnalyzer = new SpendingAnalyzer();
        recommendationEngine = new RecommendationEngine();
        insightReport = new InsightReport();
    }

    /**
     * Generates a complete yearly insight report.
     *
     * @param transactions yearly transaction list
     * @return completed InsightResult
     */
    public InsightResult generateInsights(
            List<String[]> transactions) {

        validateTransactions(transactions);

        int year = extractYear(transactions);

        int totalIncome =
                budgetStatistics.calculateTotalIncome(transactions);

        int totalExpenses =
                budgetStatistics.calculateTotalExpenses(transactions);

        int netBalance =
                budgetStatistics.calculateNetBalance(
                        totalIncome,
                        totalExpenses);

        BudgetStatus budgetStatus =
                budgetStatistics.determineBudgetStatus(
                        netBalance);

        Map<Integer, Integer> monthlyTotals =
                spendingAnalyzer.calculateMonthlyTotals(
                        transactions);

        Map<String, Integer> categoryTotals =
                spendingAnalyzer.calculateCategoryTotals(
                        transactions);

        Map<String, Double> categoryPercentages =
                spendingAnalyzer.calculateCategoryPercentages(
                        categoryTotals,
                        totalExpenses);

        double averageMonthlySpending =
                spendingAnalyzer.calculateAverageMonthlySpending(
                        totalExpenses);

        List<String> recommendations =
                recommendationEngine.generateRecommendations(
                        budgetStatus,
                        netBalance,
                        categoryPercentages);

        return new InsightResult(
                year,
                totalIncome,
                totalExpenses,
                netBalance,
                budgetStatus,
                monthlyTotals,
                categoryTotals,
                categoryPercentages,
                averageMonthlySpending,
                recommendations);
    }

    /**
     * Returns the formatted yearly report.
     *
     * @param transactions yearly transaction list
     * @return report text
     */
    public String analyzeYear(
            List<String[]> transactions) {

        InsightResult result =
                generateInsights(transactions);

        return insightReport.generateSummary(result);
    }

    /**
     * Prints the report to the console.
     *
     * @param transactions yearly transaction list
     */
    public void displayInsights(
            List<String[]> transactions) {

        InsightResult result =
                generateInsights(transactions);

        insightReport.printReport(result);
    }

    /**
     * Exports the report to a CSV file.
     *
     * @param transactions yearly transaction list
     * @param filePath destination file
     * @throws IOException if export fails
     */
    public void exportInsights(
            List<String[]> transactions,
            String filePath)
            throws IOException {

        InsightResult result =
                generateInsights(transactions);

        insightReport.saveReportToCSV(
                result,
                filePath);
    }

    /**
     * Ensures the transaction list is valid.
     *
     * @param transactions transaction list
     */
    private void validateTransactions(
            List<String[]> transactions) {

        if (transactions == null) {
            throw new IllegalArgumentException(
                    "Transaction list cannot be null.");
        }

        if (transactions.isEmpty()) {
            throw new IllegalArgumentException(
                    "Transaction list cannot be empty.");
        }
    }

    /**
     * Extracts the year from the first transaction.
     *
     * Expected format:
     *
     * MM/DD/YYYY
     *
     * @param transactions transaction list
     * @return budget year
     */
    private int extractYear(
            List<String[]> transactions) {

        String date =
                transactions.get(0)[0];

        String[] parts =
                date.split("/");

        if (parts.length != 3) {
            throw new IllegalArgumentException(
                    "Invalid date format.");
        }

        return Integer.parseInt(parts[2]);
    }

    /**
     * Returns the BudgetStatistics object.
     *
     * @return BudgetStatistics
     */
    public BudgetStatistics getBudgetStatistics() {
        return budgetStatistics;
    }

    /**
     * Returns the SpendingAnalyzer object.
     *
     * @return SpendingAnalyzer
     */
    public SpendingAnalyzer getSpendingAnalyzer() {
        return spendingAnalyzer;
    }

    /**
     * Returns the RecommendationEngine object.
     *
     * @return RecommendationEngine
     */
    public RecommendationEngine getRecommendationEngine() {
        return recommendationEngine;
    }

    /**
     * Returns the InsightReport object.
     *
     * @return InsightReport
     */
    public InsightReport getInsightReport() {
        return insightReport;
    }
}