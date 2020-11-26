package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Main extends Application {

    private final String PATH_SAMPLE_XML = "sample.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource(PATH_SAMPLE_XML));
        primaryStage.setTitle("Net Chat");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        Network network = new Network();
        if (!network.connect()) {
            showErrorMessage("", "Ошибка подключения к серверу");
        }

        Controller viewController = loader.getController();
        if (viewController != null) {
            viewController.setNetwork(network);
            network.waitMessage(viewController);
            primaryStage.setOnCloseRequest(windowEvent -> network.close());
        }




    }

    public static void showErrorMessage(String message, String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Проблемы с соединением");
        alert.setHeaderText(errorMessage);
        alert.setContentText(message);
        alert.showAndWait();

    }


    public static void main(String[] args) {
        launch(args);
    }


}
