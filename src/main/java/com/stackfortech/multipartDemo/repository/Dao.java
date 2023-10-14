package com.stackfortech.multipartDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.stackfortech.multipartDemo.model.UploadFileEntity;

public interface Dao extends JpaRepository<UploadFileEntity,String> {
	
	//public UploadFileEntity DownloadFile(String fileId);
}
