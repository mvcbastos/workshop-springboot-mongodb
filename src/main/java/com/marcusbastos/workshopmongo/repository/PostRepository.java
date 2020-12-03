package com.marcusbastos.workshopmongo.repository;

import java.util.Date;
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
	
	// Como comments é uma collection, eu tenho que especificar o campo da collection cujo conteúdo eu quero comparar, colocando um ponto após o campo 'comments'	
	@Query("{ $and: [ { date: {$gte: ?1} }, { date: { $lte: ?2} }, { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);
}
