package telran.java47.post.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import telran.java47.post.model.Post;

public interface PostRepository extends MongoRepository<Post, String> {

	Stream<Post> findAllByAuthor(String author);
	
	Stream<Post> findByTagsInIgnoreCase(List<String> tags);
	
	Stream<Post> findByDateCreatedBetween(LocalDate from, LocalDate to);
	
//	@Query("{'dateCreated' : {'$gte': ?0, '$lte': ?1}}")
//	Stream<Post> findAllByDate(LocalDate dateFrom, LocalDate dateTo);
}
