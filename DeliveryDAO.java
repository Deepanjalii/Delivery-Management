package com.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.demo.entities.DeliveryEntity;


@Repository
public interface DeliveryDAO extends CrudRepository<DeliveryEntity, Long> {
}
