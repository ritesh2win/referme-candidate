package com.referme.candidate.dto;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.ArrayList;


@DynamoDBTable(tableName = "customer")public class Candidate {
    private String candidateId;

    private String firstName;

    private String lastName;

    private String email;

    private String contact;

    private String jobId;

    private String experience;

    private ArrayList<String> skills;

    private boolean readyToRelocate;

    private boolean isActiveForJob;

    private String resumeUrl;

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    public String getId() {
        return candidateId;
    }
    @DynamoDBAttribute
    public String getFirstName() {
        return firstName;
    }

    @DynamoDBAttribute
    public String getLastName() {
        return lastName;
    }

    @DynamoDBAttribute
    public String getEmail() {
        return email;
    }

    @DynamoDBAttribute
    public String getContact() {
        return contact;
    }

    @DynamoDBAttribute
    public String getJobId() {
        return jobId;
    }

    @DynamoDBAttribute
    public String getExperience() {
        return experience;
    }

    @DynamoDBAttribute
    public ArrayList<String> getSkills() {
        return skills;
    }

    @DynamoDBAttribute
    public boolean isReadyToRelocate() {
        return readyToRelocate;
    }

    @DynamoDBAttribute
    public boolean isActiveForJob() {
        return isActiveForJob;
    }

    @DynamoDBAttribute
    public String getResumeUrl() {
        return resumeUrl;
    }
}
