package programmers.kakaoBlind2021.matchingCards;

import java.util.*;
import java.util.stream.Collectors;

public class MatchingCards {
    public int solution(int[][] board, int row, int col) {
        List<Integer> list = Arrays.stream(board)
                .flatMapToInt(Arrays::stream)
                .filter(item -> item != 0)
                .distinct()
                .boxed()
                .collect(Collectors.toList());
        Map<Integer, List<Position>> positionMap = new HashMap<>();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int currentItem = board[i][j];
                if (currentItem != 0) {
                    List<Position> lst = positionMap.getOrDefault(currentItem, new ArrayList<>());
                    lst.add(new Position(i, j));
                    positionMap.put(currentItem, lst);
                }
            }
        }

        List<List<Integer>> results = new ArrayList<>();
        getPermutations(list, 0, results);
        int res = Integer.MAX_VALUE;
        for (List<Integer> order : results) {
            int[][] boardCopy = new int[board.length][board[0].length];
            for (int i = 0; i < board.length; i++) {
                boardCopy[i] = Arrays.copyOf(board[i], board[i].length);
            }
            res = Math.min(res, dfs(boardCopy, order, 0, positionMap, row, col, 0));
        }
        return res;
    }

    private void getPermutations(List<Integer> listToPermute, int index, List<List<Integer>> results){
        if(index == listToPermute.size()-1){
            results.add(listToPermute.stream().collect(Collectors.toList()));
            return;
        }
        for(int i = index; i < listToPermute.size(); i++){
            Collections.swap(listToPermute, index, i);
            getPermutations(listToPermute, index+1, results);
            Collections.swap(listToPermute, i, index);
        }
    }

    private int dfs(int[][] board, List<Integer> order, int currentIdx, Map<Integer, List<Position>> positionMap, int row, int col, int result) {
        if (currentIdx == order.size()) return result;

        int tempResult = Integer.MAX_VALUE;
        int currentItem = order.get(currentIdx);
        List<Position> currentList = positionMap.get(currentItem);
        for (int i = 0; i < currentList.size(); i++) {
            Position from;
            Position to;
            if (i == 0) {
                from = currentList.get(0);
                to = currentList.get(1);
            }
            else {
                to = currentList.get(0);
                from = currentList.get(1);
            }

            int temp = 2;
            temp += getMinimumKeyCount(board, new Position(row, col), from);
            temp += getMinimumKeyCount(board, from ,to);
            board[from.row][from.col] = 0;
            board[to.row][to.col] = 0;
            tempResult = Math.min(tempResult, dfs(board, order, currentIdx + 1, positionMap, to.row, to.col, result + temp));
            board[from.row][from.col] = 1;
            board[to.row][to.col] = 1;
        }
        return tempResult;
    }

    int[] moveRow = {1, -1, 0, 0};
    int[] moveCol = {0, 0, 1, -1};
    private int getMinimumKeyCount(int[][] board, Position p1, Position p2){
        Queue<Cursor> cursorQueue = new LinkedList<>();
        boolean[][] visited = new boolean[board.length][board[0].length];
        visited[p1.row][p1.col] = true;
        cursorQueue.add(new Cursor(p1, 0));
        while (!cursorQueue.isEmpty()) {
            Cursor currentCursor = cursorQueue.poll();
            if (currentCursor.p.equals(p2)) {
                return currentCursor.count;
            }

            for (int i = 0; i < 4; i++) {
                int nextRow = currentCursor.p.row + moveRow[i];
                int nextCol = currentCursor.p.col + moveCol[i];
                if (nextRow >= 0 && nextCol >= 0 && nextRow < board.length && nextCol < board[0].length) {
                    if (visited[nextRow][nextCol] == false) {
                        visited[nextRow][nextCol] = true;
                        cursorQueue.add(new Cursor(new Position(nextRow, nextCol), currentCursor.count + 1));
                    }

                    while (nextRow + moveRow[i] >= 0 && nextCol + moveCol[i]>= 0 && nextRow + moveRow[i] < board.length && nextCol + moveCol[i] < board[0].length
                            && board[nextRow][nextCol] == 0) {
                        nextRow += moveRow[i];
                        nextCol += moveCol[i];
                    }

                    if (visited[nextRow][nextCol] == false) {
                        visited[nextRow][nextCol] = true;
                        cursorQueue.add(new Cursor(new Position(nextRow, nextCol), currentCursor.count + 1));
                    }
                }
            }
        }
        return 0;
    }


    class Cursor {
        Position p;
        int count;

        public Cursor(Position p, int count) {
            this.p = p;
            this.count = count;
        }
    }

    class Position {
        int row;
        int col;

        public Position (int row, int col){
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object obj) {
            Position p = (Position) obj;
            return p.col == this.col && p.row == this.row;
        }
    }
}
