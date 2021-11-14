package concurrency;

public class UnsafeSimpleList<T> implements SimpleList<T> {

    private Object[] elements;
    private int size;

    public UnsafeSimpleList() {
        this.elements = new Object[10000];
    }

    @Override
    public void add(T item) {
        this.elements[getSize()] = item;
        this.size++;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public T get(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Index can't be negative");
        }
        if (index > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        return (T) this.elements[index];
    }

    @Override
    public void remove(int index) {
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
