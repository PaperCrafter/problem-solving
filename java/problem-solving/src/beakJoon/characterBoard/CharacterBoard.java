package beakJoon.characterBoard;

import java.util.Arrays;
import java.util.Scanner;

public class CharacterBoard {
    int[] moveRow = {1, -1, 0, 0};
    int[] moveCol = {0, 0, 1, -1};
    int[][][] dp = new int[100][100][80];
    public void solution() {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        Scanner scanner = new Scanner(System.in);
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        int boundary = scanner.nextInt();
        char[][] board = new char[row][col];
        for (int i = 0; i < row; i++) {
            String currentRow = scanner.next();
            for (int j = 0; j < col; j++) {
                board[i][j] = currentRow.charAt(j);
            }
        }
        String word = scanner.next();
        int result = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == word.charAt(0)) {
                    result += dfs(board, i, j, word, 0, boundary);
                }
            }
        }
        System.out.println(result);
    }

    int dfs(char[][] board, int row, int col, String word, int index, int boundary) {
        if (word.length() == index + 1) return 1;
        if (dp[row][col][index] != -1) return dp[row][col][index];

        dp[row][col][index] = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= boundary; j++) {
                int nextRow = row + moveRow[i] * j;
                int nextCol = col + moveCol[i] * j;
                int nextIndex = index + 1;
                if (nextRow < 0 || nextCol < 0 || nextRow >= board.length || nextCol >= board[0].length) continue;
                if (board[nextRow][nextCol] == word.charAt(nextIndex)) {
                    dp[row][col][index] += dfs(board, nextRow, nextCol, word, nextIndex, boundary);
                }
            }
        }
        return dp[row][col][index];
    }
}
