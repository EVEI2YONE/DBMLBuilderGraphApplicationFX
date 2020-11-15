package controllers;

import basics.graph.Graph;
import basics.graph.Vertex;
import javafx.scene.canvas.Canvas;
import models.shapes.RowContainer;
import models.shapes.TableContainer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//THIS GRAPH CONTROLLER WILL SET UP THE LOCATIONS OF THE TABLE OBJECTS
public class GraphController {
    private CanvasController canvasController;
    private Graph graph;
    private double width, height;
    private Random random = new Random();

    public void setUp(Canvas canvas) {
        width = canvas.getWidth();
        height = canvas.getHeight();
    }
    public void setGraph(Graph g) {
        graph = g;
    }
    public void calculatePlacement() {
        if(graph == null) return;
        if(width < 1 || height < 1) return;
        List<Vertex> tables = new ArrayList<>();
        List<Vertex> rows = new ArrayList<>();

        int tableWidth = 150, tableHeight = 45;
        int rowWidth = 150, rowHeight = 45;
        List<Integer> xPoints = new ArrayList<>();
        List<Integer> yPoints = new ArrayList<>();
        for(Vertex v : graph.getVertices()) {
            if(v.getValue() == null) continue;
            if (v.getValue().getClass() == TableContainer.class) {
                TableContainer table = (TableContainer) v.getValue();
                table.setX(random.nextInt((int) width - tableWidth) + tableWidth/2);
                table.setY(random.nextInt((int) height - tableHeight) + tableHeight/2);
                table.setWidth(tableWidth);
                table.setHeight(tableHeight);
                int rowCount = 0;
                for (Vertex v2 : v.getAdjacencyList()) {
                    if(v2.getValue() == null) continue;
                    RowContainer row = (RowContainer) v2.getValue();
                    int yOffset = tableHeight/2 + rowHeight * rowCount + rowHeight / 2;
                    row.setX(table.getX());
                    row.setY(table.getY() + yOffset);
                    row.setWidth(rowWidth);
                    row.setHeight(rowHeight);
                    rowCount++;
                }
            }
        }

        //TODO: THINK MORE ABOUT CALCULATING OPTIMAL DATABASE TABLE PLACEMENT
        // - MAYBE JUST THE CENTER
        // - MAYBE AN OPEN/NON-OVERLAPPING AREA?
        int
            canvasPadding = 20,
            TableContainerMargin = 20,
            itemsAcross = (int)(width-canvasPadding*2), //table container width
            targetMargin = (itemsAcross-1); // table container margin
            itemsAcross = itemsAcross - targetMargin;
        int targetWidth = itemsAcross*30/20,
            targetHeight = (tables.size() / targetWidth);
    }
}
