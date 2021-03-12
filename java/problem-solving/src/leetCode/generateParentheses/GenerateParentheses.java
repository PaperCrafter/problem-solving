package leetCode.generateParentheses;

import java.util.LinkedList;
import java.util.List;

public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> results = new LinkedList<>();
        dfs(n ,n, results, new StringBuffer());
        return results;
    }

    void dfs(int left, int right, List<String> results, StringBuffer currentResult) {
        if (left == 0 && right == 0) {
            results.add(currentResult.toString());
        }

        if (left < right) {
            currentResult.append(')');
            dfs(left, right - 1, results, currentResult);
            currentResult.deleteCharAt(currentResult.length() - 1);
        }

        if (left > 0) {
            currentResult.append('(');
            dfs(left - 1, right, results, currentResult);
            currentResult.deleteCharAt(currentResult.length() - 1);
        }
    }
}
