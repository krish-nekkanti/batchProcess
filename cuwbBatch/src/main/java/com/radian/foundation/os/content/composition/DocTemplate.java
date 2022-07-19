
package com.radian.foundation.os.content.composition;

import com.radian.foundation.os.content.composition.DocumentType;
import org.jdom.transform.JDOMSource;
/** Object representing a template containing formatting info, etc. required to generate a document.
 *
 * @author Drea Leed
 */
public interface DocTemplate
{
    /** Compares this DocTemplate with any other.
     * @param matchTemplate The DocTemplate to compare.
     * @return true if the templates match, false otherwise.
     */    
        public boolean equals(DocTemplate matchTemplate);
        
        /** Retrieves the ID of this template.
         * @return ID of this template.
         */        
        public long getID();
       
        /** get the filename of this template.
         * @return the filename of this template.
         */        
        public String getFileName();
        
        /** Indicates whether this is to be a cached template, or whether template data should be reloaded every time this template is requested from the Template Factory
         * @return true it the template is to be cached; false if the template is to be reloaded every time it's called.
         */        
        public boolean isCached();
        
        /** Indicates whether this template is the default version for all templates of this name and document type. For all templates with a given name and document type, only one can be the default.
         * @return true if this is the default template, false otherwise.
         */        
        public boolean isDefault();
        
        /** Retrieve the document type of this template.
         * @return document type metadata
         */        
    	public DocumentType getDocumentType();
        
        /** retrieve the name of this template
         * @return name of this template
         */        
        public String getName();
        
        /** retrieve the version of this template.
         * @return version of this template
         */        
        public String getVersion();
        
        /** Retrieve the xml content of this template in Source format
         * @return the xml source of this template.
         */        
	public JDOMSource getTemplateData() ;
        
        /** Retrieve a string rendering of template data
         * @return String rendering of template data
         */        
        public String toString();
       
        /** Set the template data to the input XML source.
         * @param templateData input xml source.
         */        
        public void setTemplateData(JDOMSource templateData);
        
        /** Load a DocTemplate with the given input data.
         * @param templateID ID of this template (unique).
         * @param templateName Name of this template.
         * @param templateVersion Version of this template.
         * @param templateData XML data source of this template.
         * @param templateFilename filename of this template
         * @param docType Document type of this template
         * @param isDefault whether this template is the default template for this template name/document type
         * @param isCached whether this template should be cached or reloaded on request
         */        
        public void loadTemplate(long templateID, String templateName, String templateVersion, JDOMSource templateData,String templateFilename, DocumentType docType,boolean isDefault,boolean isCached);

                 
}
