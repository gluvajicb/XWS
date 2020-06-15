package xmlproject.be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xmlproject.be.repository.ArticleRepository;
import xmlproject.be.service.interfaces.ArticleServiceImplementation;

@Service
public class ArticleService implements ArticleServiceImplementation {

	@Autowired
	private ArticleRepository articleRepository;

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

}