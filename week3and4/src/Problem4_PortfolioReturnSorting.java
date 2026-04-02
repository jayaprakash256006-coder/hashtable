import java.util.*;

public class Problem4_PortfolioReturnSorting {

    static class Asset {
        String symbol;
        double returnRate;
        double volatility;

        Asset(String symbol, double returnRate, double volatility) {
            this.symbol = symbol;
            this.returnRate = returnRate;
            this.volatility = volatility;
        }

        @Override
        public String toString() {
            return symbol + "(return=" + returnRate + "%, vol=" + volatility + ")";
        }
    }

    // ---------------- Merge Sort (Stable, Ascending by returnRate) ----------------
    static void mergeSort(Asset[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    static void merge(Asset[] arr, int left, int mid, int right) {
        Asset[] temp = new Asset[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (arr[i].returnRate <= arr[j].returnRate) { // stable
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

    // ---------------- Quick Sort (DESC returnRate + ASC volatility) ----------------
    static void quickSort(Asset[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = medianOfThree(arr, low, high);
            swap(arr, pivotIndex, high);

            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    static int partition(Asset[] arr, int low, int high) {
        Asset pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compareDesc(arr[j], pivot) <= 0) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    static int compareDesc(Asset a, Asset b) {
        // returnRate DESC
        if (Double.compare(a.returnRate, b.returnRate) != 0) {
            return Double.compare(b.returnRate, a.returnRate);
        }
        // volatility ASC
        return Double.compare(a.volatility, b.volatility);
    }

    static int medianOfThree(Asset[] arr, int low, int high) {
        int mid = (low + high) / 2;

        Asset a = arr[low], b = arr[mid], c = arr[high];

        if (compareDesc(a, b) > 0) swap(arr, low, mid);
        if (compareDesc(arr[low], arr[high]) > 0) swap(arr, low, high);
        if (compareDesc(arr[mid], arr[high]) > 0) swap(arr, mid, high);

        return mid;
    }

    static void swap(Asset[] arr, int i, int j) {
        Asset temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        Asset[] assets = {
                new Asset("AAPL", 12.0, 0.25),
                new Asset("TSLA", 8.0, 0.40),
                new Asset("GOOG", 15.0, 0.20),
                new Asset("MSFT", 15.0, 0.18)
        };

        Asset[] mergeArr = Arrays.copyOf(assets, assets.length);
        mergeSort(mergeArr, 0, mergeArr.length - 1);

        System.out.println("Merge Sort (returnRate ascending):");
        for (Asset a : mergeArr) System.out.println("  " + a);

        Asset[] quickArr = Arrays.copyOf(assets, assets.length);
        quickSort(quickArr, 0, quickArr.length - 1);

        System.out.println("\nQuick Sort (returnRate DESC + volatility ASC):");
        for (Asset a : quickArr) System.out.println("  " + a);
    }
}