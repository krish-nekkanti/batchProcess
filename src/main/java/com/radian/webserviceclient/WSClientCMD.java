package com.radian.webserviceclient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.rmi.RemoteException;
import java.util.Collection;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import MDMServices.Radian.GetBillingProfile;
import MDMServices.Radian.GetBillingProfileCoveredBranches;
import MDMServices.Radian.GetBillingProfileCoveredBranchesResponse;
import MDMServices.Radian.GetBillingProfileOnsites;
import MDMServices.Radian.GetBillingProfileOnsitesResponse;
import MDMServices.Radian.GetBillingProfileResponse;
import MDMServices.Radian.GetBillingProfileThirdParties;
import MDMServices.Radian.GetBillingProfileThirdPartiesResponse;
import MDMServices.Radian.GetBillingProfiles;
import MDMServices.Radian.GetBillingProfilesLite;
import MDMServices.Radian.GetBillingProfilesLiteResponse;
import MDMServices.Radian.GetBillingProfilesResponse;
import MDMServices.Radian.GetCodes;
import MDMServices.Radian.GetCodesResponse;
import MDMServices.Radian.GetCostCenter;
import MDMServices.Radian.GetCostCenterResponse;
import MDMServices.Radian.GetCustomer;
import MDMServices.Radian.GetCustomerLocation;
import MDMServices.Radian.GetCustomerLocationResponse;
import MDMServices.Radian.GetCustomerResponse;
import MDMServices.Radian.GetOnsitePricing;
import MDMServices.Radian.GetOnsitePricingResponse;
import MDMServices.Radian.GetServiceCenterPricing;
import MDMServices.Radian.GetServiceCenterPricingResponse;
import MDMServices.Radian.GetServiceTypes;
import MDMServices.Radian.GetServiceTypesResponse;
import MDMServices.Radian.GetThirdPartySubmitterBilledInvestors;
import MDMServices.Radian.GetThirdPartySubmitterBilledInvestorsResponse;

import com.radian.cuwbilling.billing.cuw.bo.dto.pricing.request.BillingProfileRequestDTO;
import com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response.BillingProfileDTO;
import com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response.CustLocationDTO;
import com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response.CustomerPricingStructuresDTO;
import com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response.UnderwriterPricingStructuresDTO;
import com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response.CustomerDTO;
import com.radian.foundation.common.logging.LogManager;
import com.radian.foundation.common.logging.Logger;
import com.radian.ws.client.CMDServiceSoap;

public class WSClientCMD 
{
	private CMDServiceSoap webservice;
    private static Logger logger = LogManager.getLogger(WSClientCMD.class);
	private static String WS_INVALID_REQUEST = "WSClientCMD: requestDTO missing required params";
	private static String WS_NORESPONSE = "WSClientCMD: null or unusable response from CMD";
	private static String EMPTY_STRING = "";
	private static String WS_CLEANXML_AMPSYM = "\\&";
    private static String WS_CLEANXML_AMPSYM_REPL = " ";
	
	/**
	 * @param webservice
	 */
	protected void setWebservice(CMDServiceSoap webservice)
	{
		this.webservice = webservice;
	}
	
	/**
	 * @return the webservice
	 */
	private CMDServiceSoap getWebservice()
	{
		return webservice;
	}
	
	/**
	 * Calls pricing web service
	 * @param dtoRequest contains time and loan data for pricing one onsite underwriter (rep)
	 * @return reponse DTO filled with results from call to pricing web service
	 */
	public CustomerDTO getCustomer(String custNum) throws WSClientException, WSCMDExceptionStatus
	{
		if (custNum == null)
		{
			throw new WSClientException(WS_INVALID_REQUEST);
		}
		
		GetCustomer cust = new GetCustomer();
		cust.setCustomerNumber(custNum);
		GetCustomerResponse resp = null;
			
		try
		{	//call web service
			resp = getWebservice().getCustomer(cust);
			//System.out.print(resp.getGetCustomerResult());
		}
		catch (RemoteException e)
		{	//unable to call web service
			throw new WSClientException(e);
		}	
        
		//parse XML response
		WSCMDSaxHandlerCustInfo custInfoHandler = new WSCMDSaxHandlerCustInfo();
		parseResponse(resp.getGetCustomerResult(), custInfoHandler);
		
		return custInfoHandler.getCustInfo();
	}
	
