package beakJoon.weightLimit;

import java.util.*;

public class WeightLimit {
    public void solution() {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        List<Edge>[] graph = new List[N + 1];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            int node1 = scanner.nextInt();
            int node2 = scanner.nextInt();
            int weight = scanner.nextInt();
            graph[node1].add(new Edge(node1, node2, weight));
            graph[node2].add(new Edge(node2, node1, weight));
        }
        int from = scanner.nextInt();
        int to = scanner.nextInt();

        System.out.println(optimize(graph, from, to));
    }

    private int optimize(List<Edge>[] graph, int from, int to) {
        int lo = 1;
        int hi = 1000000000;
        int result = 0;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (decision(graph, from, to, mid)) {
                result = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return result;
    }

    private boolean decision(List<Edge>[] graph, int from, int to, int mid) {
        boolean[] visited = new boolean[graph.length];
        Queue<Integer> q = new LinkedList<>();
        q.add(from);
        visited[from] = true;
        while (!q.isEmpty()) {
           int currentNode = q.poll();
           if (currentNode == to) {
               return true;
           }
           for (int i = 0; i < graph[currentNode].size(); i++) {
               Edge nextEdge = graph[currentNode].get(i);
               if (visited[nextEdge.to] == false && nextEdge.weight >= mid) {
                   q.add(nextEdge.to);
                   visited[nextEdge.to] = true;
               }
           }
        }
        return false;
    }

    class Edge {
        public int from;
        public int to;
        public int weight;


        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
}
