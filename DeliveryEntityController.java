package com.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.demo.entities.DeliveryEntity;
import com.demo.exception.DuplicateRecordException;
import com.demo.exception.RecordNotFoundException;
import com.demo.service.DeliveryService;


@RestController                              //Handle the request
@RequestMapping(value = "/deliveryentity")   //map specific request path
public class DeliveryEntityController {

   @Autowired                               //inject the object dependency implicitly
   DeliveryService deliveryService;

   //http://localhost:8080/UserApp/deliveryentity/show
   @GetMapping(value = "/show", produces = "application/json")
    List<DeliveryEntity> showDeliveryEntity() {
        System.out.println("Delivery Entity controller");
        List<DeliveryEntity> deliveryList = deliveryService.showDeliveryEntity();
        
        return deliveryList;
    }
   
   @GetMapping(value = "/show3", produces = "application/json")
   public ResponseEntity<DeliveryEntity> findById(@PathVariable("Id")long Id) {
   DeliveryEntity delivery= deliveryService.findById(Id);
       return new ResponseEntity<DeliveryEntity>(delivery,HttpStatus.OK);
   }


   //http://localhost:8080/UserApp/deliveryentity
   @PostMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<DeliveryEntity> add(@RequestBody DeliveryEntity delivery) throws DuplicateRecordException {

	   
       DeliveryEntity Id = deliveryService.add(delivery);
        if (Id.getId() == 0) {
            System.out.println("Before exception");
            throw new DuplicateRecordException("DeliveryEntity with this id already Exist");



       }
        System.out.println("DeliveryEntity name in controller is " + Id);
        return ResponseEntity.ok(Id);
    }


   //http://localhost:8080/UserApp/deliveryentity
   @PutMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<DeliveryEntity> update(@RequestBody DeliveryEntity delivery) throws RecordNotFoundException {
        DeliveryEntity Id = deliveryService.update(delivery);
        
        if (Id == null) {
            throw new RecordNotFoundException(
                    "DeliveryEntity with this name " + delivery.getId() + "does not Exist");

       }
        System.out.println("DeliveryEntity name in controller is " + Id);
        return ResponseEntity.ok(Id);
    }

   //http://localhost:8080/UserApp/deliveryentity/show2/Id
   @DeleteMapping(value = "/show2/{DeliveryId}", produces = "application/json")
    public ResponseEntity<DeliveryEntity> deleteDeliveryById(@PathVariable("DeliveryId") long DeliveryId){
            
        deliveryService.deleteDeliveryById(DeliveryId);
        return new ResponseEntity<DeliveryEntity>(HttpStatus.OK);
    }
}