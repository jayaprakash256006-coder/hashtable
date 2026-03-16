import java.util.*;

public class AutocompleteSystem{

    static HashMap<String,Integer> queries=new HashMap<>();

    public static void addQuery(String q){
        queries.put(q,queries.getOrDefault(q,0)+1);
    }

    public static List<String> search(String prefix){

        List<String> result=new ArrayList<>();

        for(String q:queries.keySet()){
            if(q.startsWith(prefix)){
                result.add(q);
            }
        }

        return result;
    }

    public static void main(String[] args){

        addQuery("java tutorial");
        addQuery("javascript guide");
        addQuery("java download");

        System.out.println(search("jav"));
    }
}