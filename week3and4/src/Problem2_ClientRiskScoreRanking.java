import java.util.*;

public class Problem2_ClientRiskScoreRanking {

    static class Client {
        String name;
        int riskScore;
        double accountBalance;

        Client(String name, int riskScore, double accountBalance) {
            this.name = name;
            this.riskScore = riskScore;
            this.accountBalance = accountBalance;
        }

        @Override
        public String toString() {
            return name + "(risk=" + riskScore + ", balance=" + accountBalance + ")";
        }
    }

    // Bubble Sort: risk ascending
    static int bubbleSortRiskAsc(Client[] arr) {
        int swaps = 0;
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].riskScore > arr[j + 1].riskScore) {
                    Client temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swaps++;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
        return swaps;
    }

    // Insertion Sort: risk DESC, then accountBalance ASC
    static void insertionSortRiskDescBalanceAsc(Client[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Client key = arr[i];
            int j = i - 1;

            while (j >= 0 && compare(arr[j], key) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    static int compare(Client a, Client b) {
        // DESC by riskScore
        if (a.riskScore != b.riskScore) {
            return Integer.compare(b.riskScore, a.riskScore);
        }
        // ASC by accountBalance
        return Double.compare(a.accountBalance, b.accountBalance);
    }

    static void printTopRisks(Client[] arr, int topN) {
        System.out.println("\nTop " + Math.min(topN, arr.length) + " highest risk clients:");
        for (int i = 0; i < Math.min(topN, arr.length); i++) {
            System.out.println("  " + arr[i]);
        }
    }

    public static void main(String[] args) {
        Client[] clients = {
                new Client("clientC", 80, 20000),
                new Client("clientA", 20, 50000),
                new Client("clientB", 50, 15000),
                new Client("clientD", 80, 10000)
        };

        Client[] bubbleArr = Arrays.copyOf(clients, clients.length);
        int swaps = bubbleSortRiskAsc(bubbleArr);

        System.out.println("Bubble Sort (risk ascending):");
        for (Client c : bubbleArr) System.out.println("  " + c);
        System.out.println("Swaps = " + swaps);

        Client[] insertionArr = Arrays.copyOf(clients, clients.length);
        insertionSortRiskDescBalanceAsc(insertionArr);

        System.out.println("\nInsertion Sort (risk DESC + balance ASC):");
        for (Client c : insertionArr) System.out.println("  " + c);

        printTopRisks(insertionArr, 10);
    }
}