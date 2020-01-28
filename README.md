# GameDev 1

Gamedev versucht sich an der gamifizierung des Arbeitsalltags von Software Entwicklern. Dabei werden Achievements aus Software Entwicklungstools wie Git und Jira generiert, um die Arbeit langfristig motivierender zu gestalten. 

### Terminologie und Aufbau

 * **Hauptanwendung**: Die Hauptanwendung hostet die Webseite und stellt die Rest-API zur Verfügung. Sie überprüft ob sich die User in dem angebunden LDAP befinden und legt diese bei Erstanmeldung in dem `main` Datenbbank schema an. In dem `main` schema werden alle angemeldeten User mit ihren Achievements verwaltet, sowie auch die angemeldeten Kollektoren. 

 * **Kollektoren** sind eigenständige, von der Hauptanwendung abgekoppelte Systeme, welche die genutzten Entwicklungstools auslesen und entsprechend auswerten. Jeder Kollektor hat in dem `metrics` Schema seine eigene Datenbank, in der er informationen für seine eigenen Achievements abspeichert. Da der Kollektor selbstständig arbeitet und sein eigenes Datenbank schema hat, ist er auch selber dafür zuständig die Achievements der einzelnen User an die Hauptanwendung zu übermitteln.

### Anforderungen

Um diese Software betreiben zu können, müssen folgende Voraussetzungen erfüllt werden:

 * Betriebssystem prinzipiell beliebig; vorzugsweise UNIX-artig.
 * Installiert sind `docker` und `docker-compose` (letzteres in einer Version, die Compose-Dateien mit `version: '3.7'` oder höher unterstützt).
 * Eine Internet-Anbindung ist verfügbar

### Starten der Anwendung(en)

 * Die Hauptanwendung und die Kollektoren werden in voneinander unabhängigen Docker-Containern gestartet.
 * In den jeweiligen Ordnern (`main` / `collectors/*`) befinden sich weitere Anweisungen, wie diese Dienste jeweils zu konfigurieren sind und (ggf. mit zusätzlicher Entwicklungs-Umgebung) gestartet werden können.
