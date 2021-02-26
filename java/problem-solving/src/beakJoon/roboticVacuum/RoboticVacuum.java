package beakJoon.roboticVacuum;

import com.sun.javafx.scene.input.ExtendedInputMethodRequests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class RoboticVacuum {
    int[] moveX = {1, -1, 0, 0};
    int[] moveY = {0, 0, 1, -1};
    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        loop:
        do {
            String[] rc = br.readLine().split(" ");
            int width = Integer.parseInt(rc[0]);
            int height = Integer.parseInt(rc[1]);
            if (width == 0 && height == 0) break;
            List<Position> positions = new ArrayList<>();
            char[][] board = new char[height][width];
            for (int i = 0; i < height; i++) {
                String currentRow = br.readLine();
                for (int j = 0; j < width; j++) {
                    board[i][j] = currentRow.charAt(j);
                    if (board[i][j] == '*') {
                        board[i][j] = '.';
                        positions.add(new Position(j, i));
                    }
                    if (board[i][j] == 'o') {
                        board[i][j] = '.';
                        positions.add(0, new Position(j, i));
                    }
                }
            }

            int[][] boardDistance = new int[positions.size()][positions.size()];
            for (int i = 0; i < positions.size(); i++) {
                Position currentPosition = positions.get(i);
                int[][] minDist = bfs(board, currentPosition.posX, currentPosition.posY);
                for (int j = 1; j < positions.size(); j++) {
                    Position toPosition = positions.get(j);
                    boardDistance[i][j] = minDist[toPosition.posY][toPosition.posX];
                    if (i != j && boardDistance[i][j] == 0) {
                        System.out.println(-1);
                        continue loop;
                    }
                }
            }

            List<Integer> tempOrder = new ArrayList<>();
            tempOrder.add(0);
            int total = permutation(positions.size(), new boolean[positions.size()], tempOrder, boardDistance, 0);
            System.out.println(total);

        } while(true);
    }

    private int permutation(int n, boolean[] visited, List<Integer> order, int[][] minDist, int result) {
        if (order.size() == n) {
            return result;
        }
        int res = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            if (visited[i] == false) {
                order.add(i);
                visited[i] = true;
                res = Math.min(res, permutation(n, visited, order, minDist, result + minDist[order.get(order.size() - 2)][order.get(order.size() -1)]));
                visited[i] = false;
                order.remove(order.size() - 1);
            }
        }
        return res;
    }

    private int[][] bfs(char[][] originalBoard, int posX, int posY) {
        Queue<QueueItem> q = new LinkedList<>();
        q.add(new QueueItem(posX, posY, 0));
        int[][] board = new int[originalBoard.length][originalBoard[0].length];
        board[posY][posX] = 0;
        boolean[][] visited = new boolean[board.length][board[0].length];
        visited[posY][posX] = true;
        while(!q.isEmpty()) {
            QueueItem qi = q.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = qi.posX + moveX[i];
                int nextY = qi.posY + moveY[i];
                int nextCount = qi.count + 1;
                if (nextX < 0 || nextY < 0 || nextX >= board[0].length || nextY >= board.length) continue;
                if (originalBoard[nextY][nextX] == 'x') continue;
                if (visited[nextY][nextX] == false || board[nextY][nextX] > nextCount) {
                    board[nextY][nextX] = nextCount;
                    visited[nextY][nextX] = true;
                    q.add(new QueueItem(nextX, nextY, nextCount));
                }
            }
        }
        return board;
    }

    private class Position{
        int posX;
        int posY;

        public Position(int posX, int posY) {
            this.posX = posX;
            this.posY = posY;
        }
    }

    private class QueueItem {
        int posX;
        int posY;
        int count;

        public QueueItem(int posX, int posY, int count) {
            this.posX = posX;
            this.posY = posY;
            this.count = count;
        }
    }
}
