package pl.edu.wat.customerreservations.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.customerreservations.entities.CustomerEntity;
import pl.edu.wat.customerreservations.entities.ReservationEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends CrudRepository<ReservationEntity, Long> {

    Iterable<ReservationEntity> findReservationEntityByCustomerEntity(CustomerEntity customerEntity);
}
