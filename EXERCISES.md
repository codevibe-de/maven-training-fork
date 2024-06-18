# Maven Grundlagen - Übungen

**Hinweis:** Für die meisten Übungen ab Kapitel 040 existieren bereits Verzeichnisse, in denen
Dateien und/oder
Verzeichnisse zur leichteren Ausführung der Übung für Sie vorbereitet wurden. Diese Verzeichnisse
folgen dem Nummerierungsschema (z.B. "040.b__junit-test")

## 010 - Einführung

### a) IDE Import

Importieren Sie dieses Projekt in Ihre IDE.

- Intellij IDEA:
    * Menü: File > New > Project from Existing Sources...
    * Verzeichnis "maven-training" selektieren >> OK
    * New Window
- Eclipse:
    * Menü: File > Import
    * "General / Existing Projects into Workspace" selektieren >> Next
    * Verzeichnis **oberhalb** von "maven-training" selektieren
    * "maven-training" Eintrag selektieren >> Finish

### b) Minimale pom.xml

1. Erstellen Sie sich im Projekt ein Arbeitsverzeichnis mit Namen "exercise" (auf gleicher Ebene wie
   diese Datei)
2. Erstellen Sie (mit einem einfachen Editor wie z.B. Notepad oder Sublime) eine `pom.xml`,
   welche nur die minimal benötigten Inhalte enthält (Google ist Ihr Freund)
3. Probieren Sie aus, verschiedene Plugins oder Phasen auszuführen

**Hinweis:** Das soeben erstellte "exercise" Verzeichnis kann nun in Ihrer IDE als dediziertes Maven-Projekt
eingelesen werden -- und als Maven-unterstütztes Projekt Ihnen weitere Dienste leisten:

- Intellij IDEA:
    * Menü: File > New > Module from Existing Sources...
    * Verzeichnis "exercise" selektieren >> OK
    * "Import project from external model" selektieren, "Maven" selektieren >> Create
    * das Unterverzeichnis "exercise" wird nun anders dargestellt
- Eclipse:
    * Menü: File > Import
    * "Maven / Existing Maven Projects" selektieren >> Next
    * Verzeichnis "maven-training" selektieren
    * Deselect All
    * "/exercise/pom.xml" Eintrag selektieren >> Finish
    * das Unterverzeichnis "exercise" wird nun anders dargestellt (im "Project Explorer") bzw. top-level eingebunden
      (im "Package Explorer")

### c) Mini Projekt

1. Erstellen Sie im "exercise" Verzeichnis die per Maven Konvention erwarteten Unterverzeichnisse:
    - `/src/main/java`
    - `/src/main/resources`
2. Legen Sie im entsprechenden Verzeichnis eine Java Klasse an (mit oder ohne Package).
   Diese soll eine `main()` Methode enthalten.
3. Legen Sie im entsprechenden Verzeichnis eine zusätzliche Ressource an, z.B. eine (leere)
   `data.csv` Datei.
4. Lassen Sie Maven die Anwendung kompilieren (auf der Kommandozeile).
    - Ggf. gibt es hier eine Fehlermeldung wie z.B. `"Source option 5 is no longer supported. Use 6
      or later."` -- hierfür können Sie online nach einer Lösung suchen und benutzen, Sie müssen diese noch
      nicht verstehen :)
5. Schauen Sie sich den Output von Maven an (`target` Verzeichnis).
6. Was passiert dort außerdem noch, wenn Sie die Anwendung paketieren (`mvn package`) lassen?

## 020 - Project Object Model

### a) Packaging "jar"

1. Legen Sie das Packaging in Ihrer POM als `jar` fest
2. Ändert sich etwas an dem Output im `target` Verzeichnis, wenn Sie `mvn package` aufrufen?

### b) Packaging "pom"

1. Ändern Sie das Packaging zu `pom` und erhöhen Sie die Version in Ihrer POM
2. Rufen Sie nun `mvn install` auf -- damit wird das Output-Artefakt in Ihr lokales Repository
   installiert
3. Schauen Sie in Ihrem lokalen Repository nach, welche Dateien dort installiert wurden
    * Tipp: Im Home-Verzeichnis unter ".m2/repository"
    * Tipp: Ihre "groupId" bestimmt den Ablageort

### c) Demo-Libs

Installieren Sie mithilfe von Maven (`mvn install`) in Ihr lokales Maven-Repository die Bibliotheken aus den
Verzeichnissen
- `/000__demo-lib-A`
- `/000__demo-lib-B`

Sinn der Übung ist, dass diese Bibliotheken später zur Verwendung als Dependencies parat stehen.

### d) Parent-POM

Legen Sie ein Verzeichnis `/parent-pom-artifact` im Projekt (auf gleicher Ebene wie `/exercise`) an.

Dort wollen wir eine eigene Parent-POM definieren. Diese soll eine Parent-POM für Ihre POM
im `/exercise` Projekt sein.

