package line.studioIntern2021;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Problem1 {
    public int solution(String[] holidays, int k) {
        int[] weeks = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        List<Day> dayList = new ArrayList<>();
        for (int i = 0; i < holidays.length; i++) {
            String[] dayAndMonth = holidays[i].split("/");
            dayList.add(new Day(Integer.parseInt(dayAndMonth[0]), Integer.parseInt(dayAndMonth[1])));
        }

        List<Integer> succeedingHolidayDays = new ArrayList<>();
        int month = 1;
        int day = 2;
        boolean isSaturday = true;
        while (month < 13) {
            Day nextHoliday = new Day(month, day);
            if (!dayList.contains(nextHoliday)) {
                dayList.add(nextHoliday);
            };

            if (isSaturday) {
                day += 1;
                isSaturday = false;
            } else {
                day += 6;
                isSaturday = true;
            }

            if (day > weeks[month]) {
                day %= weeks[month];
                month += 1;
            }
        }

        Collections.sort(dayList);
        int weekendCounter = 1;
        for (int i = 1; i < dayList.size(); i++) {
            if (dayList.get(i-1).isSucceedingDay(dayList.get(i), weeks)) {
                weekendCounter++;
            } else {
                succeedingHolidayDays.add(weekendCounter);
                weekendCounter = 1;
            }

            if (i == dayList.size() - 1) {
                succeedingHolidayDays.add(weekendCounter);
            }
        }

        succeedingHolidayDays = succeedingHolidayDays.stream()
                .distinct()
                .sorted((num1, num2) -> Integer.compare(num2, num1))
                .collect(Collectors.toList());

        return succeedingHolidayDays.get(k - 1);
    }

    class Day implements Comparable{
        int month;
        int day;

        public Day(int month, int day) {
            this.month = month;
            this.day = day;
        }

        @Override
        public boolean equals(Object obj) {
            Day d = (Day) obj;
            return d.day == this.day && d.month == this.month;
        }

        public boolean isSucceedingDay(Day day, int[] weeks) {
            if (this.month == day.month && day.day - this.day == 1) {
                return true;
            } else if (day.month - this.month == 1 && this.day == weeks[this.month] && day.day == 1) {
                return true;
            } return false;
        }

        @Override
        public int compareTo(Object o) {
            Day d = (Day) o;
            if (this.month == d.month) {
                return this.day - d.day;
            }
            return this.month - d.month;
        }
    }
}
