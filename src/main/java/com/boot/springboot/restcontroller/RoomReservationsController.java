package com.boot.springboot.restcontroller;

import com.boot.springboot.business.domain.RoomReservation;
import com.boot.springboot.business.service.RoomReservationService;
import com.boot.springboot.utils.DateUtils;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/roomReservations")
public class RoomReservationsController {

    private RoomReservationService roomReservationService;

    @Autowired
    public RoomReservationsController(RoomReservationService roomReservationService) {
        this.roomReservationService = roomReservationService;
    }

    @GetMapping
    public String getReservations(@RequestParam(value = "date",required = false)String dateString, Model model)
    {
        System.out.println("Inside RoomReservations Controller");
        Date date = DateUtils.createDateFromDateString(dateString);
        List<RoomReservation> roomReservations = roomReservationService.getRoomReservationForDate(date);
        model.addAttribute("reservations",roomReservations);
        return "reservations";
    }
}
