package com.marcusbastos.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcusbastos.workshopmongo.domain.User;
import com.marcusbastos.workshopmongo.repository.UserRepository;
import com.marcusbastos.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){
		return this.repo.findAll();
	}
	
	public User findById(String id){
		Optional<User> user = this.repo.findById(id);
		if (user.isEmpty()) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
		return user.get();
	}
}
