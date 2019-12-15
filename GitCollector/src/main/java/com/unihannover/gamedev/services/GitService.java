package com.unihannover.gamedev.services;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand;
import org.eclipse.jgit.api.PullResult;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.transport.CredentialsProvider;

import java.util.List;

public class GitService {
    private Repository repository;
    private Git git;
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
    //TODO: Add Timer which pulls from git
    //TODO: Iterate through all branches
    public void iterateBranches(){
        //List<Ref> call = null;
        try{
            List<Ref> call = git.branchList().setListMode(ListBranchCommand.ListMode.ALL).call();
            for(Ref ref : call)
            {System.out.println("\n\n\nBranch: " + ref.getName()+ "\n\n\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        }
    }
    //TODO: Read git log and generate achievements from the data
    //TODO: Update Achievements



