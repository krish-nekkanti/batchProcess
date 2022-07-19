/**
 * @(#) Document.java
 */

package com.radian.foundation.os.content.composition;

import java.io.OutputStream;
import com.radian.foundation.os.content.composition.DocumentType;
import com.radian.foundation.os.content.composition.DocGenException;

/**
 * This represents a document generated by the document generation framework
 */
public interface Document {
	
    /** retrieve the content of the document
     * @return content of the document
     */    
	public OutputStream getContent( );

        /** retrieve the content of the document in postscript format. This is only applicable for documents with Document Type POSTSCRIPT. For Document Type PDF or TEXT documents, this method will not return anything.
         * @throws DocGenException
         * @return Postscript format of the document
         */        
        public OutputStream getContentasPostScript( ) throws DocGenException;
        
        /** retrieve the document type of the document
         * @return Document type of the document
         */        
        public DocumentType getDocumentType();
	
        /** Retrieve the template name of the template the document was generated from
         * @return template name
         */        
	public String getTemplateName();
        
        /** Retrieve the template version of the template the document was generated from
         * @return template version
         */        
        public String getTemplateVersion();
        
        /** compares this document to another document
         * @param matchDocument the document to compare
         * @return true if the documents match, false otherwise
         */        
        public boolean equals(Document matchDocument);
        
        /** Load a document
         * @param content The content of the document
         * @param docType the document type of the document
         * @param templateName the name of the template the document was generated from
         * @param templateVersion the version of the template the document was created from
         */        
        public void loadDocument(OutputStream content,DocumentType docType,String templateName, String templateVersion);
        
        /** Load a document
         * @param content The content of the document
         * @param postscriptContent The content of the document in postscript format
         * @param docType the document type of the document
         * @param templateName the name of the template the document was generated from
         * @param templateVersion the version of the template the document was created from
         */ 
        public void loadDocument(OutputStream content, OutputStream postscriptContent, DocumentType docType,String templateName, String templateVersion);
        
	
}