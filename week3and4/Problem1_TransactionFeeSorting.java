import java.util.*;

public class Problem1_TransactionFeeSorting {

    static class Transaction {
        String id;
        double fee;
        String timestamp; // HH:MM

        Transaction(String id, double fee, String timestamp) {
            this.id = id;
            this.fee = fee;
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return id + ": fee=" + fee + ", ts=" + timestamp;
        }
    }

    static class BubbleResult {
        int passes;
        int swaps;

        BubbleResult(int passes, int swaps) {
            this.passes = passes;
            this.swaps = swaps;
        }
    }

    // Bubble Sort: fee ascending (stable)
    static BubbleResult bubbleSortByFee(ArrayList<Transaction> list) {
        int n = list.size();
        int swaps = 0, passes = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            passes++;

            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    Collections.swap(list, j, j + 1);
                    swaps++;
                    swapped = true;
                }
            }

            if (!swapped) break; // early termination
        }

        return new BubbleResult(passes, swaps);
    }

    // Insertion Sort: fee ascending, then timestamp ascending (stable)
    static void insertionSortByFeeAndTimestamp(ArrayList<Transaction> list) {
        for (int i = 1; i < list.size(); i++) {
            Transaction key = list.get(i);
            int j = i - 1;

            while (j >= 0 && compareFeeTimestamp(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }

    static int compareFeeTimestamp(Transaction a, Transaction b) {
        if (Double.compare(a.fee, b.fee) != 0) {
            return Double.compare(a.fee, b.fee);
        }
        return a.timestamp.compareTo(b.timestamp);
    }

    static void printOutliers(ArrayList<Transaction> list) {
        boolean found = false;
        System.out.println("High-fee outliers (> $50):");
        for (Transaction t : list) {
            if (t.fee > 50) {
                System.out.println("  " + t);
                found = true;
            }
        }
        if (!found) System.out.println("  none");
    }

    public static void main(String[] args) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));
        transactions.add(new Transaction("id4", 25.0, "09:00"));
        transactions.add(new Transaction("id5", 60.0, "11:00"));

        // Bubble Sort copy
        ArrayList<Transaction> bubbleList = new ArrayList<>(transactions);
        BubbleResult result = bubbleSortByFee(bubbleList);

        System.out.println("Bubble Sort (fee ascending):");
        for (Transaction t : bubbleList) System.out.println("  " + t);
        System.out.println("Passes = " + result.passes + ", Swaps = " + result.swaps);

        // Insertion Sort copy
        ArrayList<Transaction> insertionList = new ArrayList<>(transactions);
        insertionSortByFeeAndTimestamp(insertionList);

        System.out.println("\nInsertion Sort (fee + timestamp ascending):");
        for (Transaction t : insertionList) System.out.println("  " + t);

        System.out.println();
        printOutliers(transactions);
    }
}