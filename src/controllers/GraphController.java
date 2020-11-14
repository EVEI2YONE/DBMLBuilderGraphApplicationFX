package controllers;

import javafx.scene.canvas.Canvas;
import models.graph.Graph;

public class GraphController {
    private CanvasController canvasController;
    private Graph graph;


    public void setCanvas(Canvas canvas) {
        canvasController = new CanvasController(canvas);
    }
    public void setGraph(Graph g) {
        graph = g;
    }

}
