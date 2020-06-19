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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.andrewoma.dexx.collection.ArrayList;

import xmlproject.be.dto.SearchDTO;
import xmlproject.be.service.ArticleService;
import xmlproject.be.service.CoverLetterService;

@RestController
@RequestMapping(value = "/article")
@CrossOrigin()

public class ArticleController {
	@Autowired
	ArticleService articleService;
	
	@RequestMapping(
            path= "/create",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_XML_VALUE
    )
	public ResponseEntity<Boolean> createArticle(@RequestBody(required = true) String articleDTO) {
		try {
			articleService.save(articleDTO);
			return new ResponseEntity<Boolean>(HttpStatus.CREATED);
		} catch (Exception e) {
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
			retVal = articleService.findById(id);
			System.out.println("RETVAL");
		} catch (Exception e) {
			return new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>(retVal, HttpStatus.OK);
	}
	 
	 @PutMapping(value="/update/{id}", consumes = MediaType.APPLICATION_XML_VALUE,produces = MediaType.APPLICATION_XML_VALUE)
		public ResponseEntity<String> update(@RequestBody String dto, @PathVariable("id") String id) throws Exception {

			String coverLetter_ = articleService.update(id, dto);
			return new ResponseEntity<>(coverLetter_, HttpStatus.OK);
		}
	 
	 @DeleteMapping(value="/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<String> delete(@PathVariable("id") String id) throws Exception{
		 articleService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	 
	 @GetMapping(value="/author/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity< List<String>> findByUsername(@PathVariable("id") String id) throws Exception{
		 List<String> retVal = articleService.findByAuthorUsername(id);
			return new ResponseEntity< List<String>>(retVal, HttpStatus.OK);
		}
	 
	 @PostMapping(value="/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<String> logicalDelete(@PathVariable("id") String id) throws Exception{
		 String retVal = articleService.logicalDelete(id);
			return new ResponseEntity<String>(retVal, HttpStatus.OK);
		}
	 
	 @PostMapping(value="/{id}/submit", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<String> submitArticle(@PathVariable("id") String id) throws Exception{
		 String retVal = articleService.submitArticle(id);
			return new ResponseEntity<String>(retVal, HttpStatus.OK);
		}
	 
	 @PostMapping(value="/{id}/accept", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<String> acceptArticle(@PathVariable("id") String id) throws Exception{
		 String retVal = articleService.acceptArticle(id);
			return new ResponseEntity<String>(retVal, HttpStatus.OK);
		}
	 
	 @PostMapping(value="/{id}/reject", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<String> rejectArticle(@PathVariable("id") String id) throws Exception{
		 String retVal = articleService.rejectArticle(id);
			return new ResponseEntity<String>(retVal, HttpStatus.OK);
		}
	 
	 @GetMapping(value="/submitted", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity< List<String>> getAllSubmitted() throws Exception{
		 List<String> retVal = articleService.getAllSubmitted();
			return new ResponseEntity< List<String>>(retVal, HttpStatus.OK);
		}

	 @PostMapping(value="/search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity< List<String>> search(@RequestBody SearchDTO dto) throws Exception{
		 System.out.println(dto.getAbst() + " " + dto.getAuthor() + " " + dto.getTitle());
		 List<String> retVal = articleService.searchArticles(dto.getAbst(), dto.getTitle(), dto.getKeyword(), dto.getAuthor(), dto.getSection(), dto.getStatus());
			return new ResponseEntity< List<String>>(retVal, HttpStatus.OK);
		}
	 
	 @GetMapping(value="/getArticle/HTML/{id}", produces = MediaType.TEXT_HTML_VALUE)
		public ResponseEntity<String> getHTML(@PathVariable("id") String id) throws Exception{
			String coverLetter = articleService.findByIdHTML(id);
			return new ResponseEntity<>(coverLetter, HttpStatus.OK);
		}

		@GetMapping(value="/getArticle/PDF/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
		public ResponseEntity<byte[]> getPDF(@PathVariable("id") String id) throws Exception{
			ByteArrayOutputStream coverLetter = articleService.findByIdPDF(id);
			return new ResponseEntity<>(coverLetter.toByteArray(), HttpStatus.OK);
		}

}
