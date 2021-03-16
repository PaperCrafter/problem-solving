package beakJoon.matrixSquared;

import java.util.Arrays;
import java.util.Scanner;

public class MatrixSquared {
    public void solution() {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int B = scanner.nextInt();


    }

    public int[][] multiplyMatrix(int[][] A, int num) {

        int[][] matrix = new int[A.length][A[0].length];
        for (int i = 0; i < A.length; i++) {
            matrix[i] = Arrays.copyOf(A[i], A.length);
        }

        int[][] result = new int[A.length][A[0].length];
        for (int i = 0; i < Math.log(num); i++) {

        }
        return result;
    }
}
