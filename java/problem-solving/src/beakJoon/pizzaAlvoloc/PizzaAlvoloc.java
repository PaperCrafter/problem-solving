package beakJoon.pizzaAlvoloc;

import java.util.*;

public class PizzaAlvoloc {
    public void solution() {
        Scanner scanner = new Scanner(System.in);
        int x1 = scanner.nextInt();
        int y1 = scanner.nextInt();
        int x2 = scanner.nextInt();
        int y2 = scanner.nextInt();
        int x3 = scanner.nextInt();
        int y3 = scanner.nextInt();
        int x4 = scanner.nextInt();
        int y4 = scanner.nextInt();
        if (discriminator(x3, y3, x1, y1, x2, y2) * discriminator(x4, y4, x1, y1, x2, y2) < 0) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }

    }

    int discriminator(int x, int y, int x1, int y1, int x2, int y2) {
        int temp =  (y - y1) * (x2 - x1) - (y2 - y1) * (x - x1);
        if (temp < 0) return -1;
        else if (temp == 0) return 0;
        else return 1;
    }
}
