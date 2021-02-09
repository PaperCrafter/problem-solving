package beakJoon.laserCommunication;

import com.sun.scenario.effect.impl.sw.java.JSWColorAdjustPeer;

import java.util.*;

public class LaserCommunication {
    int[] dirX = {1, -1, 0, 0};
    int[] dirY = {0, 0, 1, -1};
    public void solution() {
        Scanner scanner = new Scanner(System.in);
        int width = scanner.nextInt();
        int height = scanner.nextInt();
        int[][] board = new int[height][width];
        List<Position> points = new LinkedList<>();
        for (int i = 0; i < height; i++) {
            String currentRow = scanner.next();
            for (int j = 0; j < width; j++) {
                if (currentRow.charAt(j) == '.') {
                    board[i][j] = 0;
                } else if (currentRow.charAt(j) == '*') {
                    board[i][j] = -1;
                } else {
                    board[i][j] = 0;
                    points.add(new Position(j, i));
                }
            }
        }

        Queue<Laser> q = new LinkedList<>();
        boolean[][] visited = new boolean[height][width];
        Position laserBegin = points.get(0);
        visited[laserBegin.posY][laserBegin.posX] = true;
        Position laserEnd = points.get(1);
        for (int i = 0; i < 4; i++) {
            int nextDirX = dirX[i];
            int nextDirY = dirY[i];
            int nextPosX = laserBegin.posX + nextDirX;
            int nextPosY = laserBegin.posY + nextDirY;
            if (nextPosX >= 0 && nextPosY >= 0 && nextPosX < width && nextPosY < height) {
                if (board[nextPosY][nextPosX] == -1) continue;
                q.add(new Laser(nextDirX, nextDirY, nextPosX, nextPosY, 0));
                visited[nextPosY][nextPosX] = true;
            }
        }

        while (!q.isEmpty()) {
            Laser currentLaser = q.poll();
            for (int i = 0; i < 4; i++) {
                int nextDirX = dirX[i];
                int nextDirY = dirY[i];
                int nextPosX = currentLaser.position.posX + nextDirX;
                int nextPosY = currentLaser.position.posY + nextDirY;
                if (nextPosX >= 0 && nextPosY >= 0 && nextPosX < width && nextPosY < height) {
                    if (board[nextPosY][nextPosX] == -1) continue;
                    int nextMirrorNum = currentLaser.cnt;
                    if (currentLaser.dirX != nextDirX || currentLaser.dirY != nextDirY) {
                        nextMirrorNum++;
                    }
                    if (visited[nextPosY][nextPosX] == false || board[nextPosY][nextPosX] >= nextMirrorNum) {
                        q.add(new Laser(nextDirX, nextDirY, nextPosX, nextPosY, nextMirrorNum));
                        board[nextPosY][nextPosX] = nextMirrorNum;
                        visited[nextPosY][nextPosX] = true;
                    }
                }
            }
        }

        System.out.println(board[laserEnd.posY][laserEnd.posX]);
    }

    class Position {
        public int posX;
        public int posY;

        public Position(int posX, int posY) {
            this.posX = posX;
            this.posY = posY;
        }
    }

    class Laser {
        public int dirX;
        public int dirY;
        public Position position;
        public int cnt;

        public Laser(int dirX, int dirY, int posX, int posY, int cnt) {
            this.dirX = dirX;
            this.dirY = dirY;
            position = new Position(posX, posY);
            this.cnt = cnt;
        }
    }
}