In der Parent-POM können Sie z.B. die Java Version, das Encoding und ggf. sogar schon
Versionsnummern von Plugins definieren.

Nun installieren Sie die Parent-POM mittels `mvn install` in Ihr lokales Repository.

Ab jetzt kann dieser Parent in anderen Projekten genutzt werden (GAV-Koordinaten entsprechend
anpassen):

````xml

<parent>
    <groupId>de.codevibe.maven-training</groupId>
    <artifactId>maven-training-parent-pom</artifactId>
    <version>1.0</version>
</parent>
````

Probieren Sie die Nutzung der Parent-POM in Ihrem `/exercise` Projekt aus.

## 030 - Properties

Keine Übung

## 040 - Dependencies und Management

### a) Projekt mit Abhängigkeiten

ACHTUNG -- für diese Übung muss vorher "020.c" bearbeitet worden sein (Installation der Demo-Libs)!

Diese Übung können Sie im Verzeichnis `/040.a__projekt-mit-dependencies` durchführen, wo eine
leere `pom.xml` vorbereitet ist.

1. Fügen Sie dem Maven Projekt Abhängigkeiten auf `org.apache.commons:commons-lang3:3.11`
   und `org.junit.jupiter:junit-jupiter-api:5.9.0` (Junit5) hinzu
2. Stellen Sie die Abhängigkeiten mithilfe des "dependency" Plugins dar (als Liste und als Baum)
3. Erweitern Sie das Projekt um Abhängigkeiten auf `demo-lib-A` und `demo-lib-B`. Welche transitiven
   Abhängigkeiten kommen dazu? In welcher Version? Und welche nicht?
4. Entfernen Sie die `commons-lang3` Dependency aus ihrer POM -- was ändert sich nun bezüglich der
   transitiven Abhängigkeiten?
5. Lassen Sie sich mögliche Upgrades anzeigen
6. Führen Sie ein Upgrade der Dependencies aus
7. Ändern Sie die `pom.xml`, sodass die transitive Abhängigkeit `commons-io` von demo-lib-A nicht
   mit in das Projekt aufgenommen wird

### b) Testfall (optional)

Diese Übung können Sie im Verzeichnis `/040.b__junit-test` durchführen, wo bereits etwas vorbereitet
ist.

1. Erstellen Sie einen leeren Testfall in der Klasse `RandomStringGeneratorTest`
2. Fügen Sie noch die Dependency "junit-jupiter-engine" analog der "junit-jupiter-api" Dependency
   hinzu. Wie können Sie in Ihrer POM eine Wiederholung der gleichen Versionsnummer vermeiden?
3. Rufen Sie Maven so auf, dass Tests von Maven angestoßen werden. Wird ein Test
   tatsächlich ausgeführt? Dies kann bei älteren Maven Versionen nicht der Fall sein!
4. Für ältere Maven-Version muss der Surefire Plugin auf eine höhere Version gehoben werden (es
   braucht mindestens 2.22.0) -- also ggf. folgenden Block einfügen:
   ````xml
    <build>
        <plugins>
            <!-- JUnit 5 requires Surefire version 2.22.0 or higher -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>2.22.0</version>
            </plugin>
        </plugins>
    </build>
    ````
5. Führt Maven den Test nun aus? Dies sollte so sein ...
6. Implementieren Sie in dem Test nun einen Aufruf an die Methode
   `com.example.generators.RandomStringGenerator#generateRandomString`. Diese Klasse finden Sie in
   der Demo-Lib-A. Der Test soll sicherstellen, dass das Ergebnis die richtige Länge hat.
7. Was wäre ein passender Scope für die JUnit5 Dependency? Welchen Scope kann oder sollte die
   Demo-Lib-A Dependency haben?

### c) Bill-Of-Materials in Parent-POM

Fügen Sie der Parent-POM in `parent-pom-artifact` einen `<dependencyManagement>` Block hinzu, in dem
Sie eine Bibliothek Ihrer Wahl definieren.

Dazu sollten Sie dann natürlich auch die Version des Parents erhöhen (da Änderung) und erneut
installieren.

Nutzen Sie dann die neue Parent-POM und speziell diese Bibliothek in einem Projekt Ihrer Wahl
(z.B. `/exercise`).

## 050 - Plugins

### a) Der "exec" Plugin

Diese Übung können Sie im Verzeichnis `/050.a__exec-plugin` durchführen, wo bereits etwas
vorbereitet
ist.

1. Erstellen Sie eine Klasse mit einer `main()` Methode in Ihrem Projekt, die eine Ausgabe
   auf `System.out` macht
2. Schauen Sie sich den "Exec Maven Plugin" an, insbesondere das "exec:java" Goal. Welche Parameter
   benötigt dieses Goal zur Ausführung?
    * https://www.mojohaus.org/exec-maven-plugin/java-mojo.html
