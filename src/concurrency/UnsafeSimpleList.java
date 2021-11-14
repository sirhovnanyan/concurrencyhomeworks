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
        if(index<0 || index>=getSize()){
            throw new IllegalArgumentException("Invalid index value");
        }
        for(int i=index;i<getSize();i++){
            elements[i]=elements[i+1];
        }
        this.size--;
    }

    public void printList() {
        System.out.print(getClass() + " = [");
        for (int i = 0; i < getSize(); i++) {
            System.out.print(elements[i] + ", ");
        }
        System.out.println("]");
    }
}
