package com.unihannover.gamedev.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GitCollector_Metrics", schema="metrics")
public class Metric {

    @Id
    @Column(name = "useremail")
    private String useremail;

    @Column(name = "numberOfCommits")
    private int numberOfCommits;

    public int getDinnerCommits() {
        return DinnerCommits;
    }

    public void setDinnerCommits(int dinnerCommits) {
        DinnerCommits = dinnerCommits;
    }

    @Column(name = "DinnerCommits")
    private int DinnerCommits;

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public int getNumberOfCommits() {
        return numberOfCommits;
    }

    public void setNumberOfCommits(int numberOfCommits) {
        this.numberOfCommits = numberOfCommits;
    }

}
