package pl.edu.wat.customerreservations.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.edu.wat.customerreservations.dtos.CustomerRequest;
import pl.edu.wat.customerreservations.dtos.CustomerResponse;
import pl.edu.wat.customerreservations.entities.CustomerEntity;
import pl.edu.wat.customerreservations.repositories.CustomerRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CustomerServiceImpl implements CustomerService{


    private final CustomerRepository customerRepository;
    private final ReservationService reservationService;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ReservationService reservationService) {
        this.customerRepository = customerRepository;
        this.reservationService = reservationService;
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
        return StreamSupport.stream(customerRepository.findAll().spliterator(), false)
                .map(customerEntity -> new CustomerResponse(customerEntity.getId(), customerEntity.getName(),
                        customerEntity.getSurname(), customerEntity.getLogin(), customerEntity.getEmail(),
                        reservationService.getCustomerReservations(customerEntity)))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponse getCustomerById(Long id) {
        return null;
    }

    @Override
    public void addNewCustomer(CustomerRequest customerRequest) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(customerRequest.getName());
        customerEntity.setSurname(customerRequest.getSurname());
        customerEntity.setLogin(customerRequest.getLogin());
        customerEntity.setEmail(customerRequest.getEmail());
        customerRepository.save(customerEntity);
    }

    @Override
    public ResponseEntity updateCustomer(CustomerRequest customerRequest, Long customerId) {
        try{
            Optional<CustomerEntity> result = customerRepository.findById(customerId);
            CustomerEntity customerEntity = result.get();
            customerEntity.setName(customerRequest.getName());
            customerEntity.setSurname(customerRequest.getSurname());
            customerEntity.setLogin(customerRequest.getLogin());
            customerEntity.setEmail(customerRequest.getEmail());
            customerRepository.save(customerEntity);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity deleteCustomer(Long customerId) {
        try {
            Optional<CustomerEntity> result = customerRepository.findById(customerId);
            CustomerEntity customerEntity = result.get();
            customerRepository.delete(customerEntity);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }


    public CustomerEntity getCustomerEntityById(Long customerId)
    {
        Optional<CustomerEntity> customerEntityOptional = customerRepository.findById(customerId);
        if(customerEntityOptional.isPresent())
            return customerEntityOptional.get();
        else
            throw new IllegalArgumentException();
    }
}
