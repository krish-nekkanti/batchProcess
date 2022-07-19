/*
 * DocumentType.java
 *
 * Created on June 20, 2003, 3:14 PM
 */
package com.radian.foundation.os.content.composition;

/** Object containing document metadata
 * @author Drea Leed
 */
public interface DocumentType {
    
    /** retrieve the ID of this document type
     * @return Document Type ID
     */    
    public long getID();
    
    /** Set the ID of this document type
     * @param tmp Document Type ID to set
     */    
    public void setID(long tmp);
    
    /** Retrieve the name of this document type
     * @return Document Type Name
     */    
    public String getName();
    
    /** Set the name of this document type
     * @param tmp Document Type Name
     */    
    public void setName(String tmp);
    
    /** Retrieve the mime type corresponding to this document type
     * @return Document mime-type
     */    
    public String getMimeType();
    
    /** Set the mime type corresponding to this document type
     * @param tmp mime type
     */    
    public void setMimeType(String tmp);
    
    /** Retrieve the filename extension corresponding to this document type
     * @return filename extension
     */    
    public String getFileExtension();
    
    /** Set the filename extension corresponding to this document type
     * @param tmp filename extension
     */    
    public void setFileExtension(String tmp);
    
    /** String representation of the documentType object
     * @return String representation of the document type object
     */    
    public String toString() ;
}
