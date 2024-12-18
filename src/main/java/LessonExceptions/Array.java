package LessonExceptions;

import LessonExceptions.exceptions.MyArrayDataException;
import LessonExceptions.exceptions.MyArraySizeException;

public class Array {
    public static int addArray(String[][] array) throws MyArraySizeException, MyArrayDataException {
        if (array.length != 4 || array[0].length != 4) {
            throw new MyArraySizeException("Переданы неверные аргументы. Массив должен быть 4x4");
        }
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    try {
                        sum += Integer.parseInt(array[i][j]);
                    }
                    catch (NumberFormatException e) {
                        throw new MyArrayDataException("Ошибка преобразования в int в строке " + (i + 1) + ", колонке " + (j + 1) + ". Исходное значение: " + array[i][j]);
                    }
                }

        }
        return sum;

    }
}
