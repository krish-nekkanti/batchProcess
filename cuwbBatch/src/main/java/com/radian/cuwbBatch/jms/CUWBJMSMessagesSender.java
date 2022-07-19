package com.radian.cuwbBatch.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

public class CUWBJMSMessagesSender {

	private static final Logger LOGGER =LoggerFactory.getLogger(CUWBJMSMessagesSender.class);

	@Autowired
	private JmsTemplate jmsTemplate;

	public void send(Object object) {
		LOGGER.info("sending message ");
		jmsTemplate.convertAndSend("CUWB_OUT", object);
		LOGGER.info("Message Successfully Sent ");
	}
}
