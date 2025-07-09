# Indice

- [Creazione database](#creazione-database)
- [Creazione server Tomcat](#creazione-server-tomcat)
  - [Installazione server](#installazione-server-tomcat)
- [Creazione progetto Maven](#creazione-progetto-maven)
  - [Inizializzazione progetto](#inizializzazione-progetto-maven)
  - [Deployment progetto sul server](#deployment-progetto-sul-server)
  - [Installazione dipendenze](#installazione-dipendenze)
  - [Creazione servlet](#creazione-servlet)

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

## Packages e classi

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
Il metodo elimina utilizza le stesse risorse dei metodi precedenti, cambia solamente che è più semplice da scrivere in quanto se esiste un id nella mia tabella Dipendenti di un dipendente, me lo andrà a

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
