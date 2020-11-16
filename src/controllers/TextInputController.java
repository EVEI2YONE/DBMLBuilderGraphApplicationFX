package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextInputController {
    private static List<HBox> lines = new ArrayList<>();
    private static StringBuilder input = new StringBuilder();
    private static VBox root;
    private static int
            lineNumberLimit = 32,
            lineNumberCurrent = 1;
    @FXML
    Label labelNumber;
    @FXML
    TextArea parserInput;

    private int lineNumber;
    public TextInputController() {
        this.lineNumber = lines.size() + 1;
    }

    public static void setContainer(VBox inputContainer) {
        root = inputContainer;
    }

    public static void createNew() throws IOException {
        FXMLLoader loader = new FXMLLoader(InteractiveController.class.getResource("../resources/fxml/text-input.fxml"));
        TextInputController inputControl = new TextInputController();
        loader.setController(inputControl);
        HBox lineNumber = loader.load();
        inputControl.init(lineNumber);
        lines.add(lineNumber);
        root.getChildren().add(lineNumber);
    }



    //onInputMethodTextChanged="#onTextChanged" onKeyReleased="#onKeyReleased" onKeyTyped="#onKeyTyped"
    public void onTextChanged(InputMethodEvent inputMethodEvent) {

    }

    public void onKeyReleased(KeyEvent event) {

    }

    public void onKeyTyped(KeyEvent event) throws IOException {
        try {
            if (event.getCharacter().equals("\r")) { // 'Enter'
                createNew();
                parserInput.setText(parserInput.getText().trim());
                System.out.println("creating new section! " + lines.size());
            }
            else if(event.getCharacter().equals("\b")) { // 'backspace'

            }
        } catch(Exception e) {

        }
    }

    public void onMouseClicked(MouseEvent event) {
        System.out.println(getLabel());
    }

    public void init(HBox parent) {
        setLabel();
        parserInput.setOnInputMethodTextChanged(e -> {
            onTextChanged(e);
        });
        parserInput.setOnKeyReleased(e -> {
            onKeyReleased(e);
        });
        parserInput.setOnKeyTyped(e -> {
            try {
                onKeyTyped(e);
            } catch (IOException ioException) { }
        });
        parserInput.setOnMouseClicked(e -> {
            onMouseClicked(e);
        });
        parent.setOnMouseClicked(e -> {
            onMouseClicked(e);
        });
    }

    private void setLabel() {
        labelNumber.setText(lineNumber + "  ");
    }
    private String getLabel() { return labelNumber.getText(); }
}
