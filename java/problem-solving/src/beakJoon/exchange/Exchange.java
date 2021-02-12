package beakJoon.exchange;

import java.util.*;

public class Exchange {
    public void solution() {
        Scanner scanner = new Scanner(System.in);
        String number = scanner.next();
        int k = scanner.nextInt();
        boolean[] visitedOdd = new boolean[(int) Math.pow(10, number.length())];
        boolean[] visitedEven = new boolean[(int) Math.pow(10, number.length())];

        Queue<Item> q = new LinkedList<>();
        q.add(new Item(number, 0));

        while (!q.isEmpty()) {
            Item currentItem = q.poll();
            if (currentItem.depth > k) continue;

            int currentNumber = Integer.parseInt(currentItem.number);
            if (currentItem.depth % 2 == 0) {
                if (visitedEven[currentNumber] == true) continue;
                visitedEven[currentNumber] = true;
            } else {
                if (visitedOdd[currentNumber] == true) continue;
                visitedOdd[currentNumber] = true;
            }

            for (int i = 0; i < currentItem.number.length(); i++) {
                for (int j = i + 1; j < currentItem.number.length(); j++) {
                    StringBuffer nextNumber = new StringBuffer(currentItem.number);
                    char temp = nextNumber.charAt(j);
                    nextNumber.setCharAt(j, nextNumber.charAt(i));
                    nextNumber.setCharAt(i, temp);
                    if (nextNumber.charAt(0) == '0') continue;
                    q.add(new Item(String.valueOf(nextNumber), currentItem.depth + 1));
                }
            }
        }

        int result = -1;
        for (int i = 0; i < visitedEven.length; i++) {
            if (k % 2 == 0 && visitedEven[i] == true) {
                result = Math.max(result, i);
            } else if (k % 2 != 0 && visitedOdd[i] == true) {
                result = Math.max(result, i);
            }
        }

        if ((number.length() == 2 && number.contains("0")) || number.length() == 1) result = -1;
        System.out.println(result);
    }

    class Item {
        public String number;
        public int depth;

        public Item(String number, int depth) {
            this.number = number;
            this.depth = depth;
        }
    }
}
