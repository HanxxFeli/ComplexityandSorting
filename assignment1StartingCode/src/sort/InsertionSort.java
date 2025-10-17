package sort;

import java.util.Comparator;
import shapes.Shape;


public class InsertionSort {
	/**
	 * Insertion sort using Comparable (by height)
	 * Sort in Descending order
     */
    public static void sort(Shape[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            Shape key = arr[i];
            int j = i - 1;

            // Move elements greater than key to the right (descending order)
            while (j >= 0 && arr[j].compareTo(key) < 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    /**
	 * Insertion sort using comparator (base area or volume)
	 * Sort in descending order
     */
    public static void sort(Shape[] arr, Comparator<Shape> comparator) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            Shape key = arr[i];
            int j = i - 1;

            // Move elements greater than key to the right (descending order)
            while (j >= 0 && comparator.compare(arr[j], key) < 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}
