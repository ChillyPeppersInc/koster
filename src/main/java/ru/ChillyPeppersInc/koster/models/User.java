package ru.ChillyPeppersInc.koster.models;


import  jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    @NotEmpty(message = "Username shouldn't be empty")
    @Size(min = 3, max = 50, message = "Username length should be between 3 and 20")
    @Column(name = "username", unique = true)
    private String username;

    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 2, max = 50, message = "Name length should be between 2 and 50")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Surname shouldn't be empty")
    @Size(min = 2, max = 50, message = "Surname length should be between 2 and 50")
    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    @NotEmpty(message = "Email shouldn't be empty")
    @Email
    private String email;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "date_of_birthday")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    // работает с типами java.util.Date, java.util.Calendar, java.time.*
    @Past
    private LocalDate birthdate;

    @NotEmpty(message = "Группа не может быть пустой")
    @Column(name = "academicGroup")
    private String academicGroup;

    @Column(name = "bio")
    private String bio;

    @Column(name = "followersCount")
    private int followersCount;

    @Column(name = "followsCount")
    private int followsCount;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "last_login")
    private LocalDate lastLogin;

    @Column(name = "password")
    @NotEmpty(message = "Password shouldn't be empty")
    private String password;

    @Column(name = "created_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate updatedAt;

    @Column(name = "deleted_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    private LocalDate deletedAt;

    @Column(name = "status")
    private String status;


    public User(List<Post> posts, String username, String name, String surname, String email, String avatar, LocalDate birthdate, String academicGroup, String bio, int followersCount, int followsCount, boolean isActive, LocalDate lastLogin, String password, LocalDate createdAt, LocalDate updatedAt, LocalDate deletedAt, String status) {
        this.posts = posts;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.avatar = avatar;
        this.birthdate = birthdate;
        this.academicGroup = academicGroup;
        this.bio = bio;
        this.followersCount = 0; //TOCHANGE
        this.followsCount = 0; //TOCHANGE
        this.isActive = isActive;
        this.lastLogin = lastLogin;
        setPassword(password);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.status = status;
    }

    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String mail) {
        this.email = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDate getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDate lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getPassword() {
        return password;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void setAcademicGroup(String academicGroup) {
        this.academicGroup = academicGroup;
    }

    public String getAcademicGroup() {
        return academicGroup;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getBio() {
        return bio;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public int getFollowsCount() {
        return followsCount;
    }

    public void setFollowsCount(int followsCount) {
        this.followsCount = followsCount;
    }
}
