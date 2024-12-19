package com.example.radio_station.models;


import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;
import com.example.radio_station.models.enums.Role;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "surname")
    private String surname;
    @Column(name = "name")
    private String name;
    @Column(name = "login", unique = true)
    private String userLogin;
    @Column(name = "password")
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="user_role", joinColumns = @JoinColumn(name="user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();
    @Column(name="date_of_created")
    private LocalDateTime dateOfCreated;
    @Column(name="active")
    private boolean active;

    @PrePersist
    private void init(){
        dateOfCreated = LocalDateTime.now();
    }

    public boolean isAdmin(){return roles.contains(Role.ADMIN);}

    public boolean isPerformer(){return roles.contains(Role.PERFORMER);}

}
