import controllers.InteractiveController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DBMLBuilderGraphApplication extends Application {
    private static InteractiveController interactiveController = new InteractiveController();

    @Override
    public void start(Stage stage) throws Exception {
        VBox root = new VBox();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./resources/fxml/interactive-dbml-builder.fxml"));
        BorderPane node =  loader.load();
        loader.setController(interactiveController);
        interactiveController.init();
        root.getChildren().add(node);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
