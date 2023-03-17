package gna;

/**
 * Performs sort by using the Insertion Sort algorithm.
 * 
 */
public class InsertionSort extends SortingAlgorithm {
	/**
	 * Sorts the given array using insertion sort.
	 * 
	 * @see super
	 */
	public long sort(Comparable[] array) throws IllegalArgumentException {
		if (array == null) {
			throw new IllegalArgumentException("argument 'array' must not be null.");
		}
        resetCount();
		int length = array.length;
        for (int i = 1; i < length; i++) {
            for (int j = i; j > 0 && smallerThan(array[j],array[j-1]); j--) {
                switchPositions(array, j, j-1); 
            }
        }
        return getCount();
	}
    
	/**
	 * Constructor.
	 */
	public InsertionSort() {
	}
}
