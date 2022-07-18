
package com.radian.foundation.os.content.composition;

import com.radian.foundation.os.content.composition.DocumentGenerator;
import com.radian.foundation.os.content.composition.impl.DocumentGeneratorImpl;
import com.radian.foundation.bs.BaseFactory;

/**
 * Simple Document Generator factory
 *
 * @author Drea Leed
 */
public final class DocumentGeneratorFactory extends BaseFactory
{
  private static DocumentGenerator docGenerator; 
  private static DocumentGeneratorFactory docGeneratorFactory;

    /**
     * Constructs DocumentGenerator factory.
     */
    private DocumentGeneratorFactory()
    {      
        super();
    }

    public static DocumentGeneratorFactory getInstance()
    {
     if (docGeneratorFactory==null) {
      docGeneratorFactory=new DocumentGeneratorFactory();   
     }
     return docGeneratorFactory;
     
    }
    /**
     * Gets Document Generator.
     *
     * @return Document Generator
     */
    public DocumentGenerator getDocumentGenerator()
    {
        if (docGenerator ==null)
        {
            docGenerator=DocumentGeneratorImpl.getInstance();
        }
        return docGenerator;
    }    
}
