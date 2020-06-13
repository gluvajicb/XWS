package xmlproject.be.util.DOMParser;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ErrorHandlerImpl implements ErrorHandler{
	/*
	 * Error handling methods
	 */

	@Override
	public void error(SAXParseException err) throws SAXParseException {
		// Propagate the exception
		throw err;
	}

	@Override
	public void fatalError(SAXParseException err) throws SAXException {
		// Propagate the exception
		throw err;
	}
	
	@Override
    public void warning(SAXParseException err) throws SAXParseException {
    	System.out.println("[WARN] Warning, line: " + err.getLineNumber() + ", uri: " + err.getSystemId());
        System.out.println("[WARN] " + err.getMessage());
    }
}