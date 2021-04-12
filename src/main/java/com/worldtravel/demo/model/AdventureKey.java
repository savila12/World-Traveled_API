package com.worldtravel.demo.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class AdventureKey implements Serializable {

    @Column(name= "user_id")
    private Long userId;

    @Column(name = "country_id")
    private Long countryId;
}
