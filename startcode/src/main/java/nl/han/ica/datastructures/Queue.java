package nl.han.ica.datastructures;

public class Queue<T> implements IHANQueue<T>{

    private final IHANLinkedList<T> list;
    private final int INDEX_COUNT_ZERO = 0;

    public Queue() {
        list = new LinkedList<>();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean isEmpty() {
        return list.getSize() == INDEX_COUNT_ZERO;
    }

    @Override
    public void enqueue(T val) {
        list.insert(list.getSize(), val);
    }

    @Override
    public T dequeue() {
        T tmp = list.getFirst();
        list.removeFirst();

        return tmp;
    }

    @Override
    public T peek() {
        return list.getFirst();
    }

    @Override
    public int getSize() {
        return list.getSize();
    }
}