package insights_fixed;

import java.util.List;

/**
 * Calculates high-level budget statistics such as income, expenses, and net balance.
 *
 * <p>Each transaction row is expected to contain three values in this order:
 * Date, Category, Amount. Amount is positive for income and negative for expenses.</p>
 *
 * @author Waliur Sun
 */
public class BudgetStatistics {

    /** Index of the amount column within a transaction row. */
    private static final int AMOUNT_INDEX = 2;

    /**
     * Constructs a new, stateless budget statistics calculator.
     */
    public BudgetStatistics() {
    }

    /**
     * Calculates the total income from all positive transaction amounts.
     *
     * @param transactions list of transaction rows
     * @return total income
     * Author: Waliur Sun
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
     * Calculates the total expenses from all negative transaction amounts.
     *
     * <p>The returned value is positive. For example, expenses of -500 and -300
     * return 800, not -800.</p>
     *
     * @param transactions list of transaction rows
     * @return total expenses as a positive number
     * Author: Waliur Sun
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
     * Calculates the net balance for the year.
     *
     * @param totalIncome total yearly income
     * @param totalExpenses total yearly expenses as a positive number
     * @return income minus expenses
     * Author: Waliur Sun
     */
    public int calculateNetBalance(int totalIncome, int totalExpenses) {
        return totalIncome - totalExpenses;
    }

    /**
     * Determines if the budget ended in surplus, deficit, or balance.
     *
     * @param netBalance income minus expenses
     * @return budget status
     * Author: Waliur Sun
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
     * Parses the amount field safely.
     *
     * @param transaction one transaction row
     * @return parsed amount
     * Author: Waliur Sun
     */
    private int parseAmount(String[] transaction) {
        if (transaction == null || transaction.length <= AMOUNT_INDEX) {
            throw new IllegalArgumentException("Each transaction must contain Date, Category, and Amount.");
        }

        try {
            return Integer.parseInt(transaction[AMOUNT_INDEX].trim());
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Amount must be a whole dollar integer: " + transaction[AMOUNT_INDEX], exception);
        }
    }
}