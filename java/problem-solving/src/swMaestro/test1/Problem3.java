package swMaestro.test1;

import java.util.*;

public class Problem3 {
    public void solution() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int E = sc.nextInt();
        int[] beans = new int[N];
        for (int i = 0; i < N; i++) {
            beans[i] = sc.nextInt();
        }

        int minVal = Integer.MAX_VALUE;
        for (int i = 0; i < N - (M - 1); i++) {
            int idxLo = i;
            int idxHi = i + (M - 1);
            if (beans[idxLo] <= E && E <= beans[idxHi]) {
                minVal = Math.min(minVal, beans[idxHi] - beans[idxLo]);
            } else if (beans[idxHi] < E) {
                minVal = Math.min(minVal, (E - beans[idxLo]));
            } else {
                minVal = Math.min(minVal, (beans[idxHi]) - E);
            }
        }
        System.out.println(minVal);
    }
}
