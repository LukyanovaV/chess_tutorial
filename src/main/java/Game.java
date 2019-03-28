/**
 * Game
 * Class of main method, which provide algorithm of knight movement
 * Version info 1.0
 *
 * Copyright notice
 */

import java.util.ArrayList;

public class Game {

    public static Integer[][] DESK;
    public static ArrayList<Integer> knight;
    public static ArrayList<Integer> knightHistory;


    public static void main(String[] args) {
        Chess chess = new Chess();
        DESK = Chess.DESK;

        knight = new ArrayList<Integer>();
        KnightFigure knightFigure = new KnightFigure(knight, DESK);

        //Task №1 : Write an application to move the knight around the chessboard as on Fig. 7.30

        Integer[] horizontal = {2, 1, -1, -2, -2, -1, 1, 2};
        Integer[] vertical = {-1, -2, -2, -1, 1, 2, 2, 1};

        for (int i = 0; i < 8; i++) {
            knightFigure.moveKnightFromXY(3, 4, horizontal[i], vertical[i]);
        }
        knightFigure.showWay();

        //Task №2 : Write a new version of app, using the accessibility heuristic.
        // Knight should always move to the square with the lowest "accessibility number"
        // 2.1 Make accessibility-matrix
        Integer[][] knightAccessibilityPoints;
        knightAccessibilityPoints = knightFigure.getKnightAccessibilityPoints();
        knightHistory = new ArrayList<Integer>();

        //Output
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(" " + knightAccessibilityPoints[i][j]);
            }
            System.out.println();
        }
        System.out.println();

        //Move to the square with the lowest accessibility number
        knightFigure.moveToLowAccessibilityNumberFrom(0, 0);
        knightFigure.moveToLowAccessibilityNumberFrom(1, 1);
        knightFigure.moveToLowAccessibilityNumberFrom(3, 2);


    }


}
