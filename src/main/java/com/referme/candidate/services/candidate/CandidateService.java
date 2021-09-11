package com.referme.candidate.services.candidate;

import org.springframework.web.multipart.MultipartFile;

import com.referme.candidate.model.Candidate;

public interface CandidateService {

	boolean saveAndPublishCandidateData(MultipartFile resume, Candidate candidate);

	String fetchDownloadUrl(String candidateId);

	
}
