package beakJoon.mineral;

import java.util.*;

public class Mineral {
    public void solution() {
        Scanner scanner = new Scanner(System.in);
        int r = scanner.nextInt();
        int c = scanner.nextInt();
        int[][] cave = new int[r][c];
        for (int i = 0; i < r; i++) {
            String currentRow = scanner.next();
            for (int j = 0; j < c; j++) {
                if (currentRow.charAt(j) == 'x') {
                    cave[i][j] = 1;
                } else {
                    cave[i][j] = 0;
                }
            }
        }

        int num = scanner.nextInt();
        boolean isFromLeft = true;
        boolean[][] visited = new boolean[r][c];
        for (int i = 0; i < num; i++) {
            int rowToBreak = scanner.nextInt();
            //부숨
            removeBlock(cave, rowToBreak, isFromLeft);
            isFromLeft = !isFromLeft;
            //클러스터 구분
            int movedBlockNum = 0;
            int nextClusterNum = 1;
            for (int j = 0; j < r; j++) {
                for (int k = 0; k < c; k++) {
                    if (visited[j][k] == false && cave[j][k] > 0) {
                        makeCluster(cave, j, k, visited, nextClusterNum++);
                    }
                }
            }
            do {
            //두 클러스터 사이에 공간이 없으면, 아래로 아래로 내릴 수 없음 (두 섹터 사이에 공간이 있을 경우에 한칸씩 내림)
                if (isMovable(cave) == false) break;
                for (int j = 0; j < visited.length; j++) {
                    Arrays.fill(visited[j], false);
                }
                Set<Integer> clusterFromBottom = clustersFromBottom(cave);
                movedBlockNum = moveDownOneCell(cave, clusterFromBottom);
            } while (movedBlockNum > 0);
        }
        printCave(cave);
    }


    private int pivot(int row, int size) {
        return size - row;
    }

    private void removeBlock(int[][] cave, int row, boolean isFromLeft) {
        int rowInverted = pivot(row, cave.length);
        if (isFromLeft == true) {
            for (int i = 0; i < cave[pivot(row, cave.length)].length; i++) {
                if (cave[rowInverted][i] != 0) {
                    cave[rowInverted][i] = 0;
                    break;
                }
            }
        } else {
            for (int i = cave[pivot(row, cave.length)].length - 1; i >= 0; i--) {
                if (cave[rowInverted][i] != 0) {
                    cave[rowInverted][i] = 0;
                    break;
                }
            }
        }
    }

    int[] moveRow = {1, -1, 0, 0};
    int[] moveCol = {0, 0, 1, -1};
    private void makeCluster(int[][] cave, int row, int col, boolean[][] visited, int currentClusterNum) {
        if (visited[row][col] == true) return;
        if (cave[row][col] == 0) return;
        cave[row][col] = currentClusterNum;
        visited[row][col] = true;

        for (int i = 0; i < 4; i++) {
            int nextRow = row + moveRow[i];
            int nextCol = col + moveCol[i];
            if (nextRow >= 0 && nextCol >= 0 && nextRow < cave.length && nextCol < cave[0].length && visited[nextRow][nextCol] == false) {
                makeCluster(cave, nextRow, nextCol, visited, currentClusterNum);
            }
        }
    }

    private boolean isMovable(int[][] cave) {
        for (int i = cave.length - 2; i >= 0; i--) {
            for (int j = 0; j < cave[0].length; j++) {
                if (cave[i + 1][j] != 0 && cave[i][j] != 0 && cave[i][j] != cave[i + 1][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private int moveDownOneCell(int[][] cave, Set<Integer> sectionFromBottom) {
        int movedBlockNum = 0;
        for (int i = cave.length - 2; i >= 0; i--) {
            for (int j = 0; j < cave[0].length; j++) {
                if (cave[i][j] != 0 && !sectionFromBottom.contains(cave[i][j])) {
                    cave[i + 1][j] = cave[i][j];
                    cave[i][j] = 0;
                    movedBlockNum++;
                }
            }
        }
        return movedBlockNum;
    }

    private Set<Integer> clustersFromBottom(int[][] cave) {
        Set<Integer> clusterSet = new HashSet<>();
        for (int i = 0; i < cave[0].length; i++) {
            int currentCell = cave[cave.length - 1][i];
            if (currentCell != 0) clusterSet.add(currentCell);
        }
        return clusterSet;
    }

    private void printCave(int[][] cave) {
        for (int i = 0; i < cave.length; i++) {
            for (int j = 0; j < cave[0].length; j++) {
                if (cave[i][j] == 0) {
                    System.out.print('.');
                } else {
                    System.out.print('x');
                }
            }
            System.out.println();
        }
    }
}
