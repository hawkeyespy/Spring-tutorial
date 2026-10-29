package com.example.springtutorial;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String addRoom() {
        return "Added new room";
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF', 'GUEST')")
    @PostAuthorize("returnObject.assignedTo == authentication.name")
    public Room getRoom(@PathVariable Long id) {
        return new Room(id, "usr");
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public String getAllRooms() {
        return "All rooms";
    }
}
