package ru.ChillyPeppersInc.koster.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "Friendships")
public class Friendship {
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "friend_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User friend;

    @Column(name = "date_of_becoming_friends")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    private Date dateOfBecomingFriends;

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

    public Friendship() {}

    public Friendship(User user, User friend, Date dateOfBecomingFriends, Date createdAt, Date updatedAt, Date deletedAt, String status) {
        this.user = user;
        this.friend = friend;
        this.dateOfBecomingFriends = dateOfBecomingFriends;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }

    public Date getDateOfBecomingFriends() {
        return dateOfBecomingFriends;
    }

    public void setDateOfBecomingFriends(Date dateOfBecomingFriends) {
        this.dateOfBecomingFriends = dateOfBecomingFriends;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
