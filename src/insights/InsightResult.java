package insights;

import java.util.List;
import java.util.Map;

/**
 * Stores the final results produced by the Insights module.
 *
 * This record is immutable and contains all calculated values
 * for a yearly budget analysis.
 *
 * @author Waliur Sun
 */
public record InsightResult(

        int year,

        int totalIncome,

        int totalExpenses,

        int netBalance,

        BudgetStatus budgetStatus,

        Map<Integer, Integer> monthlyTotals,

        Map<String, Integer> categoryTotals,

        Map<String, Double> categoryPercentages,

        double averageMonthlySpending,

        List<String> recommendations

) {

    /**
     * Validates all values before creating the record.
     */
    public InsightResult {

        if (year < 1900 || year > 2099) {
            throw new IllegalArgumentException(
                    "Invalid year.");
        }

        if (totalIncome < 0) {
            throw new IllegalArgumentException(
                    "Income cannot be negative.");
        }

        if (totalExpenses < 0) {
            throw new IllegalArgumentException(
                    "Expenses cannot be negative.");
        }

        if (budgetStatus == null) {
            throw new IllegalArgumentException(
                    "Budget status cannot be null.");
        }

        if (monthlyTotals == null) {
            throw new IllegalArgumentException(
                    "Monthly totals cannot be null.");
        }

        if (categoryTotals == null) {
            throw new IllegalArgumentException(
                    "Category totals cannot be null.");
        }

        if (categoryPercentages == null) {
            throw new IllegalArgumentException(
                    "Category percentages cannot be null.");
        }

        if (recommendations == null) {
            throw new IllegalArgumentException(
                    "Recommendations cannot be null.");
        }

        /*
         * Make defensive copies so that callers
         * cannot modify the stored data.
         */
        monthlyTotals = Map.copyOf(monthlyTotals);
        categoryTotals = Map.copyOf(categoryTotals);
        categoryPercentages = Map.copyOf(categoryPercentages);
        recommendations = List.copyOf(recommendations);
    }
}