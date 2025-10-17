package sort;

import java.util.Comparator;

/**
 * Custom Algorithm: Shell Sort 
 * 
 * This class implements the Shell Sort algorithm for sorting arrays
 * in descending order. It supports both natural ordering and custom
 * comparators (e.g., sorting shapes by volume, area, or height).
 *
 * @author Celine
 */

public class ShellSort {
	// version 1: natural order
	public static <T extends Comparable<? super T>> void sort(T[] items)
	{
		sort(items, null);
	}
	
	// version 2: custom comparator if its given
	public static <T> void sort(T[] items, Comparator<? super T> compareBy)
	{
		int size = items.length;
		
		// starts with a big gap, then shrinks it
		for (int gap = size / 2; gap > 0; gap /= 2)
		{
			// goes through the array, starting from the current gap
			for (int i = gap; i < size; i++)
			{
				T currentItem = items[i];
				int position = i;
				
				// moves the bigger elements forward (goes in descending order)
				while (position >= gap && compare(items[position - gap], currentItem, compareBy) < 0)
				{
					items[position] = items[position - gap];
					position-= gap;
				}
				
				// places the saved item in the correct spot
				items[position] = currentItem;
			}
		}
	}
	
	// helper method 
	private static <T> int compare(T first, T second, Comparator<? super T> compareBy) 
	{
		if (compareBy != null) 
		{
			return compareBy.compare(first,  second);
		} else
		{
			return ((Comparable<? super T>) first).compareTo(second);
		}
	}
}
