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

create table telefono(
	numero varchar(10) primary key,
	prefisso varchar(3) not null default "+39",
	utente varchar(16) not null references utente(CF)
		on update cascade
		on delete cascade,
	isPrimary boolean not null default false
);

create table conto(
	IBAN VARCHAR(27) PRIMARY key,
    intestatario varchar(16),
    foreign key (intestatario) references utente(CF)
    	on update cascade
    	on delete cascade,
    numero_carta varchar(20) unique not null,
    cvv varchar(3) not null,
    isPrimary boolean not null default false
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
    iva int references iva(IDiva)
       on UPDATE cascade
       on delete set null,
    categoria int references categoria(IDcat)
		on UPDATE cascade
        on delete cascade
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
	IDcat int primary key auto_increment,
	tipo boolean not null default false,                     
    nome_cat varchar(20) not null,
    descrizione varchar(200)
);


create table composizione(
    prezzo double not null,  
    qAcquistate int not null,      
    iva int not null,
    articolo int references articolo(codice)
         on update cascade
         on delete no action,
	acquisto int references acquisto(idAcquisto)
         on update cascade
         on delete no action,
    primary key(articolo, acquisto)
);

create table indirizzo(
	via varchar(50) not null,
    civico int not null,
    citta varchar(20) not null,
    PRIMARY KEY(via, civico, citta),
    cap int not null,
    cliente varchar(16) references utente(CF)
        on update cascade
        on delete cascade,
    isPrimary boolean not null default false
);

CREATE TABLE iva(
    IDiva int auto_increment primary key,
    percentuale double not null
);


CREATE TABLE acquisto (
   idAcquisto INT PRIMARY KEY AUTO_INCREMENT,
   consumer VARCHAR(16) REFERENCES utente(CF)
        ON UPDATE CASCADE
        ON DELETE SET NO ACTION,
   conto VARCHAR(27) REFERENCES conto(IBAN)
        ON UPDATE CASCADE 
        ON DELETE SET NO ACTION,
   data_acquisto DATETIME DEFAULT CURRENT_TIMESTAMP,
   importo DOUBLE NOT NULL,
   via VARCHAR(50),
   citta VARCHAR(20),
   civico int,
   FOREIGN KEY (via, civico, citta) REFERENCES indirizzo(via, civico, citta)
        ON UPDATE CASCADE
        ON DELETE SET NULL
);


/*QUERY DI INSERIMENTO*/
/*amministratore*/
insert into utente values ('PSCMHL01E30E791A','Mario','Rossi','mariorossi@gmail.com','root','root',1);
