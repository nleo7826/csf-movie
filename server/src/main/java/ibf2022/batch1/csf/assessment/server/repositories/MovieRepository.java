package ibf2022.batch1.csf.assessment.server.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import ibf2022.batch1.csf.assessment.server.models.*;

@Repository
public class MovieRepository {

	// TODO: Task 5
	// You may modify the parameter but not the return type
	// Write the native mongo database query in the comment below
	//

	@Autowired
    private MongoTemplate mongoTemplate;

    private static final String COMMENTS_COL = "comments";

    // db.comments.insertOne({
    //     "title": <title>,
    //     "comment": <comment>,
    // })
    
    public Comment insertComment(Comment r) {
        return mongoTemplate.insert(r, COMMENTS_COL);
    }

    // db.comments.find({})

    public List<Comment> getAllComments(String title){
		return mongoTemplate.findAll(Comment.class, COMMENTS_COL);
	}

    // db.comments.count({
    //     "title": <title>
    // })

    public long getCommentCount(String ) {
        Query query = new Query();
        query.addCriteria(Criteria.where("title").is(title));
        return mongoTemplate.count(query, COMMENTS_COL);
    }

	// TODO: Task 8
	// Write a method to insert movie comments comments collection
	// Write the native mongo database query in the comment below
	//
}
