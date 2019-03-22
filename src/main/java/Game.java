import java.util.ArrayList;

public class Game extends Chest{


    public static   ArrayList<Integer> knight;
    public static Integer[][] knightAccessibilityPoints;

    public static void main(String[] args){
     Chest chest = new Chest();

     knight = new ArrayList<Integer>();

     //Задание №1 : фигурка двигается из квадратика, как на рисунке Fig. 7.30 из К[3,4] в пронумерованные квадраты
     Integer[] horizontal = {-2,-1,1,2};
     Integer[] vertical = {-2,-1,1,2};

        for (Integer hor:horizontal){
            for (Integer ver:vertical){
                moveKnightFromXY(3,4,ver, hor);
            }

        }

      //Задание №2 : Движение фигурки конька к клетке с меньшим  "accessibility number"

     knightAccessibilityPoints = new Integer[8][8];
      // 2.1 Построение accessibility-матрицы
        for(int i= 0;i<8;i++){
            for (int j = 0; j<8; j++){
                knight.clear();

                for (Integer hor:horizontal){
                    for (Integer ver:vertical){
                        moveKnightFromXY(i,j,ver, hor);
                    }

                }
                knightAccessibilityPoints[i][j] = knight.size();

            }
        }
        //Её вывод
        for(int i= 0;i<8;i++){
            for (int j = 0; j<8; j++){
                System.out.print(" "+knightAccessibilityPoints[i][j]);
            }
            System.out.println();
        }
        knight.clear();



    }



    public  static void moveKnightFromCurrentPosition(Integer vertical, Integer horizontal){
        System.out.println("Ход конём на "+vertical+" по вертикали и на "+horizontal+" по горизонтали.");

        Integer[] position = Chest.getFigurePosition(knight);

        Integer currentRow = position[0];
        Integer currentColumn = position[1];

        if(Math.abs(horizontal)+Math.abs(vertical)!=3||vertical.equals(0)||horizontal.equals(0)){
            try {
                throw new Exception();
            }catch (Exception e){
                System.out.println("Фигурка Конь не может переместиться на "+horizontal+" клетки по горизонтали и на "+vertical+ " по вертикали!");
                System.out.println("Чтобы переместить фигурку коня используйте значения от -2 до 2 (кроме 0) по горизонтали и вертикали" +
                        " таким образом чтобы суммарный путь был равен трём клеткам (напоминал букву Г). ");
                return;
            }

        }

            try {
                knight.add(DESK[currentRow+horizontal][currentColumn+vertical]);
            }catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Невозможно выйти за пределы шахматной клетки! Попробуйте сделать другой ход.");
            }

        System.out.println("Фигурка коня находится на клетке №"+knight.get(knight.size()-1)+".");


    }
    public  static void moveKnightFromXY(Integer x, Integer y, Integer vertical, Integer horizontal){
        System.out.println("Ход конём на "+vertical+" по вертикали и на "+horizontal+" по горизонтали из клетки c координатами "+x+", "+y+".");


        Integer currentRow = x;
        Integer currentColumn = y;

        if(Math.abs(horizontal)+Math.abs(vertical)!=3||vertical.equals(0)||horizontal.equals(0)){
            try {
                throw new Exception();
            }catch (Exception e){
                System.out.println("Фигурка Конь не может переместиться на "+horizontal+" клетки по горизонтали и на "+vertical+ " по вертикали!");
                System.out.println("Чтобы переместить фигурку коня используйте значения от -2 до 2 (кроме 0) по горизонтали и вертикали" +
                        " таким образом чтобы суммарный путь был равен трём клеткам (напоминал букву Г). ");
                return;

            }

        }

        try {
            if(!knight.contains(DESK[currentRow+horizontal][currentColumn+vertical]))
            knight.add(DESK[currentRow+horizontal][currentColumn+vertical]);
            System.out.println("Фигурка коня находится на клетке №"+knight.get(knight.size()-1)+".");
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Невозможно выйти за пределы шахматной клетки! Попробуйте сделать другой ход.");
        }



    }
}
