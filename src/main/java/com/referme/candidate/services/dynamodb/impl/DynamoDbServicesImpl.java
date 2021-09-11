package com.referme.candidate.services.dynamodb.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.referme.candidate.dto.CandidateDetail;
import com.referme.candidate.repository.CandidateRepository;
import com.referme.candidate.services.dynamodb.DynamoDbServices;

@Service
public class DynamoDbServicesImpl implements DynamoDbServices {

	@Autowired
	CandidateRepository candidateRepository;

	@Override
	public CandidateDetail saveCandidateDetails(CandidateDetail candidateDetail) {
		return candidateRepository.saveCandidate(candidateDetail);
	}

	@Override
	public String fetchDownloadUrl(String candidateId) {
		CandidateDetail candidate = candidateRepository.getCandidateById(candidateId);
		if(candidate!=null)
			return candidate.getResumeUrl();
		
		return null;
	}

}
