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

    public void addVariable(K key, V val) {
        this.listOfScopes.getFirst().put(key, val);
    }

    public V getVariableValue(K key) {
        AtomicReference<V> result = new AtomicReference<>();
        listOfScopes.forEach(scope -> {
            V val = scope.get(key);
            if (val != null) {
                result.set(val);
            }
        });
        return result.get();
    }

}
