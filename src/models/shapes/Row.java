package models.shapes;

import basic.shapes.Shape;
import javafx.scene.canvas.GraphicsContext;
import models.RowConnection;

public class Row extends Shape {
    private int
        rowPaddingTop = 5,
        rowPaddingWidth = 10;
    private String name;
    private String type;
    private RowConnection connection;
    private Row next;

    public Row(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    public void setRowPadding(int rowPadding) {
        this.rowPaddingWidth = rowPadding;
    }
    public int getRowPadding() {
        return rowPaddingWidth;
    }

    @Override
    public double distanceFromBounds(Shape shape) {
        return 0;
    }

    @Override
    public double pointDistanceFromBounds(int i, int i1) {
        return 0;
    }

    @Override
    public void display(GraphicsContext g) {
        int
            xOffset = width/2,
            yOffset = height/2;
        //draw container
        g.setFill(currFill);
        g.fillRect(x-xOffset, y-yOffset, width, height);
        //draw text
        g.setFill(currStroke);
        g.fillText(name, x-width/2+rowPaddingWidth, y+rowPaddingTop);
        //draw outline of container
        g.setStroke(prevStroke);
        g.strokeRect(x-xOffset, y-yOffset, width, height);
    }

    @Override
    public String toString() {
        String[] _name = name.split(" : ");
        return String.format("Row %s from Table %s", _name[1], _name[0]);
    }
}
