package com.unihannover.gamedev.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.reflect.Method;

@Entity
@Table(name = "GitCollector_Metrics", schema="metrics")
public class Metric {

    @Id
    @Column(name = "useremail")
    private String useremail;

    public int getNumberOfNewFiles() {
        return numberOfNewFiles;
    }

    public void setNumberOfNewFiles(int numberOfNewFiles) {
        this.numberOfNewFiles = numberOfNewFiles;
    }

    @Column(name = "numberOfNewFiles")
    private int numberOfNewFiles;

    @Column(name = "numberOfCommits")
    private int numberOfCommits;

    public int getNumberOfCorrectCommitMessages() {
        return numberOfCorrectCommitMessages;
    }

    public void setNumberOfCorrectCommitMessages(int numberOfCorrectCommitMessages) {
        this.numberOfCorrectCommitMessages = numberOfCorrectCommitMessages;
    }

    @Column(name = "numberOfCorrectCommitMessages")
    private int numberOfCorrectCommitMessages;

    public int getDinnerCommits() {
        return DinnerCommits;
    }

    public void setDinnerCommits(int dinnerCommits) {
        DinnerCommits = dinnerCommits;
    }

    @Column(name = "DinnerCommits")
    private int DinnerCommits;

    public int getNightCommits() {
        return NightCommits;
    }

    public void setNightCommits(int nightCommits) {
        NightCommits = nightCommits;
    }

    @Column(name = "NightCommits")
    private int NightCommits;

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
