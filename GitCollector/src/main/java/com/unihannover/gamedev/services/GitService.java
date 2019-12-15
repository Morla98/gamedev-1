package com.unihannover.gamedev.services;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;

public class GitService {
    private Repository repository;
    private Git git;
    public GitService(Repository repository, Git git)
    {
        this.repository = repository;
        this.git = git;
    }
    //TODO: Add Timer which pulls from git
    //TODO: Iterate through all branches
    //TODO: Read git log and generate achievements from the data
    //TODO: Update Achievements


}
