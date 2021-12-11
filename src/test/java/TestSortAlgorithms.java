import collections.implementation.SortAndSearching;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

public class TestSortAlgorithms {

    @RepeatedTest(200)
    public void testQuickSortArrayRandomNumbers() {
        Integer[] originalArray = new Integer[10];
        Integer[] tmpArray = new Integer[10];

        for (int i = 0; i < originalArray.length; i++) {
            originalArray[i] = ThreadLocalRandom.current().nextInt(i * 12, (1 + i) * 11 + 1); //Random unique values in array
            tmpArray[i] = originalArray[i];
        }

        SortAndSearching.selectionSort(tmpArray); //Secure operation, method tested.
        SortAndSearching.quickSort(originalArray, 0, originalArray.length - 1);

        Assertions.assertArrayEquals(originalArray, tmpArray);
    }

    @Test
    public void testQuickSortArrayLimits() {
        Integer[] originalArray = new Integer[5];
        Integer[] tmpArray = new Integer[5];
        originalArray[0] = tmpArray[0] = 4;
        originalArray[1] = tmpArray[1] = 3;
        originalArray[2] = tmpArray[2] = 1;
        originalArray[3] = tmpArray[3] = 7;
        originalArray[4] = tmpArray[4] = 8;

        SortAndSearching.selectionSort(tmpArray); //Secure operation, method tested.
        SortAndSearching.quickSort(originalArray, 0, originalArray.length - 1);

        Assertions.assertArrayEquals(originalArray, tmpArray);

        originalArray[0] = tmpArray[0] = 2;
        originalArray[1] = tmpArray[1] = 7;
        originalArray[2] = tmpArray[2] = 10;
        originalArray[3] = tmpArray[3] = 8;
        originalArray[4] = tmpArray[4] = 9;

        SortAndSearching.selectionSort(tmpArray); //Secure operation, method tested.
        SortAndSearching.quickSort(originalArray, 0, originalArray.length - 1);

        Assertions.assertArrayEquals(originalArray, tmpArray);
    }

    @RepeatedTest(200)
    public void testMergeSortArrayRandomNumbers() {
        Integer[] originalArray = new Integer[10];
        Integer[] tmpArray = new Integer[10];

        for (int i = 0; i < originalArray.length; i++) {
            originalArray[i] = ThreadLocalRandom.current().nextInt(i * 12, (1 + i) * 11 + 1); //Random unique values in array
            tmpArray[i] = originalArray[i];
        }

        SortAndSearching.selectionSort(tmpArray); //Secure operation, method tested.
        SortAndSearching.mergeSort(originalArray, 0, originalArray.length - 1);

        Assertions.assertArrayEquals(originalArray, tmpArray);
    }
}

