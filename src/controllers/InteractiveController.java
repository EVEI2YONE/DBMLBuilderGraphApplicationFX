package controllers;

import basic.shapes.Shape;
import basics.graph.Graph;
import dbBuilder.DBMLGrammarParser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.shapes.Table;

import java.io.File;

public class InteractiveController {
    private GraphController graphController = new GraphController();
    private CanvasController canvasController;
    private Graph g;
    //TODO: CONSIDERATIONS
    // 1) KEEP TRACK OF CHANGES AT LINE NUMBERS,
    // 2) WORDS DELETED AND INPUTS,
    // 3) WORRY ABOUT TIMING WHEN TO UPDATE
    // 4) ?

    //Listeners set from fxml layout whether to create a new file, save, share, or export
    //Can actually use a static file helper class
    @FXML
    TextField searchField;
    @FXML
    Button buttonNew, buttonSave, buttonShare, buttonImport;
    @FXML
    MenuButton buttonMenuExport;
    //used for updating GraphController to create new DB structures
    @FXML
    ListView lineNumbers;
    @FXML
    TextField parserInput;
    //don't use, just return and let GraphController handle it
    @FXML
    Canvas canvas;

    public void init() {
        graphController.setUp(canvas);
        canvasController = new CanvasController(canvas);
    }
    public void setTextFieldListener() {
        parserInput.textProperty().addListener(e -> {
            System.out.println("text changed");
        });
    }

    public void onActionButtonNew(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        try {
            fc.setInitialDirectory(new File("C:/Users/azva_/IdeaProjects/DBMLBuilderGraphApplicationFX/src/resources/text"));
            File selectedFile = fc.showOpenDialog(new Stage());
            String filename = selectedFile.getAbsolutePath();

            g = DBMLGrammarParser.parseDB(filename);
            graphController.setGraph(g);
            graphController.createTableList();
            graphController.calculatePlacement();
            graphController.sort();

            canvasController.setGraph(g);
            canvasController.setup();
            canvasController.draw();
        } catch (Exception ex) {
            System.out.println("Error reading file from Interactive Controller");
        }


    }

    public void onActionButtonSave(ActionEvent actionEvent) {

    }

    public void onActionButtonShare(ActionEvent actionEvent) {

    }

    public void onActionButtonImport(ActionEvent actionEvent) {

    }

    public void onActionButtonMenuExport(ActionEvent actionEvent) {

    }

    public void onTextChanged(InputMethodEvent inputMethodEvent) {

    }

    public void onKeyReleased(KeyEvent event) {

    }

    public void onKeyTyped(KeyEvent event) {

    }


    Shape container;
    StringBuilder type = new StringBuilder();
    public void onMouseDragged(MouseEvent mouseEvent) {
        int x = (int) mouseEvent.getX(),
            y = (int) mouseEvent.getY();
        if(container != null) {
            container.setX(x);
            container.setY(y);
            if(container.getClass() == Table.class)
                graphController.updateRow((Table)container);

            canvasController.draw();
        }
    }

    public void onMouseReleased(MouseEvent mouseEvent) {

    }

    public void onMousePressed(MouseEvent mouseEvent) {
        int x = (int) mouseEvent.getX(),
            y = (int) mouseEvent.getY();

        container = graphController.findContainer(x, y);
        System.out.println(container);
    }
}
