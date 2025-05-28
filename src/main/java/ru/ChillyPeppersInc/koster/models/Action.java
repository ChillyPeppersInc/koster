package ru.ChillyPeppersInc.koster.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Action")
@Inheritance(
        strategy = InheritanceType.JOINED
)
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
}
