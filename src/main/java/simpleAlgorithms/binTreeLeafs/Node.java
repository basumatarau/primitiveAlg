package simpleAlgorithms.binTreeLeafs;

class Node <T extends Comparable<T>>{
    private Node<T> left = null;
    private Node<T> right = null;
    private T data;

    Node(T data){
        this.data = data;
    }

    Node<T> getLeft() {
        return left;
    }

    void setLeft(Node<T> left) {
        this.left = left;
    }

    Node<T> getRight() {
        return right;
    }

    void setRight(Node<T> right) {
        this.right = right;
    }

    T getData() {
        return data;
    }
}
