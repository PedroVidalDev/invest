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

    public User(Object id2, String name2, String email2, String password2, String photo2, boolean active2,
            CategoryType category2, LocalDateTime verifiedAt2, Object createdAt2, Object updatedAt2) {
        //TODO Auto-generated constructor stub
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
}
