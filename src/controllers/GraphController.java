package controllers;

import javafx.scene.canvas.Canvas;

public class GraphController {
    private CanvasController canvasController;


    public void setCanvas(Canvas canvas) {
        canvasController = new CanvasController(canvas);
    }
}