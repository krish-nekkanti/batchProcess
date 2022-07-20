package com.radian.webserviceclient;
import java.io.IOException;
import java.io.StringReader;
import java.rmi.RemoteException;
import java.util.Collection;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.radian.cuwbilling.billing.cuw.bo.dto.pricing.request.BillingProfileRequestDTO;
import com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response.BillingProfileDTO;
import com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response.CustLocationDTO;
import com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response.CustomerDTO;
import com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response.CustomerPricingStructuresDTO;
import com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response.UnderwriterPricingStructuresDTO;
import com.radian.webserviceclient.cmd.endPoints.CMDWSConnector;
import com.radian.webserviceclient.cmd.model.GetBillingProfile;
import com.radian.webserviceclient.cmd.model.GetBillingProfileCoveredBranches;
import com.radian.webserviceclient.cmd.model.GetBillingProfileCoveredBranchesResponse;
import com.radian.webserviceclient.cmd.model.GetBillingProfileOnsites;
import com.radian.webserviceclient.cmd.model.GetBillingProfileOnsitesResponse;
import com.radian.webserviceclient.cmd.model.GetBillingProfileResponse;
import com.radian.webserviceclient.cmd.model.GetBillingProfileThirdParties;
import com.radian.webserviceclient.cmd.model.GetBillingProfileThirdPartiesResponse;
import com.radian.webserviceclient.cmd.model.GetBillingProfiles;
import com.radian.webserviceclient.cmd.model.GetBillingProfilesLite;
import com.radian.webserviceclient.cmd.model.GetBillingProfilesLiteResponse;
import com.radian.webserviceclient.cmd.model.GetBillingProfilesResponse;
import com.radian.webserviceclient.cmd.model.GetCodes;
import com.radian.webserviceclient.cmd.model.GetCodesResponse;
import com.radian.webserviceclient.cmd.model.GetCostCenter;
import com.radian.webserviceclient.cmd.model.GetCostCenterResponse;
import com.radian.webserviceclient.cmd.model.GetCustomer;
import com.radian.webserviceclient.cmd.model.GetCustomerLocation;
import com.radian.webserviceclient.cmd.model.GetCustomerLocationResponse;
import com.radian.webserviceclient.cmd.model.GetCustomerResponse;
import com.radian.webserviceclient.cmd.model.GetOnsitePricing;
import com.radian.webserviceclient.cmd.model.GetOnsitePricingResponse;
import com.radian.webserviceclient.cmd.model.GetServiceCenterPricing;
import com.radian.webserviceclient.cmd.model.GetServiceCenterPricingResponse;
import com.radian.webserviceclient.cmd.model.GetServiceTypes;
import com.radian.webserviceclient.cmd.model.GetServiceTypesResponse;
import com.radian.webserviceclient.cmd.model.GetThirdPartySubmitterBilledInvestors;
import com.radian.webserviceclient.cmd.model.GetThirdPartySubmitterBilledInvestorsResponse;
import com.radian.webserviceclient.miOnline.endPoints.WMCUWGetRepInfoResponse;

public class WSClientCMDNew {
	
    private static final String WEBSERVICE_URL = null;
	Logger logger = LoggerFactory.getLogger(WSClientMionlineNew.class);
	private static String WS_INVALID_REQUEST = "WSClientCMD: requestDTO missing required params";
	private static String WS_NORESPONSE = "WSClientCMD: null or unusable response from CMD";
	private static String EMPTY_STRING = "";
	private static String WS_CLEANXML_AMPSYM = "\\&";
	private static String WS_CLEANXML_AMPSYM_REPL = " ";
    private CMDWSConnector wsConnector;

