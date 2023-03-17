package gna;

import java.util.Random;

/**
 * Performs sort by using the Quick Sort algorithm.
 *
 */
public class QuickSort extends SortingAlgorithm{
	/**
	 * Sorts the given array using quick sort.
	 * 
	 * @see super
	 */
	public long sort(Comparable[] array) throws IllegalArgumentException {
		if (array == null) {
			throw new IllegalArgumentException("argument 'array' must not be null.");
		}
        resetCount();
        int length = array.length;
        if (length == 1) return 0;
		recursiveSort(array, 0, length - 1);
        return getCount();
	}
    
    private void recursiveSort(Comparable[] array, int start, int end) {
        if (end > start) {
            int split = partition(array, start, end);
            recursiveSort(array, start, split-1);
            recursiveSort(array, split+1, end);
        }
        else
            return;
        
    }
    
    private int partition(Comparable[] array, int start, int end) {
        Comparable a = array[start]; 
		int i = start;
		int j = end+1;
		int m;
		int n;

		while(true) {
			i++;
			for (m = i; smallerThan(array[m],a); m++) {
				if (m == end) break;
			}
			i = m;
			j--;
			for (n = j; smallerThan(a,array[n]); n--) {
				if (n == start) break;
			}
			j = n;
			if (i >= j) break;
			switchPositions(array, i, j);
			
		}
        switchPositions(array, start, j);
        return j;
    }
    
    
    
	/**
	 * Constructor.
	 */
	public QuickSort() {
	}
}
