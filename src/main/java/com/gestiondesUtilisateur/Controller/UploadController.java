package com.gestiondesUtilisateur.Controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



@RestController
public class UploadController {

	@PostMapping("/upload")
	public String UploadFile(MultipartFile file) {
		String destionationFilename =".\\upload\\"+file.getOriginalFilename();
		try {
			Files.copy(file.getInputStream(),Paths.get(destionationFilename),StandardCopyOption.REPLACE_EXISTING);
			
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
	return destionationFilename +"il est uoload";
	}
	@GetMapping("/downoald/{filename}")
	public Resource downoald(@PathVariable String filename) {
		Path pathToFile = Paths.get(".\\upload\\"+filename);
		UrlResource resource=null ; 
		try {
			resource = new  UrlResource(pathToFile.toUri());
			
		}catch (MalformedURLException e) {
			 throw new RuntimeException(e);
		}
		
		return resource;
		 
	}
}
