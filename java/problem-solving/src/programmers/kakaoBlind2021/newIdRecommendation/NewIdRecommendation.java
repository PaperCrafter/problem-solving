package programmers.kakaoBlind2021.newIdRecommendation;

public class NewIdRecommendation {
    public String solution(String new_id) {
        //step1
        String newId = new_id.toLowerCase();
        //step2
        newId = newId.replaceAll("[^a-z1-9-_.]", "");
        //step3
        newId = newId.replaceAll("[.]{2,}", ".");
        //step4
        if (newId.startsWith(".")) newId = newId.substring(1);
        if (newId.endsWith(".")) newId = newId.substring(0, newId.length()-1);
        //step5
        if (newId.isEmpty()) newId = "a";
        //step6
        if (newId.length() >= 16) newId = newId.substring(0, 15);
        if (newId.endsWith(".")) newId = newId.substring(0, newId.length()-1);
        //step7
        if (newId.length() <= 2) {
            while (newId.length() < 3) {
                newId += newId.charAt(newId.length() - 1);
            }
        }
        return newId;
    }
}
