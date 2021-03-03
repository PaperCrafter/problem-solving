package beakJoon.specificMinimumDistance;

import java.util.*;

public class SpecificMinimumDistance {
    public void solution() {
        Scanner sc = new Scanner(System.in);
        int nodeNum = sc.nextInt();
        int edgeNum = sc.nextInt();
        List<List<Edge>> graph = new ArrayList<>(nodeNum + 1);
        for (int i = 0; i <= nodeNum; i++) graph.add(new ArrayList());
        for (int i = 0; i  < edgeNum; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int cost = sc.nextInt();
            graph.get(from).add(new Edge(from, to, cost));
            graph.get(to).add(new Edge(to, from, cost));
        }

        int node1 = sc.nextInt();
        int node2 = sc.nextInt();

        int[] minDistFrom = getMinimumDistance(graph, 1);
        if (minDistFrom[1] == -1 || minDistFrom[node1] == -1 || minDistFrom[node2] == -1 || minDistFrom[nodeNum] == -1) {
            System.out.println(-1);
            return;
        }
        int[] minDistByPass1 = getMinimumDistance(graph, node1);
        int[] minDistByPass2 = getMinimumDistance(graph, node2);

        int results = Integer.MAX_VALUE;
        results = Math.min(results, minDistFrom[node1] + minDistByPass2[node1] + minDistByPass2[nodeNum]);
        results = Math.min(results, minDistFrom[node2] + minDistByPass1[node2] + minDistByPass1[nodeNum]);
        System.out.println(results);
        return;
    }

    private int[] getMinimumDistance(List<List<Edge>> graph, int from) {
        int[] results = new int[graph.size()];
        Arrays.fill(results, -1);
        results[from] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.cost));
        for (Edge nextEdge : graph.get(from)) {
            pq.add(nextEdge);
            if (results[nextEdge.to] == -1 || results[nextEdge.from] + nextEdge.cost < results[nextEdge.to]) {
                results[nextEdge.to] = results[nextEdge.from] + nextEdge.cost;
                pq.add(nextEdge);
            }
        }
        while (!pq.isEmpty()) {
            Edge currentEdge = pq.poll();
            for (Edge nextEdge : graph.get(currentEdge.to)) {
                if (results[nextEdge.to] == -1 || results[nextEdge.from] + nextEdge.cost < results[nextEdge.to]) {
                    results[nextEdge.to] = results[nextEdge.from] + nextEdge.cost;
                    pq.add(nextEdge);
                }
            }
        }
        return results;
    }

    class Edge {
        public int from;
        public int to;
        public int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
}