	/**
	 * Calls pricing web service
	 * @param dtoRequest contains time and loan data for pricing one onsite underwriter (rep)
	 * @return reponse DTO filled with results from call to pricing web service
	 */
	public CustomerDTO getCustomer(String custNum) throws WSClientException, WSCMDExceptionStatus{
		if (custNum == null){
			throw new WSClientException(WS_INVALID_REQUEST);
		}
		GetCustomer cust = new GetCustomer();
		cust.setCustomerNumber(custNum);
		GetCustomerResponse resp = null;
		try{	
			 resp  = (GetCustomerResponse) wsConnector.callWebService(WEBSERVICE_URL, cust);
		}
		catch (RemoteException e){	
			throw new WSClientException(e);
		}	
		
		WSCMDSaxHandlerCustInfo custInfoHandler = new WSCMDSaxHandlerCustInfo();
		parseResponse(resp.getGetCustomerResult(), custInfoHandler);
		return custInfoHandler.getCustInfo();
	}
	public CustLocationDTO getCustomerLocation(String custNum, String locNum) throws WSClientException, WSCMDExceptionStatus{
		if (custNum == null){
			throw new WSClientException(WS_INVALID_REQUEST);
		}
		GetCustomerLocation loc = new GetCustomerLocation();
		loc.setCustomerNumber(custNum);
		loc.setLocationNumber(locNum);
		GetCustomerLocationResponse resp = null;
		try{	
			resp = getWebservice().getCustomerLocation(loc);
			System.out.print(resp.getGetCustomerLocationResult());
		}
		catch (RemoteException e){	
			throw new WSClientException(e);
		}	
		
		WSCMDSaxHandlerCustLocationInfo custLocInfoHandler = new WSCMDSaxHandlerCustLocationInfo();
		parseResponse(resp.getGetCustomerLocationResult(), custLocInfoHandler);
		return custLocInfoHandler.getCustLocInfo();
	}
	public CustomerPricingStructuresDTO getServiceCenterPricing(String custNum) throws WSClientException, WSCMDExceptionStatus{
		GetServiceCenterPricing req = new GetServiceCenterPricing();
		req.setCustomerNumber(custNum);
		req.setCustomerID("");
		if (custNum == null){
			throw new WSClientException(WS_INVALID_REQUEST);
		}
		GetServiceCenterPricingResponse resp = null;
		try{	
			resp = getWebservice().getServiceCenterPricing(req);
			System.out.print(resp.getGetServiceCenterPricingResult());
		}
		catch (RemoteException e){	
			throw new WSClientException(e);
		}			
		
		WSCMDSaxHandlerSCPricing pricingInfoHandler = new WSCMDSaxHandlerSCPricing();
		parseResponse(resp.getGetServiceCenterPricingResult(), pricingInfoHandler);
		return pricingInfoHandler.getPrcingInfo();
	}
	public Collection getBillingProfileOnsites(String profileNum) throws WSClientException, WSCMDExceptionStatus{
		if (profileNum == null){
			throw new WSClientException(WS_INVALID_REQUEST);
		}
		GetBillingProfileOnsites prof = new GetBillingProfileOnsites();
		prof.setBillingProfileNumber(profileNum);
		GetBillingProfileOnsitesResponse resp = null;
		try{	
			resp = getWebservice().getBillingProfileOnsites(prof);
		}
		catch (RemoteException e){	
			throw new WSClientException(e);
		}		
		
		WSCMDSaxHandlerBillingProfileOnsites profileOnsitesHandler = new WSCMDSaxHandlerBillingProfileOnsites();
		parseResponse(resp.getGetBillingProfileOnsitesResult(), profileOnsitesHandler);
		System.out.print(resp.getGetBillingProfileOnsitesResult());
		return profileOnsitesHandler.getBillingProfileOnsites();
	}
	public UnderwriterPricingStructuresDTO getOnsitePricing(String repID) throws WSClientException, WSCMDExceptionStatus{
		if (repID == null){
			throw new WSClientException(WS_INVALID_REQUEST);
		}
		GetOnsitePricing rep = new GetOnsitePricing ();
		rep.setRepresentativeNum(repID);
		GetOnsitePricingResponse resp = null;
		try{	
			resp = getWebservice().getOnsitePricing(rep);
		}
		catch (RemoteException e){	
			throw new WSClientException(e);
		}			
		
		WSCMDSaxHandlerOnsitePricing onsitePricingHandler = new WSCMDSaxHandlerOnsitePricing();
		parseResponse(resp.getGetOnsitePricingResult(), onsitePricingHandler);
		System.out.print(resp.getGetOnsitePricingResult());
		return onsitePricingHandler.getPrcingInfo();
	}
	public String getCostCenter(String repID) throws WSClientException, WSCMDExceptionStatus{
		if (repID == null){
			throw new WSClientException(WS_INVALID_REQUEST);
		}
		GetCostCenter cc = new GetCostCenter();
		cc.setRepresentativeNum(repID);
		GetCostCenterResponse resp = null;
		try{	
			resp = getWebservice().getCostCenter(cc);
		}
		catch (RemoteException e){	
			throw new WSClientException(e);
		}			
		
		WSCMDSAXHandlerCostCenter costCenterHandler = new WSCMDSAXHandlerCostCenter();
		parseResponse(resp.getGetCostCenterResult(), costCenterHandler);
		System.out.print(resp.getGetCostCenterResult());
		return costCenterHandler.getCostCenterNum();
	}
	public Collection getBillingProfileCoveredBranches(String profileNum) throws WSClientException, WSCMDExceptionStatus{
		if (profileNum == null){
			throw new WSClientException(WS_INVALID_REQUEST);
		}
		GetBillingProfileCoveredBranches rep = new GetBillingProfileCoveredBranches();
		rep.setBillingProfileNumber(profileNum);
		GetBillingProfileCoveredBranchesResponse resp = null;
		try{	
			resp = getWebservice().getBillingProfileCoveredBranches(rep);
		}
		catch (RemoteException e){	
			throw new WSClientException(e);
		}			
		
		WSCMDSaxHandlerBillingProfileCoveredBranches coveredBranchsHandler = new WSCMDSaxHandlerBillingProfileCoveredBranches();
		parseResponse(resp.getGetBillingProfileCoveredBranchesResult(), coveredBranchsHandler);
		System.out.print(resp.getGetBillingProfileCoveredBranchesResult());
		return coveredBranchsHandler.getBillingProfileCoveredBranches();
	}
	public Collection getBillingProfileThirdParties(String profileNum) throws WSClientException, WSCMDExceptionStatus{
		if (profileNum == null){
			throw new WSClientException(WS_INVALID_REQUEST);
		}
		GetBillingProfileThirdParties rep = new GetBillingProfileThirdParties();
		rep.setBillingProfileNumber(profileNum);
		GetBillingProfileThirdPartiesResponse resp = null;
		try{	
			resp = getWebservice().getBillingProfileThirdParties(rep);
		}
		catch (RemoteException e){	
			throw new WSClientException(e);
		}			
		
		WSCMDSaxHandlerBillingProfileThirdParties thirdPartiesHandler = new WSCMDSaxHandlerBillingProfileThirdParties();
		parseResponse(resp.getGetBillingProfileThirdPartiesResult(), thirdPartiesHandler);
		System.out.print(resp.getGetBillingProfileThirdPartiesResult());
		return thirdPartiesHandler.getBillingProfileThirdParties();
	}
	public Collection getBillingProfilesLite(BillingProfileRequestDTO requestDTO)  throws WSClientException, WSCMDExceptionStatus{
		if (requestDTO == null){
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
		try{	
			resp = getWebservice().getBillingProfilesLite(prof); 
		}
		catch (RemoteException e){	
			throw new WSClientException(e);
		}		
		
		WSCMDSaxHandlerBillingProfileLite profileHandler = new WSCMDSaxHandlerBillingProfileLite();
		parseResponse(resp.getGetBillingProfilesLiteResult(), profileHandler);
		System.out.print(resp.getGetBillingProfilesLiteResult());
		return profileHandler.getProfileSummaryDTOs();
	}
	public BillingProfileDTO getBillingProfile(String profileNum) throws WSClientException, WSCMDExceptionStatus{
		if (profileNum == null){
			throw new WSClientException(WS_INVALID_REQUEST);
		}
		GetBillingProfile prof = new GetBillingProfile();
		prof.setBillingProfileNumber(profileNum);
		GetBillingProfileResponse resp = null;
		try{	
			resp = getWebservice().getBillingProfile(prof);
		}
		catch (RemoteException e){	
			throw new WSClientException(e);
		}		
		
		WSCMDSaxHandlerBillingProfile profileHandler = new WSCMDSaxHandlerBillingProfile();
		parseResponse(resp.getGetBillingProfileResult(), profileHandler);
		System.out.print(resp.getGetBillingProfileResult());
		return profileHandler.getBillingProfile();
	}
	public Collection getBillingProfiles(BillingProfileRequestDTO requestDTO) throws WSClientException, WSCMDExceptionStatus{
		if (requestDTO == null){
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
		try{	
			resp = getWebservice().getBillingProfiles(prof); 
		}
		catch (RemoteException e){	
			throw new WSClientException(e);
		}		
		
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
	public Collection getCodes(String tableName) throws WSClientException, WSClientExceptionStatus{
		if (tableName == null){
			throw new WSClientException(WS_INVALID_REQUEST);
		}
		GetCodes code = new GetCodes();
		code.setTableName(tableName);
		GetCodesResponse resp = null;
		try{	
			resp = getWebservice().getCodes(code);
		}
		catch (RemoteException e){	
			throw new WSClientException(e);
		}		
		
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
	public Collection getServiceTypes() throws WSClientException, WSCMDExceptionStatus{
		GetServiceTypes serviceTypes = new GetServiceTypes();
		GetServiceTypesResponse resp = null;
		try{	
			resp = getWebservice().getServiceTypes(serviceTypes);
		}
		catch (RemoteException e){	
			throw new WSClientException(e);
		}		
		
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
	public Collection getInvestorListPerBP(String profileNum) throws WSClientException, WSCMDExceptionStatus{
		if (profileNum == null){
			throw new WSClientException(WS_INVALID_REQUEST);
		}
		GetThirdPartySubmitterBilledInvestors rep = new GetThirdPartySubmitterBilledInvestors();
		rep.setBillingProfileNumber(profileNum);
		GetThirdPartySubmitterBilledInvestorsResponse resp = null;
		try{	
			resp = getWebservice().getThirdPartySubmitterBilledInvestors(rep);
		}
		catch (RemoteException e){	
			throw new WSClientException(e);
		}			
		
		WSCMDSaxHandlerThirdPartySubmitterBilledInvestors handler = new WSCMDSaxHandlerThirdPartySubmitterBilledInvestors();
		parseResponse(resp.getGetThirdPartySubmitterBilledInvestorsResult(), handler);
		System.out.print(resp.getGetThirdPartySubmitterBilledInvestorsResult());
		return handler.getInvestors();
	}
	private void parseResponse(String response, WSCMDClientSaxHandler handler) throws WSClientException, WSCMDExceptionStatus{
		try{
			//get JAXP parser factory, config for no validation, get parser, parse using appropriate handler
			SAXParserFactory spf = SAXParserFactory.newInstance();
			spf.setValidating(false);
			SAXParser sp = spf.newSAXParser();
			InputSource input = new InputSource(new StringReader(response));
			sp.parse(input, handler);
			WSCMDStatus status = handler.getStatus();
			if (status != null && handler.getStatus().getErrorcode().length() > 0){	//have error condition
				throw new WSCMDExceptionStatus(status);
			}
			//else successfully parsed response and response is not an error response
		}
		catch (FactoryConfigurationError e){
			throw new WSClientException(e);
		}
		catch (ParserConfigurationException e){
			throw new WSClientException(e);
		}
		catch (SAXException e){
			throw new WSClientException(e);
		}
		catch (IOException e){
			throw new WSClientException(e);
		}
		catch (RuntimeException e){
			throw new WSClientException(e);
		}
	}
	private void parseResponse(String response, WSMionlineClientSaxHandler handler) throws WSClientException, WSClientExceptionStatus{
		try{
			//get JAXP parser factory, config for no validation, get parser, parse using appropriate handler
			SAXParserFactory spf = SAXParserFactory.newInstance();
			spf.setValidating(false);
			SAXParser sp = spf.newSAXParser();
			InputSource input = new InputSource(new StringReader(cleanResponse(response)));
			sp.parse(input, handler);
			WSMionlineStatus status = handler.getStatus();
			if (status != null && handler.getStatus().getErrorcode().length() > 0){	//have error condition
				throw new WSClientExceptionStatus(status);
			}
			//else successfully parsed response and response is not an error response
		}
		catch (FactoryConfigurationError e){
			throw new WSClientException(e);
		}
		catch (ParserConfigurationException e){
			throw new WSClientException(e);
		}
		catch (SAXException e){
			throw new WSClientException(e);
		}
		catch (IOException e){
			throw new WSClientException(e);
		}
		catch (RuntimeException e){
			throw new WSClientException(e);
		}
	}
	private String cleanResponse(String response){
		try{
			return response.replaceAll(WS_CLEANXML_AMPSYM, WS_CLEANXML_AMPSYM_REPL);
		}
		catch (Exception e){	//possible to have PatternSyntaxException (though highly unlikely)--return orig response instead
			return response;
		}
	}
}
