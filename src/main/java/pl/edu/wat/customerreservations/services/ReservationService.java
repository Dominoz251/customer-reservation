package pl.edu.wat.customerreservations.services;

import org.springframework.http.ResponseEntity;
import pl.edu.wat.customerreservations.dtos.ReservationRequest;
import pl.edu.wat.customerreservations.dtos.ReservationResponse;
import pl.edu.wat.customerreservations.entities.CustomerEntity;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    List<ReservationResponse> getAllReservations();
    List<ReservationResponse> getCustomerReservations(CustomerEntity customerEntity);

    ResponseEntity addNewReservation(ReservationRequest reservationRequest, Long customerId);
    ResponseEntity updateReservation(ReservationRequest reservationRequest, Long customerId, Long reservationId);
    ResponseEntity deleteReservation(Long customerId, Long reservationId);
}
