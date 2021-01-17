package beakJoon.toyTank;

import java.io.*;
import java.util.*;

public class ToyTank {
    public void solution() throws IOException {
        Scanner scanner = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = scanner.nextInt();
        List<Tank> tankList = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            tankList.add(new Tank(i, row, col));
        }

        int counter = 0;

        //sort by row
        StringBuilder sb1 = new StringBuilder();
        tankList.sort(Comparator.comparingInt(Tank::getRow));

        int currentRow = 1;
        for (Tank tank : tankList) {
            while (currentRow != tank.getRow()) {
                counter++;
                if (currentRow < tank.getRow()) {
                    sb1.append(tank.getTankNo() + " U\n");
                    tank.row -= 1;
                }
                else {
                    sb1.insert(0, tank.getTankNo() + " D\n");
                    tank.row += 1;
                }
            }
            currentRow++;
        }

        //sort by col
        StringBuilder sb2 = new StringBuilder();
        tankList.sort(Comparator.comparingInt(Tank::getCol));

        int currentCol = 1;
        for (Tank tank : tankList) {
            while (currentCol != tank.getCol()) {
                counter++;
                if (currentCol < tank.getCol()) {
                    sb2.append(tank.getTankNo() + " L\n");
                    tank.col -= 1;
                }
                else {
                    sb2.insert(0, tank.getTankNo() + " R\n");
                    tank.col += 1;
                }
            }
            currentCol++;
        }
        bw.write(String.valueOf(counter) + "\n" + sb1.toString() + sb2.toString());
        bw.close();
    }
}

class Tank {
    public int tankNo;
    public int row;
    public int col;

    public Tank(int tankNo, int row, int col) {
        this.tankNo = tankNo;
        this.row = row;
        this.col = col;
    }

    public int getTankNo() {
        return tankNo;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}