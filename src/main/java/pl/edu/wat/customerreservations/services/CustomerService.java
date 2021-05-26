package pl.edu.wat.customerreservations.services;

import org.springframework.http.ResponseEntity;
import pl.edu.wat.customerreservations.dtos.CustomerRequest;
import pl.edu.wat.customerreservations.dtos.CustomerResponse;
import pl.edu.wat.customerreservations.entities.CustomerEntity;

import java.util.List;

public interface CustomerService {

    List<CustomerResponse> getAllCustomers();
    CustomerResponse getCustomerById(Long id);

    void addNewCustomer(CustomerRequest customerRequest);
    ResponseEntity updateCustomer(CustomerRequest customerRequest, Long customerId);
    ResponseEntity deleteCustomer(Long customerId);
    CustomerEntity getCustomerEntityById(Long customerId);
}
