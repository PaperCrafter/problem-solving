package beakJoon.stoneGroup;

import java.util.*;

public class StoneGroup {
    public void solution() {
        Scanner scanner = new Scanner(System.in);
        int[] stones = new int[3];
        for (int i = 0; i < stones.length; i++) {
            stones[i] = scanner.nextInt();
        }
        Set<StoneGroups> stoneGroupsVisited = new HashSet<>();
        Queue<StoneGroups> q = new LinkedList<>();
        StoneGroups initialGroups = new StoneGroups(stones);
        q.add(initialGroups);
        stoneGroupsVisited.add(initialGroups);

        while (!q.isEmpty()) {
            StoneGroups stoneGroups = q.poll();
            if (checkIsEqual(stoneGroups.stones)) {
                System.out.println(1);
                return;
            }

            for (int i = 0; i < 3; i++) {
                for (int j = i + 1; j < 3; j++) {
                    int[] stoneArray = Arrays.copyOf(stoneGroups.stones, stoneGroups.stones.length);
                    int low = stoneArray[i];
                    int hi = stoneArray[j];
                    if (low == hi) continue;
                    hi = hi - low;
                    low = low + low;
                    stoneArray[i] = Math.min(low, hi);
                    stoneArray[j] = Math.max(low, hi);
                    if (hi >= 1 && low >= 1) {
                        StoneGroups newStoneGroups = new StoneGroups(stoneArray);
                        if (stoneGroupsVisited.contains(newStoneGroups)) continue;
                        else {
                            q.add(newStoneGroups);
                            stoneGroupsVisited.add(newStoneGroups);
                        }
                    }
                }
            }
        }
        System.out.println(0);
        return;
    }

    private boolean checkIsEqual(int[] arr) {
        for (int i = 1; i < 3; i++) {
            if (arr[i] != arr[i-1]) return false;
        }
        return true;
    }

    class StoneGroups {
        public int[] stones;

        public StoneGroups(int[] stones) {
            this.stones = stones;
            Arrays.sort(this.stones);
        }

        @Override
        public boolean equals(Object obj) {
            StoneGroups stoneGroup = (StoneGroups) obj;
            for (int i = 0; i < stoneGroup.stones.length; i++) {
                if (stoneGroup.stones[i] != this.stones[i]) return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            int hashCode = 0;
            for (int i = 0; i < this.stones.length; i++) {
                hashCode += stones[i] + hashCode * 13;
            }
            return hashCode;
        }
    }
}
