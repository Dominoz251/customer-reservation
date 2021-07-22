package pl.edu.wat.customerreservations.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateReservationRequest {
    private Long id;
    private String date;
    private Long customerId;
    private String customerLogin;
}
