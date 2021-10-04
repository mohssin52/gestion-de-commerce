package com.gestiondesUtilisateur.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
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

import com.gestiondesUtilisateur.Exception.UserException;
import com.gestiondesUtilisateur.Request.UserRequest;
import com.gestiondesUtilisateur.Response.ErrorMessages;
import com.gestiondesUtilisateur.Response.UserResponse;
import com.gestiondesUtilisateur.Service.UserService;
import com.gestiondesUtilisateur.shered.dto.UserDto;



@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired 
     UserService userService;
	@PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest) throws Exception{
		if(userRequest.getFirstname().isEmpty()) throw new UserException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
	
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userRequest, UserDto.class);
	
		
		 UserDto user = userService.createUser(userDto); 


		 UserResponse userResponse = modelMapper.map(user, UserResponse.class);
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED); 
	}
	@GetMapping(	produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<UserResponse> >getUsers(@RequestParam(value="page" ,defaultValue ="1") int page,@RequestParam(value= "limit",defaultValue="5" ) int limit,@RequestParam(value= "search",defaultValue="" ) String  search,@RequestParam(value= "status",defaultValue="1" ) int  status  ){
	 
		List<UserResponse>usersResponse = new ArrayList<>();
	 List<UserDto>  users	= userService.getUsers(page,limit,search,status); 
	 for( UserDto userDto : users)
	 { 
	
		  ModelMapper modelMapper = new ModelMapper();
		  UserResponse userResponse = modelMapper.map(userDto, UserResponse.class);
		
		 usersResponse.add(userResponse);
	 }
	  
		return new ResponseEntity<List<UserResponse>>(usersResponse, HttpStatus.OK);
		
	}
	
	@GetMapping(	path = "/{userid}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<UserResponse>getUser(@PathVariable String userid ){
		
		  UserDto userDto= userService.getUserByUserId(userid);
		 UserResponse userResponse  =new UserResponse();
		 BeanUtils.copyProperties(userDto, userResponse);
	
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
}
	@DeleteMapping(path ="/{userId}")
	public ResponseEntity<Object>deleteUser(@PathVariable String userId){
		userService.deleteUser(userId); 
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	} 
	@PutMapping(path="/{userId}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
	consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
public ResponseEntity<UserResponse> UpdateUser(@RequestBody UserRequest userRequest,@PathVariable String userId){
	
		UserDto userDto =new UserDto();
		BeanUtils.copyProperties(userRequest, userDto);
		 UserDto user = userService.UpdateUser(userId,userDto);
		 UserResponse userResponse = new UserResponse();
		 BeanUtils.copyProperties(user, userResponse);
		
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.ACCEPTED);
	}
	}
