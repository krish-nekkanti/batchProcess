
package com.radian.foundation.os.content.composition;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.transform.JDOMSource;

import com.radian.foundation.bs.BaseFactory;
import com.radian.foundation.os.content.composition.impl.DocTemplateImpl;

           
/** Factory that loads and caches DocTemplate objects and returns requested templates to the end user
 *
 * @author Drea Leed
 */
public final class DocTemplateFactory extends BaseFactory
{
        private static DocTemplateFactory docTemplateFactory;
	private static HashMap templates = new HashMap();
       
    /**
     * Constructs DocTemplate factory.
     */
    
    private DocTemplateFactory()
    {                       
        super();
    }

    /** retrieves an instance of the factory
     *
     * @return DocTemplateFactory factory
     */
    public static DocTemplateFactory getInstance() {
        if (docTemplateFactory == null) {
            docTemplateFactory=new DocTemplateFactory();
        }
        return docTemplateFactory;
    }
    
    /** retrieves a template that matches the input parameters
     * @param name
     * @param version
     * @param docTypeEnum
     * @throws DocGenException
     * @return  DocTemplate template
     */    
    public DocTemplate getTemplate(String name, String version, DocumentTypeEnum docTypeEnum) throws DocGenException
    {
        DocTemplate template=null;
        if (version==null) 
        {
            template = (DocTemplate) templates.get(name + "|DEFAULT|" + String.valueOf(docTypeEnum.getID()));
        }
        else
        {
             template = (DocTemplate) templates.get(name + "|" + version + "|" + String.valueOf(docTypeEnum.getID()));
        }
        
       if (template == null)
       {
          String templateDir=DocTemplateConfiguration.getTemplateDir();
          if (version==null) {
               version="DEFAULT";
               template=DocTemplateConfiguration.getTemplate(name,docTypeEnum);
          }
          else {
               template=DocTemplateConfiguration.getTemplate(name,version,docTypeEnum);
          }  
          
          if (template == null)
          {
                throw new DocGenException("Template does not exist with these parameters");
          }
    
          template.setTemplateData(loadXMLFile(templateDir + template.getFileName()));
          synchronized(templates)
          {
                templates.put(name + "|" + version + "|" + String.valueOf(docTypeEnum.getID()), template);              
          }
       }
       
        //if this template isn't to be cached, reload data from template file every time
        else if (template.isCached()==false) 
        {
            String templateDir=DocTemplateConfiguration.getTemplateDir();
            template.setTemplateData(loadXMLFile(templateDir + template.getFileName()));
            
            //update template stored in hashmap
            synchronized(templates)
            {
                templates.remove(template.getName() + "|" + template.getVersion() + "|" + String.valueOf(docTypeEnum.getID()));
                templates.put(template.getName() + "|" + template.getVersion() + "|" + String.valueOf(docTypeEnum.getID()), template);              
            }
            if (template.isDefault())
            {
                 synchronized(templates)
                {
                    templates.remove(template.getName() + "|DEFAULT|" + docTypeEnum.getValue());
                    templates.put(template.getName() + "|DEFAULT|" + docTypeEnum.getValue(), template);              
                }   
            }
        }
        System.out.println("retrieving template " + template.toString());
        return template;
    }   
    
    /** Retrieves the default template for the given input parameters
     * @param String templateName the name of the template to return
     * @param DocumentTypeEnum docTypeEnum The document type to return (PDF, TEXT, etc)
     * @throws DocGenException
     * @return DocTemplate template
     */    
    public DocTemplate getTemplate(String name, DocumentTypeEnum docTypeEnum) throws DocGenException
    {
        DocTemplate template = getTemplate (name, null, docTypeEnum);
        return template;
    }  
	
    /** returns an empty DocTemplate object
     * @return DocTemplate template
     */    
    public DocTemplate getTemplate()
    {
        return new DocTemplateImpl();
    }
    
    //replace with axiom util to load xml files?
    private JDOMSource loadXMLFile(String fileName) throws DocGenException
    {
        JDOMSource XMLData=null;
        try 
        {
            //load & parse template, & then load into Source form for storage in Template
            SAXBuilder builder=new SAXBuilder();
            InputStream templateData=DocTemplateFactory.class.getClassLoader().getResourceAsStream(fileName);
            org.jdom.Document tmpDoc=builder.build(templateData);
            
            XMLData=new JDOMSource(tmpDoc);
            
            //XMLOutputter xmlout=new XMLOutputter();
            //System.out.println("loaded template: \"" + xmlout.outputString(tmpDoc) );
            return XMLData;
        }
        catch (JDOMException spe)
        {
            throw new DocGenException(spe);
        }
        catch (IOException ie)
        {
            throw new DocGenException(ie);
        }
    }
}
