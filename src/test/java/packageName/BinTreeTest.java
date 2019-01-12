package packageName;

import org.junit.Test;
import static org.junit.Assert.*;

import packageName.BinTree.BinTree;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BinTreeTest {
    private static Random random = new Random(47);
    private static final int SIZE = 20;
    private static final int BOUND = 100;

    private PrintStream newOut;
    private PrintStream oldOut = System.out;
    private StringWriter strOut = new StringWriter();

    {
        newOut = new PrintStream(new OutputStream() {
            int pos = 0;
            byte[] bytes = new byte[1];

            @Override
            public void write(int b) throws IOException {
                if (pos == 0){
                    if((b & 0b11110000) == 0b11110000){
                        bytes = new byte[4];
                    }else if((b & 0b11100000) == 0b11100000){
                        bytes = new byte[3];
                    }else if((b & 0b11000000) == 0b11000000){
                        bytes = new byte[2];
                    }else{
                        bytes = new byte[1];
                    }
                }

                bytes[pos++] = (byte) b;

                if (pos == bytes.length){
                    String character = new String(bytes);
                    strOut.append(character);
                    oldOut.append(character);
                    pos = 0;
                }
            }
        });

    }

    public BinTreeTest(){
        System.setOut(newOut);
    }

    private static BinTreeTest run(){
        return new BinTreeTest();
    }

    private void include(String str){
        assertTrue(str + " is not present in the std output", strOut.toString().contains(str));
    }

    private void exclude(String str){
        assertTrue(str + " is present in the std output", !strOut.toString().contains(str));
    }

    @Test
    public void elementInsertionTest(){
        BinTree<Integer> binTree = new BinTree<>();
        List<Integer> list = new ArrayList<>(SIZE);

        for (int i = 0; i < SIZE; i++) {
            int randInt = random.nextInt(BOUND);
            binTree.insert(randInt);
            list.add(randInt);
        }

        BinTreeTest testCase = run();

        binTree.displayTree();

        for (Integer integer : list) {
            testCase.include(integer.toString());
        }
    }

    @Test
    public void initialTest(){
        BinTree<Integer> binTree = new BinTree<>();
        binTree.insert(1234);
        binTree.insert(1233);
        binTree.insert(1235);
        binTree.insert(1232);
        binTree.insert(1236);
        binTree.insert(1230);
        binTree.insert(1231);
        binTree.insert(1227);

        binTree.displayTree();

        binTree.delete(1230);
        //binTree.delete(1227);

        binTree.displayTree();
    }

    @Test
    public void elementDeletionTest(){
        BinTree<Integer> binTree = new BinTree<>();
        List<Integer> list = new ArrayList<>(SIZE);

        for (int i = 0; i < SIZE; i++) {
            int randInt = random.nextInt(BOUND);
            binTree.insert(randInt);
            list.add(randInt);
        }

        for (Integer integer : list) {
            binTree.delete(integer);
        }

        binTree.displayTree();

        for (Integer integer : list) {
            run().exclude(integer.toString());
        }

    }
}
