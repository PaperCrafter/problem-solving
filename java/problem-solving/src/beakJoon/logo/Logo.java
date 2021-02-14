package beakJoon.logo;

import java.util.*;

public class Logo {
    public void solution() {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int[] disjointSet = new int[num];
        Rectangle[] rectangles = new Rectangle[num];
        for (int i = 0; i < num; i++) {
            int x1 = scanner.nextInt();
            int y1 = scanner.nextInt();
            int x2 = scanner.nextInt();
            int y2 = scanner.nextInt();
            rectangles[i]  = new Rectangle(x1, y1, x2, y2);
        }

        for (int i = 0; i < disjointSet.length; i++) {
            disjointSet[i] = i;
        }

        for (int i = 0; i < rectangles.length; i++) {
            for (int j = i + 1; j < rectangles.length; j++) {
                if (findParent(disjointSet, i, j)) continue;
                else {
                    if (rectangles[i].includes(rectangles[j])) {
                        unionParent(disjointSet, i, j);
                    }
                }
            }
        }

        boolean[] checked = new boolean[num];
        int counter = 0;
        for (int i = 0; i < num; i++) {
            int currentParent = getParent(disjointSet, i);
            if (checked[currentParent] == false) {
                counter++;
                checked[currentParent] = true;
            }
        }

        for (int i = 0; i < num; i++) {
            if (rectangles[i].isOnRectangle(0, 0)) {
                counter--;
                break;
            }
        }

        System.out.print(counter);
    }

    class Rectangle {
        public int x1;
        public int x2;
        public int y1;
        public int y2;

        public Rectangle(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        public boolean includes(Rectangle rectangle) {
            if (this.x1 > rectangle.x1 && this.y1 > rectangle.y1 && this.x2 < rectangle.x2 && this.y2 < rectangle.y2) return false;
            if (this.x1 < rectangle.x1 && this.y1 < rectangle.y1 && this.x2 > rectangle.x2 && this.y2 > rectangle.y2) return false;
            if (this.x1 > rectangle.x2 || this.y1 > rectangle.y2 || rectangle.x1 > this.x2 || rectangle.y1 > this.y2) return false;
            return true;
        }

        public boolean isOnRectangle(int x, int y) {
            if (this.x1 == x && this.y1 <= y && this.y2 >= y) return true;
            if (this.x2 == x && this.y1 <= y && this.y2 >= y) return true;
            if (this.y1 == y && this.x1 <= x && this.x2 >= x) return true;
            if (this.y2 == y && this.x1 <= x && this.x2 >= x) return true;
            return false;
        }
    }

    private boolean findParent(int[] disjointSet, int node1, int node2) {
        int parent1 = getParent(disjointSet, node1);
        int parent2 = getParent(disjointSet, node2);
        if (parent1 == parent2) return true;
        return false;
    }

    private int getParent(int[] disjointSet, int node) {
        if (disjointSet[node] == node) return node;
        return getParent(disjointSet, disjointSet[node]);
    }

    private void unionParent(int[] disjointSet, int node1, int node2) {
        int parent1 = getParent(disjointSet, node1);
        int parent2 = getParent(disjointSet, node2);

        if (parent1 < parent2) disjointSet[parent2] = parent1;
        else disjointSet[parent1] = parent2;
    }
}
