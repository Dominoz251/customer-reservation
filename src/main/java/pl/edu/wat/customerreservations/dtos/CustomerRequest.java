package pl.edu.wat.customerreservations.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
    private String name;
    private String surname;
    private String login;
    private String email;
    List<ReservationRequest> reservations;
}
