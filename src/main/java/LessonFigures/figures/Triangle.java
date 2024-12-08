package LessonFigures.figures;

import LessonFigures.BaseFigure;
import LessonFigures.Figures;

public class Triangle extends BaseFigure implements Figures {
    private double side1;
    private double side2;
    private double side3;

    public Triangle(String name, String bodyColor, String borderColor, double side1, double side2, double side3) {
        super(name, bodyColor, borderColor);
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    @Override
    public double calculatingPerimeter() {
        if (getSide1() <= 0 || getSide2() <= 0 || getSide3() <= 0) {
            throw new IllegalArgumentException("Все стороны треугольника должны быть положительными.");
        }

        // Проверка неравенства треугольника
        if ((getSide1() + getSide2() <= getSide3()) || (getSide1() + getSide3() <= getSide2()) || (getSide2() + getSide3() <= getSide1())) {
            throw new IllegalArgumentException("Сумма любых двух сторон должна быть больше третьей.");
        }

        return getSide1() + getSide2() + getSide3();
    }

    @Override
    public double calculatingArea() {
        if (getSide1() <= 0 || getSide2() <= 0 || getSide3() <= 0) {
            throw new IllegalArgumentException("Стороны треугольника должны быть положительными числами.");
        }

        // Проверка неравенства треугольника
        if ((getSide1() >= getSide2() + getSide3()) || (getSide2() >= getSide1() + getSide3()) || (getSide3() >= getSide1() + getSide2())) {
            throw new IllegalArgumentException("Не существует треугольника со сторонами " + getSide1() + ", " + getSide2() + ", " + getSide3());
        }

        // Вычисление полупериметра
        double p = (getSide1() + getSide2() + getSide3()) / 2;

        // Вычисление площади по формуле Герона
        return Math.sqrt(p * (p - getSide1()) * (p - getSide2()) * (p - getSide3()));
    }

    public double getSide1() {
        return side1;
    }

    public double getSide2() {
        return side2;
    }

    public double getSide3() {
        return side3;
    }
}
