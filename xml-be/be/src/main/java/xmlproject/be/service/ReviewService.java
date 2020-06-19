package xmlproject.be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import antlr.collections.List;
import xmlproject.be.repository.ArticleRepository;
import xmlproject.be.repository.ReviewRepository;
import xmlproject.be.service.interfaces.ReviewServiceImplementation;

@Service
public class ReviewService implements ReviewServiceImplementation{

	@Autowired
	private ReviewRepository reviewRepository;

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

}
