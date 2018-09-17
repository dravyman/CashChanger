package authorization;

public class User {
    private String login;
    private long currentMoney;


    public String getLogin() {
        return login;
    }
    public long getCurrentMoney() {
        return currentMoney;
    }

    public User(String login, long cash) {
        this.login = login;
        this.currentMoney = cash;
    }
}
