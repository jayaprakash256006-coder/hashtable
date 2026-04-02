import java.util.*;

public class Problem6_RiskThresholdBinaryLookup {

    // Linear Search in unsorted array
    static int linearSearch(int[] arr, int target) {
        int comparisons = 0;
        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i] == target) {
                System.out.println("Linear Search: found at index=" + i + ", comparisons=" + comparisons);
                return i;
            }
        }
        System.out.println("Linear Search: not found, comparisons=" + comparisons);
        return -1;
    }

    // Binary Search exact match
    static int binarySearch(int[] arr, int target) {
        int low = 0, high = arr.length - 1, comparisons = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;

            if (arr[mid] == target) {
                System.out.println("Binary Search: found at index=" + mid + ", comparisons=" + comparisons);
                return mid;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("Binary Search: not found, comparisons=" + comparisons);
        return -1;
    }

    // Insertion point (lower bound)
    static int insertionPoint(int[] arr, int target) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (arr[mid] < target) low = mid + 1;
            else high = mid;
        }
        return low;
    }

    // Floor = largest <= target
    static Integer floorValue(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        Integer floor = null;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == target) return arr[mid];
            if (arr[mid] < target) {
                floor = arr[mid];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return floor;
    }

    // Ceiling = smallest >= target
    static Integer ceilingValue(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        Integer ceil = null;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == target) return arr[mid];
            if (arr[mid] > target) {
                ceil = arr[mid];
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ceil;
    }

    public static void main(String[] args) {
        int[] unsortedBands = {50, 10, 100, 25};
        int[] sortedBands = {10, 25, 50, 100};
        int target = 30;

        System.out.println("Unsorted Bands: " + Arrays.toString(unsortedBands));
        linearSearch(unsortedBands, target);

        System.out.println("\nSorted Bands: " + Arrays.toString(sortedBands));
        binarySearch(sortedBands, target);

        int insertPos = insertionPoint(sortedBands, target);
        System.out.println("Insertion Point for " + target + " = index " + insertPos);

        Integer floor = floorValue(sortedBands, target);
        Integer ceil = ceilingValue(sortedBands, target);

        System.out.println("Floor(" + target + ") = " + (floor != null ? floor : "none"));
        System.out.println("Ceiling(" + target + ") = " + (ceil != null ? ceil : "none"));
    }
}