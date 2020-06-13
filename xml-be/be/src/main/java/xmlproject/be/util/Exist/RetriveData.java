package xmlproject.be.util.Exist;

import org.springframework.stereotype.Component;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XPathQueryService;

import xmlproject.be.util.Authentication.*;
import xmlproject.be.util.Authentication.AuthenticationUtilities.ConnectionProperties;

@Component
public class RetriveData {
	/**
     * conn XML DB connection properties args[0] Should be the collection ID to
     * access
     */
    public static ResourceSet executeXPathExpression(String collectionId, String xpathExp, String TARGET_NAMESPACE) throws Exception {

        ResourceSet result = null;

        ConnectionProperties conn = AuthenticationUtilities.loadProperties();
    	// initialize database driver
    	System.out.println("[INFO] Loading driver class: " + conn.driver);
        Class<?> cl = Class.forName(conn.driver);
        
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        
        DatabaseManager.registerDatabase(database);
        
        Collection col = null;
        
        try { 
        	// get the collection
        	System.out.println("[INFO] Retrieving the collection: " + collectionId);
            col = DatabaseManager.getCollection(conn.uri + collectionId);
            
            if(col == null)
            {
                return null;
            }
            // get an instance of xpath query service
            XPathQueryService xpathService = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            xpathService.setProperty("indent", "yes");
            
            // make the service aware of namespaces, using the default one
            xpathService.setNamespace("", TARGET_NAMESPACE);
            // System.out.println("\n[INPUT] Enter an XPath expression (e.g. doc(\"1.xml\")//book[@category=\"WEB\" and price>35]/title): ");
            // execute xpath expression 
            System.out.println("[INFO] Invoking XPath query service for: " + xpathExp);
            result = xpathService.query(xpathExp);
            // handle the results
            System.out.println("[INFO] Handling the results... ");
        } finally {
        	
            // don't forget to cleanup
            if(col != null) {
                try { 
                	col.close();
                } catch (XMLDBException xe) {
                	xe.printStackTrace();
                }
            }
        }
        return result;
    }
}
