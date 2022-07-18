
package com.radian.foundation.os.content.composition;

import java.io.InputStream;

/**
 * DocumentGenerator  interface.
 *
 * @author Drea Leed
 */
public interface DocumentGenerator
{
    
        /** generate a document based upon the input xml data, the input template name and the input document type
         * @param XMLData The XML Data for the document to be generated
         * @param templateName the template to use for this document
         * @param docTypeEnum the document type to generate (PDF, TEXT, etc)
         * @throws DocGenException
         * @return a Document object containing the created document and document metadata
         */        
	public Document generate(InputStream XMLData,String templateName, DocumentTypeEnum docTypeEnum) throws DocGenException;
	
         /** generate a document based upon the input xml data, the input template name and the input document type
         * @param XMLData The XML Data for the document to be generated
         * @param templateName the template to use for this document
         * @param version the version of the template to use
         * @param docTypeEnum the document type to generate (PDF, TEXT, etc)
         * @throws DocGenException
         * @return a Document object containing the created document and document metadata
         */ 
	public Document generate(InputStream XMLData,String templateName, String version, DocumentTypeEnum docTypeEnum) throws DocGenException;

         /** generate a document based upon the input xml data, the input template name and the input document type
         * @param XMLData The XML Data for the document to be generated
         * @param templateName the template to use for this document
         * @param version the version of the template to use
         * @param docTypeEnum the document type to generate (PDF, TEXT, etc)
         * @throws DocGenException
         * @return a Document object containing the created document and document metadata
         */ 

}
