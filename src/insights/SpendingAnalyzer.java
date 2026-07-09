package insights;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Performs spending analysis for yearly transactions.
 *
 * @author Waliur Sun
 */
public class SpendingAnalyzer {

    /** Index of the date column. */
    private static final int DATE_INDEX = 0;

    /** Index of the amount column. */
    private static final int AMOUNT_INDEX = 2;

    /**
     * Calculates the net total for each month.
     *
     * @param transactions yearly transaction list
     * @return map of month -> net amount
     */
    public Map<Integer, Integer> calculateMonthlyTotals(
            List<String[]> transactions) {

        validateTransactions(transactions);

        Map<Integer, Integer> monthlyTotals = new HashMap<>();

        for (int month = 1; month <= 12; month++) {
            monthlyTotals.put(month, 0);
        }

        for (String[] transaction : transactions) {

            int month = extractMonth(transaction[DATE_INDEX]);
            int amount = Integer.parseInt(
                    transaction[AMOUNT_INDEX].trim());

            monthlyTotals.put(
                    month,
                    monthlyTotals.get(month) + amount);
        }

        return monthlyTotals;
    }

    /**
     * Calculates total expenses by category.
     *
     * Expenses are stored as positive values.
     *
     * @param transactions yearly transaction list
     * @return category totals
     */
    public Map<String, Integer> calculateCategoryTotals(
            List<String[]> transactions) {

        validateTransactions(transactions);

        Map<String, Integer> categoryTotals =
                new HashMap<>();

        for (String[] transaction : transactions) {

            String category = transaction[1].trim();

            int amount = Integer.parseInt(
                    transaction[AMOUNT_INDEX].trim());

            if (amount < 0) {

                categoryTotals.put(
                        category,
                        categoryTotals.getOrDefault(
                                category,
                                0)
                                + Math.abs(amount));
            }
        }

        return categoryTotals;
    }

    /**
     * Calculates the percentage of total expenses
     * spent in each category.
     *
     * @param categoryTotals totals by category
     * @param totalExpenses total yearly expenses
     * @return category percentages
     */
    public Map<String, Double> calculateCategoryPercentages(
            Map<String, Integer> categoryTotals,
            int totalExpenses) {

        if (categoryTotals == null) {
            throw new IllegalArgumentException(
                    "Category totals cannot be null.");
        }

        Map<String, Double> percentages =
                new HashMap<>();

        if (totalExpenses == 0) {
            return percentages;
        }

        for (Map.Entry<String, Integer> entry
                : categoryTotals.entrySet()) {

            double percentage =
                    (entry.getValue() * 100.0)
                            / totalExpenses;

            percentages.put(
                    entry.getKey(),
                    percentage);
        }

        return percentages;
    }

    /**
     * Calculates the average monthly spending.
     *
     * @param totalExpenses yearly expenses
     * @return average monthly spending
     */
    public double calculateAverageMonthlySpending(
            int totalExpenses) {

        return totalExpenses / 12.0;
    }

    /**
     * Extracts the month from a date.
     *
     * Expected format:
     * MM/DD/YYYY
     *
     * @param date date string
     * @return month number
     */
    private int extractMonth(String date) {

        String[] parts = date.split("/");

        if (parts.length != 3) {
            throw new IllegalArgumentException(
                    "Invalid date format.");
        }

        int month = Integer.parseInt(parts[0]);

        if (month < 1 || month > 12) {
            throw new IllegalArgumentException(
                    "Invalid month.");
        }

        return month;
    }

    /**
     * Validates the transaction list.
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
}