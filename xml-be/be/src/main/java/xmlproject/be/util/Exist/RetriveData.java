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

	public static ResourceSet executeXPathExpression(String collectionId, String xpathExp, String TARGET_NAMESPACE)
			throws Exception {
		ResourceSet result = null;
		ConnectionProperties conn = AuthenticationUtilities.loadProperties();
		Class<?> cl = Class.forName(conn.driver);
		Database database = (Database) cl.newInstance();
		database.setProperty("create-database", "true");
		DatabaseManager.registerDatabase(database);
		Collection col = null;
		try {
			// get the collection
			col = DatabaseManager.getCollection(conn.uri + collectionId);
			if (col == null) {
				return null;
			}
			
			System.out.println(col.getResource("coverLetterc1ce1dd1-ed4a-4f6c-a86e-ced74b5337a8"));
			// get an instance of xpath query service
			XPathQueryService xpathService = (XPathQueryService) col.getService("XPathQueryService", "1.0");
			xpathService.setProperty("indent", "yes");
			// make the service aware of namespaces, using the default one
			xpathService.setNamespace("", TARGET_NAMESPACE);
			// execute xpath expression
			System.out.println(xpathExp);
			result = xpathService.query(xpathExp);
			System.out.println(result.getSize());
			System.out.println(result.getResource(1).getContent());
			// handle the results
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
		return result;
	}
}