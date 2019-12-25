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
	numeroCarta varchar(16) not null,
    tipo varchar(20) not null,
    EmailCliente varchar(320) not null,
  
  
    Primary Key(numeroCarta,EmailCliente),
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

use progettotsw;
insert into PROGETTOTSW.Prodotto value('01','Immagini/prodotti/erboristeria/sedanervNotte.jpeg','Erboristeria','SEDANERV NOTTE',7.65,55,'MIgliorare il riposo notturno',0);
insert into PROGETTOTSW.Prodotto value('02','Immagini/prodotti/erboristeria/camomillaBiologica.jpeg','Erboristeria','Camomilla biologica',6,456,'Infuso di Camomilla per alleviare los tress quotidiano',0);
insert into PROGETTOTSW.Prodotto value('03','Immagini/prodotti/erboristeria/theVerdeBiologico.jpeg','Erboristeria','The Verde Biologico',6,28,'infuso di the ottimo per tutti i momenti della giornata',0);
insert into PROGETTOTSW.Prodotto value('04','Immagini/prodotti/erboristeria/reumadol.jpeg','Erboristeria','Reumadol',15,100,'Contribuisce alla normale formazione del collagene per la corretta funzione di ossa e cartilagini',0);

insert into PROGETTOTSW.Prodotto value('11','Immagini/prodotti/integratori/hydraSalinum.jpeg','Integratori','Hydra Salinum',15,450,'Integratore indicato per ripristinare l equilibrio elettrolitico',0);
insert into PROGETTOTSW.Prodotto value('12','Immagini/prodotti/integratori/tantumVerdeNatura.jpeg','Integratori','Tantum verde Natura',10,500,'Integratore pensato per rafforzare il sistema immunitario',0);
insert into PROGETTOTSW.Prodotto value('13','Immagini/prodotti/integratori/polaseDifesaInverno.jpeg','Integratori','Polase Difesa Inverno',25,322,'Rafforzare le difese immunitarie contro l inverno',0);
insert into PROGETTOTSW.Prodotto value('14','Immagini/prodotti/integratori/susteniumBioritmo.jpeg','Integratori','Sustenium Bioritmo',16.5,56,'Ristabilisce il bioritmo naturale',0);

insert into PROGETTOTSW.Prodotto value('21','Immagini/prodotti/farmaciDaBanco/moment.jpeg','Farmaci da banco','Moment',6.99,213,'Contro dolore di varia origine e natura ',0);
insert into PROGETTOTSW.Prodotto value('22','Immagini/prodotti/farmaciDaBanco/momendol.jpeg','Farmaci da banco','Momendol',11.9,45,'contro dolore muscolare e mal di testa',0);
insert into PROGETTOTSW.Prodotto value('23','Immagini/prodotti/farmaciDaBanco/dicloreum.jpeg','Farmaci da banco','Dycloreum antifiammatorio',16.8,45,'Contro infiammazzioni localizzate',0);
insert into PROGETTOTSW.Prodotto value('24','Immagini/prodotti/farmaciDaBanco/fastum.jpeg','Farmaci da banco','Fastum antidolorifico',15.40,77,'Contro infiammazzioni muscolari e articolari',0);

insert into PROGETTOTSW.Prodotto value('31','Immagini/prodotti/igieneOrale/oralBSpazzolino.jpeg','Igiene orale','ORAL-B SPAZZOLINO',2.45,60,'Spazzolino indicato per la cura dei denti',0);
insert into PROGETTOTSW.Prodotto value('32','Immagini/prodotti/igieneOrale/aquafreshDentifricio.jpeg','Igiene orale','Aquafresh Dentifricio',13,56,'Dentifricio ottimo per denti sensibili',0);
insert into PROGETTOTSW.Prodotto value('33','Immagini/prodotti/igieneOrale/oralBPro.jpeg','Igiene orale','Oral-B Pro 600 Crossaction',47.5,58,'Spazzolino Elettrico Ricaricabile',0);
insert into PROGETTOTSW.Prodotto value('34','Immagini/prodotti/igieneOrale/dentifricioCarboneAttivo.jpeg','Igiene orale','Dentifricio al Carbone Attivo',10.99,263,'Ottimo per lo sbiancamento dentale',0);

insert into PROGETTOTSW.Prodotto value('05','Immagini/prodotti/erboristeria/BaseDiErbeAromaticheAllanice.jpg','Erboristeria','Base di erbe aromatiche',3.90,100,'per uso terapeutico, potendo beneficiare di proprietà stomachitiche, eupetiptiche, antidiarroiche e carminative.',0);
insert into PROGETTOTSW.Prodotto value('06','Immagini/prodotti/erboristeria/estrattoFunghi.jpeg','Erboristeria','Cordyceps',4,100,'Ottimo per ridurre il colesterolo totale ed aumentare il colesterolo HDL e migliorare la performance fisica, specie negli stati di aumentato stress;',0);
insert into PROGETTOTSW.Prodotto value('07','Immagini/prodotti/erboristeria/achilleaSommita.jpeg','Erboristeria','Achillea Sommita 100gr',4.40,55,'Contro disturbi derivati da cattiva circolazione , Emorroidi, Dolori mestruali e Gastrite',0);
insert into PROGETTOTSW.Prodotto value('08','Immagini/prodotti/erboristeria/Lithothamnium.jpg','Erboristeria','Lithothamnium',18,55,'Efficace nella prevenzione dell’osteoporosi e dei problemi osteoarticolari degenerativi come artrite e artrosi. ',0);
insert into PROGETTOTSW.Prodotto value('10','Immagini/prodotti/erboristeria/PropoliTintura.jpeg','Erboristeria','Propoli Tintura',10.40,55,'Tintura naturale ricca di oli essenziali',0);
insert into PROGETTOTSW.Prodotto value('41','Immagini/prodotti/erboristeria/ekoprop.jpeg','Erboristeria','ekoprop',7.65,55,'Combattere l allergia in modo naturale',0);



