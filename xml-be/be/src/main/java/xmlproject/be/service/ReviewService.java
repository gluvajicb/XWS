package xmlproject.be.service;

import org.apache.jena.shared.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmlproject.be.repository.ReviewRepository;
import xmlproject.be.service.interfaces.ReviewServiceImplementation;
import xmlproject.be.util.XSLFOTransformer.XSLFOTransformer;

import java.io.ByteArrayOutputStream;

@Service
public class ReviewService implements ReviewServiceImplementation{

	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private XSLFOTransformer xslFoTransformer;

	@Override
	public String save(String review) throws Exception {
		return reviewRepository.save(review);

	}
	
	public String update(String id, String review) throws Exception {
		return reviewRepository.update(id, review);
	}
	
	public boolean delete(String id) throws Exception {
		return reviewRepository.delete(id);
	}
	
	
	public String findById(String id) throws Exception {
		return reviewRepository.findById(id);
	}
	
	public java.util.List<String> getReviewsByArticleId(String articleId) throws Exception {
		return reviewRepository.getReviewsByArticleId(articleId);
	}

	public ByteArrayOutputStream findByIdPDF(String id) throws Exception {
		String review = reviewRepository.findById(id);
		if(review == null){
			throw new NotFoundException(id);
		}

		review = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + "<?xml-stylesheet type=\"text/xsl\" href=\"review-fo.xsl\"?>" + review;
		System.out.println(review);
		ByteArrayOutputStream clPDF = xslFoTransformer.generatePDF(review, "src/main/resources/data/xslfo/review-fo.xsl");
		return clPDF;
	}
}
