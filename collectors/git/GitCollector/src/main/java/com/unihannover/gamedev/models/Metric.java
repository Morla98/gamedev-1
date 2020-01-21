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

    @Column(name = "PythonCommits")
    private int PythonCommits;

    @Column(name = "HtmlCommits")
    private int HtmlCommits;

    @Column(name = "DoubleCommit")
    private int DoubleCommit;

    @Column(name = "EmptyLines")
    private int EmptyLines;

    @Column(name = "Javadocs")
    private int Javadocs;

    @Column(name = "TODOs")
    private int TODOs;

    public int getHtmlCommits() {
        return HtmlCommits;
    }
    public void setHtmlCommits(int htmlCommits) {
        HtmlCommits = htmlCommits;
    }
    public int getTODOs() {
        return TODOs;
    }
    public void setTODOs(int TODOs) {
        this.TODOs = TODOs;
    }
    public int getJavadocs() {
        return Javadocs;
    }
    public void setJavadocs(int javadocs) {
        Javadocs = javadocs;
    }
    public int getEmptyLines() {
        return EmptyLines;
    }
    public void setEmptyLines(int emptyLines) {
        EmptyLines = emptyLines;
    }
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
    public int getPythonCommits() {
        return PythonCommits;
    }
    public void setPythonCommits(int pythonCommits) {
        PythonCommits = pythonCommits;
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
    public int getDoubleCommit() {
        return DoubleCommit;
    }
    public void setDoubleCommit(int doubleCommits) {
        this.DoubleCommit = doubleCommits;
    }
}
