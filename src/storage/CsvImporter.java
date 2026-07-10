package storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles reading and parsing of CSV files into {@link Transaction} records,
 * including previewing file contents and filtering out invalid rows.
 *
 * @author Mohammed, Ayub, Fuad
 */
public class CsvImporter {

    private static final String EXPECTED_HEADER = "Date,Category,Amount";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private static final int PREVIEW_LINE_LIMIT = 20;

    public CsvImporter() {
    }

    public List<String> previewCsvFile(String filePath) {
        List<String> preview = new ArrayList<>();
        try {
            List<String> allLines = Files.readAllLines(Path.of(filePath));
            int limit = Math.min(allLines.size(), PREVIEW_LINE_LIMIT);
            for (int i = 0; i < limit; i++) {
                preview.add(allLines.get(i));
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to read file for preview: " + filePath, e);
        }
        return preview;
    }

    public List<Transaction> parseCsvFile(String filePath) {
        List<Transaction> transactions = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Path.of(filePath));
            if (lines.isEmpty()) {
                return transactions;
            }
            String header = lines.get(0).trim();
            if (!header.equalsIgnoreCase(EXPECTED_HEADER)) {
                throw new IllegalArgumentException(
                        "Invalid CSV header. Expected \"" + EXPECTED_HEADER + "\" but found \"" + header + "\"");
            }
            for (int i = 1; i < lines.size(); i++) {
                String line = lines.get(i);
                if (line.isBlank()) {
                    continue;
                }
                transactions.add(parseLine(line));
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to read CSV file: " + filePath, e);
        }
        return transactions;
    }

    public Transaction parseLine(String line) {
        String[] fields = line.split(",");
        if (fields.length != 3) {
            return null;
        }
        String rawDate = fields[0].trim();
        String category = fields[1].trim();
        String rawAmount = fields[2].trim();

        LocalDate date;
        try {
            date = LocalDate.parse(rawDate, DATE_FORMAT);
        } catch (DateTimeParseException e) {
            return null;
        }

        if (category.isEmpty()) {
            return null;
        }

        double amount;
        try {
            amount = Double.parseDouble(rawAmount);
        } catch (NumberFormatException e) {
            return null;
        }

        return new Transaction(date, category, amount);
    }

    public List<Transaction> filterInvalidRecords(List<Transaction> transactions) {
        List<Transaction> valid = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t != null) {
                valid.add(t);
            }
        }
        return valid;
    }
}