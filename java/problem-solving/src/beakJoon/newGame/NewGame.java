package beakJoon.newGame;

import java.util.*;

public class NewGame {
    public void solution() {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int K = scanner.nextInt();
        int[][] board = new int[N+2][N+2];
        for (int i = 0; i < board.length; i++) {
            Arrays.fill(board[i], 2);
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                board[i][j] = scanner.nextInt();
            }
        }

        List<Pieces> piecesList = new ArrayList<>();
        for (int i = 1; i <= K; i++) {
            piecesList.add(new Pieces(scanner.nextInt(), scanner.nextInt(), i, scanner.nextInt()));
        }

        int step = 0;
        while(piecesList.size() != 1 || step > 1000) {

            for (int i = 1; i <= K; i++) {

            }

//            piecesList.
        }
    }

    class Pieces {
        int row;
        int col;
        Stack<Piece> pieces;
        Piece bottom;

        public Pieces(int row, int col, int num, int dir) {
            this.row = row;
            this.col = col;

            Piece p = new Piece(num, dir);
            pieces.push(p);
            bottom = p;
        }

        public void move(int nextCell, int prevCell) {
            if (nextCell == Cell.BLUE.cellNo)  {
                rotateDir();
            }
            if (prevCell != Cell.BLUE.cellNo) {
                if (this.bottom.dir == Direction.UP.dirNum) {
                    this.row -= 1;
                } else if (this.bottom.dir == Direction.DOWN.dirNum) {
                    this.row += 1;
                } else if (this.bottom.dir == Direction.LEFT.dirNum) {
                    this.col -= 1;
                } else {
                    this.col += 1;
                }
            }
            check(nextCell);
        }

        public void rotateDir() {
            if (this.bottom.dir == Direction.UP.dirNum) {
                this.bottom.dir = Direction.DOWN.dirNum;
            } else if (this.bottom.dir == Direction.DOWN.dirNum) {
                this.bottom.dir = Direction.UP.dirNum;
            } else if (this.bottom.dir == Direction.LEFT.dirNum) {
                this.bottom.dir = Direction.RIGHT.dirNum;
            } else {
                this.bottom.dir = Direction.LEFT.dirNum;
            }
        }

        public void check(int cellNo) {
            if (cellNo == Cell.RED.cellNo) {
                Stack<Piece> stackNew = new Stack<>();
                while (!this.pieces.isEmpty()) {
                    stackNew.push(this.pieces.pop());
                }
                this.pieces = stackNew;
            }
        }

        public void addPieces(Pieces pieces) {
            this.pieces.addAll(pieces.pieces);
        }
    }

    class Piece {
        int num;
        int dir;

        public Piece(int num, int dir) {
            this.num = num;
            this.dir = dir;
        }
    }

    enum Cell {
        WHITE(0),
        RED(1),
        BLUE(2);

        int cellNo;
        Cell(int cellNo) {
            this.cellNo = cellNo;
        }
    }

    enum Direction {
        LEFT(1),
        RIGHT(2),
        UP(3),
        DOWN(4);

        int dirNum;
        Direction(int dirNum) {
            this.dirNum = dirNum;
        }
    }
}
