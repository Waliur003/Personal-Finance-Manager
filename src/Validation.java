/**
 * Provides static validation utilities for the Personal Finance Manager (PFM)
 * application.
 *
 * <p>
 * The Validation module is the single source of truth for deciding whether
 * user entries and data records are well-formed and correct. It validates
 * account credentials (usernames, passwords, secret questions and answers),
 * CSV file names and structure, dates, categories, amounts, and complete
 * transaction records, according to the PFM functional specification.
 * </p>
 *
 * <p>
 * All methods are static and stateless so that any other module (Accounts,
 * Storage, Data Audit, Insights, Reports, Integration) can call them without
 * creating an instance.
 * </p>
 *
 * @author Selina Zhu
 * @author Tasfia Rahman
 * @author David Guanga
 */

import java.nio.file.Path;

public class Validation
{
	/**
     * Validates a user's username.
     *
     * @param username the username entered by the user
     * @return true if the username satisfies all username requirements;
     *         otherwise false
     * @author Selina Zhu
     */
    public static boolean isValidUsername(String username) {
        return false;
    }

    /**
     * Validates a user's password.
     *
     * @param password the password entered by the user
     * @return true if the password satisfies all password requirements;
     *         otherwise false
     * @author Selina Zhu
     */
    public static boolean isValidPassword(String password) {
        return false;
    }

    /**
     * Validates the user's secret question.
     *
     * @param secretQuestion the secret question entered by the user
     * @return true if the secret question is valid;
     *         otherwise false
     * @author Selina Zhu
     */
    public static boolean isValidSecretQuestion(String secretQuestion) {
        return false;
    }

    /**
     * Validates the user's secret answer.
     *
     * @param secretAnswer the secret answer entered by the user
     * @return true if the secret answer is valid;
     *         otherwise false
     * @author Selina Zhu
     */
    public static boolean isValidSecretAnswer(String secretAnswer) {
        return false;
    }

	/**
     * Checks whether a file name follows the required YYYY.csv pattern.
     *
     * <p>
     * The file may be given as a bare name in the local folder or as a fully
     * qualified path name (FQPN); in either case the base name must be a
     * four-digit year followed by the .csv extension, for example 2025.csv.
     * </p>
     *
     * @param fileName the file name or fully qualified path to validate
     * @return true if the base file name matches YYYY.csv, false otherwise
     * @author Tasfia Rahman
     */
    public static boolean isValidFileName(String fileName) {
        return false; // TODO: implement
    }

    /**
     * Checks whether a CSV file exists, is readable, and begins with the
     * required header line.
     *
     * @param fileName the file name or fully qualified path of the CSV file
     * @return true if the file exists, can be read, and has a valid header,
     *         false otherwise
     * @author Tasfia Rahman
     */
    public static boolean isValidCsvFile(String fileName) {
        return false; // TODO: implement
    }

    /**
     * Checks whether a header line matches the required CSV header.
     *
     * <p>
     * The required header is {@code Date,Category,Amount}. The comparison is
     * case-insensitive and ignores surrounding whitespace and a leading
     * byte-order mark (BOM) if present.
     * </p>
     *
     * @param headerLine the first line of a CSV file
     * @return true if the header is valid, false otherwise
     * @author Tasfia Rahman
     */
    public static boolean isValidHeader(String headerLine) {
        return false; // TODO: implement
    }

    /**
     * Checks whether a CSV data file already exists for the given year in the
     * given directory.
     *
     * <p>
     * Used to warn the user before overwriting a previously loaded year.
     * </p>
     *
     * @param directory the directory to search
     * @param year      the four-digit year to look for
     * @return true if a file named YYYY.csv exists in the directory,
     *         false otherwise
     * @author Tasfia Rahman
     */
    public static boolean fileExistsForYear(String directory, int year) {
        return false; // TODO: implement
    }

	/**
	 * Checks to see if the date is in the proper format and represents a valid month, date, and year
	 *@param date takes in the date as a String object
	 *@return Returns true if date is in the correct format MM/DD/YYYY and checks to see if the date is valid
	 *@author David Guanga
	 * */
	private static boolean isValidDateFormat(String date){
		return false;
	}
	
	/**
	 * Checks to see if the year of each entry of the .csv file is the same
	 *@param year the year of the given .csv file 
	 *@return Returns true if the passed date is of the same year as all the other dates 
	 *@author David Guanga
	 * */
	public static boolean isSameYear(String year) {
		return false;
	}

	/**
	 * Checks to see if the category matches one of the accepted categories 
	 *@param categ the name of the category to validate
	 *@return Returns true if the input category is one of the following: Compensation, Allowance, Investments,
	 *Home, Utilities, Food, Appearance, Work, Education, Transportation, Entertainment, Professional Services, Other
	 *@author David Guanga
	 * */
	private static boolean isValidCategory(String categ){
		return false;
	}

	/**
	 * Checks to see if the amounts can be converted to a number
	 *@param am the amount to process and validate
	 *@return Returns true if the String am can be converted into a number 
	 *@author David Guanga
	 * */
	private static boolean isValidAmount(String am){
		return false;
	}

	/**
	 * Checks to see if an entry in the .csv file has a valid date, category, and amount
	 *@param take in a BudgetRecord that must be validated
	 *@return true if the records hava a valid date format, category, and amount
	 *@author David Guanga
	 * */
	public static boolean isValidRecord(){
		return false;
	}
}
