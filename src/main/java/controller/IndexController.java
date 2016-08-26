package controller;

import api.Requests;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.News;
import model.School;
import model.Worker;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class IndexController {
    public Tab profileTab;
    public Button loginButton;

    @FXML
    private void initialize() throws IOException {
        newsId.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        newsTitle.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        newsContent.setCellValueFactory(cellData -> cellData.getValue().contentProperty());

        workerId.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        workerFirstName.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        workerLastName.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        workerInfo.setCellValueFactory(cellData -> cellData.getValue().infoProperty());
        workerJob.setCellValueFactory(cellData -> cellData.getValue().jobTitleProperty());

        schoolId.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        schoolCost.setCellValueFactory(cellData -> cellData.getValue().costProperty());
        schoolName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        schoolWorker.setCellValueFactory(cellData -> cellData.getValue().workerProperty());

        profileSchoolId.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        profileSchoolCost.setCellValueFactory(cellData -> cellData.getValue().costProperty());
        profileSchoolName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        profileSchoolWorker.setCellValueFactory(cellData -> cellData.getValue().workerProperty());

        schoolsTable.setRowFactory(tableView -> {
            final TableRow<School> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem addSchool = new MenuItem("Start studying");

            addSchool.setOnAction(event -> {
                School school = tableView.getSelectionModel().getSelectedItem();
                updateProfile(school.getId());
            });

            contextMenu.getItems().add(addSchool);
            row.contextMenuProperty().bind(Bindings
                    .when(row.emptyProperty())
                    .then((ContextMenu) null)
                    .otherwise(contextMenu)
            );

            return row;
        });
    }

    private void updateProfile(int schoolId) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String profileData = Requests.addSchool(main.getLogin(), main.getPassword(), schoolId);
            List<School> profileList = mapper.readValue(profileData, mapper.getTypeFactory().constructCollectionType(List.class, School.class));
            main.getProfileData().clear();
            main.getProfileData().addAll(profileList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMain(Main main) {
        this.main = main;
        newsTable.setItems(main.getNewsData());
        schoolsTable.setItems(main.getSchoolsData());
        workersTable.setItems(main.getWorkersData());
        profileTable.setItems(main.getProfileData());
    }

    public void loginAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/login.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        LoginController controller = loader.getController();
        controller.setProfileTable(profileTable, profileTab);
        controller.setMain(main);
        controller.setButton(loginButton);

        stage.setResizable(false);
        stage.setTitle("Sign in");
        stage.setScene(scene);
        stage.show();
    }

    public void aboutAction(ActionEvent actionEvent) {

    }

    public void quitAction(ActionEvent actionEvent) {
        Platform.exit();
    }

    //Tables
    @FXML
    public TableView<News> newsTable;
    @FXML
    public TableView<School> schoolsTable;
    @FXML
    public TableView<Worker> workersTable;
    @FXML
    public TableView profileTable;
    //News columns
    @FXML
    public TableColumn<News, String> newsId;
    @FXML
    public TableColumn<News, String> newsTitle;
    @FXML
    public TableColumn<News, String> newsContent;
    //School columns
    @FXML
    public TableColumn<School, Number> schoolId;
    @FXML
    public TableColumn<School, String> schoolName;
    @FXML
    public TableColumn<School, Number> schoolCost;
    @FXML
    public TableColumn<School, String> schoolWorker;
    //Worker columns
    @FXML
    public TableColumn<Worker, Number> workerId;
    @FXML
    public TableColumn<Worker, String> workerFirstName;
    @FXML
    public TableColumn<Worker, String> workerLastName;
    @FXML
    public TableColumn<Worker, String> workerJob;
    @FXML
    public TableColumn<Worker, String> workerInfo;
    //Profile school columns
    @FXML
    public TableColumn<School, Number> profileSchoolId;
    @FXML
    public TableColumn<School, String> profileSchoolName;
    @FXML
    public TableColumn<School, Number> profileSchoolCost;
    @FXML
    public TableColumn<School, String> profileSchoolWorker;
    private Main main;

}
