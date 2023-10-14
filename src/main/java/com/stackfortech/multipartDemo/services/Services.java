package com.stackfortech.multipartDemo.services;

import org.springframework.web.multipart.MultipartFile;

import com.stackfortech.multipartDemo.model.UploadFileEntity;


public interface Services {

	public void uploadToLocal(MultipartFile file);
	public void uploadToDB(MultipartFile file);
	public UploadFileEntity DownloadFile(String fileId);
	
}
