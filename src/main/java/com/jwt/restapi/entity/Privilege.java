package com.jwt.restapi.entity;

import com.fasterxml.jackson.annotation.*;
import com.jwt.restapi.entity.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Privilege extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToOne
    @JoinColumn(name = "roleid", insertable = false, updatable = false)
    @JsonBackReference
    private Role role;
    private Long roleid;

    @OneToMany(mappedBy = "privilege")
    @JsonIgnore
    private List<UserPrivilageAssignment> users;
}