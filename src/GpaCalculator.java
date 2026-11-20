import java.util.Random;

/**
 * Бодлого: Оюутны n хичээлийн дүн өгөгдсөн бол голч оноог (GPA) бодож хэвлэнэ.
 * Өөрийн улирлын дүнгээр шалгах, мөн n-ийг ялгаатай өгч ажиллах хурдыг харьцуулна.
 * Голч = Σ(кредит × дүнгийн оноо) / Σ кредит
 */
public class GpaCalculator {

    private final String[] names;
    private final double[] credits;
    private final double[] scores;
    private int n;

    public GpaCalculator(int capacity) {
        if (capacity < 1) throw new IllegalArgumentException("Хичээлийн тоо >= 1 байх ёстой");
        names = new String[capacity];
        credits = new double[capacity];
        scores = new double[capacity];
    }

    public void addCourse(String name, double credit, double score) {
        if (n == names.length) throw new IllegalStateException("Хичээлийн тоо дүүрсэн");
        if (credit <= 0) throw new IllegalArgumentException("Кредит 0-ээс их байх ёстой");
        if (score < 0 || score > 100) throw new IllegalArgumentException("Оноо 0-100 байх ёстой");
        names[n] = name; credits[n] = credit; scores[n] = score; n++;
    }

    /** 100 онооны дүнг 4.0 системийн оноо руу хөрвүүлнэ */
    public static double gradePoint(double s) {
        if (s >= 96) return 4.0;
        if (s >= 91) return 3.7;
        if (s >= 88) return 3.3;
        if (s >= 84) return 3.0;
        if (s >= 81) return 2.7;
        if (s >= 78) return 2.3;
        if (s >= 74) return 2.0;
        if (s >= 71) return 1.7;
        if (s >= 68) return 1.3;
        if (s >= 64) return 1.0;
        if (s >= 61) return 0.7;
        return 0.0;
    }

    public static String letter(double s) {
        if (s >= 96) return "A";
        if (s >= 91) return "A-";
        if (s >= 88) return "B+";
        if (s >= 84) return "B";
        if (s >= 81) return "B-";
        if (s >= 78) return "C+";
        if (s >= 74) return "C";
        if (s >= 71) return "C-";
        if (s >= 68) return "D+";
        if (s >= 64) return "D";
        if (s >= 61) return "D-";
        return "F";
    }

    /** Голч оноо — O(n) */
    public double gpa() {
        if (n == 0) throw new IllegalStateException("Хичээл оруулаагүй байна");
        double sumCredit = 0, sumPoint = 0;
        for (int i = 0; i < n; i++) {
            sumCredit += credits[i];
            sumPoint += credits[i] * gradePoint(scores[i]);
        }
        return sumPoint / sumCredit;
    }

    public void print() {
        System.out.println("-----------------------------------------------------------");
        System.out.printf("%-3s %-30s %7s %6s %6s %6s%n", "№", "Хичээл", "Кредит", "Оноо", "Үсэг", "GP");
        System.out.println("-----------------------------------------------------------");
        double totalCredit = 0;
        for (int i = 0; i < n; i++) {
            System.out.printf("%-3d %-30s %7.1f %6.1f %6s %6.1f%n", i + 1, names[i], credits[i],
                    scores[i], letter(scores[i]), gradePoint(scores[i]));
            totalCredit += credits[i];
        }
        System.out.println("-----------------------------------------------------------");
        System.out.printf("Нийт кредит: %.1f    ГОЛЧ ОНОО (GPA): %.2f%n", totalCredit, gpa());
    }

