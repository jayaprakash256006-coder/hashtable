import java.util.*;

public class FlashSaleInventoryManager {

    static HashMap<String, Integer> stock = new HashMap<>();
    static Queue<Integer> waitingList = new LinkedList<>();

    public static void addProduct(String product, int count) {
        stock.put(product, count);
    }

    public static void purchaseItem(String product, int userId) {

        int available = stock.getOrDefault(product, 0);

        if (available > 0) {
            stock.put(product, available - 1);
            System.out.println("Purchase success. Remaining: " + (available - 1));
        } else {
            waitingList.add(userId);
            System.out.println("Added to waiting list");
        }
    }

    public static void main(String[] args) {
        addProduct("IPHONE15", 2);

        purchaseItem("IPHONE15", 1);
        purchaseItem("IPHONE15", 2);
        purchaseItem("IPHONE15", 3);
    }
}