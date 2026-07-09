package insights;

import java.util.List;

/**
 * Calculates high-level budget statistics such as income, expenses,
 * net balance, and budget status.
 *
 * Each transaction must contain:
 * Date, Category, Amount
 *
 * Amounts:
 * Positive = Income
 * Negative = Expense
 *
 * @author Waliur Sun
 */
public class BudgetStatistics {

    /** Index of the amount column in a transaction row. */
    private static final int AMOUNT_INDEX = 2;

    /**
     * Calculates the total yearly income.
     *
     * @param transactions list of transaction rows
     * @return total income
     */
    public int calculateTotalIncome(List<String[]> transactions) {

        validateTransactions(transactions);

        int totalIncome = 0;

        for (String[] transaction : transactions) {
            int amount = parseAmount(transaction);

            if (amount > 0) {
                totalIncome += amount;
            }
        }

        return totalIncome;
    }

    /**
     * Calculates the total yearly expenses.
     *
     * Expenses are returned as a positive value.
     *
     * @param transactions list of transaction rows
     * @return total expenses
     */
    public int calculateTotalExpenses(List<String[]> transactions) {

        validateTransactions(transactions);

        int totalExpenses = 0;

        for (String[] transaction : transactions) {

            int amount = parseAmount(transaction);

            if (amount < 0) {
                totalExpenses += Math.abs(amount);
            }
        }

        return totalExpenses;
    }

    /**
     * Calculates the net yearly balance.
     *
     * @param totalIncome total income
     * @param totalExpenses total expenses
     * @return net balance
     */
    public int calculateNetBalance(int totalIncome, int totalExpenses) {
        return totalIncome - totalExpenses;
    }

    /**
     * Determines the budget status.
     *
     * @param netBalance yearly net balance
     * @return budget status
     */
    public BudgetStatus determineBudgetStatus(int netBalance) {

        if (netBalance > 0) {
            return BudgetStatus.SURPLUS;
        }

        if (netBalance < 0) {
            return BudgetStatus.DEFICIT;
        }

        return BudgetStatus.BALANCED;
    }

    /**
     * Validates the transaction list.
     *
     * @param transactions list of transactions
     */
    private void validateTransactions(List<String[]> transactions) {

        if (transactions == null) {
            throw new IllegalArgumentException(
                    "Transaction list cannot be null.");
        }
    }

    /**
     * Parses the amount field.
     *
     * @param transaction transaction row
     * @return amount
     */
    private int parseAmount(String[] transaction) {

        if (transaction == null || transaction.length <= AMOUNT_INDEX) {
            throw new IllegalArgumentException(
                    "Each transaction must contain Date, Category, and Amount.");
        }

        try {
            return Integer.parseInt(transaction[AMOUNT_INDEX].trim());
        }
        catch (NumberFormatException exception) {

            throw new IllegalArgumentException(
                    "Invalid amount: " + transaction[AMOUNT_INDEX],
                    exception);
        }
    }
}