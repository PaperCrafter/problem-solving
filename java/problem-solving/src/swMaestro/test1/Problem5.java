package swMaestro.test1;

import java.util.*;

public class Problem5 {
    public void solution() {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < num * num; i++) {
            int point = sc.nextInt();
            int appearNum = sc.nextInt();
            for (int j = 0; j < appearNum; j++) {
                int time = sc.nextInt();
                if (hashMap.containsKey(time)) {
                    if (hashMap.get(time) < point) {
                        hashMap.replace(time, point);
                    }
                } else {
                    hashMap.put(time, point);
                }
            }
        }

        int total = 0;
        for (Map.Entry<Integer, Integer> e : hashMap.entrySet()) {
            total += e.getValue();
        }
        System.out.println(total);
        return;
    }
}
