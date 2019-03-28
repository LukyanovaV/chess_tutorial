/**
 * Operations
 * The class provides operations with arrays
 *
 * Version info 1.0
 *
 * Copyright notice
 */
public class Operations {

    /**
     * Get minimal value of two-dimensional array
     * @param array current array
     * @return minimal value
     */
    public static Integer getMinFromTwoDimensionalArray(Integer[][] array) {
        Integer min = 9;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (!(array[i][j] == null) && array[i][j] < min) {
                    min = array[i][j];
                }
            }
        }

        return min;
    }

    /**
     * Get indexes of value from two-dimensional array
     * @param array current array
     * @param point value from two-dimensional array
     * @return indexes, where [0] - x horizontal coordinate, [1] - y vertical coordinate
     */
    public static Integer[] getXYOfTwoDimensionalArrayPoint(Integer[][] array, Integer point) {
        Integer[] xy = new Integer[2];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (!(array[i][j] == null) && array[i][j].equals(point)) {
                    xy[0] = i;
                    xy[1] = j;
                }
            }
        }
        return xy;
    }

}
