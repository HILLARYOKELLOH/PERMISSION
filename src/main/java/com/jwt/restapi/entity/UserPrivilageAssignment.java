package com.jwt.restapi.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.jwt.restapi.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "user_privilege_assignment",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"userid", "privilegeid"})})
public class UserPrivilageAssignment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userid", insertable = false, updatable = false)
    private User user;
    private Long userid;

    @ManyToOne
    @JoinColumn(name = "privilegeid", insertable = false, updatable = false)
    private Privilege privilege;
    private Long privilegeid;

    public UserPrivilageAssignment(Long userid, Long privilegeid) {
        this.userid = userid;
        this.privilegeid = privilegeid;
    }
}
