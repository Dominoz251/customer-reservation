package pl.edu.wat.customerreservations.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import pl.edu.wat.customerreservations.dtos.ReservationRequest;
import pl.edu.wat.customerreservations.dtos.ReservationResponse;
import pl.edu.wat.customerreservations.entities.CustomerEntity;
import pl.edu.wat.customerreservations.entities.ReservationEntity;
import pl.edu.wat.customerreservations.repositories.CustomerRepository;
import pl.edu.wat.customerreservations.repositories.ReservationRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class ReservationServiceImpl implements ReservationService{

    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, CustomerRepository customerRepository) {
        this.reservationRepository = reservationRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<ReservationResponse> getAllReservations() {
        return StreamSupport.stream(reservationRepository.findAll().spliterator(), false)
                .map(reservationEntity -> new ReservationResponse(reservationEntity.getId(),
                        reservationEntity.getSeatNumber(), reservationEntity.getDateFrom(),
                        reservationEntity.getDateTo(), reservationEntity.getCustomerEntity().getId(),
                        reservationEntity.getCustomerEntity().getLogin()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservationResponse> getCustomerReservations(CustomerEntity customerEntity) {
        return StreamSupport.stream(reservationRepository.findReservationEntityByCustomerEntity(customerEntity)
        .spliterator(), false)
                .map(reservationEntity -> new ReservationResponse(reservationEntity.getId(),
                    reservationEntity.getSeatNumber(), reservationEntity.getDateFrom(),
                    reservationEntity.getDateTo(), reservationEntity.getCustomerEntity().getId(),
                        reservationEntity.getCustomerEntity().getLogin()))
                .collect(Collectors.toList());
    }


    @Override
    public ResponseEntity addNewReservation(ReservationRequest reservationRequest, Long customerId) {
        try {
            Optional<CustomerEntity> result = customerRepository.findById(customerId);
            CustomerEntity customerEntity = result.get();
            ReservationEntity reservationEntity= new ReservationEntity();
            reservationEntity.setSeatNumber(reservationRequest.getSeatNumber());
            reservationEntity.setDateFrom(reservationRequest.getDateFrom());
            reservationEntity.setDateTo(reservationEntity.getDateTo());
            reservationEntity.setCustomerEntity(customerEntity);
            reservationRepository.save(reservationEntity);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity updateReservation(ReservationRequest reservationRequest, Long customerId, Long reservationId) {
        try {
            Optional<CustomerEntity> result = customerRepository.findById(customerId);
            CustomerEntity customerEntity = result.get();
            Optional<ReservationEntity> reservationEntityOptional = reservationRepository.findById(reservationId);
            ReservationEntity reservationEntity = reservationEntityOptional.get();
            reservationEntity.setSeatNumber(reservationRequest.getSeatNumber());
            reservationEntity.setDateFrom(reservationRequest.getDateFrom());
            reservationEntity.setDateTo(reservationEntity.getDateTo());
            reservationEntity.setCustomerEntity(customerEntity);
            reservationRepository.save(reservationEntity);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity deleteReservation(Long customerId, Long reservationId) {
        try {
            Optional<CustomerEntity> result = customerRepository.findById(customerId);
            CustomerEntity customerEntity = result.get();
            Optional<ReservationEntity> reservationEntityOptional = reservationRepository.findById(reservationId);
            ReservationEntity reservationEntity = reservationEntityOptional.get();
            reservationRepository.delete(reservationEntity);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
