package line.studioIntern2021;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Problem3 {
    public int solution(int n, String[] recipes, String[] orders) {
        Map<String, Integer> recipeMap = new HashMap<>();
        for (int i = 0; i < recipes.length; i++) {
            String[] keyValue = recipes[i].split(" ");
            recipeMap.put(keyValue[0], Integer.parseInt(keyValue[1]));
        }

        int result = 0;
        int numOfFireHall = n;
        int recipesIndex = 0;
        int currentTime = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        while (true) {
            if (!pq.isEmpty()) {
                currentTime = pq.poll();
                numOfFireHall++;
            }

            while (numOfFireHall > 0 && recipesIndex < orders.length) {
                String[] currentOrder = orders[recipesIndex++].split(" ");
                if (recipesIndex == orders.length) {
                    result = Math.max(currentTime,  Integer.parseInt(currentOrder[1])) + recipeMap.get(currentOrder[0]);
                }
                pq.add(Math.max(currentTime,  Integer.parseInt(currentOrder[1])) + recipeMap.get(currentOrder[0]));
                numOfFireHall--;
            }

            if (pq.isEmpty()) break;
        }

        return result;
    }
}
