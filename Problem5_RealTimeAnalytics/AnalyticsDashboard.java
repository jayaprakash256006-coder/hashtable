import java.util.*;

public class AnalyticsDashboard{

    static HashMap<String,Integer> views=new HashMap<>();
    static HashMap<String,Set<String>> visitors=new HashMap<>();

    public static void process(String page,String user){

        views.put(page,views.getOrDefault(page,0)+1);

        visitors.putIfAbsent(page,new HashSet<>());
        visitors.get(page).add(user);
    }

    public static void main(String[] args){

        process("/news","u1");
        process("/news","u2");
        process("/sports","u1");

        System.out.println(views);
        System.out.println(visitors);
    }
}