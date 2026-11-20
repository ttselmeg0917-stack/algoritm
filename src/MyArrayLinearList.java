/** Лаборатори №1: ArrayLinearList-ийг өргөтгөсөн класс (бүхэл тоон элементтэй) */
public class MyArrayLinearList extends ArrayLinearList {

    public MyArrayLinearList() { super(); }

    private int val(int i) { return (Integer) element[i]; }

    private void checkEmpty() {
        if (isEmpty()) throw new IllegalStateException("Жагсаалт хоосон байна!");
    }

    public int max() {
        checkEmpty();
        int m = val(0);
        for (int i = 1; i < size; i++) if (val(i) > m) m = val(i);
        return m;
    }

    public int min() {
        checkEmpty();
        int m = val(0);
        for (int i = 1; i < size; i++) if (val(i) < m) m = val(i);
        return m;
    }

    public long sum() {
        long s = 0;
        for (int i = 0; i < size; i++) s += val(i);
        return s;
    }

    public double average() {
        checkEmpty();
        return (double) sum() / size;
    }

    /** Сондгой утгатай элементүүдийг устгана, устгасан тоог буцаана */
    public int removeOdd() {
        int j = 0, removed = 0;
        for (int i = 0; i < size; i++) {
            if (val(i) % 2 == 0) element[j++] = element[i];
            else removed++;
        }
        for (int i = j; i < size; i++) element[i] = null;
        size = j;
        return removed;
    }

    /** Оруулан эрэмбэлэх (өсөхөөр) */
    public void sort() {
        for (int i = 1; i < size; i++) {
            Object key = element[i];
            int k = (Integer) key, j = i - 1;
            while (j >= 0 && val(j) > k) { element[j + 1] = element[j]; j--; }
            element[j + 1] = key;
        }
    }

    // ---------------- Интерактив цэс ----------------
    public static void menu() {
        MyArrayLinearList list = new MyArrayLinearList();
        while (true) {
            System.out.println("\n--- MyArrayLinearList ---");
            System.out.println("Одоогийн жагсаалт: " + list);
            System.out.println("1. Элемент нэмэх - add");
            System.out.println("2. Олон элемент нэмэх");
            System.out.println("3. Элемент устгах - remove");
            System.out.println("4. Элемент авах - get");
            System.out.println("5. Элемент хайх - indexOf");
            System.out.println("6. Хамгийн их - max");
            System.out.println("7. Хамгийн бага - min");
            System.out.println("8. Нийлбэр - sum");
            System.out.println("9. Дундаж - average");
            System.out.println("10. Сондгойг устгах - removeOdd");
            System.out.println("11. Эрэмбэлэх - sort");
            System.out.println("12. Хэмжээ / хоосон эсэх - size, isEmpty");
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
                        int[] a = Input.readIntArray("Утгууд (зайгаар): ");
                        for (int v : a) list.add(list.size(), v);
                        System.out.println(a.length + " элемент нэмэгдлээ.");
                        break;
                    }
                    case 3: System.out.println("Устгагдсан: " + list.remove(Input.readInt("Индекс: "))); break;
                    case 4: System.out.println("Утга: " + list.get(Input.readInt("Индекс: "))); break;
                    case 5: {
                        int i = list.indexOf(Input.readInt("Хайх утга: "));
                        System.out.println(i < 0 ? "Олдсонгүй." : "Индекс: " + i);
                        break;
                    }
                    case 6: System.out.println("max = " + list.max()); break;
                    case 7: System.out.println("min = " + list.min()); break;
                    case 8: System.out.println("sum = " + list.sum()); break;
                    case 9: System.out.printf("average = %.2f%n", list.average()); break;
                    case 10: System.out.println(list.removeOdd() + " сондгой элемент устгагдлаа."); break;
                    case 11: list.sort(); System.out.println("Эрэмбэлэгдлээ: " + list); break;
                    case 12: System.out.println("size = " + list.size() + ", isEmpty = " + list.isEmpty()); break;
                    case 0: return;
                    default: System.out.println("Буруу сонголт!");
                }
            } catch (RuntimeException e) {
                System.out.println("Алдаа: " + e.getMessage());
            }
        }
    }
}
