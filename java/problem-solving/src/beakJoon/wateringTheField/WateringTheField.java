package beakJoon.wateringTheField;

import java.util.*;

public class WateringTheField {
    public void solution() {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int C = scanner.nextInt();
        List<Node> nodeList = new ArrayList<>();
        List<Edge>[] adjGraph = new List[N];
        for (int i = 0; i < N; i++) {
            int posX = scanner.nextInt();
            int posY = scanner.nextInt();
            Node currentNode = new Node(i, posX, posY);
            adjGraph[i] = new ArrayList<>();
            for (int j = 0; j < nodeList.size(); j++) {
                Node tempNode = nodeList.get(j);
                int cost = (int) (Math.pow(currentNode.posX - tempNode.posX, 2) + Math.pow(currentNode.posY - tempNode.posY, 2));
                if (cost >= C) {
                    adjGraph[i].add(new Edge(currentNode, tempNode, cost));
                    adjGraph[j].add(new Edge(tempNode, currentNode, cost));
                }
            }
            nodeList.add(currentNode);
        }

        int total = 0;
        int cnt = 0;
        boolean[] visited = new boolean[N];
        visited[0] = true;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < adjGraph[0].size(); i++) {
            pq.add(adjGraph[0].get(i));
        }

        while (!pq.isEmpty()) {
            if (cnt == N - 1) break;
            Edge e = pq.poll();
            if (visited[e.to.num] == true) continue;
            visited[e.to.num] = true;
            cnt++;
            total += e.cost;
            for (int i = 0; i < adjGraph[e.to.num].size(); i++) {
                Edge nextEdge = adjGraph[e.to.num].get(i);
                if (visited[nextEdge.to.num] == false) {
                    pq.add(nextEdge);
                }
            }
        }

        if (cnt < N - 1) total = -1;
        System.out.println(total);
    }

    class Node {
        public int num;
        public int posX;
        public int posY;
        public Node(int num, int posX, int posY) {
            this.num = num;
            this.posX = posX;
            this.posY = posY;
        }
    }

    class Edge implements Comparable {
        public Node from;
        public Node to;
        public int cost;
        public Edge(Node from, Node to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Object o) {
            Edge e = (Edge) o;
            return Integer.compare(this.cost, e.cost);
        }
    }
}
