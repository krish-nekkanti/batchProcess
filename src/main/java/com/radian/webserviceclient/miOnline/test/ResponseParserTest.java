package com.radian.webserviceclient.miOnline.test;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.radian.webserviceclient.miOnline.model.result.WMCUWGetAllUnbilledLoansForDatesResponseDTL;
import com.radian.webserviceclient.miOnline.model.result.WMCUWGetAllUnbilledLoansResponseDTL;
import com.radian.webserviceclient.miOnline.model.result.WMCUWGetNumberOfUnbilledLoansResponseDTL;
import com.radian.webserviceclient.miOnline.model.result.WMCUWGetRepInfoResponseDTL;
import com.radian.webserviceclient.miOnline.model.result.WMCUWListUnderwritersWithUnbilledLoansForDatesResponseDTL;
import com.radian.webserviceclient.miOnline.model.result.WMCUWListUnderwritersWithUnbilledLoansResponseDTL;
import com.radian.webserviceclient.miOnline.model.result.WMCUWUpdateLoanBillingStatusResponseDTL;

public class ResponseParserTest {

	private static String xmlPath="E:\\Spring-Practices\\batchProcess-main\\src\\main\\java\\com\\radian\\webserviceclient\\miOnline\\resultXSD\\";
	public static void main(String[] args) throws Exception {
		//testUnbilledLoansForDates();
		testWMCUWGetAllUnbilledLoans();
		//testWMCUWGetNumberOfUnbilledLoansResponse();
		//testWMCUWGetRepInfoResponse();
		//testWMCUWListUnderwritersWithUnbilledLoansResponse();
		//testWMCUWListUnderwritersWithUnbilledLoansForDatesResponse();
		//testWMCUWUpdateLoanBillingStatusResponse();
	}
	private static void testWMCUWUpdateLoanBillingStatusResponse() throws IOException, JAXBException {
		String xmlString = new String(Files.readAllBytes(Paths.get(xmlPath+"WMCUWUpdateLoanBillingStatusResponse.xml")));
		JAXBContext jaxbContext = JAXBContext.newInstance(WMCUWUpdateLoanBillingStatusResponseDTL.class);        
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		WMCUWUpdateLoanBillingStatusResponseDTL response = (WMCUWUpdateLoanBillingStatusResponseDTL) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));
		System.out.println(response);
	}
	
	private static void testWMCUWListUnderwritersWithUnbilledLoansForDatesResponse() throws IOException, JAXBException {
		String xmlString = new String(Files.readAllBytes(Paths.get(xmlPath+"WMCUWListUnderwritersWithUnbilledLoansForDatesResponse.xml")));
		JAXBContext jaxbContext = JAXBContext.newInstance(WMCUWListUnderwritersWithUnbilledLoansForDatesResponseDTL.class);        
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		WMCUWListUnderwritersWithUnbilledLoansForDatesResponseDTL response = (WMCUWListUnderwritersWithUnbilledLoansForDatesResponseDTL) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));
		System.out.println(response.getUnderwriters());
	}
	private static void testWMCUWListUnderwritersWithUnbilledLoansResponse() throws IOException, JAXBException {
		String xmlString = new String(Files.readAllBytes(Paths.get(xmlPath+"WMCUWListUnderwritersWithUnbilledLoansResponse.xml")));
		JAXBContext jaxbContext = JAXBContext.newInstance(WMCUWListUnderwritersWithUnbilledLoansResponseDTL.class);        
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		WMCUWListUnderwritersWithUnbilledLoansResponseDTL response = (WMCUWListUnderwritersWithUnbilledLoansResponseDTL) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));
		System.out.println(response.getUnderwriters());
	}

	private static void testWMCUWGetRepInfoResponse() throws IOException, JAXBException {
		String xmlString = new String(Files.readAllBytes(Paths.get(xmlPath+"WMCUWGetRepInfoResponse.xml")));
		JAXBContext jaxbContext = JAXBContext.newInstance(WMCUWGetRepInfoResponseDTL.class);        
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		WMCUWGetRepInfoResponseDTL response = (WMCUWGetRepInfoResponseDTL) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));
		System.out.println(response);
	}

	private static void testWMCUWGetNumberOfUnbilledLoansResponse() throws IOException, JAXBException {
		String xmlString = new String(Files.readAllBytes(Paths.get(xmlPath+"WMCUWGetNumberOfUnbilledLoansResponse.xml")));
		JAXBContext jaxbContext = JAXBContext.newInstance(WMCUWGetNumberOfUnbilledLoansResponseDTL.class);        
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		WMCUWGetNumberOfUnbilledLoansResponseDTL response = (WMCUWGetNumberOfUnbilledLoansResponseDTL) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));
		System.out.println(response.getNrloans());
	}

	private static void testWMCUWGetAllUnbilledLoans() throws IOException, JAXBException {
		String xmlString = new String(Files.readAllBytes(Paths.get(xmlPath+"WMCUWGetAllUnbilledLoansResponse.xml")));
		JAXBContext jaxbContext = JAXBContext.newInstance(WMCUWGetAllUnbilledLoansResponseDTL.class);        
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		WMCUWGetAllUnbilledLoansResponseDTL response = (WMCUWGetAllUnbilledLoansResponseDTL) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));
		System.out.println(response.getLoans());;
	}

	private static void testUnbilledLoansForDates() throws IOException, JAXBException {
		String xmlString = new String(Files.readAllBytes(Paths.get(xmlPath+"WMCUWGetAllUnbilledLoansForDatesResponse.xml")));
		JAXBContext jaxbContext = JAXBContext.newInstance(WMCUWGetAllUnbilledLoansForDatesResponseDTL.class);        
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		WMCUWGetAllUnbilledLoansForDatesResponseDTL response = (WMCUWGetAllUnbilledLoansForDatesResponseDTL) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));
		System.out.println(response.getLoans());;
	}

}
