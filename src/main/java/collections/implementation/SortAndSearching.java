package collections.implementation;

import collections.interfaces.ListADT;

import java.util.Iterator;

public class SortAndSearching {

    /**
     * Private constructor to prevent that is not instantiate.
     */
    private SortAndSearching() {
    }

    // ------------------------------ Search methods ------------------------------ //

    /**
     * Searches the specified array of objects using a linear search algorithm.
     *
     * @param data   the array to be searched
     * @param min    the integer representation of the min value
     * @param max    the integer representation of the max value
     * @param target the element being searched for
     * @return true if the desired element is found
     */
    public static <T extends Comparable<? super T>> boolean linearSearch(T[] data, int min, int max, T target) {
        int index = min;
        boolean found = false;

        if (index >= 0 && index < data.length) {
            while (!found && index <= max) {
                if (data[index].compareTo(target) == 0) {
                    found = true;
                }
                index++;
            }
        }
        return found;
    }

    /**
     * Searches the specified list of objects using a linear search algorithm.
     *
     * @param data   the instance of {@link ListADT} to be searched
     * @param min    the integer representation of the min value
     * @param max    the integer representation of the max value
     * @param target the element being searched for
     * @return true if the desired element is found
     */
    public static <T extends Comparable<? super T>> boolean linearSearch(ListADT<T> data, int min, int max, T target) {
        boolean found = false;
        Iterator<T> iterator = data.iterator();
        int counter = 0;

        while (!found && iterator.hasNext() && counter <= max) {
            if (counter >= min) {
                if (iterator.next().compareTo(target) == 0) {
                    found = true;
                }
            }
            counter++;
        }
        return found;
    }

    /**
     * Searches the specified array of objects using a binary search algorithm.
     *
     * @param data   the array to be searched
     * @param min    the integer representation of the minimum value
     * @param max    the integer representation of the maximum value
     * @param target the element being searched for
     * @return true if the desired element is found
     */
    public static <T extends Comparable<? super T>> boolean binarySearch(T[] data, int min, int max, T target) {
        boolean found = false;
        int midPoint = (min + max) / 2;

        if (data[midPoint].compareTo(target) == 0) {
            found = true;
        } else if (data[midPoint].compareTo(target) > 0) { //If midpoint is greater
            if (min <= midPoint - 1) {
                found = binarySearch(data, min, midPoint - 1, target);
            }
        } else if (midPoint + 1 <= max) {
            return binarySearch(data, midPoint + 1, max, target);
        }
        return found;
    }

    //------------------------------ Sort methods on arrays ------------------------ //

    /**
     * Sorts the specified array of integers using the selection sort algorithm.
     *
     * @param data the array to be sorted
     */
    public static <T extends Comparable<? super T>> void selectionSort(T[] data) {
        int min;
        T temp;
        for (int index = 0; index < data.length - 1; index++) {
            min = index;
            for (int scan = index + 1; scan < data.length; scan++) {
                if (data[scan].compareTo(data[min]) < 0) {
                    min = scan;
                }
            }
            temp = data[min];
            data[min] = data[index];
            data[index] = temp;
        }
    }

    /**
     * Sorts the specified array of objects using an insertion sort algorithm.
     *
     * @param data the array to be sorted
     */
    public static <T extends Comparable<? super T>> void insertionSort(T[] data) {
        boolean sorted = false;
        T tmp;
        for (int index = 0; index < data.length - 1; index++, sorted = false) {
            for (int position = index + 1; !sorted && position >= 1; position--) {
                if (data[position - 1].compareTo(data[position]) > 0) {
                    tmp = data[position - 1];
                    data[position - 1] = data[position];
                    data[position] = tmp;
                } else {
                    sorted = true;
                }
            }
        }
    }

    /**
     * Sorts the specified array of objects using a bubble sort
     * algorithm.
     *
     * @param data the array to be sorted
     */
    public static <T extends Comparable<? super T>> void bubbleSort(T[] data) {
        T tmp;
        for (int index = data.length; index >= 0; index--) {
            for (int position = 1; position < index; position++) {
                if (data[position - 1].compareTo(data[position]) > 0) {
                    tmp = data[position - 1];
                    data[position - 1] = data[position];
                    data[position] = tmp;
                }
            }
        }
    }

