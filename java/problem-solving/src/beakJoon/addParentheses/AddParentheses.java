package beakJoon.addParentheses;

import org.omg.CORBA.ARG_IN;

import java.lang.reflect.Array;
import java.util.*;

public class AddParentheses {
    public void solution() {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        List<Long> operands = new ArrayList<>();
        List<Character> operators = new ArrayList<>();
        String str = sc.next();
        for (int i = 0; i < str.length(); i++) {
            if (i % 2 == 0) operands.add(Long.valueOf(str.substring(i, i+1)));
            else operators.add(str.charAt(i));
        }

        List<List<Integer>> combinations = new LinkedList<>();
        for (int i = 0; i < operands.size(); i += 2) {
            combination(operands.size(), i, 0, new LinkedList<>(), combinations);
        }

        long maxResults = Integer.MIN_VALUE;
        for (List<Integer> parenthesesLocation: combinations) {
            List<Long> operandsCopy = new ArrayList<>(operands);
            List<Character> operatorsCopy = new ArrayList<>(operators);
            parenthesesLocation.sort(Collections.reverseOrder());
            Queue<Integer> queue = new LinkedList<>(parenthesesLocation);
            while (!queue.isEmpty()) {
                int to = queue.poll();
                int from = queue.poll();
                calculate(operandsCopy, operatorsCopy, from ,to);
            }
            calculate(operandsCopy, operatorsCopy, 0, operandsCopy.size() - 1);
            maxResults = Math.max(maxResults, operandsCopy.get(0));
        }
        System.out.println(maxResults);
    }

    private void combination(int n, int r, int currentNum, List<Integer> tempResult, List<List<Integer>> results) {
        if (tempResult.size() == r) {
            results.add(new ArrayList<>(tempResult));
            return;
        }
        if (currentNum >= n - 1) return;

        tempResult.add(currentNum);
        tempResult.add(currentNum + 1);
        combination(n, r, currentNum + 2, tempResult, results);
        tempResult.remove(tempResult.size() - 1);
        tempResult.remove(tempResult.size() - 1);
        combination(n, r, currentNum + 1, tempResult, results);
    }

    private void calculate(List<Long> operands, List<Character> operators, int from, int to) {
        for (int i = from; i < to; i ++) {
            while (i < to) {
                long op1 = operands.get(i);
                long op2 = operands.get(i + 1);
                to--;
                if (operators.get(i) == '+') operands.set(i, op1 + op2);
                else if (operators.get(i) == '-') operands.set(i, op1 - op2);
                else operands.set(i, op1 * op2);
                operands.remove(i + 1);
                operators.remove(i);
            }
        }
    }
}
