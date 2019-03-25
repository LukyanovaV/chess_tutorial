import java.util.ArrayList;

public class Chess {

    public static Integer[][] DESK;

    public Integer count = 1;


    /**
     * Initialization of the chessboard, where each cell has unique number from 1 to 64
     */
    public Chess(){
        DESK = new Integer[8][8];

        for(int i= 0;i<8;i++){
            for (int j = 0; j<8; j++){
                DESK[i][j] = count;
                count++;
                System.out.print(" "+DESK[i][j]);
            }
            System.out.println();
        }


    }

    /**
     * Setting figure on the chessboard
     * @param x The coordinate of the horizontal row of cells(from 0 to 7)
     * @param y The coordinate of the vertical row of cells(from 0 to 7)
     * @return Cell number
     */
    public Integer setFigurePosition(Integer x, Integer y){
        if(x<0||x>7||y<0||y>7){
            try {
                throw new NullPointerException("Некорректные координаты клетки!");
            }catch (NullPointerException e){
                System.out.println("Установите фигурку на клетки в диапозоне от 0 до 7.");
            }
        }
        System.out.println("Установка фигурки в клетку №"+DESK[x][y]+".");
        return DESK[x][y];
    }

    /**
     * Getting figure position on the chessboard
     * @param figure figure on the chessboard
     * @return Number of Figure's cell
     */
    public static Integer[] getFigurePosition(ArrayList<Integer> figure){
        Integer[] position = new Integer[2];

        if (figure.size()==0){
            try {
                throw new NullPointerException("Данная фигурка на шахматной доске отсутствует!");
            }catch (NullPointerException e){
                System.out.println("Установите фигурку на шахматную доску!");
            }

        }
        for(int i= 0;i<8;i++){
            for (int j = 0; j<8; j++){
                if(DESK[i][j].equals(figure.get(figure.size()-1))){
                    position[0] = i;
                    position[1] = j;
                    break;
                }
            }
        }

        return position;
    }



}
