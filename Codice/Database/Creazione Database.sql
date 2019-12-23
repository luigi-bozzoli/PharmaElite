drop database progettotsw;
create Database PROGETTOTSW;

use PROGETTOTSW;

create table Cliente( 
	Email varchar(256) PRIMARY KEY,
   	password varchar(20) not null,
	tipo boolean not null
);

create table Prodotto( 
	ID varchar(10) PRIMARY KEY,
	urlImmagine varchar(256) not null,
    categoria varchar(30) not null,
    nome varchar (30) not null,
    prezzo double(5,2) not null,
    quantita int not null,
    descrizione varchar(256) not null,
    flagEliminato boolean 
    
);

create table DatiAnagrafici( 
	nome varchar(30) not null,
    cognome varchar(30) not null,
    sesso varchar(10) not null,
    citta varchar(30) not null,
    numero varchar(10) not null,
    EmailCliente varchar(320) primary key,
    FOREIGN KEY (EmailCliente) REFERENCES Cliente(Email)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

create table IndirizzoSpedizione(
	ID varchar(10) not null primary key,
	indirizzo varchar(50) not null,
    EmailCliente varchar(320) not null,
    FOREIGN KEY (EmailCliente) REFERENCES Cliente(Email)
	ON DELETE CASCADE
    ON UPDATE CASCADE
	
);


create table MetodoDiPagamento(
	numeroCarta varchar(16) Primary key,
    tipo varchar(20) not null,
    EmailCliente varchar(320) not null,
  
    FOREIGN KEY (EmailCliente) REFERENCES Cliente(Email)
	ON DELETE CASCADE
    ON UPDATE CASCADE
);

create table Ordine( 
	ID varchar(10) Primary key,
    EmailCliente varchar(320),
    dataOrdine date not null,
    costo double(7,5) not null,
    FOREIGN KEY (EmailCliente) REFERENCES Cliente(Email)
	ON DELETE CASCADE
    ON UPDATE CASCADE
);

create table ProdottoNellOrdine( 
	ID varchar(10) Primary key, 
    IDProdotto varchar(10), 
    nome varchar(30) not null,
    prezzo double(7,5) not null,
    
	FOREIGN KEY (IDProdotto) REFERENCES Prodotto(ID) 
	ON DELETE CASCADE
    ON UPDATE CASCADE
);



create table Composizione( 
	IDOrdine varchar(10) not null , 
    IDProdotto varchar(10) not null ,
    quantita int not null,
    Primary Key(IDOrdine,IDProdotto),
    
    
    FOREIGN KEY (IDOrdine) REFERENCES Ordine(ID),
	FOREIGN KEY (IDProdotto) REFERENCES Prodotto(ID) 
	ON DELETE CASCADE
    ON UPDATE CASCADE
);

create table Carrello(
	 EmailCliente varchar(320),
     IDProdotto varchar(10),
     quantita int not null,
     
      FOREIGN KEY (IDProdotto) REFERENCES Prodotto(ID),
      FOREIGN KEY (EmailCliente) REFERENCES Cliente(Email),
      Primary Key(EmailCliente,IDProdotto)
);



