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

    public void decreaseCash(long amount) {
        this.currentMoney -= amount;
        System.out.println("#### " + login + ": " + (currentMoney + amount) + " -> " + currentMoney);
    }

    public void increaseCash(long amount) {
        this.currentMoney += amount;
        System.out.println("#### " + login + ": " + (currentMoney - amount) + " -> " + currentMoney);
    }

    public String toJSONString() {
        return String.format("{\"login\" : \"%s\", \"cash\" : \"%s\"}", login, currentMoney);
    }
}
