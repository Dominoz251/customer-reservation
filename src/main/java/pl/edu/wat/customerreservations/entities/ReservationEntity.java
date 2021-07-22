package pl.edu.wat.customerreservations.entities;

import javax.persistence.*;

import lombok.Data;

import java.util.Date;

@Entity
@Data
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String date;

    @ManyToOne
    private CustomerEntity customerEntity;

}
