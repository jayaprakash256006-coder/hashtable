import java.util.*;

public class UsernameAvailabilitySystem {

    static HashMap<String, Integer> users = new HashMap<>();
    static HashMap<String, Integer> attempts = new HashMap<>();

    public static boolean checkAvailability(String username) {
        attempts.put(username, attempts.getOrDefault(username, 0) + 1);
        return !users.containsKey(username);
    }

    public static List<String> suggestAlternatives(String username) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            list.add(username + i);
        }
        return list;
    }

    public static String getMostAttempted() {
        String maxUser = "";
        int max = 0;

        for (String key : attempts.keySet()) {
            if (attempts.get(key) > max) {
                max = attempts.get(key);
                maxUser = key;
            }
        }
        return maxUser;
    }

    public static void main(String[] args) {
        users.put("john_doe", 1);

        System.out.println(checkAvailability("john_doe"));
        System.out.println(checkAvailability("jane_smith"));
        System.out.println(suggestAlternatives("john_doe"));
        System.out.println(getMostAttempted());
    }
}