package nl.han.ica.datastructures;

public class Queue<T> implements IHANQueue<T>{

    private final IHANLinkedList<T> list;

    public Queue() {
        list = new LinkedList<>();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean isEmpty() {
        return list.getSize() == 0;
    }

    @Override
    public void enqueue(T value) {
        list.insert(list.getSize(), value); // 2 loops are being executed here.
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