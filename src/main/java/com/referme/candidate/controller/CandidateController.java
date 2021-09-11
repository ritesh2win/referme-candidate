package com.referme.candidate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.referme.candidate.model.Candidate;
import com.referme.candidate.services.candidate.CandidateService;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

	@Autowired
	CandidateService candidateService;

	@PostMapping(value = "/candidateFormSubmit", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ModelAndView uploadFile(@RequestParam MultipartFile resume, @ModelAttribute Candidate candidate) {
		ModelAndView modelAndView = new ModelAndView();
		if (candidateService.saveAndPublishCandidateData(resume, candidate))
			modelAndView.setViewName("success");
		else
			modelAndView.setViewName("failure");
		return modelAndView;
	}

}
