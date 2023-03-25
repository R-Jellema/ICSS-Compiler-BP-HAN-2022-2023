package nl.han.ica.datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListIterator <T> implements Iterator<T> {
    private Node<T> current;

    public LinkedListIterator(Node<T> first) {
        current = first;
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        var tempo = current;
        current = current.getNext();
        return tempo.getValue();
    }
}
