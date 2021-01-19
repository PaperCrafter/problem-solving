package beakJoon.addParentheses2;

import java.util.*;
import java.util.stream.Collectors;

public class AddParentheses2 {
    public void solution() {
        Scanner scanner = new Scanner(System.in);
        int length = Integer.parseInt(scanner.nextLine());
        String expression = scanner.nextLine();
        long[] operands = new long[length/2 + 1];
        Character[] operators = new Character[length/2];

        for (int i = 0; i < expression.length(); i++) {
            if (i % 2 == 0) {
                operands[i / 2] = expression.charAt(i) - '0';
            } else {
                operators[i / 2] = expression.charAt(i);
            }
        }

        List<Set<Integer>> operatorsToCalculateFirst = new LinkedList<>();
        operatorsToCalculateFirst.add(new HashSet<>());
        for(int i = 1; i < length / 2; i++) {
            combination(length / 2, i, 0, new HashSet<>(), operatorsToCalculateFirst);
        }

        long maxResult = Long.MIN_VALUE;
        for (Set<Integer> operatorIndexSet : operatorsToCalculateFirst) {
            List<Long> operandsCopy = Arrays.stream(operands).boxed().collect(Collectors.toList());
            List<Character> operatorsCopy = Arrays.stream(operators).collect(Collectors.toList());

            LinkedList<Integer> indexReverseOrder = new LinkedList<>(operatorIndexSet);
            indexReverseOrder.sort((num1, num2) -> Integer.compare(num2, num1));
            for (Integer index : indexReverseOrder) {
                long num1 = operands[index];
                long num2 = operands[index + 1];
                Character operator = operators[index];
                long result = calculate(num1, num2, operator);
                removeItems(operandsCopy, operatorsCopy, index, result);
            }

            for (int i = operatorsCopy.size() -1; i >= 0; i--) {
                if (operatorsCopy.get(i).equals('*')) {
                    long temp = calculate(operandsCopy.get(i), operandsCopy.get(i+1), operatorsCopy.get(i));
                    removeItems(operandsCopy, operatorsCopy, i, temp);
                }
            }

            long resultTemp = operandsCopy.get(0);
            for (int i = 0; i < operatorsCopy.size(); i++) {
                resultTemp = calculate(resultTemp, operandsCopy.get(i+1), operatorsCopy.get(i));
            }
            maxResult = Math.max(maxResult, resultTemp);
        }
        System.out.println(maxResult);
    }

    public void removeItems(List<Long> operandsCopy, List<Character> operatorsCopy, int index, long result) {
        operandsCopy.remove(index);
        operandsCopy.remove(index);
        operandsCopy.add(index, result);
        operatorsCopy.remove(index);
    }

    public long calculate (long num1, long num2, char operator) {
        long result = 0;
        if (operator== '+') {
            result = num1 + num2;
        } else if (operator == '-') {
            result = num1 - num2;
        } else {
            result = num1 * num2;
        }
        return result;
    }

    public void combination (int n, int r, int currentIdx, Set<Integer> resultTemp, List<Set<Integer>> results) {
        if (r == 0) {
            for(Integer item : resultTemp) {
                if(resultTemp.contains(item + 1) || resultTemp.contains(item - 1))
                    return;
            }
            results.add(new HashSet<>(resultTemp));
            return;
        } else if (n <= currentIdx) {
            return;
        } else {
            resultTemp.add(currentIdx);
            combination(n, r - 1, currentIdx + 2, resultTemp, results);
            resultTemp.remove(currentIdx);
            combination(n, r, currentIdx + 1, resultTemp, results);
        }
    }
}
