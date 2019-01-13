package simpleAlgorithms;

import org.junit.Test;
import simpleAlgorithms.binTreeLeafs.BinTree;
import simpleAlgorithms.binTreeLeafs.SomeDataHolder;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringWriter;
import java.util.Random;

import static junit.framework.TestCase.assertTrue;
import static simpleAlgorithms.binTreeLeafs.BinTree.balancedLeafTree;

public class BinTreeLeafsTest {
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

    public BinTreeLeafsTest(){
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
    public void initialTest(){
        BinTree<SomeDataHolder> binTree = new BinTree<>();
        for (int i = 0; i < SIZE; i++) {
            binTree.insert(new SomeDataHolder(random.nextInt(100)));
        }

        binTree.displayTree2();
    }

    @Test
    public void leafTreeTest(){
        String testStr = "QWERASDFZOP";
        BinTree<String> leafTree = balancedLeafTree(testStr.split(""), "+");
        leafTree.displayTree();
        leafTree.displayTree2();
    }

}