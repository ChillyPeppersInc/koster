package ru.ChillyPeppersInc.koster.models;

import jakarta.persistence.*;

@Entity
@Table(name = "TemporaryCode")
public class TemporaryCode {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;



}
