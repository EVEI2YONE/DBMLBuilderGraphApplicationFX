package controllers;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

public class InteractiveController {
    @FXML
    TextField searchField;
    @FXML
    Button buttonNew, buttonSave, buttonShare;
    @FXML
    MenuButton buttonMenuExport;
    @FXML
    ListView lineNumbers;
    @FXML
    TextField parserInput;
    @FXML
    Canvas canvas;
}
