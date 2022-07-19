package com.radian.cuwbilling.billing.cuw.bo.dto.pricing.response;

import com.radian.foundation.bo.codes.BaseEnumType;

public class PricingStatus extends BaseEnumType
{
	public static final PricingStatus PER_LOAN = new PricingStatus(new Long(1), "Per Loan", "Per Loan");

    public static final PricingStatus PER_DIEM = new PricingStatus(new Long(2), "Per Diem", "Per Diem");
    
    public static final PricingStatus NOT_PRICED = new PricingStatus(new Long(3), "Not Priced", "Not Priced");
    
    private PricingStatus(Long id, String label, String codeValue)
    {
        super(id, label, codeValue);
    }

}
