package models;

import basic.shapes.DisplayGraphInterface;
import basic.shapes.Shape;
import javafx.scene.canvas.GraphicsContext;
import models.graph.Vertex;

public class Table extends Vertex implements DisplayGraphInterface {

    //private List<models.Row> rows = new ArrayList<>();
    private boolean primaryKey;

    public void setPrimaryKey(boolean key) {
        primaryKey = key;
    }

    public Table(String label) {
        super(label);
    }

    public Table(String label, Shape val) {
        super(label, val);
    }

    @Override
    public void display(GraphicsContext graphicsContext) {

    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int compareTo(Vertex o) {
        return super.compareTo(o);
    }
}
