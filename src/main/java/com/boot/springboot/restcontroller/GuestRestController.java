package com.boot.springboot.restcontroller;

import com.boot.springboot.business.service.GuestService;
import com.boot.springboot.data.entity.Guest;
import com.boot.springboot.data.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/guests")
public class GuestRestController {
    private GuestService guestService;

    @Autowired
    public GuestRestController(GuestService guestService) {
        this.guestService = guestService;
    }




    @GetMapping
    public String guests(Model model)

    {
        model.addAttribute("guests",guestService.getHotelGuests());
        return "guests";
    }
}
