package models.shapes;

import basic.shapes.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Table extends Shape {
    private int
        tablePaddingTop = 5,
        tablePaddingWidth = 15,
        tableHeight;
    private String name;

    private boolean primaryKey;

    public void setTableHeight(int height) {
        this.tableHeight = height;
    }
    public int getTableHeight() { return tableHeight; }
    public void setPrimaryKey(boolean key) {
        primaryKey = key;
    }
    public boolean hasPrimaryKey() { return primaryKey; }
    public String getName() { return name ; }

    public Table(String name) {
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
        double distance = 0;
        if(shape.getClass() == Table.class) {
            Table other = (Table) shape;
            int x2 = (x - other.getX());
            int y2 = (y - other.getY());
            distance = Math.sqrt(x2*x2 + y2*y2);
        }
        return distance;
    }

    @Override
    public double pointDistanceFromBounds(int i, int i1) {
        int vertical = (i - x < 0) ? -1 : 1;
        int horizontal = (i - y < 0) ? -1 : 1;
        double distance = 0;
        //TODO: WORK ON POINT FROM TABLE (BOX) FORMULA
        if(distance < 0) {

        }
        return distance;
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
