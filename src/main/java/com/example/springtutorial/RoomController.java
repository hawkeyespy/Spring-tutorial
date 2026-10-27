package com.example.springtutorial;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @PostMapping
    public String addRoom() {
        return "Added new room";
    }

    @GetMapping("/{id}")
    public String getRoom(@PathVariable String id) {
        return "Room with id: " + id;
    }

    @GetMapping
    public String getAllRooms() {
        return "All rooms";
    }
}
