package controller;

import api.Requests;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.News;
import model.School;
import model.Worker;
import org.codehaus.jackson.map.ObjectMapper;

import java.util.List;

public class Main extends Application {
    private String login;
    private String password;

    private ObservableList<News> newsData = FXCollections.observableArrayList();
    private ObservableList<School> schoolsData = FXCollections.observableArrayList();
    private ObservableList<Worker> workersData = FXCollections.observableArrayList();
    private ObservableList<School> profileData = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<News> newsList = mapper.readValue(Requests.getAllData("news"), mapper.getTypeFactory().constructCollectionType(List.class, News.class));
        List<School> schoolsList = mapper.readValue(Requests.getAllData("schools"), mapper.getTypeFactory().constructCollectionType(List.class, School.class));
        List<Worker> workersList = mapper.readValue(Requests.getAllData("workers"), mapper.getTypeFactory().constructCollectionType(List.class, Worker.class));

        newsData.addAll(newsList);
        schoolsData.addAll(schoolsList);
        workersData.addAll(workersList);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/index.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        IndexController controller = loader.getController();
        controller.setMain(this);

        primaryStage.setResizable(false);
        primaryStage.setTitle("Music school client");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public ObservableList<News> getNewsData() {
        return newsData;
    }

    public ObservableList<School> getProfileData() {
        return profileData;
    }

    public ObservableList<Worker> getWorkersData() {
        return workersData;
    }

    public ObservableList<School> getSchoolsData() {
        return schoolsData;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
