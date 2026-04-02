import java.util.*;

public class Problem5_AccountIDLookup {

    // Linear Search: first occurrence
    static int linearSearchFirst(String[] arr, String target) {
        int comparisons = 0;
        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i].equals(target)) {
                System.out.println("Linear First: index=" + i + ", comparisons=" + comparisons);
                return i;
            }
        }
        System.out.println("Linear First: not found, comparisons=" + comparisons);
        return -1;
    }

    // Linear Search: last occurrence
    static int linearSearchLast(String[] arr, String target) {
        int comparisons = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            comparisons++;
            if (arr[i].equals(target)) {
                System.out.println("Linear Last: index=" + i + ", comparisons=" + comparisons);
                return i;
            }
        }
        System.out.println("Linear Last: not found, comparisons=" + comparisons);
        return -1;
    }

    // Binary Search: exact match
    static int binarySearch(String[] arr, String target) {
        int low = 0, high = arr.length - 1, comparisons = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;

            int cmp = arr[mid].compareTo(target);
            if (cmp == 0) {
                System.out.println("Binary Search: found at index=" + mid + ", comparisons=" + comparisons);
                return mid;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("Binary Search: not found, comparisons=" + comparisons);
        return -1;
    }

    // Lower Bound (first occurrence)
    static int lowerBound(String[] arr, String target) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (arr[mid].compareTo(target) < 0) low = mid + 1;
            else high = mid;
        }
        return low;
    }

    // Upper Bound (index after last occurrence)
    static int upperBound(String[] arr, String target) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (arr[mid].compareTo(target) <= 0) low = mid + 1;
            else high = mid;
        }
        return low;
    }

    static int countOccurrences(String[] arr, String target) {
        int lb = lowerBound(arr, target);
        int ub = upperBound(arr, target);
        return (lb < arr.length && arr[lb].equals(target)) ? (ub - lb) : 0;
    }

    public static void main(String[] args) {
        String[] unsortedLogs = {"accB", "accA", "accB", "accC"};
        String target = "accB";

        System.out.println("Unsorted Logs: " + Arrays.toString(unsortedLogs));
        linearSearchFirst(unsortedLogs, target);
        linearSearchLast(unsortedLogs, target);

        String[] sortedLogs = Arrays.copyOf(unsortedLogs, unsortedLogs.length);
        Arrays.sort(sortedLogs);

        System.out.println("\nSorted Logs: " + Arrays.toString(sortedLogs));
        binarySearch(sortedLogs, target);

        int count = countOccurrences(sortedLogs, target);
        System.out.println("Count of " + target + " = " + count);

        int first = lowerBound(sortedLogs, target);
        int last = upperBound(sortedLogs, target) - 1;
        if (count > 0) {
            System.out.println("First occurrence (sorted) = " + first);
            System.out.println("Last occurrence (sorted) = " + last);
        }
    }
}