package beakJoon.addParentheses3;

import java.util.Arrays;
import java.util.Scanner;

public class AddParentheses3 {
    long[][] dpMax = new long[12][12];
    long[][] dpMin = new long[12][12];
    public void solution() {
        Scanner sc = new Scanner(System.in);
        int length = Integer.parseInt(sc.next());
        String expression = sc.next();
        int[] operands = new int[length/ 2 + 1];
        char[] operators = new char[length / 2];
        for (int i = 0; i < dpMax.length; i++) {
            Arrays.fill(dpMax[i], Long.MIN_VALUE);
            Arrays.fill(dpMin[i], Long.MAX_VALUE);
        }

        for (int i = 0; i < length; i++) {
            if(expression.charAt(i) >= '0' && expression.charAt(i) <= '9') {
                operands[i/2] = expression.charAt(i) - '0';
            } else {
                operators[i/2] = expression.charAt(i);
            }
        }

        //갯수
        for (int num = 0; num < length / 2 + 1; num++) {
            //시작
            for (int begin = 0; begin < length / 2 + 1; begin++) {
                int end = Math.min(num + begin, length /2);
                //중간
                if(num == 0) {
                    dpMax[begin][end] = operands[begin];
                    dpMin[begin][end] = operands[begin];
                    continue;
                }
                for (int middle = begin; middle < end; middle++) {
                    dpMax[begin][end] = Math.max(dpMax[begin][end], calculate(dpMax[begin][middle], dpMax[middle + 1][end], operators[middle]));
                    dpMax[begin][end] = Math.max(dpMax[begin][end], calculate(dpMin[begin][middle], dpMin[middle + 1][end], operators[middle]));
                    dpMax[begin][end] = Math.max(dpMax[begin][end], calculate(dpMin[begin][middle], dpMax[middle + 1][end], operators[middle]));
                    dpMax[begin][end] = Math.max(dpMax[begin][end], calculate(dpMax[begin][middle], dpMin[middle + 1][end], operators[middle]));

                    dpMin[begin][end] = Math.min(dpMin[begin][end], calculate(dpMin[begin][middle], dpMin[middle + 1][end], operators[middle]));
                    dpMin[begin][end] = Math.min(dpMin[begin][end], calculate(dpMax[begin][middle], dpMax[middle + 1][end], operators[middle]));
                    dpMin[begin][end] = Math.min(dpMin[begin][end], calculate(dpMin[begin][middle], dpMax[middle + 1][end], operators[middle]));
                    dpMin[begin][end] = Math.min(dpMin[begin][end], calculate(dpMax[begin][middle], dpMin[middle + 1][end], operators[middle]));
                }
            }
        }

        System.out.println(dpMax[0][length / 2]);
    }

    long calculate(long a, long b, char operator) {
        long result = 0;
        if (operator == '+') {
            result = a + b;
        }
        else if (operator == '-') {
            result = a - b;
        }
        else {
            result = a * b;
        }
        return result;
    }
}
