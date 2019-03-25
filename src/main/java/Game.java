import java.util.ArrayList;

public class Game {

    public static Integer[][] DESK;
    public static ArrayList<Integer> knight;


    public static void main(String[] args){
     Chess chess = new Chess();
     DESK = Chess.DESK;

     knight = new ArrayList<Integer>();
     KnightFigure knightFigure = new KnightFigure(knight, DESK);

     //Task №1 : Write an application to move the knight around the chessboard as on Fig. 7.30

        Integer[] horizontal = {2,1,-1,-2,-2,-1,1,2};
        Integer[] vertical = {-1,-2,-2,-1,1,2,2,1};

        for (int i = 0; i<8; i++){
            knightFigure.moveKnightFromXY(3,4,horizontal[i], vertical[i]);
        }
        knightFigure.showWay();

      //Задание №2 : Движение фигурки конька к клетке с меньшим  "accessibility number"
        // 2.1 Построение accessibility-матрицы
        Integer[][] knightAccessibilityPoints;
        knightAccessibilityPoints = knightFigure.getKnightAccessibilityPoints();

        //Её вывод
        for(int i= 0;i<8;i++){
            for (int j = 0; j<8; j++){
                System.out.print(" "+knightAccessibilityPoints[i][j]);
            }

            System.out.println();
        }



    }




}
