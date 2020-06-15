package xmlproject.be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import rs.ac.uns.xmltim.coverletter.CoverLetter;
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
	
	public String update(String id, CoverLetter coverLetter) throws Exception {
		return coverLetterRepository.update(id, coverLetter);
	}
	
	public boolean delete(String id) throws Exception {
		return coverLetterRepository.delete(id);
	}
	
	
	public CoverLetter findById(String id) throws Exception {
		return coverLetterRepository.findById(id);
	}
 

}
