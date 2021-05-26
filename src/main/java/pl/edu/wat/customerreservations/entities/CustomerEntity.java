package pl.edu.wat.customerreservations.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String surname;
    private String login;
    private String email;

    @OneToMany(mappedBy = "customerEntity")
    List<ReservationEntity> reservationEntities;

}
