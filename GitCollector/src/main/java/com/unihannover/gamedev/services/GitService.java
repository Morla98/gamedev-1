package com.unihannover.gamedev.services;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullResult;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.transport.CredentialsProvider;

import java.util.Timer;
import java.util.TimerTask;

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
        /*
        final long start = System.currentTimeMillis();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run () {
                System.out.print("Task invoked: " +
                        (System.currentTimeMillis() - start) + " ms");
                System.out.println(" - " + Thread.currentThread());

                PullResult result = null;
                try {
                    result = git.pull()
                            .setCredentialsProvider(user)
                            .setRemote("origin")
                            .setRemoteBranchName("master")
                            .call();
                } catch (GitAPIException e) {
                    e.printStackTrace();
                }

                if (result.isSuccessful()) {

                } else {

                }

            }
        }, 100000, 100000);*/

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
                                .setRemote("origin")
                                .setRemoteBranchName("master")
                                .call();
                    } catch (GitAPIException e) {
                        e.printStackTrace();
                    }

                    if (result.isSuccessful()) {
                        System.out.println("\n\nPULL SUCCESS!\n\n");
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
    //TODO: Read git log and generate achievements from the data
    //TODO: Update Achievements


}
