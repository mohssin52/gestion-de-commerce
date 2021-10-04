package com.gestiondesUtilisateur.Service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.gestiondesUtilisateur.shered.dto.UserDto;
 
public interface UserService extends UserDetailsService{
 UserDto createUser(UserDto userDto);
  List<UserDto> getUsers(int page,int limit,String search,int status);
 UserDto getUserByUserId(String userid);
 UserDto getUser(String email);
 void deleteUser(String userId); 
 UserDto UpdateUser(String userId, UserDto userDto);
}
