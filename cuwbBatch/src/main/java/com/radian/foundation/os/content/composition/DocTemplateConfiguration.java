/**
 * @(#) DocTemplateConfiguration.java
 *
 * @author Drea Leed
 */

package com.radian.foundation.os.content.composition;


import java.util.HashMap;
import java.util.Iterator;

import org.jdom.transform.JDOMSource;

import com.radian.foundation.common.config.Configuration;
import com.radian.foundation.common.config.ConfigurationException;
import com.radian.foundation.common.config.DefaultConfigurationBuilder;
import com.radian.foundation.common.util.ServiceLocator;
import com.radian.foundation.common.util.ServiceLocatorException;
import com.radian.foundation.os.content.composition.impl.DocumentTypeImpl;

/** This class loads Document template metadata and configuration information
 *  from the DocTemplateConfig.xml file into a collection of DocTemplate objects.
 */
public class DocTemplateConfiguration {
    
    /** config constants*/
    // main axiom config constants
    private final static String ROOT = "doc-generation";
    private final static String OBJECT_CONFIG = "object-config";
    private final static String TEMPLATE_DIR = "template-dir";
    private final static String PATH = "path";
    
    //template node constants
    private final static String TEMPLATE_ROOT = "templates";
    private final static String TEMPLATE = "template";
    private final static String TEMPLATE_NAME = "name";
    private final static String TEMPLATE_VERSION = "version";
    private final static String DEFAULT = "default";
    private final static String FILENAME = "filename";
    private final static String CACHE = "cache";
    private final static String DOCTYPEID = "doctypeid";
    
    //doc type constants
    private final static String DOCTYPE_ROOT = "doctypes";
    private final static String DOCTYPE = "doctype";
    private final static String DOCTYPE_NAME = "name";
    private final static String MIMETYPE = "mimetype";
    private final static String FILE_EXT = "fileext";
    
    private HashMap templates;
    private HashMap docTypes;
    private static String templateDir = null;
    
    /** singleton */
    private static DocTemplateConfiguration instance = null;
    
    private DocTemplateConfiguration() {
        templates = new HashMap();
        docTypes = new HashMap();
    }
    
    private static Configuration getConfiguration() throws ServiceLocatorException {
        return ServiceLocator.getInstance().getConfiguration();
    }
    
    /** retrieve an instance of DocTemplateConfiguration
     * @return DocTemplateConfiguration
     */    
    public static DocTemplateConfiguration getInstance() {
        if (instance == null) {
            synchronized (DocTemplateConfiguration.class) {
                if (instance == null)
                    instance = new DocTemplateConfiguration();
            }
        }
        return instance;
    }
    
    public static void init() throws DocGenException{
        loadTemplateConfig();
    }
    
    /** retrieve the directory where Document Templates are located from the config file
     * @throws DocGenException
     * @return path of the template directory
     */    
    public static String getTemplateDir() throws DocGenException
    {
        if (DocTemplateConfiguration.templateDir==null) {
            DocTemplateConfiguration.loadTemplateConfig();
            if (DocTemplateConfiguration.templateDir == null) {
                throw new DocGenException("Cannot retrieve template directory from configuration");
            }
        }
        return DocTemplateConfiguration.templateDir;        
    }
    
    /** Retrieve a collection of all DocTemplates
     * @return Collection of DocTemplate objects
     */    
    public static Iterator getAllTemplates()
    {
        return getInstance().templates.keySet().iterator();
    }
    
    /** Retrieve a collection of all DocumentType objects
     * @return a collection of all DocumentType objects
     */    
    public static Iterator getAllDocTypes()
    {
        return getInstance().docTypes.keySet().iterator();
    }
    
    /** Retrieve the metadata for the template that matches the specified input criteria
     * @param templateName The name of the template to retrieve
     * @param docTypeEnum The document type of the template (PDF, TEXT, etc)
     * @throws DocGenException
     * @return A DocTemplate object matching the specified criteria
     */    
    public static DocTemplate getTemplate(String templateName,  DocumentTypeEnum docTypeEnum)
    throws DocGenException 
    {
        DocTemplate template=null;
        template=(DocTemplate) getInstance().templates.get(templateName  + "|DEFAULT|" +  String.valueOf(docTypeEnum.getID()));
        
        if (template == null) {
            loadTemplateConfig();
            template=(DocTemplate) getInstance().templates.get(templateName  + "|DEFAULT|" +  String.valueOf(docTypeEnum.getID()));
            
            if (template == null) {
                throw new DocGenException(
                "No default template exists for template name \"" + templateName + "\" and document type " + String.valueOf(docTypeEnum.getID()));
            }
        }    
        return template;
    }
    
    
    /** Retrieve the metadata for the template that matches the specified input criteria
     * @param templateName The name of the template to retrieve
     * @param templateVersion the version of the template to retrieve
     * @param docTypeEnum The document type of the template (PDF, TEXT, etc)
     * @throws DocGenException
     * @return A DocTemplate object matching the specified criteria
     */      
    public static DocTemplate getTemplate(String templateName, String templateVersion, DocumentTypeEnum docTypeEnum)
    throws DocGenException 
    {
        DocTemplate template=null;
        template=(DocTemplate) getInstance().templates.get(templateName  + "|" + templateVersion + "|" +  String.valueOf(docTypeEnum.getID()));
        
        if (template == null) {
            loadTemplateConfig();
            template=(DocTemplate) getInstance().templates.get(templateName  + "|" + templateVersion + "|" +  String.valueOf(docTypeEnum.getID()));
            
            if (template == null) {
                throw new DocGenException(
                "No template exists with template name " + templateName + ", template version " + templateVersion +" and document type " + String.valueOf(docTypeEnum.getID()));
            }
        }    
        return template;
    }
    
