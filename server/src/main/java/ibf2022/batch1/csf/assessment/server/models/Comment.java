package main.java.ibf2022.batch1.csf.assessment.server.models;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Comment {
    private String movieId;
    private String comment;

    public String getMovieId() {
        return movieId;
    }
    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    public static Comment create(Document d) {
        Comment c = new Comment();
        c.setCharId(d.getObjectId("movieId").toString());
        c.setComment(d.getString("comment"));
        return c;
    }

    public JsonObject toJSON() {

        return Json.createObjectBuilder()
                .add("movieId", getCharId())
                .add("comment", getComment())
                .build();
    }
    
}
