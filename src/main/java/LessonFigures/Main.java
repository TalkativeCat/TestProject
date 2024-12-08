package LessonFigures;

import LessonFigures.figures.Circle;
import LessonFigures.figures.Rectangle;
import LessonFigures.figures.Triangle;

public class Main {
    public static void main(String[] args) {
        Triangle triangle = new Triangle("Треугольник1", "white", "red", 12, 10, 3);
        System.out.println("\nДанные " + triangle.getName() + ":");
        System.out.println("Площадь: " + triangle.calculatingArea());
        System.out.println("Периметр: " + triangle.calculatingPerimeter());
        System.out.println("Цвет фона: " + triangle.getBodyColor());
        System.out.println("Цвет границ: " + triangle.getBorderWidth());

        Circle circle = new Circle("Круг1", "black", "red", 16);
        System.out.println("\nДанные " + circle.getName() + ":");
        System.out.println("Площадь: " + circle.calculatingArea());
        System.out.println("Периметр: " + circle.calculatingPerimeter());
        System.out.println("Цвет фона: " + circle.getBodyColor());
        System.out.println("Цвет границ: " + circle.getBorderWidth());

        Rectangle rectangle = new Rectangle("Прямоугольник1", "yellow", "green", 48, 21);
        System.out.println("\nДанные " + rectangle.getName() + ":");
        System.out.println("Площадь: " + rectangle.calculatingArea());
        System.out.println("Периметр: " + rectangle.calculatingPerimeter());
        System.out.println("Цвет фона: " + rectangle.getBodyColor());
        System.out.println("Цвет границ: " + rectangle.getBorderWidth());

    }
}
