package com.sportflow.booking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "players",
uniqueConstraints = @UniqueConstraint(columnNames = {"username"}))
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String surname;

    private String username;

    private String email;

    @OneToMany(mappedBy = "player")
    private List<Booking> bookings;
}