	public CustLocationDTO getCustomerLocation(String custNum, String locNum) throws WSClientException, WSCMDExceptionStatus
	{
		if (custNum == null)
		{
			throw new WSClientException(WS_INVALID_REQUEST);
		}
		
		GetCustomerLocation loc = new GetCustomerLocation();
		loc.setCustomerNumber(custNum);
		loc.setLocationNumber(locNum);
		
		GetCustomerLocationResponse resp = null;
		try
		{	//call web service
			resp = getWebservice().getCustomerLocation(loc);
			System.out.print(resp.getGetCustomerLocationResult());
		}
		catch (RemoteException e)
		{	//unable to call web service
			throw new WSClientException(e);
		}	
        
		//parse XML response
		WSCMDSaxHandlerCustLocationInfo custLocInfoHandler = new WSCMDSaxHandlerCustLocationInfo();
		parseResponse(resp.getGetCustomerLocationResult(), custLocInfoHandler);
		
		return custLocInfoHandler.getCustLocInfo();
	}
	
	public CustomerPricingStructuresDTO getServiceCenterPricing(String custNum) throws WSClientException, WSCMDExceptionStatus
	{
		GetServiceCenterPricing req = new GetServiceCenterPricing();
		req.setCustomerNumber(custNum);
		req.setCustomerID("");
		
		if (custNum == null)
		{
			throw new WSClientException(WS_INVALID_REQUEST);
		}
		
		GetServiceCenterPricingResponse resp = null;
		try
		{	//call web service
			resp = getWebservice().getServiceCenterPricing(req);
			System.out.print(resp.getGetServiceCenterPricingResult());
		}
		catch (RemoteException e)
		{	//unable to call web service
			throw new WSClientException(e);
		}			
		//parse XML response
		WSCMDSaxHandlerSCPricing pricingInfoHandler = new WSCMDSaxHandlerSCPricing();
		parseResponse(resp.getGetServiceCenterPricingResult(), pricingInfoHandler);
		
		return pricingInfoHandler.getPrcingInfo();
	}
	
	public Collection getBillingProfileOnsites(String profileNum) throws WSClientException, WSCMDExceptionStatus
	{
		if (profileNum == null)
		{
			throw new WSClientException(WS_INVALID_REQUEST);
		}
		
		GetBillingProfileOnsites prof = new GetBillingProfileOnsites();
		prof.setBillingProfileNumber(profileNum);
		GetBillingProfileOnsitesResponse resp = null;
		try
		{	//call web service
			resp = getWebservice().getBillingProfileOnsites(prof);
		}
		catch (RemoteException e)
		{	//unable to call web service
			throw new WSClientException(e);
		}		
		//parse XML response
		WSCMDSaxHandlerBillingProfileOnsites profileOnsitesHandler = new WSCMDSaxHandlerBillingProfileOnsites();
		parseResponse(resp.getGetBillingProfileOnsitesResult(), profileOnsitesHandler);
		System.out.print(resp.getGetBillingProfileOnsitesResult());
		
		return profileOnsitesHandler.getBillingProfileOnsites();
	}
	
	public UnderwriterPricingStructuresDTO getOnsitePricing(String repID) throws WSClientException, WSCMDExceptionStatus
	{
		if (repID == null)
		{
			throw new WSClientException(WS_INVALID_REQUEST);
		}
		
		GetOnsitePricing rep = new GetOnsitePricing ();
		rep.setRepresentativeNum(repID);
		GetOnsitePricingResponse resp = null;
		try
		{	//call web service
			resp = getWebservice().getOnsitePricing(rep);
		}
		catch (RemoteException e)
		{	//unable to call web service
			throw new WSClientException(e);
		}			
		//parse XML response
		WSCMDSaxHandlerOnsitePricing onsitePricingHandler = new WSCMDSaxHandlerOnsitePricing();
		parseResponse(resp.getGetOnsitePricingResult(), onsitePricingHandler);
		
		System.out.print(resp.getGetOnsitePricingResult());
        
		return onsitePricingHandler.getPrcingInfo();
	}
	
