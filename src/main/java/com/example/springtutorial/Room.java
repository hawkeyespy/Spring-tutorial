package com.example.springtutorial;

/**
 * Room record: Details of each room and its occupant.
 *
 * @param id
 * @param assignedTo
 *
 * @author Ravi Chandra
 */
public record Room(Long id, String assignedTo) {
}
