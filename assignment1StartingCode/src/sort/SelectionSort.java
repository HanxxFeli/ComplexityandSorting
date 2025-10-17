package sort;

import java.util.Comparator;

public class SelectionSort 
{
	// sort using natural ordering (comparable)
    public static <T extends Comparable<? super T>> void sort(T[] array) 
    {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) 
        {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) 
            {
                if (array[j].compareTo(array[minIndex]) < 0) 
                {
                    minIndex = j;
                }
            }
            //swaps the current element with minimum
            T temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
    }
    
    // sort using comparator
    public static <T> void sort(T[] array, Comparator<? super T> comparator) 
    {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) 
        {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) 
            {
                if (comparator.compare(array[j], array[minIndex]) < 0) 
                {
                    minIndex = j;
                }
            }
            
            //swaps current with minimum
            T temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
    }
}
