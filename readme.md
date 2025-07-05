## Risorse utilizzate:

- [Eclipse IDE](https://eclipseide.org/)
- [Visual Studio Code](https://code.visualstudio.com/)
- [MySQL Community Edition](https://www.mysql.com/products/community/)
- [Flowchart](https://www.lucidchart.com/pages)

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

## Installazione Server Tomcat

Una volta su eclipse dobbiamo entrare nella prospettiva di Java EE (Java Enterprise Edition, serve per creare applicazioni più robuste per web services):

![view](/res/image.png)

Se non dovremmo avere questa prospettiva possiamo aprirla di qui:

![mod-view](/res/mod_view.png)

Ed inserisco la nuova prospettiva:

![mod-view1](/res/javaeeview.png)

---

Una volta selezionata la prospettiva corretta, andiamo nella tab dei servers ed installiamo Tomcat.

![mod-view2](/res/serverstab.png)

Se non dovessi vedere la tab server, posso andare in `Window> Show View> Servers`

![mod-view3](/res/editserverstab.png)

---

### (?) Che cosa è Tomcat? Che cosa è un server?

Un server è un computer o un programma che offre servizi ad altri computer chiamati client, in questo caso di web app e web applications, il suo obiettivo è quello di fornire pagine web.
Quando apriamo un sito web, il browser manda una richiesta al server che ospita quel sito, il server riceve la richiesta, prende la pagina e ce la rimanda indietro al browser che ce la fa visualizzare.

**Perchè avviene tutto questo?**
Perchè il nostro PC non ha i dati del sito o in generale nessun dato di qualsiasi sito in rete che al contrario sono salvati sui server che conserva file dei siti, gestisce chi può vederli e risponde a chi li chiede.

Tomcat è un tipo di server specializzato in Java (precisamente è un **servlet container** o **application server**), in particolare è progettato per eseguire applicazioni scritte in java nelle servlet e JSP. Tomcat riceve richieste dal browser, esegue codice scritto in java sul server e restituisce le risposte (pagine HTML, dati JSON, ecc.). E' uno strumento per lo sviluppo e deployment di applicazioni web in Java.

---
