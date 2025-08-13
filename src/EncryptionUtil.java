import java.util.Base64;

/**
 * Utility class for encrypting and decrypting passwords.
 * Currently uses simple Base64 encoding/decoding.
 */
public class EncryptionUtil {

    /**
     * Encrypts a password using Base64 encoding.
     *
     * @param password The plain text password to encrypt
     * @return The encrypted password
     */
    public static String encrypt(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }

    /**
     * Decrypts a password that was encrypted using Base64 encoding.
     *
     * @param encryptedPassword The encrypted password to decrypt
     * @return The decrypted (plain text) password
     */
    public static String decrypt(String encryptedPassword) {
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedPassword);
        return new String(decodedBytes);
    }
}
