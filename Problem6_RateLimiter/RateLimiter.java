import java.util.*;

class Client{
    int tokens;

    Client(int t){
        tokens=t;
    }
}

public class RateLimiter{

    static HashMap<String,Client> clients=new HashMap<>();

    public static boolean allow(String id){

        clients.putIfAbsent(id,new Client(5));

        Client c=clients.get(id);

        if(c.tokens>0){
            c.tokens--;
            return true;
        }

        return false;
    }

    public static void main(String[] args){

        for(int i=0;i<7;i++){
            System.out.println(allow("user1"));
        }
    }
}