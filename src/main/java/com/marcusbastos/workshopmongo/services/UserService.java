package com.marcusbastos.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcusbastos.workshopmongo.domain.User;
import com.marcusbastos.workshopmongo.dto.UserDTO;
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
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User insert(User obj) {
		return this.repo.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		this.repo.deleteById(id);
	}
	
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
}
