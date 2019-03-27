/**
 * KnightFigure
 * The class provides variables for keeping information about knight movement,
 * ways number, accessibility points, knight's moving function
 * <p>
 * Version info 1.0
 * <p>
 * Copyright notice
 */

import java.util.ArrayList;


public class KnightFigure {

    public ArrayList<Integer> knight;
    public String[][] track;
    public Integer[][] DESK, accessibilityPoints;
    Integer[] horizontal = {2, 1, -1, -2, -2, -1, 1, 2};
    Integer[] vertical = {-1, -2, -2, -1, 1, 2, 2, 1};
    public static ArrayList<Integer> knightHistory;


    public KnightFigure(ArrayList<Integer> knight, Integer[][] desk) {
        this.knight = knight;
        this.DESK = desk;
        track = new String[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                track[i][j] = desk[i][j].toString();
            }
        }

        return;
    }


    public void moveKnightFromXY(Integer x, Integer y, Integer vertical, Integer horizontal) {

        Integer currentRow = x;
        Integer currentColumn = y;

        if (Math.abs(horizontal) + Math.abs(vertical) != 3 || vertical.equals(0) || horizontal.equals(0)) {
            try {
                throw new Exception();
            } catch (Exception e) {
                return;

            }

        }

        try {
            if (!knight.contains(DESK[currentRow + horizontal][currentColumn + vertical]))
                knight.add(DESK[currentRow + horizontal][currentColumn + vertical]);
            drawWay(currentRow, currentColumn, horizontal, vertical);
        } catch (ArrayIndexOutOfBoundsException e) {
            return;
        }


    }

    public void drawWay(Integer currentRow, Integer currentColumn, Integer horizontal, Integer vertical) {
        if (horizontal > 0) {
            for (int i = currentRow; i <= currentRow + horizontal; i++) {
                track[i][currentColumn] = "↓";
            }
        } else {
            for (int i = currentRow; i >= currentRow + horizontal; i--) {
                track[i][currentColumn] = "↑";
            }
        }
        if (vertical > 0) {
            for (int i = currentColumn; i <= vertical + currentColumn; i++) {
                track[currentRow][i] = "->";
            }

        } else {
            for (int i = currentColumn; i >= vertical + currentColumn; i--) {
                track[currentRow][i] = "<-";
            }
        }
        track[currentRow][currentColumn] = "K";
        track[currentRow + horizontal][currentColumn + vertical] = "♞";

    }

    public void showWay() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(" " + track[i][j]);
            }

            System.out.println();
        }

        System.out.println();
    }

    public void resetWay() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                track[i][j] = DESK[i][j].toString();
            }
        }
    }

    public Integer[][] getKnightAccessibilityPoints() {
        Integer[][] knightAccessibilityPoints = new Integer[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                knight.clear();

                for (int x = 0; x < 8; x++) {

                    moveKnightFromXY(i, j, horizontal[x], vertical[x]);

                }
                knightAccessibilityPoints[i][j] = knight.size();

            }

        }
        resetWay();
        knight.clear();
        return knightAccessibilityPoints;
    }

    public void tryMoveKnightFromXYToLowAc(Integer x, Integer y, Integer vertical, Integer horizontal) {

        if (Math.abs(horizontal) + Math.abs(vertical) != 3 || vertical.equals(0) || horizontal.equals(0)) {
            return;
        }

        try {
            if (!knight.contains(DESK[x + horizontal][y + vertical]))
                knight.add(DESK[x + horizontal][y + vertical]);
        } catch (ArrayIndexOutOfBoundsException e) {
            return;
        }


    }

    public void moveToLowAccessibilityNumberFrom(Integer i, Integer j) {

        Integer[][] localAccessibilityPoints;
        ArrayList<Integer> localAccessibilityPointsToMin;
        accessibilityPoints = getKnightAccessibilityPoints();
        knightHistory = new ArrayList<>();
        knight.clear();

        do {
            for (int x = 0; x < 8; x++) {                    // Getting value of moving number
                tryMoveKnightFromXYToLowAc(i, j, vertical[x], horizontal[x]);
            }
            localAccessibilityPoints = new Integer[8][8];
            localAccessibilityPointsToMin = new ArrayList<>();

            for (Integer kn : knight) {                     // Getting value with lowest accessibility point
                for (int a = 0; a < 8; a++) {
                    for (int b = 0; b < 8; b++) {
                        if (DESK[a][b].equals(kn)) {
                            localAccessibilityPoints[a][b] = accessibilityPoints[a][b];
                            localAccessibilityPointsToMin.add(localAccessibilityPoints[a][b]);
                        }
                    }
                }
            }

            Integer min = localAccessibilityPointsToMin.stream().min(Integer::compareTo).get();

            movingKnight:
            for (int a = 0; a < 8; a++) {                   // Moving knight to lowest accessibility point
                for (int b = 0; b < 8; b++)
                    if (localAccessibilityPoints[a][b] != null && localAccessibilityPoints[a][b].equals(min)) {

                        if (!knightHistory.contains(DESK[a][b])) {

                            moveKnightFromXY(i, j, a - i, b - j);
                            knightHistory.add(DESK[a][b]);
                            knight.clear();
                            localAccessibilityPointsToMin.clear();
                            i = a;
                            j = b;
                            break movingKnight;
                        }
                    }
            }

        } while (knight.size() == 0);

        showWay();


    }

}
