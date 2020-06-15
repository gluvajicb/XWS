package xmlproject.be.controller;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.icu.util.GregorianCalendar;

import rs.ac.uns.xmltim.coverletter.CoverLetter;
import xmlproject.be.dto.CoverLetterDTO;
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
 
		 @RequestMapping(
		         path= "/{coverLetterId}",
		         method = RequestMethod.GET,
		         produces = MediaType.APPLICATION_JSON_VALUE
		 )
		public ResponseEntity<CoverLetterDTO> getById(@PathVariable String coverLetterId) {
			CoverLetter coverLetter = new CoverLetter();
			CoverLetterDTO retVal = null;

			try {
				coverLetter = coverLetterService.findById(coverLetterId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (coverLetter == null) {
				return new ResponseEntity<CoverLetterDTO>(retVal, HttpStatus.NOT_FOUND);
			}

			retVal = new CoverLetterDTO(coverLetter.getID(), coverLetter.getTitle(), coverLetter.getArticleId(), coverLetter.getText());
			return new ResponseEntity<CoverLetterDTO>(retVal, HttpStatus.OK);
		}
		 
		 @PutMapping(value="/updateCoverLetter/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_XML_VALUE)
			public ResponseEntity<String> update(@RequestBody CoverLetterDTO dto, @PathVariable("id") String id) throws Exception {
				CoverLetter cl = new CoverLetter();
				cl.setID(id);
				cl.setText(dto.getText());
				cl.setTitle(dto.getTitle());
				cl.setArticleId(dto.getScientificWorkId());
				String coverLetter_ = coverLetterService.update(id, cl);
				return new ResponseEntity<>(coverLetter_, HttpStatus.OK);
			}
		 
		 @DeleteMapping(value="/deleteCoverLetter/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<String> delete(@PathVariable("id") String id) throws Exception{
				coverLetterService.delete(id);
				return new ResponseEntity<>(HttpStatus.OK);
			}
}
