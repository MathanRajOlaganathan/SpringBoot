package com.boot.springboot.business.service;

import com.boot.springboot.data.entity.Guest;
import com.boot.springboot.data.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GuestService {

    private GuestRepository guestRepository;

    @Autowired
    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public List<Guest> getHotelGuests()
    {
        Iterable<Guest> guests = guestRepository.findAll();
        List<Guest> guestList = new ArrayList<>();
        guests.forEach(guest -> {
            guestList.add(guest);
        });
        guestList.sort(Comparator.comparing(Guest::getLastName)
                .thenComparing(Guest::getFirstname));
        return guestList;
    }


}
