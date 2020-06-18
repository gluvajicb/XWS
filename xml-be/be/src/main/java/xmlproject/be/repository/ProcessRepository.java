package xmlproject.be.repository;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.catalina.util.ResourceSet;
import org.exist.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import rs.ac.uns.xmltim.article.Article;
import rs.ac.uns.xmltim.process.Process;
import rs.ac.uns.xmltim.process.Process.Reviews;
import rs.ac.uns.xmltim.process.Process.Reviews.ReviewElement;
import rs.ac.uns.xmltim.review.Review;
import xmlproject.be.util.Authentication.AuthenticationUtilities;
import xmlproject.be.util.Authentication.AuthenticationUtilities.ConnectionProperties;
import xmlproject.be.util.Exist.RetriveData;
import xmlproject.be.util.Exist.StoreData;
import xmlproject.be.util.Exist.UpdateData;
import xmlproject.be.util.template.XUpdateTemplate;

@Repository
public class ProcessRepository {
	@Autowired
	RetriveData existRetrieve;

	@Autowired
	ReviewRepository reviewRepository;
	
	@Autowired
	ArticleRepository articleRepository;
	
	public static String processCollectionId = "/db/XWS/process";
	public static String processSchemaPath = "src/main/resources/data/Process.xsd";

	public String save(String processXML) throws Exception {
		String Id = generateNewId();
		Process process = null;
		
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(Process.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			StringReader sr = new StringReader(processXML);
			

			process = (Process) unmarshaller.unmarshal(sr);
			process.setID(Id);
			
			List<ReviewElement> reviews = process.getReviews().getReviewElement();
			for(ReviewElement el : reviews) {
				Review newReview = new Review();
				newReview.setArticleId(process.getArticleId());
				newReview.setWorkflowId(Id);
				el.setStatus("waiting");
				String revierId = reviewRepository.save(newReview);
				el.setReviewId(revierId);
			}
			System.out.println(process.getID());
			System.out.println(process.getID());
			JAXBContext context = JAXBContext.newInstance(Process.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			marshaller.marshal(process, stream);
			
			Article article = articleRepository.findCLById(process.getArticleId());
			article.setStatus("reviewing");
			articleRepository.update(process.getArticleId(), article);
			processXML = new String(stream.toByteArray());
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		StoreData.store(processCollectionId, Id, processXML);
		return Id;
	}
	
	public String update(String id, String processXML) throws Exception {
		Process processOld = this.findCLById(id);
		if (processOld == null) {
			throw new Exception("No process with this id");
		}
		this.delete(id);
		Process process = null;
		
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(Process.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			StringReader sr = new StringReader(processXML);
			
			System.out.println(processXML);

			process = (Process) unmarshaller.unmarshal(sr);
			process.setID(id);
			System.out.println(process.getID());
			JAXBContext context = JAXBContext.newInstance(Process.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			marshaller.marshal(process, stream);
			processXML = new String(stream.toByteArray());
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		

		StoreData.store(processCollectionId, id, processXML);
		return id;
	}
	
	public String update(String id, Process processXML) throws Exception {
		Process processOld = this.findCLById(id);
		String processRet = "";

		if (processOld == null) {
			throw new Exception("No process with this id");
		}
		this.delete(id);
		try {

			JAXBContext context = JAXBContext.newInstance(Process.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			
			processXML.setID(id);
			marshaller.marshal(processXML, stream);
			processRet = new String(stream.toByteArray());
			System.out.println(processRet);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		

		StoreData.store(processCollectionId, id, processRet);
		return id;
	}
	
	public boolean delete(String id) throws Exception {
		System.out.println(id);
		long enabled = UpdateData.delete(processCollectionId, id);
		
		System.out.println(enabled);
		if (enabled == 0) {
			return false;
		}
		return true;
	}
	
	public Process findCLById(String id) throws Exception {

		ResourceSet result = null;
		ConnectionProperties conn = AuthenticationUtilities.loadProperties();
		Class<?> cl = Class.forName(conn.driver);
		Database database = (Database) cl.newInstance();
		database.setProperty("create-database", "true");
		DatabaseManager.registerDatabase(database);
		Collection col = null;
		Process retVal = null;
		try {
			// get the collection
			col = DatabaseManager.getCollection(conn.uri + processCollectionId);
			if (col == null) {
				return null;
			}
			
			XMLResource res = (XMLResource) col.getResource(id);
			System.out.println(res.getContent());
			JAXBContext jaxbContext = JAXBContext.newInstance(Process.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			
			retVal = (Process) unmarshaller.unmarshal(res.getContentAsDOM());
		} finally {
			// don't forget to cleanup
			if (col != null) {
				try {
					col.close();
				} catch (XMLDBException xe) {
					xe.printStackTrace();
				}
			}
		}

		return retVal;
	}
	
	public String findById(String id) throws Exception {

		ResourceSet result = null;
		ConnectionProperties conn = AuthenticationUtilities.loadProperties();
		Class<?> cl = Class.forName(conn.driver);
		Database database = (Database) cl.newInstance();
		database.setProperty("create-database", "true");
		DatabaseManager.registerDatabase(database);
		Collection col = null;
		Process retVal = null;
		XMLResource res = null;
		try {
			// get the collection
			col = DatabaseManager.getCollection(conn.uri + processCollectionId);
			if (col == null) {
				return null;
			}
			
			res = (XMLResource) col.getResource(id);
			System.out.println(res.getContent());
			JAXBContext jaxbContext = JAXBContext.newInstance(Process.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			
			retVal = (Process) unmarshaller.unmarshal(res.getContentAsDOM());
			if(retVal == null) {
				throw new Exception("Unmarshaling failed");

				}
		} finally {
			// don't forget to cleanup
			if (col != null) {
				try {
					col.close();
				} catch (XMLDBException xe) {
					xe.printStackTrace();
				}
			}
		}
		System.out.println("----------------------");
		return (String) res.getContent();
	}
	
	
	public String generateNewId() throws Exception {
		String retVal = "process";
		String id = UUID.randomUUID().toString();

		retVal += id;
		return retVal;
	}
	
	public String submitReview(String reviewId, String processId) throws Exception {
		Process process = this.findCLById(processId);
		System.out.println(reviewId);
		for(ReviewElement r : process.getReviews().getReviewElement()) {
			if(r.getReviewId().equals(reviewId)) {
				r.setStatus("reviewed");
				System.out.println("REVIEWWDD");
			}
		}

		this.update(processId, process);
		return processId;
	}
	
	public List<String> getReviewsForUser(String reviewerId) throws Exception {
		String xQuery = "/process/reviews/review_element[reviewer_id = '" + reviewerId + "']/review_id//text()";
		List<String> retVal = new ArrayList<>();
		String ret = null;
		ReviewElement p = null;
		try {
			org.xmldb.api.base.ResourceSet result = existRetrieve.executeXPathExpression(processCollectionId, xQuery,
					XUpdateTemplate.TARGET_NAMESPACE + "/Process");
			ResourceIterator it = result.getIterator();
			Resource res = null;
			while (it.hasMoreResources()) {
				ret = it.nextResource().getContent().toString();
				
				
				String review = reviewRepository.findById(ret);
				retVal.add(review);
				System.out.println(retVal.size() + "SIZEE");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return retVal;
	}
}
