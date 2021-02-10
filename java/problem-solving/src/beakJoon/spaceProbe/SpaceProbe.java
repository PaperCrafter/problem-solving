package beakJoon.spaceProbe;

import java.util.*;

public class SpaceProbe {
    public void solution() {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int start = scanner.nextInt();
        int[][] minimumDistance = new int[num][num];

        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                minimumDistance[i][j] = scanner.nextInt();
            }
        }

        //floyd-washal
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                for (int k = 0; k < num; k++) {
                    minimumDistance[i][j] = Math.min(minimumDistance[i][j], minimumDistance[i][k] + minimumDistance[k][j]);
                }
            }
        }
        boolean[] visited = new boolean[num];
        visited[start] = true;
        System.out.println(dfs(start, visited, minimumDistance, 0, 1));
    }

    private int dfs(int begin, boolean[] visited, int[][] dist, int sum, int depth) {
        if (dist.length == depth) {
            return sum;
        }
        int ret = Integer.MAX_VALUE;
        for (int i = 0; i < dist.length; i++) {
            if (visited[i]) continue;
            if (begin == i) continue;
            visited[i] = true;
            ret = Math.min(ret, dfs(i, visited, dist, sum + dist[begin][i], depth + 1));
            visited[i] = false;
        }
        return ret;
    }
}
