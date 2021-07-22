package pl.edu.wat.customerreservations.services;

import org.hibernate.sql.Update;
import org.springframework.http.ResponseEntity;
import pl.edu.wat.customerreservations.dtos.CustomerRequest;
import pl.edu.wat.customerreservations.dtos.CustomerResponse;
import pl.edu.wat.customerreservations.dtos.UpdateCustomerRequest;
import pl.edu.wat.customerreservations.entities.CustomerEntity;

import java.util.List;

public interface CustomerService {

    List<CustomerResponse> getAllCustomers();
    CustomerResponse getCustomerById(Long id);

    void addNewCustomer(CustomerRequest customerRequest);
    ResponseEntity updateCustomer(UpdateCustomerRequest updateCustomerRequest);
    ResponseEntity deleteCustomer(Long id);
    CustomerEntity getCustomerEntityById(Long customerId);
}
