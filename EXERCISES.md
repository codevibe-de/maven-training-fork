# Maven Grundlagen - Übungen

## 010 - Einführung

### a) Minimale pom.xml

1. Erstellen Sie sich im Projekt ein Arbeitsverzeichnis mit Namen "exercise" (auf gleicher Ebene wie
   diese Datei)
2. Erstellen Sie eine `pom.xml`, welche nur die minimal benötigten Inhalte enthält (Google ist Ihr Freund)
3. Probieren Sie aus, verschiedene Plugins oder Phasen auszuführen

### b) Mini Projekt

1. Erstellen Sie die per Maven Konvention erwarteten Verzeichnisse für den Quellcode und zusätzliche Ressourcen
2. Legen Sie im entsprechenden Verzeichnis eine Java Klasse an (ohne Package).
   Diese soll eine `main()` Methode enthalten.
3. Legen Sie im entsprechenden Verzeichnis eine zusätzliche Ressource an, z.B. eine (leere) data.csv Datei.
4. Lassen Sie Maven die Anwendung kompilieren.
    - Ggf. gibt es hier eine Fehlermeldung wie z.B. "Source option 5 is no longer supported. Use 6 or later."
      -- hierfür können Sie online nach einer Lösung suchen und benutzen, Sie müssen diese noch nicht verstehen :)
5. Schauen Sie sich den Output von Maven an (`target` Verzeichnis).
6. Was passiert dort außerdem noch, wenn Sie die Anwendung paketieren lassen?

## 020 - Project Object Model

### a) Packaging "jar"

1. Legen Sie das Packaging in Ihrer POM als `jar` fest -- ändert sich etwas?
2. Was passiert, wenn Sie `mvn install` aufrufen?

### b) Packaging "pom"

1. Ändern Sie das Packaging zu `pom` und erhöhen Sie die Version in Ihrer POM
2. Rufen Sie erneut `mvn install` auf
3. Schauen Sie in Ihrem lokalen Repository nach, welche Dateien dort installiert wurden
    * Tipp: Im Home-Verzeichnis unter ".m2/repository"
    * Tipp: Ihre "groupId" bestimmt den Ablageort

### c) Demo-Libs

1. Installieren Sie mithilfe von Maven in Ihr lokales Maven-Repository die Bibliotheken aus den Verzeichnissen
    - `/000__demo-lib-A`
    - `/000__demo-lib-B`

## 030 - Properties

Keine Übung

## 040 - Dependencies und Management

### a) Projekt mit Abhängigkeiten

ACHTUNG -- für diese Übung muss vorher "020.c" bearbeitet worden sein (Installation der Demo-Libs)!

1. Legen Sie ein Maven Projekt mit Abhängigkeiten auf `org.apache.commons:commons-lang3:3.11`
   und `org.junit.jupiter:junit-jupiter-api:5.9.0` (Junit5) an
2. Stellen Sie die Abhängigkeiten mithilfe des "dependency" Plugins dar (als Liste und als Baum)
3. Erweitern Sie das Projekt um Abhängigkeiten auf `demo-lib-A` und `demo-lib-B`. Welche transitiven
   Abhängigkeiten kommen dazu? In welcher Version? Und welche nicht?
4. Entfernen Sie die `commons-lang3` Dependency aus ihrer POM -- was ändert sich nun bzgl. der transitiven
   Abhängigkeiten?
5. Lassen Sie sich mögliche Upgrades anzeigen
6. Führen Sie ein Upgrade der Dependencies aus
7. Ändern Sie die `pom.xml`, sodass die transitive Abhängigkeit `commons-io` von demo-lib-A nicht mit in
   das Projekt aufgenommen wird

### b) Testfall (optional)

1. Erstellen Sie einen leeren Testfall in der Klasse `RandomStringGeneratorTest`
   und rufen Sie Maven so auf, dass Tests von Maven angestoßen werden. Wird ein Test
   tatsächlich ausgeführt?
