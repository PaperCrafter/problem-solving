package beakJoon.bakery;

import java.util.Scanner;

public class Bakery {
    int[] moveRow = {-1, 0, 1};
    int[] moveCol = {1, 1, 1};
    public void solution() {
        Scanner scanner = new Scanner(System.in);
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        char[][] board = new char[row][col];
        for (int i = 0; i < row; i++) {
            String currentString = scanner.next();
            for (int j = 0; j < col; j++) {
                board[i][j] = currentString.charAt(j);
            }
        }

        for (int i =0; i < row; i++) {
            if (board[i][0] != 'p' && board[i][0] != 'x') {
                dfs(board, i, 0);
            }
        }

        int result = 0;
        for (int i =0; i < row; i++) {
            if (board[i][0] == 'p') result++;
        }

        System.out.println(result);
    }

    private boolean dfs(char[][] board, int row, int col) {
        if (col == board[0].length - 1) {
            board[row][col] = 'p';
            return true;
        }

        int nextRow = 0;
        int nextCol = 0;
        for (int i = 0; i < 3; i++) {
            nextRow = row + moveRow[i];
            nextCol = col + moveCol[i];
            if (nextRow >= 0 && nextCol >= 0 && nextRow < board.length && nextCol < board[0].length) {
                if (board[nextRow][nextCol] == 'p' || board[nextRow][nextCol] == 'x' || board[nextRow][nextCol] == 'n') continue;
                if (dfs(board, nextRow, nextCol)) {
                    board[row][col] = 'p';
                    return true;
                } else {
                    board[row][col] = 'n';
                }
            }
        }
        return false;
    }
}
