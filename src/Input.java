import java.util.Scanner;

/** Гарын оролтыг алдаагүй унших туслах класс */
public class Input {
    public static final Scanner sc = new Scanner(System.in);

    public static int readInt(String msg) {
        while (true) {
            System.out.print(msg);
            String s = sc.nextLine().trim();
            try { return Integer.parseInt(s); }
            catch (NumberFormatException e) { System.out.println("  Алдаа: бүхэл тоо оруулна уу!"); }
        }
    }

    public static int readInt(String msg, int min, int max) {
        while (true) {
            int v = readInt(msg);
            if (v >= min && v <= max) return v;
            System.out.println("  Алдаа: " + min + "-" + max + " хооронд утга оруулна уу!");
        }
    }

    public static double readDouble(String msg, double min, double max) {
        while (true) {
            System.out.print(msg);
            String s = sc.nextLine().trim().replace(',', '.');
            try {
                double v = Double.parseDouble(s);
                if (v >= min && v <= max) return v;
                System.out.println("  Алдаа: " + min + "-" + max + " хооронд утга оруулна уу!");
            } catch (NumberFormatException e) { System.out.println("  Алдаа: тоо оруулна уу!"); }
        }
    }

    public static String readLine(String msg) {
        System.out.print(msg);
        return sc.nextLine().trim();
    }

    /** "1 2 3" гэх мэт мөрийг бүхэл тоон массив болгоно */
    public static int[] readIntArray(String msg) {
        while (true) {
            String s = readLine(msg);
            if (s.isEmpty()) { System.out.println("  Алдаа: хоосон байна!"); continue; }
            String[] p = s.split("[\\s,]+");
            int[] a = new int[p.length];
            try {
                for (int i = 0; i < p.length; i++) a[i] = Integer.parseInt(p[i]);
                return a;
            } catch (NumberFormatException e) { System.out.println("  Алдаа: зөвхөн бүхэл тоо, зайгаар тусгаарлана уу!"); }
        }
    }

    public static void pause() {
        System.out.print("Үргэлжлүүлэх бол Enter дарна уу...");
        sc.nextLine();
    }
}
