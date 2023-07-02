package com.tistory.eclipse4j.domain.jpa.db1.repository;

import com.tistory.eclipse4j.domain.jpa.db1.entity.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

}
