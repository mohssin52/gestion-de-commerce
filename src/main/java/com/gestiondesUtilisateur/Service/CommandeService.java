package com.gestiondesUtilisateur.Service;

import java.util.List;

import com.gestiondesUtilisateur.shered.dto.CommandeDto;

public interface CommandeService {
	CommandeDto createCommande(CommandeDto commandeDto,String email);
	 List<CommandeDto>getAllCommandes(String email,int page,int limit);
	 CommandeDto  getOneCommande(String commandeId,String email );
	CommandeDto  UpdateCommande(CommandeDto comandeDto,String commandeId);
	void DeleteCommande(String commandeId);
}
 