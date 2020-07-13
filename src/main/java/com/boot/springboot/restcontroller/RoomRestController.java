package com.boot.springboot.restcontroller;

import com.boot.springboot.data.entity.Room;
import com.boot.springboot.data.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomRestController {

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping
    public List<Room> getRooms()
    {
        System.out.println("Inside RoomRestController::getRooms method");
        List<Room> roomList = new ArrayList<>();
        roomRepository.findAll().forEach(room->roomList.add(room));
        return roomList;
    }
}
