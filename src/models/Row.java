package models;

import basic.shapes.Shape;
import basics.graph.Vertex;

public class Row extends Vertex {



    public Row(String label) {
        super(label);
    }

    public Row(String label, Shape val) {
        super(label, val);
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
