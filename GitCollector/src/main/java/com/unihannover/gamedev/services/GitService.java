package com.unihannover.gamedev.services;

import com.unihannover.gamedev.repositories.MetricRepository;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand;
import org.eclipse.jgit.api.PullResult;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.unihannover.gamedev.models.*;
import com.unihannover.gamedev.CollectorConfig;
import com.unihannover.gamedev.CollectorConfigParser;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Represents a Service that handles Git Requests.
 *
 * @author Felix Volodarskis, Lukas Niehus, Leon Curth
 */
public class GitService {
    private Repository git_repository;
    private Git git;
    //TODO: entweder lastCommitDate oder int benutzen
    private int lastCommitDate;
    //TODO: alle benötigten Daten aus CollectorService nicht durch Funktionsuafrufe bekommen, entweder neu initialisiern oder GitService von CollectorSerivce erben lassen
    private List<Achievement> achievementList;
    private ArrayList<String> seenCommits = new ArrayList<>();
    private Date minDate;
    private Calendar calendar;
    //private
    public GitService(Repository repository, Git git)
    {
        this.git_repository = repository;
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

    public void runTimer(CredentialsProvider user, MetricRepository repository,HttpService httpService, List<Achievement> achievementList1)
    {
        achievementList = achievementList1;
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
                        iterateBranches(repository, httpService);
                    } else {
                        System.out.println("\n\nPULL FAILED!\n\n");
                    }
                }
            }
        };
        thread.start();
    }
    public void updateAchievements(String user_email, HttpService httpService){
        List<Model> uaList = new ArrayList<>();
        UserAchievement newUserAchievement;
        CollectorConfig config = CollectorConfigParser.configJsonToObject();

        for(Achievement achievement: achievementList){
            newUserAchievement = new UserAchievement();
            newUserAchievement.setCollectorId(config.getCollectorId());
            newUserAchievement.setUserEmail(user_email);
            newUserAchievement.setAchievementId(achievement.getId());
            newUserAchievement.setProgress(achievement.getLogic().complied(user_email));
            newUserAchievement.setLastUpdated(new Timestamp(System.currentTimeMillis()));
            uaList.add(newUserAchievement);
        }
        httpService.sendList(uaList, "http://devgame:8080/api/user-achievements");
        return;
    }
    public Metric getDiffRelatedAchievements(String diff, Metric m){
        String lines[] = diff.split("\\r?\\n");
        if(lines[1] != null){
            if(lines[1].startsWith("new file mode ")){
                m.setNumberOfNewFiles(m.getNumberOfNewFiles() + 1);
                return m;
            }
        }
        return m;
    }
    //TODO Diff quantisieren und in Metrics abspeichern + passende Metric Spalte anlegen
    public Metric getDiffs(RevCommit commit, Metric m){
        RevCommit parent = commit.getParent(0);
        System.out.println("Printing diff between tree: " + parent + " and " + commit);
        try{
            FileOutputStream fout = new FileOutputStream("fout.txt");
            try (DiffFormatter diffFormatter = new DiffFormatter(fout)) {
                diffFormatter.setRepository(git_repository);
                for (DiffEntry entry : diffFormatter.scan(parent, commit)) {
                    diffFormatter.format(diffFormatter.toFileHeader(entry));
                    break;
                }
                FileInputStream fin = new FileInputStream(new File("fout.txt"));
                java.util.Scanner scanner = new java.util.Scanner(fin,"UTF-8").useDelimiter("/n");
                String theString = scanner.hasNext() ? scanner.next() : "";
                System.out.println("/n/n/n COMMIT STRING:");
                System.out.println(theString);
                scanner.close();
                return getDiffRelatedAchievements(theString, m);

            }catch (Exception e){
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return m;
    }
    
    public Metric getTimeRelatedAchievement(RevCommit commit, Metric m){
        calendar = GregorianCalendar.getInstance();
        calendar.setTime(commit.getCommitterIdent().getWhen());
        calendar.setTimeZone(commit.getCommitterIdent().getTimeZone());
        System.out.println("HOUR OF DAY: " + calendar.get(Calendar.HOUR_OF_DAY));
        if((calendar.get(Calendar.HOUR_OF_DAY) > 12) && (calendar.get(Calendar.HOUR_OF_DAY) < 13)){
                m.setDinnerCommits(m.getDinnerCommits() + 1);
        }
        if(calendar.get(Calendar.HOUR_OF_DAY) < 4){
            m.setNightCommits(m.getNightCommits() + 1);
        }
        return m;
    }
    public Metric getCommitMessageRelatedAchievement(RevCommit commit, Metric m){
        try {
            String msg = commit.getFullMessage();
            String string_pattern = "(feat|fix|chore|test)(.*):.*\\s";//"(feat|fix|chore|test)(.+)(:)(.+)";
            System.out.println("/n/nMessage:" + msg + "     HERE COMES THE MATCH: " + msg.matches(string_pattern));
            //Pattern pattern = Pattern.compile(string_pattern);
            if (msg.matches(string_pattern)) {
                m.setNumberOfCorrectCommitMessages(m.getNumberOfCorrectCommitMessages() + 1);
                return m;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return m;
    }
    public void parseCommit(RevCommit commit, MetricRepository repository,HttpService httpService){
        String user_email = commit.getCommitterIdent().getEmailAddress();
        //String commit_message = commit.getFullMessage();
        try{
            if(repository.findByUseremail(user_email).get(0) != null){
                System.out.println("Committer_Email:|" + user_email +"| Commit time: " + commit.getCommitTime() + "; Commit get When(): " + commit.getCommitterIdent().getWhen().getHours());
                Metric m = repository.findByUseremail(user_email).get(0);
                Metric new_metric = m;
                new_metric.setUseremail(user_email);
                new_metric.setNumberOfCommits(m.getNumberOfCommits() + 1);
                new_metric = getTimeRelatedAchievement(commit, new_metric);
                new_metric = getCommitMessageRelatedAchievement(commit, new_metric);
 //               new_metric.setNumberOfCorrectCommitMessages(m.getNumberOfCorrectCommitMessages() + getCommitMessageRelatedAchievement(commit, new_metric));
                new_metric = getDiffs(commit, new_metric);
                repository.save(new_metric);
                updateAchievements(user_email, httpService);

            }
        }catch (Exception e){
            System.out.println("email doesnt exist in Collector");
            e.printStackTrace();
        }
        return;
    }
    //TODO: eher getWhen() anstatt getCommitTime benutzen
    //TODO: Exceptions richtig handeln, insbesondere leeres email repo <--> user existiert nicht exception vorher abfragen, entweder mit userlist oder länge von Repo
    public void iterateBranches(MetricRepository repository, HttpService httpService){
        try{
            List<Ref> call = git.branchList().setListMode(ListBranchCommand.ListMode.ALL).call();
            int latest_date = lastCommitDate;
            for(Ref ref : call)
            {
                System.out.println("\nBranch: " + ref.getName()+ "\n");
                String treeName = ref.getName(); // tag or branch
                for (RevCommit commit : git.log().add(git_repository.resolve(treeName)).call()) {
                    if(commit.getCommitTime() > lastCommitDate) {
                        if(latest_date < commit.getCommitTime()){
                            latest_date = commit.getCommitTime();
                        }
                        if(!seenCommits.contains(commit.getName())){
                            seenCommits.add(commit.getName());
                            parseCommit(commit, repository, httpService);
                        }
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



