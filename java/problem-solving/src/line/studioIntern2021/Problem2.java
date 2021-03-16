package line.studioIntern2021;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Problem2 {
    public String[] solution(int[][] line) {
        List<Point> pointList = new ArrayList<>();
        for (int i = 0; i < line.length; i++) {
            double A = line[i][0];
            double B = line[i][1];
            double E = line[i][2];
            for (int j = i + 1; j < line.length; j++) {
                double C = line[j][0];
                double D = line[j][1];
                double F = line[j][2];

                double base = A*D - B*C;
                if (base == 0) continue;
                double posX = (B * F - E * D) / base;
                double posY = (E * C - A * F) / base;
                Point p = new Point(posX, posY);
                if (p.isDecimal()) {
                    pointList.add(p);
                }
            }
        }

        pointList = pointList.stream()
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        Integer minX = Integer.MAX_VALUE;
        Integer minY = Integer.MAX_VALUE;
        Integer maxX = Integer.MIN_VALUE;
        Integer maxY = Integer.MIN_VALUE;
        for (int i = 0; i < pointList.size(); i++) {
            Point currentPoint = pointList.get(i);
            minX = Math.min(minX, (int) currentPoint.posX);
            minY = Math.min(minY, (int) currentPoint.posY);
            maxX = Math.max(maxX, (int) currentPoint.posX);
            maxY = Math.max(maxY, (int) currentPoint.posY);
        }

        String[] results = new String[maxY - minY + 1];
        String temp = "";
        for (int j = minX; j <= maxX; j++) {
            temp += ".";
        }

        for (int i = 0; i <= maxY - minY; i++) {
            results[i] = temp;
        }

        for (int i = 0; i < pointList.size(); i++) {
            Point currentPoint = pointList.get(i);
            int posX = (int) currentPoint.posX - minX;
            int posY = maxY - (int) currentPoint.posY;
            StringBuilder sb = new StringBuilder(results[posY]);
            sb.setCharAt(posX, '*');
            results[posY] = sb.toString();
        }

        return results;
    }

    class Point implements Comparable {
        double posX;
        double posY;

        public Point(double posX, double posY) {
            this.posX = posX;
            this.posY = posY;
        }

        public boolean isDecimal() {
            if (this.posX - (long)this.posX == 0 && this.posY - (long)this.posY == 0) {
                return true;
            }
            return false;
        }

        @Override
        public boolean equals(Object obj) {
            Point p = (Point) obj;
            if (p.posX == this.posX && p.posY == this.posY) {
                return true;
            }
            return false;
        }

        @Override
        public int compareTo(Object o) {
            Point p = (Point) o;
            if (this.posY == p.posY) {
                return Double.compare(this.posX, p.posX);
            }
            return Double.compare(this.posY, p.posY);
        }
    }
}
