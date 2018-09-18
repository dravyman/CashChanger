package authorization;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DataAdapter {
    private static Map<String, User> allUsers;
    static {
        readAllUsers();
    }

    private static void readAllUsers() {
        HashMap res = new HashMap();
        Scanner scan = new Scanner(DataAdapter.class.getResourceAsStream("/Users.txt"));
        while (scan.hasNextLine()) {
            String[] line = scan.nextLine().split(" : ");
            String login = line[0];
            Long cash = Long.parseLong(line[1]);
            res.put(login, new User(login, cash));
        }
        allUsers = res;
    }

    public static User getUser(String login) {
        return allUsers.get(login);
    }

    public static Map<String, User> getAllUser() {
        return allUsers;
    }
}
