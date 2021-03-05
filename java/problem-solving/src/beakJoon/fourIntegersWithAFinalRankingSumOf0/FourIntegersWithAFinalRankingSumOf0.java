package beakJoon.fourIntegersWithAFinalRankingSumOf0;

import com.sun.javafx.collections.ArrayListenerHelper;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class FourIntegersWithAFinalRankingSumOf0 {
    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        int[][] arr = new int[size][4];
        for (int i = 0; i < size; i++) {
            String[] intArr = br.readLine().split(" ");
            for (int j = 0; j < 4; j++) {
                arr[i][j] = Integer.parseInt(intArr[j]);
            }
        }

        int[] sumOfTwoLeft = new int[size * size];
        int[] sumOfTwoRight = new int[size * size];
        int idx = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sumOfTwoLeft[idx] = arr[i][0] + arr[j][1];
                sumOfTwoRight[idx] = arr[i][2] + arr[j][3];
                idx++;
            }
        }

        Arrays.sort(sumOfTwoLeft);
        Arrays.sort(sumOfTwoRight);

        long result = 0;
        int left = 0;
        int right = sumOfTwoRight.length - 1;
        while (right >= 0 && left < sumOfTwoLeft.length) {
            int sumLeft = sumOfTwoLeft[left];
            int sumRight = sumOfTwoRight[right];
            if (sumLeft + sumRight == 0) {
                long leftCounter = 0;
                long rightCounter = 0;
                while (left < sumOfTwoLeft.length && sumOfTwoLeft[left] == sumLeft) {
                    leftCounter++;
                    left++;
                }
                while (right >= 0 && sumOfTwoRight[right] == sumRight) {
                    rightCounter++;
                    right--;
                }
                result += leftCounter * rightCounter;
            } else if (sumLeft + sumRight < 0) {
                left++;
            } else {
                right--;
            }
        }
        System.out.println(result);
    }
}
