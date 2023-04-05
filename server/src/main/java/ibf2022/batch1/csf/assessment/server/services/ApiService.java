package main.java.ibf2022.batch1.csf.assessment.server.services;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import ibf2022.batch1.csf.assessment.server.models.*;

public class ApiService {

    @Value("${api.url}")
    private String apiUrl;

    @Value("${api.key}")
    private String publicApiKey;

    @Value("${api.secret.key}")
    private String secretApiKey;

    public Optional<List<Review>> getReview(String title){
        ResponseEntity<String> resp = null;
        List<Review> c = null;
        System.out.println(publicApiKey);
        
        String nyApiUrl = UriComponentsBuilder
                                    .fromUriString(apiUrl + "reviews/search.json")
                                    .queryParam("title", title)
                                    .queryParam("api-key", publicApiKey.trim())
                                    .toUriString();
        System.out.println(nyApiUrl);
        RestTemplate template = new RestTemplate();
        resp = template.getForEntity(nyApiUrl,String.class);
        System.out.println(resp);
        try {
            c = Review.create(resp.getBody());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(c);
        if(c != null)
            return Optional.of(c);                        
        return Optional.empty();
    }
    
}