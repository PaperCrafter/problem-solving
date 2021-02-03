package programmers.kakaoBlind2021.menuRenewal;

import java.util.*;
import java.util.stream.Collectors;

public class MenuRenewal {
    public String[] solution(String[] orders, int[] course) {
        for (int i = 0; i < orders.length; i++) {
            char[] arr = orders[i].toCharArray();
            Arrays.sort(arr);
            orders[i] = String.valueOf(arr);
        }

        Map<String, Integer> menuCounter = new HashMap<>();
        Arrays.sort(orders);
        for (int i = 0; i < orders.length; i++) {
            int counter = 1;
            String currentMenu = orders[i];
            List<String> courseCombination = new ArrayList<>();
            for (int j = 0; j < course.length; j++) {
                if (currentMenu.length() < course[j]) break;
                combination(currentMenu, "", course[j], 0, courseCombination);
            }

            for (String courseItem : courseCombination) {
                int referencedNumber = menuCounter.getOrDefault(courseItem, 0);
                menuCounter.put(courseItem, referencedNumber + 1);
            }
        }

        List<String> result = new ArrayList<>();
        for (int i = 0; i < course.length; i++) {
            List<String> tempResult = new ArrayList<>();
            int tempMaxValue = 0;
            for (Map.Entry<String, Integer> courseItem : menuCounter.entrySet()) {
                if (course[i] == courseItem.getKey().length() && courseItem.getValue() >= 2) {
                    if (courseItem.getValue() > tempMaxValue) {
                        tempResult.clear();
                        tempResult.add(courseItem.getKey());
                        tempMaxValue = courseItem.getValue();
                    } else if (courseItem.getValue() == tempMaxValue) {
                        tempResult.add(courseItem.getKey());
                    }
                }
            }
            result.addAll(tempResult);
        }


        Collections.sort(result);
        return result.toArray(new String[result.size()]);
    }

    public void combination(String originalString, String currentString, int r, int currentIdx, List<String> results) {
        if (r == 0) {
            results.add(currentString);
            return;
        }
        if (currentIdx >= originalString.length()) return;

        combination(originalString, currentString + originalString.charAt(currentIdx), r-1, currentIdx + 1, results);
        combination(originalString, currentString , r, currentIdx + 1, results);
    }
}
