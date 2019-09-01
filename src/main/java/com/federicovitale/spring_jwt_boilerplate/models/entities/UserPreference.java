package com.federicovitale.spring_jwt_boilerplate.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UserPreference {
    @Id
    @Column(name = "id")
    private Long id;

    private Boolean getNotifiedOnLogin = false;
    private Boolean getNotifiedOnLoginFailed = false;

    @OneToOne(cascade = CascadeType.REMOVE)
    @MapsId
    @JsonIgnoreProperties("preferences")
    private User user;

    public UserPreference() {
    }

    public UserPreference(User user) {
        this.user = user;
        this.getNotifiedOnLogin = false;
        this.getNotifiedOnLoginFailed = false;
    }
}
