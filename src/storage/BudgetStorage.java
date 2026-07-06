package storage;

import java.util.List;

/**
 * Provides CRUD (Create, Read, Update, Delete) operations for persisting
 * {@link Budget} objects to and from data files.
 *
 * @author Mohammed, Ayub, Fuad
 */
public class BudgetStorage {

    /**
     * Constructs a new {@code BudgetStorage} instance.
     *
     * @author Mohammed, Ayub, Fuad
     */
    public BudgetStorage() {
    }

    /**
     * Creates and persists a new budget for the given user and year.
     *
     * @param username the username who owns the budget
     * @param budget   the budget to create
     * @author Mohammed, Ayub, Fuad
     */
    public void createBudget(String username, Budget budget) {
    }

    /**
     * Reads and returns the budget for the given user and year.
     *
     * @param username the username who owns the budget
     * @param year     the year of the budget to read
     * @return the requested budget
     * @author Mohammed, Ayub, Fuad
     */
    public Budget readBudget(String username, int year) {
        return null;
    }

    /**
     * Updates an existing budget with new data.
     *
     * @param username the username who owns the budget
     * @param budget   the budget containing updated data
     * @author Mohammed, Ayub, Fuad
     */
    public void updateBudget(String username, Budget budget) {
    }

    /**
     * Deletes the budget for the given user and year.
     *
     * @param username the username who owns the budget
     * @param year     the year of the budget to delete
     * @author Mohammed, Ayub, Fuad
     */
    public void deleteBudget(String username, int year) {
    }

    /**
     * Determines whether a budget exists for the given user and year.
     *
     * @param username the username who owns the budget
     * @param year     the year to check
     * @return {@code true} if a budget exists for the given year, {@code false} otherwise
     * @author Mohammed, Ayub, Fuad
     */
    public boolean yearExists(String username, int year) {
        return false;
    }

    /**
     * Lists all years for which a budget exists for the given user.
     *
     * @param username the username to look up
     * @return a list of years that have an associated budget
     * @author Mohammed, Ayub, Fuad
     */
    public List<Integer> listYearsForUser(String username) {
        return null;
    }
}
