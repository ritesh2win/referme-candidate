package com.referme.candidate.services.candidate.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.referme.candidate.converter.CandidateDtoConverter;
import com.referme.candidate.model.Candidate;
import com.referme.candidate.services.candidate.CandidateService;
import com.referme.candidate.services.dynamodb.DynamoDbServices;
import com.referme.candidate.services.s3.AmazonClient;

@Service
public class CandidateServiceImpl implements CandidateService {

	@Autowired
	AmazonClient amazonClient;

	@Autowired
	DynamoDbServices dynamoDbServices;

	@Autowired
	CandidateDtoConverter candidateDtoConverter;

	/**
	 * @param resume
	 * @param candidate
	 * @return
	 */
	@Override
	public boolean saveAndPublishCandidateData(MultipartFile resume, Candidate candidate) {
		boolean operationSuccess;
		try {
			String s3UrlForResume = amazonClient.uploadFile(resume);
			candidate.setResumeUrl(s3UrlForResume);
			dynamoDbServices.saveCandidateDetails(candidateDtoConverter.convertToCandidateDetails(candidate));
			operationSuccess = true;
		} catch (Exception exception) {
			throw exception;
		}
		return operationSuccess;
	}

	/**
	 * @param candidateId 
	 * @return s3URI
	 */
	@Override
	public String fetchDownloadUrl(String candidateId) {
		return dynamoDbServices.fetchDownloadUrl(candidateId);
	}

}
