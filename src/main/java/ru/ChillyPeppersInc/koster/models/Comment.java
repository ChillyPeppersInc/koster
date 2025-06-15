package ru.ChillyPeppersInc.koster.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Entity
@Table(name = "Comments")
public class Comment {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "content")
    private String content;

    @Column(name = "image")
    private String image;

    @Column(name = "created_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Past
    private LocalDate createdAt;

    @Column(name = "updated_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDate updatedAt;

    @Column(name = "deleted_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Past
    private LocalDate deletedAt;

    @Column(name = "status")
    private String status;

    @Column(name = "likes")
    private int likes;

    public Comment() {}

    public Comment(User user, String content, String image, LocalDate createdAt, LocalDate updatedAt, LocalDate deletedAt, String status) {
        this.user = user;
        this.content = content;
        this.image = image;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.status = status;
        this.likes = 0;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDate getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDate deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getLikes() {return likes;}

    public void setLikes(int likes) {this.likes = likes;}
}
