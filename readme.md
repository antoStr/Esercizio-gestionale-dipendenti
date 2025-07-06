# Indice

- [Creazione database](#creazione-database)
- [Creazione server Tomcat](#creazione-server-tomcat)
  - [Installazione server](#installazione-server-tomcat)
- [Creazione progetto Maven](#creazione-progetto-maven)
  - [Inizializzazione progetto](#creazione-progetto)

---

## Risorse utilizzate:

- [Eclipse IDE](https://eclipseide.org/)
  - Ide per java, completo per lo sviluppo di questo progetto.
- [Visual Studio Code](https://code.visualstudio.com/)
  - Editor per lo sviluppo del file del readme, sviluppo frontend per le pagine web in generale.
- [MySQL Community Edition](https://www.mysql.com/products/community/)
  - Database relazionale utilizzato per lo sviluppo del database per il progetto.
- [Flowchart](https://www.lucidchart.com/pages)
  - Sito web per la creazione di flowchart e workflow per questo progetto.
- [BiagioAltruda](https://github.com/BiagioAltruda/JavaCourse/blob/main/Maven%20project%2C%20JDBC%20%26%20Servlet.md)
  - Ho preso spunto dal suo readme riguardo la spiegazione riguardante JDBC, servlet e Maven project.
- [ChatGPT](https://chatgpt.com/)
  - Compagno AI per studio.

---

# Creazione database

Perfetto per prima cosa bisogna creare un database ed in questo caso utilizzeremo MySQL, creando un database chiamato "gestionaledipendenti" e successivamente creando una tabella che in futuro posso andare a cambiare e modificare.

Ho scelto alcuni valori unique poichè dati come codice fiscale ed email sono univoci e non esistono duplicati nella vita reale.

In futuro potremmo implementare funzioni e stored procedures.

```sql
create table dipendenti (
	id int not null auto_increment primary key,
    nome varChar(50) not null,
    cognome varChar(50) not null,
    codice_fiscale varChar(16) not null unique,
    data_nascita date not null,
    luogo_nascita varChar(50) not null,
    email varChar(100) not null unique,
    telefono int not null unique,
    indirizzo varChar(200) not null,
    data_assunzione date not null,
    ruolo varChar(75) not null,
    reparto varChar(75) not null,
    stipendio int not null,
    attivo boolean not null
);

```

# Creazione server Tomcat

### (?) Che cosa è Tomcat? Che cosa è un server?

Un server è un computer o un programma che offre servizi ad altri computer chiamati client, in questo caso di web app e web applications, il suo obiettivo è quello di fornire pagine web.
Quando apriamo un sito web, il browser manda una richiesta al server che ospita quel sito, il server riceve la richiesta, prende la pagina e ce la rimanda indietro al browser che ce la fa visualizzare.

**Perchè avviene tutto questo?**
Perchè il nostro PC non ha i dati del sito o in generale nessun dato di qualsiasi sito in rete che al contrario sono salvati sui server che conserva file dei siti, gestisce chi può vederli e risponde a chi li chiede.

Tomcat è un tipo di server specializzato in Java (precisamente è un **servlet container** o **application server**), in particolare è progettato per eseguire applicazioni scritte in java nelle servlet e JSP. Tomcat riceve richieste dal browser, esegue codice scritto in java sul server e restituisce le risposte (pagine HTML, dati JSON, ecc.). E' uno strumento per lo sviluppo e deployment di applicazioni web in Java, poi, più avanti, utilizzeremo server per mantenere l'app online e via dicendo.

---

## Installazione server

Una volta su eclipse dobbiamo entrare nella prospettiva di Java EE (Java Enterprise Edition, serve per creare applicazioni più robuste per web services):

![view](/res/image.png)

Se non dovremmo avere questa prospettiva possiamo aprirla di qui:

![mod-view](/res/mod_view.png)

Ed inserisco la nuova prospettiva:

![mod-view1](/res/javaeeview.png)

---

Una volta selezionata la prospettiva corretta, andiamo nella tab dei servers ed installiamo Tomcat.

![mod-view2](/res/serverstab.png)

Se non dovessi vedere la tab server, posso andare in `Window> Show View> Servers`:
(Stessa cosa se non dovessi vedere la roba per aggiungere prospettive, basta selezionare invece di Show View - Perspective).

![mod-view3](/res/editserverstab.png)

Clicco su _No servers are aviable. Click this link to create a new Server..._, oppure faccio tasto destro nella schermata _servers_ e faccio `New > Server` e dovrei otterene una schermata del genere:

![mod-view4](/res/select-serves.png)

Seleziono una versione di Tomcat, in questo caso seleziono ed utilizzo la versione v11.0 e faccio in basso _Next > _.

![mod-view5](/res/server-name-used.png)

Può capitare che ci dia questo errore (e nonostante abbia eliminato le istanze dei server continui a dare errore) posso semplicemente aggiungere qualche lettera o nuovo nome al nome del server, in questo caso ho aggiunto una data per ricordarmi quando l'ho installato e scaricato:

![mod-view6](/res/rename-server.png)

Una volta selezionato il nome, seleziono una directory dove andrà a scaricare il server, io l'ho salvato in una cartella nella mia workspace di Eclipse chiamata _Download installer servers_.

**NON** chiamare la tua cartella Servers, perchè Eclipse ne creerà una chimata Servers per metterci dentro la sua roba.

Infine clicco su _Download and Intstall..._, accetto i termini e condizioni e clicco su _Finish_.  
(Eventualmente mi richiederà la cartella di download del server, quindi la reinserisco ancora una volta se me lo dovesse richiedere).

![mod-view7](/res/accept-eula.png)

![mod-view8](/res/status-download.png)

Se è andato tutto bene in basso a destra su Eclipse posso vedere di poco il download del server, devo aspettare che termini (in poche parole scompare questo "download" che eclipse sta facendo) e una volta terminato posso cliccare su _Finish_.

Dovrei aver ottenuto una schermata del genere:

![mod-view9](/res/status-servers.png)

Ora proviamo a far partire il server:

![mod-view10](/res/startup-server.png)

A me per esempio, tutte le volte che faccio partire il server di Tomcat, costantemente al primo avvio mi da questo errore:

![mod-view11](/res/errors-first-startup.png)

Molto semplicemente clicco ok, e ri-eseguo lo _Start_ del server, e di solito mi funziona sempre, ma a quanto pare è il gatto Tom che gli piace giocare e scherzare.

![mod-view12](/res/ok-startup.png)
![mod-view13](/res/ok-startup2.png)

Se visualizzo queste schermate possiamo ritenerci fortunati poichè Tomcat funziona ed è avviato correttamente.

# Creazione progetto Maven

### (?) Che cosa è Maven?

Un progetto Maven è un'applicazione Java organizzata secondo uno schema preciso, gestita tramite Maven.

Maven è un programma (tool) che serve a gestire progetti Java in modo automatico, scaricare librerie/dipendenze da internet e compilare ed impacchettare il codice in .jar o .war.

.jar = Java ARchive  
Usato per programmi standalone

.war = Web ARchive
Usato per applicazioni web da caricare su application server.

Maven segue una struttura del genere:

```sql
progetto-maven-example/
├── pom.xml           <-- File di configurazione principale
└── src/
    └── main/
        └── java/     <-- Codice Java
    └── test/
        └── java/     <-- Codice Java per testing etc.
```

Il cuore del nostro progetto è `pom.xml` poichè all'interno contiene tutte le istruzioni che il progetto deve seguire come la versione di Java, che librerie deve importare e tanto altro.

Per il momento è solo per un infarinatura generale per capire e comprendere la struttura di Maven.

---

## Inizializzazione progetto Maven
