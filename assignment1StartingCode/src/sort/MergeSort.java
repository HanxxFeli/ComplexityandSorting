package sort;

import java.util.Comparator;
import shapes.Shape;

public class MergeSort {
	/**
	 * 
	 * Sort the array by height in descending order
	 * @param arr array of Shape objects
	 */
    public static void sort(Shape[] arr) {
        if (arr == null || arr.length <= 1) return;
        mergeSort(arr, 0, arr.length - 1);
    } // sort comparable

    /**
     * 
     * Recursion for merge sort for Comparable
     * @param arr array of Shape objects
     * @param l   left index
     * @param r   right index
     */
    private static void mergeSort(Shape[] arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2; // get middle index
            mergeSort(arr, l, m); 
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    } // recursion mergeSort comparable
    
    /**
     * 
     * Merges two of the sorted arrays together in descending order
     * @param arr array of Shape objects
     * @param l   left index
     * @param m   middle index
     * @param r   right index
     */
    private static void merge(Shape[] arr, int l, int m, int r) {
        int n1 = m - l + 1; // left subarray
        int n2 = r - m; // right subarray

        // temporary
        Shape[] L = new Shape[n1];
        Shape[] R = new Shape[n2];

        for (int i = 0; i < n1; i++) L[i] = arr[l + i];
        for (int j = 0; j < n2; j++) R[j] = arr[m + 1 + j];

        int i = 0, j = 0, k = l;

        // merge temporary into original array
        while (i < n1 && j < n2) {
            // Descending order: larger first
            if (L[i].compareTo(R[j]) >= 0) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    } // comparable merge
    
    /**
     * Sorts the array of Shapes using a Comparator (volume or base area) in descending order.
     *
     * @param arr array of Shape objects
     * @param comp Comparator to determine order
     */
    public static void sort(Shape[] arr, Comparator<Shape> comp) {
        if (arr == null || arr.length <= 1) return; // Already sorted
        mergeSort(arr, 0, arr.length - 1, comp);
    }

    /**
     * Recursive merge sort helper for Comparator version
     *
     * @param arr array of Shape objects
     * @param l   left index
     * @param r   right index
     * @param comp Comparator to determine order
     */
    private static void mergeSort(Shape[] arr, int l, int r, Comparator<Shape> comp) {
        if (l < r) {
            int m = l + (r - l) / 2;

            mergeSort(arr, l, m, comp);      // Sort first half
            mergeSort(arr, m + 1, r, comp);  // Sort second half

            merge(arr, l, m, r, comp);       // Merge sorted halves
        }
    }

    /**
     * Merges two sorted subarrays in descending order
     *
     * @param arr array of Shape objects
     * @param l   left index
     * @param m   middle index
     * @param r   right index
     * @param comp Comparator to determine order
     */
    private static void merge(Shape[] arr, int l, int m, int r, Comparator<Shape> comp) {
        int n1 = m - l + 1;
        int n2 = r - m;

        Shape[] L = new Shape[n1];
        Shape[] R = new Shape[n2];

        // Copy data to temporary arrays
        for (int i = 0; i < n1; i++) L[i] = arr[l + i];
        for (int j = 0; j < n2; j++) R[j] = arr[m + 1 + j];

        int i = 0, j = 0, k = l;

        // Merge temporary arrays 
        while (i < n1 && j < n2) {
            if (comp.compare(L[i], R[j]) >= 0) { // Larger first
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) arr[k++] = L[i++];

        while (j < n2) arr[k++] = R[j++];
    }
    
    
} // mergeSort class
