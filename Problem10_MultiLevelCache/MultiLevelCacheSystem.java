import java.util.*;

public class MultiLevelCacheSystem{

    static LinkedHashMap<String,String> L1=new LinkedHashMap<>(10,0.75f,true);
    static HashMap<String,String> L2=new HashMap<>();

    public static String getVideo(String id){

        if(L1.containsKey(id)){
            return "L1 HIT";
        }

        if(L2.containsKey(id)){
            L1.put(id,L2.get(id));
            return "L2 HIT -> moved to L1";
        }

        String data="VideoData";
        L2.put(id,data);

        return "L3 Database HIT";
    }

    public static void main(String[] args){

        System.out.println(getVideo("video1"));
        System.out.println(getVideo("video1"));
    }
}