package concurrency;

public interface SimpleList<T> {
    void add(T item);

    int getSize();

    T get(int index);

    void remove(int index);
}
