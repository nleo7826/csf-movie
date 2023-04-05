package ibf2022.batch1.csf.assessment.server.repositories;

public class MovieRepository {

	// TODO: Task 5
	// You may modify the parameter but not the return type
	// Write the native mongo database query in the comment below
	//

	@Autowired
    private MongoTemplate mongoTemplate;

    private static final String COMMENTS_COL = "comments";

    public Comment insertComment(Comment r) {
        return mongoTemplate.insert(r, COMMENTS_COL);
    }

    public List<Comment> getAllComments(String movieId){
		return mongoTemplate.findAll(Comment.class, COMMENTS_COL);
	}

    public long getCommentCount(String movieId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("movieId").is(movieId));
        return mongoTemplate.count(query, COMMENTS_COL);

    }

	// TODO: Task 8
	// Write a method to insert movie comments comments collection
	// Write the native mongo database query in the comment below
	//
}
