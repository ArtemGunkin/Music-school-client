package controller;

import api.Requests;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.School;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class LoginController {
    private TableView profileTable;
    private Tab profileTab;

    public PasswordField passwordText;
    public TextField loginText;
    public Button loginButton;
    public Text errorLabel;
    private Main main;
    private Button button;

    public void loginAction(ActionEvent actionEvent) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String profileData = Requests.getProfileData(loginText.getText(), passwordText.getText());

        if (profileData == null) {
            errorLabel.setText("Invalid login or password. Try again.");
        } else {
            List<School> schoolsList = mapper.readValue(profileData, mapper.getTypeFactory().constructCollectionType(List.class, School.class));
            main.getProfileData().addAll(schoolsList);
            profileTab.setDisable(false);

            main.setLogin(loginText.getText());
            main.setPassword(passwordText.getText());

            button.setText(loginText.getText());
            button.setDisable(true);
            close();
        }
    }

    public void setProfileTable(TableView profileTable, Tab profileTab) {
        this.profileTable = profileTable;
        this.profileTab = profileTab;
    }

    private void close() {
        ((Stage) loginButton.getScene().getWindow()).close();
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setButton(Button button) {
        this.button = button;
    }
}
