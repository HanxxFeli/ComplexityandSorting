package sort;

import java.util.Comparator;
import shapes.Shape;

public class BubbleSort {
	/**
	 * Bubble sort using Comparable (by height)
	 * Sort in Descending order
	 */
	public static <T extends Comparable<? super T>> void sort(T[] array) { 
		int n = array.length;
		boolean swapped;
		
		for (int i = 0; i < n - 1; i++) { 
			swapped = false;
			
			for (int j = 0; j < n - i - 1; j++) { 
				// compare using the compareTo() method by height
				if (array[j].compareTo(array[j+1]) < 0) { 
					// make into descending order
					T temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
					swapped = true;
				}
			}
		}
	} // comparable
	/**
	 * 
	 * Bubble sort using comparator (base area or volume)
	 * Sort in descending order
	 */
	public static <T> void sort(T[] array, Comparator<? super T> comparator) { 
        int n = array.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                // Compare using provided comparator
                if (comparator.compare(array[j], array[j + 1]) < 0) {
                    // swap to make descending order
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
        }
	} // comparator
} // bubble sort class
