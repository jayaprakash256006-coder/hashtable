import java.util.*;

public class FlashSaleInventoryManager {

    static HashMap<String,Integer> stock = new HashMap<>();
    static Queue<Integer> waitingList = new LinkedList<>();

    public static void addProduct(String name,int quantity){
        stock.put(name,quantity);
    }

    public static void purchase(String name,int user){

        int available = stock.getOrDefault(name,0);

        if(available>0){
            stock.put(name,available-1);
            System.out.println("Purchase successful");
        }else{
            waitingList.add(user);
            System.out.println("Added to waiting list");
        }
    }

    public static void main(String[] args){

        addProduct("IPHONE",2);

        purchase("IPHONE",1);
        purchase("IPHONE",2);
        purchase("IPHONE",3);
    }
}