package com.EEIT85.bunnySugar;

import com.EEIT85.bunnySugar.service.anniversaries.AnniversariesService;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BunnySugarApplicationTests {



	@Autowired
	AnniversariesService anniversariesService;


	@Test
	public void testAnniversariesService() throws MessagingException {
	}

}
