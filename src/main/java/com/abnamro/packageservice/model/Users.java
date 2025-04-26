package com.abnamro.packageservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.function.Function;

@Entity
@Data
@NoArgsConstructor
public class Users {

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    long id;
    private String name;
    private String street;
    private String houseNumber;
    private String pinCode;

    public Users allUsers() {
        Users user = new Users();
        user.name = this.name;
        user.street = this.street;
        user.houseNumber = this.houseNumber;
        user.pinCode = this.pinCode;
        return user;
    }
}
