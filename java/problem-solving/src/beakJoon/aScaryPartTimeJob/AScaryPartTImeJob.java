package beakJoon.aScaryPartTimeJob;

import java.util.*;

public class AScaryPartTImeJob {
    int[] tree = new int[400001];
    public void solution() {
        Scanner sc = new Scanner(System.in);
        int length = sc.nextInt();
        int[] arr = new int[length + 1];
        for (int i = 0; i < length; i++) {
            arr[i] = sc.nextInt();
        }
        init(arr, 1, 0, length - 1);
        System.out.println(solve(arr, 0, length - 1, length));
    }

    private int init(int[] arr, int node, int start, int end) {
        if (start == end) return tree[node] = start;
        int mid = (start + end) / 2;
        int idxMinLeft = init(arr, node * 2, start, mid);
        int idxMinRight = init(arr, node * 2 + 1, mid + 1, end);
        if (arr[idxMinLeft] <= arr[idxMinRight]) {
            return tree[node] = idxMinLeft;
        } else {
            return tree[node] = idxMinRight;
        }
    }

    private int getMinIdx(int[] arr, int node, int start, int end, int left, int right) {
        if (end < left || right < start) return -1;
        if (left <= start && end <= right) return tree[node];
        int mid = (start + end) / 2;
        int minIdxLeft = getMinIdx(arr, node * 2, start, mid, left, right);
        int minIdxRight = getMinIdx(arr, node * 2 + 1, mid + 1, end, left, right);
        if (minIdxLeft == -1) return minIdxRight;
        else if (minIdxRight == -1) return minIdxLeft;
        else {
            if (arr[minIdxLeft] <= arr[minIdxRight]) return minIdxLeft;
            else return minIdxRight;
        }
    }

    private long solve(int[] arr, int left, int right, int num) {
        int minIdx = getMinIdx(arr, 1, 0, num - 1, left, right);
        long result = (long) (right - left + 1) * (long) (arr[minIdx]);
        if (left <= minIdx - 1) {
            result = Math.max(result, solve(arr, left, minIdx - 1, num));
        }
        if (minIdx + 1 <= right) {
            result = Math.max(result, solve(arr, minIdx + 1, right, num));
        }
        return result;
    }
}