	public String getCostCenter(String repID) throws WSClientException, WSCMDExceptionStatus
	{
		if (repID == null)
		{
			throw new WSClientException(WS_INVALID_REQUEST);
		}
		
		GetCostCenter cc = new GetCostCenter();
		cc.setRepresentativeNum(repID);
		GetCostCenterResponse resp = null;
		try
		{	//call web service
			resp = getWebservice().getCostCenter(cc);
		}
		catch (RemoteException e)
		{	//unable to call web service
			throw new WSClientException(e);
		}			
		//parse XML response
		WSCMDSAXHandlerCostCenter costCenterHandler = new WSCMDSAXHandlerCostCenter();
		parseResponse(resp.getGetCostCenterResult(), costCenterHandler);
		
		System.out.print(resp.getGetCostCenterResult());
        
		return costCenterHandler.getCostCenterNum();
	}
	
	public Collection getBillingProfileCoveredBranches(String profileNum) throws WSClientException, WSCMDExceptionStatus
	{
		if (profileNum == null)
		{
			throw new WSClientException(WS_INVALID_REQUEST);
		}
		
		GetBillingProfileCoveredBranches rep = new GetBillingProfileCoveredBranches();
		rep.setBillingProfileNumber(profileNum);
		GetBillingProfileCoveredBranchesResponse resp = null;
		try
		{	//call web service
			resp = getWebservice().getBillingProfileCoveredBranches(rep);
		}
		catch (RemoteException e)
		{	//unable to call web service
			throw new WSClientException(e);
		}			
		//parse XML response
		WSCMDSaxHandlerBillingProfileCoveredBranches coveredBranchsHandler = new WSCMDSaxHandlerBillingProfileCoveredBranches();
		parseResponse(resp.getGetBillingProfileCoveredBranchesResult(), coveredBranchsHandler);
		
		System.out.print(resp.getGetBillingProfileCoveredBranchesResult());
        
		return coveredBranchsHandler.getBillingProfileCoveredBranches();
	}
	
	public Collection getBillingProfileThirdParties(String profileNum) throws WSClientException, WSCMDExceptionStatus
	{
		if (profileNum == null)
		{
			throw new WSClientException(WS_INVALID_REQUEST);
		}
		
		GetBillingProfileThirdParties rep = new GetBillingProfileThirdParties();
		rep.setBillingProfileNumber(profileNum);
		GetBillingProfileThirdPartiesResponse resp = null;
		try
		{	//call web service
			resp = getWebservice().getBillingProfileThirdParties(rep);
		}
		catch (RemoteException e)
		{	//unable to call web service
			throw new WSClientException(e);
		}			
		//parse XML response
		WSCMDSaxHandlerBillingProfileThirdParties thirdPartiesHandler = new WSCMDSaxHandlerBillingProfileThirdParties();
		parseResponse(resp.getGetBillingProfileThirdPartiesResult(), thirdPartiesHandler);
		
		System.out.print(resp.getGetBillingProfileThirdPartiesResult());
        
		return thirdPartiesHandler.getBillingProfileThirdParties();
	}
		
	public Collection getBillingProfilesLite(BillingProfileRequestDTO requestDTO)  throws WSClientException, WSCMDExceptionStatus
	{
		if (requestDTO == null)
		{
			throw new WSClientException(WS_INVALID_REQUEST);
		}
		
		GetBillingProfilesLite prof = new GetBillingProfilesLite();
		prof.setBillingProfileNumber(requestDTO.getBillingProfileDisplayID());  
		prof.setBillingProfileStatus(requestDTO.getActivationStatus());
		prof.setBillToName(requestDTO.getBillToName());
		prof.setCustomerNumber(requestDTO.getCustomerDisplayID());
		prof.setCustomerName(requestDTO.getCustomerName());
		prof.setLagDays(requestDTO.getLagDays());
		
		GetBillingProfilesLiteResponse resp = null;
		try
		{	//call web service
			resp = getWebservice().getBillingProfilesLite(prof); 
		}
		catch (RemoteException e)
		{	//unable to call web service
			throw new WSClientException(e);
		}		
		//parse XML response
		WSCMDSaxHandlerBillingProfileLite profileHandler = new WSCMDSaxHandlerBillingProfileLite();
		parseResponse(resp.getGetBillingProfilesLiteResult(), profileHandler);
		System.out.print(resp.getGetBillingProfilesLiteResult());
		
		return profileHandler.getProfileSummaryDTOs();
	}
	
