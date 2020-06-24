package com.lambdaschool.medcabinet.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StrainEffects {

    @ElementCollection
    @CollectionTable(name = "strainmedical")
    @Column(name = "medical")
    private Set<String> medical = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "straineffects")
    @Column(name = "effects")
    private Set<String> effects = new HashSet<>();

    public Set<String> getMedical() {
        return medical;
    }

    public void setMedical(Set<String> medical) {
        this.medical = medical;
    }

    public Set<String> getEffects() {
        return effects;
    }

    public void setEffects(Set<String> effects) {
        this.effects = effects;
    }
}