2. Fügen Sie noch die Dependency "junit-jupiter-engine" analog der "junit-jupiter-api" Dependency hinzu. Wie können
   Sie in Ihrer POM eine Wiederholung der gleichen Versionsnummer vermeiden?
3. Damit Maven JUnit 5 Tests ausführen kann, brauchen Sie noch den folgenden Block in Ihrer POM. Was da passiert,
   verstehen wir später...
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
4. Führt Maven den Test nun aus? Dies sollte so sein...
5. Implementieren Sie in dem Test nun einen Aufruf an die Methode
   `com.example.generators.RandomStringGenerator#generateRandomString`. Diese Klasse finden Sie in der Demo-Lib-A. Der
   Test soll sicherstellen, dass das Ergebnis die richtige Länge hat.
6. Was wäre ein passender Scope für die JUnit5 Dependency? Welchen Scope kann oder sollte die Demo-Lib-A Dependency
   haben?

## 050 - Plugins

### a) Der "exec" Plugin

1. Erstellen Sie eine Klasse mit einer `main()` Methode in Ihrem Projekt, die eine Ausgabe auf `System.out` macht
2. Schauen Sie sich den "Exec Maven Plugin" an, insbesondere das "exec:java" Goal. Welche Parameter benötigt
   dieses Goal zur Ausführung?
    * https://www.mojohaus.org/exec-maven-plugin/java-mojo.html
3. Führen Sie den Plugin per Kommandozeile aus, sodass Ihre Main-Klasse von Maven aus gestartet wird
    * Tipp: `mvn <plugin-group-id>:<plugin-artifact-id>[:<plugin-version>]:<goal>`
    * Tipp: Properties können von der Kommandozeile mit `mvn -D"propertyName=propertyWert"` gesetzt werden
      (insbesondere bei dieser Übung sind die Anführungsstriche wichtig)
4. Fügen Sie den Plugin nun im `<build><plugins>` Block Ihrer POM hinzu. Mittels Kommandozeile führen Sie jetzt
   das Ziel "java" des Plugins aus: `mvn exec:java`. Wofür genau steht "exec" an dieser Stelle? Funktioniert es?
5. Ergänzen Sie das Kommando um den notwendigen "mainClass" Parameter
6. Hinterlegen Sie den "mainClass" Parameter im `<configuration>` Block des Plugins
7. Binden Sie den Plugin an die "verify" Phase und führen Sie diese aus.

### b) Core Plugin Versionen festlegen

1. Legen Sie eine minimale neue pom.xml an
2. Erzeugen Sie mittels des "help" Plugins die effektive POM als `effective-pom.xml` Datei
3. Mit welcher Version wird z.B. der "maven-compiler-plugin" deklariert?
4. Suchen Sie in einem Online Repository nach der neusten Version des Plugins und deklarieren Sie diese als
   aktuelle Version in Ihrer POM.
    * Tipp: Der Wert "org.apache.maven.pugins" ist als Plugin-Group automatisch gesetzt (kann in settings.xml
      geändert werden), somit kann dieser für Core Plugins entfallen
5. Erzeugen Sie erneut mittels des "help" Plugins die effektive POM, diesmal als `effective-pom-neu.xml` Datei.
   Vergleichen Sie beide Dateien z.B. mittels eines Diff-Tools

## 055 - Profile

### a) Reihenfolge

1. Definieren Sie mindestens ein Profil in Ihrer `<USER-HOME>/.m2/settings.xml`, welches
   ein Property namens `foo` setzt
2. Definieren Sie zwei weitere Profile in Ihrer POM, die ebenfalls dieses Property setzen
3. Lassen Sie sich das Property `foo` auflösen: `mvn help:evaluate -Dexpression=foo -P <profile>` unter
   Setzen verschiedener Profile.
4. Lassen Sie sich die Reihenfolge der aktiven Profile anzeigen: `mvn help:active-profiles -P <profile>`

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