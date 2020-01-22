# GameDev 1

_TODO_ : Kurze Projekt-Beschreibung

### Terminologie und Aufbau

 * **Hauptanwendung** _TODO_

 * **Kollektoren** _TODO_

### Anforderungen

Um diese Software betreiben zu können, müssen folgende Voraussetzungen erfüllt werden:

 * Betriebssystem prinzipiell beliebig; vorzugsweise UNIX-artig.
 * Installiert sind `docker` und `docker-compose` (letzteres in einer Version, die Compose-Dateien mit `version: '3.7'` oder höher unterstützt).
 * Eine Internet-Anbindung ist verfügbar

### Starten der Anwendung(en)

 * Die Hauptanwendung und die Kollektoren werden in voneinander unabhängigen Docker-Containern gestartet.
 * In den jeweiligen Ordnern (`main` / `collectors/*`) befinden sich weitere Anweisungen, wie diese Dienste jeweils zu konfigurieren sind und (ggf. mit zusätzlicher Entwicklungs-Umgebung) gestartet werden können.
