import java.util.ArrayList;

public class Chess {

    public static Integer[][] DESK;

    public Integer count = 1;


    /**
     * Инициализация шахматной доски, где у каждой клетки свой уникальный номер от 1 до 64
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
     * Установка позиции фигурки на шахматной доске
     * @param x Координата горизонтального ряда клеток шахматной доски(от 0 до 7)
     * @param y Координата вертикального ряда клеток шахматной доски(от 0 до 7)
     * @return Номер клетки на шахматной доске
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
     * Получение позиции фигурки на шахматной доске
     * @param figure Фигурка на шахматной доске
     * @return Номер клетки на которой находится фигурка
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
