
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Core functionality for managing password entries.
 * Handles adding, viewing, searching, saving and loading entries.
 */
public class PasswordManager {
    private List<PasswordEntry> entries;
    private final String dataFile = "passwords.dat";

    /**
     * Initializes a new PasswordManager and loads any existing entries.
     */
    public PasswordManager() {
        entries = new ArrayList<>();
        loadEntries();
    }

    /**
     * Adds a new password entry and saves it.
     *
     * @param website Website/App name
     * @param username Username/Email
     * @param password Plain text password (will be encrypted before storing)
     */
    public void addEntry(String website, String username, String password) {
        String encryptedPassword = EncryptionUtil.encrypt(password);
        entries.add(new PasswordEntry(website, username, encryptedPassword));
        saveEntries();
    }

    /**
     * Returns all stored password entries.
     *
     * @return List of PasswordEntry objects
     */
    public List<PasswordEntry> getAllEntries() {
        return entries.stream()
                .map(entry -> new PasswordEntry(
                        entry.getWebsite(),
                        entry.getUsername(),
                        EncryptionUtil.decrypt(entry.getPassword())
                ))
                .collect(Collectors.toList());
    }

    /**
     * Searches for entries matching the given website/app name.
     *
     * @param website Website/App name to search for
     * @return List of matching PasswordEntry objects with decrypted passwords
     */
    public List<PasswordEntry> searchEntries(String website) {
        return entries.stream()
                .filter(entry -> entry.getWebsite().toLowerCase().contains(website.toLowerCase()))
                .map(entry -> new PasswordEntry(
                        entry.getWebsite(),
                        entry.getUsername(),
                        EncryptionUtil.decrypt(entry.getPassword())
                ))
                .collect(Collectors.toList());
    }

    /**
     * Saves all entries to a file.
     */
    private void saveEntries() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFile))) {
            oos.writeObject(entries);
            System.out.println("Entries saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving entries: " + e.getMessage());
        }
    }

    /**
     * Loads entries from a file.
     */
    @SuppressWarnings("unchecked")
    private void loadEntries() {
        File file = new File(dataFile);
        if (!file.exists()) {
            return; // No file to load yet
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            entries = (List<PasswordEntry>) ois.readObject();
            System.out.println("Entries loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading entries: " + e.getMessage());
        }
    }
}