package com.osagieerhabor.backend;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class BackendApplicationTests {

	@Autowired
	MockMvc mockMvc;


	@Test
	void contextLoads() throws Exception {
		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.get("/v1/categories")
						.contentType("application/json")

		).andReturn();

		System.out.println(mvcResult.getResponse());
	}

}
