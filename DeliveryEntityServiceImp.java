package com.demo.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.entities.DeliveryEntity;
import com.demo.exception.DuplicateRecordException;
import com.demo.exception.RecordNotFoundException;
import com.demo.repository.DeliveryDAO;

@Service("deliveryservice") //it provide business functionalities
public class DeliveryEntityServiceImp implements DeliveryService {

	@Autowired
	DeliveryDAO deliveryDao;

	@Override
	public List<DeliveryEntity> showDeliveryEntity() {   //delivery list is created which acts like an in memory database for delivery object 
		System.out.println("Service layer entity called");
		return (List<DeliveryEntity>) deliveryDao.findAll();

	}

	@Transactional // applies as default to all methods
	@Override      //use when subclass has the same method
	public DeliveryEntity add(DeliveryEntity request) throws DuplicateRecordException {
		Optional<DeliveryEntity> delivery = deliveryDao.findById(request.getId());

		if (delivery.isPresent()) { // values is present ,isPresent()

			throw new DuplicateRecordException("Delivery Entity with this name " + request.getId() + "already Exist");
		} else {
			return (DeliveryEntity) deliveryDao.save(request); // save method will return save entity

		}

	}

	@Transactional 
	@Override        //use when subclass has the same method
	public DeliveryEntity update(DeliveryEntity bean) throws RecordNotFoundException {

		Optional<DeliveryEntity> delivery = deliveryDao.findById(bean.getId());
		if (delivery.isPresent()) {
			return (DeliveryEntity) deliveryDao.save(bean);
		} else {
			throw new RecordNotFoundException("Delivery Entity with this Id " + bean.getId() + "Doesn't Exist");
		}

	}

	@Override      //use when subclass has the same method
	public boolean deleteDeliveryById(long DeliveryId) {
		deliveryDao.deleteById(DeliveryId);
		return false;
	}

	@Override
	public DeliveryEntity findById(long deliveryEntityTestId) {

		Optional<DeliveryEntity> delivery = deliveryDao.findById(deliveryEntityTestId);
		return delivery.get();
	}

	public void save(DeliveryEntity testDeliveryEntity) {

	}
}
