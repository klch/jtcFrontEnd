package pet.frontend.testcase2.entity;

import java.util.UUID;

public class User extends BaseEntity {
    UUID picture;
    String name;
    String email;

    public User() {
    }

    public UUID getPicture() {
        return picture;
    }

    public void setPicture(UUID picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "picture='" + picture + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
