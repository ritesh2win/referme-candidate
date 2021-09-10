package com.referme.candidate.model;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

public class Candidate {

    @NotEmpty(message = "{validation.firstName.NotEmpty}")
    private String firstName;

    @NotEmpty(message = "{validation.lastName.NotEmpty}")
    private String lastName;

    @NotEmpty(message = "{validation.email.NotEmpty}")
    private String email;

    @NotEmpty(message = "{validation.contact.NotEmpty}")
    private String contact;

    private String jobId;

    @NotEmpty(message = "{validation.experience.NotEmpty}")
    @Positive(message = "{validation.experience.Positive}")
    private String experience;

    @NotEmpty(message = "{validation.skills.NotEmpty}")
    private ArrayList<String> skills;

    @NotEmpty(message = "{validation.readyToRelocate.NotEmpty}")
    private boolean readyToRelocate;

    @NotEmpty(message = "{validation.isActiveForJob.NotEmpty}")
    private boolean isActiveForJob;

    private String resumeUrl;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public ArrayList<String> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<String> skills) {
        this.skills = skills;
    }

    public boolean isReadyToRelocate() {
        return readyToRelocate;
    }

    public void setReadyToRelocate(boolean readyToRelocate) {
        this.readyToRelocate = readyToRelocate;
    }

    public boolean isActiveForJob() {
        return isActiveForJob;
    }

    public void setActiveForJob(boolean activeForJob) {
        isActiveForJob = activeForJob;
    }

    public String getResumeUrl() {
        return resumeUrl;
    }

    public void setResumeUrl(String resumeUrl) {
        this.resumeUrl = resumeUrl;
    }

    public Candidate() {
    }
}
