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

import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class GitService {
    private Repository repository;
    private Git git;
    private int lastCommitDate = 0;
    //private
    public GitService(Repository repository, Git git)
    {
        this.repository = repository;
        this.git = git;
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
        }catch (Exception e){
            e.printStackTrace();
        }

        }
    }

    //TODO: Persistence of current Head
    //TODO: Generate achievements from the data
    //TODO: Update Achievements



