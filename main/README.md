# Hauptanwendung

### Konfiguration

Die Konfiguration der Hauptanwendung geschieht in zwei Dateien:

 1. `main/Backend/config/ldapConfig.json` : Konfiguration des LDAP-Servers
```json
{
  "LDAP_URL": "ldap://ldap-server:389",  # Protokoll, Host und Port zum LDAP-Server; ggf. hier ldaps verwenden
  "LDAP_SERVICE_USER_DN": "cn=admin,dc=example,dc=org",  # DN für den Service-Benutzer
  "LDAP_SERVICE_USER_PW": "admin",  # Passwort des Service-Benutzers
  "LDAP_BASE": "dc=example,dc=org",  # Basis für die Suche (nach Nutzern beim Login)
  "LDAP_SEARCH_FILTER": "(&(objectClass=posixAccount)(mail=#EMAIL)(gidNumber=500))"  # Frei konfigurierbarer Such-Filter. `#EMAIL` wird beim Login durch die vom Benutzer angegebene Email ersetzt.
}
```
 2. `main/Backend/src/main/resources/application.properties` : Konfiguration der Sicherheits-Tokens
    * `app.jwtSecret` : JWT-Secret der Haupt-Anwendung selbst, hier einen zufälligen Wert wählen
    * `app.jwtCollectorSecret` : Shared JWT-Secret für die initiale Anmeldung für Kollektoren (dieser muss in der Kollektor-Konfiruation jeweils identisch angegeben werden). Einen zufälligen Wert wählen.
    * Die anderen Optionen können belassen werden

### Starten

```shell
docker-compose -f docker-compose.yml up -d
```

#### Starten mit Entwicklungs-Umgebung

```shell
docker-compose -f docker-compose.yml -f docker-compose.dev.yml up -d
```

## Entwicklungs-Umgebung

### Weboberfläche

 * [http://localhost:8080](http://localhost:8080)
 * Swagger (API-GUI): [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### Datenbank

Postgres-Datenbank unter Host `db`.

Zugangsdaten: `root`:`root` .
Die Datenbank sollte aufgrund der schwachen Zugangsdaten niemals von außen erreichbar sein, sondern nur im Docker-internen Netzwerk (`devgame`) verwendet werden.

#### pgAdmin

Grafische Datenbank-Benutzeroberfläche (mit vorkonfigurierter Verbindung zum Server)
 * [http://localhost:8001](http://localhost:8001)
   * Benutzer `admin`
   * Passwort `admin`

### LDAP

#### Vorkonfigurierte Gruppen und Benutzer

Aktuell werden folgende (POSIX-)Benutzer angelegt:

| Username (`uid` / `cn`) | Passwort | Email |
| --- | --- | --- |
| `admin` | `admin` |
| `developer1` | `developer1` | `dev1@example.com` |
| `developer2` | `developer2` | `dev2@example.com` |
| `developer3` | `developer3` | `dev3@example.com` |
| `abc` | `abc` | `abc@gmx.de` |

Folgende (POSIX-)Gruppen werden angelegt:

| `cn` | `gid` |
| --- | --- |
| `developers` | 500 |
| `non-developers` | 501 |

Die Benutzer `developer1`, `developer2` und `abc@gmx.de` sind Mitglied der Gruppe `developers` .
`developer3` ist Mitglied der Gruppe `non-developers` .

#### phpLDAPadmin

Erreichbar unter [http://localhost:8002](http://localhost:8002).

Benutzername: `cn=admin,dc=example,dc=org`, Passwort: `admin`

#### Manuelles `ldapsearch`

Die Filter in phpLDAPadmin sind kaputt (`&` wird fälschlicherweise gequoted), daher:

```shell
docker-compose -f docker-compose.yml -f docker-compose.dev.yml exec ldap-server bash
# Startet eine Bash-Shell im OpenLDAP-Container
ldapsearch -D "cn=admin,dc=example,dc=org" -w admin -p 389 -h localhost -b "dc=example,dc=org" -s sub "(&(gidNumber=500)(objectClass=posixAccount)(mail=*))"
```
