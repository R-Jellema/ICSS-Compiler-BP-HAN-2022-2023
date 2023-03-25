package nl.han.ica.datastructures;

import java.util.Iterator;

public class LinkedList<T> implements IHANLinkedList<T> {

    private final int INDEX_COUNT_ZERO = 0;
    private final int INDEX_COUNT_ONE = 1;
    private Node<T> head = null;

    @Override
    public void addFirst(T value) {
        if (head == null) {
            head = new Node<>(value);
        } else {
            Node<T> nodeToAdd = new Node<>(value);
            nodeToAdd.setNext(head);
            head = nodeToAdd;
        }
    }

    @Override
    public void clear() {
        head = null;
    }

    @Override
    public void insert(int index, T value) {
        Node<T> nodeToAdd = new Node<>(value);
        Node<T> current = head;

        head = head == null ? nodeToAdd : head;

        if (index == INDEX_COUNT_ZERO) {
            nodeToAdd.setNext(current);
            head = nodeToAdd;
            return;
        }

        if (index <= getSize()) {
            var count = INDEX_COUNT_ZERO;
            while (count != index - INDEX_COUNT_ONE) { current = current.getNext(); count++; }
            nodeToAdd.setNext(current.getNext()); current.setNext(nodeToAdd);
        }
    }

    @Override
    public void delete(int pos) {
        if (pos == INDEX_COUNT_ZERO) { removeFirst(); return; }
        if (pos > getSize()) { return; }

        Node<T> current = head;
        var count = INDEX_COUNT_ZERO;

        while (count != pos - INDEX_COUNT_ONE) { current = current.getNext(); count++; }

        current.setNext(current.getNext().getNext());
    }

    @Override
    public T get(int pos) {
        Iterator<T> iterator = iterator();
        for (var i = INDEX_COUNT_ZERO; i < pos; i++) { iterator.next(); }
        return iterator.next();
    }

    @Override
    public void removeFirst() { head = head.getNext(); }

    @Override
    public T getFirst() { return head.getValue(); }

    @Override
    public int getSize() {
        var size = INDEX_COUNT_ZERO;
        Iterator<T> iterator = iterator();
        while(iterator.hasNext()) { size++; iterator.next(); }
        return size;
    }

    @Override
    public Iterator<T> iterator() { return new LinkedListIterator<>(head); }
}