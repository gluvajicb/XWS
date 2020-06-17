package xmlproject.be.util.Transformer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
public class PDFTransformer {
	private static DocumentBuilderFactory documentFactory;

	private static TransformerFactory transformerFactory;

	public static final String INPUT_FILE = "";

	public static final String XSL_FILE = "";

	public static final String HTML_FILE = "";

	public static final String OUTPUT_FILE = "";

	static {

		/* Inicijalizacija DOM fabrike */
		documentFactory = DocumentBuilderFactory.newInstance();
		documentFactory.setNamespaceAware(true);
		documentFactory.setIgnoringComments(true);
		documentFactory.setIgnoringElementContentWhitespace(true);

		/* Inicijalizacija Transformer fabrike */
		transformerFactory = TransformerFactory.newInstance();

	}

	/**
	 * Creates a PDF using iText Java API
	 * 
	 * @param filePath
	 * @throws IOException
	 * @throws DocumentException
	 */
	public void generatePDF(String filePath) throws IOException, DocumentException {

		// Step 1
		Document document = new Document();

		// Step 2
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));

		// Step 3
		document.open();

		// Step 4
		XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(HTML_FILE));

		// Step 5
		document.close();

	}

	public org.w3c.dom.Document buildDocument(String filePath) {

		org.w3c.dom.Document document = null;
		try {
			DocumentBuilder builder = documentFactory.newDocumentBuilder();
			document = builder.parse(new File(filePath));
		} catch (Exception e) {
			return null;

		}

		return document;
	}

	public void generateHTML(String xmlPath, String xslPath) throws FileNotFoundException {

		try {

			// Initialize Transformer instance
			StreamSource transformSource = new StreamSource(new File(xslPath));
			Transformer transformer = transformerFactory.newTransformer(transformSource);
			transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			// Generate XHTML
			transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");

			// Transform DOM to HTML
			DOMSource source = new DOMSource(buildDocument(xmlPath));
			StreamResult result = new StreamResult(new FileOutputStream(HTML_FILE));
			transformer.transform(source, result);

		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}

	}

}
