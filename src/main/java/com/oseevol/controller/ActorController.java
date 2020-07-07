package com.oseevol.controller;

import static com.oseevol.controller.RouterEndpoint.ACTORS_ROOT;
import static com.oseevol.controller.RouterEndpoint.CREATE;
import static com.oseevol.controller.RouterEndpoint.RESOURCE_ID;
import static com.oseevol.controller.RouterEndpoint.SEARCH;
import static com.oseevol.controller.RouterEndpoint.UPDATE;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oseevol.data.ActorDTO;
import com.oseevol.data.ActorUpdateDTO;
import com.oseevol.data.entity.Actor;
import com.oseevol.service.LibraryService;
import com.oseevol.util.ResponseWrapper;

@RestController
@RequestMapping(ACTORS_ROOT)
public class ActorController {

	private static final String SLASH = "/";
	private final LibraryService libraryService;

	public ActorController(LibraryService libraryService) {
		super();
		this.libraryService = libraryService;
	}

	@GetMapping
	public ResponseEntity<Object> getActors() {

		return ResponseWrapper.ok(libraryService.getActors());
	}

	@GetMapping(RESOURCE_ID)
	public ResponseEntity<Object> getActor(@PathVariable(name = "id") Long id) {
		return ResponseWrapper.ok(libraryService.getActor(id));
	}

	@GetMapping(SEARCH)
	public ResponseEntity<Object> getActors(@RequestParam Map<String, String> parameters) {
		return ResponseWrapper.ok(libraryService.getActors(parameters));
	}

	@PostMapping(CREATE)
	public ResponseEntity<Object> addActor(@RequestBody ActorDTO actor, HttpServletRequest request) {

		Actor dbActor = libraryService.addActor(actor);
		String path = request.getRequestURI().replace(CREATE, SLASH + Long.toString(dbActor.getId()));
		return ResponseWrapper.created(path);
	}

	@PutMapping(UPDATE)
	public ResponseEntity<Object> updateActor(@RequestBody ActorUpdateDTO dto) {
		libraryService.updateActor(dto);
		return ResponseWrapper.noContent();
	}
}
