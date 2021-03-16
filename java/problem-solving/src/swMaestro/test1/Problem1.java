package swMaestro.test1;

import beakJoon.characterBoard.CharacterBoard;

import java.util.*;

public class Problem1 {
    public void solution() {
        Scanner sc = new Scanner(System.in);
        Map<Character, List<Character>> graph = new LinkedHashMap<>();
        Set<Character> itemsToBegin = new LinkedHashSet<>();
        String[] inputs = sc.nextLine().split(" ");
        for (int i = 0; i < inputs.length; i++) {
            Character node = inputs[i].charAt(0);
            itemsToBegin.add(node);
            graph.put(node, new LinkedList<>());
        }


        int relation = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < relation; i++) {
            String[] edge = sc.nextLine().split(" ");
            Character from = edge[0].charAt(0);
            Character to = edge[1].charAt(0);
            List<Character> nodes = graph.get(from);
            nodes.add(to);
            graph.put(from, nodes);

            if (itemsToBegin.contains(to)) {
                itemsToBegin.remove(to);
            }
        }

        List<String> results = new LinkedList<>();
        Set<Character> visited = new HashSet<>();
        for (Character item : itemsToBegin) {
            StringBuffer sb = new StringBuffer();
            sb.append(item + " ");
            visited.add(item);
            dfs(graph, item, visited, results, sb);
        }

        for (String str : results) {
            System.out.println(str);
        }
        return;
    }

    private void dfs(Map<Character, List<Character>> graph, Character currentNode, Set<Character> visited, List<String> results, StringBuffer currentResult) {
        if (graph.get(currentNode).size() == 0) {
            if (currentResult.length() <= 2) return;
            results.add(currentResult.toString());
            return;
        }

        for (Character node : graph.get(currentNode)) {
            if (!visited.contains(node)) {
                currentResult.append(node.toString() + " ");
                visited.add(node);
                dfs(graph, node, visited, results, currentResult);
                currentResult.delete(currentResult.length() - 2, currentResult.length());
            }
        }
    }
}
