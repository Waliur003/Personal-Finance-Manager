
public class AccountService {
	
	/**
	 * @param username the user's username
	 * @param password the user's password
	 * @param secretQuestion the user's own secret question
	 * @param secretAnswer the user's own secret answer
	 * @return whether the account creation was succesful
	 * @author Dwann
	 */
	public static boolean createAccount(String username, String password, String secretQuestion, String secretAnswer) {
		// requirements: the username, password, and secret question/answer is valid. The username must be unique.
		// 
		// postconditions: an account object is created with a username, hased password
		// and secret question/answer. The account is stored into the appropiate CSV file. The password in the CSV file 
		// will be hashed
		return true;
	}
	
	/**
	 * logs in a user into an account
	 * @param username the users username
	 * @param password the users password
	 * @return whether or not the login was succesful
	 * @author Harmony
	 */
	public static boolean login(String username, String password) {
		// requirements: The username must be within the CSV file. The user's account info must be read from the file.
		// Upon hashing the password witt the same hasing algorithm, it must match the hashed password read from file.
		//
		// postconditions: The user can go to the next page set by integration to access there audits n stuff
		return true;
	}
	
	/**
	 * saves any changes to the appropiate file(s), and sends the user
	 * back to the login screen.
	 * @return whether or not the login was succesful
	 * @author Sakif
	 */
	public static boolean logout() {
		// postconditions: any account changes made will be written to the accounts CSV file.
		// the user will return to the login page.
		return true;
	}
	
	/**
	 * prompts the user to to answer their secret question. Prompts the user to change
	 * their password if they answered correctly
	 * @author Harmony
	 */
	public static void forgotPassword() {
		// requirements: The prompt requesting the user to answer their secret question
		// must be called. Their answer must be correct.
		// postcondition: The user is prompted to change their password.
	}
	
	/**
	 * prompts the user to to answer their secret question. Prompts the user to change
	 * their username if they answered correctly
	 * @author Dwann
	 */
	public static void forgotUsername() {
		// requirements: The user must correctly answer their secret question.
		// postcondition: The user is prompted to change their username.
	}
	
	/**
	 * verifies the secretAnswer from the user with the one in their account
	 * @param secretAnswer the secret answer entered from the user
	 * @param account the account of said user to compare the secret questions
	 * @return whether or not the answers matched
	 * @author Harmony
	 */
	private static boolean checkSecretAnswer(String secretAnswer, Account account) {
		// postcondition: if the secret answer matches the accounts, the user can change whatever they need to accordingly.
		return true;
	}
	
	/**
	 * changes the password of a user's account
	 * @param newPassword the new password the user wants to set
	 * @param account the account of said user in question
	 * @return whether or not the password change was succesful
	 * @author Sakif
	 */
	private static boolean changePassword(String newPassword, Account account) {
		// requirements: the new password must be valid. the password must then be hashed
		//
		// postconditions: the old password is replace with the new password. The pasword is updated in the CSV file.
		return true;
	}
	
	/**
	 * changes the username of a user's account
	 * @param newUsername the new username the user wants to set
	 * @param account the account of said user in question
	 * @return whether or not the username change was succesful
	 * @author Dwann
	 */
	private static boolean changeUsername(String newUsername, Account account) {
		// requirements: the new username must be valid. the new username must also be unique
		//
		// postconditions: the old username is replace with the new username. The username is updated in the CSV file.
		return true;
	}
	
	
	/**
	 * hashes the password into unique gibberish based on some algorithm
	 * @param password the password to be hashed
	 * @return the hashed password
	 * @author Sakif
	 */
	private static String hashPassword(String password) {
		// postcondition: the password is hashed.
		return "bob";
	}
}
