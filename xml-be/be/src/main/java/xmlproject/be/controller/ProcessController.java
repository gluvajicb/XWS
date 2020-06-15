package xmlproject.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import xmlproject.be.service.ProcessService;

@RestController
@RequestMapping(value = "/process")
@CrossOrigin()
public class ProcessController {
	@Autowired
	ProcessService processService;
	
	@RequestMapping(
            path= "/create",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_XML_VALUE
    )
	public ResponseEntity<Boolean> create(@RequestBody(required = true) String processDTO) {
		try {
			processService.save(processDTO);
			return new ResponseEntity<Boolean>(HttpStatus.CREATED);
		}
		catch (Exception e) {
			return new ResponseEntity<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	 @RequestMapping(
	         path= "/{id}",
	         method = RequestMethod.GET,
	         produces = MediaType.APPLICATION_XML_VALUE
	 )
	public ResponseEntity<String> getById(@PathVariable String id) {
		String retVal;
		try {
			retVal = processService.findById(id);
		} catch (Exception e) {
			return new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>(retVal, HttpStatus.OK);
	}
	 
	 @PutMapping(value="/update/{id}", consumes = MediaType.APPLICATION_XML_VALUE,produces = MediaType.APPLICATION_XML_VALUE)
		public ResponseEntity<String> update(@RequestBody String dto, @PathVariable("id") String id) throws Exception {

			String coverLetter_ = processService.update(id, dto);
			return new ResponseEntity<>(coverLetter_, HttpStatus.OK);
		}
	 
	 @DeleteMapping(value="/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<String> delete(@PathVariable("id") String id) throws Exception{
		 processService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
}
