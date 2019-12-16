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

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public int getIssue_created() {
        return numberOfCommits;
    }

    public void setIssue_created(int issue_created) {
        this.numberOfCommits = issue_created;
    }
}
