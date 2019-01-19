package simpleAlgorithms.TwoThreeFourTree;

import java.util.Arrays;

public class TwoThreeFourTree <T extends Comparable<T>>{

    class Node<TYPE extends Comparable<TYPE>> {
        private static final int ORDER = 4;

        private int itemsNum = 0;
        @SuppressWarnings("unchecked")
        private TYPE[] items = (TYPE[]) new Comparable[ORDER - 1];
        @SuppressWarnings("unchecked")
        private Node<TYPE>[] children = new Node[ORDER];

        public Node(TYPE data){
            items[0] = data;
            itemsNum++;
        }

        public int getItemsNum() {
            return itemsNum;
        }

        public TYPE getItem(int index) {
            return items[index];
        }

        public boolean isFull() {
            return itemsNum == ORDER - 1;
        }

        public boolean isLeaf() {
            return children[0] == null;
        }

        public Node<TYPE> getChild(int index) {
            return children[index];
        }

        public void connectChild(int childIndex, Node<TYPE> child){
            children[childIndex] = child;
        }

        public Node<TYPE> disconnectChild(int childIndex){
            Node<TYPE> result = null;
            if(children[childIndex] != null){
                result = children[childIndex];
                children[childIndex] = null;
            }
            return result;
        }

        public int findItem(TYPE data) {
            int itemIndex = -1;
            for (int i = 0; i < items.length; i++) {
                if (items[i] == null) {
                    break;
                } else {
                    if (data.compareTo(items[i]) == 0) {
                        itemIndex = i;
                    }
                }
            }
            return itemIndex;
        }

        public void insertItem(TYPE item) {
            int pos = 0;
            while(pos < items.length){
                if (items[pos] == null || item.compareTo(items[pos]) < 0){
                    break;
                }
                pos++;
            }
            TYPE[] tmp = Arrays.copyOfRange(items, pos, items.length - 1);
            items[pos] = item;
            for (int i = 0; i < tmp.length; i++) {
                items[++pos] = tmp[i];
            }
            itemsNum++;
        }

        public TYPE removeItem(){
            TYPE result = items[--itemsNum];
            items[itemsNum] = null;
            return result;
        }

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder("");
            for (int i = 0; i < itemsNum; i++) {
                result.append(items[i]);
                result.append("/");
            }
            return result.toString();
        }
    }

    private Node<T> root;

    public void insertItem(T data){
        Node<T> current = root;
        Node<T> parent = root;
        if(current == null){
            root = new Node<>(data);
            return;
        }

        while(!current.isLeaf()){
            if(current.isFull()){
                if(current == root){
                    Node<T> newNode = new Node<>(current.removeItem());
                    Node<T> newRoot = new Node<>(current.removeItem());

                    Node<T> rightNode = current.disconnectChild(Node.ORDER - 1);
                    Node<T> leftNode = current.disconnectChild(Node.ORDER - 2);

                    newNode.connectChild(0, leftNode);
                    newNode.connectChild(1, rightNode);

                    newRoot.connectChild(0, current);
                    newRoot.connectChild(1, newNode);
                    root = newRoot;
                }else{
                    Node<T> newNode = new Node<>(current.removeItem());
                    parent.insertItem(current.removeItem());
                    parent.connectChild(parent.getItemsNum(), newNode);
                    Node<T> rightNode = current.disconnectChild(Node.ORDER - 1);
                    Node<T> leftNode = current.disconnectChild(Node.ORDER - 2);
                    newNode.connectChild(0, leftNode);
                    newNode.connectChild(1, rightNode);
                }
            }
            parent = current;
            current = getNextChild(current, data);
        }

        if(current.isFull()){
            if(current == root){
                Node<T> newNode = new Node<>(current.removeItem());
                Node<T> newRoot = new Node<>(current.removeItem());
                newRoot.connectChild(0, current);
                newRoot.connectChild(1, newNode);

                getNextChild(newRoot, data).insertItem(data);
                root = newRoot;
            }else{
                //todo fix this
                Node<T> rightNode = new Node<>(current.removeItem());
                parent.insertItem(current.removeItem());
                parent.connectChild(parent.itemsNum, rightNode);

                getNextChild(parent, data).insertItem(data);
            }
        }else{
            current.insertItem(data);
        }

    }

    public int findItem(T data){
        int childNumber;

        Node<T> current = root;
        if(current == null){
            return -1;
        }

        while (!current.isLeaf()){
            if((childNumber = current.findItem(data)) != -1){
                return childNumber;
            }else if (current.isLeaf()){
                return -1;
            }
            current = getNextChild(current, data);
        }
        return current.findItem(data);
    }

    private Node<T> getNextChild(Node<T> currentNode, T key) {

        for (int childIndex = 0; childIndex < currentNode.getItemsNum(); childIndex++) {
            if(key.compareTo(currentNode.getItem(childIndex)) < 0){
                return currentNode.getChild(childIndex);
            }
        }
        return currentNode.getChild(currentNode.getItemsNum());
    }

    public void displayTree(){
        treeWalkAround(root, 0, 0);
    }

    public void treeWalkAround(Node<T> node, int level, int childNum){
        System.out.println("level=" + (level == 0 ? "root" : level) + " child=" + childNum + " " + node);

        int numItems = node.getItemsNum();
        for (int i = 0; i < numItems + 1; i++) {
            Node<T> nextNode = node.getChild(i);
            if(nextNode != null){
                treeWalkAround(nextNode, level + 1, i);
            }else {
                return;
            }
        }
    }

    public int treeDepth(){
        return subTreeDepth(root);
    }

    private int subTreeDepth(Node<T> node) {
        if (!node.isLeaf()) {
            int maxDepth = 0;
            for (int i = 0; i <= node.getItemsNum(); i++) {
                int depth = subTreeDepth(node.getChild(i));
                if(depth > maxDepth){
                    maxDepth = depth;
                }
            }
            return ++maxDepth;
        }else {
            return 0;
        }
    }

}
