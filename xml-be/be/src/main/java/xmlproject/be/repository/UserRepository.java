package xmlproject.be.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import rs.ac.uns.xmltim.user.Role;
import rs.ac.uns.xmltim.user.User;
import xmlproject.be.util.Authentication.AuthenticationUtilities;
import xmlproject.be.util.Exist.RetriveData;
import xmlproject.be.util.Exist.StoreData;
import xmlproject.be.util.template.XUpdateTemplate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class UserRepository {

    @Autowired
    RetriveData existRetrieve;


    public static String userCollectionId = "/db/XWS/user";
    public static String userSchemaPath = "src/main/resources/data/User.xsd";

    public void save(User user) throws Exception {
        user.setID(generateNewId());
        String userXML = "";

        JAXBContext context = JAXBContext.newInstance(User.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        marshaller.marshal(user, stream);
        userXML = new String(stream.toByteArray());

        StoreData.store(userCollectionId, user.getID(), userXML);
    }

    public String findByIdXml(String id) throws Exception {
        AuthenticationUtilities.ConnectionProperties conn = AuthenticationUtilities.loadProperties();
        Class<?> cl = Class.forName(conn.driver);
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);
        Collection col = null;
        XMLResource res;

        try {
            // get the collection
            col = DatabaseManager.getCollection(conn.uri + userCollectionId);
            if (col == null) {
                return null;
            }

            res = (XMLResource) col.getResource(id);
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

        if (res == null || res.getContent() == null)
            return null;

        return res.getContent().toString();
    }

    public User findById(String id) throws Exception {
        String xml = findByIdXml(id);
        if (xml == null)
            return null;

        User user;

        JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        StringReader reader = new StringReader(xml);
        user = (User) unmarshaller.unmarshal(reader);

        return user;
    }

    public List<String> findAllXml() throws Exception {
        String xQuery = "/";
        XMLResource res = null;
        List<String> users = new ArrayList<>();

        try {
            org.xmldb.api.base.ResourceSet result = existRetrieve.executeXPathExpression(userCollectionId, xQuery, XUpdateTemplate.TARGET_NAMESPACE + "/User");
            ResourceIterator it = result.getIterator();
            while (it.hasMoreResources()) {
                res = (XMLResource) it.nextResource();
                users.add(res.getContent().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (res == null || res.getContent() == null)
            return null;

        return users;
    }

    public List<User> findAll() throws Exception {
        List<String> usersXml = findAllXml();
        if (usersXml == null)
            return null;

        List<User> users = new ArrayList<>();

        JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        for (String userXml : usersXml) {
            StringReader reader = new StringReader(userXml);
            users.add((User) unmarshaller.unmarshal(reader));
        }

        return users;
    }

    public String findByUsernameXml(String username) throws Exception {
        String xQuery = "//user[username=\"" + username + "\"" + "]";
        XMLResource res = null;

        try {
            org.xmldb.api.base.ResourceSet result = existRetrieve.executeXPathExpression(userCollectionId, xQuery, XUpdateTemplate.TARGET_NAMESPACE + "/User");
            ResourceIterator it = result.getIterator();
            while (it.hasMoreResources()) {
                res = (XMLResource) it.nextResource();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (res == null || res.getContent() == null)
            return null;

        return res.getContent().toString();
    }
    
    public List<User> findAllReviewers() throws Exception {
        String xQuery = "//user[role=\"" + "reviewer" + "\"" + "]";
        XMLResource res = null;
        List<User> users = new ArrayList<>();

        try {
            org.xmldb.api.base.ResourceSet result = existRetrieve.executeXPathExpression(userCollectionId, xQuery, XUpdateTemplate.TARGET_NAMESPACE + "/User");
            ResourceIterator it = result.getIterator();
            while (it.hasMoreResources()) {
                res = (XMLResource) it.nextResource();
                JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                StringReader reader = new StringReader(res.getContent().toString());
                users.add((User) unmarshaller.unmarshal(reader));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (res == null || res.getContent() == null)
            return null;

        return users;
    }

    public User findByUsername(String username) throws Exception {
        String xml = findByUsernameXml(username);
        if (xml == null)
            return null;

        User user;

        JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        StringReader reader = new StringReader(xml);
        user = (User) unmarshaller.unmarshal(reader);

        return user;
    }

    public String findByEmailXml(String email) throws Exception {
        String xQuery = "//user[email=\"" + email + "\"" + "]";
        XMLResource res = null;
        User user = null;

        try {
            org.xmldb.api.base.ResourceSet result = existRetrieve.executeXPathExpression(userCollectionId, xQuery, XUpdateTemplate.TARGET_NAMESPACE + "/User");
            ResourceIterator it = result.getIterator();
            while (it.hasMoreResources()) {
                res = (XMLResource) it.nextResource();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (res == null || res.getContent() == null)
            return null;

        return res.getContent().toString();
    }

    public User findByEmail(String email) throws Exception {
        String xml = findByEmailXml(email);
        if (xml == null)
            return null;

        User user;

        JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        StringReader reader = new StringReader(xml);
        user = (User) unmarshaller.unmarshal(reader);

        return user;
    }

    public String generateNewId() throws Exception {
        String retVal = "user";
        String id = UUID.randomUUID().toString();

        retVal += id;
        return retVal;
    }
}