insert into PROGETTOTSW.Prodotto value('15','Immagini/prodotti/integratori/allergyDepurato.jpeg','Integratori','allergy Depurato',50,55,'Integratore durante periodi di allergia',0);
insert into PROGETTOTSW.Prodotto value('16','Immagini/prodotti/integratori/artiVita.jpeg','Integratori','Arti Vita',14.9,56,' ottimo per rinforzare le articolazioni, per curare i dolori reumatici.',0);
insert into PROGETTOTSW.Prodotto value('17','Immagini/prodotti/integratori/fitlessDrenante.jpeg','Integratori','Fitless Drenante',22,56,'Le erbe contenute nel prodotto hanno una azione dimagrante, depurativa, drenante , diuretica e bruciagrassi',0);
insert into PROGETTOTSW.Prodotto value('18','Immagini/prodotti/integratori/ultraMagnesio.jpeg','Integratori','Ultra Magnesio',18.5,56,'Ristabilisce le riserve di magnesio e altri minerali',0);
insert into PROGETTOTSW.Prodotto value('19','Immagini/prodotti/integratori/rinvofen.jpeg','Integratori','Rinfoven',16.5,56,'azione antiaggregante piastrinica e nello stesso tempo induce un aumento della tonicità muscolare dovuta in parte alla migliore distribuzione degli ioni di calcio.',0);
insert into PROGETTOTSW.Prodotto value('20','Immagini/prodotti/integratori/multicentrum.jpeg','Integratori','multicentrum',16,56,'Integratore alimentare multivitaminico e multiminerale, ideato per supportare le esigenze degli uomini adulti.',0);


insert into PROGETTOTSW.Prodotto value('25','Immagini/prodotti/farmaciDaBanco/tachipirinaCompresse.jpeg','Farmaci da banco','Tachipinia compresse',6.99,213,'trattamento sintomatico di affezioni febbrili quali influenza, e affezioni acute del tratto respiratorio, etc',0);
insert into PROGETTOTSW.Prodotto value('26','Immagini/prodotti/farmaciDaBanco/kalobaCompresse.jpeg','Farmaci da banco','Kaloba compresse',6.99,213,'Medicinale per attenuare il raffreddore comune.',0);
insert into PROGETTOTSW.Prodotto value('27','Immagini/prodotti/farmaciDaBanco/borocillina.jpeg','Farmaci da banco','NEO BOROCILLINA CON VITAMINA C',4,213,'Pastiglie on vitamina C senza zucchero, dal gradevole sapore, sono indicate come disinfettante del cavo orofaringeo  ',0);

insert into PROGETTOTSW.Prodotto value('28','Immagini/prodotti/farmaciDaBanco/paracetamolo.jpeg','Farmaci da banco','PARACETAMOLO 500 MG',2,213,'Analgesici ed antipiretici. ',0);
insert into PROGETTOTSW.Prodotto value('29','Immagini/prodotti/farmaciDaBanco/froben.jpeg','Farmaci da banco','FROBEN ACTIV ',6.5,213,'Pastiglie gusto limone e miele, si usa per alleviare sintomi quali irritazione e/o infiammazione della bocca e della faringe  ',0);
insert into PROGETTOTSW.Prodotto value('30','Immagini/prodotti/farmaciDaBanco/sinecod.jpeg','Farmaci da banco','sinecod tosse sedativo',5.9,213,'Pastiglie, indicate per calmare la tosse. Senza zucchero ',0);


insert into PROGETTOTSW.Prodotto value('35','Immagini/prodotti/igieneOrale/kitSbiancamento.jpeg','Igiene orale','Kit Sbiancamento denti',17,58,'Kit per lo sbiancamento dei denti',0);

insert into PROGETTOTSW.Prodotto value('36','Immagini/prodotti/igieneOrale/waterjet.jpeg','Igiene orale','Oral-B Waterjet',42.7,58,'Aiuta a migliorare la salute delle gengive massaggiandole e stimolandole con un getto d’acqua multiplo',0);
insert into PROGETTOTSW.Prodotto value('37','Immagini/prodotti/igieneOrale/antiplaccaCapitan.jpeg','Igiene orale','Pasta del Capitano Antiplacca ',1.5,58,'Antiplacca, Collutorio Quotidiano con Antibatterico, No Alcool.Confezione da: 400 ml',0);
insert into PROGETTOTSW.Prodotto value('38','Immagini/prodotti/igieneOrale/retainer.jpeg','Igiene orale','Retainer Brite pastiglie',47.5,58,'Ideale per Paradenti, Ortodontico Dispositivi e Protesi dentarie',0);
insert into PROGETTOTSW.Prodotto value('39','Immagini/prodotti/igieneOrale/creadent.jpeg','Igiene orale','creadent',14.9,58,'Kit per sostituire provvisoriamente un dente mancante',0);
insert into PROGETTOTSW.Prodotto value('40','Immagini/prodotti/igieneOrale/filoInterdentale.jpeg','Igiene orale','Filo interdentale cerato',2.5,58,'Filo interdentale cerato.Rimuove la placca batterica negli spazi interdentali e alla linea delle gengive.',0);





select * 
from PROGETTOTSW.prodotto;

/*
select * from Progettotsw.datianagrafici;

select * from progettotsw.cliente;

select * from progettotsw.indirizzospedizione;

select * from PROGETTOTSW.IndirizzoSpedizione where ID= "peppo@gmail.com";

ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'root';
*/

