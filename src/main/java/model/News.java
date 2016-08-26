package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class News {
    private final StringProperty title;
    private final StringProperty content;
    private final StringProperty date;
    private final StringProperty id;

    @JsonCreator
    public News(@JsonProperty("id") String id,
                @JsonProperty("title") String title,
                @JsonProperty("content") String content,
                @JsonProperty("pubDate") String date) {

        this.id = new SimpleStringProperty(id);
        this.title = new SimpleStringProperty(title);
        this.content = new SimpleStringProperty(content);
        this.date = new SimpleStringProperty(date);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getContent() {
        return content.get();
    }

    public StringProperty contentProperty() {
        return content;
    }

    public void setContent(String content) {
        this.content.set(content);
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }
}
