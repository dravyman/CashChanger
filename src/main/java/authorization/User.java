package authorization;

public class User {
    private String login;
private long currentMoney;

    public String getLogin() {
        return login;
    }

    public User(String login, long cash) {
        this.login = login;
        this.currentMoney = cash;
    }
}
