package storage;

import java.util.List;

/**
 * Handles reading and parsing of CSV files into {@link Transaction} records,
 * including previewing file contents and filtering out invalid rows.
 *
 * @author Mohammed, Ayub, Fuad
 */
public class CsvImporter {

    /**
     * Constructs a new {@code CsvImporter} instance.
     *
     * @author Mohammed, Ayub, Fuad
     */
    public CsvImporter() {
    }

    /**
     * Returns a preview of the contents of the specified CSV file without
     * fully parsing it into transactions.
     *
     * @param filePath the path to the CSV file
     * @return a list of raw lines representing a preview of the file
     * @author Mohammed, Ayub, Fuad
     */
    public List<String> previewCsvFile(String filePath) {
        return null;
    }

    /**
     * Parses the specified CSV file into a list of transactions.
     *
     * @param filePath the path to the CSV file
     * @return a list of parsed transactions
     * @author Mohammed, Ayub, Fuad
     */
    public List<Transaction> parseCsvFile(String filePath) {
        return null;
    }

    /**
     * Parses a single line of CSV text into a transaction.
     *
     * @param line the raw CSV line to parse
     * @return the parsed transaction
     * @author Mohammed, Ayub, Fuad
     */
    public Transaction parseLine(String line) {
        return null;
    }

    /**
     * Filters out invalid or malformed records from a list of parsed
     * transactions.
     *
     * @param transactions the transactions to filter
     * @return a list containing only valid transactions
     * @author Mohammed, Ayub, Fuad
     */
    public List<Transaction> filterInvalidRecords(List<Transaction> transactions) {
        return null;
    }
}
