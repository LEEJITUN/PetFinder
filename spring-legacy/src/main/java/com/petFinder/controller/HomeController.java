package com.petFinder.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
   
   @GetMapping(value = {"/","/home"})
   public String home() {
      // GET ��û 
      // http://localhost:8090/
      System.out.println("home() ȣ���...");
      
      
      return "home";// ������ jsp�� �̸��� ������.
   }
   
   @GetMapping("/display")
   @ResponseBody
	public ResponseEntity<byte[]> getInageFile(String fileName) throws IOException{
		System.out.println("fileName: " + fileName);
		File file = new File("D:/upload",fileName);
		System.out.println("���� ���� ��� : " + file.getPath());
		
		HttpHeaders headers = new HttpHeaders();
		String contentType = Files.probeContentType(file.toPath()); // "images/jpg"
		headers.add("Content-Type",contentType);
		
		byte[] imageData = FileCopyUtils.copyToByteArray(file);
		
		
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(imageData,headers,HttpStatus.OK);
		
		return responseEntity;
	}
	
	//  headers.add("Content-Type",contentType/octet-stream) ���
	//  produces= MediaType.APPLICATION_OCTET_STREAM_VALUE ��밡��
	@GetMapping(value = "/download",produces= MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource>  downloadFile(String fileName) throws UnsupportedEncodingException {
		
		File file = new File("D:/upload",fileName);

		
		Resource resource = new FileSystemResource(file);
		System.out.println("resource : " + resource);
		
		if(!resource.exists()) {
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
		}
		
		
		
		String resourceName = resource.getFilename();
		System.out.println("resourceName : " + resourceName);
		
		int beginIndex = resourceName.indexOf("_") +1;
		String originFilename = resourceName.substring(beginIndex);
		
		System.out.println("originFilename : " + originFilename);
		
		// ��ȯ�� Ÿ��,���� �� ��
		String downloadFilename = new String(originFilename.getBytes("UTF-8"), "ISO-8859-1");
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition","attachment; filename=" + downloadFilename);
		
		return new ResponseEntity<Resource>(resource,headers,HttpStatus.OK);
		
	}

}