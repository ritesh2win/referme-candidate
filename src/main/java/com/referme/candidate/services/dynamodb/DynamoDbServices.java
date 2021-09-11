package com.referme.candidate.services.dynamodb;

import com.referme.candidate.dto.CandidateDetail;

public interface DynamoDbServices {

	CandidateDetail saveCandidateDetails(CandidateDetail convertToCandidateDetails);

	String fetchDownloadUrl(String candidateId);

}
