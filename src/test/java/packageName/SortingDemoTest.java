package packageName;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import static org.junit.Assert.assertArrayEquals;

import static packageName.SortingDemo.*;

public class SortingDemoTest {

    private static Integer[] testArray;
    private static Random random = new Random(47);
    private static final int SIZE = 100;

    static {
        testArray = new Integer[SIZE];
        for (int i = 0; i < testArray.length; i++) {
            testArray[i] = random.nextInt(SIZE);
        }
    }
    private static void shuffleTestData(){
        if(testArray!=null){
            for (int i = 0; i < testArray.length; i++) {
                testArray[i] = random.nextInt(SIZE);
            }
        }
    }
    private static Integer[] getTestArrayCopy(){
        return Arrays.copyOf(testArray, testArray.length);
    }

    @Test
    public void bubbleSortTest(){
        shuffleTestData();
        Integer[] fix = getTestArrayCopy();
        Integer[] sorted = getTestArrayCopy();


        Arrays.sort(fix);
        bubbleSort(sorted);

        System.out.println(Arrays.toString(fix));
        System.out.println(Arrays.toString(sorted));

        assertArrayEquals("fail: ", fix, sorted);
    }

    @Test
    public void selectionSortTest(){
        shuffleTestData();
        Integer[] fix = getTestArrayCopy();
        Integer[] sorted = getTestArrayCopy();


        Arrays.sort(fix);
        selectionSort(sorted);

        System.out.println(Arrays.toString(fix));
        System.out.println(Arrays.toString(sorted));

        assertArrayEquals("fail: ", fix, sorted);
    }

    @Test
    public void insertionSortTest(){
        shuffleTestData();
        Integer[] fix = getTestArrayCopy();
        Integer[] sorted = getTestArrayCopy();


        Arrays.sort(fix);
        insertionSort(sorted);

        System.out.println(Arrays.toString(fix));
        System.out.println(Arrays.toString(sorted));

        assertArrayEquals("fail: ", fix, sorted);
    }

    @Test
    public void recurrentMergeSortTest(){
        shuffleTestData();
        Integer[] fix = getTestArrayCopy();
        Integer[] sorted = getTestArrayCopy();


        Arrays.sort(fix);
        recurrentMergeSort(sorted);

        System.out.println(Arrays.toString(fix));
        System.out.println(Arrays.toString(sorted));

        assertArrayEquals("fail: ", fix, sorted);
    }

    @Test
    public void recurrentMergeSortTest2(){
        shuffleTestData();
        Integer[] fix = getTestArrayCopy();
        Integer[] sorted = getTestArrayCopy();


        Arrays.sort(fix);
        recurrentMergeSort2(sorted);

        System.out.println(Arrays.toString(fix));
        System.out.println(Arrays.toString(sorted));

        assertArrayEquals("fail: ", fix, sorted);
    }

    @Test
    public void iterativeMergeSortTest(){
        shuffleTestData();
        Integer[] fix = getTestArrayCopy();
        Integer[] sorted = getTestArrayCopy();


        Arrays.sort(fix);
        iterativeMergeSort(sorted);

        System.out.println(Arrays.toString(fix));
        System.out.println(Arrays.toString(sorted));

        assertArrayEquals("fail: ", fix, sorted);
    }

    @Test
    public void recurrentQuickSortTest(){
        shuffleTestData();
        Integer[] fix = getTestArrayCopy();
        Integer[] sorted = getTestArrayCopy();


        Arrays.sort(fix);
        recursiveQuickSort(sorted);

        System.out.println(Arrays.toString(fix));
        System.out.println(Arrays.toString(sorted));

        assertArrayEquals("fail: ", fix, sorted);
    }

    @Test
    public void iterativeQuickSortTest(){
        shuffleTestData();
        Integer[] fix = getTestArrayCopy();
        Integer[] sorted = getTestArrayCopy();


        Arrays.sort(fix);
        iterativeQuickSort(sorted);

        System.out.println(Arrays.toString(fix));
        System.out.println(Arrays.toString(sorted));

        assertArrayEquals("fail: ", fix, sorted);
    }

    @Test
    public void iterativeShellSortTest(){
        shuffleTestData();
        Integer[] fix = getTestArrayCopy();
        Integer[] sorted = getTestArrayCopy();


        Arrays.sort(fix);
        iterativeShellSort(sorted);

        System.out.println(Arrays.toString(fix));
        System.out.println(Arrays.toString(sorted));

        assertArrayEquals("fail: ", fix, sorted);
    }
}
