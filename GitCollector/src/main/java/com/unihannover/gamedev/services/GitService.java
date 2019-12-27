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
import org.eclipse.jgit.patch.FileHeader;
import org.eclipse.jgit.patch.HunkHeader;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.unihannover.gamedev.models.*;
import com.unihannover.gamedev.CollectorConfig;
import com.unihannover.gamedev.CollectorConfigParser;


/**
 * Represents a Service that handles Git Requests.
 *
 * @author Felix Volodarskis, Lukas Niehus, Leon Curth
 */
public class GitService{
    private Repository git_repository;
    private Git git;
    private ArrayList<String> seenCommits = new ArrayList<>();
    private Date minDate = new Date();
    private Calendar calendar;
    private List<Achievement> achievementList;
    private HttpService httpService;
    private MetricRepository repository;
    private List<User> userList;
    private	List<UserAchievement> uaList;
    private List<Model> uaModelList;
    private List<String> regexList = new ArrayList<String>();
    private List<LambdaInterface> createDiffList = new ArrayList<LambdaInterface>();
    private CollectorConfig config = CollectorConfigParser.configJsonToObject();
    private List<NameLambdaInterface> nameLambdaList = new ArrayList<NameLambdaInterface>();
    private CredentialsProvider user;

    public GitService(Repository git_repository, Git git, List<Achievement> achievementList, HttpService httpService,MetricRepository repository,List<User> userList, List<UserAchievement> uaList, List<Model> uaModelList, CredentialsProvider user)
    {
        this.git_repository = git_repository;
        this.git = git;
        this.achievementList = achievementList;
        this.httpService = httpService;
        this.repository = repository;
        this.userList = new ArrayList<User>();
        this.uaList = uaList;
        this.uaModelList = uaModelList;
        setLastCommitDate();
        setRegex();
        setCreateDiffList();
        setNameLambdaList();
        this.user = user;
    }
    /**
     * Get the User specified LastCommitDate from the gitTimeStamp.json
     * Set the internal LastCommitDate which is used to exclude Commits that were pushed before the LastCommitDate
     */
    public void setLastCommitDate() {
        JSONParser parser = new JSONParser();
        try {
            Reader reader = new FileReader("config/collectorConfiguration/gitTimeStamp.json");
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
            minDate = formatter.parse(jsonObject.get("timestamp").toString());
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Parse the legalRegex.json to set the legal Regex Strings
     * The Regex Strings specify which commit messages are legal
     */
    public void setRegex(){
        JSONParser parser = new JSONParser();
        try{
            Reader reader = new FileReader("config/collectorConfiguration/legalRegex.json");
            JSONArray jsonArray = (JSONArray) parser.parse(reader);
            for (Object obj : jsonArray){
                JSONObject a = (JSONObject) obj;
                regexList.add((String)a.get("regex"));
            }
            reader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void writeTimeStampToJSON()
    {
        JSONObject time = new JSONObject();
        time.put("curr_head", minDate);
        try {
            FileWriter file = new FileWriter("config/collectorConfiguration/gitTimeStamp.json");
            file.write(time.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCreateDiffList() {
        List<LambdaInterface> list = new ArrayList<LambdaInterface>();
        JSONParser parser = new JSONParser();
        try{
            Reader reader = new FileReader("config/achievements/createDiffAchievement.json");
            JSONArray jsonArray = (JSONArray) parser.parse(reader);
            for(Object obj : jsonArray){
                JSONObject a = (JSONObject) obj;
                String setter = "set" + (String)a.get("command");
                String getter = "get" + (String)a.get("command");
                list.add((string, metric) -> {
                    Pattern pattern = Pattern.compile((String)a.get("regex"));
                    Matcher matcher = pattern.matcher(string);
                    int count = 0;
                    while (matcher.find()){
                        count++;
                    }
                    try{
                        Class parttypes[] = new Class[1];
                        parttypes[0] = Integer.TYPE;
                        Method setter_method = Metric.class.getMethod(setter, parttypes);
                        Method getter_method = Metric.class.getMethod(getter);
                        Object arglist[] = new Object[1];
                        arglist[0] = (int)getter_method.invoke(metric) + count;
                        setter_method.invoke(metric, arglist);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                });
            }
            reader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        this.createDiffList = list;
    }

    public void setNameLambdaList(){
        nameLambdaList.add((name, metric) -> {
            String ending = name.substring(name.lastIndexOf(".") + 1);
            if(ending.equals("java")){
                metric.setJavaCommits(metric.getJavaCommits() + 1);
            }
        });
        nameLambdaList.add((name,metric) -> {
            String ending = name.substring(name.lastIndexOf(".") + 1);
            if(ending.equals("js")){
                metric.setJavaScriptCommits(metric.getJavaScriptCommits() + 1);
            }
        });
    }


    public void gitPull()
    {
        PullResult result = null;
        try {
            result = git.pull()
                    .setCredentialsProvider(user)
                    .call();
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
        if (result.isSuccessful()) {
            System.out.println("\n\nWEBHOOK PULL SUCCESS!\n\n");
            iterateBranches();
        } else {
            System.out.println("\n\nWEBHOOK PULL FAILED!\n\n");
        }
    }

    //VERALTETE METHODE!!!
    public void runTimer(CredentialsProvider user)
    {
        setLastCommitDate();
        setRegex();
        setCreateDiffList();
        setNameLambdaList();
        System.out.println(minDate);
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

    /**
     * Send all Updated Achievements to the main Application
     * @param user_email the email of the user you wish to update
     */
    public void updateAchievements(String user_email){
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

    public void getCreateDiffRelatedAchievemnets(String diff_string, Metric metric){
        for(LambdaInterface ele : createDiffList){
            ele.createDiff(diff_string, metric);
        }
    }

    public void getNameRelatedAchievements(String name, Metric metric){
        for(NameLambdaInterface ele : nameLambdaList){
            ele.createName(name, metric);
        }
    }

    public void getChangeDiffRelatedAChievements(String[] lines, Metric metric){
        String added_diff = "";
        String deleted_diff = "";
        String file_name = lines[3].substring(lines[3].lastIndexOf("/") + 1);
        String old_name = lines[2].substring(lines[2].lastIndexOf("/") + 1);
        System.out.println("This is the name of the changed file: " + file_name);
        getNameRelatedAchievements(file_name, metric);
        for(int i = 5; i < lines.length; i++){
            if(lines[i].startsWith("+")){
                added_diff.concat(lines[i]);
            }
            if(lines[i].startsWith("-")){
                deleted_diff.concat(lines[i]);
            }
        }
        getCreateDiffRelatedAchievemnets(added_diff, metric);
    }

    /**
     * Update the metrics concerning the diffs of the commit
     * @param diff The diff String of the changed file
     * @param metric the Metric of the associated user
     */
    public void getDiffRelatedAchievements(String diff, Metric metric){
        String lines[] = diff.split("\\r?\\n");
        if(lines[1] != null){
            //new file
            if(lines[1].startsWith("new file mode ")){
                metric.setNumberOfNewFiles(metric.getNumberOfNewFiles() + 1);
                getNameRelatedAchievements(lines[0].substring(lines[0].lastIndexOf("/")), metric);
                getCreateDiffRelatedAchievemnets(diff, metric);
            // changing file
            }else if(lines[1].startsWith("index")){
                getChangeDiffRelatedAChievements(lines, metric);
            }else if(true){

            }
        }
    }

    /**
     * extract the diff from the commit and call getDiffRelatedAchievements to extract Achievements
     * @param commit the commit from which the metrics Data should be generated
     * @param metric the Metric of the associated user
     */
    public void getDiffs(RevCommit commit, Metric metric){
        RevCommit parent = commit.getParent(0);
        System.out.println("Printing diff between tree: " + parent + " and " + commit);
        //File file = new File("fout.txt");
        try{
            FileOutputStream fout = new FileOutputStream("fout.txt");
            try (DiffFormatter diffFormatter = new DiffFormatter(fout)) {
                diffFormatter.setRepository(git_repository);
                diffFormatter.setContext(0);
                for (DiffEntry entry : diffFormatter.scan(parent, commit)) {
                    diffFormatter.format(diffFormatter.toFileHeader(entry));
                    FileHeader fileHeader = diffFormatter.toFileHeader( entry );
                    List<? extends HunkHeader> hunks = fileHeader.getHunks();
                    for( HunkHeader hunk : hunks ) {
                        System.out.println( hunk );
                    }
                    FileInputStream fin = new FileInputStream(new File("fout.txt"));
                    java.util.Scanner scanner = new java.util.Scanner(fin,"UTF-8").useDelimiter("\\Z");
                    String theString = scanner.hasNext() ? scanner.next() : "";
                    System.out.println("/n COMMIT DIFF STRING Of A File: ");
                    System.out.println(theString);
                    scanner.close();
                    getDiffRelatedAchievements(theString, metric);
                    PrintWriter pw = new PrintWriter("fout.txt");
                    pw.close();
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * extract Metric Data from the concerning the time of the commit
     * @param commit the commit from which the metrics Data should be generated
     * @param metric the Metric of the associated user
     */
    public void getTimeRelatedAchievement(RevCommit commit, Metric metric){
        calendar = GregorianCalendar.getInstance();
        calendar.setTime(commit.getCommitterIdent().getWhen());
        calendar.setTimeZone(commit.getCommitterIdent().getTimeZone());
        if((calendar.get(Calendar.HOUR_OF_DAY) > 12) && (calendar.get(Calendar.HOUR_OF_DAY) < 13)){
                metric.setDinnerCommits(metric.getDinnerCommits() + 1);
        }
        if(calendar.get(Calendar.HOUR_OF_DAY) < 4){
            metric.setNightCommits(metric.getNightCommits() + 1);
        }
    }

    /**
     * extract Metric Data from the Commit Message of the commit
     * @param commit the commit from which the metrics Data should be generated
     * @param metric the Metric of the associated user
     */
    public void getCommitMessageRelatedAchievement(RevCommit commit, Metric metric){
        try {
            String msg = commit.getFullMessage();
            for(String string_pattern : regexList){
                if(msg.matches(string_pattern)){
                    metric.setNumberOfCorrectCommitMessages(metric.getNumberOfCorrectCommitMessages() + 1);
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Initialize a users Achievements in the Main Application
     * @param user_email the email of the user which Achievemnts should be initialized
     */
    public void addUserByEmail(String user_email){
        System.out.println("User " + user_email + " has logged in for the first time and is being added to databases ...");
        UserAchievement userAchievement;
        List<Model> iList = new ArrayList<>();
        for(Achievement achievement: achievementList){
            userAchievement = new UserAchievement();
            userAchievement.setAchievementId(achievement.getId());
            userAchievement.setCollectorId(config.getCollectorId());
            userAchievement.setUserEmail(user_email);
            userAchievement.setProgress(0f);
            userAchievement.setLastUpdated(new Timestamp(System.currentTimeMillis()));
            iList.add(userAchievement);
        }
        System.out.println("... Sending User " + user_email + " to Main Application ...");
        httpService.sendList(iList, "http://devgame:8080/api/user-achievements");
        System.out.println("... User " + user_email + " has been succesfully send");
        System.out.println("Initializing Metric for " + user_email);
        Metric new_Metric = new Metric();
        new_Metric.setNightCommits(0);
        new_Metric.setNumberOfNewFiles(0);
        new_Metric.setNumberOfCorrectCommitMessages(0);
        new_Metric.setDinnerCommits(0);
        new_Metric.setUseremail(user_email);
        new_Metric.setNumberOfCommits(0);
        repository.save(new_Metric);
    }

    /**
     * Check IF the given List contains the User specified by his user_email
      * @param list The list containing Users that you wish to search through
     * @param user_email the email of the sought User
     * @return
     */
    public boolean checkUser(List<User> list, String user_email){
        for(User user : list){
            if(user.getEmail().equals(user_email)){
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the User is known to the Git Collector, THEN generate Data from his commit
     * ELSE Check if the User is known to the Main Application, THEN initilize his Data and generate Data from his commit
     * ElSE Igonre this commit
     * @param commit the commit from which data should be generated
     */
    public void checkParseUser(RevCommit commit){
        String user_email = commit.getCommitterIdent().getEmailAddress();
        if(checkUser(userList, user_email)){
            parseCommit(commit);
        }else{
            userList = httpService.getUsers();
            if(checkUser(userList, user_email)){
                addUserByEmail(user_email);
                parseCommit(commit);
            }else{
                System.out.println("User " + user_email + " is not recognized by Main Application and Commit "+ commit.getName() +" will be ignored");
            }
        }
    }

    /**
     * process an incoming commit
     * @param commit the commit to process
     */
    public void parseCommit(RevCommit commit){
        String user_email = commit.getCommitterIdent().getEmailAddress();
        try{
            if(repository.findByUseremail(user_email).size() == 1){
                Metric new_metric = repository.findByUseremail(user_email).get(0);
                new_metric.setUseremail(user_email);
                new_metric.setNumberOfCommits(new_metric.getNumberOfCommits() + 1);
                getTimeRelatedAchievement(commit, new_metric);
                getCommitMessageRelatedAchievement(commit, new_metric);
                getDiffs(commit, new_metric);
                repository.save(new_metric);
                updateAchievements(user_email);
            }
        }catch (Exception e){
            System.out.println("email doesnt exist in Collector");
            e.printStackTrace();
        }
        return;
    }

    //TODO: Exceptions richtig handeln, insbesondere leeres email repo <--> user existiert nicht exception vorher abfragen, entweder mit userlist oder länge von Repo

    /**
     * Get all eligible Commits and process them
     */
    public void iterateBranches(){

        try{
            List<Ref> call = git.branchList().setListMode(ListBranchCommand.ListMode.ALL).call();
            //TODO: Zeit Zonen Fehler
            Date latest_date_t = minDate;
            System.out.println(minDate);
            //TODO: warum gibt dass hier nicht alle User die sich eingeloggt haben?
            System.out.println("Alle User von httpService.getUsers(): ");
            for(User user : httpService.getUsers()){
                System.out.println(user.getEmail());
            }
            for(Ref ref : call)
            {
                System.out.println("\nBranch: " + ref.getName()+ "\n");
                String treeName = ref.getName(); // tag or branch
                for (RevCommit commit : git.log().add(git_repository.resolve(treeName)).call()) {
                    if(commit.getCommitterIdent().getWhen().after(minDate)) {
                        if(latest_date_t.before(commit.getCommitterIdent().getWhen())){
                            latest_date_t = commit.getCommitterIdent().getWhen();
                        }
                        if(!seenCommits.contains(commit.getName())){
                            seenCommits.add(commit.getName());
                            checkParseUser(commit);
                        }
                    }
                }
            }
            minDate = latest_date_t;
            //writeTimeStampToJSON();
        }catch (Exception e){
            e.printStackTrace();
        }
        }
    }

    //TODO: Persistence of current Head

interface LambdaInterface{
    void createDiff(String diff_string, Metric metric);
}
interface NameLambdaInterface{
    void createName(String name, Metric metric);
}



