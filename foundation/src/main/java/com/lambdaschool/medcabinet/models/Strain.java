package com.lambdaschool.medcabinet.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "strains")
public class Strain extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long strainid;

    private String strain;

    private String race;

    @ElementCollection
    @CollectionTable(name = "strainflavors", joinColumns = @JoinColumn(name = "strainid"))
    @Column(name = "flavors")
    private Set<String> flavors = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "strainpositive", joinColumns = @JoinColumn(name = "strainid"))
    @Column(name = "positive")
    private Set<String> positive = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "strainnegative", joinColumns = @JoinColumn(name = "strainid"))
    @Column(name = "negative")
    private Set<String> negative = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "strainmedical", joinColumns = @JoinColumn(name = "strainid"))
    @Column(name = "medical")
    private Set<String> medical = new HashSet<>();

    private String type;

    private double rating;

    @Lob
    private String description;

    @ManyToMany(mappedBy = "strains")
    @JsonIgnoreProperties(value = "strains", allowSetters = true)
    private List<User> users = new ArrayList<>();

    public Strain() {
    }

    public Strain(String strain, String race, Set<String> flavors, Set<String> positive, Set<String> negative, Set<String> medical, String type, double rating, String description) {
        this.strain = strain;
        this.race = race;
        this.flavors = flavors;
        this.positive = positive;
        this.negative = negative;
        this.medical = medical;
        this.type = type;
        this.rating = rating;
        this.description = description;
    }

    public long getStrainid() {
        return strainid;
    }

    public void setStrainid(long strainid) {
        this.strainid = strainid;
    }

    public String getStrain() {
        return strain;
    }

    public void setStrain(String strain) {
        this.strain = strain;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public Set<String> getFlavors() {
        return flavors;
    }

    public void setFlavors(Set<String> flavors) {
        this.flavors = flavors;
    }

    public Set<String> getPositive() {
        return positive;
    }

    public void setPositive(Set<String> positive) {
        this.positive = positive;
    }

    public Set<String> getNegative() {
        return negative;
    }

    public void setNegative(Set<String> negative) {
        this.negative = negative;
    }

    public Set<String> getMedical() {
        return medical;
    }

    public void setMedical(Set<String> medical) {
        this.medical = medical;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Strain{" +
                "strainid=" + strainid +
                ", strain='" + strain + '\'' +
                ", race='" + race + '\'' +
                ", flavors=" + flavors +
                ", positive=" + positive +
                ", negative=" + negative +
                ", medical=" + medical +
                ", type='" + type + '\'' +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdDate=" + createdDate +
                ", lastModifiedBy='" + lastModifiedBy + '\'' +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}
