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
import models.shapes.RowContainer;
import models.shapes.TableContainer;

//THIS CONTROLLER WILL HANDLE CANVAS INTERACTIONS
public class CanvasController {

    Canvas canvas;
    GraphicsContext g;
    public CanvasController(Canvas canvas) {
        this.canvas = canvas;
        if(canvas != null)
            g = canvas.getGraphicsContext2D();
    }

    public void setup(Graph graph) {
        for(Vertex v : graph.getVertices()) {
            if(v.getValue().getClass() == TableContainer.class){
                TableContainer table = (TableContainer) v.getValue();
                table.setCurrFill(Color.rgb(0,25,110));
                table.setCurrStroke(Color.rgb(250, 250, 250));
            }
            else if(v.getValue().getClass() == RowContainer.class){
                RowContainer row = (RowContainer)v.getValue();
                row.setCurrFill(Color.rgb(230, 230, 230, .9));
                row.setCurrStroke(Color.BLACK);
            }
        }
        FontWeight weight = FontWeight.THIN;
        FontPosture posture = FontPosture.REGULAR;
        double size = 14;
        Font font = new Font("Calibri", 15);
        g.setFont(font);
    }
    public void draw(Graph graph) {
        //clear background
        g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        //draw data tables
        for(Vertex v : graph.getVertices())
            if(v.getValue() != null)
                ((Shape)v.getValue()).display(canvas.getGraphicsContext2D());
    }
}