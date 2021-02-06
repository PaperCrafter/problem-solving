package beakJoon.swansLake;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SwansLake {
    int[] moveRow = {1, -1, 0, 0};
    int[] moveCol = {0, 0, 1, -1};
    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] rowAndColumn = br.readLine().split(" ");
        int row = Integer.parseInt(rowAndColumn[0]);
        int col = Integer.parseInt(rowAndColumn[1]);
        int[][] lake = new int[row][col];
        List<Swan> swans = new LinkedList<>();
        int numOfSwan = 1;
        for (int i = 0; i < row; i++) {
            String rowString = br.readLine();
            for (int j = 0; j < col; j++) {
                if (rowString.charAt(j) == '.') {
                    lake[i][j] = 0;
                } else if (rowString.charAt(j) == 'X') {
                    lake[i][j] = 1;
                } else {
                    swans.add(new Swan(numOfSwan++, i, j));
                    lake[i][j] = 0;
                }
            }
        }

        markTimingToMelt(lake);
        int result = optimization(lake, swans);
        System.out.println(result);
    }

    private void markTimingToMelt(int[][] lake) {
        Queue<Glacier> g = new LinkedList<>();
        boolean[][] visited = new boolean[lake.length][lake[0].length];
        for (int i = 0; i < lake.length; i++) {
            for (int j = 0; j < lake[0].length; j++) {
                if (lake[i][j] == 0) continue;
                for (int k = 0; k < 4; k++) {
                    int nextRow = i + moveRow[k];
                    int nextCol = j + moveCol[k];
                    if (nextRow >= 0 && nextCol >= 0 && nextRow < lake.length && nextCol < lake[0].length) {
                        if (lake[nextRow][nextCol] == 0) {
                            g.add(new Glacier(lake[i][j], i, j));
                            visited[i][j] = true;
                        }
                    }
                }
            }
        }

        while (!g.isEmpty()) {
            Glacier currentGlacier = g.poll();
            lake[currentGlacier.position.row][currentGlacier.position.col] = currentGlacier.meltTiming;

            for (int i = 0; i < 4; i++) {
                int nextRow = currentGlacier.position.row + moveRow[i];
                int nextCol = currentGlacier.position.col + moveCol[i];
                if (nextRow >= 0 && nextCol >= 0 && nextRow < lake.length && nextCol < lake[0].length) {
                    if (visited[nextRow][nextCol] == false && lake[nextRow][nextCol] != 0) {
                        g.add(new Glacier(currentGlacier.meltTiming + 1, nextRow, nextCol));
                        visited[nextRow][nextCol] = true;
                    }
                }
            }
        }
    }

    private int optimization(int[][] lake, List<Swan> swans) {
        int min = 0;
        int max = 0;
        int result = 0;
        for (int i = 0; i < lake.length; i++) {
            for (int j = 0; j < lake[0].length; j++) {
                max = Math.max(max, lake[i][j]);
            }
        }

        while (min <= max) {
            int mid = (min + max) / 2;
            if (decision(lake, swans, mid)) {
                result = mid;
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return result;
    }

    private boolean decision(int[][] lake, List<Swan> swans, int limit) {
        Queue<Position> q = new LinkedList<>();
        boolean[][] visited = new boolean[lake.length][lake[0].length];
        Swan swanFrom = swans.get(0);
        Swan swanTo = swans.get(1);
        q.add(new Position(swanFrom.position.row, swanFrom.position.col));
        visited[swanFrom.position.row][swanFrom.position.col] = true;

        while (!q.isEmpty()) {
            Position currentPosition = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextRow = currentPosition.row + moveRow[i];
                int nextCol = currentPosition.col + moveCol[i];
                if (nextRow >= 0 && nextCol >= 0 && nextRow < lake.length && nextCol < lake[0].length) {
                    if (lake[nextRow][nextCol] <= limit) {
                        if (swanTo.position.row == nextRow && swanTo.position.col == nextCol) return true;
                        if (visited[nextRow][nextCol] == false) {
                            q.add(new Position(nextRow, nextCol));
                            visited[nextRow][nextCol] = true;
                        }
                    }
                }
            }
        }
        return false;
    }

    class Swan {
        public int swanNo;
        public Position position;

        public Swan(int swanNo, int row, int col) {
            this.swanNo = swanNo;
            this.position = new Position(row, col);
        }
    }

    class Glacier {
        public int meltTiming;
        public Position position;

        public Glacier(int meltTiming, int row, int col) {
            this.meltTiming = meltTiming;
            this.position = new Position(row, col);
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
