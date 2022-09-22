package com.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.demo.entities.DeliveryEntity;
import com.demo.exception.DuplicateRecordException;
import com.demo.exception.RecordNotFoundException;

@Service
public interface DeliveryService {  //invoke service
	
	List<DeliveryEntity> showDeliveryEntity();

	public DeliveryEntity add(DeliveryEntity bean) throws DuplicateRecordException ;

	public DeliveryEntity update(DeliveryEntity bean)  throws RecordNotFoundException;

	public boolean deleteDeliveryById(long DeliveryId);

	public DeliveryEntity findById(long Id);
	
}