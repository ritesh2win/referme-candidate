package com.referme.candidate.converter;

import com.referme.candidate.dto.CandidateDetail;
import com.referme.candidate.model.Candidate;
import org.springframework.stereotype.Component;

@Component
public class CandidateDtoConverter {
    public CandidateDetail convertToCandidateDetails (Candidate candidate)
    {
        CandidateDetail candidateDetail= new CandidateDetail();
        candidateDetail.setFirstName(candidate.getFirstName());
        candidateDetail.setLastName(candidate.getLastName());
        candidateDetail.setEmail(candidate.getEmail());
        candidateDetail.setContact(candidate.getContact());
        candidateDetail.setJobId(candidate.getJobId());
        candidateDetail.setExperience(candidate.getExperience());
        candidateDetail.setSkills(candidate.getSkills());
        candidateDetail.setReadyToRelocate(candidate.isReadyToRelocate());
        candidateDetail.setActiveForJob(candidate.isActiveForJob());
        candidateDetail.setResumeUrl(candidate.getResumeUrl());
        return candidateDetail;
    }
}
