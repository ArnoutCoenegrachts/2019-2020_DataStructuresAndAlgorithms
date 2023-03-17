package gna;

/**
 * Performs sort by using the Selection Sort algorithm.
 * 
 */
public class SelectionSort extends SortingAlgorithm {
	/**
	 * Sorts the given array using selection sort.
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
        for (int i = 0; i < length; i++) {
            int min = i;
            for (int j = i+1; j < length; j++) {
                if (smallerThan(array[j],array[min])) {
                    min = j;
                }
            }
            if (i != min) {
                switchPositions(array, i, min);
            }
        }
        return getCount();
	}

	/**
	 * Constructor.
	 */
	public SelectionSort() {
	}
}
