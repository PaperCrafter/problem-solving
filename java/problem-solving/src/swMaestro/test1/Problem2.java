package swMaestro.test1;

import java.util.*;

public class Problem2 {
    public void solution() {
        Scanner sc = new Scanner(System.in);
        int p = sc.nextInt();
        int n = sc.nextInt();
        int h = sc.nextInt();
        List<Integer>[] pcs = new List[p + 1];
        for (int i = 0; i < pcs.length; i++) {
            pcs[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            int pcNo = sc.nextInt();
            int time = sc.nextInt();
            if (time <= h) {
                pcs[pcNo].add(time);
            }
        }

        for (int i = 1; i < pcs.length; i++) {
            Collections.sort(pcs[i]);
            List<Integer> currentList = pcs[i];
            int[] dp = new int[currentList.size()];
            for (int j = 0; j < currentList.size(); j++) {
                if (j == 0 && currentList.get(0) <= h) dp[0] = currentList.get(0);
                for (int k = 0; k < j; k++) {
                    int nextValue = currentList.get(j) + dp[k];
                    if (nextValue > dp[j] && nextValue <= h) {
                        dp[j] = nextValue;
                    }else {
                        if (currentList.get(j) <= h && dp[k] < currentList.get(j)) {
                            dp[j] = Math.max(dp[j], currentList.get(j));
                        } else {
                            dp[j] = Math.max(dp[j], dp[k]);
                        }
                    }

                }
            }
            int maxValue = 0;
            for (int j = 0; j < dp.length; j++) {
                maxValue = Math.max(dp[j], maxValue);
            }
            System.out.println(i + " " + maxValue * 1000);
        }
    }
}
