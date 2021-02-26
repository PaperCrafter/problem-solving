package beakJoon.sns;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class SNS {
    public void solution() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] dp = new int[N + 1][2];
        boolean[] visited = new boolean[N + 1];
        List<Integer>[] tree = new List[N + 1];
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new LinkedList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            tree[from].add(to);
            tree[to].add(from);
        }
        dfs(tree, 1, dp, visited);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    private void dfs(List<Integer>[] tree, int currentNode, int[][] dp, boolean[] visited) {
        visited[currentNode] = true;
        dp[currentNode][0] = 0;
        dp[currentNode][1] = 1;
        List<Integer> currentAdjNodes = tree[currentNode];
        for (int i = 0; i < currentAdjNodes.size(); i++) {
            int nextNode = currentAdjNodes.get(i);
            if (visited[nextNode] == false) {
                dfs(tree, nextNode, dp, visited);
                dp[currentNode][0] += dp[nextNode][1];
                dp[currentNode][1] += Math.min(dp[nextNode][0], dp[nextNode][1]);
            }
        }
    }
}
