# M223 Punchclock - Nils Rothenbühler

Punchclock ist eine Applikation mit der man Zeiteinträge erstellen, bearbeiten oder löschen kann.
Man kann sich als Benutzer registrieren und anschliessend auch anmelden. Dann gelangt man auf die
Seite auf der man die Zeiteinträge verwalten kann. Auf der index.html Seite kann man sich auch noch abmelden. 

1. Benutzer registrieren -> http://localhost:8080/signup.html
2. Mit Benutzername und Passwort anmelden -> http://localhost:8080/login.html
3. Nach dem erfolgreichen Anmelden kommt man auf die Seite index.html und kann Zeiteinträge erstellen/bearbeiten/löschen

Es werden ein paar Testdaten zu Benutzer, Geschlacht und Kategorie erstellt.
Die Daten sollten beim starten in die Datenbank geladen werden.
Testdaten sind in dem der Klasse Startup.java -> Pfad: src/main/ch/zli/m233/punchclock/util/Startup.java

Folgende Schritte sind notwendig um die Applikation zu erstellen und zu starten: 
1. Stellen Sie sicher, dass OpenJDK 11 oder höher installiert und JAVA_HOME korrekt gesetzt ist.  
2. Installieren Sie (falls noch nicht vorhanden) Apache Maven 3.8.1 oder höher
3. Wechseln Sie auf der Kommandozeile in den Ordner dieser Applikation. 
`cd m223-punchclock-quarkus/`
4. Starten Sie die Applikation mit 
```shell script
./mvnw compile quarkus:dev
```

Folgende Dienste stehen während der Ausführung im Profil dev zur Verfügung:

Swagger API: http://localhost:8080/q/swagger-ui/

H2 Console: http://localhost:8080/h2/ 
Datenquelle: jdbc:h2:mem:punchclock
Benutzername: zli
Passwort: zli

