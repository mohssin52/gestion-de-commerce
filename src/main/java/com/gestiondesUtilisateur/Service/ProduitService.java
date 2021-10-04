package com.gestiondesUtilisateur.Service;

import java.util.List;

import com.gestiondesUtilisateur.shered.dto.ProduitDto;

public interface ProduitService {
ProduitDto createProduti(ProduitDto produitDto,String email);
 List<ProduitDto> getProduit(int page,int limit,String email) throws Exception; 
 ProduitDto updateProduit(ProduitDto produitDto ,String produitId);
 void deleteProduit(String produitId);
}
