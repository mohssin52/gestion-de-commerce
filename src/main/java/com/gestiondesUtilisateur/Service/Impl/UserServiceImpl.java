package com.gestiondesUtilisateur.Service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.gestiondesUtilisateur.Entities.UserEntity;
import com.gestiondesUtilisateur.Repository.CommandeRepository;
import com.gestiondesUtilisateur.Repository.UserRepository;
import com.gestiondesUtilisateur.Service.UserService;
import com.gestiondesUtilisateur.shered.Utils;
import com.gestiondesUtilisateur.shered.dto.CommandeDto;
import com.gestiondesUtilisateur.shered.dto.ProduitDto;
import com.gestiondesUtilisateur.shered.dto.UserDto;


@Service
public class UserServiceImpl implements UserService {
	@Autowired 
UserRepository userRepository;
	@Autowired 
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	Utils utils;      
	@Autowired
	CommandeRepository commandeRepository;
	@Override 
	public UserDto createUser(UserDto user) {
	 
		 UserEntity check = userRepository.findByEmail(user.getEmail());
		if(check !=null) throw new RuntimeException("User Alrady Exists !");
		
		
		for(int i=0; i<user.getCommande().size() ; i++) {
			 
	 CommandeDto commandeDto	= user.getCommande().get(i);
	 commandeDto.setUser(user);   
	 commandeDto.setCommandeId(utils.generateStringId(30));
	 user.getCommande().set(i, commandeDto);
	  
		}
		for(int i=0; i<user.getProduit().size() ; i++) {
			 
			 ProduitDto produitDto	= user.getProduit().get(i);
			 produitDto.setUserDto(user);   
			 produitDto.setProduitId(utils.generateStringId(30));
			 user.getProduit().set(i, produitDto);
			  
				}
			 
			ModelMapper modelMapper = new ModelMapper();
			UserEntity userEntity = modelMapper.map(user, UserEntity.class);
		
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword())); 
		userEntity.setUserId(utils.generateStringId(30));
		 UserEntity userE= userRepository.save(userEntity);


		 UserDto userDto = modelMapper.map(userE, UserDto.class);


		return userDto;
	}
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		 UserEntity userEntity = userRepository.findByEmail(email);
			if(userEntity ==null) throw new UsernameNotFoundException(email) ;
			
		return new User(userEntity.getEmail(),userEntity.getEncryptedPassword(),new ArrayList<>());
	}
	@Override
	public List<UserDto> getUsers(int page, int limit,String search,int status) {
		if(page> 0) page=page-1;
		List<UserDto>usersDto =new ArrayList<>();
		Pageable pageableRequest = PageRequest.of(page, limit);
		 Page<UserEntity> userPage;
		if(search.isEmpty()) {
			userPage =  userRepository.findAllUser(pageableRequest);
			 
		}
		else {
			userPage = userRepository.findUsers(pageableRequest, search,status);
			
		}
		 List<UserEntity> usersEntity = userPage.getContent();
		 for( UserEntity userEntity : usersEntity)
		 {
			 
			  ModelMapper modelMapper = new ModelMapper();
			 UserDto userDto =modelMapper.map(userEntity, UserDto.class);
			 
			 usersDto.add(userDto);
		 }
		return usersDto;
	}
	@Override
	public UserDto getUserByUserId(String userId) {
		  UserEntity userEntity= userRepository.findByUserId(userId);
		  if(userEntity == null) throw new UsernameNotFoundException(userId);
			 UserDto userDto  =new UserDto();
			 BeanUtils.copyProperties(userEntity, userDto);
		return userDto;
	} 
	@Override
	public void deleteUser(String userId) {
		  UserEntity userEntity= userRepository.findByUserId(userId);
		  if(userEntity == null) throw new UsernameNotFoundException(userId);
		  userRepository.delete(userEntity);
		
	} 
	@Override
	public UserDto UpdateUser(String userId, UserDto userDto) {
		 UserEntity userEntity= userRepository.findByUserId(userId);
		  if(userEntity == null) throw new UsernameNotFoundException(userId);
		  userEntity.setFirstname(userDto.getFirstname());
		  userEntity.setLastname(userDto.getLastname());
		  UserEntity newUser = userRepository.save(userEntity);
		  UserDto user  =new UserDto();
		  BeanUtils.copyProperties(newUser, user);
		return user;
	}
	@Override
	public UserDto getUser(String email) {
		          UserEntity userEntity = userRepository.findByEmail(email);
		          if(userEntity ==null) throw new UsernameNotFoundException(email);
		          UserDto  userDto = new UserDto();
		          BeanUtils.copyProperties(userEntity, userDto);
		          
		return userDto;
	}


}
