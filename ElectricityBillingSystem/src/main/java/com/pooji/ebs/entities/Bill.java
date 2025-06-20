package com.pooji.ebs.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String month;
    private int units;
    private double amount;

    private String status = "UNPAID";
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
