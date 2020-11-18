package controllers;

import basic.shapes.Shape;
import basics.graph.Graph;
import basics.graph.Vertex;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import models.shapes.Row;
import models.shapes.Table;

//THIS CONTROLLER WILL HANDLE CANVAS INTERACTIONS
public class CanvasController {
    Canvas canvas;
    GraphicsContext g;
    Graph graph;
    public CanvasController(Canvas canvas) {
        this.canvas = canvas;
        if(canvas != null)
            g = canvas.getGraphicsContext2D();
    }
    public void setGraph(Graph graph) { this.graph = graph; }

    public void setup() {
        if(graph == null) return;
        for(Vertex v : graph.getVertices()) {
            if(v.getValue().getClass() == Table.class){
                Table table = (Table) v.getValue();
                Color
                    tableContainer = Color.rgb(0,55,180),
                    textFill = Color.rgb(250, 250, 250);
                table.setPrimaryFill(tableContainer);
                table.setTextFill(textFill);
                //table.setSecondaryStroke(tableContainer);
            }
            else if(v.getValue().getClass() == Row.class){
                Row row = (Row)v.getValue();
                Color rowContainer = Color.rgb(230, 230, 230, .9);
                row.setPrimaryFill(rowContainer);
                row.setPrimaryStroke(Color.BLACK);
                row.setTextFill(Color.BLACK);
                //row.setSecondaryStroke(rowContainer);
            }
        }
        FontWeight weight = FontWeight.THIN;
        FontPosture posture = FontPosture.REGULAR;
        Font font = new Font("Calibri", 16);
        g.setFont(font);
    }
    public void draw() {
        if(graph == null) return;
        //clear background
        g.setFill(Color.WHITE);
        g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        g.setStroke(Color.BLACK);
        g.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());

        //draw data tables
        for(Vertex v : graph.getVertices())
            if(v.getValue() != null)
                ((Shape)v.getValue()).display(canvas.getGraphicsContext2D());
    }
}