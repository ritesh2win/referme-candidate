package com.referme.candidate.model;

/**
 * @author shubham.thakur
 *
 */
public class DownloadedFile {

	private String fileName;
	
	private byte[] content;
	
	public DownloadedFile() {
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}
}
