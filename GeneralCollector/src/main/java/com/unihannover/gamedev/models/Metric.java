package com.unihannover.gamedev.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GeneralCollector_Metrics", schema="metrics")
public class Metric {
    @Id
    @Column(name = "example")
    private int examplefield;

    public Metric() {
        super();
    }

    public Metric(int i) {
        this.examplefield = i;

    }

    public int getExamplefield() {
        return examplefield;
    }

    public void setExamplefield(int examplefield) {
        this.examplefield = examplefield;
    }
}
