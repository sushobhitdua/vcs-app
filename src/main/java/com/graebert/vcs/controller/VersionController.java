package com.graebert.vcs.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.graebert.vcs.model.TextData;
import com.graebert.vcs.service.VersionService;

@RestController
@RequestMapping("/vcs/api/v1")
public class VersionController {

	private Logger logger = LogManager.getLogger();

	@Autowired
	private VersionService versionService;

	@PostMapping(value = "/upload-file")
	public ResponseEntity<?> uploadTextFile(@RequestParam("file") MultipartFile textFile,
			@RequestParam(required = false) String fileName) {
		logger.debug("Request received to upload text file");
		TextData response = versionService.saveTextFile(textFile, fileName);
		logger.debug("Response : {}", response);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping(value = "/modify-file")
	public ResponseEntity<?> modifyTextContent(@RequestParam("file") MultipartFile textFile) {
		logger.debug("Request received to modify text file");
		TextData response = versionService.modifyTextContent(textFile);
		logger.debug("Response : {}", response);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping(value = "/rename-file")
	public ResponseEntity<?> renameFile(@RequestParam String fileName, @RequestParam String id) {
		logger.debug("Request received to modify text file have id : {}",id);
		TextData response = versionService.renameFile(fileName,id);
		logger.debug("Response : {}", response);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/delete-file/{id}")
	public ResponseEntity<?> deleteFile(@PathVariable  String id) {
		logger.debug("Request received to modify text file have id : {}",id);
		TextData response = versionService.deleteFile(id);
		logger.debug("Response : {}", response);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(value = "/history")
	public ResponseEntity<?> fetchAll() {
		return new ResponseEntity<>(versionService.fetchAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/history/{fileName}")
	public ResponseEntity<?> fetchSpecificFileHistory(@PathVariable  String fileName) {
		return new ResponseEntity<>(versionService.fetchSpecificFileHistory(fileName), HttpStatus.OK);
	}
}
