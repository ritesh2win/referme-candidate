package com.referme.candidate.repository;

import com.referme.candidate.dto.CandidateDetail;
import com.referme.candidate.model.Candidate;

public interface CandidateRepository {

	CandidateDetail saveCandidate(CandidateDetail candidateDetail);

	CandidateDetail getCandidateById(String candidateId);

	String deleteCandidateById(String candidateId);

	String updateCandidate(String candidateId, Candidate candidate);

}
