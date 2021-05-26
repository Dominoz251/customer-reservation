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
public class ReservationResponse {

    private Long id;
    private int seatNumber;
    private Date dateFrom;
    private Date dateTo;
    private Long cunstomerId;
    private String customerLogin;
}
