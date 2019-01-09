package packageName;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.Arrays;
import static org.junit.Assert.assertArrayEquals;
import static packageName.SortingTrash.shellSort2;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void bubbleSortTest(){
        SortingTrash arrayHolder = new SortingTrash();
        arrayHolder.shuffle();
        int[] shuffledArray = Arrays.copyOf(arrayHolder.getArray(),
                arrayHolder.getArray().length);

        Arrays.sort(shuffledArray);

        assertArrayEquals("bubble sort failure: int arrays are not the same",
                shuffledArray, arrayHolder.bubbleSort());
    }
    @Test
    public void selectionSortTest(){
        SortingTrash arrayHolder = new SortingTrash();
        arrayHolder.shuffle();
        int[] shuffledArray = Arrays.copyOf(arrayHolder.getArray(),
                arrayHolder.getArray().length);

        Arrays.sort(shuffledArray);

        int[] sorted = arrayHolder.selectionSort();

        assertArrayEquals("selection sort failure: int arrays are not the same",
                shuffledArray, sorted);
    }

    @Test
    public void insertionSortTest(){
        SortingTrash arrayHolder = new SortingTrash();
        arrayHolder.shuffle();
        int[] shuffledArray = Arrays.copyOf(arrayHolder.getArray(),
                arrayHolder.getArray().length);

        Arrays.sort(shuffledArray);

        int[] sorted = arrayHolder.insertionSort();

        assertArrayEquals("insertion sort failure: int arrays are not the same",
                shuffledArray, sorted);
    }

    @Test
    public void recursiveMergeSortTest(){
        SortingTrash arrayHolder = new SortingTrash();
        arrayHolder.shuffle();
        int[] shuffledArray = Arrays.copyOf(arrayHolder.getArray(),
                arrayHolder.getArray().length);

        Arrays.sort(shuffledArray);

        int[] sorted = arrayHolder.recursiveMergeSort();

        assertArrayEquals("recursiveMergeSort sort failure: int " +
                        "arrays are not the same",
                shuffledArray, sorted);
    }

    @Test
    public void iterativeMergeSortTest(){
        SortingTrash arrayHolder = new SortingTrash();
        arrayHolder.shuffle();
        int[] shuffledArray = Arrays.copyOf(arrayHolder.getArray(),
                arrayHolder.getArray().length);

        Arrays.sort(shuffledArray);

        int[] sorted = arrayHolder.iterativeMergeSort();

        assertArrayEquals("iterativeMergeSort sort failure: int " +
                        "arrays are not the same",
                shuffledArray, sorted);
    }

    @Test
    public void recursiveQuickSortTest(){
        SortingTrash arrayHolder = new SortingTrash();
        arrayHolder.shuffle();
        int[] shuffledArray = Arrays.copyOf(arrayHolder.getArray(),
                arrayHolder.getArray().length);

        Arrays.sort(shuffledArray);

        int[] sorted = arrayHolder.recursiveQuickSort();

        assertArrayEquals("recursiveQuickSort sort failure: int " +
                        "arrays are not the same",
                shuffledArray, sorted);
    }

    @Test
    public void iterativeQuickSortTest(){
        SortingTrash arrayHolder = new SortingTrash();
        arrayHolder.shuffle();
        int[] shuffledArray = Arrays.copyOf(arrayHolder.getArray(),
                arrayHolder.getArray().length);

        Arrays.sort(shuffledArray);

        int[] sorted = arrayHolder.iterativeQuickSort();

        assertArrayEquals("iterativeQuickSort sort failure: int " +
                        "arrays are not the same",
                shuffledArray, sorted);
    }

    @Test
    public void iterativeShellSortTest(){
        SortingTrash arrayHolder = new SortingTrash();
        arrayHolder.shuffle();
        int[] shuffledArray = Arrays.copyOf(arrayHolder.getArray(),
                arrayHolder.getArray().length);

        Arrays.sort(shuffledArray);

        int[] sorted = arrayHolder.shellSort1();

        System.out.println(Arrays.toString(sorted));
        System.out.println(Arrays.toString(shuffledArray));

        assertArrayEquals("iterativeShellSort sort failure: int " +
                        "arrays are not the same",
                shuffledArray, sorted);
    }

    @Test
    public void recQuickSortTest(){
        SortingTrash arrayHolder = new SortingTrash();
        arrayHolder.shuffle();
        int[] shuffledArray = Arrays.copyOf(arrayHolder.getArray(),
                arrayHolder.getArray().length);

        Arrays.sort(shuffledArray);

        int[] sorted = arrayHolder.recursiveQSort();

        System.out.println(Arrays.toString(sorted));
        System.out.println(Arrays.toString(shuffledArray));

        assertArrayEquals("recQuickSortTest sort failure: int " +
                        "arrays are not the same",
                shuffledArray, sorted);
    }

    @Test
    public void iterativeShellSortTest2(){
        SortingTrash arrayHolder = new SortingTrash();
        arrayHolder.shuffle();
        int[] shuffledArray = Arrays.copyOf(arrayHolder.getArray(),
                arrayHolder.getArray().length);

        Arrays.sort(shuffledArray);

        int[] sortedArray = arrayHolder.getArray();

        Integer[] integers = new Integer[sortedArray.length];
        for (int i = 0; i < integers.length; i++) {
            integers[i] = sortedArray[i];
        }
        Integer[] sorted = shellSort2(integers);
        for (int i = 0; i < sorted.length; i++) {
            sortedArray[i] = sorted[i];
        }


        System.out.println(Arrays.toString(sortedArray));
        System.out.println(Arrays.toString(shuffledArray));

        assertArrayEquals("iterativeQuickSort sort failure: int " +
                        "arrays are not the same",
                shuffledArray, sortedArray);
    }

}
