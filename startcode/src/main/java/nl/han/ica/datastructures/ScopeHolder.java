package nl.han.ica.datastructures;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public class ScopeHolder<K,V> {

    private final IHANLinkedList<HashMap<K, V>> listOfScopes;

    public ScopeHolder() {
        this.listOfScopes = new LinkedList<>();
    }

    public void openScope() {
        this.listOfScopes.addFirst(new HashMap<>());
    }

    public void closeScope() {
        this.listOfScopes.removeFirst();
    }

    public void addVariable(K key, V value) {
        this.listOfScopes.getFirst().put(key, value);
    }

    public V getVariable(K key) {
        AtomicReference<V> result = new AtomicReference<>();
        listOfScopes.forEach(scope -> {
            V value = scope.get(key);
            if (value != null) {
                result.set(value);
            }
        });
        return result.get();
    }

}
