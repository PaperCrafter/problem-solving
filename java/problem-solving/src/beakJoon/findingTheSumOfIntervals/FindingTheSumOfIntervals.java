package beakJoon.findingTheSumOfIntervals;

import java.util.*;

public class FindingTheSumOfIntervals {
    private long[] tree = new long[4000001];
    public void solution() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int K = sc.nextInt();

        long[] arr = new long[N];
        for (int i = 0; i < N; i++) arr[i] = sc.nextInt();
        initTree(arr, 0, arr.length - 1, 1);
        for (int i = 0; i <  M + K;  i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            long c = sc.nextLong();
            if (a == 1) {
                changeValue(arr, 0, arr.length - 1, 1, b-1, c);
                arr[b-1] = c;
            } else {
                System.out.println(getSum(0, arr.length - 1, b - 1, (int)(c - 1), 1));
            }
        }
    }

    private long initTree(long[] arr, int start, int end, int treeIdx) {
        if (start == end) {
            return tree[treeIdx] = arr[start];
        }
        int mid = (start + end) / 2;
        return tree[treeIdx] = initTree(arr, start, mid, treeIdx * 2) + initTree(arr, mid + 1, end, treeIdx * 2 + 1);
    }

    private void changeValue(long[] arr, int start, int end, int treeIdx, int idx, long value) {
        if (start > idx || end < idx) return;
        if (start == end) {
            tree[treeIdx] = value;
            return;
        }
        int mid = (start + end) / 2;
        tree[treeIdx] -= arr[idx];
        tree[treeIdx] += value;
        if (mid >= idx) changeValue(arr, start, mid, treeIdx * 2, idx, value);
        else changeValue(arr, mid + 1, end, treeIdx * 2 + 1, idx, value);
    }

    private long getSum(int start, int end, int from, int to, int treeIdx) {
        if (from > end || start > to) return 0;
        if (from <= start && end <= to) return tree[treeIdx];
        int mid = (start + end) / 2;
        return getSum(start, mid, from, to, treeIdx * 2) + getSum(mid + 1, end, from, to, treeIdx * 2 + 1);
    }
}
