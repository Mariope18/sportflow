package com.sportflow.booking.model;

import com.sportflow.booking.model.enums.SportType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courts")
public class Court {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Enumerated(EnumType.STRING)
    private SportType sport;

    private BigDecimal hourlyRate;

    @Embedded
    private Address address;

    private LocalTime openingTime;

    private LocalTime closingTime;

    private Integer slotDurationMinutes;

    @OneToMany(mappedBy = "court")
    private List<Booking> bookings;
}
