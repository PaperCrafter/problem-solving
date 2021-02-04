package programmers.kakaoBlind2021.rankingSearch;

import java.util.*;

public class RankingSearch {
    public int[] solution(String[] info, String[] query) {
        String[][] infos = new String[info.length][5];
        for (int i = 0; i < info.length; i++) {
            infos[i] = info[i].split(" ");
        }

        Arrays.sort(infos, Comparator.comparingInt((String[] list) -> Integer.parseInt(list[4])));

        List<List<Integer>> combinationList = new ArrayList<>();
        for (int i = 0; i <= 4; i++) {
            combination(4, i, 0, combinationList, new ArrayList<>());
        }

        Map<String, List<Integer>> listMap = new HashMap<>();
        for (int i = 0; i < infos.length; i++) {
            for (List<Integer> comb : combinationList) {
                String currentKey = "";
                for (int j = 0; j < 4; j++) {
                    if (comb.contains(j)) {
                        currentKey += "-";
                    }  else {
                       currentKey += infos[i][j];
                    }
                }
                List<Integer> currentValues = listMap.getOrDefault(currentKey, new ArrayList<>());
                currentValues.add(Integer.parseInt(infos[i][4]));
                listMap.put(currentKey, currentValues);
            }
        }

        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < query.length; i++) {
            String[] singleQuery = query[i].split(" ");
            singleQuery = Arrays.stream(singleQuery).filter(item -> !item.equals("and")).toArray(String[]::new);
            String currentKey = "";
            for (int j = 0; j < singleQuery.length -1; j++) {
                currentKey += singleQuery[j];
            }
            List<Integer> candidates = listMap.getOrDefault(currentKey, new ArrayList<>());
            int lowerBound = getLowerBound(candidates, Integer.parseInt(singleQuery[4]));
            int currentResult = 0;
            if (lowerBound == -1) {
                currentResult = 0;
            } else {
                currentResult = candidates.size() - lowerBound;
            }

            results.add(currentResult);
        }
        return results.stream().mapToInt(i -> i).toArray();
    }

    int getLowerBound(List<Integer> candidates, int key) {
        int left = -1;
        int right = candidates.size();
        while(left + 1 < right ) {
            int middle = (left + right) >>> 1;
            if (candidates.get(middle) >= key) right = middle ;
            else left = middle;
        }
        return right;
    }

    public void combination(int num, int r, int begin, List<List<Integer>> results, List<Integer> tempResult) {
        if (r == 0) {
            results.add(new ArrayList<>(tempResult));
            return;
        }
        if (begin >= num) return;
        tempResult.add(begin);
        combination(num, r-1, begin + 1, results, tempResult);
        tempResult.remove(tempResult.size()-1);
        combination(num, r, begin + 1, results, tempResult);
    }
}
