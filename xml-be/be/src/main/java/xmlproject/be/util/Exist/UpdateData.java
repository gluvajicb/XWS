package xmlproject.be.util.Exist;

import org.springframework.stereotype.Component;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XUpdateQueryService;

import xmlproject.be.util.Authentication.AuthenticationUtilities;

@Component
public class UpdateData {
	public static long update(String collectionId, String documentId, String contextXPath, String xmlFragment, String updateTemplate ) throws Exception
    {
        long mods = 0;
        Collection col = AuthenticationUtilities.initDBCollection(collectionId);
        try{
            XUpdateQueryService xupdateService = (XUpdateQueryService) col.getService("XUpdateQueryService", "1.0");
            xupdateService.setProperty("indent", "yes");

            String command = String.format(updateTemplate, contextXPath, xmlFragment);

            System.out.println(command);

            mods = xupdateService.updateResource(documentId, command);
            System.out.println("[INFO] " + mods + " modifications processed.");
            //long mods = xupdateService.updateResource(,String.format(INSERT_AFTER, contextXPath, xmlFragment));
            return mods;    
        }
        finally {
            if(col != null) {
                try { 
                	col.close();
                } catch (XMLDBException xe) {
                	xe.printStackTrace();
                }
            }
        }
    }

    public static long delete(String collectionId,String documentId, String contextXPath) throws Exception{
        long mods = 0;
        Collection col = AuthenticationUtilities.initDBCollection(collectionId);
        try{
            Resource res = col.getResource(documentId);
            col.removeResource(res);
            mods = 1;
            return mods;
        }
        finally {
            if(col != null) {
                try { 
                	col.close();
                } catch (XMLDBException xe) {
                	xe.printStackTrace();
                }
            }
        }
    } 
}
