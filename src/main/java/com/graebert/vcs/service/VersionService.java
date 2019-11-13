package com.graebert.vcs.service;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.graebert.vcs.model.TextData;
import com.graebert.vcs.repository.VersionRepository;

@Service
public class VersionService {

	private Logger logger = LogManager.getLogger();

	@Autowired
	private VersionRepository versionRepository;

	@Autowired
	private MongoOperations mongo;

	public TextData saveTextFile(MultipartFile textFile, String fileName) {
		if (StringUtils.isEmpty(fileName)) {
			fileName = getFileName(textFile);
		}
		TextData data = null;
		try {
			data = new TextData(true, fileName, getContentInStringFormat(textFile));
			TextData response = versionRepository.save(data);
			response.setContent("");
			return response;
		} catch (IOException e) {
			logger.error(e.getMessage());
			return data;
		}
	}

	public TextData modifyTextContent(MultipartFile textFile) {
		TextData data = null;
		try {
			// Update the latest tag first
			TextData response =versionRepository.findByFileNameAndLatest(getFileName(textFile), true);
			logger.debug(response);
			response.setLatest(false);
			response= versionRepository.save(response);
			logger.debug(response);
			// Create new record
			return saveTextFile(textFile, null);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return data;
		}
	}

	public TextData renameFile(String fileName, String id) {
		try {
			// Update the latest tag first
			TextData response = versionRepository.findByUuid(id);
			response.setLatest(false);
			versionRepository.save(response);

			TextData renameData = new TextData(true, fileName, response.getContent());
			renameData = versionRepository.save(renameData);
			renameData.setContent("");
			return renameData;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new TextData();
		}
	}

	public TextData deleteFile(String id) {
		TextData response = versionRepository.findByUuid(id);
		
		//Now Delete Specified ID
		versionRepository.deleteById(id);
		List<TextData> res = versionRepository.findByFileName(response .getFileName());
		Collections.sort(res, new SortbyUpdateTime());
		TextData latest=res.get(0);
		latest.setLatest(true);
		latest=versionRepository.save(latest);
		return latest;
	}

	private String getFileName(MultipartFile textFile) {
		return StringUtils.cleanPath(textFile.getOriginalFilename());
	}

	private String getContentInStringFormat(MultipartFile textFile) throws IOException {
		return new String(textFile.getBytes());
	}

	public List<TextData> fetchAll() {
		Sort sort = Sort.by(Sort.Direction.DESC, "updatedAt");
		return versionRepository.findAll(sort);
	}

	public List<TextData> fetchSpecificFileHistory(String fileName) {
		List<TextData> res = versionRepository.findByFileName(fileName);
		Collections.sort(res, new SortbyUpdateTime());
		return res;
	}

}

class SortbyUpdateTime implements Comparator<TextData> {
	
	// Used for sorting by updatedAt
	public int compare(TextData a, TextData b) {
		return b.getUpdatedAt().compareTo(a.getUpdatedAt());
	}
}
