import java.util.ArrayList;

public class KnightFigure {


    public  ArrayList<Integer> knight;
    public  String[][] track;
    public  Integer[][] DESK;

    public KnightFigure(ArrayList<Integer> knight, Integer[][] desk){
        this.knight = knight;
        this.DESK = desk;
        track = new String[8][8];

        for(int i= 0;i<8;i++){
            for (int j = 0; j<8; j++){
                track[i][j] = desk[i][j].toString();
            }
        }

        return;
    }


    public void moveKnightFromXY( Integer x, Integer y, Integer vertical, Integer horizontal){
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
                drawWay(currentRow,currentColumn,horizontal,vertical);
            System.out.println("Фигурка коня находится на клетке №"+knight.get(knight.size()-1)+".");
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Невозможно выйти за пределы шахматной клетки! Попробуйте сделать другой ход.");
        }


    }

    public void drawWay(Integer currentRow, Integer currentColumn, Integer horizontal, Integer vertical){
        if(horizontal>0) {
            for (int i = currentRow; i <= currentRow + horizontal; i++) {
                track[i][currentColumn]= "↓";
            }
        }
        else {
            for (int i = currentRow; i >= currentRow + horizontal; i--) {
                track[i][currentColumn]= "↑";
            }
        }
        if (vertical>0){
            for (int i = currentColumn;i <= vertical + currentColumn; i++ ){
                track[currentRow][i]= "->";
            }

        }else {
            for (int i = currentColumn;i >= vertical + currentColumn; i-- ){
                track[currentRow][i]= "<-";
            }
        }
        track[currentRow][currentColumn] = "K";
        track[currentRow+horizontal][currentColumn+vertical] = "♞";

    }

    public void showWay(){
        for(int i= 0;i<8;i++){
            for (int j = 0; j<8; j++){
                System.out.print(" "+track[i][j]);
            }

            System.out.println();
        }
    }

    public void resetWay(){
        for(int i= 0;i<8;i++){
            for (int j = 0; j<8; j++){
                track[i][j]=DESK[i][j].toString();
            }
        }
    }

    public Integer[][] getKnightAccessibilityPoints( ){
        Integer[][] knightAccessibilityPoints = new Integer[8][8];
        Integer[] horizontal = {2,1,-1,-2,-2,-1,1,2};
        Integer[] vertical = {-1,-2,-2,-1,1,2,2,1};
        for(int i= 0;i<8;i++){
            for (int j = 0; j<8; j++){
                knight.clear();

                for (int x = 0; x<8; x++){

                    moveKnightFromXY(i,j,horizontal[x], vertical[x]);

                }
                knightAccessibilityPoints[i][j] = knight.size();

            }

        }
        resetWay();
        knight.clear();
        return knightAccessibilityPoints;
    }

    public void tryTOmoveKnightFromXY( Integer x, Integer y, Integer vertical, Integer horizontal){
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

    public void moveKnightFromCurrentPosition(Integer vertical, Integer horizontal){
        System.out.println("Ход конём на "+vertical+" по вертикали и на "+horizontal+" по горизонтали.");

        Integer[] position = Chess.getFigurePosition(knight);

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
}
