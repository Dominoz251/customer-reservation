package pl.edu.wat.customerreservations.services;

import org.springframework.http.ResponseEntity;
import pl.edu.wat.customerreservations.dtos.ReservationRequest;
import pl.edu.wat.customerreservations.dtos.ReservationResponse;
import pl.edu.wat.customerreservations.dtos.UpdateReservationRequest;
import pl.edu.wat.customerreservations.entities.CustomerEntity;

import java.util.Date;
import java.util.List;

public interface ReservationService {
    List<ReservationResponse> getAllReservations();
    List<ReservationResponse> getCustomerReservations(CustomerEntity customerEntity);
    List<String> getBussyDates();

    ResponseEntity addNewReservation(ReservationRequest reservationRequest);
    ResponseEntity updateReservation(UpdateReservationRequest updateReservationRequest);
    ResponseEntity deleteReservation(Long reservationId);
}
