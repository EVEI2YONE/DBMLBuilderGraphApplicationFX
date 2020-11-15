package models.shapes;

import basic.shapes.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TableContainer extends Shape {
    private int
        tablePaddingTop = 5,
        tablePaddingWidth = 15;
    private String name;

    private boolean primaryKey;

    public void setPrimaryKey(boolean key) {
        primaryKey = key;
    }
    public boolean hasPrimaryKey() { return primaryKey; }
    public String getName() { return name ; }

    public TableContainer(String name) {
        super();
        this.name = name;
    }

    public int getTablePadding() {
        return tablePaddingWidth;
    }
    public void setTablePadding(int tablePadding) {
        this.tablePaddingWidth = tablePadding;
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
        g.fillText(name, x-width/2+tablePaddingWidth, y);
        //draw outline
        g.setStroke(Color.BLACK);
        g.strokeRect(x-xOffset, y-yOffset, width, height);
    }
}
