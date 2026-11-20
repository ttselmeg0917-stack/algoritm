/** Массиваар хэрэгжүүлсэн шугаман жагсаалт (сурах бичиг) */
public class ArrayLinearList implements LinearList {
    protected Object[] element;
    protected int size;

    public ArrayLinearList(int initialCapacity) {
        if (initialCapacity < 1)
            throw new IllegalArgumentException("initialCapacity must be >= 1");
        element = new Object[initialCapacity];
    }
    public ArrayLinearList() { this(10); }

    public boolean isEmpty() { return size == 0; }
    public int size() { return size; }

    protected void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index = " + index + "  size = " + size);
    }

    public Object get(int index) { checkIndex(index); return element[index]; }

    public int indexOf(Object theElement) {
        for (int i = 0; i < size; i++)
            if (element[i].equals(theElement)) return i;
        return -1;
    }

    public Object remove(int index) {
        checkIndex(index);
        Object removed = element[index];
        for (int i = index + 1; i < size; i++) element[i - 1] = element[i];
        element[--size] = null;
        return removed;
    }

    public void add(int index, Object theElement) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("index = " + index + "  size = " + size);
        if (size == element.length) {
            Object[] n = new Object[2 * element.length];
            System.arraycopy(element, 0, n, 0, size);
            element = n;
        }
        for (int i = size - 1; i >= index; i--) element[i + 1] = element[i];
        element[index] = theElement;
        size++;
    }

    public String toString() {
        StringBuilder s = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            if (i > 0) s.append(", ");
            s.append(element[i]);
        }
        return s.append("]").toString();
    }
}
