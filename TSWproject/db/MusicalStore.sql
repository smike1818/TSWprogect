use MusicalStoreDB;

create table utente(
    id int primary key,
    nome varchar(50) not null,
    cognome varchar(50) not null,
    email varchar(100) not null,
    pw varchar(20) not null,
    tipo int(1) not null,
    privilegi varchar(100)
);

create table ticket(
    id int primary key,
    descrizione varchar(1000),
    apertura date not null,
    chiusura date,
    consumer int not null references utente(id)
          on update cascade
          on delete set null,
    consumer_serv int not null references utente(id)
          on update cascade
          on delete set null
);

create table articolo(
    codice int primary key,
    nome varchar(50) not null,
    prezzoBase double not null,
    quantita int not null,
    colore varchar(20) not null,
    descrizione varchar(1000) not null,
    marca varchar(50) not null,
    tipo int(1) not null,
    corde int(1),
    tipologia varchar(50),
    immagine varchar(10000) not null,
);

create table manipola(
   adm int references utente(id)
        on update cascade
        on delete set null,
   articolo int references articolo(codice)
        on update cascade
        on delete set null,
   dataModifica date,
   primary key(adm,articolo)
);

create table acquisto(
   consumer int references utente(id)
        on update cascade
        on delete set null,
   articolo int  references articolo(codice)
        on update cascade
        on delete set null,
   dataAcquisto date not null,
   IVA varchar(20) not null,
   importo double not null,
   primary key(consumer,articolo)
);