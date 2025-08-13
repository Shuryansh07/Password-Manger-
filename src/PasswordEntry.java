import java.io.Serializable;

/**
 * Represents a password entry with website/app name, username/email, and password.
 */
public class PasswordEntry implements Serializable {
    private static final long serialVersionUID = 1L;

    private String website;
    private String username;
    private String password;

    public PasswordEntry(String website, String username, String password) {
        this.website = website;
        this.username = username;
        this.password = password;
    }

    // Getters
    public String getWebsite() {
        return website;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setWebsite(String website) {
        this.website = website;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Website/App: " + website +
                "\nUsername/Email: " + username +
                "\nPassword: " + password;
    }
}
