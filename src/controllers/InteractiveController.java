package controllers;

import basic.shapes.Shape;
import basics.graph.Graph;
import dbBuilder.DBMLGrammarParser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.shapes.Table;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
    TextArea parserInput;
    //don't use, just return and let GraphController handle it
    @FXML
    VBox inputContainer;
    @FXML
    Canvas canvas;

    List<TextInputController> inputControllers = new ArrayList<>();
    public void init() {
        graphController.setUp(canvas);
        canvasController = new CanvasController(canvas);
//        try {
////          TextInputController.setContainer(inputContainer);
////          TextInputController.createNew();
//            //Creating the scroll pane
////            ScrollPane scroll = new ScrollPane();
////            scroll.setPrefSize(265, 800);
//            TableView scroll = new TableView();
//            Label temp = new Label("1  ");
//            temp.setPrefWidth(20);
//            TextArea area = new TextArea("test");
//            area.setPrefWidth(265);
//            HBox box = new HBox();
//            box.getChildren().add(temp);
//            box.getChildren().add(area);
//
//            scroll.setFocusTraversable(false);
//            box.setFocusTraversable(false);
//            temp.setFocusTraversable(false);
//            area.setFocusTraversable(false);
//
////            inputContainer.getChildren().add(scroll);
//        } catch(Exception ex) { }
    }

    public void onActionButtonNew(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        try {
            fc.setInitialDirectory(new File("C:/Users/azva_/IdeaProjects/DBMLBuilderGraphApplicationFX/src/resources/text"));
            File selectedFile = fc.showOpenDialog(new Stage());
            String filename = selectedFile.getAbsolutePath();

            g = DBMLGrammarParser.parseDB(filename);
            update();
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

    public void update() {
        if(g == null) return;
        graphController.setGraph(g);
        graphController.createTableList();
        graphController.calculatePlacement();
        graphController.sort();

        canvasController.setGraph(g);
        canvasController.setup();
        canvasController.draw();
    }

    private long lastReleased = 0;
    public void onKeyReleased(KeyEvent event) {
        String input = parserInput.getText();
        g = DBMLGrammarParser.build(input);
        update();
        lastReleased = System.currentTimeMillis();
        sync = true;
    }

    public void start() {
        running = true;
        Thread timer = new Thread(() -> {
            sync();
        });
        //timer.start();
    }
    public void stop() {
        running = false;
    }

    private boolean
        running = false,
        sync = false,
        sleeping = false;
    private void sync() {
        long end;
        while(running) {
            end = 0;
            if(sync) {
                try {
                    Thread.sleep(200);
                    end = System.currentTimeMillis();
                } catch (Exception ex) { }
                if(end - lastReleased > 201) {
                    lastReleased = end * 2;
                    String input = parserInput.getText();
                    g = DBMLGrammarParser.build(input);
                    update();
                    sync = false;
                }
            }
        }
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
    }
}
