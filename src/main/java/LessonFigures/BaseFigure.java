package LessonFigures;

public class BaseFigure {
    private String name;
    private String bodyColor;
    private String borderColor;

    public BaseFigure(String name, String bodyColor, String borderColor) {
        this.name = name;
        this.bodyColor = bodyColor;
        this.borderColor = borderColor;
    }

    public String getName() {
        return name;
    }

    public String getBodyColor() {
        return bodyColor;
    }

    public String getBorderWidth() {
        return borderColor;
    }

}
