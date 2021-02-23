package beakJoon.stickingSticker;

import java.util.*;

public class StickingSticker {
    public void solution() {
        Scanner scanner = new Scanner(System.in);
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        int[][] board = new int[row][col];
        int numOfSticker = scanner.nextInt();
        for (int i = 0; i < numOfSticker; i++) {
            int stickerRow = scanner.nextInt();
            int stickerCol = scanner.nextInt();
            int[][] sticker = new int[stickerRow][stickerCol];
            for (int j = 0; j < stickerRow; j++) {
                for (int k = 0; k < stickerCol; k++) {
                    sticker[j][k] = scanner.nextInt();
                }
            }
            postSticker(board, new Sticker(stickerRow, stickerCol, sticker));
        }

        int result = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 1) result++;
            }
        }
        System.out.println(result);
        return;
    }

    private void postSticker(int[][] board, Sticker sticker) {
        for (int k = 0; k < 4; k++) {
            for (int i = 0; i < board.length - sticker.row + 1; i++) {
                for (int j = 0; j < board[0].length - sticker.col + 1; j++) {
                    if (stickable(board, sticker, i, j)) {
                        stick(board, sticker, i, j);
                        return;
                    }
                }
            }
            sticker.rotate();
        }
    }

    private boolean stickable(int[][] board, Sticker sticker, int row, int col) {
        for (int i = 0; i < sticker.row; i++) {
            for (int j = 0; j < sticker.col; j++) {
                if (board[i + row][j + col] == 1 && sticker.sticker[i][j] != 0) return false;
            }
        }
        return true;
    }

    private void stick(int[][] board, Sticker sticker, int row, int col) {
        for (int i = 0; i < sticker.row; i++) {
            for (int j = 0; j < sticker.col; j++) {
                if (sticker.sticker[i][j] == 1) board[i + row][j + col] = sticker.sticker[i][j];
            }
        }
    }

    class Sticker {
        int row;
        int col;
        int[][] sticker;

        public Sticker(int row, int col, int[][] sticker) {
            this.row = row;
            this.col = col;
            this.sticker = sticker;
        }

        public void rotate() {
            int[][] stickerCopy = new int[this.col][this.row];
            for (int i = 0; i < this.row; i++) {
                for (int j = 0; j < this.col; j++) {
                    stickerCopy[j][this.row - 1 - i] = sticker[i][j];
                }
            }
            int temp = this.row;
            this.row = this.col;
            this.col = temp;
            this.sticker = stickerCopy;
        }
    }
}
