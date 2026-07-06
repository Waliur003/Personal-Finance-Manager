package storage;

/**
 * Manages persistence of user account data to and from the file system,
 * including basic password obfuscation.
 *
 * @author Mohammed, Ayub, Fuad
 */
public class AccountFileManager {

    /**
     * Constructs a new {@code AccountFileManager} instance.
     *
     * @author Mohammed, Ayub, Fuad
     */
    public AccountFileManager() {
    }

    /**
     * Saves the given account's data to the file system.
     *
     * @param username the username identifying the account
     * @param password the account password to persist (will be obfuscated)
     * @author Mohammed, Ayub, Fuad
     */
    public void saveAccount(String username, String password) {
    }

    /**
     * Loads account data for the specified username from the file system.
     *
     * @param username the username identifying the account
     * @return the loaded account data, or {@code null} if not found
     * @author Mohammed, Ayub, Fuad
     */
    public Object loadAccount(String username) {
        return null;
    }

    /**
     * Deletes the account data associated with the specified username.
     *
     * @param username the username identifying the account
     * @author Mohammed, Ayub, Fuad
     */
    public void deleteAccount(String username) {
    }

    /**
     * Determines whether an account exists for the specified username.
     *
     * @param username the username to check
     * @return {@code true} if an account exists, {@code false} otherwise
     * @author Mohammed, Ayub, Fuad
     */
    public boolean accountExists(String username) {
        return false;
    }

    /**
     * Obfuscates the given plaintext password prior to storage.
     *
     * @param plainTextPassword the plaintext password to obfuscate
     * @return the obfuscated password
     * @author Mohammed, Ayub, Fuad
     */
    public String obfuscatePassword(String plainTextPassword) {
        return null;
    }

    /**
     * Reverses obfuscation on a stored password, returning its plaintext
     * form.
     *
     * @param obfuscatedPassword the obfuscated password to decode
     * @return the plaintext password
     * @author Mohammed, Ayub, Fuad
     */
    public String deobfuscatePassword(String obfuscatedPassword) {
        return null;
    }
}
