# Indice

- [Creazione database](#creazione-database)
- [Creazione server Tomcat](#creazione-server-tomcat)
  - [Installazione server](#installazione-server-tomcat)
- [Creazione progetto Maven](#creazione-progetto-maven)
  - [Inizializzazione progetto](#inizializzazione-progetto-maven)
  - [Deployment progetto sul server](#deployment-progetto-sul-server)
  - [Installazione dipendenze](#installazione-dipendenze)
  - [Creazione servlet](#creazione-servlet)
- [Strutturazione esercizio](#strutturazione-esercizio)
  - [Packages e classi](#packages-e-classi)
    - [Package model](#package-model)
      - [Classe Dipendente](#classe-dipendente)
    - [Package dao](#package-dao)
      - [Classe DatabaseConnection](#classe-databaseconnection)
      - [Interfaccia DipendenteDAO](#interfaccia-dipenentedao)
      - [Classe DipendenteDAOImpl](#classe-dipendentedaoimpl)
    - [Package controller](#package-controller)
      - [Servlet DipendentiServlet](#servlet-dipendentiservlet)
  - [Html, css, form ed input](#html-css-form-ed-input)
    - [Html, form ed input](#html-form-ed-input)
      - [Pagina principale](#pagina-principale)
      - [Pagina aggiunta di un dipendente](#pagina-di-aggiunta-dipendente)
      - [Pagina modifica di un dipendente](#pagina-di-modifica-del-dipendente)
      - [Pagina rimozione di un dipendente](#pagina-di-rimozione-di-un-dipendente)
      - [Pagina ricerca di un dipendente](#pagina-di-ricerca-di-un-dipendente)
      - [Pagina ricerca dipendenti](#pagina-di-ricerca-dipendenti)
      - [Pagina di return](#pagina-di-return)
    - [Css](#css)

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
- [Oracle](https://www.oracle.com/database/technologies/maven-central-guide.html)
  - Documentazione utilizzata per Jdbc e Maven.
- [Maven Central Repository](https://central.sonatype.com/)
  - Sito per la ricerca delle dipendenze per Maven.
- [ChatGPT](https://chatgpt.com/)
  - Compagno AI per studio.
- [Claude](https://claude.ai/)
  - Compagno AI per studio e revisione codice.

---

# Traccia e flow dell'esercizio

![esercizio](/res/flowchart.png)

# Creazione database

Perfetto per prima cosa bisogna creare un database ed in questo caso utilizzeremo MySQL, creando un database chiamato "gestionaledipendenti" e successivamente creando una tabella che in futuro posso andare a cambiare e modificare.

Ho scelto alcuni valori unique poichè dati come codice fiscale ed email sono univoci e non esistono duplicati nella vita reale.

Infine ho impostato i valori not null perchè non avrebbe senso avere dei dati vuoti in un database e perderebbe di senso averli appunto vuoti.

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
  tel int not null unique,
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

**.jar** = Java ARchive  
Usato per programmi standalone

**.war** = Web ARchive
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

Per creare un nuovo progetto Maven eseguo `File > New > ` e se trovo selezionato _Maven Project_ clicco lì, altrimenti posso andare su `File > New > Other` e cerco `Maven`:

![maven](/res/maven-20init.png)

Una volta creato faccio _Next >_ e spunto _Create a simple project (skip archetype selecion)_:

![maven-2](/res/maven-init2.png)

Dopo, devo inserire il _Group Id_ ed l' _Artifact Id_ dove:  
**Group Id** = identifica l'azienda o il gruppo che ha creato il progetto.

**Artifact Id** = identifica il nome del progetto

Inoltre, dato che stiamo sviluppando una web application, seleziono il package corretto _war_.

Una volta compilato tutto faccio _Finish_.

![maven-3](/res/maven-init3.png)

Dovremmo aver ottenuto un progetto del genere, dove, come abbiamo visto prima, c'è il nostro _pom.xml_ e la nostra cartella src assieme ad altri files del nostro progetto.

![maven-4](/res/progiettomaiven.png)

---

## Deployment progetto sul server

### (?) Che significa fare il deployment?

Fare il deployment del nostro progetto sul server significa rendere il nostro progetto pronto all'uso su un server, o detto in parole povere carichiamo il nostro progetto sul server, dove il server lo esegue e ce lo rende accessibile per esempio via browser.

Per deployare il nostro progetto faccio tasto destro sul server che abbiamo creato [qui](#installazione-server), e faccio _Add and Remove..._ :

![maven-41](/res/addnremove.png)

Nella schermata che mi si presenta mi mostra i progetti disponibili al deployment (a sinistra) ed i progetti deployati (a destra).

Clicchiamo sul nostro progetto e facciamo _Add >_ :

![maven-412](/res/addnremove2.png)

Infine faccio finish. Abbiamo deployato il nostro progetto sul nostro server Tomcat. Ora installiamo le dipendenze necessarie per il progetto. Avremo bisogno sicuramente di un qualcosa che colleghi il nostro Eclipse o perlomeno il nostro progetto al nostro database.

---

## Installazione dipendenze

Spostiamoci su _pom.xml_, apriamo il file e dovremmo avere un file del genere:

![pom](/res/pom.png)

Per qualche motivo ho un errore genera il file, elimino le righe dopo il primo link in project e mi assicuro che rispecchi una struttura del genere:

![pom2](/res/pom2.png)

Ora inseriamo le dipendenze, in questo blocco di codice puoi trovare quelle di jakarta servlet e di mysql, se hai bisogno di qualsiasi dipendenza che vuoi includere puoi andare sul sito di [Maven Central Repository](https://central.sonatype.com/) dove puoi trovare nella barra di ricerca le dipendenza che vuoi.

Inseriamo queste dipendenze dopo il packaging.

```xml
<dependencies>
	<dependency>
		<groupId>jakarta.servlet</groupId>
		<artifactId>jakarta.servlet-api</artifactId>
		<version>6.0.0</version>
		<scope>provided</scope>
	</dependency>
	<dependency>
		<groupId>mysql</groupId>
		<artifactId>mysql-connector-java</artifactId>
		<version>8.0.33</version>
	</dependency>
</dependencies>
```

Dovremmo aver raggiunto una struttura simile:

![pom3](/res/pom3.png)

Inoltre abbiamo bisogno di un file che a volte non viene generato ma possiamo farlo fortunatamente anche dopo, il file in questione è _web.xml_, questo file ci aiuta a configurare servlet, definire url mapping e tanto altro, in poche parole dice al nostro server Tomcat come gestire la nostra web app.

![webxml](/res/webxml.png)

Per generarlo posso seguire queste istruzioni:

Tasto destro sul nostro progetto e faccio `Java EE Tools > Generate Deployment Descriptor Stub`.

Per aprire il nostro file _web.xml_ mi assicuro che sia nella tab source in basso quando lo apro e che abbia una struttura simile a questa.

Se ci sono tag servlet e servlet mapping posso cancellarli.

Dovremmo controllare regolarmente questo file per assicurarci che Maven importi le nostre servlet e le aggiunga al nostro file di configurazione.

![webxml](/res/webxml2.png)

Per installare le dependencies aggiunte faccio tasto destro su _pom.xml_ ed eseguo `Run As > 5 Maven Install`:

![maveninst](/res/maveninstall.png)

Se è andato tutto liscio dovrei avere un output in console del genere:

![mavenok](/res/mavenoutok.png)

Io ho avuto un problema ed è importante leggere la console in quanto l'applicazione di Maven utilizzava una versione più recente di quella installata su Eclipse e nel mio computer e non me la buildava. (Avevo Java 8 o da cmd con `java -version` _java version "1.8.0_451"_) Quando avevo installato java avevo installato la versione stabile e comune dal sito ed ero convinto che fosse l'ultima installata quando in realtà non era così, l'ho aggiornata da [qui](https://dev.java/download/) scaricando l'ultima disponibile. Una volta scaricat ed installato la nuova versione l'ha aggiornata all'ultima automaticamente facendo `java -version` (_java version "24.0.1" 2025-04-15_)

Su Eclipse ho modificato la jdk da `Window > Preferences > Java > Installed JREs` ed ho selezionato l'ultima scaricata (nel mio caso la jdk-24).

Il progetto va ugualmente aggiornato e per cambiare la versione di java con cui viene buildato devo fare tasto destro sul progetto e seguo `Build Path > Configure Build Path...`.  
Seleziono la JRE vecchia o che voglio sostituire, la rimuovo con _Remove_ a destra ed aggiungo la nuova JRE con `Add Library > JRE System Library` ed ho settato con _Workspace default JRE_ che nel mio caso avendola aggiornata prima mi dice _(jdk-24)_ ed infine faccio finish. Alla fine ho risolto ed ho buildato correttamente il mio progetto.

## Creazione servlet

Per testare se il nostro progetto funziona come dovrebbe e prima di incominciare a creare i nostri package per incominciare il nostro esercizio creiamo una servlet.

### (?) Che cosa è una servlet?

Una servlet è una classe in Java che vive sul lato server e risponde alle richieste dei client come un browser web.

Essa è come un intermediario: prende la richiesta di un utente, esegue del codice e poi ci dà indietro una risposta.  
Un esempio semplice è quello del cameriere in una pizzeria: noi ordiniamo una pizza al cameriere, il cameriere prende l'ordine e lo consegna al pizzaiolo che farà la pizza che restituirà al cameriere che, alla fine, ce la consegna e si assicura che noi l'abbiamo ricevuta.

### Struttura di una servlet

La servlet quando viene creata estende la classe HttpServlet, essa è composta da diversi metodi che possiamo implementare e di cui dobbiamo implementarne almeno uno, di solito quando creo la servlet mi mette in automatico un doGet ed un doPost per non restituirci errori.

Il **doGet** è un metodo viene invocato quando la servlet riceve una richiesta http get, tipicamente questo metodo è utilizzato per leggere dei dati.

Il **doPost** invece è un metodo viene invocato in una risposta http post, quindi è una richiesta utilizzata per inviare dati al server.

I metodi **doPut** e **doDelete** svolgono operazioni rispettivamente di aggiornamento ed eliminazione ma sono meno comuni. (da chiedere domani).

---

Faccio tasto destro sul progetto e faccio: `New > Servlet`.
Inserisco un package (in questo package _com_ vuol dire un package generico, com si usa appunto come _test_, _prova_) ed un nome della servlet, o perlomeno il nome della servlet che gestirà quaclosa del nostro progetto, in questo caso la lascio neutra come TestSvt (testServlet).

E' importante ricordare che una servlet agirà solo su una tabella del nostro database, se ho un altra tabella che devo gestire, per esempio ho una tabella dipendenti ed una tabella con amministratori, dovrò creare due servlet diverse dove una si occuperà della tabella dipendenti ed un'altra andrà ad operare sulla tabella amministratori.

E' utile ricordare che quando creo una servlet su Eclipse me la generererà con una struttura del genere e volte mi updaterà il _pom.xml_ con dei tag riguardanti la servlet appena creata:

```java
// import ...

  @WebServlet("/DipendentiServlet")
  public class DipendentiServlet extends HttpServlet {
    // codice ...
  }

```

Scrivere `@WebServlet("/DipendentiServlet")` e aggiungere tag servlet (o tag simile) con nome DipendentiServlet vuol dire creare la servlet due volte con lo stesso nome ed andrà in errore. Quindi devo sempre accertarmi di eliminare tag servlet dal mio _pom.xml_ o al massimo non aggiungere la riga `@WebServlet("/DipendentiServlet")` nella mia servlet ed importarla diversamente. Io preferisco cancellare eventuali tag correlati alla servlet che ho creato così non ho problemi di servlet multiple.

Una volta inseriti i dati clicco su _Finish_.

![svtwiz](/res/svtwizard.png)

Una volta creata la nostra servlet la apriamo e dovremmo avere una struttura di questo tipo:

![wizbelike](/res/svtbelike.png)

Ho sostituito riga 30 (commento autogenerato) con un:

```java
System.out.println("Hello!");
```

Ed infine ho salvato la servlet.

A volte può capitare che dia errore il server e dobbiamo andare a eliminare in _web.xml_ qualsiasi tag **servlet** e **servlet mapping** e poi runnare il server.

Per testare il funzionamento della nostra servlet dobbiamo prima di tutto startare il server, ed una volta fatto accedere dal browser al nostro progetto che nel mio caso è:

```
http://localhost:8080/gestioneDipendenti/TestSrv
```

Se visualizzo un qualcosa come: `Served at: /gestioneDipendenti` e nella console di Eclipse visualizzo il system out allora la nostra servlet è stata eseguita correttamente e possiamo procedere con il nostro esercizio.

![alt text](/res/svthello.png)

---

# Strutturazione esercizio

Il progetto di maven ha due view principalmente, quello delle risorse e quello delle risorse deployate nell'app, principalmente sono la stessa cosa, ogni volta che aggiungo qualcosa al progetto, lui si occuperà di smistarlo nella classe e package che gli diciamo di inserire quindi possiamo stare tranquilli.  
Se non dovessi visualizzare qualcosa durante il progetto posso fare tasto destro sul progetto e seguire `Maven > Update Project ...` che sarebbe come un refresh delle risorse e magari mostrare alcuni files che potrebbero non vedersi inizialmente o che non sono stati caricati.

Il workflow dell'esercizio seguirà questa linea:  
Accederemo al nostro gestionale tramite una pagina html dal nostro server dove ci saranno diverse pagine collegate alla pagina principale in base all'operazione da svolgere.  
Una volta selezionata la pagina la servlet svolgerà l'operazione richiesta e verremo reindirizzati ad una pagina che ci dice se vogliamo fare altro con sotto un link per ritornare alla pagina principale.

## Packages, classi e servlet

Per incominciare abbiamo bisogno di creare i nostri package per tenere ordinato il nostro esercizio ed abbiamo bisogno di tre package principali:

- **un package controller** che si occuperà di gestire la logica dell'esercizio.
- **un package dao** che si occuperà di connettersi, accedere al database e svolgere operazioni.
- **un package model** che avrà la descrizione delle risorse utilizzate nel nostro esercizio.

In questo caso:

Il **package controller** sarà la nostra servlet che gestirà le richieste fra le pagine web.  
Il **package dao** sarà composto da due classi ed un interfaccia: una classe di connessione al database dove ci connettiamo al database, un interfaccia che descriverà i metodi che dovranno essere implementati nella classe del dao ed infine una classe appunto dao che utilizzerà le classi precedenti per implementare i metodi da utilizzare nella servlet.
Infine il **package model** sarà composto da una classe che descriverà il modello della struttura del nostro dato che utilizzeremo.

Le classi che invece verranno create saranno:

- Dipendente (modello del dipendente)
- DatabaseConnection (classe di connessione al database)
- DipendenteDAO (interfaccia con i vari metodi da far implementare alla classe che gestirà la logica delle operazioni)
- DiplententeDAOImpl (classe che implementerà l'interfaccia e svilupperà singolarmente ogni metodo).

Infine nel controller verrà creata la servlet che importerà tutte le risorse e gestirà le richieste http in base all'input dell'utente.

---

### Package Model

Il package model sarà composto da una classe Dipendente.
Nella classe dipendente dobbiamo andare a descrivere e costruire il nostro oggetto dipendente.

#### Classe Dipendente

La classe Dipendente sarà composta in questo modo:

- Attributi generali privati.
- Costruttore vuoto per la creazione di oggetti vuoti (sono utili se voglio istanziarli vuoti per poi riempirli)
- Costruttore con id del dipendente per le operazioni di ricerca.
- Costruttore senza id del dipendente perchè nel nostro database verrà gestito automaticamente con _auto_increment_ e _primary key_.
- Getter / Setter per prendere e settare valori
- toString per l'output degli elementi in console. (Non avendo fatto javascript è uno dei metodi che abbiamo per mostrare dell'output direttamente in console).

Dovremmo avere una struttura del genere:

```java
public class Dipendente {
  private // Attributi . . .

  public Dipendente(){
  // Costruttore vuoto
  }

  public Dipendente(int id /*. . . altri attributi */){
  // Costruttore con id per operazioni di ricerca ed attributi
  }

  public Dipendente(/*Attributi*/){
  // Costruttore con attributi
  }

  // Getter

  // Setter

  // toString
}
```

Creando più costruttori abbiamo risolto molti problemi sfruttando una particolarità di Java detta **overloading**, l'overloading in Java è la capacità di definire più metodi con lo stesso nome nella stessa classe, purchè abbiano parametri diversi.

Con l'overloading abbiamo risolto il problema di creare oggetti con e senza id che verranno utilizzati in maniera diversa in base ai metodi dell'interfaccia.

---

### Package dao

DAO vuol dire Data Access Object e ci permette di gestire tutte le operazioni di accesso ai dati in un database, quindi si collegherà al database, eseguire query (in questo caso operazioni CRUD), gestire eventuali errori e chiudere la connessione.

Il package dao sarà composto da un'interfaccia che importerà la classe modello e ci elencherà le operazioni che potremmo svolgere con ogni dipendente, una classe di connessione al database ed infine da una classe dao che implementerà l'interfaccia con i metodi descritti dove verranno sviluppati singolarmente.

#### Classe DatabaseConnection

La classe DatabaseConnection che si occuperà di restituirci la connessione al database quando verrà richiamata.

La mia classe sarà più o meno così:

```java
public class DatabaseConnection {
  private static final String url = "jdbc:mysql://localhost:3306/ilNostroDatabase";
  private static final String username = "ilNostroUsername";
  private static final String password = "laNostraPassword";

  public static Connection getConnection() {
    // sviluppo della classe getConnection
    try {
      // sviluppo della classe getConnection
      conn = DriverManager.getConnection(url, username, password); //riga per la connessione
    } catch (SQLException e){
      // output di errore con un e.printStackTrace() o con un e.getMessage()
    }
  }
}
```

Ho inizializzato url, user e password con i modificatori private, static e final in quanto:

- private mi rende queste istanze private solo alla classe e non modificabili altrove in quanto istanze sensibili che non devono essere modificate ed accessibili da nessuna parte.
- final mi rende le istanza non più modificabili una volta istanziate, quindi non posso richiamarle e modificarle, anche perchè non avrei bisogno di modificare user e password per accedere al mio database e soprattutto non voglio accidentalmente riassegnarle.
- static mi rende accessibili le istanze e metodi alla mia classe ed a tutti gli oggetti.

#### Interfaccia DipenenteDAO

L'interfaccia DipendenteDAO verrà sviluppata così:

- Import della classe Dipendente
- Metodi da far implementare alla classe DipendenteDaoImpl

```java
public interface dipendenteDAO {
  void inserisci(Dipendente d);
  void aggiorna(Dipendente d);
  void elimina(int i);
  Dipendente cercaPerID(int id);
  List<Dipendente> getAllDipendenti();
}
```

Il metodo _inserisci_ è void perchè è un metodo che non ci restituisce nulla poichè inserirà un dato nel database. Per funzionare ha bisogno di un elemento Dipendente in input.

Il metodo _aggiorna_ stessa cosa come il metodo _inserisci_, è void perchè andrà ad aggiornare un oggetto Dipendente, quindi non ci restituirà nulla ma avrà solo bisogno di un input di un oggetto Dipendente.

Il metodo _elimina_ è void per lo stesso motivo dei metodi elencati sopra ma a differenza sfrutteremo l'id per ricercare nel database un dipendente e se esiste un dipendente con lo stesso id verrà eliminato, quindi in input utilizzerà un valore intero.

Il metodo _cercaPerID_ ci restituirà un elemento Dipendente purchè noi gli diamo in input un valore intero (cioè l'id del nostro dipendente da cercare);

Infine il metodo _getAllDipendenti_ ci restituirà semplicemente un arraylist con tutti i dipendenti nel nostro database.

#### Classe DipendenteDAOImpl

La classe DipendenteDAOImpl implementerà l'interfaccia ed il modello dipendente in modo da poter generare oggetti dipendente ed implementare metodi descritti nell'interfaccia.

**Metodo inserisci:**
Scrivo la query che andremo ad effettuare nel database e wrappo tutto in un try with resources per sviluppare il mio metodo. (Il metodo try with resources è un semplice try catch dove all'interno del try istanzio i metodi che userò e che chiuderà automaticamente alla fine del try senza che debba farlo io. Questo approccio è mantenuto in tutti i metodi della classe).

Come risorse nel try utilizzo un istanza di connessione con il metodo per ottenerla ed il **PreparedStatement** che pre-compila la quary e riutilizzarla più volte con parametri diversi. Si usano i '?' come segnaposto per i valori e per "sanificare" l'input dell'utente per prevenire sql injections, richiamerà quindi la mia query con il PreparedStatement aggiungendo i valori '?' con dei valori da ricercare nel database.

Una volta terminata l'aggiunta dei valori chiudo con un _executeUpdate()_ per eseguire la query di _INSERT_.

Dovrei aver ottenuto un risultato simile:

```java
public void inserisci(Dipendente d) {
		String querySql = "INSERT INTO dipendenti (nome, cognome, codice_fiscale, data_nascita, luogo_nascita, email, tel, indirizzo, data_assunzione, ruolo, reparto, stipendio, attivo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(querySql)){

			pstmt.setString(1, d.getNome());
			pstmt.setString(2, d.getCognome());
			pstmt.setString(3, d.getCodice_fiscale());
			pstmt.setDate(4, java.sql.Date.valueOf(d.getData_nascita()));
			pstmt.setString(5, d.getLuogo_nascita());
			pstmt.setString(6, d.getEmail());
			pstmt.setString(7, d.getTel());
			pstmt.setString(8, d.getIndirizzo());
			pstmt.setDate(9, java.sql.Date.valueOf(d.getData_assunzione()));
			pstmt.setString(10, d.getRuolo());
			pstmt.setString(11, d.getReparto());
			pstmt.setInt(12, d.getStipendio());
			pstmt.setBoolean(13, d.isAttivo());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.err.println("Errore durante l'inserimento del dipendente." + e.getStackTrace()) ;
		}
}
```

Tutti i valori dovranno essere dello stesso tipo del database in modo da non avere conflitti (quindi dove ho un varChar nella tabella avrò una stringa, dove avrò un int avrò un valore int e via dicendo).

Per il valore della data ho dovuto importare la libreria sql date che mi ha permesso di gestire il valore del get della data con _valueOf_.

**Metodo aggiorna:**
Il metodo aggiorna è un copia ed incolla del metodo inserisci ma con una piccola modifica nella query. Lui mi inserirà dei valori nella tabella Dipendenti ma solo se esiste un id di un dipendente.

```java
public void aggiorna(Dipendente d) {
		String querySql = "UPDATE dipendenti SET nome = ?, cognome = ?, codice_fiscale = ?, data_nascita = ?, luogo_nascita= ?, email = ?, tel = ?, indirizzo = ?, data_assunzione = ?, ruolo = ?, reparto = ?, stipendio = ?, attivo = ? WHERE id = ?";

		try (Connection conn = DatabaseConnection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(querySql)) {

			pstmt.setString(1, d.getNome());
			pstmt.setString(2, d.getCognome());
			pstmt.setString(3, d.getCodice_fiscale());
			pstmt.setDate(4, java.sql.Date.valueOf(d.getData_nascita()));
			pstmt.setString(5, d.getLuogo_nascita());
			pstmt.setString(6, d.getEmail());
			pstmt.setString(7, d.getTel());
			pstmt.setString(8, d.getIndirizzo());
			pstmt.setDate(9, java.sql.Date.valueOf(d.getData_assunzione()));
			pstmt.setString(10, d.getRuolo());
			pstmt.setString(11, d.getReparto());
			pstmt.setInt(12, d.getStipendio());
			pstmt.setBoolean(13, d.isAttivo());
			pstmt.setInt(14, d.getId());

			int righeAggiornate = pstmt.executeUpdate();

	    if (righeAggiornate > 0) {
	            System.out.println("Dipendente aggiornato con successo!");
	    } else {
	            System.out.println("Nessun dipendente trovato con quell'ID.");
	    }

		} catch (SQLException e) {
			System.err.println("Errore durante l'aggiornamento del dipendente.");
		}
}
```

La condizione invece _righeAggiornate_ mi serve per capire se la quary ha modificato delle righe nella tabella, quindi se le righe aggiornate sono positive significa che ha modificato delle righe e quindi un dipendente esiste, al contrario se non esiste non aggiornerà nessuna riga e mi dirà che il dipendente non esiste. E' una condizione che verrà utilizzata anche in altri metodi ed è molto utile.

**Metodo elimina:**
Il metodo elimina utilizza le stesse risorse dei metodi precedenti, cambia solamente che è più semplice da scrivere in quanto se esiste un id nella mia tabella Dipendenti di un dipendente, me lo andrà ad eliminare.

Utilizzo il metodo delle righe aggiornate per controllare se il dipendente è stato eliminato o meno.

```java
public void elimina(int i) {
		String querySql = "DELETE FROM dipendenti WHERE id = ?";

		try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(querySql)){

			pstmt.setInt(1, i);

			int righeAggiornate = pstmt.executeUpdate();

	        if (righeAggiornate > 0) {
	            System.out.println("Dipendente aggiornato con successo!");
	        } else {
	            System.out.println("Nessun dipendente trovato con quell'ID.");
	        }

		} catch (SQLException e) {
			System.err.println("Errore durante l'eliminazione del dipendente.");
		}

}
```

**Metodo cercaPerID:**
Il metodo cercaPerID utilizza le stesse risorse utilizzate precedentemente ma con l'aggiunta del ResultSet, è un interfaccia di java che rappresenta il risultato di una query SQL di tipo _SELECT_. E' essenzialmente un cursore che punta alle righe restituite dalla query e permette di navigare attraverso i dati.

Un metodo molto utilizzato dell'interfaccia ResultSet è il metodo .next(), dove semplicemente muove il cursore del ResultSet alla prossima riga disponibile. In questo caso viene utilizzato in un ciclo condizionale dove gli ho detto "Spostati sulla prima riga disponibile se nella quary esiste l'id inserito". Se l'id che avremo inserito esisterà nella tabella Dipendenti allora procederà con l'istruzione condizionale.

```java
public Dipendente cercaPerID(int id) {
		String querySql = "SELECT * FROM dipendenti WHERE id = ?";
		Dipendente d = new Dipendente();

		try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(querySql)) {

			pstmt.setInt(1, id);

			try (ResultSet rs = pstmt.executeQuery()){
				if (rs.next()) {
					int idDipendente = rs.getInt("id");
					String nomeDipendente = rs.getString("nome");
					String cognomeDipendente = rs.getString("cognome");
					String codiceFiscale = rs.getString("codice_fiscale");
					LocalDate dataNascita = rs.getDate("data_nascita").toLocalDate();
					String luogoNascita = rs.getString("luogo_nascita");
					String emailDipendente = rs.getNString("email");
					String telDipendente = rs.getString("tel");
					String indirizzo = rs.getNString("indirizzo");
					LocalDate dataAssunzione = rs.getDate("data_assunzione").toLocalDate();
					String reparto = rs.getNString("reparto");
					String ruolo = rs.getString("ruolo");
					int stipendio = rs.getInt("stipendio");
					boolean attivo = rs.getBoolean("attivo");

					d = new Dipendente (idDipendente, nomeDipendente, cognomeDipendente, codiceFiscale, dataNascita, luogoNascita, emailDipendente, telDipendente, indirizzo, dataAssunzione, ruolo, reparto, stipendio, attivo);
					System.out.println(d);
				}
			}
		} catch (SQLException e) {
			System.err.println("Errore nella ricerca del dipendente.");
		}

		return d;
}
```

Una volta accertato che l'input esiste lui mi creerà l'oggetto dipendente da restituire e me lo mostra in console altrimenti mi darà un errore nel catch che gestirà entrambi i try with resources.

LocalDate è un tipo di dato che mi restituisce una data che però non è compresa nel set di metodi del ResultSet, quidni per ovviare a questo problema faccio un cast con .toLocalDate().

**Metodo getAllDipendenti:**

Il metodo getAllDipendenti svolgerà le stesse mansioni del metodo cercaPerID, l'unico cambiamento è ci restituirà un'arraylist di dipendenti. Come prima uso il rs.next() per muovermi alla prossima riga disponibile, raccolgo dati da inserire nella query, assemblo l'oggetto dipendente e lo inserisco nell'arraylist che verrà mostrato alla fine.  
Prima ho utilizzato un if poichè stavo cercando un solo dipendente per id, qui ho bisogno di mostrarne un numero che è maggiore di uno quindi uso un while che mi mostrerà più risultati, nella quary ho un _select _ from dipendenti\* quidni il while mi ciclerà tutta la nostra tabella.

```java
public List<Dipendente> getAllDipendenti() {
		String querySql = "SELECT * FROM dipendenti";
		List<Dipendente> listaDipendenti = new ArrayList<>();
		Dipendente d = new Dipendente();

		try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(querySql)){

			try(ResultSet rs = pstmt.executeQuery()){
				while ( rs.next()) {
					int idDipendente = rs.getInt("id");
					String nomeDipendente = rs.getString("nome");
					String cognomeDipendente = rs.getString("cognome");
					String codiceFiscale = rs.getString("codice_fiscale");
					LocalDate dataNascita = rs.getDate("data_nascita").toLocalDate();
					String luogoNascita = rs.getString("luogo_nascita");
					String emailDipendente = rs.getNString("email");
					String telDipendente = rs.getString("tel");
					String indirizzo = rs.getNString("indirizzo");
					LocalDate dataAssunzione = rs.getDate("data_assunzione").toLocalDate();
					String reparto = rs.getNString("reparto");
					String ruolo = rs.getString("ruolo");
					int stipendio = rs.getInt("stipendio");
					boolean attivo = rs.getBoolean("attivo");

					d = new Dipendente (idDipendente, nomeDipendente, cognomeDipendente, codiceFiscale, dataNascita, luogoNascita, emailDipendente, telDipendente, indirizzo, dataAssunzione, reparto, ruolo, stipendio, attivo);
					listaDipendenti.add(d);
				}
			}
		} catch (Exception e) {
			System.err.println("Errore nell'output dei dipendenti.");
		}

		System.out.println(listaDipendenti);
		return listaDipendenti;
}
```

---

### Package controller

Il package controller è il package che conterrà le servlet che gestiranno gli input e richieste http.

Sarà principalmente composto da una sola servlet che gestirà gli input dai form ed invocherà i metodi che abbiamo sviluppato in DipendenteDAOImpl importando tutto il necessario che sarà il model dell'impiegato ed il dao che invocherà i metodi creando un istanza del dao nel metodo.

Dobbiamo vedere il dao come una cassetta degli attrezzi e richiamarla quando abbiamo bisogno di un metodo, quando abbiamo bisogno di un metodo sviluppato prima, creo un istanza del dao e poi sfrutto il dato sull'oggetto a cui vogliamo svoglere delle operazioni.

Esempio:

```java
//Ho un oggetto con meno parametri ma è solo per l'esempio

//Ho un oggetto dipendente con dei valori
Dipendente d = new Dipendente("Luca", "Bianchi");

//Istanzio un dao per svoglere operazioni con il mio dipendente, in questo caso ho bisogno di aggiungerlo nel database e quindi faccio inserisci.
DipendenteDaoImpl dao = new DipendenteDaoImpl();
dao.inserisci(d);

//Il metodo inserisci come visto prima si connette al database e svolge l'operazione di aggiunta al database nello specifico nella tabella dipendenti.

/
```

#### Servlet DipendentiServlet

Nella sezione [servlet](#creazione-servlet) c'è la spiegazione di tutti i metodi che implementa la servlet di base, nello specifico e per praticità faremo le operazioni di ricerca nel _doGet()_ e le operazioni di aggiunta, modifica ed eliminazione nel _doPost()_.

Nei metodi richiamo il metodo _getParameter()_ che prenderà dei valori non necessariamente identici a quelli che ho sul database (a livello di nome del nome ahah, _request.getParameter("nome")_ =! da nome che ho sul db, è solo un nome placeholder), questi valori andremo poi a riutilizzarli nel form dell'html per collegare i valori ai vari input differenti.

Inoltre il metodo _request.getParameter()_ di default ci ritornerà tutto in stringa, dovremmo noi man mano andare a castare le variabili che vogliamo nel tipo che vogliamo.

Nel mio _doGet()_ e _doPost_ istanzio due nuove request che avranno un valore di default che sarà cambiato in uno switch per differenziare l'utilizzo del metodo e servlet differente. Questo metodo mi sarà utile più avanti nel form.

_doGet():_

```java
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action == null) {
			action = "showAll";
		}

		switch (action) {
		case "findId":
			cercaDipendente(request, response);
			break;
		case "showAll":
			mostraDipendenti(request, response);
			break;
		default:
			response.sendError(400, "Azione non valida");
			break;
		}
}
```

_doPost():_

```java
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action == null) {
			action = "insert";
		}

		switch (action) {
		case "insert":
			inserisciDipendente(request, response);
			break;
		case "remove":
			rimuoviDipendente(request, response);
			break;
		case "update":
			modificaDipendente(request, response);
			break;
		default:
			response.sendError(400, "Azione non valida");
			break;
		}
}
```

Nota:
Una roba che devo migliorare sicuramente nel codice è la gestione dell'errore nell'input, ma siccome volevo documentare l'esercizio e scrivere pian piano una guida che potesse rendermi utile cercherò di farlo appena posso e di migliorare tutto.

**Metodo inserisciDipendente:**

```java
private void inserisciDipendente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String codiceFiscale = request.getParameter("codice_fiscale");

		String dataDiNascitaStr = request.getParameter("data_nascita");
		LocalDate dataDiNascita = LocalDate.parse(dataDiNascitaStr);

		String luogoNascita = request.getParameter("luogo_nascita");
		String email = request.getParameter("email");
		String numeroTel = request.getParameter("tel");
		String indirizzo = request.getParameter("indirizzo");

		String dataAssunzioneStr = request.getParameter("data_assunzione");
		LocalDate dataAssunzione = LocalDate.parse(dataAssunzioneStr);

		String ruolo = request.getParameter("ruolo");
		String reparto = request.getParameter("reparto");

		String stipendioStr = request.getParameter("stipendio");
		int stipendio = Integer.parseInt(stipendioStr);

		String attivoStr = request.getParameter("attivo");
		boolean attivo = Boolean.parseBoolean(attivoStr);

		Dipendente d = new Dipendente(nome, cognome, codiceFiscale, dataDiNascita, luogoNascita, email, numeroTel, indirizzo, dataAssunzione, ruolo, reparto, stipendio, attivo);

		DipendenteDaoImpl dao = new DipendenteDaoImpl();
		dao.inserisci(d);

		response.sendRedirect("ReturnPage.html");

}
```

Nel metodo vado a settare ogni stringa con un valore che verrà usato nel form come spiegato prima, dopo istanzio un nuovo oggetto dipendente che utilizzerà le variabili raccolte, infine istanzio un dao per richiamare il metodo inserisci per inserire il dipendente creato nel database.

Una volta terminata l'operazione lui mi rimanda ad una pagina di return che se vorrà continuare a fare altre operazioni dovrà ritornare alla pagina principale con un link nella pagina.

**Metodo rimuoviDipendente:**

```java
private void rimuoviDipendente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idDipendenteStr = request.getParameter("id");
		int idDipendente = Integer.parseInt(idDipendenteStr);

		DipendenteDaoImpl dao = new DipendenteDaoImpl();
		dao.elimina(idDipendente);

		response.sendRedirect("ReturnPage.html");
}
```

Stessa cosa di prima (i metodi saranno più o meno tutti uguali), raccolgo dati ed uso il dao per fare operazioni, in questo caso di elimina tramite id che sarà raccolto nel form nell'html.

**Metodo modificaDipendente:**

E' più o meno un copia ed incolla del metodo inserisciDipendente l'unica cosa diversa è che qui usiamo un id per controllare se il dipendente esiste nella tabella del database, richiamo il dao ed aggiorno.

```java
private void modificaDipendente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);

		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String codiceFiscale = request.getParameter("codice_fiscale");

		String dataNascitaStr = request.getParameter("data_nascita");
		LocalDate dataNascita = LocalDate.parse(dataNascitaStr);

		String luogoNascita = request.getParameter("luogo_nascita");
		String email = request.getParameter("email");

		String tel = request.getParameter("tel");
		String indirizzo = request.getParameter("indirizzo");

		String dataAssunzioneStr = request.getParameter("data_assunzione");
		LocalDate dataAssunzione = LocalDate.parse(dataAssunzioneStr);

		String ruolo = request.getParameter("ruolo");
		String reparto = request.getParameter("reparto");

		String stipendioStr = request.getParameter("stipendio");
		int stipendio = Integer.parseInt(stipendioStr);

		String attivoStr = request.getParameter("attivo");
		boolean attivo = Boolean.parseBoolean(attivoStr);

		Dipendente d = new Dipendente(id, nome, cognome, codiceFiscale, dataNascita, luogoNascita, email, tel, indirizzo, dataAssunzione, ruolo, reparto, stipendio, attivo);

		DipendenteDaoImpl dao = new DipendenteDaoImpl();
		dao.aggiorna(d);

		response.sendRedirect("ReturnPage.html");

}
```

**Metodo cercaDipendente:**

Il metodo cercaDipendente fa come al solito stesse cose di prima dove però, come il metodo elimina, ha solo bisogno di un valore per poter fare operazioni.

```java
private void cercaDipendente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);

		DipendenteDaoImpl dao = new DipendenteDaoImpl();
		dao.cercaPerID(id);

		response.sendRedirect("ReturnPage.html");

	}
```

**Metodo mostraDipendenti:**

E' il metodo più snello di tutti il quale non ha bisogno di nessun parametro dove semplicemente istanzia il dao per l'operazione di _getAllDipendenti()_.

```java
private void mostraDipendenti(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DipendenteDaoImpl dao = new DipendenteDaoImpl();
		dao.getAllDipendenti();

		response.sendRedirect("ReturnPage.html");
	}
```

---

## Html, Css, Form ed Input

### Html, form ed input

#### Pagina Principale

Abbiamo introdotto nella sezione [strutturazione eserizio](#strutturazione-esercizio) che accederemo al nostro server tramite link alla pagina html di default del nostro gestionale, quindi incominciamo a creare una semplice pagina html facendo tasto destro sul nostro progetto e seguendo `New > HTML File`. Aggiungiamo anche diversi collegamenti a pagine che creeremo più avanti in base alle operazioni che faremo:

```html
<!DOCTYPE html>
<html lang="it">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="MainPage.css" />
    <title>Gestionale</title>
  </head>
  <body>
    <div class="titolo">
      <h1>Benvenuto nel gestionale dipendenti.</h1>
      <h2>Scegli la tua operazione:</h2>
    </div>
    <div class="container">
      <div class="menu">
        <a href="AggiuntaDipendente.html">Aggiungi un dipendente</a>
        <a href="RimuoviDipendente.html">Rimuovi un dipendente</a>
        <a href="ModificaDipendente.html">Modifica un dipendente</a>
        <a href="CercaDipendente.html">Mostra un dipendente tramite id</a>
        <a href="MostraDipendenti.html">Mostra tutti i dipendenti</a>
      </div>
    </div>
  </body>
</html>
```

Posso accedere alla pagina avviando il server di Tomcat e ricercare l'indirizzo nel browser:

```
http://localhost:8080/gestioneDipendenti/MainPage.html
```

Posso linkare diverse pagine creandole come prima ed aggiungendole correttamente nell'href prestando attenzione alla directory di creazione (in questo caso io le ho lasciate tutte in un unica cartella assieme ai files di css ma una buona pratica è dividere i files html da quelli css come in questo caso in diverse cartelle, per comodità lasciamo tutto così com'è).

#### Pagina di aggiunta dipendente

La pagina di aggiunta dipendente dovrà contenre un form che prenderà come action l'action della nostra servlet spiegata [qui](#package-controller) dove grazie a questo parametro potremmo andare a differenziare la nostra servlet, quindi in base a quello che farà il form cambierà anche l'action, in questo caso è un form di inserimento quindi avrà insert come valore.

Il form è un grande campo dove verranno associati dei valori di input al suo interno dove quest'ultimi raccoglieranno input dell'utente.

Gli input riceveranno come _name_, i parametri passati nel _request.getParameter()_ in modo tale da collegarli e renderli funzionali, i campi di _type_ cambiano in base alla richiesta di input che voglio dare all'utente, quindi se voglio raccogliere un campo di testo uso _text_, se voglio raccogliere una data metterò _date_ e via dicendo.

Alla fine di un form per far raccogliere tutti i dati utilizzo un input di tipo _submit_ che raccoglierà appunto tutti i dati che verranno elaborati dalla servlet.

```html
<!DOCTYPE html>
<html lang="it">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="AggiuntaDipendente.css" />
    <title>Aggiunta dipendente</title>
  </head>
  <body>
    <div class="container">
      <h1>Inserimento nuovo dipendente</h1>
      <form
        action="DipendentiServlet?action=insert"
        class="form-wrapper"
        method="post"
      >
        <div>
          <span>Nome:</span>
          <input type="text" name="nome" required />
        </div>
        <div>
          <span>Cognome:</span>
          <input type="text" name="cognome" required />
        </div>
        <div>
          <span>Codice fiscale:</span>
          <input type="text" name="codice_fiscale" required />
        </div>
        <div>
          <span>Data di nascita:</span>
          <input type="date" name="data_nascita" required />
        </div>
        <div>
          <span>Luogo di nascita:</span>
          <input type="text" name="luogo_nascita" required />
        </div>
        <div>
          <span>Email:</span>
          <input type="email" name="email" required />
        </div>
        <div>
          <span>Numero di telefono:</span>
          <input type="text" name="tel" required />
        </div>
        <div>
          <span>Indirizzo:</span>
          <input type="text" name="indirizzo" required />
        </div>
        <div>
          <span>Data assunzione:</span>
          <input type="date" name="data_assunzione" required />
        </div>
        <div>
          <span>Ruolo:</span>
          <input type="text" name="ruolo" required />
        </div>
        <div>
          <span>Reparto:</span>
          <input type="text" name="reparto" required />
        </div>
        <div>
          <span>Stipendio:</span>
          <input type="number" name="stipendio" required />
        </div>
        <div>
          <span>Attività attuale:</span>
          <select name="attivo" id="">
            <option value="true">Attivo</option>
            <option value="false">Non attivo</option>
          </select>
        </div>
        <div>
          <input type="submit" />
        </div>
      </form>
    </div>
  </body>
</html>
```

#### Pagina di modifica del dipendente

Le pagine sono più o meno le stesse e seguono la stessa logica spiegata nella pagina di aggiunta dipendente con form, l'unica cosa che cambierà sarà appunto l'action all'interno del form ed il metodo utilizzato nel form a seconda di come l'ho implementato nella servlet, se il metodo l'ho messo nel _doGet()_ allora il form avrà un metodo _get_ altrimenti varia a seconda del metodo dove ho istanziato i miei metodi.

```html
<!DOCTYPE html>
<html lang="it">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="AggiuntaDipendente.css" />
    <title>Modifica dipendente</title>
  </head>
  <body>
    <div class="container">
      <h1>Modifica dipendente esistente</h1>
      <form
        action="DipendentiServlet?action=update"
        class="form-wrapper"
        method="post"
      >
        <div>
          <span>Id:</span>
          <input type="number" name="id" required />
        </div>
        <div>
          <span>Nome:</span>
          <input type="text" name="nome" required />
        </div>
        <div>
          <span>Cognome:</span>
          <input type="text" name="cognome" required />
        </div>
        <div>
          <span>Codice fiscale:</span>
          <input type="text" name="codice_fiscale" required />
        </div>
        <div>
          <span>Data di nascita:</span>
          <input type="date" name="data_nascita" required />
        </div>
        <div>
          <span>Luogo di nascita:</span>
          <input type="text" name="luogo_nascita" required />
        </div>
        <div>
          <span>Email:</span>
          <input type="email" name="email" required />
        </div>
        <div>
          <span>Numero di telefono:</span>
          <input type="text" name="tel" required />
        </div>
        <div>
          <span>Indirizzo:</span>
          <input type="text" name="indirizzo" required />
        </div>
        <div>
          <span>Data assunzione:</span>
          <input type="date" name="data_assunzione" required />
        </div>
        <div>
          <span>Ruolo:</span>
          <input type="text" name="ruolo" required />
        </div>
        <div>
          <span>Reparto:</span>
          <input type="text" name="reparto" required />
        </div>
        <div>
          <span>Stipendio:</span>
          <input type="number" name="stipendio" required />
        </div>
        <div>
          <span>Attività attuale:</span>
          <select name="attivo" id="">
            <option value="true">Attivo</option>
            <option value="false">Non attivo</option>
          </select>
        </div>
        <div>
          <input type="submit" />
        </div>
      </form>
    </div>
  </body>
</html>
```

#### Pagina di rimozione di un dipendente

```html
<!DOCTYPE html>
<html lang="it">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Rimuovi dipendente</title>
    <link rel="stylesheet" href="RimuoviDipendente.css" />
  </head>
  <body>
    <div class="container">
      <h1>Rimozione di un dipendente</h1>
      <form action="DipendentiServlet?action=remove" method="post">
        <span>Inserisci id dipendente da rimuovere:</span>
        <input type="number" name="id" required />
        <input type="submit" />
      </form>
    </div>
  </body>
</html>
```

#### Pagina di ricerca di un dipendente

Non avendo fatto Javascript o altre tecnologie più avanzate dovremmo accontentarci dell'output da console :)

```html
<!DOCTYPE html>
<html lang="it">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Ricerca dipendente</title>
    <link rel="stylesheet" href="RimuoviDipendente.css" />
  </head>
  <body>
    <div class="container">
      <h1>Ricerca di un dipendente per id:</h1>
      <form action="DipendentiServlet?action=findId" method="get">
        <span>Inserisci id dipendente da cercare:</span>
        <input type="number" name="id" required />
        <input type="submit" />
      </form>
    </div>
  </body>
</html>
```

#### Pagina di ricerca dipendenti

```html
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
    <link rel="stylesheet" href="MostraDipendenti.css" />
  </head>
  <body>
    <form action="DipendentiServlet?action=showAll" method="get">
      <input type="submit" />
    </form>
  </body>
</html>
```

#### Pagina di return

```html
<!DOCTYPE html>
<html lang="it">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="ReturnPage.css" />
    <title>Return</title>
  </head>
  <body>
    <div class="container">
      <h1>E adesso?</h1>
      <a href="MainPage.html">Ritorna alla home</a>
    </div>
  </body>
</html>
```

### CSS

Seguendo `New > CSS file` posso creare un nuovo file css da importare nel mio
html.

Esempio file css:

```css
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: Arial, Helvetica, sans-serif;
}

html {
  background-color: #171717;
}

.container {
  height: 100vh;
  width: 100vw;
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.titolo {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.menu {
  margin-top: 50px;
}

h1,
h2 {
  color: #aaaaaa;
  margin-top: 20px;
}

a {
  text-decoration: none;
  color: #fff;
  cursor: pointer;
  display: block;
  margin-top: 30px;
}
```
