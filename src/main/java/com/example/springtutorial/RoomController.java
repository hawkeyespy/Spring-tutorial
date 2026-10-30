package com.example.springtutorial;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Room controller class: APIs for the rooms services
 *
 * @author RAvi Chandra
 */
@RestController
@RequestMapping("/rooms")
public class RoomController {

    @PostMapping
    @PreAuthorize("hasAuthority('ROOM_ADD')")
    public String addRoom() {
        return "Added new room";
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROOM_VIEW')")
    public Room getRoom(@PathVariable Long id) {
        return new Room(id, "usr");
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROOM_VIEW_ALL')")
    public String getAllRooms() {
        return "All rooms";
    }
}