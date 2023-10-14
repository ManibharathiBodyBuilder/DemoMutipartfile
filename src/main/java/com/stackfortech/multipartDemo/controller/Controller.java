package com.stackfortech.multipartDemo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.stackfortech.multipartDemo.model.UploadFileEntity;
import com.stackfortech.multipartDemo.services.Services;

@RestController
@RequestMapping("/api/v1")
public class Controller {

	@Autowired
	private Services services;

	@PostMapping("/upload/local")
	public String uploadFileData(@RequestParam("file") MultipartFile multipartFile) {
		services.uploadToLocal(multipartFile);
		return "Successfully_Uploaded the file";
	}

	@PostMapping("/upload/db")
	public String uploadFileDB(@RequestParam("file") MultipartFile multipartFile) {
		services.uploadToDB(multipartFile);
		return "Successfully_Uploaded the file on DB";
	}

	@GetMapping("/Download/{id}")
	public ResponseEntity<Resource> DownloadData(@PathVariable String id) {
		UploadFileEntity uploadFileEntity = services.DownloadFile(id);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(uploadFileEntity.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filname=" + uploadFileEntity.getFileName())
				.body(new ByteArrayResource(uploadFileEntity.getFileData()));

	}

}
