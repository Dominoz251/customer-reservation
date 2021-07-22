package pl.edu.wat.customerreservations.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import pl.edu.wat.customerreservations.dtos.ReservationRequest;
import pl.edu.wat.customerreservations.dtos.ReservationResponse;
import pl.edu.wat.customerreservations.dtos.UpdateReservationRequest;
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
                        reservationEntity.getDate(),
                        reservationEntity.getCustomerEntity().getId(),
                        reservationEntity.getCustomerEntity().getLogin()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservationResponse> getCustomerReservations(CustomerEntity customerEntity) {
        return StreamSupport.stream(reservationRepository.findReservationEntityByCustomerEntity(customerEntity)
        .spliterator(), false)
                .map(reservationEntity -> new ReservationResponse(reservationEntity.getId(),
                    reservationEntity.getDate(), reservationEntity.getCustomerEntity().getId(),
                        reservationEntity.getCustomerEntity().getLogin()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getBussyDates() {
        return StreamSupport.stream(reservationRepository.findAll().spliterator(), false)
                .map(reservationEntity -> reservationEntity.getDate())
                .collect(Collectors.toList());
    }


    @Override
    public ResponseEntity addNewReservation(ReservationRequest reservationRequest) {
        try {
            Optional<CustomerEntity> result = customerRepository.findById(reservationRequest.getCutomerId());
            CustomerEntity customerEntity = result.get();
            System.out.println(reservationRequest.getDate().toString());
            ReservationEntity reservationEntity= new ReservationEntity();
            reservationEntity.setDate(reservationRequest.getDate());
            reservationEntity.setCustomerEntity(customerEntity);
            reservationRepository.save(reservationEntity);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity updateReservation(UpdateReservationRequest updateReservationRequest) {
        try {
            Optional<ReservationEntity> reservationEntityOptional = reservationRepository.findById(updateReservationRequest.getId());
            ReservationEntity reservationEntity = reservationEntityOptional.get();
            reservationEntity.setDate(updateReservationRequest.getDate());
            reservationRepository.save(reservationEntity);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity deleteReservation(Long reservationId) {
        try {
            Optional<ReservationEntity> reservationEntityOptional = reservationRepository.findById(reservationId);
            ReservationEntity reservationEntity = reservationEntityOptional.get();
            reservationRepository.delete(reservationEntity);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }



}
