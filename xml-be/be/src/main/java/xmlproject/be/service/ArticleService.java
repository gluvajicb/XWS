package xmlproject.be.service;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.jena.shared.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;

import com.github.andrewoma.dexx.collection.ArrayList;

import xmlproject.be.repository.ArticleRepository;
import xmlproject.be.service.interfaces.ArticleServiceImplementation;
import xmlproject.be.util.XSLFOTransformer.XSLFOTransformer;

@Service
public class ArticleService implements ArticleServiceImplementation {

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private XSLFOTransformer xslFoTransformer;
	
	@Override
	public String save(String article) throws Exception {
		return articleRepository.save(article);

	}
	
	public String update(String id, String article) throws Exception {
		return articleRepository.update(id, article);
	}
	
	public boolean delete(String id) throws Exception {
		return articleRepository.delete(id);
	}
	
	
	public String findById(String id) throws Exception {
		return articleRepository.findById(id);
	}

	public  List<String> findByAuthorUsername(String username) throws XMLDBException {
		return articleRepository.findByAuthorUsername(username);
	}
	
	public  String submitArticle(String id) throws Exception {
		return articleRepository.submitArticle(id);
	}
	public  String acceptArticle(String id) throws Exception {
		return articleRepository.acceptArticle(id);
	}
	public  String rejectArticle(String id) throws Exception {
		return articleRepository.rejectArticle(id);
	}	
	public  String logicalDelete(String id) throws Exception {
		return articleRepository.logicalDelete(id);
	}
	public  String reviewingArtilce(String id) throws Exception {
		return articleRepository.reviewArticle(id);
	}
	
	public  List<String> getAllSubmitted() throws Exception {
		return articleRepository.getAllSubmitted();
	}
	
	public  List<String> searchArticles(String abst, String title,String keyword,String author,String section, String status) throws Exception {
		return articleRepository.searchArticles(abst, title, keyword, author, section, status);
	}
	
	public String findByIdHTML(String id) throws Exception {
		String coverLetter = articleRepository.findById(id);
		if(coverLetter == null){
			throw new NotFoundException(id);
		}
		String clHTML = xslFoTransformer.generateHTML(coverLetter, "src/main/resources/data/xslt/article.xsl");
		return clHTML;
	}

	public ByteArrayOutputStream findByIdPDF(String id) throws Exception {
		String coverLetter = articleRepository.findById(id);
		if(coverLetter == null){
			throw new NotFoundException(id);
		}
		
		coverLetter = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + "<?xml-stylesheet type=\"text/xsl\" href=\"article-fo.xsl\"?>" + coverLetter;
		System.out.println(coverLetter);
		ByteArrayOutputStream clPDF = xslFoTransformer.generatePDF(coverLetter, "src/main/resources/data/xslfo/article-fo.xsl");
		return clPDF;
	}
}


