create database musicalstoredb;

use musicalstoredb;

create table utente(
	CF varchar(16) primary key,
    nome varchar(20) not null,
    cognome varchar(20) not null,
    email varchar(50) unique not null,
    pwd varchar(20) not null,
    username varchar(20) unique not null,
    ruolo boolean not null DEFAULT false
);

create table conto(
	IBAN VARCHAR(27) PRIMARY key,
    intestatario varchar(16),
    foreign key (intestatario) references utente(CF),
    numero_carta int unique not null,
    cvv int unique not null
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
    categoria int references categoria(IDcat)
		on UPDATE cascade
        on delete set null
);

create table categoria(
	IDcat int primary key,
    nome_cat varchar(20) not null,
    descrizione varchar(200)
);

create table acquisto(
   consumer int references utente(id)
        on update cascade
        on delete set null,
   articolo int  references articolo(codice)
        on update cascade
        on delete no action,
	conto varchar(27) references conto(IBAN)
		on update cascade 
        on delete set null,
   dataAcquisto date not null,
   IVA varchar(20) not null,
   importo double not null,
   primary key(consumer,articolo)
);

create table indirizzo(
	via varchar(50) not null,
    civico int not null,
    citta varchar(20) not null,
    PRIMARY KEY(via, civico, citta),
    cap int not null,
    cliente varchar(16) references cliente(CF)
);