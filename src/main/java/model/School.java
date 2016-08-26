package model;

import javafx.beans.property.*;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public class School {
    private final IntegerProperty id;
    private final IntegerProperty cost;
    private final BooleanProperty enable;
    private final StringProperty name;
    private final StringProperty info;
    private final StringProperty worker;

    @JsonCreator
    public School(@JsonProperty("id") int id,
                  @JsonProperty("cost") int cost,
                  @JsonProperty("enable") boolean enable,
                  @JsonProperty("name") String name,
                  @JsonProperty("info") String info,
                  @JsonProperty("worker") Worker worker,
                  @JsonProperty("users") List<Object> users) {

        this.id = new SimpleIntegerProperty(id);
        this.cost = new SimpleIntegerProperty(cost);
        this.enable = new SimpleBooleanProperty(enable);
        this.name = new SimpleStringProperty(name);
        this.info = new SimpleStringProperty(info);
        this.worker = new SimpleStringProperty(worker.getFullName());
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

    public int getCost() {
        return cost.get();
    }

    public IntegerProperty costProperty() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost.set(cost);
    }

    public boolean getEnable() {
        return enable.get();
    }

    public BooleanProperty enableProperty() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable.set(enable);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
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

    public String getWorker() {
        return worker.get();
    }

    public StringProperty workerProperty() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker.set(worker);
    }
}
