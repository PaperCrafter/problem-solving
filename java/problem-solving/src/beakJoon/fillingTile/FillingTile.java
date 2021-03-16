package beakJoon.fillingTile;

import java.util.*;

public class FillingTile {
    private final long TILE_NUM_BOUNDARY =  2_147_483_647;
    public void solution() {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        long[] dp = new long[1000];
        dp[1] = 1;
        dp[2] = 5;
        dp[3] = 11;
        int i = 4;
        long nextBoundary = 0;
        while (nextBoundary < TILE_NUM_BOUNDARY) {
            dp[i] = dp[i-1] + 4 * dp[i - 2];
//            for () {
//
//            }
//            nextBoundary = dp[0][i] + dp[1][i] + dp[2][i];
            i++;
        }
        for (int j = 0; j < tc; j++) {
            int idx = sc.nextInt();
//            System.out.print(dp[0][idx] + dp[1][idx] + dp[2][idx]);
        }
    }
}