    private synchronized static void loadTemplateConfig() throws DocGenException 
    {
        String fileName="";
        Configuration config=null;
        try {
            //load template config file into Configuration object
            fileName =getConfiguration().getChild(ROOT).getChild(OBJECT_CONFIG).getString(PATH);
            
            //comment for dev junit testing purposes, until figure out how to avoid error
            config =new DefaultConfigurationBuilder().build(
                DocTemplateConfiguration.class.getClassLoader().getResourceAsStream(fileName));
       //     config=new DefaultConfigurationBuilder().buildFromFile(fileName);
        
        } catch (ServiceLocatorException sl) {
            throw new DocGenException(sl);
        } catch (ConfigurationException ce) {
            throw new DocGenException("Could not find doc template config file \"" + fileName + "\":" + ce.getMessage());
        }
        
        try
        {
            //load template file dir
            templateDir=config.getChild(TEMPLATE_DIR).getString(PATH);
            
            //load document types
            Configuration[] cfgDocTypes = config.getChild(DOCTYPE_ROOT).getChildren(DOCTYPE);
            Configuration con = null;
            for (int i = 0; i < cfgDocTypes.length; i++) {
                con = cfgDocTypes[i];
                DocumentType docType=loadDocType(con);
                getInstance().docTypes.put(String.valueOf(docType.getID()),docType);
            }
            
            //load templates
            Configuration[] cfgTemplates = config.getChild(TEMPLATE_ROOT).getChildren(TEMPLATE);
            for (int j = 0; j < cfgTemplates.length; j++) {
                con = cfgTemplates[j];
//                System.out.println("loading line " + String.valueOf(j) + " of " + String.valueOf(cfgDocTypes.length));
                DocTemplate template=loadTemplate(con);
                
//                System.out.println("adding key to templates: " + "id|" + String.valueOf(template.getID()));
//                System.out.println("adding key to templates: " + template.getName() + "|" + template.getVersion() + "|" + String.valueOf(template.getDocumentType().getID()));
                getInstance().templates.put("id|" + String.valueOf(template.getID()), (Object) template);
                getInstance().templates.put(template.getName() + "|" + template.getVersion() + "|" + String.valueOf(template.getDocumentType().getID()), (Object) template);
                if (template.isDefault()) {
//                System.out.println("adding key to templates: " + template.getName() + "|DEFAULT|" + String.valueOf(template.getDocumentType().getID()));

                    getInstance().templates.put(template.getName() + "|DEFAULT|" + String.valueOf(template.getDocumentType().getID()), (Object) template);
                }
            }
        } catch (ConfigurationException ce) {
            throw new DocGenException(ce);
        }
    }

    
    private synchronized static DocumentType loadDocType(Configuration con) throws DocGenException 
    {
        DocumentType docType=new DocumentTypeImpl();
        int docTypeID=0;
        try {
            docTypeID=con.getInt("id");
            docType.setID(docTypeID);
            docType.setName(con.getString(DOCTYPE_NAME));
            docType.setMimeType(con.getString(MIMETYPE));
            docType.setFileExtension(con.getString(FILE_EXT));
            return docType;
        } catch (ConfigurationException ce) {
            throw new DocGenException("Unable to parse doctype node " + String.valueOf(docTypeID) + " in the Template Configuration File: " + ce.getMessage());
        }
    }
    
    private synchronized static DocTemplate loadTemplate(Configuration con) throws DocGenException 
    {
        long templateID=0;
        DocTemplate template=null;
        
        try {
            //parse template data from the <template> node
            templateID = con.getInt("id");
            String name = con.getString(TEMPLATE_NAME);
            String version = con.getString(TEMPLATE_VERSION);
            String cache=con.getString(CACHE);
            String isDefaultVersion=con.getString(DEFAULT);
            String fileName=con.getString(FILENAME);
            String docTypeID=con.getString(DOCTYPEID);
            boolean isCached=Boolean.valueOf(cache).booleanValue();
            boolean isDefault=Boolean.valueOf(isDefaultVersion).booleanValue();
            //retrieve the document type specified in this template
            DocumentType thisDocType=(DocumentType) getInstance().docTypes.get(docTypeID);
            
            //load the template with the parsed data
            JDOMSource nullSource=null;
            template=DocTemplateFactory.getInstance().getTemplate();
            //System.out.println("retrieved new template");
            //System.out.println("loading template ID " + String.valueOf(templateID) + " with name " + name + ", version " + version + ", docType" + String.valueOf(thisDocType.getID()) + ", isDefault" + String.valueOf(isDefault));
            template.loadTemplate(templateID,name,version,nullSource,fileName,thisDocType,isDefault,isCached);
            return template;
            
        } catch (ConfigurationException ce) {
            throw new DocGenException("Unable to parse template node " + String.valueOf(templateID) + " in the Doc Template Configuration File:" + ce.getMessage());
        }
    }
    
}
