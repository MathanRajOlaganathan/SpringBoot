package com.boot.springboot.business.service;

import com.boot.springboot.business.domain.RoomReservation;
import com.boot.springboot.data.entity.Guest;
import com.boot.springboot.data.entity.Reservation;
import com.boot.springboot.data.entity.Room;
import com.boot.springboot.data.repository.GuestRepository;
import com.boot.springboot.data.repository.ReservationRepository;
import com.boot.springboot.data.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoomReservationService {

    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public RoomReservationService(GuestRepository guestRepository, ReservationRepository reservationRepository, RoomRepository roomRepository) {
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
    }

    public List<RoomReservation> getRoomReservationForDate(Date date)
    {
        Iterable<Room> rooms = roomRepository.findAll();
        Map<Long,RoomReservation> roomReservationMap = new HashMap<>();
        rooms.forEach(room->{
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getRoomId());
            roomReservation.setRoomName(room.getRoomName());
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservationMap.put(room.getRoomId(),roomReservation);
        });
        Iterable<Reservation> reservations = reservationRepository.findAllByResDate(
                new java.sql.Date(date.getTime()));
        reservations.forEach(reservation -> {
            RoomReservation roomReservation = roomReservationMap.get(reservation.getRoomId());
            roomReservation.setDate(date);
            Guest guest = guestRepository.findById(roomReservation.getGuestId()).get();
            roomReservation.setFirstName(guest.getFirstname());
            roomReservation.setLastName(guest.getLastName());
            roomReservation.setGuestId(guest.getGuestId());
        });

        List<RoomReservation> roomReservationList = new ArrayList<>();
        for(Long id:roomReservationMap.keySet())
        {
            roomReservationList.add(roomReservationMap.get(id));
        }
        return roomReservationList;
    }
}
