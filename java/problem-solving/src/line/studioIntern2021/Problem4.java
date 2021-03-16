package line.studioIntern2021;

public class Problem4 {
    int[] moveXEven = {-1, 0, 1, 0, -1, -1};
    int[] moveYEven = {-1, -1 , 0, 1, 1, 0};
    int[] moveXOdd = {0, 1, 1, 1, 0, -1};
    int[] moveYOdd = {-1, -1, 0, 1, 1, 0};

    public int[] solution(String[] board, int[][] choices) {
        int[][] boardInt = new int[board.length][board[0].length()];

        Position cat;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length(); j++) {
                char currentCell = board[i].charAt(j);
                boardInt[i][j] = currentCell;
                if (currentCell == 'c') {
                    cat = new Position(j, i);
                }
            }
        }
        int[] answer = {};
        return answer;
    }

    class Position {
        int posX;
        int posY;

        public Position(int posX, int posY){
            this.posX = posX;
            this.posY = posY;
        }
    }
}
