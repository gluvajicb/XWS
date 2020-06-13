package xmlproject.be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import xmlproject.be.model.CoverLetter;
import xmlproject.be.repository.CoverLetterRepository;
import xmlproject.be.service.interfaces.CoverLetterServiceImplementation;
import xmlproject.be.util.Exist.RetriveData;

@Service
public class CoverLetterService implements CoverLetterServiceImplementation {

	@Autowired
	private CoverLetterRepository coverLetterRepository;

	public String save(CoverLetter coverLetter) throws Exception {
		return coverLetterRepository.save(coverLetter);
	}

}