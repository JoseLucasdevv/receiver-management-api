package com.TaskDeveloper;

import com.TaskDeveloper.Dtos.ReceiverDTO;
import com.TaskDeveloper.Entity.Pix;
import com.TaskDeveloper.Entity.Receiver;
import com.TaskDeveloper.TypesPix.TypesPix;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskDeveloperApplicationTests {

	private Pix pixReceiver = new Pix();
	private List<Pix> pixList = new ArrayList<>();
	private ReceiverDTO receiverdto = new ReceiverDTO(null, "company", "95.214.249/0001-01", "company@gmail.com", pixList, null);

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testPostReceiverSuccess() {
		pixReceiver.setKey_type(TypesPix.EMAIL);
		pixReceiver.setKey_pix("zelucasplay2001@gmail.com");

		pixList.add(pixReceiver);

		ResponseEntity<String> response = this.restTemplate.postForEntity("/Receiver", receiverdto, String.class);
		assertNotNull(response.getBody());
		assertEquals("save", response.getBody());
	}

	@Test
	public void testGetReceiverEndpoint() {
		ResponseEntity<List> response = this.restTemplate.getForEntity("/Receiver/0", List.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());

		List<Receiver> receivers = response.getBody();
		System.out.println(receivers);
	}

	@Test
	public void testGetReceiverByNameEndpoint() {
		ResponseEntity<String> response = this.restTemplate.getForEntity("/Receiver/name/company/0", String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());

		System.out.println(response.getBody());
	}

	@Test
	public void testUpdateReceiverSuccess() {
		pixReceiver.setKey_type(TypesPix.EMAIL);
		pixReceiver.setKey_pix("zelucasplay2001@gmail.com");

		pixList.add(pixReceiver);

		ReceiverDTO receiverdtoUpdated = new ReceiverDTO(null, "company", "95.214.249/0001-01", "carlos@gmail.com", pixList, null);
		this.restTemplate.put("/Receiver/1", receiverdtoUpdated);


		ResponseEntity<Receiver> response = this.restTemplate.getForEntity("/Receiver/keyvalue/zelucasplay2001@gmail.com", Receiver.class);


		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals("carlos@gmail.com", response.getBody().getEmail());
	}
}