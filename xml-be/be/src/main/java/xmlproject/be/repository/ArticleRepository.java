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

import rs.ac.uns.xmltim.article.Article;
import xmlproject.be.util.Authentication.AuthenticationUtilities;
import xmlproject.be.util.Authentication.AuthenticationUtilities.ConnectionProperties;
import xmlproject.be.util.Exist.RetriveData;
import xmlproject.be.util.Exist.StoreData;
import xmlproject.be.util.Exist.UpdateData;
@Repository
public class ArticleRepository {

	@Autowired
	RetriveData existRetrieve;


	public static String articleCollectionId = "/db/XWS/article";
	public static String articleSchemaPath = "src/main/resources/data/Article.xsd";

	public String save(String articleXML) throws Exception {
		String Id = generateNewId();
		Article coverLetter = null;
		
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(Article.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			StringReader sr = new StringReader(articleXML);
			

			coverLetter = (Article) unmarshaller.unmarshal(sr);
			coverLetter.setID(Id);
			System.out.println(coverLetter.getID());
			System.out.println(coverLetter.getID());
			JAXBContext context = JAXBContext.newInstance(Article.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			marshaller.marshal(coverLetter, stream);
			articleXML = new String(stream.toByteArray());
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		StoreData.store(articleCollectionId, Id, articleXML);
		return Id;
	}
	
	public String update(String id, String articleXML) throws Exception {
		Article articleOld = this.findCLById(id);
		if (articleOld == null) {
			throw new Exception("No Article with this id");
		}
		this.delete(id);
		Article article = null;
		
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(Article.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			StringReader sr = new StringReader(articleXML);
			
			System.out.println(articleXML);

			article = (Article) unmarshaller.unmarshal(sr);
			System.out.println(article.getID());
			JAXBContext context = JAXBContext.newInstance(Article.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			marshaller.marshal(article, stream);
			articleXML = new String(stream.toByteArray());
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		

		StoreData.store(articleCollectionId, id, articleXML);
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
	
	public Article findCLById(String id) throws Exception {

		ResourceSet result = null;
		ConnectionProperties conn = AuthenticationUtilities.loadProperties();
		Class<?> cl = Class.forName(conn.driver);
		Database database = (Database) cl.newInstance();
		database.setProperty("create-database", "true");
		DatabaseManager.registerDatabase(database);
		Collection col = null;
		Article retVal = null;
		try {
			// get the collection
			col = DatabaseManager.getCollection(conn.uri + articleCollectionId);
			if (col == null) {
				return null;
			}
			
			XMLResource res = (XMLResource) col.getResource(id);
			System.out.println(res.getContent());
			JAXBContext jaxbContext = JAXBContext.newInstance(Article.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			
			retVal = (Article) unmarshaller.unmarshal(res.getContentAsDOM());
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
		Article retVal = null;
		XMLResource res = null;
		try {
			// get the collection
			col = DatabaseManager.getCollection(conn.uri + articleCollectionId);
			if (col == null) {
				return null;
			}
			
			res = (XMLResource) col.getResource(id);
			System.out.println(res.getContent());
			JAXBContext jaxbContext = JAXBContext.newInstance(Article.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			
			retVal = (Article) unmarshaller.unmarshal(res.getContentAsDOM());
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
		String retVal = "article";
		String id = UUID.randomUUID().toString();

		retVal += id;
		return retVal;
	}

}