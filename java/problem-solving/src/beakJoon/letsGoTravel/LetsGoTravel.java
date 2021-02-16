package beakJoon.letsGoTravel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class LetsGoTravel {
    public void solution() {
        Scanner scanner = new Scanner(System.in);
        int numOfCities = scanner.nextInt();
        int numOfCityPlan = scanner.nextInt();
        int[] disjointSet = new int[numOfCities + 1];
        initializeSet(disjointSet, numOfCities);
        for (int i = 1; i <= numOfCities; i++) {
            for (int j = 1; j <= numOfCities; j++) {
                int isConnected = scanner.nextInt();
                if (isConnected == 1 && !findParent(disjointSet, i, j)) {
                    unionParent(disjointSet, i, j);
                }
            }
        }

        List<Integer> citiesToVisit = new ArrayList<>();
        for (int i = 0; i < numOfCityPlan; i++) {
            citiesToVisit.add(scanner.nextInt());
        }

        for (int i = 1; i < numOfCityPlan; i++) {
            if (!findParent(disjointSet, citiesToVisit.get(i), citiesToVisit.get(i-1))) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
        return;
    }

    void initializeSet(int[] disjointSet, int numOfCities) {
        IntStream.range(0, numOfCities + 1).forEach(item -> disjointSet[item] = item);
    }

    boolean findParent(int[] disjointSet, int item1, int item2) {
        int parent1 = getParent(disjointSet, item1);
        int parent2 = getParent(disjointSet, item2);
        if (parent1 == parent2) return true;
        return false;
    }

    int getParent(int[] disjointSet, int item) {
        if (disjointSet[item] == item) return item;
        return getParent(disjointSet, disjointSet[item]);
    }

    void unionParent(int[] disjointSet, int item1, int item2) {
        int parent1 = getParent(disjointSet, item1);
        int parent2 = getParent(disjointSet, item2);
        if (parent1 < parent2) disjointSet[parent2] = parent1;
        else disjointSet[parent1] = parent2;
    }
}
