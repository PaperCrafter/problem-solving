package beakJoon.castle;

import java.util.*;

public class Castle {
    int[] moveRow = {0, -1, 0, 1};
    int[] moveCol = {-1, 0, 1, 0};
    public void solution() {
        Scanner scanner = new Scanner(System.in);
        int col = scanner.nextInt();
        int row = scanner.nextInt();
        int[][] castle = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                castle[i][j] = scanner.nextInt();
            }
        }

        int[][] visited = new int[row][col];
        int castleNo = 1;
        int maxSize = 0;
        int[] castles = new int[2501];
        Map<Integer, List<Integer>> adjacentMap = new HashMap<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (visited[i][j] == 0) {
                    castles[castleNo] = dfs(castle, i, j, visited, castleNo, adjacentMap);
                    maxSize = Math.max(maxSize, castles[castleNo]);
                    castleNo++;
                }
            }
        }
        System.out.println(castleNo - 1);
        System.out.println(maxSize);
        int maxSizeWhenWallBroken = 0;
        for (Map.Entry<Integer, List<Integer>> e : adjacentMap.entrySet()) {
            for (int wall : e.getValue()) {
                maxSizeWhenWallBroken = Math.max(maxSizeWhenWallBroken, castles[e.getKey()] + castles[wall]);
            }
        }
        System.out.println(maxSizeWhenWallBroken);
    }

    private int dfs(int[][] castle, int row, int col, int[][] visited, int num, Map<Integer, List<Integer>> adjacentMap) {
        int total = 1;
        visited[row][col] = num;
        for (int i = 0; i < 4; i++) {
            int nextRow = row + moveRow[i];
            int nextCol = col + moveCol[i];
            if (nextRow >= 0 && nextCol >= 0 && nextRow < castle.length && nextCol < castle[0].length) {
                if (visited[nextRow][nextCol] == 0) {
                    if ((castle[row][col] & (int) Math.pow(2, i)) == 0) {
                        visited[nextRow][nextCol] = num;
                        total += dfs(castle, nextRow, nextCol, visited, num, adjacentMap);
                    }
                } else {
                    if (num != visited[nextRow][nextCol]) {
                        List<Integer> adjList = adjacentMap.getOrDefault(num, new ArrayList<>());
                        if (adjList.contains(visited[nextRow][nextCol]) == false) {
                            adjList.add(visited[nextRow][nextCol]);
                        }
                        adjacentMap.put(num, adjList);
                    }
                }
            }
        }
        return total;
    }
}
