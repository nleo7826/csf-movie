package main.java.ibf2022.batch1.csf.assessment.server.models;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Comment {
    private String title;
    private String comment;


    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static Comment create(Document d) {
        Comment c = new Comment();
        c.setTitle(d.getObjectName("title").toString());
        c.setComment(d.getString("comment"));
        return c;
    }

    public JsonObject toJSON() {

        return Json.createObjectBuilder()
                .add("title", getTitle())
                .add("comment", getComment())
                .build();
    }
    
}
