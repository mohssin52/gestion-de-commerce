package com.gestiondesUtilisateur.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestiondesUtilisateur.Entities.ProduitEntity;
import com.gestiondesUtilisateur.Entities.UserEntity;
@Repository
public interface ProduitRepository extends JpaRepository<ProduitEntity, Long> {
	Page<ProduitEntity> findByUser(UserEntity user,Pageable pageable);
	ProduitEntity findByProduitId(String produitId);
}
