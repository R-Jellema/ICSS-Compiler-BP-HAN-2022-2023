package nl.han.ica.datastructures;

public class Node<T> {

    private T value;
    private Node<T> next;

    public Node(T value) {
        this.value = value;
    }

    public void setNext(Node<T> next){
        this.next = next;
    }

    public Node<T> getNext() {
        return this.next;
    }

    public T getValue(){
        return this.value;
    }

}
