package beakJoon.move;

import java.util.*;

public class Move {
    public void solution() {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            points.add(new Point(sc.nextInt(), sc.nextInt()));
        }
        double minDist = Double.MAX_VALUE;
        int minIdx = 0;
        for (int i = 0; i < points.size(); i++) {
            Point p1 = points.get(i);
            double maxDist = -1;
            for (int j = 0; j < points.size(); j++) {
                if (i == j) continue;
                Point p2 = points.get(j);
                double dist = Math.sqrt(Math.pow((p1.posX - p2.posX), 2) + Math.pow((p1.posY - p2.posY), 2));
                if (dist > maxDist) {
                    maxDist = dist;
                }
            }

            if (minDist > maxDist) {
                minIdx = i;
                minDist = maxDist;
            }
        }
        Point p = points.get(minIdx);
        System.out.println(p.posX + " " + p.posY);
    }

    class Point {
        int posX;
        int posY;

        public Point(int posX, int posY) {
            this.posX = posX;
            this.posY = posY;
        }
    }
}
