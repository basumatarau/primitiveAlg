package packageName;

import java.util.Arrays;
import java.util.LinkedList;

public class SortingDemo {

    public static <T extends Comparable<T>> T[] bubbleSort(T[] array) {
        T tmp;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[j].compareTo(array[i]) > 0) {
                    tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                }
            }
        }
        return array;
    }

    public static <T extends Comparable<T>> T[] selectionSort(T[] array) {
        T tmp;
        int minInd;
        for (int i = 0; i < array.length; i++) {
            minInd = i;
            for (int j = i; j < array.length; j++) {
                if (array[j].compareTo(array[minInd]) < 0) {
                    minInd = j;
                }
            }
            tmp = array[i];
            array[i] = array[minInd];
            array[minInd] = tmp;
        }

        return array;
    }

    public static <T extends Comparable<T>> T[] insertionSort(T[] array) {

        for (int i = 1; i < array.length; i++) {
            int ptr = i;
            T tmp = array[ptr];

            int inner = i - 1;
            while (inner >= 0 && tmp.compareTo(array[inner]) < 0) {
                array[ptr] = array[inner];
                ptr = inner--;
            }

            array[ptr] = tmp;
        }
        return array;
    }

    public static <T extends Comparable<T>> T[] recurrentMergeSort(T[] array) {
        recMergeSort(array, 0, array.length - 1);
        return array;
    }

    private static <T extends Comparable<T>> void recMergeSort(T[] array, int l, int r) {
        if (l >= r) {
            return;
        }

        recMergeSort(array, l, (l + r) / 2);
        recMergeSort(array, (l + r) / 2 + 1, r);

        merge(array, l, r);
    }

    private static <T extends Comparable<T>> void merge(T[] array, int l, int r) {

        int middle = (l + r) / 2;
        int ptrOne = l;
        int ptrTwo = middle + 1;

        Comparable[] temp = new Comparable[r - l + 1];

        int ptrResult = 0;

        while (ptrOne <= middle && ptrTwo <= r) {

            T leftVal = ptrOne <= middle ? array[ptrOne] : null;
            T rightVal = ptrTwo <= r ? array[ptrTwo] : null;

            if (leftVal != null && rightVal == null) {
                temp[ptrResult++] = array[ptrOne++];
            } else if (leftVal == null && rightVal != null) {
                temp[ptrResult++] = array[ptrTwo++];
            } else {
                if (array[ptrOne].compareTo(array[ptrTwo]) < 0) {
                    temp[ptrResult++] = array[ptrOne++];
                } else {
                    temp[ptrResult++] = array[ptrTwo++];
                }
            }
        }
        while (ptrOne <= middle) {
            temp[ptrResult++] = array[ptrOne++];
        }
        while (ptrTwo <= r) {
            temp[ptrResult++] = array[ptrTwo++];
        }

        for (int i = 0; i < temp.length; i++) {
            //noinspection unchecked
            array[l + i] = ((T) temp[i]);
        }
    }

    public static <T extends Comparable<T>> T[] recurrentMergeSort2(T[] array) {
        recMergeSort2(array, 0, array.length);
        return array;
    }

    private static <T extends Comparable<T>> void recMergeSort2(T[] array, int left, int right) {
        if (right - left > 2) {
            recMergeSort2(array, left, left + (right - left) / 2);
            recMergeSort2(array, left + (right - left) / 2, right);
        }
        merge2(array, left, right);
    }

    private static <T extends Comparable<T>> void merge2(T[] array, int left, int right) {
        T[] tmp = Arrays.copyOfRange(array, left, left + (right - left) / 2);
        int ptrLeft = 0;
        int ptrRight = left + (right - left) / 2;
        int ptrWrite = left;

        while (ptrLeft < tmp.length && ptrRight < right) {
            if (array[ptrRight].compareTo(tmp[ptrLeft]) < 0) {
                array[ptrWrite++] = array[ptrRight++];
            } else {
                array[ptrWrite++] = tmp[ptrLeft++];
            }
        }
        while (ptrLeft < tmp.length) {
            array[ptrWrite++] = tmp[ptrLeft++];
        }
    }

    public static <T extends Comparable<T>> T[] iterativeMergeSort(T[] array) {
        iterativeMergeSort(array, 0, array.length);
        return array;
    }

    private static <T extends Comparable<T>> void iterativeMergeSort(T[] array, int l, int r) {

        int k = 2;

        while (k <= array.length) {

            for (int i = k; i <= array.length; i += k) {
                int m = (i - k) + (k - 1) / 2;
                merge3(array, i - k, m, i - 1);
            }

            int tail = array.length % k;
            if (tail > k / 2) {
                int m = array.length - tail % (k / 2) - 1;
                merge3(array, array.length - tail, m, array.length - 1);
            }

            if (k * 2 > array.length) {
                merge3(array, 0, array.length - tail - 1, array.length - 1);
                break;
            }

            k *= 2;
        }

    }

    private static <T extends Comparable<T>> void merge3(T[] array, int l, int m, int r) {
        int ptrLeft = l;
        int ptrRight = m + 1;
        int ptrResult = 0;
        T[] tmp = Arrays.copyOfRange(array, l, r + 1);

        while (ptrLeft <= m && ptrRight <= r) {
            if (array[ptrLeft].compareTo(array[ptrRight]) > 0) {
                tmp[ptrResult++] = array[ptrRight++];
            } else {
                tmp[ptrResult++] = array[ptrLeft++];
            }
        }

        while (ptrLeft <= m) {
            tmp[ptrResult++] = array[ptrLeft++];
        }
        while (ptrRight <= r) {
            tmp[ptrResult++] = array[ptrRight++];
        }

        System.arraycopy(tmp, 0, array, l, tmp.length);
    }

    public static <T extends Comparable<T>> void recursiveQuickSort(T[] array) {
        recursiveQSort(array, 0, array.length - 1);
    }

    private static <T extends Comparable<T>> void recursiveQSort(T[] array, int left, int right) {
        if (left < right) {
            int pivotInd = partition(array, left, right);
            recursiveQSort(array, left, pivotInd - 1);
            recursiveQSort(array, pivotInd + 1, right);
        }
    }

    private static <T extends Comparable<T>> int partition(T[] array, int left, int right) {
        T pivot = array[right];
        int pivotInd = left;

        for (int i = left; i < right; i++) {
            if (array[i].compareTo(pivot) < 0) {
                T tmp = array[pivotInd];
                array[pivotInd] = array[i];
                array[i] = tmp;

                pivotInd++;
            }
        }
        T tmp = array[pivotInd];
        array[pivotInd] = pivot;
        array[right] = tmp;

        return pivotInd;
    }

    public static <T extends Comparable<T>> void iterativeQuickSort(T[] array) {
        LinkedList<Integer> stack = new LinkedList<>();

        stack.push(0);
        stack.push(array.length - 1);

        while (!stack.isEmpty()) {
            Integer right = stack.pop();
            Integer left = stack.pop();
            if (left < right) {
                int pivotInd = partition2(array, left, right);

                stack.push(left);
                stack.push(pivotInd - 1);
                stack.push(pivotInd + 1);
                stack.push(right);
            }
        }
    }

    private static <T extends Comparable<T>> int partition2(T[] array, int left, int right) {

        T pivot = array[right];
        int leftPtr = left - 1;
        int rightPtr = right + 1;

        while (true) {

            while (leftPtr < right) {
                if (array[++leftPtr].compareTo(pivot) > 0) {
                    break;
                }
            }
            while (rightPtr > left){
                if (array[--rightPtr].compareTo(pivot) < 0){
                    break;
                }
            }

            if(leftPtr>=rightPtr){
                break;
            }else{
                T tmp = array[rightPtr];
                array[rightPtr] = array[leftPtr];
                array[leftPtr] = tmp;
            }
        }

        array[right] = array[leftPtr];
        array[leftPtr] = pivot;

        return leftPtr;
    }

    public static <T extends Comparable<T>> void recursiveQuickSort2(T[] array){
        recursiveQuickSort2(array, 0, array.length - 1);
    }

    private static <T extends Comparable<T>> void recursiveQuickSort2(T[] array, int left, int right) {
        if(right - left + 1 > 3){
            int pivotInd = partition3(array, left, right);
            recursiveQuickSort2(array, left, pivotInd - 1);
            recursiveQuickSort2(array, pivotInd + 1, right);
        }else{
            manualSort(array, left, right);
        }
    }

    private static <T extends Comparable<T>> void manualSort(T[] array, int left, int right) {
        int size = right - left + 1;

        if (size <= 1){
            return;
        }else if (size == 2){
            if (array[left].compareTo(array[right]) > 0){
                T tmp = array[left];
                array[left] = array[right];
                array[right] = tmp;
            }
        }else {
            if (array[left].compareTo(array[right - 1]) > 0){
                T tmp = array[left];
                array[left] = array[right - 1];
                array[right - 1] = tmp;
            }
            if (array[left].compareTo(array[right]) > 0){
                T tmp = array[left];
                array[left] = array[right];
                array[right] = tmp;
            }
            if(array[right - 1].compareTo(array[right]) > 0){
                T tmp = array[right - 1];
                array[right - 1] = array[right];
                array[right] = tmp;
            }
        }
    }

    private static <T extends Comparable<T>> int partition3(T[] array, int left, int right) {
        T pivot = selectMedianPivot(array, left, right);

        int leftPtr = left;
        int rightPtr = right - 1;

        while (true){
            while (array[++leftPtr].compareTo(pivot) < 0) {
            }

            while (array[--rightPtr].compareTo(pivot) > 0) {
            }

            if(leftPtr >= rightPtr){
                break;
            }else {
                T tmp = array[leftPtr];
                array[leftPtr] = array[rightPtr];
                array[rightPtr] = tmp;
            }
        }
        T tmp = array[leftPtr];
        array[leftPtr] = array[right - 1];
        array[right - 1] = tmp;

        return leftPtr;
    }

    private static <T extends Comparable<T>> T selectMedianPivot(T[] array, int left, int right) {

        int medianInd = (left + right) / 2;
        T tmp;

        if(array[left].compareTo(array[medianInd])>0){
            tmp = array[left];
            array[left] = array[medianInd];
            array[medianInd] = tmp;
        }
        if(array[left].compareTo(array[right])>0){
            tmp = array[left];
            array[left] = array[right];
            array[right] = tmp;
        }
        if(array[medianInd].compareTo(array[right])>0){
            tmp = array[medianInd];
            array[medianInd] = array[right];
            array[right] = tmp;
        }

        tmp = array[medianInd];
        array[medianInd] = array[right - 1];
        array[right - 1] = tmp;

        return array[right - 1];
    }


    public static <T extends Comparable<T>> void iterativeShellSort(T[] array) {
        int h = 1;
        while (h <= array.length / 3) {
            h = h * 3 + 1;
        }

        while (h > 0) {
            for (int i = h; i < array.length; i++) {
                int insertionInd = i;
                T insertedItem = array[insertionInd];

                for (int j = i; j >= h; j -= h) {
                    if (array[j - h].compareTo(insertedItem) > 0) {
                        array[j] = array[j - h];
                        insertionInd = j - h;
                    } else {
                        break;
                    }
                }

                array[insertionInd] = insertedItem;
            }
            h = (h - 1) / 3;
        }
    }

    //todo heapSort alg to be implemented here...
}
