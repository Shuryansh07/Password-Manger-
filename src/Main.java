
import java.util.List;
import java.util.Scanner;

/**
 * SafeKeep Password Manager
 * A simple console-based password manager
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final PasswordManager passwordManager = new PasswordManager();

    public static void main(String[] args) {
        System.out.println("===================================");
        System.out.println("   SAFEKEEP PASSWORD MANAGER");
        System.out.println("===================================");

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    addNewEntry();
                    break;
                case 2:
                    viewAllEntries();
                    break;
                case 3:
                    searchEntries();
                    break;
                case 4:
                    System.out.println("Thank you for using SafeKeep Password Manager!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    /**
     * Displays the main menu options.
     */
    private static void displayMenu() {
        System.out.println("\nPlease select an option:");
        System.out.println("1. Add new password");
        System.out.println("2. View all passwords");
        System.out.println("3. Search passwords");
        System.out.println("4. Exit");
        System.out.print("Enter your choice (1-4): ");
    }

    /**
     * Gets the user's menu choice.
     */
    private static int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1; // Invalid choice
        }
    }

    /**
     * Handles adding a new password entry.
     */
    private static void addNewEntry() {
        System.out.println("\n--- Add New Password ---");

        System.out.print("Enter website/app name: ");
        String website = scanner.nextLine();

        System.out.print("Enter username/email: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        passwordManager.addEntry(website, username, password);
        System.out.println("Password added successfully!");
    }

    /**
     * Displays all saved password entries.
     */
    private static void viewAllEntries() {
        System.out.println("\n--- All Saved Passwords ---");
        List<PasswordEntry> entries = passwordManager.getAllEntries();

        if (entries.isEmpty()) {
            System.out.println("No passwords saved yet.");
            return;
        }

        for (int i = 0; i < entries.size(); i++) {
            PasswordEntry entry = entries.get(i);
            System.out.println("\nEntry #" + (i + 1));
            System.out.println("Website/App: " + entry.getWebsite());
            System.out.println("Username/Email: " + entry.getUsername());
            System.out.println("Password: " + entry.getPassword());
            System.out.println("----------------------------");
        }
    }

    /**
     * Searches for password entries by website/app name.
     */
    private static void searchEntries() {
        System.out.println("\n--- Search Passwords ---");
        System.out.print("Enter website/app name to search: ");
        String searchTerm = scanner.nextLine();

        List<PasswordEntry> results = passwordManager.searchEntries(searchTerm);

        if (results.isEmpty()) {
            System.out.println("No matching entries found.");
            return;
        }

        System.out.println("\nSearch Results:");
        for (int i = 0; i < results.size(); i++) {
            PasswordEntry entry = results.get(i);
            System.out.println("\nResult #" + (i + 1));
            System.out.println("Website/App: " + entry.getWebsite());
            System.out.println("Username/Email: " + entry.getUsername());
            System.out.println("Password: " + entry.getPassword());
            System.out.println("----------------------------");
        }
    }
}
