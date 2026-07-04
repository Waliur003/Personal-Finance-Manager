package finalproject;

public class Account {
/**
 * username for this account
 */
	private String username;
/**
 * hashed password for security measures.
 * Allows passwords to be stored in secrecy.
 */
	private String hashedPassword;
/**
 * A security question as an extra layer of protection for password.
 */
	private String secretQuestion;
/**
 * The answer for the security question.
 * Should be hashed in storage.
 */
	private String secretAnswer;
/**
 * Pathing to user's financial data.
 */
	private String pathToUserInfo;
	
/**
 * Create new account with the specific details
 * @param username Unique username for the account
 * @param hashedPassword 
 * @param secretQuestion A security question for recovery
 * @param secretAnswer The answer to the security question
 * @param pathToUserInfo File path to user's info
 * @author Sakif
 */
	public account(String username, String hashedPassword, String secretQuestion, String secretAnswer, String pathToUserInfo) {
		this.username = username;
		this.hashedPassword = hashedPassword;
		this.secretQuestion = secretQuestion;
		this.secretAnswer = secretAnswer;
		this.pathToUserInfo = pathToUserInfo;
	}
/**
 * Get username that belongs to the account.
 * @return Account username
 * @author Sakif
 */
	public String getUsername() {
		return username;
	}
/**
 * Set the username for the account.
 * @param username New username to set
 * @author Sakif
 */
	public void setUsername(String username) {
		this.username = username;
	}
/**
 * Get the hashed password for the account.
 * @return Hashed password string
 * @author Sakif
 */
	public String getHashedPassword() {
		return hashedPassword;
	}
/**
 * Set hashed password for the account.
 * In this case the password would be hashed prior to calling this method.
 * @param hashedPassword The new hashed password to be set
 * @author Sakif
 */
	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}
/**
 * Get security question for password recovery.
 * @return Security question
 * @author Sakif
 */
	public String getSecretQuestion() {
		return secretQuestion;
	}
/**
 * Get the answer to the secret question
 * @return Secret answer (should already be  hashed)
 * @author Sakif
 */
	public String getSecretAnswer() {
		return secretAnswer;
	}
/**
 * Get the file path to user financial data
 * @return Path to user data
 * @author Sakif
 */
	public String getPathToUserInfo() {
		return pathToUserInfo;
	}
/**
 * Set path to user's financial data
 * @param pathToUserInfo New path to set
 * @author Sakif
 */
	public void setPathToUserInfo(String pathToUserInfo) {
		this.pathToUserInfo = pathToUserInfo;
	}
}
