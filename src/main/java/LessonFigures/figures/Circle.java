package LessonFigures.figures;

import LessonFigures.BaseFigure;
import LessonFigures.Figures;

public class Circle extends BaseFigure implements Figures {

    private int radius;

    public Circle(String name, String bodyColor, String borderColor, int radius) {
        super(name, bodyColor, borderColor);
        this.radius = radius;
    }


    @Override
    public double calculatingPerimeter() {
        final double PI = Math.PI;
        return 2 * PI * radius;
    }

    @Override
    public double calculatingArea() {
        final double PI = Math.PI;
        return PI * radius * radius;
    }
}
