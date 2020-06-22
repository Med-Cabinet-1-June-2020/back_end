package com.lambdaschool.medcabinet.models;

import javax.persistence.Entity;
import javax.persistence.Table;

//@Entity
//@Table(name = "strain")
public class Strain extends Auditable{

    private long strainid;

    public Strain() {
    }
}
