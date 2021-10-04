package com.gestiondesUtilisateur.Controller;

import java.lang.reflect.Type;
import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gestiondesUtilisateur.Request.CommandeRequest;
import com.gestiondesUtilisateur.Response.CommandeResponse;
import com.gestiondesUtilisateur.Service.CommandeService;
import com.gestiondesUtilisateur.shered.dto.CommandeDto;

@RestController
@RequestMapping("/commande")
public class CommandeController {
	@Autowired
	CommandeService commandeService;

	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<CommandeResponse> createCommande(@RequestBody @Valid CommandeRequest commandeRequest,
			Principal principal) {

		ModelMapper modelMapper = new ModelMapper();
		CommandeDto commandeDto = modelMapper.map(commandeRequest, CommandeDto.class);
		CommandeDto commande = commandeService.createCommande(commandeDto, principal.getName());
		CommandeResponse commandeResponse = modelMapper.map(commande, CommandeResponse.class);
		return new ResponseEntity<CommandeResponse>(commandeResponse, HttpStatus.CREATED);
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<CommandeResponse>> getAllCommandes(int page,int limit,Principal principal) {
		List<CommandeDto> commandeDto = commandeService.getAllCommandes(principal.getName(),page,limit);
		Type listType = new TypeToken<List<CommandeResponse>>() {}.getType();
		List<CommandeResponse> commandeResponse = new ModelMapper().map(commandeDto, listType);
		return new ResponseEntity<List<CommandeResponse>>(commandeResponse, HttpStatus.OK);
	}

	@GetMapping(path = "/{commandeId}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<CommandeResponse> getOneCommande(@PathVariable String commandeId, Principal principal) {
		CommandeDto commandeDto = commandeService.getOneCommande(commandeId, principal.getName());
		ModelMapper modelMapper = new ModelMapper();
		CommandeResponse commandeResponse = modelMapper.map(commandeDto, CommandeResponse.class);

		return new ResponseEntity<CommandeResponse>(commandeResponse, HttpStatus.OK);
	}

	@PutMapping(path = "/{commandeId}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<CommandeResponse> UpdateCommande(@RequestBody CommandeRequest commandeRequest,
			@PathVariable String commandeId) {

		ModelMapper modelMapper = new ModelMapper();
		CommandeDto commandeDto = modelMapper.map(commandeRequest, CommandeDto.class);
		CommandeDto comandeDto = commandeService.UpdateCommande(commandeDto, commandeId);
		CommandeResponse commandeResponse = modelMapper.map(comandeDto, CommandeResponse.class);

		return new ResponseEntity<CommandeResponse>(commandeResponse, HttpStatus.ACCEPTED);
	}

	@DeleteMapping(path = "/{commandeId}")
	public ResponseEntity<Object> DeleteCommande(@PathVariable String commandeId) {
		commandeService.DeleteCommande(commandeId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
