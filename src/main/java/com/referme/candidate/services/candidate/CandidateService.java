package com.referme.candidate.services.candidate;

import com.referme.candidate.converter.CandidateDtoConverter;
import com.referme.candidate.model.Candidate;
import com.referme.candidate.services.dynamodb.DynamoDbServices;
import com.referme.candidate.services.s3.AmazonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CandidateService {
    @Autowired
    AmazonClient amazonClient;
    @Autowired
    DynamoDbServices dynamoDbServices;
    @Autowired
    CandidateDtoConverter candidateDtoConverter;
    public boolean saveAndPublishCandidateData(MultipartFile resume, Candidate candidate)
    {
        boolean operationSuccess;
        try {
            String s3UrlForResume = amazonClient.uploadFile(resume);
            candidate.setResumeUrl(s3UrlForResume);
            dynamoDbServices.saveCandidateDetails(candidateDtoConverter.convertToCandidateDetails(candidate));
            operationSuccess=true;
        }
        catch (Exception exception)
        {
            throw exception;
        }
        return operationSuccess;
    }
}
