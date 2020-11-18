package models.shapes;

import basic.shapes.MyMath;
import basic.shapes.Shape;
import javafx.scene.canvas.GraphicsContext;

public class Table extends Shape {
    private int
        tablePaddingTop = 5,
        tablePaddingWidth = 10,
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
            distance = MyMath.calculateDistance(x, y, other.getX(), other.getY());
        }
        return distance;
    }

    @Override
    public double pointDistanceFromBounds(int i, int j) {
        int
            x1, y1, x2, y2;

        int vertical;
        int horizontal;
        x1 = x-width/2;
        x2 = x+width/2;
        if(i - x1 < 0) { //past left bounds
            horizontal = -1;
            x2 = x1;
        }
        else if(i - x2 > 0) { //past right bounds
            horizontal = 1;
            x1 = x2;
        }
        else //in xbounds
            horizontal = 0;

        y1 = y-height/2;
        y2 = y+height/2;
        if(j - y1 < 0) { //past upper bounds
            vertical = 1;
            y2 = y1;
        }
        else if(j - y2 > 0) { //past bottom bounds
            vertical = -1;
            y1 = y2;
        }
        else //in y bounds
            vertical = 0;

        //either in bounds or distance from corner
        if(horizontal == 0 && vertical == 0) //in bounds
            return -1;
        double
            xPoints[] = { x1, x ,x2 },
            yPoints[] = { y1, y, y2 },
            distance = MyMath.distancePointFromLine(xPoints, yPoints);
        return distance;
    }

    @Override
    public void display(GraphicsContext g) {
        int
            xOffset = width/2,
            yOffset = height/2;
        //draw container
        g.setFill(primaryFill);
        g.fillRect(x-xOffset, y-yOffset, width, height);
        //draw text
        g.setFill(textFill);
        g.fillText(name, x-width/2+tablePaddingWidth, y + tablePaddingTop);
        //draw outline
        g.setStroke(primaryStroke);
        g.strokeRect(x-xOffset, y-yOffset, width, height);
    }

    @Override
    public String toString() {
        return String.format("table %s", name);
    }
}
