package beakJoon.bridging;

import java.util.*;

public class Bridging {
    int dp[][] = new int[31][31];
    public void solution() {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        for (int i = 0; i < tc; i++) {
            int left = sc.nextInt();
            int right = sc.nextInt();
            System.out.println(combination(right, left));
        }
    }

    private int combination(int n, int r) {
        if (r == 0) return 1;
        else if (r == 1) return n;
        else if (n == r) return 1;
        if (dp[n][r] != 0) return dp[n][r];
        return dp[n][r] = combination(n-1, r-1) + combination(n-1, r);
    }
}
