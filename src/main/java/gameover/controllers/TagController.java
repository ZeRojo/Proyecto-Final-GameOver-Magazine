package gameover.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gameover.models.Tag;
import gameover.resources.iface.IntGlobalVariables;
import gameover.service.iface.IntTagService;

@Controller
@RequestMapping("/gestion/tag")
public class TagController {
	
	@Autowired
	private IntGlobalVariables variables;
	
	@Autowired
	private IntTagService tagService;

	@PostMapping("/guarda")
	public ResponseEntity<Object> guardaTag(@RequestParam("nombretag") String nombre) {
		String nombreSaneado = variables.sanearCadena(nombre);
		Tag tag=tagService.getTagByNombre(nombreSaneado);
		if (tag==null) {
			tag=tagService.saveAndRetrieveTag(new Tag(nombre,nombreSaneado));		
		}
		return new ResponseEntity<>(String.valueOf(tag.getIdtag()),HttpStatus.OK);
	}
}
