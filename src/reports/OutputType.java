package reports;

/**
 * Represents the available output formats for generated reports in the
 * Personal Finance Manager (PFM) application.
 *
 * <p>
 * Reports may either be displayed on the console or exported to a
 * Comma-Separated Values (CSV) file.
 * </p>
 *
 * @author Alyssa Johnson
 * @version 1.0
 * @since 1.0
 */
public enum OutputType {

    /**
     * Displays the report on the console.
     */
    CONSOLE("Console"),

    /**
     * Exports the report to a CSV file.
     */
    CSV("CSV");

    /**
     * Stores a friendly label that can be shown in menus or messages.
     */
    private final String displayName;

    /**
     * Constructs an OutputType with a readable display name.
     *
     * @param displayName the user-friendly name for the output type
     */
    OutputType(String displayName) {

        // This value is temporary and simple, but it avoids hard-coded labels elsewhere.
        this.displayName = displayName;

    }

    /**
     * Gets the user-friendly name for this output type.
     *
     * @return the display name for this output type
     */
    public String getDisplayName() {

        // Return the label so menus can show "Console" instead of the enum name "CONSOLE".
        return displayName;

    }

    /**
     * Returns the user-friendly name for this output type.
     *
     * @return the display name for this output type
     */
    @Override
    public String toString() {

        // Keeping toString readable is helpful for simple print statements in early testing.
        return displayName;

    }

}
