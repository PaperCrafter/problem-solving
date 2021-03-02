package beakJoon.z;

import java.util.*;

public class Z {
    public void solution() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int row = sc.nextInt();
        int col = sc.nextInt();
        System.out.println(divide(n, row, col, 0));
    }
    private int divide(int num, int row, int col, int result) {
        if (num == 0) return result;
        int currentDelimeter = (int) Math.pow(2, num-1);
        if (col < currentDelimeter && row < currentDelimeter) {
            return divide(num-1, row, col, result);
        }
        else if (col >= currentDelimeter && row < currentDelimeter) {
            return divide(num-1, row, col - currentDelimeter, result + currentDelimeter * currentDelimeter);
        }
        else if (col < currentDelimeter && row >= currentDelimeter) {
            return divide(num-1, row - currentDelimeter, col, result + currentDelimeter * currentDelimeter * 2);
        } else {
            return divide(num-1, row - currentDelimeter, col - currentDelimeter, result + currentDelimeter * currentDelimeter * 3);
        }
    }
}
