package xmlproject.be.repository;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import xmlproject.be.model.CoverLetter;
import xmlproject.be.util.Exist.RetriveData;
import xmlproject.be.util.Exist.StoreData;;

@Repository
public class CoverLetterRepository {

	@Autowired
	RetriveData existRetrieve;


	public static String coverLetterCollectionId = "/db/XWS/coverLetter";
	public static String coverLetterSchemaPath = "src/main/resources/data/CoverLetter.xsd";

	public String save(CoverLetter coverLetter) throws Exception {
		String Id = generateNewCoverLetterId();
		coverLetter.setID(Id);
		
		String coverLetterXML = "";
		
		try {

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


	public String generateNewCoverLetterId() throws Exception {
		String retVal = "coverLetter";
		String id = UUID.randomUUID().toString();

		retVal += id;
		return retVal;
	}
}