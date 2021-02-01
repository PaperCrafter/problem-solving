package beakJoon.internetInstallation;

import java.util.*;

public class InternetInstallation {
    public void solution() {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int P = scanner.nextInt();
        int K = scanner.nextInt();

        List<Path>[] adjMatrix = new List[N + 1];
        for (int i = 0; i < adjMatrix.length; i++) {
            adjMatrix[i] = new ArrayList<>();
        }

        for (int i = 0; i < P; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int cost = scanner.nextInt();
            adjMatrix[from].add(new Path(from, to, cost));
            adjMatrix[to].add(new Path(to, from, cost));
        }

        int result = optimization(adjMatrix, K, 1, N);
        System.out.println(result);
    }

    class Path implements Comparable{
        public int from;
        public int to;
        public int cost;

        public Path(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }


        @Override
        public int compareTo(Object o) {
            Path p = (Path) o;
            return Integer.compare(this.cost, p.cost);
        }
    }

    public int optimization(List<Path>[] adjMatrix, int freeLine, int nodeFrom, int nodeTo) {
        int begin = 0;
        int end = 1000000;
        int result = -1;

        while (begin <= end) {
            int mid = (begin + end) / 2;
            if (decision(adjMatrix, freeLine, nodeFrom, nodeTo, mid)) {
                result = mid;
                end = mid - 1;
            } else {
                begin = mid + 1;
            }
        }
        return result;
    }

    public boolean decision(List<Path>[] adjMatrix, int freeLine, int nodeFrom, int nodeTo, int threshold) {
        int[] costs = new int[nodeTo + 1];
        Arrays.fill(costs, -1);
        costs[nodeFrom] = 0;

        List<Path>[] adjMatrixCopy = new List[adjMatrix.length];
        for (int i = 0; i < adjMatrix.length; i++) {
            adjMatrixCopy[i] = new ArrayList<>();
            for (int j = 0; j < adjMatrix[i].size(); j++) {
                int from = adjMatrix[i].get(j).from;
                int to = adjMatrix[i].get(j).to;
                int cost = adjMatrix[i].get(j).cost > threshold ? 1 : 0;
                adjMatrixCopy[i].add(new Path(from, to, cost));
            }
        }

        PriorityQueue<Path> pq = new PriorityQueue();
        for (int i = 0; i < adjMatrixCopy[nodeFrom].size(); i++) {
            pq.add(adjMatrixCopy[nodeFrom].get(i));
        }

        while (!pq.isEmpty()) {
            Path currentPath = pq.poll();
            int expectedCost = currentPath.cost + costs[currentPath.from];
            if (costs[currentPath.to] == -1 || costs[currentPath.to] > expectedCost) {
                costs[currentPath.to] = expectedCost;
            } else {
                continue;
            }

            for (int i = 0; i < adjMatrixCopy[currentPath.to].size(); i++) {
                pq.add(adjMatrixCopy[currentPath.to].get(i));
            }
        }

        if (costs[nodeTo] != -1 && costs[nodeTo] <= freeLine) {
            return true;
        }
        return false;
    }
}
