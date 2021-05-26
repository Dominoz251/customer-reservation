package pl.edu.wat.customerreservations.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.customerreservations.dtos.ReservationRequest;
import pl.edu.wat.customerreservations.dtos.ReservationResponse;
import pl.edu.wat.customerreservations.repositories.CustomerRepository;
import pl.edu.wat.customerreservations.services.CustomerService;
import pl.edu.wat.customerreservations.services.ReservationService;

import java.util.List;

@RestController
public class ReservationController {

    private final ReservationService reservationService;
    private final CustomerRepository customerRepository;
    private final CustomerService customerService;

    @Autowired
    public ReservationController(ReservationService reservationService, CustomerRepository customerRepository, CustomerService customerService)
    {
        this.reservationService=reservationService;
        this.customerRepository = customerRepository;
        this.customerService = customerService;
    }

    @GetMapping(path = "/api/reservations")
    public ResponseEntity<List<ReservationResponse>> getAllReservations(){
        return new ResponseEntity<>(reservationService.getAllReservations(), HttpStatus.OK);
    }

    @GetMapping(path = "/api/customer&customerId={id}/reservation")
    public ResponseEntity<List<ReservationResponse>> getCustomerReservations(@PathVariable Long id){
        return new ResponseEntity<>(reservationService
                .getCustomerReservations(customerService.getCustomerEntityById(id)), HttpStatus.OK);
    }

    @PostMapping(path = "/api/customer&customerId={id}/reservation")
    public ResponseEntity addNewReservation(@RequestBody ReservationRequest reservationRequest,
                                            @PathVariable Long id) {
        reservationService.addNewReservation(reservationRequest, id);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping(path = "api/customer&customerId={custId}/reservation&reservationId={reservId}")
    public ResponseEntity updateCustomer(@RequestBody ReservationRequest reservationRequest,
                                         @PathVariable Long custId,
                                         @PathVariable Long reservId) {
        return reservationService.updateReservation(reservationRequest, custId, reservId);
    }

    @DeleteMapping(path = "api/customer&customerId={custId}/reservation&reservationId={reservId}")
    public ResponseEntity deleteCustomer(@PathVariable Long custId,
                                         @PathVariable Long reservId) {
        return reservationService.deleteReservation(custId, reservId);
    }
}