    // ---------------- 1. Гараас дүн оруулах ----------------
    private static void manualInput() {
        int n = Input.readInt("Хичээлийн тоо n = ", 1, 100);
        GpaCalculator g = new GpaCalculator(n);
        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + "-р хичээл:");
            String name = Input.readLine("  Нэр: ");
            if (name.isEmpty()) name = "Хичээл " + (i + 1);
            double cr = Input.readDouble("  Кредит (0.5-10): ", 0.5, 10);
            double sc = Input.readDouble("  Оноо (0-100): ", 0, 100);
            g.addCourse(name, cr, sc);
        }
        g.print();
    }

    // ---------------- 2. Өөрийн улирлын дүнгээр шалгах ----------------
    private static void myTranscript() {
        System.out.println("Веб дээрх улирлын дүнгээ оруулж шалгана уу (жишээ дүн доор):");
        GpaCalculator g = new GpaCalculator(6);
        g.addCourse("Алгоритм ба өгөгдлийн бүтэц", 3, 92);
        g.addCourse("Шугаман алгебр", 3, 85);
        g.addCourse("Програмчлал Java", 3, 97);
        g.addCourse("Англи хэл", 2, 79);
        g.addCourse("Магадлал статистик", 3, 88);
        g.addCourse("Биеийн тамир", 1, 100);
        g.print();
        System.out.println("Веб дээрх голчтой харьцуулж шалгана уу. Өөрийн дүнгээ оруулах бол 1-р сонголтыг ашиглана.");
    }

    // ---------------- 3. Ажиллах хурдыг харьцуулах ----------------
    public static long measure(int n, Random rnd) {
        GpaCalculator g = new GpaCalculator(n);
        for (int i = 0; i < n; i++) g.addCourse("C" + i, 1 + rnd.nextInt(4), rnd.nextInt(101));
        long t0 = System.nanoTime();
        double r = g.gpa();
        long t1 = System.nanoTime();
        if (r < 0) System.out.println(); // оновчлолоор устгагдахаас сэргийлэх
        return t1 - t0;
    }

    private static void speedTest() {
        System.out.println("1. Тогтмол n утгуудаар (10, 100, 1000, 10^4, 10^5, 10^6)");
        System.out.println("2. n-ийг гараас оруулах");
        int c = Input.readInt("Сонголт: ", 1, 2);
        int[] ns;
        if (c == 1) ns = new int[]{10, 100, 1000, 10_000, 100_000, 1_000_000};
        else {
            int k = Input.readInt("Хэдэн n туршихыг оруулна уу (1-10): ", 1, 10);
            ns = new int[k];
            for (int i = 0; i < k; i++) ns[i] = Input.readInt("  n" + (i + 1) + " = ", 1, 5_000_000);
        }
        Random rnd = new Random(42);
        measure(100_000, rnd); // JVM халаах
        System.out.println("-------------------------------------------");
        System.out.printf("%-12s %-15s %-12s%n", "n", "Хугацаа (нс)", "Хугацаа (мс)");
        System.out.println("-------------------------------------------");
        for (int n : ns) {
            long t = measure(n, rnd);
            System.out.printf("%-12d %-15d %-12.4f%n", n, t, t / 1e6);
        }
        System.out.println("-------------------------------------------");
        System.out.println("Дүгнэлт: GPA бодох нь O(n) тул n өсөхөд хугацаа шугаман өснө.");
    }

    public static void menu() {
        while (true) {
            System.out.println("\n--- GPA тооцоолуур ---");
            System.out.println("1. n хичээлийн дүн оруулж голч бодох");
            System.out.println("2. Өөрийн улирлын дүнгээр шалгах");
            System.out.println("3. Ажиллах хурдыг n-ээр харьцуулах");
            System.out.println("4. Дүнгийн хүснэгт харах");
            System.out.println("0. Буцах");
            int c = Input.readInt("Сонголт хийнэ үү: ");
            try {
                switch (c) {
                    case 1: manualInput(); break;
                    case 2: myTranscript(); break;
                    case 3: speedTest(); break;
                    case 4:
                        System.out.println("96-100 A 4.0 | 91-95 A- 3.7 | 88-90 B+ 3.3 | 84-87 B 3.0");
                        System.out.println("81-83 B- 2.7 | 78-80 C+ 2.3 | 74-77 C 2.0 | 71-73 C- 1.7");
                        System.out.println("68-70 D+ 1.3 | 64-67 D 1.0  | 61-63 D- 0.7 | 0-60 F 0.0");
                        break;
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
