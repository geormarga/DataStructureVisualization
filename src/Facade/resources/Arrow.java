package Facade.resources;

import javafx.beans.NamedArg;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

/**
 * @author kn
 */
public class Arrow extends Path {
    private static final double defaultArrowHeadSize = 10.0;


    public Arrow(double startX, double startY, double endX, double endY, double arrowHeadSize) {

        super();

        strokeProperty().bind(fillProperty());
        setFill(Color.WHITE);

        //Line
        getElements().add(new MoveTo(startX, startY));
        getElements().add(new LineTo(endX, endY));

        //ArrowHead
        double angle = Math.atan2((endY - startY), (endX - startX)) - Math.PI / 2.0;
        double sin = Math.sin(angle);
        double cos = Math.cos(angle);
        //point1
        double x1 = (-1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * arrowHeadSize + endX;
        double y1 = (-1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * arrowHeadSize + endY;
        //point2
        double x2 = (1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * arrowHeadSize + endX;
        double y2 = (1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * arrowHeadSize + endY;

        getElements().add(new LineTo(x1, y1));
        getElements().add(new LineTo(x2, y2));
        getElements().add(new LineTo(endX, endY));
    }

    public Arrow(@NamedArg("startX") double startX, @NamedArg("startY") double startY, @NamedArg("endX") double endX, @NamedArg("endY") double endY) {
        this(startX + 62.5, startY, endX + 62.5, endY + 50, defaultArrowHeadSize);
    }

    public Arrow(double startX, double startY){
        this(startX + 62.5, startY, startX + 62.5, startY + 50, defaultArrowHeadSize);
    }
}