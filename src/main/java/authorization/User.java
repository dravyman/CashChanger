package authorization;

public class User {
    private String login = "";

    public String getLogin() {
        return login;
    }

    public User(String login) {
        this.login = login;
    }
}
