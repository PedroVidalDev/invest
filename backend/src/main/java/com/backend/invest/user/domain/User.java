package com.backend.invest.user.domain;

import java.time.LocalDateTime;

public class User {
    private String id;
    private String name;
    private String email;
    private String password;
    private String photo;
    private boolean active;
    private CategoryType category;
    private LocalDateTime verifiedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public User(String id, String name, String email, String password, String photo, boolean active, CategoryType category, LocalDateTime verifiedAt, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.photo = photo;
        this.active = active;
        this.category = category;
        this.verifiedAt = verifiedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getPhoto() {
        return this.photo;
    }

    public boolean getActive() {
        return this.active;
    }

    public CategoryType getCategory() {
        return this.category;
    }

    public LocalDateTime getVerifiedAt() {
        return this.verifiedAt;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    // Behavior Setters
    public void updateProfile(String name, String email, String photo) {
        this.name = name;
        this.email = email;
        this.photo = photo;
    }

    public void toggleActive() {
        this.active = !this.active;
        this.updatedAt = LocalDateTime.now();
    }

    public void verify() { 
        this.verifiedAt = LocalDateTime.now(); 
    }
}
