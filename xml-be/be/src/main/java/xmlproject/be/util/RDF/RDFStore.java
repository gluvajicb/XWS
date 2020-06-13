package xmlproject.be.util.RDF;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;

import xmlproject.be.util.Authentication.AuthenticationUtilities;
import xmlproject.be.util.Authentication.AuthenticationUtilities.ConnectionPropertiesFusekiJena;



public class RDFStore {

	public static String rdfFilePath = "src/main/resources/data/rdfa/output.rdf";

    public static void store(StringWriter rdfData, String SPARQL_NAMED_GRAPH_URI) throws Exception
    {

        PrintWriter prw = new PrintWriter(new File(rdfFilePath));
        prw.write(rdfData.toString());
        prw.flush();

        ConnectionPropertiesFusekiJena conn = AuthenticationUtilities.loadPropertiesFusekiJena();

        Model model = ModelFactory.createDefaultModel();
        model.read(rdfFilePath);
        
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		model.write(out, SparqlUtil.NTRIPLES);
		
				
		// Writing the named graph
		String sparqlUpdate = SparqlUtil.insertData(conn.dataEndpoint + SPARQL_NAMED_GRAPH_URI, new String(out.toByteArray()));

		// UpdateRequest represents a unit of execution
		UpdateRequest update = UpdateFactory.create(sparqlUpdate);

		System.out.println("Update " + update + " ** " + conn.updateEndpoint);
		UpdateProcessor processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint);
		processor.execute();
		
    }
}
