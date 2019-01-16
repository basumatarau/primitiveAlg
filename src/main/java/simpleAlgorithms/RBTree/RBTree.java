package simpleAlgorithms.RBTree;

import java.util.LinkedList;
import java.util.Objects;

public class RBTree<T extends Comparable<T>> {
    private Node<T> root;

    public void insert(T data) {

        if (root == null) {
            root = new Node<>(data);
            root.isRed = false;
            return;
        }

        Node<T> grandParent = root;
        Node<T> parent = root;
        Node<T> current = root;

        //todo something doesn't work...
        while (current != null) {
            if (data.compareTo(current.getData()) < 0) {
                if (current.getLeft() != null) {
                    if (!current.isRed && current.getLeft().isRed && current.getRight() != null) {
                        flip(current);
                    } else if (current.isRed && current.getLeft().isRed) {
                        if (Objects.equals(current, parent.getLeft())) {
                            rotateRight(current, parent, grandParent);
                        } else {
                            rotateLeftRight(current, parent, grandParent);
                        }
                    }
                    grandParent = parent;
                    parent = current;
                    current = current.getLeft();
                } else {
                    current.setLeft(new Node<>(data));
                    if (current.isRed) {
                        if (Objects.equals(parent.getLeft(), current)) {
                            rotateRight(current, parent, grandParent);
                        } else {
                            rotateLeftRight(current, parent, grandParent);
                        }
                    } else {
                        if (current.getRight() != null) {
                            flip(current);
                        }
                    }
                    return;
                }
            } else {
                if (current.getRight() != null) {
                    if (!current.isRed && current.getRight().isRed && current.getLeft() != null) {
                        flip(current);
                    } else if (current.isRed && current.getRight().isRed) {
                        if (Objects.equals(current, parent.getRight())) {
                            rotateLeft(current, parent, grandParent);
                        } else {
                            rotateRightLeft(current, parent, grandParent);
                        }
                    }
                    grandParent = parent;
                    parent = current;
                    current = current.getRight();
                } else {
                    current.setRight(new Node<>(data));
                    if (current.isRed) {
                        if (Objects.equals(parent.getRight(), current)) {
                            rotateLeft(current, parent, grandParent);
                        } else {
                            rotateRightLeft(current, parent, grandParent);
                        }
                    } else {
                        if (current.getLeft() != null) {
                            flip(current);
                        }
                    }
                    return;
                }
            }
        }
    }

    private void rotateLeft(Node<T> current, Node<T> parent, Node<T> grandParent) {
        parent.isRed = !parent.isRed;
        current.isRed = !current.isRed;

        if (parent == grandParent){
            root = current;
        }else if (Objects.equals(parent, grandParent.getLeft())) {
            grandParent.setLeft(current);
        } else {
            grandParent.setRight(current);
        }

        Node<T> tmp = current.getLeft();
        current.setLeft(parent);
        parent.setRight(tmp);
    }

    private void rotateRight(Node<T> current, Node<T> parent, Node<T> grandParent) {
        parent.isRed = !parent.isRed;
        current.isRed = !current.isRed;

        if (parent == grandParent){
            root = current;
        }else if (Objects.equals(parent, grandParent.getLeft())) {
            grandParent.setLeft(current);
        } else {
            grandParent.setRight(current);
        }

        Node<T> tmp = current.getRight();
        current.setRight(parent);
        parent.setLeft(tmp);
    }

    private void rotateRightLeft(Node<T> current, Node<T> parent, Node<T> grandParent) {
        current.getRight().isRed = !current.getRight().isRed;
        parent.isRed = !parent.isRed;

        parent.setLeft(current.getRight());
        parent.getLeft().setLeft(current);
        current.setRight(null);

        if (Objects.equals(parent, grandParent.getLeft())) {
            grandParent.setLeft(parent.getLeft());
        } else {
            grandParent.setRight(parent.getLeft());
        }

        parent.getLeft().setRight(parent);
        parent.setLeft(null);

    }

    private void rotateLeftRight(Node<T> current, Node<T> parent, Node<T> grandParent) {
        current.getRight().isRed = !current.getRight().isRed;
        parent.isRed = !parent.isRed;

        parent.setRight(current.getLeft());
        parent.getRight().setRight(current);
        current.setLeft(null);

        if (Objects.equals(parent, grandParent.getLeft())) {
            grandParent.setLeft(parent.getRight());
        } else {
            grandParent.setRight(parent.getRight());
        }

        parent.getRight().setLeft(parent);
        parent.setRight(null);
    }

    private void flip(Node<T> node) {
        if (node != root) {
            node.isRed = !node.isRed;
        }
        node.getRight().isRed = !node.getRight().isRed;
        node.getLeft().isRed = !node.getLeft().isRed;
    }

    public void displayTree() {
        int depth = treeDepth(root);
        int width = (int) Math.pow(2, depth);

        LinkedList<Node<T>> currentLevelNodes = new LinkedList<>();
        currentLevelNodes.push(root);
        boolean isNextLineEmpty = false;

        while (!isNextLineEmpty) {
            isNextLineEmpty = true;
            LinkedList<Node<T>> children = new LinkedList<>();
            for (int i = 0; i < width / 2; i++) {
                System.out.print("----");
            }

            while (!currentLevelNodes.isEmpty()) {
                Node<T> tmp = currentLevelNodes.pop();
                if (tmp != null) {
                    System.out.print(tmp);
                    children.push(tmp.getLeft());
                    children.push(tmp.getRight());
                    if (tmp.getLeft() != null || tmp.getRight() != null) {
                        isNextLineEmpty = false;
                    }
                } else {
                    System.out.print("****");
                    children.push(null);
                    children.push(null);
                }
                for (int i = 0; i < width - 1; i++) {
                    System.out.print("----");
                }
            }
            System.out.println();
            width /= 2;
            while (!children.isEmpty()) {
                currentLevelNodes.push(children.pop());
            }
        }
    }

    private int treeDepth(Node<T> root) {
        if (root == null) {
            return 0;
        } else {
            int leftDepth = treeDepth(root.getLeft());
            int rightDepth = treeDepth(root.getRight());
            return leftDepth > rightDepth ? ++leftDepth : ++rightDepth;
        }
    }
}
