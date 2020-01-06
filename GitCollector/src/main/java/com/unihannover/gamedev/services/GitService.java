import org.springframework.beans.factory.annotation.Autowired;
public class GitService {
    //TODO: entweder lastCommitDate oder int benutzen
    private int lastCommitDate;
    //TODO: alle benötigten Daten aus CollectorService nicht durch Funktionsuafrufe bekommen, entweder neu initialisiern oder GitService von CollectorSerivce erben lassen
    private List<Achievement> achievementList;
    private Date minDate;
    //private
    public GitService(Repository repository, Git git)
        this.git_repository = repository;

            Reader reader = new FileReader("config/collectorConfiguration/gitTimestamp.json");
            lastCommitDate = ((Long) jsonObject.get("timestamp")).intValue();
    public void writeTimestamptoJSON(int timestamp)
        time.put("timestamp", timestamp);
            FileWriter file = new FileWriter("config/collectorConfiguration/gitTimestamp.json");
    public void runTimer(CredentialsProvider user, MetricRepository repository,HttpService httpService, List<Achievement> achievementList1)
        achievementList = achievementList1;

                        iterateBranches(repository, httpService);
    public void updateAchievements(String user_email, HttpService httpService){
    public Metric getDiffRelatedAchievements(String diff, Metric m){
                m.setNumberOfNewFiles(m.getNumberOfNewFiles() + 1);
                return m;
        return m;
    //TODO Diff quantisieren und in Metrics abspeichern + passende Metric Spalte anlegen
    public Metric getDiffs(RevCommit commit, Metric m){
                    break;
                FileInputStream fin = new FileInputStream(new File("fout.txt"));
                java.util.Scanner scanner = new java.util.Scanner(fin,"UTF-8").useDelimiter("/n");
                String theString = scanner.hasNext() ? scanner.next() : "";
                System.out.println("/n/n/n COMMIT STRING:");
                System.out.println(theString);
                scanner.close();
                return getDiffRelatedAchievements(theString, m);
        return m;
    public Metric getTimeRelatedAchievement(RevCommit commit, Metric m){
        System.out.println("HOUR OF DAY: " + calendar.get(Calendar.HOUR_OF_DAY));
                m.setDinnerCommits(m.getDinnerCommits() + 1);
            m.setNightCommits(m.getNightCommits() + 1);
        return m;
    public Metric getCommitMessageRelatedAchievement(RevCommit commit, Metric m){
            String string_pattern = "(feat|fix|chore|test)(.*):.*\\s";//"(feat|fix|chore|test)(.+)(:)(.+)";
            System.out.println("/n/nMessage:" + msg + "     HERE COMES THE MATCH: " + msg.matches(string_pattern));
            //Pattern pattern = Pattern.compile(string_pattern);
            if (msg.matches(string_pattern)) {
                m.setNumberOfCorrectCommitMessages(m.getNumberOfCorrectCommitMessages() + 1);
                return m;
        return m;
    public void parseCommit(RevCommit commit, MetricRepository repository,HttpService httpService){
        //String commit_message = commit.getFullMessage();
            if(repository.findByUseremail(user_email).get(0) != null){
                System.out.println("Committer_Email:|" + user_email +"| Commit time: " + commit.getCommitTime() + "; Commit get When(): " + commit.getCommitterIdent().getWhen().getHours());
                Metric m = repository.findByUseremail(user_email).get(0);
                Metric new_metric = m;
                new_metric.setNumberOfCommits(m.getNumberOfCommits() + 1);
                new_metric = getTimeRelatedAchievement(commit, new_metric);
                new_metric = getCommitMessageRelatedAchievement(commit, new_metric);
 //               new_metric.setNumberOfCorrectCommitMessages(m.getNumberOfCorrectCommitMessages() + getCommitMessageRelatedAchievement(commit, new_metric));
                new_metric = getDiffs(commit, new_metric);
                updateAchievements(user_email, httpService);

    //TODO: eher getWhen() anstatt getCommitTime benutzen
    public void iterateBranches(MetricRepository repository, HttpService httpService){
            int latest_date = lastCommitDate;
                    if(commit.getCommitTime() > lastCommitDate) {
                        if(latest_date < commit.getCommitTime()){
                            latest_date = commit.getCommitTime();
                            parseCommit(commit, repository, httpService);
            lastCommitDate = latest_date;
            writeTimestamptoJSON(lastCommitDate);

    //TODO: Generate achievements from the data
    //TODO: Update Achievements