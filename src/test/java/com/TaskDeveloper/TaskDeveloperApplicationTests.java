package com.TaskDeveloper;

import com.TaskDeveloper.Dtos.ReceiverDTO;
import com.TaskDeveloper.Entity.Pix;

import com.TaskDeveloper.TypesPix.TypesPix;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskDeveloperApplicationTests {
	private Pix pixReceiver = new Pix();
	private List<Pix> pixList = new ArrayList<>();
	private ReceiverDTO receiverdto = new ReceiverDTO(null,"company","95.214.249/0001-01","company@gmail.com",pixList,null);
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testPostEndpointSuccess(){

		pixReceiver.setKey_type(TypesPix.EMAIL);
		pixReceiver.setKey_pix("zelucasplay2001@gmail.com");

		pixList.add(pixReceiver);

		ResponseEntity<String> response = this.restTemplate.postForEntity("/Receiver",receiverdto,String.class);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals("save",response.getBody());
	}



	@Test
	public void testGetEndpoint() {
		ResponseEntity<String> response = this.restTemplate.getForEntity("/Receiver/0", String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
	}
}
