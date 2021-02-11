package beakJoon.exersise;

import java.util.*;

public class Exercise {
    final int INF_COST = 10001;
    public void solution() {
        Scanner scanner = new Scanner(System.in);
        int vertex = scanner.nextInt();
        int edge = scanner.nextInt();
        int[][] minimumDistance = new int[vertex + 1][vertex + 1];

        for (int i = 1; i <= vertex; i++) {
            for (int j = 1; j <= vertex; j++) {
                if (i == j) continue;
                minimumDistance[i][j] = INF_COST;
            }
        }

        for (int i = 0; i < edge; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int cost = scanner.nextInt();
            minimumDistance[from][to] = cost;
        }

        for (int i = 1; i <= vertex; i++) {
            for (int j = 1; j <= vertex; j++) {
                for (int k = 1; k <= vertex; k++) {
                    if (minimumDistance[i][j] > minimumDistance[i][k] + minimumDistance[k][j]) {
                        minimumDistance[i][j] = Math.min( minimumDistance[i][j], minimumDistance[i][k] + minimumDistance[k][j]);
                    }
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 1; i <= vertex; i++) {
            for (int j = 1; j <= vertex; j++) {
                if (minimumDistance[i][j] == 0 || minimumDistance[j][i] == 0 || minimumDistance[i][j] == INF_COST || minimumDistance[j][i] == INF_COST) continue;
                result = Math.min(result, minimumDistance[i][j] + minimumDistance[j][i]);
            }
        }

        if (result == Integer.MAX_VALUE) result = -1;
        System.out.println(result);
    }
}
