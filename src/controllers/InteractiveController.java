package controllers;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;

public class InteractiveController {
    private GraphController graphController = new GraphController();
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
    Button buttonNew, buttonSave, buttonShare;
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
        graphController.setCanvas(canvas);
    }

    public void onTextChanged(InputMethodEvent inputMethodEvent) {

    }

    public void onKeyReleased(KeyEvent event) {

    }

    public void onKeyTyped(KeyEvent event) {

    }
}
