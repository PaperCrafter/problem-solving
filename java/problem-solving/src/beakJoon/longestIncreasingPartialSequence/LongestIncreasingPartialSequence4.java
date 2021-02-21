package beakJoon.longestIncreasingPartialSequence;

import java.util.*;

public class LongestIncreasingPartialSequence4 {
    public void solution() {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int[] numbers = new int[num];
        int[] dp = new int[num];
        int[] from = new int[num];
        Arrays.fill(from, -1);
        for (int i = 0; i < num; i++) {
            numbers[i] = scanner.nextInt();
        }

        int maxNum = 0;
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < i; j++) {
                if (numbers[i] > numbers[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    if (dp[i] == dp[j] + 1) {
                        from[i] = j;
                    }
                    maxNum = Math.max(maxNum, dp[i]);
                }
            }
        }

        Stack<Integer> stack = new Stack<>();
        for (int i = num - 1; i >= 0; i--) {
            if (maxNum == dp[i]) {
                int nextNum = i;
                while (true) {
                    if (nextNum == -1) {
                        break;
                    }
                    stack.push(nextNum);
                    nextNum = from[nextNum];
                }
            }
            if (!stack.isEmpty()) break;
        }
        System.out.println(stack.size());
        for (int i = 0; i < maxNum + 1; i++) {
            System.out.print(numbers[stack.pop()] + " ");
        }
    }
}
