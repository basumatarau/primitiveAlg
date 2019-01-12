package simpleAlgorithms.BinTree;

import java.util.Objects;

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

                        successor.setLeft(current.getLeft());
                        if(!Objects.equals(successor, current.getRight())) {
                            successor.setRight(current.getRight());
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
                        Node<T> successor = getSuccessor(current);

                        successor.setLeft(current.getLeft());
                        if(!Objects.equals(successor, current.getRight())) {
                            successor.setRight(current.getRight());
                        }

                        root = successor;
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

        if(!Objects.equals(node, parent)) {
            parent.setLeft(current.getRight());
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
}
