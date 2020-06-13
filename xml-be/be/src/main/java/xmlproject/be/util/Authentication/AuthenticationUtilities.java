package xmlproject.be.util.Authentication;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;

/**
 * Utilities to support and simplify examples.
 */
public class AuthenticationUtilities {
	
	private static String connectionUri = "xmldb:exist://%1$s:%2$s/exist/xmlrpc";
	
	/**
	 * Connection parameters.
	 */
	static public class ConnectionProperties {

		public String host;
		public int port = -1;
		public String user;
		public String password;
		public String driver;
		public String uri;

		public ConnectionProperties(Properties props) {
			super();
			
			user = props.getProperty("conn.user").trim();
			password = props.getProperty("conn.password").trim();

			host = props.getProperty("conn.host").trim();
			port = Integer.parseInt(props.getProperty("conn.port"));
			
			uri = String.format(connectionUri, host, port);
			
			driver = props.getProperty("conn.driver").trim();
		}
	}

	/**
	 * Read the configuration properties for the example.
	 * 
	 * @return the configuration object
	 */
	public static ConnectionProperties loadProperties() throws IOException {
		String propsName = "application.properties";

		InputStream propsStream = openStream(propsName);
		if (propsStream == null)
			throw new IOException("Could not read properties " + propsName);

		Properties props = new Properties();
		props.load(propsStream);

		return new ConnectionProperties(props);
	}

	/**
	 * Read a resource for an example.
	 * 
	 * @param fileName
	 *            the name of the resource
	 * @return an input stream for the resource
	 * @throws IOException
	 */
	public static InputStream openStream(String fileName) throws IOException {
		return AuthenticationUtilities.class.getClassLoader().getResourceAsStream(fileName);
	}
	
	public static Collection initDBCollection(String collectionId) throws Exception
    {
		ConnectionProperties conn = AuthenticationUtilities.loadProperties();
        Collection col = null;
        Class<?> cl = Class.forName(conn.driver); 
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);
        col = DatabaseManager.getCollection(conn.uri + collectionId, conn.user, conn.password);
        col.setProperty("indent", "yes");
        return col;
    }
	
	// ------------  UTLITY for FusekiJena ----------------------
		static public class ConnectionPropertiesFusekiJena {

			public String endpoint;
			public String dataset;
			
			public String queryEndpoint;
			public String updateEndpoint;
			public String dataEndpoint;
			

			public ConnectionPropertiesFusekiJena(Properties props) {
				super();
				dataset = props.getProperty("conn.dataset").trim();
				endpoint = props.getProperty("conn.endpoint").trim();
				
				queryEndpoint = String.join("/", endpoint, dataset, props.getProperty("conn.query").trim());
				updateEndpoint = String.join("/", endpoint, dataset, props.getProperty("conn.update").trim());
				dataEndpoint = String.join("/", endpoint, dataset, props.getProperty("conn.data").trim());
			}
		}

		/**
		 * Read the configuration properties for the example.
		 * 
		 * @return the configuration object
		 */
		public static ConnectionPropertiesFusekiJena loadPropertiesFusekiJena() throws IOException {
			String propsName = "fuseki_jena.properties";

			InputStream propsStream = openStream(propsName);
			if (propsStream == null)
				throw new IOException("Could not read properties " + propsName);

			Properties props = new Properties();
			props.load(propsStream);

			return new ConnectionPropertiesFusekiJena(props);
		}
	
}
