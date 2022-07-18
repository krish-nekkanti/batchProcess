package com.radian.cuwbilling.billing.cuw.bo.domain;

import com.radian.cuwbilling.common.bo.domain.BaseDomainObject;

public interface InvoiceEmail extends BaseDomainObject
{
	InvoiceTransmission getInvoiceTransmission();
	void setInvoiceTransmission(InvoiceTransmission trans);
	
	String getEmailAddress();
	void setEmailAddress(String email);
}
