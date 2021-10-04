package com.gestiondesUtilisateur.Service.Impl;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gestiondesUtilisateur.Entities.ProduitEntity;
import com.gestiondesUtilisateur.Entities.UserEntity;
import com.gestiondesUtilisateur.Repository.ProduitRepository;
import com.gestiondesUtilisateur.Repository.UserRepository;
import com.gestiondesUtilisateur.Response.ErrorMessages;
import com.gestiondesUtilisateur.Service.ProduitService;
import com.gestiondesUtilisateur.shered.Utils;
import com.gestiondesUtilisateur.shered.dto.ProduitDto;
import com.gestiondesUtilisateur.shered.dto.UserDto;

@Service
public class ProduitServiceImpl implements ProduitService {
	@Autowired
	ProduitRepository produitRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	Utils utils;

	@Override
	public ProduitDto createProduti(ProduitDto produitDto, String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		ModelMapper modelMapper = new ModelMapper();

		UserDto userDto = modelMapper.map(userEntity, UserDto.class);
		produitDto.setProduitId(utils.generateStringId(30));
		produitDto.setUserDto(userDto);
		ProduitEntity produitEntity = modelMapper.map(produitDto, ProduitEntity.class);
		ProduitEntity produit = produitRepository.save(produitEntity);
		ProduitDto newProduitDto = modelMapper.map(produit, ProduitDto.class);
		return newProduitDto;
	}
 
	@Override
	public List<ProduitDto> getProduit(int page, int limit, String email) throws Exception {
	
		UserEntity currenUser = userRepository.findByEmail(email);
		Pageable pageable = PageRequest.of(page, limit);
		Page<ProduitEntity> produitPage;
		if(produitRepository.findAll().isEmpty()) throw  new Exception(ErrorMessages.PRODUCT_VIDE.getErrorMessage());
			
		
		if (currenUser.getAdmin() == true)
			produitPage = produitRepository.findAll(pageable);
		
		else
			produitPage = produitRepository.findByUser(currenUser, pageable);
		List<ProduitEntity> produitEntity = produitPage.getContent();
		Type listType = new TypeToken<List<ProduitDto>>() {
		}.getType();
		List<ProduitDto> produitDto = new ModelMapper().map(produitEntity, listType);
		return produitDto;
	}

	@Override
	public ProduitDto updateProduit(ProduitDto produitDto, String produitId) {
 ProduitEntity produitEntity = produitRepository.findByProduitId(produitId);
 if(produitEntity ==null) throw new RuntimeException("produit n'exist pas");

 produitEntity.setCode(produitDto.getCode());
 produitEntity.setDescription(produitDto.getDescription());
 produitEntity.setNom(produitDto.getNom());
 produitEntity.setPrix(produitDto.getPrix());
   ProduitEntity newProduitEntity= produitRepository.save(produitEntity);
   ModelMapper modelMapper = new ModelMapper();
   
  ProduitDto produit=  modelMapper.map(produitEntity, ProduitDto.class);
		return produit;
	}

	@Override
	public void deleteProduit(String produitId) {
	ProduitEntity produitEntity = produitRepository.findByProduitId(produitId);
	if(produitEntity==null) throw new RuntimeException("produit n'exist pas");
	produitRepository.delete(produitEntity);
		
	}

}
