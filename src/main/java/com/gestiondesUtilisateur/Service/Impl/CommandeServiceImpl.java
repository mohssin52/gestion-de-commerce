package com.gestiondesUtilisateur.Service.Impl;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gestiondesUtilisateur.Entities.CommandeEntity;
import com.gestiondesUtilisateur.Entities.UserEntity;
import com.gestiondesUtilisateur.Repository.CommandeRepository;
import com.gestiondesUtilisateur.Repository.UserRepository;
import com.gestiondesUtilisateur.Service.CommandeService;
import com.gestiondesUtilisateur.shered.Utils;
import com.gestiondesUtilisateur.shered.dto.CommandeDto;
import com.gestiondesUtilisateur.shered.dto.UserDto;

@Service
public class CommandeServiceImpl implements CommandeService {
 @ Autowired 
	Utils  utils;
 @Autowired
 UserRepository userRepository;
 @Autowired
 CommandeRepository commandeRepository;
	@Override
	public CommandeDto createCommande(CommandeDto commandeDto, String email) {
		UserEntity cuurentUser = userRepository.findByEmail(email);
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(cuurentUser, UserDto.class);
		commandeDto.setCommandeId(utils.generateStringId(30));
		commandeDto.setUser(userDto);
		CommandeEntity commandeEntity = modelMapper.map(commandeDto, CommandeEntity.class);
	    CommandeEntity commandeEnti =  commandeRepository.save(commandeEntity);
	         CommandeDto commande =   modelMapper.map(commandeEnti, CommandeDto.class);
	    
		return commande;
	}
	@Override    
	public List<CommandeDto> getAllCommandes(String email,int page,int limit) {
		UserEntity currentUser = userRepository.findByEmail(email);
		Pageable pageable =PageRequest.of(page,limit);
		Page<CommandeEntity> commandePage;
		if( currentUser.getAdmin()==true )
		{
			commandePage	= commandeRepository.findAll(pageable );
		}
		else {
			commandePage=commandeRepository.findByUser(pageable , currentUser);
		}
	List<CommandeEntity>commandeEntity=commandePage.getContent();
	Type listType = new TypeToken<List<CommandeDto>>() {}.getType();
	List<CommandeDto> commandeDto = new ModelMapper().map(commandeEntity, listType);
	return commandeDto;
	}
	@Override
	public CommandeDto getOneCommande(String commandeId,String email) {
		UserEntity currentUser = userRepository.findByEmail(email);
		
	CommandeEntity commandeEntity	= currentUser.getAdmin()==true ? commandeRepository.findByCommandeId(commandeId): commandeRepository.findAllByCommandeId(commandeId);
	
		ModelMapper modelMapper = new ModelMapper();
		CommandeDto commandeDto = modelMapper.map(commandeEntity, CommandeDto.class);
		
		return commandeDto;
	}
	@Override 
	public CommandeDto UpdateCommande(CommandeDto commandeDto,String commandeId) {
		 CommandeEntity commandeEntity = commandeRepository.findByCommandeId(commandeId);
		if(commandeEntity ==null) throw new UsernameNotFoundException(commandeId);
		commandeEntity.setDateCmd(commandeDto.getDateCmd());
		commandeEntity.setNumero(commandeDto.getNumero());
		commandeEntity.setTotale(commandeDto.getTotale());
		commandeEntity.setValide(commandeDto.getValide());
	    CommandeEntity commande	= commandeRepository.save(commandeEntity);

		ModelMapper modelMapper = new ModelMapper();
		CommandeDto commandeDt = modelMapper.map(commande, CommandeDto.class);
	
		return commandeDt;
	}
	@Override
	public void DeleteCommande(String commandeId) {
		 CommandeEntity commandeEntity = commandeRepository.findByCommandeId(commandeId);
		 if(commandeEntity ==null) throw new UsernameNotFoundException(commandeId);
		 commandeRepository.delete(commandeEntity);
	}



}
