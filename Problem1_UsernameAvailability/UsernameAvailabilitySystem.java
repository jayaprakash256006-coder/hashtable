import java.util.*;

public class UsernameAvailabilitySystem {

    static HashSet<String> users = new HashSet<>();
    static HashMap<String,Integer> attempts = new HashMap<>();

    public static boolean checkAvailability(String username){
        attempts.put(username, attempts.getOrDefault(username,0)+1);
        return !users.contains(username);
    }

    public static List<String> suggestAlternatives(String username){
        List<String> list = new ArrayList<>();
        for(int i=1;i<=3;i++){
            list.add(username+i);
        }
        return list;
    }

    public static String mostAttempted(){
        String name="";
        int max=0;

        for(String key: attempts.keySet()){
            if(attempts.get(key)>max){
                max=attempts.get(key);
                name=key;
            }
        }
        return name;
    }

    public static void main(String[] args){
        users.add("john_doe");

        System.out.println(checkAvailability("john_doe"));
        System.out.println(checkAvailability("jane_smith"));
        System.out.println(suggestAlternatives("john_doe"));
        System.out.println(mostAttempted());
    }
}