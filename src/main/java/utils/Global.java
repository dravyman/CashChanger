package utils;

import authorization.User;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Global {
    static {
        accounts = getAllAccountsFromFile();
    }

    public static Map<String, User> accounts;
    public static final String session_attr_currentUser = "currentSessionUser";

    private static Map<String, User> getAllAccountsFromFile() {
        Map<String, User> result = new HashMap<String, User>();
        Scanner scan = new Scanner(Global.class.getResourceAsStream("Users.txt"));
        while (scan.hasNextLine()) {
            String nextLine = scan.nextLine();
            System.out.println(nextLine);
            String[] split = nextLine.split(" - ");
            User tempUserp = new User(split[0], Long.parseLong(split[1]));
            result.put(split[0].toLowerCase(), tempUserp);
        }
        return result;
    }
}
