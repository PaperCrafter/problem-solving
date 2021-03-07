package beakJoon.theSicklyYoungJeong;

import java.util.*;

public class TheSicklyYoungJeong {
    public void solution() {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        Map<Integer, Integer> symptomMedicineMap = new HashMap<>();
        for (int i = 0; i < num; i++) {
            symptomMedicineMap.put(sc.nextInt(), sc.nextInt());
        }
        int diseaseNum = sc.nextInt();
        for (int i = 0; i < diseaseNum; i++) {
            int symptoms = sc.nextInt();
            List<Integer> diseaseList = new ArrayList<>();
            for (int j = 0; j < symptoms; j++) {
                diseaseList.add(sc.nextInt());
            }

            boolean flag = false;
            List<Integer> medicineList = new ArrayList<>();
            for (int disease : diseaseList) {
                if (symptomMedicineMap.containsKey(disease)) {
                    medicineList.add(symptomMedicineMap.get(disease));
                } else {
                    System.out.println("YOU DIED");
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                StringBuffer sb = new StringBuffer();
                for (int item : medicineList) {
                    sb.append(item + " ");
                }
                System.out.println(sb.toString());
            }
        }
    }
}
