# Jira-Kollektor

Dieser Kollektor ist in der Lage, WebHooks einer Atlassian Jira-Instanz zu empfangen, auszuwerten und in Errungenschaften innerhalb unserer Gamification-Software zu überführen.

## Setup

### Konfiguration

Die Konfiguration erfolgt in der Datei `jira-collector/config/configuration.json` :

```json
{
  "name" : "Jira",
  "collectorId" : "jira",
  "token" : "",
  "mainApiUrl" : "http://devgame:8080/api",
  "jwtSecret": "GamerControlDINGS",
  "jiraIssueTypeIdEpic": "10000",
  "jiraIssueTypeIdStory": "10001",
  "jiraIssueTypeIdSubtask": "10003"
}
```

 * **name** : Innerhalb der Hauptanwendung angezeigter Name des Kollektors; kann unverändert übernommen werden
 * **collectorId** : Interne Datenbank-ID des Kollektors; ab besten unverändert lassen
 * **token** : Bei der Registrierung an der Hauptanwendung wird dem Kollektor ein Token zugewiesen und hier abgespeichert; dieses Feld kann daher ignoriert werden
 * **mainApiUrl** : Hier die URL zur API der Hauptanwendung anpassen.
 * * Wird der Kollektor in der von uns bereitgestellten Weise betrieben (innerhalb des internen Docker-Netzwerkes `devgame`), braucht dieser Eintrag nichtgeändert zu werden.
 * **jwtSecret** : Das JwtSecret der Hauptanwendung für Kollektoren (wird für die initiale Anmeldung benötigz)
   * aus der Konfiguration der Hauptanwendung (`/main/Backend/src/main/resources/application.properties`, Feld `app.jwtCollectorSecret`) übernehmen
 * **jiraIssueTypeIdEpic** : Die Jira-Interne (Instanz-spezifische) ID eines Epics
 * **jiraIssueTypeIdStory** : Die Jira-Interne (Instanz-spezifische) ID einer Story
 * **jiraIssueTypeIdSubtask** : Die Jira-Interne (Instanz-spezifische) ID einer Unteraufgabe

### Starten

```shell
docker-compose -f docker-compose.yml up --build --detach
```

#### Starten mit Entwicklungs-Umgebung

Startet zusätzlich eine Jira-Instanz.

```shell
docker-compose -f docker-compose.yml -f docker-compose.dev.yml up --build --detach
```

## Konfiguration von Jira

Um das Verwenden von WebHooks aus Jira an den Kollektor zu konfigurieren, gehen Sie folgendermaßen vor:

 1. Navigieren Sie als Administrator in Jira in die System-Einstellungen (oben rechts auf das Zahnrad, dann `System`)
 2. Navigieren Sie in die WebHook-Einstellungen (Links in der Liste unter `Advanced` auf `WebHooks`)
 3. Beginnen Sie mit `+ Create a WebHook` (oben rechts) den Dialog zum Erstellen eines neuen WebHooks
   * Tragen Sie unter `Name` einen beliebigen Namend ein
   * `Status` muss auf `Enabled` stehen
   * `URL` anpassen: 
     * Wird die vorkonfigurierte Entwicklungs-Umgebung verwendet, so geben Sie dort folgendes ein: `http://jira-collector:8080/update`
     * Andernfalls `http[s]://<ip-des-hosts>:<port-des-kollektors>/update`
   * Unter `Events` müssen mindestens alle Checkboxen unterhalb von `Comment` und `Issue` angehakt werden
   * Die weiteren Einstellungen können belassen werden
   * Mit Klick auf `Create` abschließen

