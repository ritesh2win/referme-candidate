package com.referme.candidate.repository.impl;


import com.referme.candidate.dto.CandidateDetail;
import org.springframework.data.repository.CrudRepository;

public interface CandidateDetailsRepository extends
        CrudRepository<CandidateDetail, String> {

}
