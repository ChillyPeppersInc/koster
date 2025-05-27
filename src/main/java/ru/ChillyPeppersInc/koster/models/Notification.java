package ru.ChillyPeppersInc.koster.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "Notifications")
public class Notification {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "sender_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User sender;

    @Column(name = "related_id")


    @Column(name = "has_readen")
    private boolean hasReaden;

    @Column(name = "created_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    private Date createdAt;

    @Column(name = "updated_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    private Date updatedAt;

    @Column(name = "deleted_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    private Date deletedAt;

    @Column(name = "status")
    private String status;
}
