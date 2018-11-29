package Facade.CustomElements;

public class HorizontalArrow extends Arrow {
    public HorizontalArrow(double startX, double startY) {
        super(startX, startY + 62.5, startX + 50, startY + 62.5, 10.0);
    }
}
