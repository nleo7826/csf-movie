package ibf2022.batch1.csf.assessment.server.services;

import java.util.List;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import csf.app.server.model.Comment;
import csf.app.server.model.Review;
import csf.app.server.repository.CharacterRepository;

import ibf2022.batch1.csf.assessment.server.models.*;
import ibf2022.batch1.csf.assessment.server.repositories.*;

@Service
public class MovieService {

	// TODO: Task 4
	// DO NOT CHANGE THE METHOD'S SIGNATURE
    @Autowired
    private MovieRepository movieRepo;

 
    public List<Comment> getAllComments(String title) {
        public Optional<List<Review>> getReview(String title) {
            return movieRepo.getReviews(title);
        }

        public Comment insertComment(Comment r){
            return movieRepo.insertComment(r);
        }

        public List<Review> searchReviews(String query) {
            return movieRepo.searchReviews(query);
        }
    }
}

