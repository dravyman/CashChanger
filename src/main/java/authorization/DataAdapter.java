package authorization;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class DataAdapter {
    private static int lastTimeStamp;
    private static Map<String, User> allUsers;
    private static Timer timer;
    private static boolean isChanged = false;
    static {
        timer = new Timer();
        readAllUsers();
        lastTimeStamp = allUsers.hashCode();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (isChanged || lastTimeStamp != allUsers.hashCode()) {
                    writeAllusers();
                    lastTimeStamp = allUsers.hashCode();
                    isChanged = false;
                }
            }
        }, 1000 * 5, 1000 * 5);
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

    private static void writeAllusers() {
        System.out.println("Save users data to " + DataAdapter.class.getResource("/Users.txt").getPath());
        try (FileOutputStream fos = new FileOutputStream(DataAdapter.class.getResource("/Users.txt").getPath());){

            for (Map.Entry<String, User> entry : allUsers.entrySet()) {
                String x = entry.getKey();
                User y = entry.getValue();
                fos.write(String.format("%s : %s\n", x, y.getCurrentMoney()).getBytes("utf-8"));
            }
            fos.flush();
            System.out.println("#### Have saved a dataAdapter");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
         } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static User getUser(String login) {
        return allUsers.get(login);
    }

    public static Map<String, User> getAllUser() {
        return allUsers;
    }

    public static void setChanged() {
        isChanged = true;
    }
}
