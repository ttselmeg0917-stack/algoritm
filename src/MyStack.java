import java.util.EmptyStackException;

/** Лаборатори №3: ArrayStack-ийг өргөтгөсөн класс */
public class MyStack extends ArrayStack {

    public MyStack() { super(); }

    /** Стекийн хэмжээ */
    public int size() { return top + 1; }

    /** Стекийг гараас оруулах (ёроолоос орой руу) */
    public void inputStack() {
        int[] a = Input.readIntArray("Стекийн элементүүд (ёроолоос орой руу, зайгаар): ");
        for (int v : a) push(v);
    }

    /** Стекийг string болгож хэвлэх */
    public String printStack() {
        StringBuilder s = new StringBuilder("[ёроол] ");
        for (int i = 0; i <= top; i++) {
            s.append(stack[i]);
            if (i < top) s.append(", ");
        }
        s.append(" [орой]");
        System.out.println(s);
        return s.toString();
    }

    /** Стекийг хоёр хуваана: доод хагас нь энэ стект үлдэж, дээд хагас нь шинэ стек болж буцна. Дараалал хадгалагдана. */
    public MyStack splitStack() {
        if (size() < 2) throw new IllegalStateException("Хуваахад дор хаяж 2 элемент хэрэгтэй!");
        int half = size() / 2;          // доод хэсэгт үлдэх элементийн тоо
        MyStack upper = new MyStack();
        for (int i = half; i <= top; i++) { upper.push(stack[i]); stack[i] = null; }
        top = half - 1;
        return upper;
    }

    /** Энэ стекийн орой дээр s2-ийн элементүүдийг нэмж шинэ стек үүсгэнэ. Дараалал хадгалагдана. */
    public MyStack combineStack(MyStack s2) {
        if (s2 == null) throw new IllegalArgumentException("Стек null байна");
        MyStack r = new MyStack();
        for (int i = 0; i <= top; i++) r.push(stack[i]);
        for (int i = 0; i <= s2.top; i++) r.push(s2.stack[i]);
        return r;
    }

    // ---------------- Интерактив цэс ----------------
    public static void menu() {
        MyStack s = new MyStack();
        while (true) {
            System.out.println("\n--- MyStack ---");
            System.out.print("Одоогийн стек: "); s.printStack();
            System.out.println("1. Элемент нэмэх - push");
            System.out.println("2. Элемент гаргах - pop");
            System.out.println("3. Оройн элемент - peek");
            System.out.println("4. Хоосон эсэх - empty");
            System.out.println("5. Стекийн хэмжээ - size");
            System.out.println("6. Стекийг оруулах - inputStack");
            System.out.println("7. Стекийг хэвлэх - printStack");
            System.out.println("8. Стекийг хоёр хуваах - splitStack");
            System.out.println("9. Хоёр стекийг нэгтгэх - combineStack");
            System.out.println("0. Буцах");
            int c = Input.readInt("Сонголт хийнэ үү: ");
            try {
                switch (c) {
                    case 1: s.push(Input.readInt("Утга: ")); System.out.println("Нэмэгдлээ."); break;
                    case 2: System.out.println("Гаргасан: " + s.pop()); break;
                    case 3: System.out.println("Орой: " + s.peek()); break;
                    case 4: System.out.println("empty = " + s.empty()); break;
                    case 5: System.out.println("size = " + s.size()); break;
                    case 6: s.inputStack(); System.out.println("Оруулагдлаа."); break;
                    case 7: s.printStack(); break;
                    case 8: {
                        MyStack upper = s.splitStack();
                        System.out.print("1-р стек (доод хэсэг): "); s.printStack();
                        System.out.print("2-р стек (дээд хэсэг): "); upper.printStack();
                        break;
                    }
                    case 9: {
                        MyStack s2 = new MyStack();
                        s2.inputStack();
                        MyStack r = s.combineStack(s2);
                        System.out.print("Шинэ стек: "); r.printStack();
                        int k = Input.readInt("Шинэ стекийг үндсэн болгох уу? (1-тийм, 0-үгүй): ", 0, 1);
                        if (k == 1) s = r;
                        break;
                    }
                    case 0: return;
                    default: System.out.println("Буруу сонголт!");
                }
            } catch (EmptyStackException e) {
                System.out.println("Алдаа: Стек хоосон байна!");
            } catch (RuntimeException e) {
                System.out.println("Алдаа: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) { menu(); }
}
