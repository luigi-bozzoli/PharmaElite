drop database progettotsw;
create Database PROGETTOTSW;

use PROGETTOTSW;

create table Cliente( 
	Email varchar(256) PRIMARY KEY,
   	password varchar(20) not null,
	tipo boolean not null
);

create table Prodotto( 
	ID varchar(10) not null PRIMARY KEY,
	urlImmagine varchar(256) not null,
    categoria varchar(30) not null,
    nome varchar (30) not null,
    prezzo double(5,2) not null,
    quantità int not null,
    descrizione varchar(256) not null,
    flagEliminato boolean 
    
);

create table DatiAnagrafici( 
	nome varchar(30) not null,
    cognome varchar(30) not null,
    sesso varchar(10) not null,
    città varchar(30) not null,
    numero varchar(10) not null,
    EmailCliente varchar(320) primary key,
    FOREIGN KEY (EmailCliente) REFERENCES Cliente(Email)
);

create table IndirizzoSpedizione(
	ID varchar(10) not null primary key,
	indirizzo varchar(50) not null,
    EmailCliente varchar(320) not null,
    FOREIGN KEY (EmailCliente) REFERENCES Cliente(Email)

	
);


create table MetodoDiPagamento(
	numeroCarta varchar(16) not null primary key,
    tipo varchar(20) not null,
    EmailCliente varchar(320) not null,
  
    FOREIGN KEY (EmailCliente) REFERENCES Cliente(Email)
);

create table Ordine( 
	ID varchar(10),
    EmailCliente varchar(320),
    dataOrdine date not null,
    costo double(7,5) not null,
    primary key(ID, EmailCliente),
    FOREIGN KEY (EmailCliente) REFERENCES Cliente(Email)
);

create table ProdottoNellOrdine( 
	ID varchar(10) not null Primary key, 
    IDProdotto varchar(10), 
    nome varchar(30) not null,
    prezzo double(7,5) not null,
    
	FOREIGN KEY (IDProdotto) REFERENCES Prodotto(ID) 
);



create table Composizione( 
	IDOrdine varchar(10) not null , 
    IDProdotto varchar(10) not null , 
    Primary Key(IDOrdine,IDProdotto),
    
    
    FOREIGN KEY (IDOrdine) REFERENCES Ordine(ID),
	FOREIGN KEY (IDProdotto) REFERENCES Prodotto(ID) 
);



