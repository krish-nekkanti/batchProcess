package com.radian.cuwbilling.billing.cuw.bo.domain;

import java.util.Date;
import com.radian.cuwbilling.common.bo.domain.BaseDomainObject;

public interface TimeSheetImportEntry extends BaseDomainObject
{
	 Date getDate();

	 Double getHours();

	 String getUnderwriterCode();

	 void setDate(Date date);

	 void setHours(Double hours);

	 void setUnderwriterCode(String underwriterCode);


}
