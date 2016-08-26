package model;

import javafx.beans.property.*;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class Worker {
    private final IntegerProperty id;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty jobTitle;
    private final StringProperty info;

    @JsonCreator
    public Worker(@JsonProperty("id") int id,
                  @JsonProperty("firstName") String firstName,
                  @JsonProperty("lastName") String lastName,
                  @JsonProperty("info") String info,
                  @JsonProperty("jobTitle") String jobTitle) {

        this.id = new SimpleIntegerProperty(id);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.info = new SimpleStringProperty(info);
        this.jobTitle = new SimpleStringProperty(jobTitle);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getJobTitle() {
        return jobTitle.get();
    }

    public StringProperty jobTitleProperty() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle.set(jobTitle);
    }

    public String getInfo() {
        return info.get();
    }

    public StringProperty infoProperty() {
        return info;
    }

    public void setInfo(String info) {
        this.info.set(info);
    }

    public String getFullName(){
        return getFirstName() + " " + getLastName();
    }
}
