# Maven Grundlagen - Übungen

## 010 - Einführung

### a) Minimale pom.xml

1. Erstellen Sie sich im Projekt ein Arbeitsverzeichnis mit Namen "exercise" (auf gleicher Ebene wie
2. diese Datei).
3. Erstellen Sie eine `pom.xml`, welche nur die minimal benötigten Inhalte enthält (Google ist Ihr Freund).
4. Probieren Sie verschiedene Plugins oder Phasen auszuführen.

### b) Mini Projekt

1. Erstellen Sie die per Maven Konvention erwarteten Verzeichnisse für den Anwendungs-Quellcode
und zusätzliche Ressourcen.
2. Legen Sie im entsprechenden Verzeichnis eine Klasse an (ohne Package). 
Diese soll eine `main()` Methode enthalten.
3. Legen Sie im entsprechenden Verzeichnis eine zusätzliche Ressource an, z.B. eine (leere) data.csv Datei.
4. Lassen Sie Maven die Anwendung kompilieren.
    - Ggf. gibt es hier eine Fehlermeldung wie z.B. "Source option 5 is no longer supported. Use 6 or later." 
   -- hierfür können Sie online nach einer Lösung suchen und benutzen, Sie müssen diese noch nicht verstehen :)
5. Schauen Sie sich den Output von Maven an.
6. Was passiert zusätzlich, wenn Sie die Anwendung paketieren lassen?

## 020 - pom.xml

### a) Parent-Pom

hier oder später bei module???