	public BillingProfileDTO getBillingProfile(String profileNum) throws WSClientException, WSCMDExceptionStatus
	{
		if (profileNum == null)
		{
			throw new WSClientException(WS_INVALID_REQUEST);
		}
		
		GetBillingProfile prof = new GetBillingProfile();
		prof.setBillingProfileNumber(profileNum);
		GetBillingProfileResponse resp = null;
		try
		{	//call web service
			resp = getWebservice().getBillingProfile(prof);
		}
		catch (RemoteException e)
		{	//unable to call web service
			throw new WSClientException(e);
		}		
		//parse XML response
		WSCMDSaxHandlerBillingProfile profileHandler = new WSCMDSaxHandlerBillingProfile();
		parseResponse(resp.getGetBillingProfileResult(), profileHandler);
		System.out.print(resp.getGetBillingProfileResult());
		
		return profileHandler.getBillingProfile();
	}

	public Collection getBillingProfiles(BillingProfileRequestDTO requestDTO) throws WSClientException, WSCMDExceptionStatus
	{
		if (requestDTO == null)
		{
			throw new WSClientException(WS_INVALID_REQUEST);
		}
		
		GetBillingProfiles prof = new GetBillingProfiles();
		prof.setBillingProfileNumber(requestDTO.getBillingProfileDisplayID());  
		prof.setBillingProfileStatus(requestDTO.getActivationStatus());
		prof.setBillToName(requestDTO.getBillToName());
		prof.setCustomerNumber(requestDTO.getCustomerDisplayID());
		prof.setCustomerName(requestDTO.getCustomerName());
		prof.setLagDays(requestDTO.getLagDays());
		
		GetBillingProfilesResponse resp = null;
		try
		{	//call web service
			resp = getWebservice().getBillingProfiles(prof); 
		}
		catch (RemoteException e)
		{	//unable to call web service
			throw new WSClientException(e);
		}		
		//parse XML response
		WSCMDSaxHandlerBillingProfile profileHandler = new WSCMDSaxHandlerBillingProfile();
		parseResponse(resp.getGetBillingProfilesResult(), profileHandler);
		System.out.print(resp.getGetBillingProfilesResult());
		
		return profileHandler.getBillingProfiles();
	}

	/**
	 * 
	 * @param tableName
	 * @return
	 * @throws WSClientException
	 * @throws WSClientExceptionStatus
	 */
	public Collection getCodes(String tableName) throws WSClientException, WSClientExceptionStatus
	{
		if (tableName == null)
		{
			throw new WSClientException(WS_INVALID_REQUEST);
		}
		
		GetCodes code = new GetCodes();
		code.setTableName(tableName);
		
		GetCodesResponse resp = null;
		try
		{	//call web service
			resp = getWebservice().getCodes(code);
		}
		catch (RemoteException e)
		{	//unable to call web service
			throw new WSClientException(e);
		}		
		//parse XML response
		WSCMDSaxHandlerCode codeHandler = new WSCMDSaxHandlerCode();
		parseResponse(resp.getGetCodesResult(), codeHandler);
		System.out.print(resp.getGetCodesResult());
		
		return codeHandler.getCodes();
	}

	/**
	 * 
	 * @return
	 * @throws WSClientException
	 * @throws WSClientExceptionStatus
	 */
	public Collection getServiceTypes() throws WSClientException, WSCMDExceptionStatus
	{
		GetServiceTypes serviceTypes = new GetServiceTypes();
		
		GetServiceTypesResponse resp = null;
		try
		{	//call web service
			resp = getWebservice().getServiceTypes(serviceTypes);
		}
		catch (RemoteException e)
		{	//unable to call web service
			throw new WSClientException(e);
		}		
		//parse XML response
		WSCMDSaxHandlerServiceType serviceTypeHandler = new WSCMDSaxHandlerServiceType();
		parseResponse(resp.getGetServiceTypesResult(), serviceTypeHandler);
		System.out.print(resp.getGetServiceTypesResult());
		
		return serviceTypeHandler.getServiceTypes();
	}
	
