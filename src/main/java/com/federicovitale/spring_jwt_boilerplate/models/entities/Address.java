package com.federicovitale.spring_jwt_boilerplate.models.entities;

import com.federicovitale.spring_jwt_boilerplate.models.User.User;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;
    private Long number;
    private Long zipCode;
    private String firstName;
    private String lastName;
    private String notes;


    @ManyToMany(mappedBy = "addresses")
    private List<User> users;
}
