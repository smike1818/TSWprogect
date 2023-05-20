use musicalstore;

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
    numero_carta varchar(20) unique not null,
    cvv varchar(3) unique not null
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

create table image(
     pathImage varchar(500) not null,
     articolo int references articolo(codice)
         on update cascade
         on delete cascade,
     nome varchar(100) not null,
     primary key(articolo,nome)
);

create table categoria(
	IDcat int primary key auto increment,
    nome_cat varchar(20) not null,
    descrizione varchar(200)
);

create table composizione(
    prezzo double not null,  
    qAcquistate int not null,      //quantità acquistate
    iva varchar(20) not null,
    articolo int references articolo(codice)
         on update cascade
         on delete set null,
	consumer varchar(16) references acquisto(consumer)
         on update cascade
         on delete set null,
    conto varchar(27) references acquisto(conto)
         on update cascade
         on delete set null
    primary key(articolo, consumer, conto)
);

create table acquisto(
   consumer int references utente(id)
        on update cascade
        on delete set null,
   conto varchar(27) references conto(IBAN)
		on update cascade 
        on delete set null,
   dataAcquisto date not null,
   importo double not null,
   primary key(consumer,conto)
);

create table indirizzo(
	via varchar(50) not null,
    civico int not null,
    citta varchar(20) not null,
    PRIMARY KEY(via, civico, citta),
    cap int not null,
    cliente varchar(16) references utente(CF)
        on delete cascade
        on update cascade
    foreign key(cliente)
);



