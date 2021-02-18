package beakJoon.cheese;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Cheese {
    private int[] moveRow = {1, -1, 0, 0};
    private int[] moveCol = {0, 0, 1, -1};
    public void solution() {
        Scanner scanner = new Scanner(System.in);
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        int[][] cheese = new int[row][col];
        boolean[][] visited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                cheese[i][j] = scanner.nextInt() * 4;
            }
        }

        Queue<Position> airQueue = new LinkedList<>();
        airQueue.add(new Position(0,0));
        visited[0][0] = true;
        int result = 0;
        while (!airQueue.isEmpty()) {
            int updatedNum = 0;
            Queue<Position> nextQueue = new LinkedList<>();
            while (!airQueue.isEmpty()) {
                Position currentAir = airQueue.poll();
                cheese[currentAir.row][currentAir.col] = 0;
                for (int i = 0; i < 4; i++) {
                    int nextRow = currentAir.row + moveRow[i];
                    int nextCol = currentAir.col + moveCol[i];
                    if (nextRow >= 0 && nextCol >= 0 && nextRow < row && nextCol < col) {
                        if (visited[nextRow][nextCol]) continue;
                        if (cheese[nextRow][nextCol] == 0) {
                            visited[nextRow][nextCol] = true;
                            airQueue.add(new Position(nextRow, nextCol));
                        } else {
                            updatedNum++;
                            cheese[nextRow][nextCol] -= 1;
                            if (cheese[nextRow][nextCol] == 2) {
                                visited[nextRow][nextCol] = true;
                                nextQueue.add(new Position(nextRow, nextCol));
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < nextQueue.size(); i++) {
                Position cheesePiece = nextQueue.poll();
                cheese[cheesePiece.row][cheesePiece.col] = 0;
                nextQueue.add(cheesePiece);
            }
            if (updatedNum == 0) break;
            airQueue = nextQueue;
            result++;
        }
        System.out.println(result);
        return;
    }

    class Position {
        int row;
        int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
