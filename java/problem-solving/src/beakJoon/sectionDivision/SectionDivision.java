package beakJoon.sectionDivision;

import java.util.Arrays;
import java.util.Scanner;

// 백준 2228 구간 나누기
public class SectionDivision {
    public void solution() {
        Scanner scanner = new Scanner(System.in);
        int M = scanner.nextInt();
        int N = scanner.nextInt();
        int[] arr = new int[M];
        int[][] dp = new int[N+1][M+1];

        for (int i = 0; i < M; i++) {
            arr[i] = scanner.nextInt();
        }

        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        }

        for (int i = 0; i < N; i++) {
            //끝 : j
            for (int j = 2 * i; j < M; j++) {
                // 중간값 : k
                if (j != 0) {
                    dp[i][j] = dp[i][j -1];
                }
                for (int k = 2 * i; k <= j; k++) {
                    int partialSum = 0;
                    for (int l = k; l <= j; l++) {
                        partialSum += arr[l];
                    }
                    if (i == 0) {
                        dp[i][j] = Math.max(dp[i][j], partialSum);
                    } else {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][k - 2] + partialSum);
                    }
                }
            }
        }

        int result = Integer.MIN_VALUE;
        for (int i = 0; i < M; i++) {
            result = Math.max(result, dp[N - 1][i]);
        }

        System.out.println(result);
    }
}
/*
* 구간 1개의 최댓값
* -1 3 4 6 10 10
* 구간 2개의 최댓값
*      0 3 9  9
* 구간 3개의 최댓값
*
* */