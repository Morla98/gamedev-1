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

    @Column(name = "issue_created")
    private int issue_created;

    @Column(name = "issue_updated")
    private int issue_updated;

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public int getIssue_created() {
        return issue_created;
    }

    public void setIssue_created(int issue_created) {
        this.issue_created = issue_created;
    }

    public int getIssue_updated() {
        return issue_updated;
    }

    public void setIssue_updated(int issue_updated) {
        this.issue_updated = issue_updated;
    }
}
