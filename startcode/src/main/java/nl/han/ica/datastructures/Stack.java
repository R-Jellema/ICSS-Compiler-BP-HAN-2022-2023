package nl.han.ica.datastructures;

public class Stack<T> implements IHANStack<T> {

    private final int INDEX_COUNT_ZERO = 0;
    private final IHANLinkedList<T> linkedList;

    public Stack() {
        linkedList = new LinkedList<>();
    }

    @Override
    public void push(T val) {
        linkedList.addFirst(val);
    }

    @Override
    public T pop() {
        T popped = linkedList.getFirst();
        linkedList.removeFirst();
        return popped;
    }

    @Override
    public T peek() {
        return linkedList.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.getSize() == INDEX_COUNT_ZERO;
    }
}