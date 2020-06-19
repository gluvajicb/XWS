package xmlproject.be.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.css.sac.ProcessingInstructionSelector;

import xmlproject.be.repository.ProcessRepository;
import xmlproject.be.service.interfaces.ProcesServiceImplementation;

@Service
public class ProcessService implements ProcesServiceImplementation{
	@Autowired
	private ProcessRepository processRepository;

	@Override
	public String save(String process) throws Exception {
		return processRepository.save(process);

	}
	
	public String update(String id, String process) throws Exception {
		return processRepository.update(id, process);
	}
	
	public boolean delete(String id) throws Exception {
		return processRepository.delete(id);
	}
	
	
	public String findById(String id) throws Exception {
		return processRepository.findById(id);
	}
	
	public  List<String> getReviewsForUser(String reviewerId) throws Exception {
		return processRepository.getReviewsForUser(reviewerId);
	}
	
	public String submitReview(String reviewId, String processID) throws Exception {
		return processRepository.submitReview(reviewId, processID);
	}
	
	public List<String> findAllArticlesReviews(String articleId) throws Exception{
		return processRepository.findAllArticlesReviews(articleId);
	}
}
