package LessonExceptions;

public class Main {
    public static void main(String[] args) {
        String[][] matrix = {
                {"1", "cGf", "1", "1"},
                {"1", "1", "1", "1"},
                {"1", "1", "g1e", "1"},
                {"1", "1", "1", "1"}
        };
        System.out.println("Сумма значений всех преобразованных ячеек: " + Array.addArray(matrix));
        String[][] matrix1 = {
                {"1", "cGf", "1"},
                {"1", "1", "1", "1"},
                {"1", "1", "g1e", "1"},
                {"1", "1", "1", "1"}
        };
        System.out.println("Сумма значений всех преобразованных ячеек: " + Array.addArray(matrix1));



    }
}
