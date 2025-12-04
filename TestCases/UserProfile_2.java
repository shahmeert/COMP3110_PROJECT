package TestCases;
public class UserProfile_2 {

    private String username;
    private String emailAddress;
    private int age;
    private boolean emailVerified;

    public UserProfile_2(String username, String emailAddress, int age, boolean emailVerified) {
        this.username = username;
        this.emailAddress = emailAddress;
        this.age = age;
        this.emailVerified = emailVerified;
    }

    public void verifyEmail() {
        this.emailVerified = true;
    }

    public boolean isAdult() {
        return age >= 18;
    }

    public void changeEmail(String newEmail) {
        if (newEmail != null && newEmail.contains("@")) {
            this.emailAddress = newEmail;
        }
    }

    public String toString() {
        return username + " <" + emailAddress + ">, verified: " + emailVerified;
    }
}
