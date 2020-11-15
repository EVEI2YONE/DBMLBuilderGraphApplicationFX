package controllers;

import basics.graph.Graph;
import basics.graph.Vertex;
import javafx.scene.canvas.Canvas;
import models.shapes.Row;
import models.shapes.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//THIS GRAPH CONTROLLER WILL SET UP THE LOCATIONS OF THE TABLE OBJECTS
public class GraphController {
    private CanvasController canvasController;
    private Graph graph;
    private double width, height;
    private Random random = new Random();
    private List<Vertex> tableList = new ArrayList<>();

    public void setUp(Canvas canvas) {
        width = canvas.getWidth();
        height = canvas.getHeight();
    }
    public void setGraph(Graph g) {
        graph = g;
    }

    public void createTableList() {
        for(Vertex v : graph.getVertices()) {
            if(v.getValue() == null) continue;
            if (v.getValue().getClass() == Table.class) {
                tableList.add(v);
            }
        }
    }

    public void calculatePlacement() {
        if(graph == null) return;
        if(width < 1 || height < 1) return;
        int tableWidth = 150, tableHeight = 45;
        for(Vertex v : tableList) {
            Table table = (Table) v.getValue();
            table.setX(random.nextInt((int) width - tableWidth) + tableWidth / 2);
            table.setY(random.nextInt((int) height - tableHeight) + tableHeight / 2);
            table.setWidth(tableWidth);
            table.setHeight(tableHeight);
            calculateRowPlacement(v);
        }
    }

    public void calculateRowPlacement(Vertex v) {
        Table table = (Table)v.getValue();
        int tableHeight = table.getHeight();
        int rowWidth = 150, rowHeight = 45;
        int rowCount = 0;
        int height = tableHeight;
        for (Vertex v2 : v.getAdjacencyList()) {
            if (v2.getValue() == null) continue;
            Row row = (Row) v2.getValue();
            int yOffset = tableHeight / 2 + rowHeight * rowCount + rowHeight / 2;
            row.setX(table.getX());
            row.setY(table.getY() + yOffset);
            row.setWidth(rowWidth);
            row.setHeight(rowHeight);
            rowCount++;
            height += rowHeight;
        }
        table.setTableHeight(height);
    }

    private int padding = 20;
    public void sort() {
        if(graph == null) return;
        int x = padding, y = padding; //initial padding
        for (Vertex v : tableList) {
            Table t = (Table) v.getValue();

        }
    }

    public boolean inBounds(Table t){
        int tableHeight = t.getTableHeight();
        int height = t.getHeight() + tableHeight/2;
        int canvasWidth = (int) canvasController.canvas.getWidth();
        int canvasHeight = (int) canvasController.canvas.getHeight();
        if(t.pointDistanceFromBounds(0, height) < padding)
            return false; //left bounds
        else if(t.pointDistanceFromBounds(t.getWidth(), 0) < padding)
            return false; //top bounds
        else if(t.pointDistanceFromBounds(canvasWidth, height) < padding)
            return false; //rigth bounds
        else if(t.pointDistanceFromBounds(t.getX(), canvasHeight) < padding)
            return false; //bottom bounds
        return true;
    }
}
