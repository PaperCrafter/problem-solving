package programmers.kakaoBlind2021.rideTogetherTaxiFee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class RideTogetherTaxiFee {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        List<Edge>[] graph = new List[n + 1];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < fares.length; i++) {
            graph[fares[i][0]].add(new Edge(fares[i][0], fares[i][1], fares[i][2]));
            graph[fares[i][1]].add(new Edge(fares[i][1], fares[i][0], fares[i][2]));
        }

        int[] distance1;
        int[] distance2;
        distance1 = dijkstra(graph, n, s);
        int minimumDistance = distance1[a] + distance1[b];
        for (int i = 1; i < distance1.length; i++) {
            if (distance1[i] > minimumDistance) continue;
            distance2 = dijkstra(graph, n, i);
            minimumDistance = Math.min(minimumDistance, distance1[i] + distance2[a] + distance2[b]);
        }

//        int[] distanceS = dijkstra(graph, n, s);
//        int[] distanceA = dijkstra(graph, n, a);
//        int[] distanceB = dijkstra(graph, n, b);
//        int minimumDistance = Integer.MAX_VALUE;
//
//        for (int i = 1; i < distanceS.length; i++) {
//            minimumDistance = Math.min(minimumDistance, distanceS[i] + distanceA[i] + distanceB[i]);
//        }

        return minimumDistance;
    }

    public int[] dijkstra(List<Edge>[] graph, int nodeNum, int begin) {
        PriorityQueue<Edge> pq = new PriorityQueue();
        int[] distance = new int[nodeNum + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        distance[begin] = 0;
        for (int i = 0; i < graph[begin].size(); i++) {
            pq.add(graph[begin].get(i));
        }

        while (!pq.isEmpty()) {
            Edge currentEdge = pq.poll();
            int currentExpectedCost = currentEdge.cost + distance[currentEdge.begin];
            if (distance[currentEdge.end] < currentExpectedCost) {
                continue;
            }

            distance[currentEdge.end] = currentExpectedCost;

            for (Edge nextEdge : graph[currentEdge.end]) {
                int nextExpectedCost = distance[nextEdge.begin] + nextEdge.cost;
                if (distance[nextEdge.end] > nextExpectedCost) {
                    distance[nextEdge.end] = nextExpectedCost;
                    pq.add(nextEdge);
                }
            }
        }
        return distance;
    }

    class Edge implements Comparable<Edge> {
        public int begin;
        public int end;
        public int cost;

        public Edge(int begin, int end, int cost) {
            this.begin = begin;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge e) {
            return Integer.compare(this.cost, e.cost);
        }
    }
}
