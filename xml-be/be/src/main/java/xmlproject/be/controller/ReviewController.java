package xmlproject.be.controller;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import xmlproject.be.service.ReviewService;


@RestController
@RequestMapping(value = "/review")
@CrossOrigin()
public class ReviewController {
	@Autowired
	ReviewService reviewService;
	
	@RequestMapping(path= "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Boolean> create(@RequestBody(required = true) String reviewDTO) {
		try {
			reviewService.save(reviewDTO);
			return new ResponseEntity<Boolean>(HttpStatus.CREATED);
		}
		catch (Exception e) {
			return new ResponseEntity<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(path= "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<String> getById(@PathVariable String id) {
		String retVal;
		try {
			retVal = reviewService.findById(id);
		} catch (Exception e) {
			return new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>(retVal, HttpStatus.OK);
	}
	 
	@PutMapping(value="/update/{id}", consumes = MediaType.APPLICATION_XML_VALUE,produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<String> update(@RequestBody String dto, @PathVariable("id") String id) throws Exception {
		String coverLetter_ = reviewService.update(id, dto);
		return new ResponseEntity<>(coverLetter_, HttpStatus.OK);
	}
	 
	@DeleteMapping(value="/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> delete(@PathVariable("id") String id) throws Exception{
		reviewService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	 
	@GetMapping(value="/article/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity< List<String>> getReviewForArticle(@PathVariable("id") String id) throws Exception{
		List<String> retVal = reviewService.getReviewsByArticleId(id);
		return new ResponseEntity< List<String>>(retVal, HttpStatus.OK);
	}

	@GetMapping(value="/getReview/PDF/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> getPDF(@PathVariable("id") String id) throws Exception{
		ByteArrayOutputStream coverLetter = reviewService.findByIdPDF(id);
		return new ResponseEntity<>(coverLetter.toByteArray(), HttpStatus.OK);
	}
}
