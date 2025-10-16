package sort;

import java.util.Comparator;

public class CustomSort {
	// version 1: natural order
	public static <T extends Comparable<? super T>> void sort(T[] items)
	{
		sort(items, null);
	}
	
	// version 2: custom comparator if given
	public static <T> void sort(T[] items, Comparator<? super T> compareBy)
	{
		ShellSort.sort(items, compareBy);
	}
}
