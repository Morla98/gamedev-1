# GameDev-1

## Setup
### Anforderungen

 * Docker CE/EE
 * `docker-compose`
 * ggf. `git`

### Starten der Anwendung

```shell
docker-compose up --build
```

### Beenden der Anwendung

```shell
docker-compose down
```

### Fehlerbehebung

Sollte mal etwas hartnäckig nicht klappen, dann komplett aufräumen:
```shell
docker-compose down
docker volume prune
docker container prune 
```

und starten mit
```shell
docker-compose up --build --remove-orphans --force-recreate
```

## Weboberfläche

 * [http://localhost:8082](http://localhost:8082)
   * Benutzer `admin`
   * Passwort `gameDev`
 * Swagger (API-GUI): [http://localhost:8082/swagger-ui.html](http://localhost:8082/swagger-ui.html)

## Datenbank

Postgres-Datenbank unter Host `db`.

Zugangsdaten: `root`:`root` .

### pgAdmin

Grafische Benutzeroberfläche mit vorkonfigurierter Verbindung zum Server:
 * [http://localhost:8001](http://localhost:8001)
   * Benutzer `admin`
   * Passwort `admin`

## LDAP

### Gruppen und Benutzer

(Aktuell) werden folgende (POSIX-)Benutzer angelegt:

| Username (`uid` / `cn`) | Passwort | Email |
| --- | --- | --- |
| `admin` | `admin` |
| `developer1` | `developer1` | `dev1@example.com` |
| `developer2` | `developer2` | `dev2@example.com` |

Folgende (POSIX-)Gruppen werden angelegt:

| `cn` | `gid` |
| --- | --- |
| `developers` | 500 |

Die Benutzer `developer1` und `developer2` sind Mitglied der Gruppe `developers` .

### phpLDAPadmin

Erreichbar unter [http://192.168.60.101:8002](http://192.168.60.101:8002).

Benutzername: `cn=admin,dc=example,dc=org`, Passwort: `admin`
