package com.demo.controller.test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.context.ActiveProfiles;

import com.demo.controller.DeliveryEntityController;
import com.demo.entities.DeliveryEntity;
import com.demo.exception.DuplicateRecordException;
import com.demo.exception.RecordNotFoundException;
import com.demo.service.DeliveryService;


@ActiveProfiles("test")
@SpringBootTest
class DeliveryEntityControllerTest {

	@Autowired
	private DeliveryService deliveryService;

	@MockBean

	private DeliveryEntityController deliveryCon;

	@Test
	void deleteDeliveryTest() throws ParseException, DuplicateRecordException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = formatter.parse("06-06-2022");
		DeliveryEntity testDeliveryEntity = new DeliveryEntity("Salini", "09:40:00", date, "Active");
		DeliveryEntity testDeliveryEntitySaved = deliveryService.add(testDeliveryEntity);
		long deliveryEntityTestId = testDeliveryEntitySaved.getId();
		deliveryService.deleteDeliveryById(deliveryEntityTestId);
		Assertions.assertThrows(NoSuchElementException.class, () -> {
			deliveryService.findById(deliveryEntityTestId);
		});

	}

	@Test
	 void saveDeliveryTest() throws ParseException, DuplicateRecordException {

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = formatter.parse("06-06-2022");

		DeliveryEntity testDeliveryEntity = new DeliveryEntity("Salini", "09:40:00", date, "Active");
		DeliveryEntity testDeliveryEntitySaved = deliveryService.add(testDeliveryEntity);
		long deliveryEntityTestId = testDeliveryEntitySaved.getId();

		DeliveryEntity testDeliveryEntityDB = deliveryService.findById(deliveryEntityTestId);
		Assertions.assertNotNull(testDeliveryEntityDB);

		deliveryService.deleteDeliveryById(deliveryEntityTestId);
	}

	@Test
	void TestRecordDeliveryByIdShouldThrowDelIdException() throws RecordNotFoundException {
		assertThrows(RecordNotFoundException.class, () -> {
			DeliveryEntity d = new DeliveryEntity();
			deliveryService.update(d);
		});
	}

}