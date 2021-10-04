package com.gestiondesUtilisateur.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.gestiondesUtilisateur.Entities.CommandeEntity;
import com.gestiondesUtilisateur.Entities.UserEntity;
 
@Repository
public interface CommandeRepository extends JpaRepository<CommandeEntity, Long> {
	Page< CommandeEntity> findByUser(Pageable pageable,UserEntity currentUser);
	
	//CommandeEntity  findByCommandeId(String commandId);
	@Query(value ="select * from  commandes  c where ( c.commandeId :=commandeId)",nativeQuery=true)
	CommandeEntity findAllByCommandeId(@Param("value = commandeId") String commandeId); 
	//@Query(value ="select * from  commandes  c where ( c.commandeId :=commandeId)",nativeQuery=true)
	// CommandeEntity findCommande(String commandeId);
	CommandeEntity findByCommandeId(String commandeId);
}
