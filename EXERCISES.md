# Maven Grundlagen - Übungen

## 010 - Einführung

### a) Minimale pom.xml

Erstellen Sie sich im Projekt ein Arbeitsverzeichnis mit Namen "exercise" (auf gleicher Ebene wie
diese Datei).

Erstellen Sie eine `pom.xml`, welche nur die minimal benötigten Inhalte enthält (Google ist Ihr Freund).

Probieren Sie verschiedene Plugins oder Phasen auszuführen.

### b) Mini Projekt

Erstellen Sie die per Maven Konvention erwarteten Verzeichnisse für den Anwendungs-Quellcode
und zusätzliche Ressourcen.

Legen Sie im entsprechenden Verzeichnis eine Klasse an (ohne Package). 
Diese soll eine `main()` Methode enthalten.

Legen Sie im entsprechenden Verzeichnis eine zusätzliche Ressource an, z.B. eine (leere) data.csv Datei.

Lassen Sie Maven die Anwendung kompilieren. Ggf. gibt es hier eine Fehlermeldung wie z.B. 
"Source option 5 is no longer supported. Use 6 or later." -- hierfür können Sie online
nach einer Lösung suchen und benutzen, Sie müssen diese noch nicht verstehen :)

Schauen Sie sich den Output von Maven an.