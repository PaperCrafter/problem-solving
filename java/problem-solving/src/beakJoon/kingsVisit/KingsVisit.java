package beakJoon.kingsVisit;

import util.fastIO.FastIO;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KingsVisit {
    public void solution() {
        FastIO fastIO = new FastIO();
        int N = fastIO.nextInt();
        int M = fastIO.nextInt();

        int A = fastIO.nextInt();
        int B = fastIO.nextInt();
        int K = fastIO.nextInt();
        int G = fastIO.nextInt();

        int[] godliVisit = new int[G];
        for (int i = 0; i < G; i++) {
            godliVisit[i] = fastIO.nextInt();
        }

        Dijkstra dijkstra = new Dijkstra(N);
        for (int i = 0; i < M; i++) {
            int U = fastIO.nextInt();
            int V = fastIO.nextInt();
            int L = fastIO.nextInt();
            dijkstra.addVertex(U, V, L);
        }

        int result = dijkstra.calcShortestPath(A, B, K, godliVisit);
        System.out.println(result);
    }

    class Dijkstra {
        private int size = 0;
        private int[][] graph;
        private int[] result;
        private boolean[] visited;

        public Dijkstra(int size) {
            this.size = size;
            graph = new int[size + 1][size + 1];
            for (int i = 0; i < size + 1; i++) {
                Arrays.fill(graph[i], 0);
            }
            result = new int[size + 1];
            visited = new boolean[size + 1];
            Arrays.fill(visited, false);
        }

        public void addVertex(int from, int to, int cost) {
            graph[from][to] = cost;
            graph[to][from] = cost;
        }

        public int calcShortestPath(int start, int end, int begin, int[] godvisit) {
            Arrays.fill(result, begin);

            PriorityQueue<Pair> pq = new PriorityQueue<>();
            Pair godliLocBegin = getGodliLocation(godvisit, begin);
            visited[start] = true;
            for (int i = 1; i < this.size + 1; i++) {
                if(graph[start][i] == 0) continue;
                if ((godliLocBegin.from == start && godliLocBegin.to == i) ||
                        (godliLocBegin.from == i && godliLocBegin.to == start) ) {
                    int alpha = godliLocBegin.cost - begin > 0 ? godliLocBegin.cost - begin : 0;
                    pq.add(new Pair(start, i, graph[start][i] + alpha));
                } else {
                    pq.add(new Pair(start, i, graph[start][i]));
                }
            }

            while (!pq.isEmpty()) {
                Pair currentPair = pq.poll();
                if (result[currentPair.to] != begin || visited[currentPair.to]) continue;

                visited[currentPair.to] = true;
                Pair godliLoc = getGodliLocation(godvisit, result[currentPair.to]);
                result[currentPair.to] = result[currentPair.from] + currentPair.cost;

                for (int i = 1; i < size + 1; i++) {
                    if (graph[currentPair.to][i] != 0) {
                        if ((currentPair.to == godliLoc.from && i == godliLoc.to)
                        || (currentPair.to == godliLoc.to && i == godliLoc.from)) {
                            int alpha = godliLoc.cost - result[currentPair.to] > 0 ? godliLoc.cost - result[currentPair.to] : 0;
                            pq.add(new Pair(currentPair.to, i, graph[currentPair.to][i] + alpha));
                        } else {
                            pq.add(new Pair(currentPair.to, i, graph[currentPair.to][i]));
                        }
                    }
                }
            }

            return result[end] - begin;
        }

        public Pair getGodliLocation(int[] godliVisit, int time) {
            int cost = 0;
            int begin = 0;
            int end = 0;
            for (int i = 1; i < godliVisit.length; i++) {
                begin = godliVisit[i-1];
                end = godliVisit[i];
                cost += graph[godliVisit[i-1]][godliVisit[i]];
                if(cost > time) break;
            }
            return new Pair(begin, end, cost);
        }

        class Pair implements Comparable {
            public int from;
            public int to;
            public int cost;

            public Pair(int from, int to, int cost) {
                this.from = from;
                this.to = to;
                this.cost = cost;
            }

            @Override
            public int compareTo(Object o) {
                Pair p = (Pair) o;
                return cost - p.cost;
            }
        }
    }
}
