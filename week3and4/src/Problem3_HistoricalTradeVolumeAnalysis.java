import java.util.*;

public class Problem3_HistoricalTradeVolumeAnalysis {

    static class Trade {
        String id;
        int volume;

        Trade(String id, int volume) {
            this.id = id;
            this.volume = volume;
        }

        @Override
        public String toString() {
            return id + ":" + volume;
        }
    }

    // ---------------- Merge Sort (Stable, Ascending) ----------------
    static void mergeSort(Trade[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    static void merge(Trade[] arr, int left, int mid, int right) {
        Trade[] temp = new Trade[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (arr[i].volume <= arr[j].volume) { // stable
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        for (int x = 0; x < temp.length; x++) {
            arr[left + x] = temp[x];
        }
    }

    // ---------------- Quick Sort (Descending) ----------------
    static void quickSortDesc(Trade[] arr, int low, int high) {
        if (low < high) {
            int pi = partitionDesc(arr, low, high);
            quickSortDesc(arr, low, pi - 1);
            quickSortDesc(arr, pi + 1, high);
        }
    }

    static int partitionDesc(Trade[] arr, int low, int high) {
        int pivot = arr[high].volume; // Lomuto pivot
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].volume >= pivot) { // DESC
                i++;
                Trade temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        Trade temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    // Merge two already sorted ascending lists
    static Trade[] mergeTwoSortedLists(Trade[] a, Trade[] b) {
        Trade[] result = new Trade[a.length + b.length];
        int i = 0, j = 0, k = 0;

        while (i < a.length && j < b.length) {
            if (a[i].volume <= b[j].volume) {
                result[k++] = a[i++];
            } else {
                result[k++] = b[j++];
            }
        }

        while (i < a.length) result[k++] = a[i++];
        while (j < b.length) result[k++] = b[j++];

        return result;
    }

    static int totalVolume(Trade[] arr) {
        int total = 0;
        for (Trade t : arr) total += t.volume;
        return total;
    }

    public static void main(String[] args) {
        Trade[] trades = {
                new Trade("trade3", 500),
                new Trade("trade1", 100),
                new Trade("trade2", 300)
        };

        Trade[] mergeArr = Arrays.copyOf(trades, trades.length);
        mergeSort(mergeArr, 0, mergeArr.length - 1);

        System.out.println("Merge Sort (ascending by volume):");
        for (Trade t : mergeArr) System.out.println("  " + t);

        Trade[] quickArr = Arrays.copyOf(trades, trades.length);
        quickSortDesc(quickArr, 0, quickArr.length - 1);

        System.out.println("\nQuick Sort (descending by volume):");
        for (Trade t : quickArr) System.out.println("  " + t);

        Trade[] morning = {
                new Trade("m1", 100),
                new Trade("m2", 400)
        };

        Trade[] afternoon = {
                new Trade("a1", 200),
                new Trade("a2", 300)
        };

        Trade[] merged = mergeTwoSortedLists(morning, afternoon);

        System.out.println("\nMerged Morning + Afternoon:");
        for (Trade t : merged) System.out.println("  " + t);

        System.out.println("\nTotal Volume = " + totalVolume(merged));
    }
}