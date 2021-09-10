package com.referme.candidate.services.dynamodb;

import com.referme.candidate.dto.CandidateDetail;
import com.referme.candidate.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DynamoDbServices {
    @Autowired
    CandidateRepository candidateRepository;

    public CandidateDetail saveCandidateDetails(CandidateDetail candidateDetail)
    {
        return candidateRepository.saveCandidate(candidateDetail);
    }

}
