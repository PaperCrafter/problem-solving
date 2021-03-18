package beakJoon.imReducingEverything;

import java.util.*;

public class ImReducingEverything {
    private final long MOD = 1000000007;
    public void solution() {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int lengthOfLongestWord = 0;
        Node root = new Node();
        for (int i = 0; i < num; i++ ){
            String currentWord = sc.next();
            root.addNode(currentWord, 0);
            lengthOfLongestWord = Math.max(currentWord.length(), lengthOfLongestWord);
        }
        String strToFind = sc.next();
        long[] dp = new long[strToFind.length() + 1];
        for (int i = 0; i < strToFind.length(); i++) {
            Node triRoot = root;
            long sum = 1;
            if (i != 0) sum = dp[i - 1];
            for (int j = i; j < strToFind.length(); j++) {
                char currentChar = strToFind.charAt(j);
                if (!triRoot.nextNodes.containsKey(currentChar)) break;
                triRoot = triRoot.nextNodes.get(currentChar);
                dp[j] = (dp[j] + sum * triRoot.numOfSubtree) % MOD;
            }
        }
        System.out.println(dp[strToFind.length() - 1]);
    }

    class Node {
        Map<Character, Node> nextNodes;
        long numOfSubtree;

        public Node() {
            nextNodes = new HashMap<>();
            numOfSubtree = 0;
        }

        public void addNode(String str, int depth) {
            this.numOfSubtree++;
            if (str.length() == depth) return;
            char nextChar = str.charAt(depth);
            if (!nextNodes.containsKey(nextChar)) {
                nextNodes.put(nextChar, new Node());
            }
            nextNodes.get(nextChar).addNode(str, depth + 1);
        }
    }
}
