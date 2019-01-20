package simpleAlgorithms.TwoThreeTree;

import java.util.Arrays;

public class TwoThreeTree<T extends Comparable<T>> {
    private Node<T> root;

    public void insert(T data) {
        if (root == null) {
            root = new Node<>(null, data);
            return;
        }
        Node<T> current = root;
        while (!current.isLeaf()) {
            current = getNextChild(current, data);
        }

        Node<T> leftNode = null;
        Node<T> rightNode = null;

        while (current != null) {

            if (current.isFull()) {
                if (data.compareTo(current.getItem(Node.ORDER - 2)) < 0) {
                    if (leftNode == null) {
                        leftNode = new Node<>(null, data);
                        rightNode = new Node<>(null, current.removeItem());
                    } else {
                        Node<T> tmpLeftNode = new Node<>(null, data);
                        tmpLeftNode.connectChild(0, leftNode);
                        tmpLeftNode.connectChild(1, rightNode);
                        leftNode.setParent(tmpLeftNode);
                        rightNode.setParent(tmpLeftNode);

                        Node<T> tmpRightNode = new Node<>(null, current.removeItem());
                        tmpRightNode.connectChild(0, current.getChild(1));
                        tmpRightNode.connectChild(1, current.getChild(2));
                        tmpRightNode.getChild(0).setParent(tmpRightNode);
                        tmpRightNode.getChild(1).setParent(tmpRightNode);

                        leftNode = tmpLeftNode;
                        rightNode = tmpRightNode;
                    }
                    data = current.removeItem();

                } else if (data.compareTo(current.getItem(Node.ORDER - 1)) < 0) {
                    if (leftNode == null) {
                        rightNode = new Node<>(null, current.removeItem());
                        leftNode = new Node<>(null, current.removeItem());
                    } else {
                        Node<T> tmpRightNode = new Node<>(null, current.removeItem());
                        tmpRightNode.connectChild(0, rightNode);
                        tmpRightNode.connectChild(1, current.getChild(2));
                        rightNode.setParent(tmpRightNode);
                        tmpRightNode.getChild(1).setParent(tmpRightNode);
                        rightNode = tmpRightNode;

                        Node<T> tmpLeftNode = new Node<>(null, current.removeItem());
                        tmpLeftNode.connectChild(0, current.getChild(0));
                        tmpLeftNode.connectChild(1, leftNode);
                        leftNode.setParent(tmpLeftNode);
                        tmpLeftNode.getChild(0).setParent(tmpLeftNode);
                        leftNode = tmpLeftNode;
                    }
                } else {
                    T tmpData = current.removeItem();
                    if (leftNode == null) {
                        leftNode = new Node<>(null, current.removeItem());
                        rightNode = new Node<>(null, data);
                    } else {
                        Node<T> tmpLeftNode = new Node<>(null, current.removeItem());
                        tmpLeftNode.connectChild(0, current.getChild(0));
                        tmpLeftNode.connectChild(1, current.getChild(1));
                        tmpLeftNode.getChild(0).setParent(tmpLeftNode);
                        tmpLeftNode.getChild(1).setParent(tmpLeftNode);

                        Node<T> tmpRightNode = new Node<>(null, data);
                        tmpRightNode.connectChild(0, leftNode);
                        tmpRightNode.connectChild(1, rightNode);
                        rightNode.setParent(tmpRightNode);
                        leftNode.setParent(tmpRightNode);

                        leftNode = tmpLeftNode;
                        rightNode = tmpRightNode;
                    }
                    data = tmpData;
                }

            } else {
                if (leftNode != null) {
                    if (data.compareTo(current.getItem(Node.ORDER - 2)) < 0) {
                        Node<T> hangingChild = current.disconnectChild(Node.ORDER - 1);
                        current.disconnectChild(Node.ORDER - 2);
                        current.connectChild(0, leftNode);
                        current.connectChild(1, rightNode);
                        current.connectChild(2, hangingChild);
                        T tmp = current.removeItem();
                        current.insertItem(data);
                        current.insertItem(tmp);

                        leftNode.setParent(current);
                        rightNode.setParent(current);
                    } else {
                        current.disconnectChild(Node.ORDER - 1);
                        current.connectChild(Node.ORDER - 1, leftNode);
                        current.connectChild(Node.ORDER, rightNode);
                        current.insertItem(data);

                        leftNode.setParent(current);
                        rightNode.setParent(current);
                    }
                } else {
                    if (data.compareTo(current.getItem(Node.ORDER - 2)) < 0) {
                        T tmp = current.removeItem();
                        current.insertItem(data);
                        current.insertItem(tmp);
                    } else {
                        current.insertItem(data);
                    }
                }
                return;
            }

            current = current.getParent();
        }

        root = new Node<>(null, data);
        root.connectChild(0, leftNode);
        root.connectChild(1, rightNode);
        leftNode.setParent(root);
        rightNode.setParent(root);
    }

    private Node<T> getNextChild(Node<T> currentNode, T key) {

        for (int childIndex = 0; childIndex < currentNode.getItemsNum(); childIndex++) {
            if (key.compareTo(currentNode.getItem(childIndex)) < 0) {
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

    class Node<TYPE extends Comparable<TYPE>> {
        private static final int ORDER = 2;

        private int itemsNum = 0;
        @SuppressWarnings("unchecked")
        private TYPE[] items = (TYPE[]) new Comparable[ORDER];
        @SuppressWarnings("unchecked")
        private Node<TYPE>[] children = new Node[ORDER + 1];
        private Node<TYPE> parent;

        public Node(Node<TYPE> parent, TYPE data) {
            items[0] = data;
            itemsNum++;

            this.parent = parent;
        }

        public Node<TYPE> getParent() {
            return parent;
        }

        public void setParent(Node<TYPE> parent) {
            this.parent = parent;
        }

        public int getItemsNum() {
            return itemsNum;
        }

        public TYPE getItem(int index) {
            return items[index];
        }

        public boolean isFull() {
            return itemsNum == ORDER;
        }

        public boolean isLeaf() {
            return children[0] == null;
        }

        public Node<TYPE> getChild(int index) {
            return children[index];
        }

        public void connectChild(int childIndex, Node<TYPE> child) {
            children[childIndex] = child;
        }

        public Node<TYPE> disconnectChild(int childIndex) {
            Node<TYPE> result = null;
            if (children[childIndex] != null) {
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
            while (pos < items.length) {
                if (items[pos] == null || item.compareTo(items[pos]) < 0) {
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

        public TYPE removeItem() {
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
}
