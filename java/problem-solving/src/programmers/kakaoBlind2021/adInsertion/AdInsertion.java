package programmers.kakaoBlind2021.adInsertion;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import java.util.*;

public class AdInsertion {
    public String solution(String play_time, String adv_time, String[] logs) {
        int[] viewerPerTIme = new int[360000];
        String result = "00:00:00";
        for (String str : logs) {
            String[] startEnd = str.split("-");
            int startTIme = strToSec(startEnd[0]);
            int endTime = strToSec(startEnd[1]);
            for (int i = startTIme; i < endTime; i++) {
                viewerPerTIme[i]++;
            }
        }

        int playTime = strToSec(play_time);
        int adTime = strToSec(adv_time);

        long sum = 0;
        long maxSum = 0;
        for (int i = 0; i < adTime; i++) {
            sum += viewerPerTIme[i];
        }
        maxSum = sum;

        for (int i = adTime; i < playTime; i++) {
            sum += viewerPerTIme[i];
            sum -= viewerPerTIme[i - adTime];
            if (sum > maxSum) {
                maxSum = sum;
                result = secToString(i - adTime + 1);
            }
        }
        return result;
    }

    int strToSec(String s) {
        String[] hms = s.split(":");
        int result = 0;
        result += Integer.parseInt(hms[0]) * 60 * 60;
        result += Integer.parseInt(hms[1]) * 60;
        result += Integer.parseInt(hms[2]);
        return result;
    }

    String secToString(int sec) {
        String h = String.valueOf(sec / (60 * 60));
        if (h.length() == 1) h = "0" + h;
        sec = sec % (60 * 60);
        String m = String.valueOf(sec / 60);
        if (m.length() == 1) m = "0" + m;
        sec = sec % 60;
        String s = String.valueOf(sec);
        if (s.length() == 1) s = "0" + s;
        return h + ":" + m + ":" + s;
    }
}
