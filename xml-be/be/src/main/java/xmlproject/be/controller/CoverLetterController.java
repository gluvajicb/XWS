package xmlproject.be.controller;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.icu.util.GregorianCalendar;

import xmlproject.be.dto.CoverLetterDTO;
import xmlproject.be.model.CoverLetter;
import xmlproject.be.service.CoverLetterService;
@RestController
@RequestMapping(value = "/coverLetter")
@CrossOrigin()
public class CoverLetterController {
	@Autowired
	CoverLetterService coverLetterService;

 @RequestMapping(
            path= "/create",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
	public ResponseEntity<Boolean> createCoverLetter(@RequestBody(required = false) CoverLetterDTO coverLetterDTO) {
		System.out.println(coverLetterDTO.getScientificWorkId());
		CoverLetter coverLetter = new CoverLetter();
		DatatypeFactory datatypeFactory = null;
		try {
			datatypeFactory = DatatypeFactory.newInstance();
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		coverLetter.setSubmissionDate(datatypeFactory.newXMLGregorianCalendar(new java.util.GregorianCalendar()));
		coverLetter.setText(coverLetterDTO.getText());
		coverLetter.setTitle(coverLetterDTO.getTitle());
		coverLetter.setArticleId(coverLetterDTO.getScientificWorkId());
		try {
			coverLetterService.save(coverLetter);
			return new ResponseEntity<Boolean>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
