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

    @Column(name = "numberOfComments")
    private int numberOfComments;

    @Column(name = "numberOfNewFiles")
    private int numberOfNewFiles;

    @Column(name = "numberOfCommits")
    private int numberOfCommits;

    @Column(name = "numberOfCorrectCommitMessages")
    private int numberOfCorrectCommitMessages;

    @Column(name = "DinnerCommits")
    private int DinnerCommits;

    @Column(name = "NumberOfHelloWorlds")
    private int NumberOfHelloWorlds;

    @Column(name = "NightCommits")
    private int NightCommits;

    @Column(name = "JavaCommits")
    private int JavaCommits;

    @Column(name = "JavaScriptCommits")
    private int JavaScriptCommits;

    public int getJavaScriptCommits() {
        return JavaScriptCommits;
    }
    public void setJavaScriptCommits(int javaScriptCommits) {
        JavaScriptCommits = javaScriptCommits;
    }
    public int getJavaCommits() {
        return JavaCommits;
    }
    public void setJavaCommits(int javaCommits) {
        JavaCommits = javaCommits;
    }
    public int getNumberOfNewFiles() {
        return numberOfNewFiles;
    }
    public void setNumberOfNewFiles(int numberOfNewFiles) {
        this.numberOfNewFiles = numberOfNewFiles;    }
    public int getDinnerCommits() {
        return DinnerCommits;
    }
    public void setDinnerCommits(int dinnerCommits) {
        DinnerCommits = dinnerCommits;
    }
    public int getNumberOfHelloWorlds() {
        return NumberOfHelloWorlds;
    }
    public void setNumberOfHelloWorlds(int numberOfHelloWorlds) {
        NumberOfHelloWorlds = numberOfHelloWorlds;
    }
    public int getNightCommits() {
        return NightCommits;
    }
    public void setNightCommits(int nightCommits) {
        NightCommits = nightCommits;
    }
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
    public int getNumberOfComments() {
        return numberOfComments;
    }
    public void setNumberOfComments(int numberOfComments) {
        this.numberOfComments = numberOfComments;
    }
    public int getNumberOfCorrectCommitMessages() {
        return numberOfCorrectCommitMessages;
    }
    public void setNumberOfCorrectCommitMessages(int numberOfCorrectCommitMessages) {
        this.numberOfCorrectCommitMessages = numberOfCorrectCommitMessages;
    }
}
