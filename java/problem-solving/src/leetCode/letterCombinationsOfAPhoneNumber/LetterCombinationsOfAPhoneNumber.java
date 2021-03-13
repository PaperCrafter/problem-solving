package leetCode.letterCombinationsOfAPhoneNumber;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class LetterCombinationsOfAPhoneNumber {
    public List<String> solution(String digits) {
        List<List<String>> lists = new LinkedList<>();
        for (String num : digits.split("")) {
            if (num.equals("2")) {
                lists.add(Arrays.stream(new String[]{"a", "b", "c"}).collect(Collectors.toList()));
            } else if (num.equals("3")) {
                lists.add(Arrays.stream(new String[]{"d", "e", "f"}).collect(Collectors.toList()));
            } else if (num.equals("4")) {
                lists.add(Arrays.stream(new String[]{"g", "h", "i"}).collect(Collectors.toList()));
            } else if (num.equals("5")) {
                lists.add(Arrays.stream(new String[]{"j", "k", "l"}).collect(Collectors.toList()));
            } else if (num.equals("6")) {
                lists.add(Arrays.stream(new String[]{"m", "n", "o"}).collect(Collectors.toList()));
            } else if (num.equals("7")) {
                lists.add(Arrays.stream(new String[]{"p", "q", "r", "s"}).collect(Collectors.toList()));
            } else if (num.equals("8")) {
                lists.add(Arrays.stream(new String[]{"t", "u", "v"}).collect(Collectors.toList()));
            } else if (num.equals("9")) {
                lists.add(Arrays.stream(new String[]{"w", "x", "y", "z"}).collect(Collectors.toList()));
            }
        }
        List<String> combinations = new LinkedList<>();
        if (lists.size() > 0) {
            combination(lists, combinations, new StringBuffer(), 0);
        }
        return combinations;
    }

    private void combination(List<List<String>> lists, List<String> combinations, StringBuffer currentString, int depth) {
        if (depth == lists.size()) {
            combinations.add(currentString.toString());
            return;
        }

        for (String alphabet : lists.get(depth)) {
            currentString.append(alphabet);
            combination(lists, combinations, currentString, depth + 1);
            currentString.deleteCharAt(currentString.length() - 1);
        }
    }
}
