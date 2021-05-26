package pl.edu.wat.customerreservations.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.customerreservations.dtos.CustomerRequest;
import pl.edu.wat.customerreservations.dtos.CustomerResponse;
import pl.edu.wat.customerreservations.services.CustomerService;

import java.util.List;

@RestController
public class CustomerController {

    private final CustomerService customerServicie;

    @Autowired
    public CustomerController(CustomerService customerServicie)
    {
        this.customerServicie=customerServicie;
    }

    @GetMapping(path = "/api/customer")
    public ResponseEntity<List<CustomerResponse>> getAllCustomers(){
        return new ResponseEntity<>(customerServicie.getAllCustomers(), HttpStatus.OK);
    }

    @PostMapping(path = "/api/customer")
    public ResponseEntity addNewCustomer(@RequestBody CustomerRequest customerRequest) {
        customerServicie.addNewCustomer(customerRequest);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping(path = "api/customer&customerId={id}")
    public ResponseEntity updateCustomer(@RequestBody CustomerRequest customerRequest,
                                         @PathVariable Long id) {
        return customerServicie.updateCustomer(customerRequest, id);
    }

    @DeleteMapping(path = "api/customer&customerId={id}")
    public ResponseEntity deleteCustomer(@PathVariable Long id) {
        return customerServicie.deleteCustomer(id);
    }
}
