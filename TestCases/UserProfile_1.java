package TestCases;
public class UserProfile_1 {

    private String username;
    private String email;
    private int age;
    private boolean verified;

    public UserProfile_1(String username, String email, int age, boolean verified) {
        this.username = username;
        this.email = email;
        this.age = age;
        this.verified = verified;
    }

    public void verify() {
        this.verified = true;
    }

    public boolean isAdult() {
        return age >= 18;
    }

    public String getProfile() {
        return username + " (" + email + "), age " + age + ", verified: " + verified;
    }

    public void updateEmail(String newEmail) {
        if (newEmail != null && newEmail.contains("@")) {
            this.email = newEmail;
        }
    }
}
