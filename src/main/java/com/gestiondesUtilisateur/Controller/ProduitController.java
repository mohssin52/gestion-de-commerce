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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gestiondesUtilisateur.Request.ProduitRequest;
import com.gestiondesUtilisateur.Response.ProduitResponse;
import com.gestiondesUtilisateur.Service.ProduitService;
import com.gestiondesUtilisateur.shered.dto.ProduitDto;

@RestController
@RequestMapping("/produit")
public class ProduitController {
	@Autowired 
	ProduitService produitService;
	@PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ProduitResponse> createProduit(@RequestBody @Valid ProduitRequest produitRequest){
		
		ModelMapper modelMapper = new ModelMapper();

		ProduitDto produitDto= modelMapper.map(produitRequest, ProduitDto.class);
		 ProduitDto newProduitDto = produitService.createProduti(produitDto, null);
		 ProduitResponse produitResponse= modelMapper.map(newProduitDto, ProduitResponse.class);
		return new ResponseEntity<ProduitResponse>(produitResponse,HttpStatus.CREATED);
	}
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	
	public ResponseEntity<List<ProduitResponse>> getProduit(@RequestParam(value="page" ,defaultValue ="1") int page,@RequestParam(value= "limit",defaultValue="5" ) int limit,Principal principal) throws Exception {
		
		ModelMapper modelMapper =new ModelMapper();
		 
		    List<ProduitDto> produitDto = produitService.getProduit(page,limit,principal.getName());
		    Type listType = new TypeToken<List<ProduitResponse>>() {}.getType();
			List<ProduitResponse> produitResponse = new ModelMapper().map(produitDto, listType);
		                
		return new ResponseEntity<List<ProduitResponse>>(produitResponse,HttpStatus.OK);
		
	}
	@PutMapping (path = "/{produitId}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ProduitResponse> updateProduit(@RequestBody ProduitRequest produitRequest,@PathVariable String produitId   ) {
		ModelMapper modelMapper =new ModelMapper();
		ProduitDto produitDto= modelMapper.map(produitRequest, ProduitDto.class);
		 ProduitDto newproduitDto = produitService.updateProduit(produitDto,produitId);
	      ProduitResponse produitResponse	=  modelMapper.map(newproduitDto, ProduitResponse.class);
	    		return new ResponseEntity<ProduitResponse>(produitResponse,HttpStatus.ACCEPTED);
	}
	@DeleteMapping(path = "/{produitId}")
	public ResponseEntity<Object> deleteProduit(@PathVariable String produitId){
		  produitService.deleteProduit(produitId);
		  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
}
