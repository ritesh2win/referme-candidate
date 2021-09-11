package com.referme.candidate.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.referme.candidate.dto.CandidateDetail;
import com.referme.candidate.model.Candidate;
import com.referme.candidate.repository.CandidateRepository;

@Repository
public class CandidateRepositoryImpl implements CandidateRepository{

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Override
    public CandidateDetail saveCandidate(CandidateDetail candidateDetail) {
        dynamoDBMapper.save(candidateDetail);
        return candidateDetail;
    }

    @Override
    public CandidateDetail getCandidateById(String candidateId) {
        return dynamoDBMapper.load(CandidateDetail.class, candidateId);
    }

	@Override
    public String deleteCandidateById(String candidateId) {
        dynamoDBMapper.delete(dynamoDBMapper.load(Candidate.class, candidateId));
        return "Candidate Id : "+ candidateId +" Deleted!";
    }

	@Override
    public String updateCandidate(String candidateId, Candidate candidate) {
        dynamoDBMapper.save(candidate,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("candidateId",
                                new ExpectedAttributeValue(
                                        new AttributeValue().withS(candidateId)
                                )));
        return candidateId;
    }
}
