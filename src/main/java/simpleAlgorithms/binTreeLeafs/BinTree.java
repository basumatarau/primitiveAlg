package simpleAlgorithms.binTreeLeafs;

import java.util.*;

public class BinTree<T extends Comparable<T>> {
    private Node<T> root;

    public void insert(T data) {
        if (root == null) {
            root = new Node<>(data);
        } else {
            Node<T> currentNode = root;
            while (true) {
                if (currentNode.getData().compareTo(data) > 0) {
                    if (currentNode.getLeft() == null) {
                        currentNode.setLeft(new Node<>(data));
                        return;
                    } else {
                        currentNode = currentNode.getLeft();
                    }
                } else {
                    if (currentNode.getRight() == null) {
                        currentNode.setRight(new Node<>(data));
                        return;
                    } else {
                        currentNode = currentNode.getRight();
                    }
                }
            }
        }
    }

    public Node find(T data) {
        Node<T> current = root;

        while (current != null) {
            if (current.getData().compareTo(data) < 0) {
                current = current.getLeft();
            } else if (current.getData().compareTo(data) == 0) {
                return current;
            } else {
                current = current.getRight();
            }
        }
        return null;
    }

    public void delete(T data) {
        Node<T> current = root;
        Node<T> parent = null;
        while (current != null) {
            if (data.compareTo(current.getData()) < 0) {
                parent = current;
                current = current.getLeft();
            } else if (current.getData().compareTo(data) == 0) {
                if (parent != null) {
                    if (current.getLeft() != null && current.getRight() != null) {

                        Node<T> successor = getSuccessor(current);
                        if (Objects.equals(parent.getLeft(), current)) {
                            parent.setLeft(successor);
                        } else {
                            parent.setRight(successor);
                        }

                    } else if (current.getLeft() == null && current.getRight() == null) {
                        if (Objects.equals(parent.getLeft(), current)) {
                            parent.setLeft(null);
                        } else {
                            parent.setRight(null);
                        }
                    } else {
                        if (current.getLeft() == null && current.getRight() != null) {
                            if (Objects.equals(parent.getLeft(), current)) {
                                parent.setLeft(current.getRight());
                            } else {
                                parent.setRight(current.getRight());
                            }
                        }
                        if (current.getLeft() != null && current.getRight() == null) {
                            if (Objects.equals(parent.getLeft(), current)) {
                                parent.setLeft(current.getLeft());
                            } else {
                                parent.setRight(current.getLeft());
                            }
                        }
                    }
                } else {
                    if (current.getLeft() != null && current.getRight() != null) {
                        root = getSuccessor(current);
                    } else if (current.getLeft() == null && current.getRight() == null) {
                        root = null;
                    } else {
                        if (current.getLeft() != null && current.getRight() == null) {
                            root = current.getLeft();
                        }
                        if (current.getLeft() == null && current.getRight() != null) {
                            root = current.getRight();
                        }
                    }
                }
                return;
            } else {
                parent = current;
                current = current.getRight();
            }
        }
    }

    private Node<T> getSuccessor(Node<T> node) {
        Node<T> parent = node;
        Node<T> current = node.getRight();

        while (current.getLeft() != null) {
            parent = current;
            current = current.getLeft();
        }

        current.setLeft(node.getLeft());
        if(!Objects.equals(node, parent)) {
            parent.setLeft(current.getRight());
            current.setRight(node.getRight());
        }else{
            parent.setRight(current.getRight());
        }

        return current;
    }

    public void displayTree() {
        walkAroundInOrder(root, "");
    }

    private void walkAroundInOrder(Node<T> node, String offset) {
        if (node != null) {
            System.out.println(offset + node.getData());

            offset += '\t';
            walkAroundInOrder(node.getLeft(), offset);
            walkAroundInOrder(node.getRight(), offset);

        }
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

    public static <T extends Comparable<T>> BinTree<T> balancedLeafTree(T[] array, T junction){
        Deque<BinTree<T>> forest = new LinkedList<>();

        for (int i = 0; i < array.length; i++) {
            BinTree<T> leaf = new BinTree<>();
            leaf.insert(array[i]);
            forest.add(leaf);
        }

        while(forest.size() > 1){
            BinTree<T> subTreeOne = forest.removeLast();
            BinTree<T> subTreeTwo = forest.removeLast();
            BinTree<T> tree = new BinTree<>();

            tree.insert(junction);
            tree.root.setLeft(subTreeOne.root);
            tree.root.setRight(subTreeTwo.root);

            forest.push(tree);
        }

        return forest.remove();
    }

    public static <T extends Comparable<T>> BinTree<T> unbalancedLeafTree(T[] array, T junction){
        Deque<BinTree<T>> forest = new LinkedList<>();

        for (int i = 0; i < array.length; i++) {
            BinTree<T> leaf = new BinTree<>();
            leaf.insert(array[i]);
            forest.add(leaf);
        }

        while(forest.size() > 1){
            BinTree<T> subTreeOne = forest.remove();
            BinTree<T> subTreeTwo = forest.remove();
            BinTree<T> tree = new BinTree<>();

            tree.insert(junction);
            tree.root.setLeft(subTreeOne.root);
            tree.root.setRight(subTreeTwo.root);

            forest.push(tree);
        }

        return forest.remove();
    }

    private void insert(BinTree<T> subTree) {
        Node<T> current = root;
        if(current == null){
            root = subTree.root;
        }else{
            while(true){
                if(current.getData().compareTo(subTree.root.getData()) > 0){
                    if(current.getLeft() == null){
                        current.setLeft(subTree.root);
                        return;
                    }else {
                        current = current.getLeft();
                    }
                }else {
                    if (current.getRight() == null){
                        current.setRight(subTree.root);
                        return;
                    }else{
                        current = current.getRight();
                    }
                }
            }
        }
    }
}
