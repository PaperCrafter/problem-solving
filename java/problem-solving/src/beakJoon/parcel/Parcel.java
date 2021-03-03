package beakJoon.parcel;

import java.util.*;

public class Parcel {
    public void solution() {
        Scanner sc = new Scanner(System.in);
        int weight = sc.nextInt();
        int size = sc.nextInt();
        int[] arr = new int[size + 1];
        for (int i = 0; i < size; i++) {
            arr[i] = sc.nextInt();
        }

        boolean[] sum = new boolean[400001];
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                int currentSum = arr[i] + arr[j];
                if (currentSum >= weight || weight - currentSum > 400000) continue;
                if (sum[weight - currentSum]) {
                    System.out.print("YES");
                    return;
                }
            }

            for (int j = 0; j < i; j++) {
                sum[arr[i] + arr[j]] = true;
            }
        }
        System.out.print("NO");
    }
}
