package com.referme.candidate.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.referme.candidate.dto.CandidateDetail;
import com.referme.candidate.model.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CandidateRepository  {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public CandidateDetail saveCandidate(CandidateDetail candidateDetail) {
        dynamoDBMapper.save(candidateDetail);
        return candidateDetail;
    }

    public Candidate getCandidateById(String candidateId) {
        return dynamoDBMapper.load(Candidate.class, candidateId);
    }

    public String deleteCandidateById(String candidateId) {
        dynamoDBMapper.delete(dynamoDBMapper.load(Candidate.class, candidateId));
        return "Candidate Id : "+ candidateId +" Deleted!";
    }

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
