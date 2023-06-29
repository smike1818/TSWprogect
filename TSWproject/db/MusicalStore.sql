use musicalstore;

CREATE TABLE categoria (
    IDcat INT PRIMARY KEY AUTO_INCREMENT,
    tipo BOOLEAN NOT NULL DEFAULT FALSE,
    nome_cat VARCHAR(20) NOT NULL,
    descrizione VARCHAR(200)
);

CREATE TABLE iva (
    IDiva INT AUTO_INCREMENT PRIMARY KEY,
    percentuale DOUBLE NOT NULL
);

CREATE TABLE utente (
    CF VARCHAR(16) PRIMARY KEY,
    nome VARCHAR(20) NOT NULL,
    cognome VARCHAR(20) NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL,
    pwd VARCHAR(20) NOT NULL,
    username VARCHAR(20) UNIQUE NOT NULL,
    ruolo BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE telefono (
    numero VARCHAR(10) PRIMARY KEY,
    prefisso VARCHAR(3) NOT NULL DEFAULT "+39",
    utente VARCHAR(16) NOT NULL,
    isPrimary BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (utente) REFERENCES utente(CF)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE conto (
    IBAN VARCHAR(27) PRIMARY KEY,
    intestatario VARCHAR(16),
    numero_carta VARCHAR(20) UNIQUE NOT NULL,
    cvv VARCHAR(3) NOT NULL,
    isPrimary BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (intestatario) REFERENCES utente(CF)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE articolo (
    codice INT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    prezzoBase DOUBLE NOT NULL,
    quantita INT NOT NULL,
    colore VARCHAR(20) NOT NULL,
    descrizione VARCHAR(1000) NOT NULL,
    marca VARCHAR(50) NOT NULL,
    tipo INT NOT NULL,
    corde INT,
    tipologia VARCHAR(50) NOT NULL,
    iva INT,
    categoria INT,
    FOREIGN KEY (iva) REFERENCES iva(IDiva)
        ON UPDATE CASCADE
        ON DELETE SET NULL,
    FOREIGN KEY (categoria) REFERENCES categoria(IDcat)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE image (
    pathImage VARCHAR(500) NOT NULL,
    articolo INT,
    nome VARCHAR(100) NOT NULL,
    PRIMARY KEY (articolo, nome),
    FOREIGN KEY (articolo) REFERENCES articolo(codice)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE indirizzo (
    via VARCHAR(50) NOT NULL,
    civico INT NOT NULL,
    citta VARCHAR(20) NOT NULL,
    cap INT NOT NULL,
    cliente VARCHAR(16),
    isPrimary BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY (via, civico, citta),
    FOREIGN KEY (cliente) REFERENCES utente(CF)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE acquisto (
    idAcquisto INT PRIMARY KEY AUTO_INCREMENT,
    consumer VARCHAR(16),
    conto VARCHAR(27),
    data_acquisto DATETIME DEFAULT CURRENT_TIMESTAMP,
    importo DOUBLE NOT NULL,
    via VARCHAR(50),
    citta VARCHAR(20),
    civico INT,
    FOREIGN KEY (consumer) REFERENCES utente(CF)
        ON UPDATE CASCADE
        ON DELETE NO ACTION,
    FOREIGN KEY (conto) REFERENCES conto(IBAN)
        ON UPDATE CASCADE
        ON DELETE NO ACTION,
    FOREIGN KEY (via, civico, citta) REFERENCES indirizzo(via, civico, citta)
        ON UPDATE CASCADE
        ON DELETE NO ACTION
);

CREATE TABLE composizione (
    prezzo DOUBLE NOT NULL,
    qAcquistate INT NOT NULL,
    iva INT NOT NULL,
    articolo INT,
    acquisto INT,
    PRIMARY KEY (articolo, acquisto),
    FOREIGN KEY (articolo) REFERENCES articolo(codice)
        ON UPDATE CASCADE
        ON DELETE NO ACTION,
    FOREIGN KEY (acquisto) REFERENCES acquisto(idAcquisto)
        ON UPDATE CASCADE
        ON DELETE NO ACTION
);


/*QUERIES DI INSERIMENTO*/
/*amministratore*/
INSERT INTO utente VALUES ('PSCMHL01E30E791A','Mario','Rossi','mariorossi@gmail.com','root','root',1);
