package com.federicovitale.snkrshub.models.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Data
public class Address {
    private String address;
    private Long number;
    private Long zipCode;
    private String firstName;
    private String lastName;
    private String notes;

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }

    @ManyToMany(mappedBy = "addresses")
    private List<User> users;
}
