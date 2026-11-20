/** Үндсэн цэс — бүх лабораторийн ажлыг эндээс ажиллуулна */
public class MainMenu {

    private static void labMenu() {
        while (true) {
            System.out.println("\n--- Лабораторын ажил ---");
            System.out.println("1. GPA тооцоолуур (голч оноо)");
            System.out.println("2. Лаб 1 - MyArrayLinearList");
            System.out.println("3. Лаб 2 - MyChain");
            System.out.println("4. Лаб 3 - MyStack");
            System.out.println("0. Буцах");
            switch (Input.readInt("Сонголт хийнэ үү: ")) {
                case 1: GpaCalculator.menu(); break;
                case 2: MyArrayLinearList.menu(); break;
                case 3: MyChain.menu(); break;
                case 4: MyStack.menu(); break;
                case 0: return;
                default: System.out.println("Буруу сонголт!");
            }
        }
    }

    private static void listMenu() {
        while (true) {
            System.out.println("\n--- Жагсаалт ---");
            System.out.println("1. Массивт жагсаалт - MyArrayLinearList");
            System.out.println("2. Холбоост жагсаалт - MyChain");
            System.out.println("0. Буцах");
            switch (Input.readInt("Сонголт хийнэ үү: ")) {
                case 1: MyArrayLinearList.menu(); break;
                case 2: MyChain.menu(); break;
                case 0: return;
                default: System.out.println("Буруу сонголт!");
            }
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Menu =====");
            System.out.println("1. Лабораторын ажил");
            System.out.println("2. Жагсаалт");
            System.out.println("3. Стек");
            System.out.println("4. Хэш хүснэгт");
            System.out.println("5. Хоёртын мод");
            System.out.println("6. Толь бичиг");
            System.out.println("7. Нэмэлт онооны даалгаврууд");
            System.out.println("8. Бие даалтын ажлууд");
            System.out.println("0. Гарах");
            int c = Input.readInt("Сонголт хийнэ үү: ");
            switch (c) {
                case 1: labMenu(); break;
                case 2: listMenu(); break;
                case 3: MyStack.menu(); break;
                case 4: case 5: case 6: case 7: case 8:
                    System.out.println("Энэ хэсэг хараахан хэрэгжээгүй байна."); break;
                case 0: System.out.println("Баяртай!"); return;
                default: System.out.println("Буруу сонголт!");
            }
        }
    }
}
