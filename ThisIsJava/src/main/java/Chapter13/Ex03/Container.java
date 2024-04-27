package Chapter13.Ex03;

public class Container<T, K> {
    private T key;
    private K value;

    public void set(T key, K value) {
        this.key = key;
        this.value = value;
    }

    public T getKey() {
        return key;
    }

    public K getValue() {
        return value;
    }
}
