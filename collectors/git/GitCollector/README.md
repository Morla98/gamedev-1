# GIT-Kollektor

Dieser Kollektor ist in der Lage Webhooks eines Git Repositories zu empfangen, Git Commits auszuwerten und in Errungenschaften innerhalb unserer Gamification Software zu überführen

## Setup
---

### Konfiguration

Die Konfigurationen erfolgt in dem Ordner `git/GitCollector/config/collectorConfiguration` :
#### Repository Konfiguration
 `git/GitCollector/config/collectorConfiguration/gitCredentials.json` :
```json
{
    "repoURL":"http://bitbucket:7990/scm/test/test.git",
    "adminName":"root",
    "adminPass":"adminroot"
}
```
* **repoURL** : Die URL des Repos aus dem man die Achievements generieren möchte
* **adminName** : Der Name eines Git Benutzers der in dem Repository Pull und Clone rechte hat
* **adminPass** : Das dazugehörige Passwort von `adminName`
  
#### Kollektor Konfiguration
`git/GitCollector/config/collectorConfiguration/gitTimestamp.json` :
```json
{
  "timestamp":"20-Dec-2019 10:30:50",
  "regex": "(feat|fix|chore|test)(.*):.*\\s",
  "timezone": "",
  "threshold": 10000
}
```
* **timestamp** : Der Startzeitpunkt ab dem Commits berücksichtigt werden sollen im Format dd-MONTH-YYYY HH-MM-SS
*  **regex** : (*nicht benutzt*)
*  **timezone** : Angabe der Zeitzone (*wird momentan nicht benutzt*)
*  **threshold** : gibt in millisekunden an, was der zeitliche mindest Abstand zwischen zwei PULL Befehlen des Kollektors sein darf
  
### Webhooks
Es muss noch ein Webhook in Ihrem Git-Repo eingerichtet, die auf die API des GitCollectors postet. Die Anwendung kann jedoch schon mit dem Befehl `docker-compose up --build` gestartet werden sofern die Hauptanwendung und Docker bereits laufen.
Je nach dem, ob Ihr Git Environment im gleichen Docker Netzwerk laufen wird oder nicht wird Ihr Link für den Webhook anders aussehen.
Läuft das Git Environment im gleichen Docker Netzwerk wie der GitCollector, können Sie als URL für den Webhook `http://GitCollector:8080/update` verwenden. Falls Sie den Container in der docker-compose.yml umbennen müssen Sie den entsprechenden Namen für den Container in der URL ersetzen. Das Gleiche gilt auch für den Docker-internen Port.
Läuft das Git Environment NICHT im gleichen Docker Netzwerk wie der GitCollector, müssen Sie als URL für den Webhook `http://IHRGATEWAY:9002/update` verwenden. Sollten Sie den externen Port in der `docker-compose.yml` verändert haben, gilt wie oben, dass dieser dementsprechend auch in der URL verändert wird. 
Falls Sie nicht wissen, wie Sie einen Webhook in ihr Git-Repository einfügen, können Sie in den entsprechenden Dokumentationen zu den Git Envoronments dies nachlesen :
1. [Dokumentation für BitBucket]("https://confluence.atlassian.com/bitbucket/manage-webhooks-735643732.html")
2. [Dokumentation für GitLab]("https://docs.gitlab.com/ee/user/project/integrations/webhooks.html")
3. [Dokumentation für GitHub]("https://developer.github.com/webhooks/creating/")

### Errungenschaften Konfiguration
Der Git-Kollektor hat seine eigene Datenbank im *Metric*`s Schema. Dort speichert er Informationen zu den Aktivitäten seiner Benutzer ab und wertet diese mithilfe der Logik der *Achievement*-Objekte aus. 
Alle folgenden generierten Achievements lesen immer nur eine Spalte der Datenbank aus und bestimmen dann daran den Fortschritt des Achievements.

#### Achievementgenerator
`git/GitCollector/config/achievements/config.json` :
```json
[
    {
        "description": "Push for the first time",
        "upperbound": "1.0",
        "name": "Hello Git",
        "value": 10,
        "command": "getNumberOfCommits"

    }
]
```

* **description**: Eine Beschreibung, die dem Nutzer deutlich macht, was er tun muss um das Achievement zu erhalten
* **uppperbound** : der Wert der angibt, wie hoch der Wert in der Datenbank sein muss, damit das Achievemetn als abgeschlossen gilt
*  **name** : der Name des Achievements
*  **value** : wie viele Punkte das Achievement geben soll
*  **command** : Name des Commands, mit dem man den aktuellen Wert des Fortschrittes des Achievements aus der Datenbank auslesen kann (*Command muss existieren und deshalb ggf manuell in der Metrics Klasse (`git/GitCollector/src/main/java/com/unihannover/gamedev/models/Metric`) hinzugefügt werden*)

#### Diff Achievements
`git/GitCollector/config/achievements/createDiffAchievement.json` :
```json
[
    {
        "command": "NumberOfComments",
        "regex": "//"
    }
]
```

* **command** : Name der Spalte in der das generierte Achievement schreiben und auslesen soll*Command muss existieren und deshalb ggf manuell in der Metrics Klasse (`git/GitCollector/src/main/java/com/unihannover/gamedev/models/Metric`) hinzugefügt werden*)
* **regex** : Die zugehörige Spalte in der Datenbank wird für jedes Vorkommnis von *regex* in dem Diff des Commits um eins inkrementiert, dabei wird jedes Zeichen aus dem Diff nur einmal berücksichtigt, also bei regex: "//" und Diff: "////" würde die Spalte nur um zwei inkrementiert werden
  
#### Commit Nachricht
`git/GitCollector/config/collectorConfiguration/legalRegex.json` :
```json
[   
    {
        "regex" : "(feat|fix|chore|test)(.*):.*\\s"
    }
]
```
* **regex** : Regulärer Ausdruck, der den Richtlinien entsprechende Commit Nachrichten spezifiziert