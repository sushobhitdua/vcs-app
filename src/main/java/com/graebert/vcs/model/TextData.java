package com.graebert.vcs.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "text-data-collection")
public class TextData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5365724824183905500L;
	
	@Id
	private String uuid;
	private boolean latest;
	private String fileName;
	private String content;
	private Date updatedAt;
	
	public TextData(String fileName) {
		super();
		this.fileName = fileName;
	}

	public TextData(boolean latest, String fileName,String content) {
		super();
		this.latest = latest;
		this.fileName = fileName;
		this.content= content;
		this.updatedAt = new Date();
	}

	public TextData() {
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public boolean isLatest() {
		return latest;
	}

	public void setLatest(boolean latest) {
		this.latest = latest;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{\nuuid=");
		builder.append(uuid);
		builder.append(", \nlatest=");
		builder.append(latest);
		builder.append(", \nfileName=");
		builder.append(fileName);
		builder.append(", \ncontent=");
		builder.append(content);
		builder.append(", \nupdatedAt=");
		builder.append(updatedAt);
		builder.append("\n}");
		return builder.toString();
	}

	
	
}
