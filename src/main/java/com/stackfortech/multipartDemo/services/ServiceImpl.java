package com.stackfortech.multipartDemo.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.stackfortech.multipartDemo.model.UploadFileEntity;
import com.stackfortech.multipartDemo.repository.Dao;

@Service
public class ServiceImpl implements Services {
	@Autowired
	private Dao dao;

	private String uploadFolderPath = "C:/Users/MADDY/Desktop/TestingSpringBoot/uploaded_";

	@Override
	public void uploadToLocal(MultipartFile file) {

		try {
			byte[] data = file.getBytes();
			Path path = Paths.get(uploadFolderPath + file.getOriginalFilename());
			Files.write(path, data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void uploadToDB(MultipartFile file) {
		UploadFileEntity uploadFileEntity = new UploadFileEntity();
		try {
			uploadFileEntity.setFileData(file.getBytes());
			uploadFileEntity.setFileType(file.getContentType());
			uploadFileEntity.setFileName(file.getOriginalFilename());
			dao.save(uploadFileEntity);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public UploadFileEntity DownloadFile(String fileId) {

		UploadFileEntity uploadFileEntity = dao.getOne(fileId);
		return uploadFileEntity;
	}

}
