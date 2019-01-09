package packageName;

import java.util.LinkedList;
import java.util.Random;

public class SortingTrash {
    private int SIZE = 100;
    private static Random random = new Random(47);
    private int[] array;

    public int[] getArray() {
        return array;
    }

    {
        array = new int[SIZE];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100);
        }
    }

    public void shuffle() {
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100);
        }
    }

    public int[] bubbleSort() {
        for (int i = 0; i < array.length; i++) {
            for (int i1 = 1; i1 < array.length; i1++) {
                if (array[i1 - 1] > array[i1]) {
                    int tmp = array[i1];
                    array[i1] = array[i1 - 1];
                    array[i1 - 1] = tmp;
                }
            }
        }
        return array;
    }

    public int[] selectionSort() {
        for (int i = 0; i < array.length; i++) {
            int minInd = i;
            for (int j = i; j < array.length; j++) {
                if (array[minInd] > array[j]) {
                    minInd = j;
                }
            }
            int tmp = array[minInd];
            array[minInd] = array[i];
            array[i] = tmp;
        }

        return array;
    }

    public int[] insertionSort() {

        for (int i = 1; i < array.length; i++) {

            int ptr = i;
            int tmp = array[ptr];

            for (int j = i - 1; j >= 0; j--) {
                if (tmp < array[j]) {
                    array[ptr] = array[j];
                    ptr = j;
                } else {
                    break;
                }
            }
            array[ptr] = tmp;
        }
        return array;
    }

    public int[] shellSort1() {
        int ptr;
        int tmp;
        int h = 1;
        while (h <= array.length / 3) {
            h *= 3 + 1;
        }

        while (h > 0) {

            for (int i = h; i < array.length; i++) {
                ptr = i;
                tmp = array[ptr];

                while (ptr > (h - 1) && array[ptr - h] >= tmp) {
                    array[ptr] = array[ptr - h];
                    ptr -= h;
                }

                array[ptr] = tmp;
            }

            h = (h - 1) / 3;
        }

        return array;
    }

    public static <T extends Comparable<T>> T[] shellSort2(T[] array) {
        int ptr;
        T tmp;
        int h = 1;
        while (h <= array.length / 3) {
            h = h * 3 + 1;
        }

        while (h > 0) {

            for (int i = h; i < array.length; i++) {
                ptr = i;
                tmp = array[ptr];

                while (ptr > (h - 1) && array[ptr - h].compareTo(tmp) > 0) {
                    array[ptr] = array[ptr - h];
                    ptr -= h;
                }
                array[ptr] = tmp;
            }

            h = (h - 1) / 3;
        }

        return array;
    }

    public int[] recursiveMergeSort() {
        return mergeSort(array, 0, array.length - 1);
    }

    public int[] mergeSort(int[] array, int left, int right) {

        if (left >= right) {
            return new int[]{array[left]};
        }

        int[] arrayOne = mergeSort(array, left, (left + right) / 2);
        int[] arrayTwo = mergeSort(array, (left + right) / 2 + 1, right);

        return merge(arrayOne, arrayTwo);
    }

    private int[] merge(int[] arrayOne, int[] arrayTwo) {
        int ptrOne = 0;
        int ptrTwo = 0;
        int[] result = new int[arrayOne.length + arrayTwo.length];

        int resultPtr = 0;
        while (ptrOne < arrayOne.length && ptrTwo < arrayTwo.length) {
            if (arrayOne[ptrOne] < arrayTwo[ptrTwo]) {
                result[resultPtr++] = arrayOne[ptrOne++];
            } else {
                result[resultPtr++] = arrayTwo[ptrTwo++];
            }
        }
        while (ptrOne < arrayOne.length) {
            result[resultPtr++] = arrayOne[ptrOne++];
        }
        while (ptrTwo < arrayTwo.length) {
            result[resultPtr++] = arrayTwo[ptrTwo++];
        }

        return result;
    }

    public int[] iterativeMergeSort() {
        return iterativeMergeSort(array, 0, array.length - 1);
    }

    private int[] iterativeMergeSort(int[] array, int left, int right) {
        int[][] tempArrays = new int[right - left + 1][1];
        for (int j = 0; j < tempArrays.length; j++) {
            tempArrays[j][0] = array[j];
        }

        int k = 1;
        while (k <= (tempArrays.length / 2) + 1) {
            for (int j = k; j < tempArrays.length; j += 2 * k) {
                tempArrays[j - k] = merge(tempArrays[j - k], tempArrays[j]);
            }

            if (tempArrays.length % (2 * k) != 0) {
                int tail = tempArrays.length % (2 * k);
                if (tail > k) {
                    tempArrays[0] = merge(tempArrays[tempArrays.length - tail], tempArrays[0]);
                }
            }
            k *= 2;
        }
        return tempArrays[0];
    }

    public int[] recursiveQuickSort() {
        qSort(array, 0, array.length - 1);
        return array;
    }

    private void qSort(int[] array, int left, int right) {

        if (left < right) {
            int pivotInd = partition2(array, left, right);
            qSort(array, left, pivotInd - 1);
            qSort(array, pivotInd + 1, right);
        }
    }

    private int partition(int[] array, int left, int right) {

        int pivot = array[right];
        int i = left;
        for (int j = left; j < right; j++) {
            if (array[j] <= pivot) {

                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;

                i++;
            }
        }
        int temp = array[i];
        array[i] = array[right];
        array[right] = temp;

        return i;
    }

    private int partition2(int[] array, int l, int r){
        int pivot = array[r];
        int index = l;

        for (int i = l; i < r; i++) {
            if(array[i]<pivot){

                int tmp = array[index];
                array[index] = array[i];
                array[i] = tmp;

                index++;
            }
        }
        int tmp = array[index];
        array[index] = array[r];
        array[r] = tmp;

        return index;
    }

    public int[] iterativeQuickSort() {
        iterativeQSort(array, 0, array.length - 1);
        return array;
    }

    private void iterativeQSort(int[] array, int left, int right) {
        LinkedList<Integer> limits = new LinkedList<>();

        limits.push(right);
        limits.push(left);

        while (!limits.isEmpty()) {

            Integer leftInd = limits.pop();
            Integer rightInd = limits.pop();

            int pivotInd = partition(array, leftInd, rightInd);

            if (pivotInd - 1 > leftInd) {
                limits.push(pivotInd - 1);
                limits.push(leftInd);
            }
            if (pivotInd + 1 < rightInd) {
                limits.push(rightInd);
                limits.push(pivotInd + 1);
            }
        }
    }

    public int[] shellSort() {
        int ptr;
        int h = 1;
        int tmp;

        while (h <= array.length / 3) {
            h = h * 3 + 1;
        }

        while (h > 0) {

            for (int outer = h; outer < array.length; outer++) {
                tmp = array[outer];
                ptr = outer;

                while (ptr > h - 1 && array[ptr - h] >= tmp) {
                    array[ptr] = array[ptr - h];
                    ptr -= h;
                }
                array[ptr] = tmp;
            }

            h = (h - 1) / 3;
        }

        return array;
    }

    public int[] recursiveQSort(){
        recQSort(array, 0, array.length - 1);

        return array;
    }

    private void recQSort(int[] array, int l, int r) {
        if(l<r){
            int pivotInd = partition3(array, l, r);
            recQSort(array, l, pivotInd-1);
            recQSort(array, pivotInd+1, r);
        }
    }

    private int partition3(int[] array, int l, int r){
        int pivotInd = l;
        int pivot = array[r];

        for (int i = l; i < r; i++) {
            if(array[i] < pivot){
                int tmp = array[pivotInd];
                array[pivotInd] = array[i];
                array[i] = tmp;

                pivotInd++;
            }
        }
        int tmp = array[pivotInd];
        array[pivotInd] = pivot;
        array[r] = tmp;

        return pivotInd;
    }

    public void heapSort() {

    }

}
