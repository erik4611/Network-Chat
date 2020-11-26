package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Controller {


    @FXML
    private TextField inputField;
    @FXML
    private ListView<String> listView;
    @FXML
    private Button startButton;
    @FXML
    private ListView<String> listViewAbout;

    private Network network;

    ObservableList<String> wordsList =
            FXCollections.observableArrayList("Привет!", "Пока");
    ObservableList<String> aboutInfo =
            FXCollections.observableArrayList("Название: Net Chat", "Версия: 0.1",
                    "Создание: Ноябрь 2020 г.");

    @FXML
    public void initialize() {
            listView.setItems(wordsList);
        listViewAbout.setItems(aboutInfo);
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Controller.this.addWordToList();
            }
        });
        inputField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Controller.this.addWordToList();
            }
        });
    }

    @FXML
    public void addWordToList() {
        String word = inputField.getText();
        if (!word.isBlank()) {
            listView.getItems().add(word);
        }
        inputField.setText("");
        try {
            network.getDataOutputStream().writeUTF(word);
        } catch (IOException e) {
            e.printStackTrace();
            String errorMessage = "Ошибка при отправке";
            Main.showErrorMessage(e.getMessage(), errorMessage);

        }


    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    @FXML
    public void exit() {
        System.exit(0);
    }

}
