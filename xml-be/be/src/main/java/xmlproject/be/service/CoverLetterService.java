package xmlproject.be.service;

import org.apache.jena.shared.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import rs.ac.uns.xmltim.coverletter.CoverLetter;
import xmlproject.be.repository.CoverLetterRepository;
import xmlproject.be.service.interfaces.CoverLetterServiceImplementation;
import xmlproject.be.util.Exist.RetriveData;
import xmlproject.be.util.XSLFOTransformer.XSLFOTransformer;

import java.io.ByteArrayOutputStream;

@Service
public class CoverLetterService implements CoverLetterServiceImplementation {

	@Autowired
	private CoverLetterRepository coverLetterRepository;

	@Autowired
	private XSLFOTransformer xslFoTransformer;

	public String save(String coverLetter) throws Exception {
		return coverLetterRepository.save(coverLetter);
	}
	
	public String update(String id, String coverLetter) throws Exception {
		return coverLetterRepository.update(id, coverLetter);
	}
	
	public boolean delete(String id) throws Exception {
		return coverLetterRepository.delete(id);
	}
	
	
	public String findById(String id) throws Exception {
		return coverLetterRepository.findById(id);
	}

	public ByteArrayOutputStream findByIdPDF(String id) throws Exception {
		String coverLetter = coverLetterRepository.findById(id);
		if(coverLetter == null){
			throw new NotFoundException(id);
		}

		coverLetter = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + "<?xml-stylesheet type=\"text/xsl\" href=\"cover-letter-fo.xsl\"?>" + coverLetter;
		System.out.println(coverLetter);
		ByteArrayOutputStream clPDF = xslFoTransformer.generatePDF(coverLetter, "src/main/resources/data/xslfo/cover-letter-fo.xsl");
		return clPDF;
	}

}
