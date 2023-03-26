package nl.han.ica.datastructures;

public class Node<T> {

    private T val;
    private Node<T> next;

    public Node(T val) {
        this.val = val;
    }

    public void setNext(Node<T> next){
        this.next = next;
    }

    public Node<T> getNext() {
        return this.next;
    }

    public T getVal(){
        return this.val;
    }

}
