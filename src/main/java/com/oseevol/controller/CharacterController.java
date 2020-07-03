package com.oseevol.controller;

import static com.oseevol.controller.RouterEndpoint.CHARACTERS_MOVIE;
import static com.oseevol.controller.RouterEndpoint.CHARACTERS_ROOT;
import static com.oseevol.controller.RouterEndpoint.CREATE;
import static com.oseevol.controller.RouterEndpoint.RESOURCE_ID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oseevol.data.MovieCharacterDTO;
import com.oseevol.data.entity.MovieCharacter;
import com.oseevol.service.LibraryService;
import com.oseevol.util.ResponseWrapper;

@RestController
@RequestMapping(CHARACTERS_ROOT)
public class CharacterController {
	private static final String SLASH = "/";
	
	private final LibraryService libraryService;

	public CharacterController(LibraryService libraryService) {
		super();
		this.libraryService = libraryService;
	}
	
	@GetMapping(RESOURCE_ID)
	public ResponseEntity<Object> getCharacter(@PathVariable(name = "id") Long id) {
		return ResponseWrapper.ok(libraryService.getCharacter(id));
	}
	
	@GetMapping(CHARACTERS_MOVIE)
	public ResponseEntity<Object> findMovieCharacters(@PathVariable(name = "id") Long id){
		return ResponseWrapper.ok(libraryService.getMovieCharacters(id));
	}
	
	@PostMapping(CREATE)
	public ResponseEntity<Object> createCharacter(@RequestBody MovieCharacterDTO characterDTO, HttpServletRequest request){
		MovieCharacter character = libraryService.addCharacter(characterDTO);
		var path = request.getRequestURI().replace(CREATE, SLASH + Long.toString(character.getId()));
		return ResponseWrapper.created(path);
	}
}
