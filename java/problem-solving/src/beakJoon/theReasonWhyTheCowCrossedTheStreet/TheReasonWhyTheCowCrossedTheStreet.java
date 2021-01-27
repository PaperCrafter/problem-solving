package beakJoon.theReasonWhyTheCowCrossedTheStreet;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class TheReasonWhyTheCowCrossedTheStreet {
    int[] moveX = {1, -1, 0, 0};
    int[] moveY = {0, 0, 1, -1};
    public void solution() {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int K = scanner.nextInt();
        int R = scanner.nextInt();

        Set<Path> paths = new HashSet<>();
        for (int i = 0; i < R; i++) {
            int r1 = scanner.nextInt();
            int c1 = scanner.nextInt();
            int r2 = scanner.nextInt();
            int c2 = scanner.nextInt();
            paths.add(new Path(r1, c1, r2, c2));
            paths.add(new Path(r2, c2, r1, c1));
        }

        int[][] board = new int[N + 2][N + 2];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (i == 0 || j == 0 || i == N + 1 || j == N + 1) {
                    board[i][j] = -1;
                } else {
                    board[i][j] = 0;
                }
            }
        }

        int sectorIdx = 1;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 0) {
                    dfs(board, j, i, paths, sectorIdx);
                    sectorIdx++;
                }
            }
        }

        int[] cowsPerSector = new int[sectorIdx];
        for (int i = 0; i < K; i++) {
            int r = scanner.nextInt();
            int c = scanner.nextInt();
            cowsPerSector[board[r][c]]++;
        }

        int total = 0;
        for (int i = 1; i < sectorIdx; i++) {
            for (int j = i + 1; j < sectorIdx; j++) {
                total += cowsPerSector[i] * cowsPerSector[j];
            }
        }

        System.out.println(total);
    }

    private void dfs(int[][] board, int col, int row, Set<Path> paths, int currentLandIdx) {
        if (board[row][col] == -1) return;
        if (board[row][col] != 0) return;
        int nextCol = 0;
        int nextRow = 0;
        board[row][col] = currentLandIdx;
        for (int i = 0; i < 4; i++) {
            nextCol = col + moveX[i];
            nextRow = row + moveY[i];
            if (!paths.contains(new Path(row, col, nextRow, nextCol))){
                dfs(board, nextCol, nextRow, paths, currentLandIdx);
            }
        }
    }

    class Path {
        int row1;
        int col1;
        int row2;
        int col2;

        public Path(int row1, int col1, int row2, int col2) {
            this.row1 = row1;
            this.col1 = col1;
            this.row2 = row2;
            this.col2 = col2;
        }

        @Override
        public int hashCode() {
            int hashCode = this.row1;
            hashCode += hashCode * 13 + this.col1;
            hashCode += hashCode * 13 + this.row2;
            hashCode += hashCode * 13 +this.col2;
            return hashCode;
        }

        @Override
        public boolean equals(Object obj) {
            Path p = (Path) obj;
            return (row1 == p.row1) && (row2 == p.row2)
                    && (col1 == p.col1) && (col2 == p.col2);
        }
    }
}
