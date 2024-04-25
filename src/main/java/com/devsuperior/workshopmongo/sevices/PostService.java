package com.devsuperior.workshopmongo.sevices;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.workshopmongo.models.dto.PostDTO;
import com.devsuperior.workshopmongo.models.entities.Post;
import com.devsuperior.workshopmongo.repositories.PostRepository;
import com.devsuperior.workshopmongo.sevices.exceptions.ResourceNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;

	public PostDTO findById(String id) {
		Post entity = getEntityById(id);
		return new PostDTO(entity);
	}

	private Post getEntityById(String id) {
		Optional<Post> result = repository.findById(id);
		return result.orElseThrow(() -> new ResourceNotFoundException("Resource not found."));
	}
	
	public List<PostDTO> findByTitle(String text) {
		List<Post> list = repository.findByTitleContainingIgnoreCase(text);
		return list.stream().map(x -> new PostDTO(x)).toList();
	}
}
