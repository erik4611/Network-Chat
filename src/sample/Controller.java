package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class Controller {


    @FXML
    private TextField inputField;
    @FXML
    private ListView<String> listView;
    @FXML
    private Button startButton;
    @FXML
    private ListView<String> listViewAbout;

    ObservableList<String> wordsList =
            FXCollections.observableArrayList("Привет!", "Пока");
    ObservableList<String> aboutInfo =
            FXCollections.observableArrayList("Название: Net Chat", "Версия: 0.1",
                    "Создание: Ноябрь 2020 г.");

    @FXML
    public void initialize() {
            listView.setItems(wordsList);
        listViewAbout.setItems(aboutInfo);
    }

    @FXML
    public void addWordToList() {
        String word = inputField.getText();
        if (!word.isBlank()) {
            listView.getItems().add(word);
        }
        inputField.setText("");
    }

    @FXML
    public void exit() {
        System.exit(0);
    }

}
