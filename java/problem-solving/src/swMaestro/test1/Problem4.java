package swMaestro.test1;

import java.util.*;

public class Problem4 {
    public void solution() {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int[] board = new int[num];
        for (int i = 0; i < num; i++) {
            board[i] = sc.nextInt();
        }

        int maxValue = 0;
        for (int i = 0; i < 3; i++) {
            int start = i;
            boolean[] visited = new boolean[num];
            maxValue = Math.max(maxValue, dfs(board, start, visited, 1));
        }
        System.out.println(maxValue);
    }

    public int dfs(int[] board, int start, boolean[] visited, int iteration) {
        if (visited[start] == true) {
            return iteration;
        }
        visited[start] = true;
        return dfs(board, start + board[start], visited, iteration + 1);
    }
}
