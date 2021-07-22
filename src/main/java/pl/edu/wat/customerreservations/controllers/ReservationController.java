package pl.edu.wat.customerreservations.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.customerreservations.dtos.ReservationRequest;
import pl.edu.wat.customerreservations.dtos.ReservationResponse;
import pl.edu.wat.customerreservations.dtos.UpdateReservationRequest;
import pl.edu.wat.customerreservations.repositories.CustomerRepository;
import pl.edu.wat.customerreservations.services.CustomerService;
import pl.edu.wat.customerreservations.services.ReservationService;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
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
    public ResponseEntity<List<ReservationResponse>> getCustomerReservations(@PathVariable("id") Long id){
        return new ResponseEntity<>(reservationService
                .getCustomerReservations(customerService.getCustomerEntityById(id)), HttpStatus.OK);
    }

    @PostMapping(path = "/api/customer/addReservation")
    public ResponseEntity addNewReservation(@RequestBody ReservationRequest reservationRequest) {
        reservationService.addNewReservation(reservationRequest);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping(path = "api/customer/reservation")
    public ResponseEntity updateCustomer(@RequestBody UpdateReservationRequest updateReservationRequest) {
        return reservationService.updateReservation(updateReservationRequest);
    }

    @DeleteMapping(path = "api/reservation-delete&reservationId={id}")
    public ResponseEntity deleteCustomer(@PathVariable("id") Long reservId) {
        return reservationService.deleteReservation(reservId);
    }

    @GetMapping(path = "api/bussy-dates")
    public ResponseEntity<List<String>> getBussyDates()
    {
        return new ResponseEntity<>(reservationService.getBussyDates(), HttpStatus.OK);
    }
}
