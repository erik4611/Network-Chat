package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class Main extends Application {

    public static final List<String> USERS_TEST_DATA = List.of("Alex", "Bob", "Fred");
    public Stage primaryStage;


    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("sample.fxml"));
        String PATH_SAMPLE_XML = "sample.fxml";
        Parent root = FXMLLoader.load(getClass().getResource(PATH_SAMPLE_XML));
        primaryStage.setTitle("Net Chat");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        Network network = new Network();
        if (!network.connect()) {
            showErrorMessage("", "Ошибка подключения к серверу");
        }

        Controller controller = loader.getController();
        if (controller != null) {
            controller.setNetwork(network);
            network.waitMessage(controller);
            this.primaryStage = primaryStage;
            controller.setMain(this);

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

    public void showDialog() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("dialog.fxml"));
            Parent page = loader.load();


            Stage dialogStage = new Stage();
            dialogStage.setTitle("Тестовое окно");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            DialogController controller = loader.getController();


            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
