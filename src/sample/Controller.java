package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Arrays;

public class Controller {

    @FXML
    private TextArea chatHistory;
    @FXML
    public ListView<String> usersList;
    @FXML
    private TextField inputField;
    @FXML
    private ListView<String> listView;
    @FXML
    private Button startButton;
    @FXML
    private ListView<String> listViewAbout;
    @FXML
    private Network network;
    @FXML
    Main main;

    ObservableList<String> aboutInfo =
            FXCollections.observableArrayList("Название: Net Chat", "Версия: 0.1",
                    "Создание: Ноябрь 2020 г.");



    @FXML
    public void initialize() {
        usersList.setItems(FXCollections.observableArrayList(Main.USERS_TEST_DATA));
        startButton.setOnAction(event -> Controller.this.sendMessage());
        inputField.setOnAction(event -> Controller.this.sendMessage());
        listViewAbout.setItems(aboutInfo);

    }


    private void sendMessage() {
        String message = inputField.getText();
        appendMessage(message);
        inputField.clear();

        if (network != null) {                      //без этого костыля все просто падает в NPE, причину я до сих пор не выявил
            try {                                      //подозреваю, что network у меня null, но как это исправить я хз
                network.getDataOutputStream().writeUTF(message);
            } catch (IOException e) {
                e.printStackTrace();
                String errorMessage = "Ошибка при отправке сообщения";
                Main.showErrorMessage(e.getMessage(), errorMessage);
            }
        }
    }


    public void setNetwork(Network network) {
        this.network = network;
    }



    public void appendMessage(String message) {
        for (String s : Arrays.asList(message, System.lineSeparator())) {
            chatHistory.appendText(s);
        }
    }

    @FXML
    public void exit() {
        System.exit(0);
    }

    @FXML public void newDialog() {
        main.showDialog();
    }

    public void setMain(Main main) {
        this.main = main;
    }

}
