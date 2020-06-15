package xmlproject.be.repository;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.UUID;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.catalina.util.ResourceSet;
import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;

import rs.ac.uns.xmltim.coverletter.CoverLetter;
import xmlproject.be.util.NSPrefixMapper;
import xmlproject.be.util.Authentication.AuthenticationUtilities;
import xmlproject.be.util.Authentication.AuthenticationUtilities.ConnectionProperties;
import xmlproject.be.util.Exist.RetriveData;
import xmlproject.be.util.Exist.StoreData;
import xmlproject.be.util.Exist.UpdateData;
import xmlproject.be.util.template.XUpdateTemplate;;

@Repository
public class CoverLetterRepository {

	@Autowired
	RetriveData existRetrieve;


	public static String coverLetterCollectionId = "/db/XWS/coverLetter";
	public static String coverLetterSchemaPath = "src/main/resources/data/CoverLetter.xsd";

	public String save(String coverLetterXML) throws Exception {
		String Id = generateNewCoverLetterId();
		CoverLetter coverLetter = null;
		
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(CoverLetter.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			StringReader sr = new StringReader(coverLetterXML);
			
			System.out.println(coverLetterXML);

			coverLetter = (CoverLetter) unmarshaller.unmarshal(sr);
			coverLetter.setID(Id);
			System.out.println(coverLetter.getID());
			JAXBContext context = JAXBContext.newInstance(CoverLetter.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			marshaller.marshal(coverLetter, stream);
			coverLetterXML = new String(stream.toByteArray());
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		StoreData.store(coverLetterCollectionId, Id, coverLetterXML);
		return Id;
	}
	
	public String update(String id, String coverLetterXML) throws Exception {
		CoverLetter coverLetterOld = this.findCLById(id);
		if (coverLetterOld == null) {
			throw new Exception("No CoverLetter with this id");
		}
		this.delete(id);
		CoverLetter coverLetter = null;
		
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(CoverLetter.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			StringReader sr = new StringReader(coverLetterXML);
			
			System.out.println(coverLetterXML);

			coverLetter = (CoverLetter) unmarshaller.unmarshal(sr);
			System.out.println(coverLetter.getID());
			JAXBContext context = JAXBContext.newInstance(CoverLetter.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			marshaller.marshal(coverLetter, stream);
			coverLetterXML = new String(stream.toByteArray());
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		

		StoreData.store(coverLetterCollectionId, id, coverLetterXML);
		return id;
	}
	
	public boolean delete(String id) throws Exception {
		System.out.println(id);
		long enabled = UpdateData.delete(coverLetterCollectionId, id);
		
		System.out.println(enabled);
		if (enabled == 0) {
			return false;
		}
		return true;
	}
	
	public CoverLetter findCLById(String id) throws Exception {

		ResourceSet result = null;
		ConnectionProperties conn = AuthenticationUtilities.loadProperties();
		Class<?> cl = Class.forName(conn.driver);
		Database database = (Database) cl.newInstance();
		database.setProperty("create-database", "true");
		DatabaseManager.registerDatabase(database);
		Collection col = null;
		CoverLetter retVal = null;
		try {
			// get the collection
			col = DatabaseManager.getCollection(conn.uri + coverLetterCollectionId);
			if (col == null) {
				return null;
			}
			
			XMLResource res = (XMLResource) col.getResource(id);
			System.out.println(res.getContent());
			JAXBContext jaxbContext = JAXBContext.newInstance(CoverLetter.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			
			retVal = (CoverLetter) unmarshaller.unmarshal(res.getContentAsDOM());
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
		CoverLetter retVal = null;
		XMLResource res = null;
		try {
			// get the collection
			col = DatabaseManager.getCollection(conn.uri + coverLetterCollectionId);
			if (col == null) {
				return null;
			}
			
			res = (XMLResource) col.getResource(id);
			System.out.println(res.getContent());
			JAXBContext jaxbContext = JAXBContext.newInstance(CoverLetter.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			
			retVal = (CoverLetter) unmarshaller.unmarshal(res.getContentAsDOM());
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

		return (String) res.getContent();
	}



	public String generateNewCoverLetterId() throws Exception {
		String retVal = "coverLetter";
		String id = UUID.randomUUID().toString();

		retVal += id;
		return retVal;
	}

}