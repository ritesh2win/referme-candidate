package com.referme.candidate.services.dynamodb;

import com.referme.candidate.model.Candidate;
import com.referme.candidate.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DynamoDbServices {
    @Autowired
    CandidateRepository candidateRepository;

    public Candidate saveCandidateDetails(Candidate candidate)
    {
        return candidateRepository.saveCandidate(candidate);
    }

}
