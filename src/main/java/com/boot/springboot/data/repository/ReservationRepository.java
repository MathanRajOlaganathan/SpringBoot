package com.boot.springboot.data.repository;

import com.boot.springboot.data.entity.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation,Long> {

    public Iterable<Reservation> findAllByResDate(Date date);

}