3. Führen Sie den Plugin per Kommandozeile aus, sodass Ihre Main-Klasse von Maven aus gestartet wird
    * Tipp: `mvn <plugin-group-id>:<plugin-artifact-id>[:<plugin-version>]:<goal>`
    * Tipp: Properties können von der Kommandozeile mit `mvn -D"propertyName=propertyWert"` gesetzt
      werden (insbesondere bei dieser Übung sind die Anführungsstriche wichtig)
4. Fügen Sie den Plugin nun im `<build><plugins>` Block Ihrer POM hinzu. Mittels Kommandozeile
   führen Sie jetzt das Ziel "java" des Plugins aus: `mvn exec:java`. Wofür genau steht "exec" an dieser Stelle?
   Funktioniert es?
5. Ergänzen Sie das Kommando um den notwendigen "mainClass" Parameter
6. Hinterlegen Sie den "mainClass" Parameter im `<configuration>` Block des Plugins
7. Binden Sie den Plugin an die "verify" Phase und führen Sie diese aus.

### b) Core Plugin Versionen festlegen

1. Legen Sie eine minimale neue pom.xml an
2. Erzeugen Sie mittels des "help" Plugins die effektive POM als `effective-pom.xml` Datei
3. Mit welcher Version wird z.B. der "maven-compiler-plugin" deklariert?
4. Suchen Sie in einem Online Repository nach der neusten Version des Plugins und deklarieren Sie
   diese als
   aktuelle Version in Ihrer POM.
    * Tipp: Der Wert "org.apache.maven.plugins" ist als Plugin-Group automatisch gesetzt (kann in
      settings.xml
      geändert werden), somit kann dieser für Core Plugins entfallen
5. Erzeugen Sie erneut mittels des "help" Plugins die effektive POM, diesmal
   als `effective-pom-neu.xml` Datei.
   Vergleichen Sie beide Dateien z.B. mittels eines Diff-Tools

### c) Buildnumber

Der "buildnumber-maven-plugin" (https://www.mojohaus.org/buildnumber-maven-plugin/)
kann eine automatische Build-Nummer generieren. Diese wird als Property `${buildNumber}` hinterlegt.

Für diese Übung arbeiten Sie in der Datei `050.c__buildnumber/pom.xml`.

Definieren Sie dort diesen Plugin in dem `<plugins>` Block (innerhalb von `<build>`), um
ihn dem Build-Prozess bekannt zu machen und binden Sie den Plugin an die "initialize" Phase (Achtung,
entgegen der Dokumentation scheint der Plugin sich nicht selbst an diese Phase zu binden).

Lassen Sie sich mithilfe des "help" Plugins den Wert des
Platzhalters ausgeben. Dies geht auf Basis des `help:evaluate` Goals und Angabe einer
Expression: `-Dexpression=buildNumber`:

````shell
mvn validate help:evaluate -Dexpression=buildNumber
````

## 055 - Profile

### a) Reihenfolge

1. Definieren Sie mindestens ein Profil in Ihrer `<USER-HOME>/.m2/settings.xml`, welches
   ein Property namens `foo` setzt
2. Definieren Sie zwei weitere Profile in Ihrer POM, die ebenfalls dieses Property setzen
3. Lassen Sie sich das Property `foo` auflösen: `mvn help:evaluate -Dexpression=foo -P <profile>`
   unter
   Setzen verschiedener Profile.
4. Lassen Sie sich die Reihenfolge der aktiven Profile
   anzeigen: `mvn help:active-profiles -P <profile>`

## 060 - Archetypes

1. Wechseln Sie in ein temporäres Verzeichnis, z.B. `C:\Temp`
2. Lassen Sie sich dort ein neues Maven Projekt mithilfe des `archetype:generate` Kommandos erzeugen

## 070 - Multi-Module

1. Beginnen Sie in einem leeren Arbeitsverzeichnis mit der Erstellung eines Multi-Module-Projekts
2. Hierzu benötigen Sie zuerst eine top-level POM mit Packaging "pom" und einem `<modules>` Block
3. Legen Sie zwei Unterverzeichnisse für die Module an - jeweils mit einer eigenen `pom.xml`.
   Die GroupId sollte die gleiche wie in der top-level POM sein. Denken Sie an die Deklaration der
   Parent-POM.
4. Vergessen Sie nicht, die Module in der Parent-POM zu hinterlegen (`<module>` Elemente) mit Namen
   der angelegten Modul-Verzeichnisse
5. Führen Sie den Build für alle oder für ein Modul aus

## 080 - Sonstiges

### Wrapper

Generieren Sie den Maven-Wrapper im Verzeichnis `/080.wrapper`:

````shell
mvn wrapper:wrapper
````

Schauen Sie sich den Inhalt der Skripte und des generierten `/.mvn` Verzeichnisses an.

Führen Sie einen (leeren) Build mittels Wrapper aus:

````shell
mvn clean compile
````
