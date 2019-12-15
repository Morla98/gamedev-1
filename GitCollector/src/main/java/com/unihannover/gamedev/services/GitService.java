package com.unihannover.gamedev.services;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;

import java.util.List;

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
    public void iterateBranches(){
        List<Ref> call = null;
        try{
            call = git.branchList().call();
            for(Ref ref : call)
            {System.out.println("/n/n/n/n/n/n/n/n/n/n/n/nBranch: " + ref.getName()+ "/n/n/n/n/n/n/n/n");}
        }catch (Exception e){
            e.printStackTrace();
        }

        }
    }
    //TODO: Read git log and generate achievements from the data
    //TODO: Update Achievements



