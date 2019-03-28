/**
 * KnightFigure
 * The class provides variables for keeping information about knight movement,
 * ways number, accessibility points, knight's moving function
 *
 * Version info 1.0
 *
 * Copyright notice
 */

import java.util.ArrayList;


public class KnightFigure {

    public ArrayList<Integer> knight;
    public String[][] track;
    public Integer[][] DESK;
    public Integer[][] accessibilityPoints;
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
    }

    /**
     * Moving knight from start point to another point
     * @param x horizontal coordinate (0<x<7)
     * @param y vertical coordinate (0<y<7)
     * @param vertical vertical displacement value
     * @param horizontal horizontal displacement value
     */
    public void moveKnightFromXY(Integer x, Integer y, Integer vertical, Integer horizontal) {

        Integer currentRow = x;
        Integer currentColumn = y;

        if (Math.abs(horizontal) + Math.abs(vertical) != 3 || vertical.equals(0) || horizontal.equals(0)) {
            return;
        }

        try {
            if (!knight.contains(DESK[currentRow + horizontal][currentColumn + vertical]))
                knight.add(DESK[currentRow + horizontal][currentColumn + vertical]);
            drawWay(currentRow, currentColumn, horizontal, vertical);
        } catch (ArrayIndexOutOfBoundsException e) {
            return;
        }
    }

    /**
     * Set view of knight position
     * @param currentRow horizontal coordinate of knight position (0<x<7)
     * @param currentColumn vertical coordinate of knight position (0<x<7)
     * @param horizontal horizontal displacement value to new knight position
     * @param vertical vertical displacement value to new knight position
     */
    public void drawWay(Integer currentRow, Integer currentColumn, Integer horizontal, Integer vertical) {
        track[currentRow][currentColumn] = "♘";
        track[currentRow + horizontal][currentColumn + vertical] = "♞";

    }

    /**
     * Show knight on the desk
     */
    public void showWay() {
        String space = "   ";
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.printf("%2s%s", track[i][j], space);
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Set view to init
     */
    public void resetWay() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                track[i][j] = DESK[i][j].toString();
            }
        }
    }

    /**
     * Get accessibility points matrix of knight
     * @return accessibility points matrix
     */
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

    /**
     * Get available accessibility points coordinates for current position of knight
     * @param locAccessibilityPoints keep local accessibility points matrix for current knight
     * @param x horizontal coordinate of current knight
     * @param y vertical coordinate of current knight
     * @param vertical vertical displacement value to new knight position
     * @param horizontal horizontal displacement value to new knight position
     */
    public void tryMoveKnightFromXYToLowAc(Integer[][] locAccessibilityPoints, Integer x, Integer y, Integer vertical, Integer horizontal) {

        if (Math.abs(horizontal) + Math.abs(vertical) != 3 || vertical.equals(0) || horizontal.equals(0)) {
            return;
        }
        try {
            if (!knight.contains(DESK[x + horizontal][y + vertical]))
                knight.add(DESK[x + horizontal][y + vertical]);
            locAccessibilityPoints[x + horizontal][y + vertical] = accessibilityPoints[x + horizontal][y + vertical];
        } catch (ArrayIndexOutOfBoundsException e) {
            return;
        }
    }

    /**
     * Move knight to point with lowest accessibility point
     * @param i horizontal coordinate of current knight
     * @param j vertical coordinate of current knight
     */
    public void moveToLowAccessibilityNumberFrom(Integer i, Integer j) {

        Integer I = i;
        Integer J = j;
        Integer[][] localAccessibilityPoints = new Integer[8][8];
        accessibilityPoints = getKnightAccessibilityPoints();
        knightHistory = new ArrayList<>();
        knight.clear();
        System.out.print("Start moving from " + I + ", " + J + " point.\n");
        do {
            for (int x = 0; x < 8; x++) {                                                            // Getting value of moving number
                tryMoveKnightFromXYToLowAc(localAccessibilityPoints, i, j, vertical[x], horizontal[x]);
            }

            Integer min = Operations.getMinFromTwoDimensionalArray(localAccessibilityPoints);       // Getting value with lowest accessibility point
            Integer[] toMov = Operations.getXYOfTwoDimensionalArrayPoint(localAccessibilityPoints, min);

            if (!knightHistory.contains(DESK[toMov[0]][toMov[1]])) {                                // Moving knight to lowest accessibility point

                moveKnightFromXY(i, j, toMov[0] - i, toMov[1] - j);
                knightHistory.add(DESK[toMov[0]][toMov[1]]);
                knight.clear();
                i = toMov[0];
                j = toMov[1];
                System.out.print("Point moved to " + i + ", " + j + ".\n");
            }

        } while (knight.size() == 0);
        System.out.print("Point finished on " + i + ", " + j + ".\n");
        // track[I][J] = "K";
        // track[i][j] = ((i!=I)||(j!=J))?"♞":"K";
        // showWay();

    }


}
