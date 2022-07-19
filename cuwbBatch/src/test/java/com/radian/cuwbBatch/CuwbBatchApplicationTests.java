package com.radian.cuwbBatch;

import static org.junit.Assert.assertThat;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.radian.cuwbBatch.jms.CUWBJMSMessagesReceiver;
import com.radian.cuwbBatch.jms.CUWBJMSMessagesSender;

@SpringBootTest
class CuwbBatchApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private CUWBJMSMessagesSender cuwbJMSMessagesSender;

	@Autowired
	private CUWBJMSMessagesReceiver receiver;

	@Test
	public void testReceive() throws Exception {
		try {
			cuwbJMSMessagesSender.send("Hello Spring JMS ActiveMQ!");
			receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
