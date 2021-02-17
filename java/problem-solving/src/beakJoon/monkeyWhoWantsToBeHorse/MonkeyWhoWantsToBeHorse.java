package beakJoon.monkeyWhoWantsToBeHorse;

import java.util.*;

public class MonkeyWhoWantsToBeHorse {
    int[] moveWidthM = {1, -1, 0, 0};
    int[] moveHeightM = {0, 0, 1, -1};
    int[] moveWidthH = {-2, -1, 1, 2, 2, 1, -1, -2};
    int[] moveHeightH = {1, 2, 2, 1, -1, -2, -2, -1};
    public void solution() {
        Scanner scanner = new Scanner(System.in);
        int horseCount = scanner.nextInt();
        int width = scanner.nextInt();
        int height = scanner.nextInt();
        int[][] board = new int[height][width];
        boolean[][][] visited = new boolean[horseCount + 1][height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = scanner.nextInt();
            }
        }

        Queue<Monkey> q = new LinkedList<>();
        q.add(new Monkey(0, 0, horseCount, 0));
        visited[horseCount][0][0] = true;
        while (!q.isEmpty()) {
            Monkey currentMonkey = q.poll();
            if (currentMonkey.posX == width - 1 && currentMonkey.posY == height - 1) {
                System.out.println(currentMonkey.count);
                return;
            }

            int nextPosX = 0;
            int nextPosY = 0;
            int nextHorseCount = currentMonkey.horseCount;
            for (int i = 0; i < moveWidthM.length; i++) {
                nextPosX = currentMonkey.posX + moveWidthM[i];
                nextPosY = currentMonkey.posY + moveHeightM[i];
                if (nextPosX >= 0 && nextPosX < width && nextPosY >= 0 && nextPosY < height) {
                    if (visited[nextHorseCount][nextPosY][nextPosX] == false && board[nextPosY][nextPosX] == 0) {
                        visited[nextHorseCount][nextPosY][nextPosX] = true;
                        q.add(new Monkey(nextPosX, nextPosY, nextHorseCount, currentMonkey.count + 1));
                    }
                }
            }

            nextHorseCount = currentMonkey.horseCount - 1;
            if (currentMonkey.horseCount > 0) {
                for (int i = 0; i < moveHeightH.length; i++) {
                    nextPosX = currentMonkey.posX + moveWidthH[i];
                    nextPosY = currentMonkey.posY + moveHeightH[i];
                    if (nextPosX >= 0 && nextPosX < width && nextPosY >= 0 && nextPosY < height) {
                        if (visited[nextHorseCount][nextPosY][nextPosX] == false && board[nextPosY][nextPosX] == 0) {
                            visited[nextHorseCount][nextPosY][nextPosX] = true;
                            q.add(new Monkey(nextPosX, nextPosY, nextHorseCount, currentMonkey.count + 1));
                        }
                    }
                }
            }
        }
        System.out.println(-1);
        return;
    }

    class Monkey {
        int posX;
        int posY;
        int horseCount;
        int count;

        public Monkey(int posX, int posY, int horseCount, int count) {
            this.posX = posX;
            this.posY = posY;
            this.horseCount = horseCount;
            this.count = count;
        }
    }
}
