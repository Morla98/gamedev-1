package com.unihannover.gamedev.services;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand;
import org.eclipse.jgit.api.PullResult;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a Service that handles Git Requests.
 *
 * @author Felix Volodarskis, Lukas Niehus, Leon Curth
 */
public class GitService {
    private Repository repository;
    private Git git;
    private int lastCommitDate;
    //private
    public GitService(Repository repository, Git git)
    {
        this.repository = repository;
        this.git = git;
        setLastCommitDate();
    }

    public void setLastCommitDate() {
        JSONParser parser = new JSONParser();
        try {
            Reader reader = new FileReader("config/collectorConfiguration/gitTimestamp.json");
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            lastCommitDate = ((Long) jsonObject.get("timestamp")).intValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeTimestamptoJSON(int timestamp)
    {
        JSONObject time = new JSONObject();
        time.put("timestamp", timestamp);
        try {
            FileWriter file = new FileWriter("config/collectorConfiguration/gitTimestamp.json");
            file.write(time.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void runTimer(CredentialsProvider user)
    {

        Thread thread = new Thread() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    PullResult result = null;
                    try {
                        result = git.pull()
                                .setCredentialsProvider(user)
                                .call();
                    } catch (GitAPIException e) {
                        e.printStackTrace();
                    }

                    if (result.isSuccessful()) {
                        System.out.println("\n\nPULL SUCCESS!\n\n");
                        iterateBranches();
                    } else {
                        System.out.println("\n\nPULL FAILED!\n\n");
                    }
                }
            }
        };
        thread.start();
    }

    public void iterateBranches(){
        try{
            List<Ref> call = git.branchList().setListMode(ListBranchCommand.ListMode.ALL).call();
            int latest_date = lastCommitDate;
            for(Ref ref : call)
            {
                System.out.println("\n\n\nBranch: " + ref.getName()+ "\n\n\n");
                String treeName = ref.getName(); // tag or branch
                for (RevCommit commit : git.log().add(repository.resolve(treeName)).call()) {
                    if(commit.getCommitTime() > lastCommitDate) {
                        if(latest_date < commit.getCommitTime()){
                            latest_date = commit.getCommitTime();
                        }
                        System.out.println("Full Message: " + commit.getFullMessage() + "; Timestamp(int): " + commit.getCommitTime());
                    }
                }
            }
            lastCommitDate = latest_date;
            writeTimestamptoJSON(lastCommitDate);
        }catch (Exception e){
            e.printStackTrace();
        }

        }
    }

    //TODO: Persistence of current Head
    //TODO: Generate achievements from the data
    //TODO: Update Achievements



