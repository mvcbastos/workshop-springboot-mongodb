package com.marcusbastos.workshopmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.marcusbastos.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{

	// Quando for necessário aproveitar o parâmetro do método na consulta, usa-se ?, o 0 é pra indicar que é o primeiro parâmetro
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	List<Post> searchTitle(String text);
	
	List<Post> findByTitleContainingIgnoreCase(String text);
}