    /**
     * Used by the quick sort algorithm to find the partition.
     *
     * @param data the array to be sorted
     * @param min  the integer representation of the minimum value
     * @param max  the integer representation of the maximum value
     */
    private static <T extends Comparable<? super T>> int findPartition(T[] data, int min, int max) {
        int left, right;
        T temp, partitionelement;
        int middle = (min + max) / 2;
        // use middle element as partition
        partitionelement = data[middle];
        left = min; //Left side will go from 0 to middle
        right = max; //Right side will go from last to middle
        while (left < right) {
            while (data[left].compareTo(partitionelement) < 0) { //Lock left pointer to element smaller then pivot
                left++;
            }
            while (data[right].compareTo(partitionelement) > 0) { //Lock right pointer to element greater then pivot
                right--;
            }
            /** swap the elements */
            if (left < right) {
                temp = data[left];
                data[left] = data[right];
                data[right] = temp;
            }
        }
        temp = data[min];
        data[min] = data[right];
        data[right] = temp;
        return right;
    }

    private static <T extends Comparable<? super T>> int findPartitionLeftPivot(T[] data, int min, int max) {
        int left, right;
        T temp, partitionelement;
        //use the first element as the partition element
        partitionelement = data[min];
        left = min;
        right = max;
        while (left < right) {
            // search for an element that is > the partitionelement
            while (data[left].compareTo(partitionelement) <= 0 && left < right) {
                left++;
            }
            //search for an element that is < the partitionelement
            while (data[right].compareTo(partitionelement) > 0) {
                right--;
            }
            //swap the elements
            if (left < right) {
                temp = data[left];
                data[left] = data[right];
                data[right] = temp;
            }
        }
        //move partition element to partition index
        temp = data[min];
        data[min] = data[right];
        data[right] = temp;

        return right;
    }

    /**
     * Sorts the specified array of objects using the quick sort algorithm.
     *
     * @param data the array to be sorted
     * @param min  the integer representation of the minimum value
     * @param max  the integer representation of the maximum value
     */
    public static <T extends Comparable<? super T>> void quickSort(T[] data, int min, int max) {
        if (max - min > 0) {
            int pivotIndex = findPartitionLeftPivot(data, min, max);

            quickSort(data, min, pivotIndex - 1);
            quickSort(data, pivotIndex + 1, max);
        }
    }

    /**
     * Merge two given sorted arrays in a sorted array that have both elements.
     *
     * @param arrA first array
     * @param arrB second array
     * @return array sorted will all elements
     */
    @SuppressWarnings("unchecked")
    private static <T extends Comparable<? super T>> T[] merge(T[] arrA, T[] arrB) {
        T[] arrC = (T[]) (new Comparable[arrA.length + arrB.length]);
        int indexA, indexB, indexC;
        indexA = indexB = indexC = 0;

        while (indexA < arrA.length && indexB < arrB.length) {
            if (arrA[indexA].compareTo(arrB[indexB]) > 0) { //If element in arrA is greater than element in arrB
                arrC[indexC] = arrA[indexA];
                indexA++;
                indexC++;
            } else {
                arrC[indexC] = arrB[indexB];
                indexC++;
                indexB++;
            }
        }
        while (indexA < arrA.length) {
            arrC[indexC] = arrA[indexA];
            indexA++;
            indexC++;
        }
        while (indexB < arrB.length) {
            arrC[indexC] = arrB[indexB];
            indexC++;
            indexB++;
        }
        return arrC;
    }

    /**
     * Sorts the specified array of objects using the merge sort algorithm.
     *
     * @param data the array to be sorted
     * @param min  the integer representation of the minimum value
     * @param max  the integer representation of the maximum value
     */
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<? super T>> void mergeSort(T[] data, int min, int max) {
        if (min != max) { //If the element have more than one element

            int size = max - min + 1; //Size of list
            int pivot = (min + max) / 2; //Middle of list
            T[] temp = (T[]) (new Comparable[size]); //Temp array with elements of right and left side to be merged
            int index1; //Pointer that will track the temp array
            int left = 0; //Pointer that will traverse left side
            int right = pivot - min + 1; //Pointer that will traverse right side

            mergeSort(data, min, pivot); // sort left half of list
            mergeSort(data, pivot + 1, max); // sort right half of list

            //Merge two parts
            for (index1 = 0; index1 < size; index1++) {//Create a temp array
                temp[index1] = data[min + index1];
            }

            for (index1 = 0; index1 < size; index1++) { //For all temp array
                if (right <= max - min) { //if right pointer has not reached the right limit

                    if (left <= pivot - min) { //if left pointer has not reached the left side limit

                        if (temp[left].compareTo(temp[right]) > 0) { //If element on left side is greater than right side
                            data[index1 + min] = temp[right++];
                        } else {
                            data[index1 + min] = temp[left++];
                        }

                    } else {
                        data[index1 + min] = temp[right++];
                    }
                } else {
                    data[index1 + min] = temp[left++];
                }
            }
        }
    }
}