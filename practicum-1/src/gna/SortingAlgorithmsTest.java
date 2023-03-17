package gna;

import static org.junit.Assert.*;
import libpract.SortingAlgorithm;

import org.junit.Test;

import java.util.Arrays;

/**
 * Tests that test whether the implementations of the sorting algorithms do sort.
 */
public class SortingAlgorithmsTest {
    
    
    private static Comparable[] unsortedBooleanArray = new Comparable[]{ false, true, false, false, false, true, false, true};
    private static Comparable[] unsortedDoubleArray = new Comparable[]{ 1.0, 8.0, 9.0, 0.0, -2.0, 8.0, 3.0, 0.0};
    private static Comparable[] unsortedFloatArray = new Comparable[]{ 1.0f, 8.0f, 9.0f, 0.0f, -2.0f, 8.0f, 3.0f, 0.0f};
    private static Comparable[] unsortedIntArray = new Comparable[]{ 1, 8, 9, 0, -2, 8, 3, 0};
    private static Comparable[] unsortedStringArray = new Comparable[]{"b", "x", "z", "a", "b", "r", "b", "s"};
    
    public Comparable[] getUnsortedBooleanArray() { return unsortedBooleanArray; }
    public Comparable[] getUnsortedDoubleArray() { return unsortedDoubleArray; }
    public Comparable[] getUnsortedFloatArray() { return unsortedFloatArray; }
    public Comparable[] getUnsortedIntArray() { return unsortedIntArray; }
    public Comparable[] getUnsortedStringArray() { return unsortedStringArray; }

    private static Comparable[] sortedBooleanArray = new Comparable[]{ false, false, false, false, false, true, true, true};
    private static Comparable[] sortedDoubleArray = new Comparable[]{ -2.0, 0.0, 0.0, 1.0, 3.0, 8.0, 8.0, 9.0};
    private static Comparable[] sortedFloatArray = new Comparable[]{ -2.0f, 0.0f, 0.0f, 1.0f, 3.0f, 8.0f, 8.0f, 9.0f};    
    private static Comparable[] sortedIntArray = new Comparable[]{ -2, 0, 0, 1, 3, 8, 8, 9};    
    private static Comparable[] sortedStringArray = new Comparable[]{"a", "b", "b", "b", "r", "s", "x", "z"};

    public Comparable[] getSortedBooleanArray() { return sortedBooleanArray; }
    public Comparable[] getSortedDoubleArray() { return sortedDoubleArray; }
    public Comparable[] getSortedFloatArray() { return sortedFloatArray; }
    public Comparable[] getSortedIntArray() { return sortedIntArray; }
    public Comparable[] getSortedStringArray() { return sortedStringArray; }
    
    private static int length = 8;
    public int getLength() { return 8;}
    
    @Test
    public void booleanSelectionSort() {
        Comparable[] array = getUnsortedBooleanArray();
        int length = getLength();
        long count = new SelectionSort().sort(array);
        if (Arrays.equals(array, getSortedBooleanArray()) == false) fail("boolean array was not sorted via selection sort");
    }
    
    @Test
    public void doubleSelectionSort() {
        Comparable[] array = getUnsortedDoubleArray();
        int length = getLength();
        long count = new SelectionSort().sort(array);
        if (Arrays.equals(array, getSortedDoubleArray()) == false) fail("double array was not sorted via selection sort");
    }
    
    @Test
    public void floatSelectionSort() {
        Comparable[] array = getUnsortedFloatArray();
        int length = getLength();
        long count = new SelectionSort().sort(array);
        if (Arrays.equals(array, getSortedFloatArray()) == false) fail("float array was not sorted via selection sort");
    }
    
    @Test
    public void intSelectionSort() {
        Comparable[] array = getUnsortedIntArray();
        int length = getLength();
        long count = new SelectionSort().sort(array);
        if (Arrays.equals(array, getSortedIntArray()) == false) fail("integer array was not sorted via selection sort");
    }
    
    @Test
    public void stringSelectionSort() {
        Comparable[] array = getUnsortedStringArray();
        int length = getLength();
        long count = new SelectionSort().sort(array);
        if (Arrays.equals(array, getSortedStringArray()) == false) fail("string array was not sorted via selection sort");
    }
    
    @Test
    public void booleanInsertionSort() {
        Comparable[] array = getUnsortedBooleanArray();
        int length = getLength();
        long count = new InsertionSort().sort(array);
        if (Arrays.equals(array, getSortedBooleanArray()) == false) fail("boolean array was not sorted via insertion sort");
    }
    
    @Test
    public void doubleInsertionSort() {
        Comparable[] array = getUnsortedDoubleArray();
        int length = getLength();
        long count = new InsertionSort().sort(array);
        if (Arrays.equals(array, getSortedDoubleArray()) == false) fail("double array was not sorted via insertion sort");
    }
    
    @Test
    public void floatInsertionSort() {
        Comparable[] array = getUnsortedFloatArray();
        int length = getLength();
        long count = new InsertionSort().sort(array);
        if (Arrays.equals(array, getSortedFloatArray()) == false) fail("float array was not sorted via insertion sort");
    }
    
    @Test
    public void intInsertionSort() {
        Comparable[] array = getUnsortedIntArray();
        int length = getLength();
        long count = new InsertionSort().sort(array);
        if (Arrays.equals(array, getSortedIntArray()) == false) fail("integer array was not sorted via insertion sort");
    }
    
    @Test
    public void stringInsertionSort() {
        Comparable[] array = getUnsortedStringArray();
        int length = getLength();
        long count = new InsertionSort().sort(array);
        if (Arrays.equals(array, getSortedStringArray()) == false) fail("string array was not sorted via insertion sort");
    }
    
    @Test
    public void booleanQuickSort() {
        Comparable[] array = getUnsortedBooleanArray();
        int length = getLength();
        long count = new QuickSort().sort(array);
        if (Arrays.equals(array, getSortedBooleanArray()) == false) fail("boolean array was not sorted via quick sort");
    }
    
    @Test
    public void doubleQuickSort() {
        Comparable[] array = getUnsortedDoubleArray();
        int length = getLength();
        long count = new QuickSort().sort(array);
        if (Arrays.equals(array, getSortedDoubleArray()) == false) fail("double array was not sorted via quick sort");
    }
    
    @Test
    public void floatQuickSort() {
        Comparable[] array = getUnsortedFloatArray();
        int length = getLength();
        long count = new QuickSort().sort(array);
        if (Arrays.equals(array, getSortedFloatArray()) == false) fail("float array was not sorted via quick sort");
    }
    
    @Test
    public void intQuickSort() {
        Comparable[] array = getUnsortedIntArray();
        int length = getLength();
        long count = new QuickSort().sort(array);
        if (Arrays.equals(array, getSortedIntArray()) == false) fail("integer array was not sorted via quick sort");
    }
    
    @Test
    public void stringQuickSort() {
        Comparable[] array = getUnsortedStringArray();
        int length = getLength();
        long count = new QuickSort().sort(array);
        if (Arrays.equals(array, getSortedStringArray()) == false) fail("string array was not sorted via quick sort");
    }

    
}
