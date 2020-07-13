package com.boot.springboot.web;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.boot.springboot.business.domain.RoomReservation;
import com.boot.springboot.business.service.RoomReservationService;
import com.boot.springboot.restcontroller.RoomReservationsController;
import com.boot.springboot.utils.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(RoomReservationsController.class)
public class RoomReservationsControllerTest {

    @MockBean
    RoomReservationService roomReservationService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getReservations() throws Exception
    {
        String dateString = "2020-01-01";
        Date date = DateUtils.createDateFromDateString(dateString);
        List<RoomReservation> roomReservationList = new ArrayList<>();
        RoomReservation roomReservation = new RoomReservation();
        roomReservation.setGuestId(1);
        roomReservation.setLastName("Daemon");
        roomReservation.setFirstName("Matt");
        roomReservation.setDate(date);
        roomReservation.setRoomName("Mars");
        roomReservation.setRoomNumber("2");
        roomReservationList.add(roomReservation);
        given(roomReservationService.getRoomReservationForDate(date)).willReturn(roomReservationList);

        mockMvc.perform(get("/roomReservations?date=20202-01-01"))
                .andExpect(status().isOk());
//                .andExpect(content().string(containsString(("Daemon, Matt"))));
    }

}
