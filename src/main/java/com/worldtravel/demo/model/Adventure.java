package com.worldtravel.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "adventures")
public class Adventure {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String adventureName;

    @Column
    private String dateWent;

    @Column
    private String adventureDescription;



    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    public Adventure() {
    }

    public Adventure(Long id, String adventureName, String dateWent, String adventureDescription) {
        this.id = id;
        this.adventureName = adventureName;
        this.dateWent = dateWent;
        this.adventureDescription = adventureDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateWent() {
        return dateWent;
    }

    public void setDateWent(String dateWent) {
        this.dateWent = dateWent;
    }

    public String getAdventureDescription() {
        return adventureDescription;
    }

    public void setAdventureDescription(String adventureDescription) {
        this.adventureDescription = adventureDescription;
    }

    @Override
    public String toString() {
        return "Adventure{" +
                "id=" + id +
                ", dateWent='" + dateWent + '\'' +
                ", adventureDescription='" + adventureDescription + '\'' +
                '}';
    }

    public String getAdventureName() {
        return adventureName;
    }

    public void setAdventureName(String adventureName) {
        this.adventureName = adventureName;
    }

    public User getUser() {
        return user;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
