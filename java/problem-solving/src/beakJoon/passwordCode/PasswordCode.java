package beakJoon.passwordCode;

import java.util.*;

public class PasswordCode {
    private final int NUM_TO_DIV = 1000000;
    private int[] dp = new int[5001];
    public void solution() {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        if (str.charAt(0) == '0') {
            System.out.println(0);
            return;
        } else {
            dp[0] = 1;
            dp[1] = 1;
        }
        for (int i = 2; i <= str.length(); i++) {
            if (str.charAt(i - 1) == '0' && str.charAt(i - 2) == '0') {
                System.out.println(0);
                return;
            }
            if (str.charAt(i - 1) != '0') dp[i] += dp[i - 1];
            if (str.charAt(i - 2) != '0' && Integer.parseInt(str.substring(i - 2, i)) <= 26) {
                dp[i] += dp[i - 2];
            }
            dp[i] %= NUM_TO_DIV;
        }
        System.out.println(dp[str.length()]);
    }
}
