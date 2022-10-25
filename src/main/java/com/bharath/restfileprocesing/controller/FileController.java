package com.bharath.restfileprocesing.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {
	
	@Value("${uploadDir}")
	private String UPLOAD_DIR; 
	
	@PostMapping("/upload")
	public void upload(@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
		file.transferTo(new File(UPLOAD_DIR+ file.getOriginalFilename()));
//		return true;
		System.out.print(true);
	}

	@GetMapping("/download/{fileName}")
	public ResponseEntity<byte[]> download(@PathVariable("fileName")	String fileName) throws IOException {
		byte[] fieData = Files.readAllBytes(new File(UPLOAD_DIR+fileName).toPath());
		
		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.IMAGE_PNG_VALUE);
		headers.setContentType(MediaType.IMAGE_PNG);
		
		return new ResponseEntity<byte[]>(fieData,headers,HttpStatus.OK);
		
//		complete after
	}
}




























