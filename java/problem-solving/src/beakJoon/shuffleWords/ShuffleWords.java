package beakJoon.shuffleWords;

import java.util.*;

public class ShuffleWords {

    public void solution() {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        scanner.nextLine();
        for (int i = 1; i <= num; i++) {
            String line = scanner.nextLine();
            String[] words = line.split(" ");
            boolean[][] visited = new boolean[words[0].length() + 1][words[1].length() + 1];
            if (dfs(words[0], words[1], words[2], 0, 0, 0, visited)) System.out.println("Data set " + i + ": yes");
            else System.out.println("Data set " + i + ": no");
        }
    }

    public boolean dfs(String wordA, String wordB, String combination, int idxA, int idxB, int idxC, boolean[][] visited) {
        if (idxA == wordA.length() && idxB == wordB.length() && idxC == combination.length()) {
            return true;
        }
        if (visited[idxA][idxB]) return false;
        visited[idxA][idxB] = true;

        boolean result = false;
        if (idxA < wordA.length() && combination.charAt(idxC) == wordA.charAt(idxA)) {
            result |= dfs(wordA, wordB, combination, idxA + 1, idxB, idxC + 1, visited);
        }

        if (idxB < wordB.length() && combination.charAt(idxC) == wordB.charAt(idxB)) {
            result |= dfs(wordA, wordB, combination, idxA, idxB + 1, idxC + 1, visited);
        }

        return result;
    }
}
