/** Лаборатори №2: Chain (холбоост шугаман жагсаалт)-ийг өргөтгөсөн класс */
public class MyChain implements LinearList {

    static class ChainNode {
        Object element;
        ChainNode next;
        ChainNode(Object e, ChainNode n) { element = e; next = n; }
    }

    protected ChainNode firstNode;
    protected int size;

    public MyChain() {}

    public boolean isEmpty() { return size == 0; }
    public int size() { return size; }

    void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index = " + index + "  size = " + size);
    }

    public Object get(int index) {
        checkIndex(index);
        ChainNode p = firstNode;
        for (int i = 0; i < index; i++) p = p.next;
        return p.element;
    }

    public int indexOf(Object theElement) {
        ChainNode p = firstNode;
        int i = 0;
        while (p != null && !p.element.equals(theElement)) { p = p.next; i++; }
        return p == null ? -1 : i;
    }

    public Object remove(int index) {
        checkIndex(index);
        Object removed;
        if (index == 0) { removed = firstNode.element; firstNode = firstNode.next; }
        else {
            ChainNode q = firstNode;
            for (int i = 0; i < index - 1; i++) q = q.next;
            removed = q.next.element;
            q.next = q.next.next;
        }
        size--;
        return removed;
    }

    public void add(int index, Object theElement) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("index = " + index + "  size = " + size);
        if (index == 0) firstNode = new ChainNode(theElement, firstNode);
        else {
            ChainNode p = firstNode;
            for (int i = 0; i < index - 1; i++) p = p.next;
            p.next = new ChainNode(theElement, p.next);
        }
        size++;
    }

    public String toString() {
        StringBuilder s = new StringBuilder("[");
        for (ChainNode p = firstNode; p != null; p = p.next) {
            s.append(p.element);
            if (p.next != null) s.append(", ");
        }
        return s.append("]").toString();
    }

    // ------------- Өргөтгөсөн аргууд -------------

    /** Жагсаалтыг массив руу хөрвүүлнэ */
    public Object[] toArray() {
        Object[] a = new Object[size];
        int i = 0;
        for (ChainNode p = firstNode; p != null; p = p.next) a[i++] = p.element;
        return a;
    }

    /** Өгөгдсөн элементүүдийг жагсаалтын ард нэмнэ */
    public void addRange(Object[] elements) {
        if (elements == null) throw new IllegalArgumentException("elements null байна");
        ChainNode last = firstNode;
        while (last != null && last.next != null) last = last.next;
        for (Object e : elements) {
            ChainNode n = new ChainNode(e, null);
            if (last == null) firstNode = n; else last.next = n;
            last = n;
            size++;
        }
    }

    /** Нэгдэл (давхардалгүй). Үндсэн жагсаалт өөрчлөгдөхгүй */
    public MyChain union(MyChain chain) {
        if (chain == null) throw new IllegalArgumentException("chain null байна");
        MyChain r = new MyChain();
        for (ChainNode p = firstNode; p != null; p = p.next)
            if (r.indexOf(p.element) < 0) r.addRange(new Object[]{p.element});
        for (ChainNode p = chain.firstNode; p != null; p = p.next)
            if (r.indexOf(p.element) < 0) r.addRange(new Object[]{p.element});
        return r;
    }

    /** Огтлолцол (давхардалгүй). Үндсэн жагсаалт өөрчлөгдөхгүй */
    public MyChain intersection(MyChain chain) {
        if (chain == null) throw new IllegalArgumentException("chain null байна");
        MyChain r = new MyChain();
        for (ChainNode p = firstNode; p != null; p = p.next)
            if (chain.indexOf(p.element) >= 0 && r.indexOf(p.element) < 0)
                r.addRange(new Object[]{p.element});
        return r;
    }

    // ---------------- Интерактив цэс ----------------
    private static MyChain readChain(String msg) {
        int[] a = Input.readIntArray(msg);
        MyChain c = new MyChain();
        Object[] o = new Object[a.length];
        for (int i = 0; i < a.length; i++) o[i] = a[i];
        c.addRange(o);
        return c;
    }

    public static void menu() {
        MyChain list = new MyChain();
        while (true) {
            System.out.println("\n--- MyChain ---");
            System.out.println("Одоогийн жагсаалт: " + list + "  (size=" + list.size() + ")");
            System.out.println("1. Элемент нэмэх");
            System.out.println("2. Элемент устгах");
            System.out.println("3. Жагсаалтыг массив руу хөрвүүлэх - toArray");
            System.out.println("4. Жагсаалтын ард нэмэх - addRange");
            System.out.println("5. Нэгдэл жагсаалтыг буцаах - union");
            System.out.println("6. Огтлолцол жагсаалтыг буцаах - intersection");
            System.out.println("7. Жагсаалтыг хэвлэх");
            System.out.println("0. Буцах");
            int c = Input.readInt("Сонголт хийнэ үү: ");
            try {
                switch (c) {
                    case 1: {
                        int v = Input.readInt("Утга: ");
                        int idx = Input.readInt("Байрлал (0.." + list.size() + "): ");
                        list.add(idx, v);
                        System.out.println("Нэмэгдлээ.");
                        break;
                    }
                    case 2: {
                        if (list.isEmpty()) { System.out.println("Жагсаалт хоосон байна!"); break; }
                        System.out.println("Устгагдсан: " + list.remove(Input.readInt("Индекс (0.." + (list.size() - 1) + "): ")));
                        break;
                    }
                    case 3: {
                        Object[] a = list.toArray();
                        System.out.println("Массив: " + java.util.Arrays.toString(a) + "  (урт=" + a.length + ")");
                        break;
                    }
                    case 4: {
                        int[] a = Input.readIntArray("Нэмэх утгууд (зайгаар): ");
                        Object[] o = new Object[a.length];
                        for (int i = 0; i < a.length; i++) o[i] = a[i];
                        list.addRange(o);
                        System.out.println("Нэмэгдлээ: " + list);
                        break;
                    }
                    case 5: {
                        MyChain other = readChain("2 дахь жагсаалт (зайгаар): ");
                        System.out.println("Нэгдэл: " + list.union(other));
                        System.out.println("Үндсэн жагсаалт (өөрчлөгдөөгүй): " + list);
                        break;
                    }
                    case 6: {
                        MyChain other = readChain("2 дахь жагсаалт (зайгаар): ");
                        System.out.println("Огтлолцол: " + list.intersection(other));
                        System.out.println("Үндсэн жагсаалт (өөрчлөгдөөгүй): " + list);
                        break;
                    }
                    case 7: System.out.println("Жагсаалт: " + list); break;
                    case 0: return;
                    default: System.out.println("Буруу сонголт!");
                }
            } catch (RuntimeException e) {
                System.out.println("Алдаа: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) { menu(); }
}