	/**
	 * Return investor List per billing profile that contains all the customers where current customer is a 3rd party submitter.
	 * @param profileNum
	 * @return
	 * @throws WSClientException
	 * @throws WSCMDExceptionStatus
	 */
	public Collection getInvestorListPerBP(String profileNum) throws WSClientException, WSCMDExceptionStatus
	{
		if (profileNum == null)
		{
			throw new WSClientException(WS_INVALID_REQUEST);
		}
		
		GetThirdPartySubmitterBilledInvestors rep = new GetThirdPartySubmitterBilledInvestors();
		rep.setBillingProfileNumber(profileNum);
		GetThirdPartySubmitterBilledInvestorsResponse resp = null;
		try
		{	//call web service
			resp = getWebservice().getThirdPartySubmitterBilledInvestors(rep);
		}
		catch (RemoteException e)
		{	//unable to call web service
			throw new WSClientException(e);
		}			
		//parse XML response
		WSCMDSaxHandlerThirdPartySubmitterBilledInvestors handler = new WSCMDSaxHandlerThirdPartySubmitterBilledInvestors();
		parseResponse(resp.getGetThirdPartySubmitterBilledInvestorsResult(), handler);
		
		System.out.print(resp.getGetThirdPartySubmitterBilledInvestorsResult());
        
		return handler.getInvestors();
	}
	
	private void parseResponse(String response, WSCMDClientSaxHandler handler) throws WSClientException, WSCMDExceptionStatus
	{
		try
		{
			//get JAXP parser factory, config for no validation, get parser, parse using appropriate handler
			SAXParserFactory spf = SAXParserFactory.newInstance();
			spf.setValidating(false);
			SAXParser sp = spf.newSAXParser();
			InputSource input = new InputSource(new StringReader(response));
			sp.parse(input, handler);
			WSCMDStatus status = handler.getStatus();
			if (status != null && handler.getStatus().getErrorcode().length() > 0)
			{	//have error condition
				throw new WSCMDExceptionStatus(status);
			}
			//else successfully parsed response and response is not an error response
		}
		catch (FactoryConfigurationError e)
		{
			throw new WSClientException(e);
		}
		catch (ParserConfigurationException e)
		{
			throw new WSClientException(e);
		}
		catch (SAXException e)
		{
			throw new WSClientException(e);
		}
		catch (IOException e)
		{
			throw new WSClientException(e);
		}
		catch (RuntimeException e)
		{
			throw new WSClientException(e);
		}
	}
	
	private void parseResponse(String response, WSMionlineClientSaxHandler handler) throws WSClientException, WSClientExceptionStatus
	{
		try
		{
			//get JAXP parser factory, config for no validation, get parser, parse using appropriate handler
			SAXParserFactory spf = SAXParserFactory.newInstance();
			spf.setValidating(false);
			SAXParser sp = spf.newSAXParser();
			InputSource input = new InputSource(new StringReader(cleanResponse(response)));
			sp.parse(input, handler);
			WSMionlineStatus status = handler.getStatus();
			if (status != null && handler.getStatus().getErrorcode().length() > 0)
			{	//have error condition
				throw new WSClientExceptionStatus(status);
			}
			//else successfully parsed response and response is not an error response
		}
		catch (FactoryConfigurationError e)
		{
			throw new WSClientException(e);
		}
		catch (ParserConfigurationException e)
		{
			throw new WSClientException(e);
		}
		catch (SAXException e)
		{
			throw new WSClientException(e);
		}
		catch (IOException e)
		{
			throw new WSClientException(e);
		}
		catch (RuntimeException e)
		{
			throw new WSClientException(e);
		}
	}
	
	private String cleanResponse(String response)
	{
		try
		{
			return response.replaceAll(WS_CLEANXML_AMPSYM, WS_CLEANXML_AMPSYM_REPL);
		}
		catch (Exception e)
		{	//possible to have PatternSyntaxException (though highly unlikely)--return orig response instead
			return response;
		}
	}
}
