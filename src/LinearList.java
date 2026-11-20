/** Шугаман жагсаалтын интерфейс (сурах бичиг) */
public interface LinearList {
    boolean isEmpty();
    int size();
    Object get(int index);
    int indexOf(Object theElement);
    Object remove(int index);
    void add(int index, Object theElement);
    String toString();
}
