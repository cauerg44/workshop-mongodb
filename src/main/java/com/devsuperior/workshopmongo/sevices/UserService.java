package com.devsuperior.workshopmongo.sevices;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.devsuperior.workshopmongo.models.dto.PostDTO;
import com.devsuperior.workshopmongo.models.dto.UserDTO;
import com.devsuperior.workshopmongo.models.entities.User;
import com.devsuperior.workshopmongo.repositories.UserRepository;
import com.devsuperior.workshopmongo.sevices.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<UserDTO> findAll() {
		List<User> list = repository.findAll();
		return list.stream().map(x -> new UserDTO(x)).toList();
	}
	
	public UserDTO findById(String id) {
		User entity = getEntityById(id);
		return new UserDTO(entity);
	}
	
	private User getEntityById(String id) {
		Optional<User> result = repository.findById(id);
		return result.orElseThrow(() -> new ResourceNotFoundException("Resource not found."));
	}
	
	public UserDTO insert(UserDTO dto) {
		User entity = new User();
		copyDtotoEntity(dto, entity);
		entity = repository.insert(entity);
		return new UserDTO(entity);
	}

	public UserDTO update(String id, UserDTO dto) {
		User entity = getEntityById(id);
		copyDtotoEntity(dto, entity);
		entity = repository.save(entity);
		return new UserDTO(entity);
	}
	
	public void delete(@PathVariable String id) {
		getEntityById(id);
		repository.deleteById(id);
	}
	
	public List<PostDTO> getUserPosts(String id) {
		User user = getEntityById(id);
		return user.getPosts().stream().map(x -> new PostDTO(x)).toList();
	}
	
	private void copyDtotoEntity(UserDTO dto, User entity) {
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
	}
}
