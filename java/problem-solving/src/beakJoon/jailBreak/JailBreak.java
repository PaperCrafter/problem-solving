package beakJoon.jailBreak;

import java.util.*;

public class JailBreak {
    int[] moveRow = {1, -1, 0, 0};
    int[] moveCol = {0, 0, 1, -1};
    public void solution() {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        for (int i = 0; i < num; i++) {
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            int[][] jail = new int[row + 2][col + 2];
            int[][][] board = new int[3][row + 2][col + 2];
            List<Position> positionList = new ArrayList<>();

            for (int j = 0; j < row + 2; j++) {
                for (int k = 0; k < 3; k++) {
                    Arrays.fill(board[k][j], Integer.MAX_VALUE);
                }
            }

            positionList.add(new Position(0, 0));
            for (int j = 1; j < row + 1; j++) {
                String currentRow = scanner.next();
                for (int k = 0; k < col; k++) {
                    int currentCell = 0;
                    if (currentRow.charAt(k) == '*') currentCell = -1;
                    else if (currentRow.charAt(k) == '.') currentCell = 0;
                    else if (currentRow.charAt(k) == '#') currentCell = 1;
                    else {
                        currentCell = 0;
                        positionList.add(new Position(j, k + 1));
                    }
                    jail[j][k + 1] = currentCell;
                }
            }
            for (int j = 0; j < 3; j++) {
                bfs(board[j], jail, positionList.get(j));
            }

            int result = Integer.MAX_VALUE;
            for (int j = 0; j < row + 2; j++) {
                for (int k = 0; k < col + 2; k++) {
                    if (jail[j][k] != -1) {
                        int resultTemp = board[0][j][k] + board[1][j][k] + board[2][j][k];
                        if (jail[j][k] == 1) {
                            result = Math.min(result, resultTemp - 2);
                        } else if (jail[j][k] == 0) {
                            result = Math.min(result, resultTemp);
                        }
                    }
                }
            }
            System.out.println(result);
        }
    }

    private void bfs(int[][] board, int[][] jail, Position begin) {
        Queue<Position> q = new LinkedList<>();
        boolean[][] visited = new boolean[board.length][board[0].length];
        q.add(begin);
        if (jail[begin.row][begin.col] == 0) board[begin.row][begin.col] = 0;
        else board[begin.row][begin.col] = 1;

        while (!q.isEmpty()) {
            Position p = q.poll();
            for (int i = 0; i < 4; i++) {
                int nextRow = p.row + moveRow[i];
                int nextCol = p.col + moveCol[i];
                if (nextRow >= 0 && nextCol >= 0 && nextRow < jail.length && nextCol < jail[0].length) {
                    if (jail[nextRow][nextCol] != -1 && (visited[nextRow][nextCol] == false || board[p.row][p.col] + jail[nextRow][nextCol] <  board[nextRow][nextCol])) {
                        q.add(new Position(nextRow, nextCol));
                        board[nextRow][nextCol] = Math.min(board[p.row][p.col] + jail[nextRow][nextCol], board[nextRow][nextCol]);
                        visited[nextRow][nextCol] = true;
                    }
                }
            }
        }
    }

    class Position {
        public int row;
        public int col;
        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
