package com.referme.candidate.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.referme.candidate.model.DownloadedFile;
import com.referme.candidate.services.candidate.CandidateService;
import com.referme.candidate.services.s3.AmazonClient;

@RestController
@RequestMapping("/employer")
public class EmployerController {

	@Autowired
	AmazonClient client;

	@Autowired
	CandidateService candidateService;

	@GetMapping(value = "/download")
	public ResponseEntity<byte[]> downloadResume(@RequestParam String candidateId, HttpServletResponse response) {
		String s3Uri = candidateService.fetchDownloadUrl(candidateId);
		try {
			DownloadedFile downloadFileObj = client.downloadFileFromS3Url(s3Uri);

			return ResponseEntity.ok().contentType(contentType(downloadFileObj.getFileName()))
					.header(HttpHeaders.CONTENT_DISPOSITION,
							"attachment; filename=\"" + downloadFileObj.getFileName() + "\"")
					.body(downloadFileObj.getContent());
		} catch (Exception e) {
			e.printStackTrace();
			try (PrintWriter writer = response.getWriter()) {
				writer.write("Error while downloading file.");
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			return null;
		}
	}

	private MediaType contentType(String filename) {
		String[] fileArrSplit = filename.split("\\.");
		String fileExtension = fileArrSplit[fileArrSplit.length - 1];
		switch (fileExtension) {
		case "pdf":
			return MediaType.APPLICATION_PDF;
		case "json":
			return MediaType.APPLICATION_JSON;
		case "png":
			return MediaType.IMAGE_PNG;
		case "jpg":
			return MediaType.IMAGE_JPEG;
		default:
			return MediaType.APPLICATION_OCTET_STREAM;
		}
	}
}
