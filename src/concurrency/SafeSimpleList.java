package concurrency;

public class SafeSimpleList<T> implements SimpleList<T> {
    private Object[] elements;
    private int size;

    public SafeSimpleList() {
        this.elements = new Object[10000];
    }

    @Override
    public synchronized void add(T item) {
        this.elements[getSize()] = item;
        this.size++;
    }

    @Override
    public synchronized int getSize() {
        return size;
    }

    @Override
    public synchronized T get(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Index can't be negative");
        }
        if (index > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        return (T) this.elements[index];
    }

    @Override
    public synchronized void remove(int index) {
        int newIndex = 0;
        Object[] newElement = new Object[getSize() - 1];

        for (int i = 0; i < getSize(); i++) {
            if (i == index) {
                continue;
            }

            newElement[newIndex] = this.elements[i];
            newIndex++;
        }
        this.elements = newElement;
    }

    public void printList() {
        System.out.print(getClass() + " = [");
        for (int i = 0; i < getSize(); i++) {
            System.out.print(elements[i] + ", ");
        }
        System.out.println("]");
    }
}
