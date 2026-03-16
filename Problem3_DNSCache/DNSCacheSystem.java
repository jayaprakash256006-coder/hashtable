import java.util.*;

class DNSEntry{
    String ip;
    long expiry;

    DNSEntry(String ip,long ttl){
        this.ip=ip;
        this.expiry=System.currentTimeMillis()+ttl;
    }
}

public class DNSCacheSystem{

    static HashMap<String,DNSEntry> cache=new HashMap<>();

    public static String resolve(String domain){

        if(cache.containsKey(domain)){
            DNSEntry e=cache.get(domain);

            if(System.currentTimeMillis()<e.expiry){
                return "Cache HIT "+e.ip;
            }
        }

        String ip="192.168.0."+new Random().nextInt(100);
        cache.put(domain,new DNSEntry(ip,5000));

        return "Cache MISS "+ip;
    }

    public static void main(String[] args){

        System.out.println(resolve("google.com"));
        System.out.println(resolve("google.com"));
    }
}