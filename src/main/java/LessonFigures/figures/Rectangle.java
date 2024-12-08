package LessonFigures.figures;

import LessonFigures.BaseFigure;
import LessonFigures.Figures;

public class Rectangle extends BaseFigure implements Figures {
    private int x;
    private int y;

    public Rectangle(String name, String bodyColor, String borderColor, int x, int y) {
        super(name, bodyColor, borderColor);
        this.x = x;
        this.y = y;
    }

    @Override
    public double calculatingPerimeter() {
        return 2 * (x + y);
    }

    @Override
    public double calculatingArea() {
        return x * y;
    }
}
