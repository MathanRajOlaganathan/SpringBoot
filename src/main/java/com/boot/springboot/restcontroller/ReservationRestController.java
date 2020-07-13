package com.boot.springboot.restcontroller;

import com.boot.springboot.data.entity.Reservation;
import com.boot.springboot.data.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
public class ReservationRestController {

    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping
    public Iterable<Reservation> getAllReservations()
    {
        return reservationRepository.findAll();
    }
}
