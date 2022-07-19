
package com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response;
import com.radian.foundation.bo.dto.BaseDTO;
/**
 * DTO for result holds info for an onsite underwriter (a "rep")
 */
public class RepInfoDTO extends BaseDTO{
	
	private String repID;
	private String firstName;
	private String lastName;
	private String jobFunction;
	private String location;
	private static String NAME_SEP = ", ";
	private static String EMPTY_STRING = "";
	/**
	 * @return the repID
	 */
	public String getRepID(){
		return repID;
	}
	/**
	 * @param repID the repID to set
	 */
	public void setRepID(String repID){
		this.repID = repID;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName(){
		if (firstName != null){
			return firstName;			
		}
		return EMPTY_STRING;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName(){
		if (lastName != null){
			return lastName;			
		}
		return EMPTY_STRING;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	/**
	 * @return last, first as name
	 */
	public String getName(){
		if (lastName != null && lastName.length() > 0){	//have last name
			if (firstName != null && firstName.length() > 0){	//combine first and last name as "last, first"
				return lastName + NAME_SEP + firstName;
			}
			//if no first name, return only last name
			return lastName;
		}
		//if no last name, return only first name
		return getFirstName();
	}
	/**
	 * @return the jobFunction
	 */
	public String getJobFunction(){
		return jobFunction;
	}
	/**
	 * @param jobFunction the jobFunction to set
	 */
	public void setJobFunction(String jobFunction){
		this.jobFunction = jobFunction;
	}
	/**
	 * @return the location
	 */
	public String getLocation(){
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location){
		this.location = location;
	}
	@Override
	public String toString() {
		return "RepInfoDTO [repID=" + repID + ", firstName=" + firstName + ", lastName=" + lastName + ", jobFunction="
				+ jobFunction + ", location=" + location + "]";
	}
}
