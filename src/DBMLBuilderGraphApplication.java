import controllers.InteractiveController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DBMLBuilderGraphApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        InteractiveController interactiveController;
        VBox root = new VBox();
        //load fxml and add
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./resources/fxml/interactive-dbml-builder.fxml"));
        BorderPane node =  loader.load();
        root.getChildren().add(node);
        //set up controller and initailize other controllers
        interactiveController = loader.getController();
        interactiveController.init();
        interactiveController.setTextFieldListener();
        //start application
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
