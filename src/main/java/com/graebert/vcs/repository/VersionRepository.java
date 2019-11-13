package com.graebert.vcs.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.graebert.vcs.model.TextData;

@Repository
public interface VersionRepository extends MongoRepository<TextData, String> {
	
	public List<TextData> findByFileName(String fileName);
	public TextData findByFileNameAndLatest(String fileName,boolean latest);
	public TextData findByLatest(boolean latest);
	public TextData findByUuid(String uuid);
	
}
