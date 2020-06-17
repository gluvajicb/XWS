package xmlproject.be.repository;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.catalina.util.ResourceSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import rs.ac.uns.xmltim.review.Review;
import xmlproject.be.service.ArticleService;
import xmlproject.be.util.Authentication.AuthenticationUtilities;
import xmlproject.be.util.Authentication.AuthenticationUtilities.ConnectionProperties;
import xmlproject.be.util.Exist.RetriveData;
import xmlproject.be.util.Exist.StoreData;
import xmlproject.be.util.Exist.UpdateData;

@Repository
public class ReviewRepository {
	@Autowired
	RetriveData existRetrieve;

	@Autowired
	ArticleService articleService;
	
	public static String articleCollectionId = "/db/XWS/review";
	public static String articleSchemaPath = "src/main/resources/data/Review.xsd";

	public String save(String reviewXML) throws Exception {
		String Id = generateNewId();
		Review coverLetter = null;
		
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(Review.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			StringReader sr = new StringReader(reviewXML);
			

			coverLetter = (Review) unmarshaller.unmarshal(sr);
			coverLetter.setID(Id);
			
			
			String ret = articleService.findById(coverLetter.getArticleId());
			if(ret == null)
				throw new Exception("Article not found");
			
			System.out.println(coverLetter.getID());
			System.out.println(coverLetter.getID());
			JAXBContext context = JAXBContext.newInstance(Review.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			marshaller.marshal(coverLetter, stream);
			reviewXML = new String(stream.toByteArray());
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		StoreData.store(articleCollectionId, Id, reviewXML);
		return Id;
	}
	
	public String save(Review coverLetter) throws Exception {
		String Id = generateNewId();
		String reviewXML = "";
		try {
			coverLetter.setID(Id);

			String ret = articleService.findById(coverLetter.getArticleId());
			if(ret == null)
				throw new Exception("Article not found");
			
			System.out.println(coverLetter.getID());
			System.out.println(coverLetter.getID());
			JAXBContext context = JAXBContext.newInstance(Review.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			marshaller.marshal(coverLetter, stream);
			reviewXML = new String(stream.toByteArray());
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		StoreData.store(articleCollectionId, Id, reviewXML);
		return Id;
	}
	
	public String update(String id, String reviewXML) throws Exception {
		Review reviewOld = this.findCLById(id);
		if (reviewOld == null) {
			throw new Exception("No Article with this id");
		}
		this.delete(id);
		Review review = null;
		
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(Review.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			StringReader sr = new StringReader(reviewXML);
			

			review = (Review) unmarshaller.unmarshal(sr);
			System.out.println(review.getID());
			
			String ret = articleService.findById(review.getArticleId());
			if(ret == null)
				throw new Exception("Article not found");
			
			JAXBContext context = JAXBContext.newInstance(Review.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			marshaller.marshal(review, stream);
			reviewXML = new String(stream.toByteArray());
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		

		StoreData.store(articleCollectionId, id, reviewXML);
		return id;
	}
	
	public boolean delete(String id) throws Exception {
		System.out.println(id);
		long enabled = UpdateData.delete(articleCollectionId, id);
		
		System.out.println(enabled);
		if (enabled == 0) {
			return false;
		}
		return true;
	}
	
	public Review findCLById(String id) throws Exception {

		ResourceSet result = null;
		ConnectionProperties conn = AuthenticationUtilities.loadProperties();
		Class<?> cl = Class.forName(conn.driver);
		Database database = (Database) cl.newInstance();
		database.setProperty("create-database", "true");
		DatabaseManager.registerDatabase(database);
		Collection col = null;
		Review retVal = null;
		try {
			// get the collection
			col = DatabaseManager.getCollection(conn.uri + articleCollectionId);
			if (col == null) {
				return null;
			}
			
			XMLResource res = (XMLResource) col.getResource(id);
			System.out.println(res.getContent());
			JAXBContext jaxbContext = JAXBContext.newInstance(Review.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			
			retVal = (Review) unmarshaller.unmarshal(res.getContentAsDOM());
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
		Review retVal = null;
		XMLResource res = null;
		try {
			// get the collection
			col = DatabaseManager.getCollection(conn.uri + articleCollectionId);
			if (col == null) {
				return null;
			}
			
			res = (XMLResource) col.getResource(id);
			System.out.println(res.getContent());
			JAXBContext jaxbContext = JAXBContext.newInstance(Review.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			
			retVal = (Review) unmarshaller.unmarshal(res.getContentAsDOM());
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
		String retVal = "review";
		String id = UUID.randomUUID().toString();

		retVal += id;
		return retVal;
	}
}
