package simpleAlgorithms;

import org.junit.Test;
import simpleAlgorithms.TwoThreeFourTree.TwoThreeFourTree;
import simpleAlgorithms.TwoThreeTree.TwoThreeTree;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringWriter;
import java.util.Random;

import static junit.framework.TestCase.assertTrue;

public class TwoThreeTreeTest {

    private static Random random = new Random(47);
    private static final int SIZE = (int) Math.pow(2, 10);
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
                if (pos == 0) {
                    if ((b & 0b11110000) == 0b11110000) {
                        bytes = new byte[4];
                    } else if ((b & 0b11100000) == 0b11100000) {
                        bytes = new byte[3];
                    } else if ((b & 0b11000000) == 0b11000000) {
                        bytes = new byte[2];
                    } else {
                        bytes = new byte[1];
                    }
                }

                bytes[pos++] = (byte) b;

                if (pos == bytes.length) {
                    String character = new String(bytes);
                    strOut.append(character);
                    oldOut.append(character);
                    pos = 0;
                }
            }
        });

    }

    public TwoThreeTreeTest() {
        System.setOut(newOut);
    }

    private static TwoThreeTreeTest run() {
        return new TwoThreeTreeTest();
    }

    private void include(String str) {
        assertTrue(str + " is not present in the std output", strOut.toString().contains(str));
    }

    private void exclude(String str) {
        assertTrue(str + " is present in the std output", !strOut.toString().contains(str));
    }

    @Test
    public void insertionTest() {
        TwoThreeTree<String> tree = new TwoThreeTree<>();

        TwoThreeTreeTest testCase = run();

        //tree.displayTree();


        for (int i = 0; i < SIZE; i++) {
            tree.insert("A");
        }

        System.out.println(tree.treeDepth());
        assertTrue("234-Tree height is too high",
                tree.treeDepth() < 2 * (Math.log(SIZE + 1) / Math.log(2)));


        String[] testStrings = "qwertyuiopasdfghjklzxcvbnm".split("");
        for (int i = 0; i < SIZE; i++) {
            tree.insert(testStrings[random.nextInt(testStrings.length)]);
        }

        System.out.println(tree.treeDepth());
        assertTrue("234-Tree height is too high",
                tree.treeDepth() < 2 * (Math.log(SIZE + 1) / Math.log(2)));

    }

}
