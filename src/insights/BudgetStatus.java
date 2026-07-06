package insights_fixed;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Performs detailed spending calculations for monthly totals, category totals,
 * category percentages, and average monthly spending.
 *
 * @author Waliur Sun
 */
public class SpendingAnalyzer {

    /** Index of the date column within a transaction row. */
    private static final int DATE_INDEX = 0;

    /** Index of the category column within a transaction row. */
    private static final int CATEGORY_INDEX = 1;

    /** Index of the amount column within a transaction row. */
    private static final int AMOUNT_INDEX = 2;

    /** Number of months used when computing monthly totals and averages. */
    private static final int MONTHS_IN_YEAR = 12;

    /**
     * Constructs a new, stateless spending analyzer.
     */
    public SpendingAnalyzer() {
    }

    /**
     * Calculates the net total for each month.
     *
     * <p>Income increases the monthly total and expenses decrease the monthly total.</p>
     *
     * @param transactions list of transaction rows
     * @return map of month number to net monthly total
     * Author: Waliur Sun
     */
    public Map<Integer, Integer> calculateMonthlyTotals(List<String[]> transactions) {
        validateTransactions(transactions);

        Map<Integer, Integer> monthlyTotals = createEmptyMonthlyMap();

        for (String[] transaction : transactions) {
            int month = parseMonth(transaction);
            int amount = parseAmount(transaction);
            monthlyTotals.put(month, monthlyTotals.get(month) + amount);
        }

        return monthlyTotals;
    }

    /**
     * Calculates the total spending for each expense category.
     *
     * <p>Only negative amounts are counted because category percentages should focus
     * on spending patterns, not income sources.</p>
     *
     * @param transactions list of transaction rows
     * @return map of expense category to total expense amount as a positive number
     * Author: Waliur Sun
     */
    public Map<String, Integer> calculateCategoryTotals(List<String[]> transactions) {
        validateTransactions(transactions);

        Map<String, Integer> categoryTotals = new LinkedHashMap<>();

        for (String[] transaction : transactions) {
            int amount = parseAmount(transaction);
            if (amount < 0) {
                String category = parseCategory(transaction);
                int currentTotal = categoryTotals.getOrDefault(category, 0);
                categoryTotals.put(category, currentTotal + Math.abs(amount));
            }
        }

        return categoryTotals;
    }

    /**
     * Calculates each spending category as a percentage of total expenses.
     *
     * @param categoryTotals map of category totals
     * @param totalExpenses total yearly expenses as a positive number
     * @return map of category to expense percentage
     * Author: Waliur Sun
     */
    public Map<String, Double> calculateCategoryPercentages(Map<String, Integer> categoryTotals, int totalExpenses) {
        if (categoryTotals == null) {
            throw new IllegalArgumentException("Category totals cannot be null.");
        }
        if (totalExpenses < 0) {
            throw new IllegalArgumentException("Total expenses cannot be negative.");
        }

        Map<String, Double> categoryPercentages = new LinkedHashMap<>();

        for (Map.Entry<String, Integer> entry : categoryTotals.entrySet()) {
            double percentage = 0.0;
            if (totalExpenses > 0) {
                percentage = (entry.getValue() * 100.0) / totalExpenses;
            }
            categoryPercentages.put(entry.getKey(), percentage);
        }

        return categoryPercentages;
    }

    /**
     * Calculates the average monthly spending for the year.
     *
     * @param totalExpenses total yearly expenses as a positive number
     * @return average monthly spending
     * Author: Waliur Sun
     */
    public double calculateAverageMonthlySpending(int totalExpenses) {
        if (totalExpenses < 0) {
            throw new IllegalArgumentException("Total expenses cannot be negative.");
        }
        return totalExpenses / (double) MONTHS_IN_YEAR;
    }

    /**
     * Creates a map with all 12 months initialized to zero.
     *
     * @return empty monthly map
     * Author: Waliur Sun
     */
    private Map<Integer, Integer> createEmptyMonthlyMap() {
        Map<Integer, Integer> monthlyTotals = new LinkedHashMap<>();
        for (int month = 1; month <= MONTHS_IN_YEAR; month++) {
            monthlyTotals.put(month, 0);
        }
        return monthlyTotals;
    }

    /**
     * Validates that the transaction list is not null.
     *
     * @param transactions list of transaction rows
     * Author: Waliur Sun
     */
    private void validateTransactions(List<String[]> transactions) {
        if (transactions == null) {
            throw new IllegalArgumentException("Transaction list cannot be null.");
        }
    }

    /**
     * Parses the month from a MM/DD/YYYY date.
     *
     * @param transaction one transaction row
     * @return month number from 1 to 12
     * Author: Waliur Sun
     */
    private int parseMonth(String[] transaction) {
        validateTransactionRow(transaction);
        String date = transaction[DATE_INDEX].trim();
        String[] parts = date.split("/");

        if (parts.length != 3) {
            throw new IllegalArgumentException("Date must use MM/DD/YYYY format: " + date);
        }

        try {
            int month = Integer.parseInt(parts[0]);
            if (month < 1 || month > 12) {
                throw new IllegalArgumentException("Month must be between 1 and 12: " + date);
            }
            return month;
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Month must be numeric: " + date, exception);
        }
    }

    /**
     * Parses the category from a transaction row.
     *
     * @param transaction one transaction row
     * @return trimmed category name
     * Author: Waliur Sun
     */
    private String parseCategory(String[] transaction) {
        validateTransactionRow(transaction);
        String category = transaction[CATEGORY_INDEX].trim();
        if (category.isEmpty()) {
            throw new IllegalArgumentException("Category cannot be blank.");
        }
        return category;
    }

    /**
     * Parses the amount from a transaction row.
     *
     * @param transaction one transaction row
     * @return parsed amount
     * Author: Waliur Sun
     */
    private int parseAmount(String[] transaction) {
        validateTransactionRow(transaction);
        try {
            return Integer.parseInt(transaction[AMOUNT_INDEX].trim());
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Amount must be a whole dollar integer: " + transaction[AMOUNT_INDEX], exception);
        }
    }

    /**
     * Validates that one transaction row has Date, Category, and Amount.
     *
     * @param transaction one transaction row
     * Author: Waliur Sun
     */
    private void validateTransactionRow(String[] transaction) {
        if (transaction == null || transaction.length <= AMOUNT_INDEX) {
            throw new IllegalArgumentException("Each transaction must contain Date, Category, and Amount.");
        }
    }
}