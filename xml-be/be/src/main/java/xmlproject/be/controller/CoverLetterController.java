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

import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping(value = "/coverLetter")
@CrossOrigin()
public class CoverLetterController {
	@Autowired
	CoverLetterService coverLetterService;

	@RequestMapping(path= "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Boolean> createCoverLetter(@RequestBody(required = true) String coverLetterDTO) {
		try {
			coverLetterService.save(coverLetterDTO);
			return new ResponseEntity<Boolean>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
 
	@RequestMapping(path= "/{coverLetterId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<String> getById(@PathVariable String coverLetterId) {
		String retVal;
		try {
			retVal = coverLetterService.findById(coverLetterId);
		} catch (Exception e) {
			return new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>(retVal, HttpStatus.OK);
	}
		 
	@PutMapping(value="/updateCoverLetter/{id}", consumes = MediaType.APPLICATION_XML_VALUE,produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<String> update(@RequestBody String dto, @PathVariable("id") String id) throws Exception {

		String coverLetter_ = coverLetterService.update(id, dto);
		return new ResponseEntity<>(coverLetter_, HttpStatus.OK);
	}
		 
 	@DeleteMapping(value="/deleteCoverLetter/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> delete(@PathVariable("id") String id) throws Exception{
		coverLetterService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(value="/getCoverLetter/PDF/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> getPDF(@PathVariable("id") String id) throws Exception{
		ByteArrayOutputStream coverLetter = coverLetterService.findByIdPDF(id);
		return new ResponseEntity<>(coverLetter.toByteArray(), HttpStatus.OK);
	}
}
