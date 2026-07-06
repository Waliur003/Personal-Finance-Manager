package storage;

import java.util.List;

/**
 * Represents a user's budget for a given year, containing a collection of
 * {@link Transaction} records and operations to query and modify them.
 *
 * @author Mohammed, Ayub, Fuad
 */
public class Budget {

    /**
     * Constructs a new, empty {@code Budget} for the specified year.
     *
     * @param year the year this budget represents
     * @author Mohammed, Ayub, Fuad
     */
    public Budget(int year) {
    }

    /**
     * Returns the year associated with this budget.
     *
     * @return the budget year
     * @author Mohammed, Ayub, Fuad
     */
    public int getYear() {
        return 0;
    }

    /**
     * Returns all transactions currently stored in this budget.
     *
     * @return a list of all transactions
     * @author Mohammed, Ayub, Fuad
     */
    public List<Transaction> getTransactions() {
        return null;
    }

    /**
     * Adds a new transaction to this budget.
     *
     * @param transaction the transaction to add
     * @author Mohammed, Ayub, Fuad
     */
    public void addTransaction(Transaction transaction) {
    }

    /**
     * Returns all transactions that occurred in the specified month.
     *
     * @param month the month to filter by (1-12)
     * @return a list of transactions occurring in the given month
     * @author Mohammed, Ayub, Fuad
     */
    public List<Transaction> getTransactionsByMonth(int month) {
        return null;
    }

    /**
     * Returns all transactions that belong to the specified category.
     *
     * @param category the category to filter by
     * @return a list of transactions belonging to the given category
     * @author Mohammed, Ayub, Fuad
     */
    public List<Transaction> getTransactionsByCategory(String category) {
        return null;
    }
}
