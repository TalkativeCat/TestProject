package LessonExceptions;

import LessonExceptions.exceptions.MyArrayDataException;
import LessonExceptions.exceptions.MyArraySizeException;

public class Main {
    public static void main(String[] args) throws MyArraySizeException, MyArrayDataException {
        String[][] matrix0 = {
                {"1", "3", "1", "1"},
                {"1", "1", "1", "1"},
                {"1", "1", "6", "1"},
                {"1", "1", "1", "1"}
        };
        String[][] matrix1 = {
                {"1", "cGf", "1", "1"},
                {"1", "1", "1", "1"},
                {"1", "1", "g1e", "1"},
                {"1", "1", "1", "1"}
        };
        String[][] matrix2 = {
                {"1", "cGf", "1"},
                {"1", "1", "1", "1"},
                {"1", "1", "g1e", "1"},
                {"1", "1", "1", "1"}
        };
        System.out.println("Сумма значений всех преобразованных ячеек: " + Array.addArray(matrix0));
        System.out.println("Сумма значений всех преобразованных ячеек: " + Array.addArray(matrix1));
        System.out.println("Сумма значений всех преобразованных ячеек: " + Array.addArray(matrix2));

    }
}
