package ibf2022.batch1.csf.assessment.server.services;

import java.util.List;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.joda.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import csf.app.server.model.Comment;
import csf.app.server.model.MarvelCharacter;
import csf.app.server.repository.CharacterRepository;

import ibf2022.batch1.csf.assessment.server.models.Review;;

@Service
public class MovieService {

	// TODO: Task 4
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	public List<Review> searchReviews(String query) {
		return null;
	}

    @Autowired
    private MarvelApiService marvelApiSvc;

    @Autowired
    private CharacterRepository charRepo;
 
 
    public Optional<List<MarvelCharacter>> getCharacters(String characterName,
            Integer limit , Integer offset){
        return marvelApiSvc.getCharacters(characterName, limit, offset);
    }

    public Review getMovieReview(String movieName) 
            throws IOException{
        MarvelCharacter cc = null;
        String detailsJson = (String)redisTemplate.opsForValue().get(characterId);
        System.out.println("" + detailsJson);
        if(detailsJson != null){
            cc = (MarvelCharacter) MarvelCharacter.createForCache(detailsJson);
        }else{
            Optional<MarvelCharacter> c  = marvelApiSvc.getCharacterDetails(characterId);
            cc = c.get();
            cc.setComments(this.getAllComments(characterId));
                
        }
        
        return cc;
    }

    public Comment insertComment(Comment r){
        return charRepo.insertComment(r);
    }

}
