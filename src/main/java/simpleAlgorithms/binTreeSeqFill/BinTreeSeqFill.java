package simpleAlgorithms.binTreeSeqFill;

import java.util.ArrayList;
import java.util.LinkedList;

public class BinTreeSeqFill<T extends Comparable<T>> {
    private Node<T> root = null;

    public void seqFill(T[] array){
        if (array == null) {
            return;
        }

        ArrayList<Node<T>> nodes = new ArrayList<>();
        nodes.add(new Node<>(array[0]));

        for (int i = 0; i < array.length; i++) {
            Node<T> head = nodes.get(i);
            if((i + 1) * 2 <= array.length){
                Node<T> leftNode = new Node<>(array[(i+1) * 2 - 1]);
                head.setLeft(leftNode);
                nodes.add(leftNode);
            }
            if((i + 1) * 2 + 1 <= array.length){
                Node<T> rightNode = new Node<>(array[(i+1) * 2]);
                head.setRight(rightNode);
                nodes.add(rightNode);
            }
        }
        root = nodes.get(0);
    }

    public void displayTree2() {
        int depth = treeDepth(root);
        int width = (int) Math.pow(2, depth);

        LinkedList<Node<T>> currentLevelNodes = new LinkedList<>();
        currentLevelNodes.push(root);
        boolean isNextLineEmpty = false;

        while (!isNextLineEmpty){
            isNextLineEmpty = true;
            LinkedList<Node<T>> children = new LinkedList<>();
            for (int i = 0; i < width/2; i++) {
                System.out.print("-");
            }

            while(!currentLevelNodes.isEmpty()){
                Node<T> tmp = currentLevelNodes.pop();
                if (tmp != null){
                    System.out.print(tmp.getData());
                    children.push(tmp.getLeft());
                    children.push(tmp.getRight());
                    if (tmp.getLeft() != null || tmp.getRight() != null){
                        isNextLineEmpty = false;
                    }
                }else{
                    System.out.print("*");
                    children.push(null);
                    children.push(null);
                }
                for (int i = 0; i < width - 1; i++) {
                    System.out.print("-");
                }
            }
            System.out.println();
            width /= 2;
            while (!children.isEmpty()){
                currentLevelNodes.push(children.pop());
            }
        }
    }

    private int treeDepth(Node<T> root){
        if(root == null){
            return 0;
        } else{
            int leftDepth = treeDepth(root.getLeft());
            int rightDepth = treeDepth(root.getRight());
            return leftDepth > rightDepth ? ++leftDepth : ++rightDepth;
        }
    }
}
