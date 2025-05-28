package ru.ChillyPeppersInc.koster.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "Messages")
public class Message extends Action {
    @JoinColumn(name = "sender_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user1;

    @JoinColumn(name = "receiver_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user2;

    @Column(name = "content")
    private String content;

    @Column(name = "image")
    private String image;

    @Column(name = "has_readen")
    private boolean hasReaden;

    @Column(name = "sended_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    private Date sendedAt;

    @Column(name = "readen_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    private Date readenAt;

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

    public Message() {}

    public Message(User user1, User user2, String content, String image, boolean hasReaden, Date sendedAt, Date readenAt, Date createdAt, Date updatedAt, Date deletedAt, String status) {
        this.user1 = user1;
        this.user2 = user2;
        this.content = content;
        this.image = image;
        this.hasReaden = hasReaden;
        this.sendedAt = sendedAt;
        this.readenAt = readenAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.status = status;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isHasReaden() {
        return hasReaden;
    }

    public void setHasReaden(boolean hasReaden) {
        this.hasReaden = hasReaden;
    }

    public Date getSendedAt() {
        return sendedAt;
    }

    public void setSendedAt(Date sendedAt) {
        this.sendedAt = sendedAt;
    }

    public Date getReadenAt() {
        return readenAt;
    }

    public void setReadenAt(Date readenAt) {
        this.readenAt = readenAt;
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
