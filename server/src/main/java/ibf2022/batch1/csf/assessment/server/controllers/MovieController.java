package ibf2022.batch1.csf.assessment.server.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

import ibf2022.batch1.csf.assessment.server.services.MovieService;

@RestController
@RequestMapping(path="/api/list", consumes = MediaType.APPLICATION_JSON_VALUE, 
        produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieController {

	// TODO: Task 3, Task 4, Task 8    
    @Autowired
    private MovieService movieService;

    @GetMapping(path="/{title}")
    public ResponseEntity<String> getMovies(
        @RequestParam(required=true) String title) {

    JsonArray result = null;
    Optional<List<Review>> or = this.movieService.getMovieReview(title);
    List<Review> results = or.get(); 
    JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
    for (Review r : results)
        arrBuilder.add(r.toJSON());
    result = arrBuilder.build();
    return ResponseEntity
        .status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(result.toString());
}

    @GetMapping(path="/{title}")
    public ResponseEntity<String> getMovieDetails(
        @PathVariable(required=true) String title) throws IOException {
        JsonObjectBuilder ocjBuilder = Json.createObjectBuilder();
        ocjBuilder.add("details" , c.toJSON());
        JsonObject result = ocjBuilder.build();
        return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(result.toString());
    }


    @GetMapping(path="/comments/{title}")
    public ResponseEntity<String> getComments(@PathVariable(required=true) String title) {
        List<Comment> aa = this.movieService.getAllComments(title);
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        for (Comment c : aa)
            arrBuilder.add(c.toJSON());
        JsonArray result = arrBuilder.build();
        return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(result.toString());
    }
}

