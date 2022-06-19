drop database if exists rojina;
create database rojina;

use rojina;

create table Amministratore(
    id 				tinyint auto_increment,
    nome 			varchar(30) not null,
    cognome 		varchar(30) not null,
    email			varchar(30),
    pass			varchar(2500),
    check (nome regexp '[a-zA-Z ]{1,30}'),
    check (cognome regexp '[a-zA-Z\' ]{1,30}'),
    primary key(id)
);

create table Giornalista(
    id 				tinyint auto_increment,
    nome 			varchar(30) not null,
    cognome 		varchar(30) not null,
    email			varchar(30),
    pass			varchar(2500),
    immagine        mediumblob,
    check (nome regexp '[a-zA-Z ]{1,30}'),
    check (cognome regexp '[a-zA-Z\' ]{1,30}'),
    primary key(id)
);

create table Gioco(
    titolo 			varchar(50),
    dataDiRilascio 	date,
    casaDiSviluppo	varchar(30) not null,
    mediaVoto		float(4,2) not null,
    numeroVoti		int not null,
    copertina		mediumblob,
    primary key(titolo)
);

create table Recensione(
    id				tinyint auto_increment,
    testo			text,
    giornalista		tinyint,
    gioco   		varchar(50),
    titolo 			varchar(100) unique,
    voto 			float not null,
    dataCaricamento	date not null,
    immagine 		mediumblob,
    foreign key(giornalista)
                references Giornalista(id)
                on update cascade,
   foreign key(gioco)
                references Gioco(titolo)
                on delete set null
                on update cascade,
   primary key(id),
   check (voto<=10 and voto>=1)
);

create table Notizia(
    id				tinyint auto_increment,
    testo   		text,
    giornalista  	tinyint,
    titolo			varchar(100) unique,
    dataCaricamento	date not null,
    immagine		mediumblob,
    foreign key(giornalista)
                references Giornalista(id)
                on update cascade,
    primary key(id)
);

create table Gioco_Notizia(
    giornalista		tinyint,
    notizia 		tinyint,
    gioco 			varchar(50),
    foreign key(giornalista)
                references Giornalista(id)
                on update cascade,
    foreign key(notizia)
                references Notizia(id)
                on delete cascade
                on update cascade,
    foreign key(gioco)
                references Gioco(titolo)
                on delete cascade
                on update cascade,
    primary key(giornalista, notizia, gioco)
);

create table Piattaforma(
    nome			varchar(30),
    brand 			varchar(30),
    primary	key(nome)
);

create table Tipologia(
    nome			varchar(30),
    primary key(nome)
);

create table Gioco_Piattaforma(
    gioco			varchar(50),
    piattaforma		varchar(30),
    foreign key(gioco)
                references Gioco(titolo)
                on delete cascade
                on update cascade,
    foreign key(piattaforma)
                references Piattaforma(nome)
                on delete cascade
                on update cascade,
    primary key(gioco, piattaforma)
);

create table Gioco_Tipologia(
    gioco			varchar(50),
    tipologia		varchar(30),
    foreign	key(gioco)
                references Gioco(titolo)
                on delete cascade
                on update cascade,
    foreign key(tipologia)
                references  Tipologia(nome)
                on delete cascade
                on update cascade,
    primary key(Gioco, Tipologia)
);

create table Utente(
   email 			varchar(30),
   nickname 		varchar(30) not null unique,
   pass 			varchar(256) not null,
   nome			    varchar(30),
   cognome			varchar(30),
   eta				tinyint,
   primary key(email)
);

create table Indirizzo(
    utente 			varchar(30),
    via				varchar(30),
    numeroCivico	smallint,
    città			varchar(30),
    cap				varchar(6),
    foreign key(utente)
                references Utente(email)
                on delete cascade
                on update cascade,
    primary key(utente, via, numeroCivico, città, cap)
);

create table Telefono(
    utente 			varchar(30),
    numero 			varchar(10),
    foreign key(utente)
                references Utente(email)
                on delete cascade
                on update cascade, -- da aggiungere regexp
    primary key(utente,numero)
);

create table CommentoNotizia(
    utente			varchar(30),
    testo			tinytext,
    dataScrittura   datetime,
    notizia			tinyint,
    foreign key(utente)
                references Utente(email)
                on delete cascade
                on update cascade,
    foreign key(notizia)
                references Notizia(id)
                on delete cascade
                on update cascade,
    primary key(utente, dataScrittura)
);

create table CommentoRecensione(
    utente 			varchar(30),
    testo			tinytext,
    dataScrittura	datetime,
    recensione		tinyint,
       foreign key(utente)
                   references Utente(email)
                   on delete cascade
                   on update cascade,
       foreign key(recensione)
                   references Recensione(id)
                   on delete cascade
                   on update cascade,
    primary key(utente, dataScrittura)
);

create table Voto(
    gioco  			varchar(50),
    utente  		varchar(30),
    voto 			float not null,
    dataVotazione	date,
    foreign key(gioco)
                references Gioco(titolo)
                on delete cascade
                on update cascade,
     foreign key(utente)
                 references Utente(email)
                 on delete cascade
                 on update cascade,
    primary key(gioco,utente,dataVotazione),
    check (voto<=10 and voto>=1)
);

create table Prodotto(
     id				int auto_increment,
     nome			varchar(30),
     descrizione	tinytext,
     disponibilità  int,
     prezzo			float,
     immagine		mediumblob,
     sconto			boolean,
     mediaVoto		float(4,2) not null,
     numeroVoti		int not null,
     primary key(id)
);

create table Gradimento(
    prodotto        int,
    utente  		varchar(30),
    voto 			float not null,
    dataVotazione	date,
    foreign key(prodotto)
        references Prodotto(id)
        on delete cascade
        on update cascade,
    foreign key(utente)
        references Utente(email)
        on delete cascade
        on update cascade,
    primary key(prodotto,utente,dataVotazione),
    check (voto<=10 and voto>=1)
);

create table Sconto(
   nome			    varchar(5), -- nome e percentuale vanno in coppia, esempio nome = 30%, percentuale = 0,70 per fare una semplice moltiplicazione
   percentuale      decimal(3, 2),
   prodotto		    int,
   foreign key(prodotto)
               references Prodotto(id),
   primary key(nome, prodotto)
);

create table Categoria(
  nome			varchar(30),
  primary key(nome)
);

create table Prodotto_Categoria(
    prodotto		int,
    categoria		varchar(30),
    foreign key(prodotto)
                references Prodotto(id),
    foreign key(categoria)
                references Categoria(nome),
    primary key(prodotto, categoria)
);

create table Pagamento(
  nome			    varchar(30),
  cognome			varchar(30),
  utente			varchar(30),
  numeroCarta		varchar(20),
  dataScadenza	    date,
  foreign key(utente)
              references Utente(email),
  primary key(utente, numeroCarta)
);

create table Ordine(
   id				int auto_increment,
   stato			varchar(30),
   tracking		    varchar(50), -- link che porta al sito tracking
   dataOrdine		date,
   totale			float,
   pagamento    	varchar(20),
   utente			varchar(30),
   via			    varchar(30),
   numeroCivico	    smallint,
   città			varchar(30),
   cap			    varchar(6),
   foreign key(utente, pagamento)
               references Pagamento(utente, numeroCarta),
   foreign key(utente)
               references Utente(email),
   foreign key(utente, via, numeroCivico, città, cap)
               references Indirizzo(utente, via, numeroCivico, città, cap),
   primary key(id)
);

create table Prodotto_Ordine(
    prodotto		int,
    ordine			int,
    prezzo			float,
    quantità		tinyint,
    foreign key(prodotto)
                references Prodotto(id),
    foreign key(ordine)
                references Ordine(id),
    primary key(prodotto, ordine)
);

create table Carrello(
     totale			float,
     utente 		varchar(30),
     foreign key(utente)
                 references Utente(email),
     primary key(utente)
);

create table Prodotto_Carrello(
      prodotto		    int,
      utente			varchar(30),
      quantità 		    tinyint,
      foreign key(utente)
                  references Carrello(utente),
      foreign key(prodotto)
                  references Prodotto(id),
      primary key(utente, prodotto)
);

DELIMITER //
create trigger AggiornaVoto
    before insert on Voto
    for each row
begin
    if (select count(*) from Voto where new.Utente=Utente and new.Gioco=Gioco) = 0 then

        update Gioco
        set  MediaVoto = (MediaVoto*NumeroVoti + new.Voto) / (NumeroVoti+1), NumeroVoti = NumeroVoti+1
        where new.Gioco=Titolo;

    else

        update Gioco
        set  MediaVoto = ( MediaVoto*NumeroVoti - (select Voto from Voto where (Gioco,Utente,DataVotazione) in (select Gioco,Utente,max(DataVotazione) from Voto where new.Utente=Utente and new.Gioco=Gioco group by Gioco,Utente)) + new.Voto) / NumeroVoti
        where new.Gioco=Titolo;
    end if;
end//
DELIMITER ;

DELIMITER //
create trigger AggiornaGradimento
    before insert on Gradimento
    for each row
begin
    if (select count(*) from Gradimento where new.Utente=Utente and new.prodotto=prodotto) = 0 then

        update Prodotto
        set  MediaVoto = (MediaVoto*NumeroVoti + new.Voto) / (NumeroVoti+1), NumeroVoti = NumeroVoti+1
        where new.prodotto=Prodotto.id;

    else

        update Prodotto
        set  MediaVoto = ( MediaVoto*NumeroVoti - (select voto from Gradimento where (prodotto,Utente,DataVotazione) in (select prodotto,utente,max(DataVotazione) from Gradimento where new.Utente=Utente and new.prodotto=prodotto group by prodotto,Utente)) + new.Voto) / NumeroVoti
        where new.prodotto=Prodotto.id;
    end if;
end//
DELIMITER ;

insert into Amministratore (nome, cognome, email, pass) values
("Andrea", "Vitolo", "zindrè@gmail.com", "papapa"),
("Carmine", "Iemmino", "carmineiemmino@gmail.com", "lalala"),
("Carlo", "Colizzi", "carletto@gmail.com", "bobobobo");

insert into Giornalista (nome, cognome) values
("Mario", "Dell'Orca"),
("Carla", "Bianchi"),
("Giovanni", "Verdi"),
("Franco", "Neri"),
("Carlo", "Rossi"),
("Lorenza", "Gialli"),
("Paolo", "Rosati"),
("Marco", "Franca"),
("Paolo", "Franca");

insert into Utente values
("venebroguppeu@yopmail.com","GamaOnix",SHA2('oemfshif',256), "Paolo", "Dell'Orca", 20),
("jaunnureudeilla@yopmail.com","AimZero",SHA2('efkmfeug',256), "Giovanna", "Bianchi", 25),
("frefimeitromo@yopmail.com","ZeroVirus",SHA2('vwnjviecwo',256), "Pio", "Verdi", null),
("ceuprofraucoudi@yopmail.com","ShadowWait",SHA2('nfhuofewoj',256), "Franco", "Neri", 19),
("ceubujotawo@yopmail.com","PhantomEagle",SHA2('obufewgou',256), null, null, null),
("gralameiddauquau@yopmail.com","GhostSteel",SHA2('nefihouefwpoj',256), "Lorenza", "Gialli", 18),
("febremoulaqui@yopmail.com","PredatorBeta",SHA2('fnkebouewf',256), "Christian", "Rosati", 26),
("zindre@yopmail.com","BlackDeath",SHA2('jofhouefwpoj',256), null, null, null),
("cazzare@yopmail.com","AbyssWalker",SHA2('pmiwefhoufpewj',256), "Carmine", "Franca", 22),
("oefo@yopmail.com","Papiciacra",SHA2('piefwhouefoj',256), "Fonz", "Cretoso", 20);


insert into Indirizzo values
("cazzare@yopmail.com", "xxiv maggio", 342, "Poggiomarino", "80040"),
("oefo@yopmail.com", "mattarella", 232, "Striano", "80040"),
("zindre@yopmail.com", "cavour", 342, "Pontecagnano", "80020");

insert into Telefono values
("cazzare@yopmail.com", "3348970852"),
("cazzare@yopmail.com", "3344030653"),
("zindre@yopmail.com", "3334977623"),
("oefo@yopmail.com", "3378470251"),
("ceuprofraucoudi@yopmail.com", "3398974621"),
("jaunnureudeilla@yopmail.com", "3318576452");


insert into Piattaforma values
("Game Boy", "Nintendo"),
("Game Boy Color", "Nintendo"),
("Game Boy Advance", "Nintendo"),
("Nintendo DS", "Nintendo"),
("Nintendo 3DS", "Nintendo"),
("Wii", "Nintendo"),
("Wii U", "Nintendo"),
("Nintendo Switch", "Nintendo"),
("PlayStation", "Sony"),
("PlayStation 2", "Sony"),
("PlayStation 3", "Sony"),
("PlayStation 4", "Sony"),
("PlayStation 5", "Sony"),
("Xbox", "Microsoft"),
("Xbox 360", "Microsoft"),
("Xbox One", "Microsoft"),
("Xbox Series X/S", "Microsoft");

insert into Tipologia values
("Altro"),
("Arcade"),
("Avventura"),
("Azione"),
("Coaching"),
("Coop online"),
("Cooperativa locale"),
("Cooperazione"),
("FPS"),
("Free To Play"),
("Gestione"),
("Giocatore Singolo"),
("Indies"),
("MMO"),
("Multiplayer"),
("Multiplayer Online"),
("Piacchiduro"),
("RPG"),
("Simulazione"),
("Sport"),
("Strategia"),
("VR"),
("Wargame");

insert into Gioco values
("Dark Souls","2011-09-22","FromSoftware", 0, 0, load_file('C:\ProgramData\MySQL\MySQL Server 8.0\Uploads\darksouls.jpg')),
("Dark Souls II","2014-03-11","FromSoftware", 0, 0, null),
("Dark Souls III","2016-04-12","FromSoftware", 0, 0, null),
("Sekiro: Shadows Die Twice","2019-03-22","FromSoftware", 0, 0, null),
("FIFA 20","2019-09-27","EA", 0, 0, null),
("FIFA 21","2020-10-05","EA", 0, 0, null),
("FIFA 22","2021-09-26","EA", 0, 0, null),
("Mario Kart 8","2014-05-29","Nintendo EPD", 0, 0, null),
("The Legend of Zelda: Breath of the Wild","2017-03-03","Nintendo EPD", 0, 0, null),
("Bloodborne 2", null,"FromSoftware", 0, 0, null),
("Halo 2","2004-11-09","Bungie Studios", 0, 0, null),
("Halo 3","2007-09-25","Bungie Studios", 0, 0, null),
("Halo 4","2012-11-06","343 Industries", 0, 0, null),
("Halo 5","2015-10.27","343 Industries", 0, 0, null),
("Halo Infinite","2021-12-08","343 Industries", 0, 0, null);

insert into Gioco_Piattaforma values
("Dark Souls","Xbox 360"),
("Dark Souls","Xbox One"),
("Dark Souls","Nintendo Switch"),
("Dark Souls II","PlayStation 3"),
("Dark Souls II","PlayStation 4"),
("Dark Souls II","Xbox 360"),
("Dark Souls II","Xbox One"),
("Dark Souls III","PlayStation 4"),
("Dark Souls III","Xbox One"),
("Sekiro: Shadows Die Twice","PlayStation 4"),
("Sekiro: Shadows Die Twice","Xbox One"),
("Sekiro: Shadows Die Twice","PlayStation 5"),
("FIFA 20","PlayStation 4"),
("FIFA 20","Xbox One"),
("FIFA 20","Nintendo Switch"),
("FIFA 21","PlayStation 4"),
("FIFA 21","PlayStation 5"),
("FIFA 21","Xbox One"),
("FIFA 21","Xbox Series X/S"),
("FIFA 21","Nintendo Switch"),
("FIFA 22","PlayStation 4"),
("FIFA 22","PlayStation 5"),
("FIFA 22","Xbox One"),
("FIFA 22","Xbox Series X/S"),
("FIFA 22","Nintendo Switch"),
("Mario Kart 8","Nintendo Switch"),
("Mario Kart 8","Wii U"),
("The Legend of Zelda: Breath of the Wild","Nintendo Switch"),
("The Legend of Zelda: Breath of the Wild","Wii U"),
("Halo 2","Xbox"),
("Halo 3","Xbox 360"),
("Halo 3","Xbox One"),
("Halo 4","Xbox 360"),
("Halo 5","Xbox One"),
("Halo Infinite","Xbox One"),
("Halo Infinite","Xbox Series X/S");

insert into Gioco_Tipologia values
("Dark Souls","Azione"),
("Dark Souls","RPG"),
("Dark Souls","Giocatore Singolo"),
("Dark Souls","Multiplayer Online"),
("Dark Souls II","Azione"),
("Dark Souls II","RPG"),
("Dark Souls II","Giocatore Singolo"),
("Dark Souls II","Multiplayer Online"),
("Dark Souls III","Azione"),
("Dark Souls III","RPG"),
("Dark Souls III","Giocatore Singolo"),
("Dark Souls III","Multiplayer Online"),
("Sekiro: Shadows Die Twice","Avventura"),
("Sekiro: Shadows Die Twice","Giocatore Singolo"),
("FIFA 20","Sport"),
("FIFA 20","Giocatore Singolo"),
("FIFA 20","Multiplayer Online"),
("FIFA 21","Sport"),
("FIFA 21","Giocatore Singolo"),
("FIFA 21","Multiplayer Online"),
("FIFA 22","Sport"),
("FIFA 22","Giocatore Singolo"),
("FIFA 22","Multiplayer Online"),
("Mario Kart 8","Giocatore Singolo"),
("Mario Kart 8","Multiplayer Online"),
("Mario Kart 8","Simulazione"),
("The Legend of Zelda: Breath of the Wild","Giocatore Singolo"),
("The Legend of Zelda: Breath of the Wild","Avventura"),
("Halo 2","FPS"),
("Halo 2","Giocatore Singolo"),
("Halo 2","Multiplayer"),
("Halo 2","Multiplayer Online"),
("Halo 3","FPS"),
("Halo 3","Giocatore Singolo"),
("Halo 3","Multiplayer"),
("Halo 3","Multiplayer Online"),
("Halo 4","FPS"),
("Halo 4","Giocatore Singolo"),
("Halo 4","Multiplayer"),
("Halo 4","Multiplayer Online"),
("Halo 5","FPS"),
("Halo 5","Giocatore Singolo"),
("Halo 5","Multiplayer"),
("Halo 5","Multiplayer Online"),
("Halo Infinite","FPS"),
("Halo Infinite","Giocatore Singolo"),
("Halo Infinite","Multiplayer"),
("Halo Infinite","Multiplayer Online");

insert into Recensione (testo, giornalista, gioco, titolo, voto, dataCaricamento, immagine) values
("Dark souls è un gioco rpg...", 1,"Dark Souls","Recensione Dark Souls",8.7,"2011-10-22", null),
("Il seguito di Dark Souls I...", 1, "Dark Souls II","Dark Souls torna a colpire",9,"2014-04-11", null),
("Dark Souls 3 richiama i...", 3, "Dark Souls III","Recensione Dark Souls III",9,"2016-05-12", null),
("Sekiro nuova avventura diversa...", 5, "Sekiro: Shadows Die Twice","Il gioco From Software più difficile di sempre",9,"2019-04-22", null),
("Halo 2 fps...", 7, "Halo 2","Master Chief è tornato più in forma che mai",9.5,"2004-11-16", null),
("Halo 3 è indubbiamente...", 1, "Halo 3","Il ritorno di Master Chief",8.5 ,"2007-10-01", null),
("Halo 4 si avvicina a cod...", 2,"Halo 4","Recensione Halo 4",9,"2022-01-13", null),
("Halo 5 non lo so...", 6, "Halo 5","Recensione Halo 5: Guardians",8.8,"2022-01-3", null),
("Halo infinite figurati...", 4, "Halo Infinite","Un ritorno epico",9,current_date(), null);

insert into Notizia (testo, giornalista, titolo, dataCaricamento, immagine) values
("Halo Infinite è un gioco...", 2, "Halo Infinite e la community tossica","2022-01-24", null),
("Doppiaggio in italiano in...", 3, "La Mod italiana per Dark Souls Remastered arriva domani","2021-10-13", null),
("La nintendo...", 1, "I tesori di casa Nintendo","2020-03-03", null),
("Bloodborne 2 è molto atteso...", 6, "Bloodborne 2, rumor o verità?","2019-09-12", null),
("Fifa 21 fa sempre cagare...", 5, "FIFA 21: Cosa ci aspettiamo","2022-01-10", null),
("Fifa 22 fa ancor più cagare...", 5, "FIFA 22: Cosa ci aspettiamo", "2022-01-29", null);

insert into Gioco_Notizia values
(2, 1,"Halo Infinite"),
(3, 2,"Dark Souls"),
(1, 3,"Mario Kart 8"),
(1, 3,"The Legend of Zelda: Breath of the Wild"),
(6, 4,"Bloodborne 2"),
(5, 5,"FIFA 20"),
(5, 5,"FIFA 21"),
(5, 6,"FIFA 21"),
(5, 6,"FIFA 22");


insert into Voto values
("Dark Souls","venebroguppeu@yopmail.com",9,"2011-10-22"),
("Dark Souls II","venebroguppeu@yopmail.com",7,"2014-04-11"),
("Dark Souls III","venebroguppeu@yopmail.com",8,"2016-05-12"),
("Sekiro: Shadows Die Twice","venebroguppeu@yopmail.com",9,"2019-04-22"),
("FIFA 20","venebroguppeu@yopmail.com",6,"2019-10-27"),
("FIFA 21","venebroguppeu@yopmail.com",6,"2020-11-05"),
("FIFA 22","venebroguppeu@yopmail.com",6,"2021-10-26"),
("Mario Kart 8","venebroguppeu@yopmail.com",8,"2014-06-29"),
("The Legend of Zelda: Breath of the Wild","venebroguppeu@yopmail.com",9,"2017-03-03"),
("Halo 2","venebroguppeu@yopmail.com",7,"2004-12-09"),
("Halo 3","venebroguppeu@yopmail.com",8,"2007-10-25"),
("Halo 4","venebroguppeu@yopmail.com",8,"2012-12-06"),
("Halo 5","venebroguppeu@yopmail.com",7,"2015-11.27"),
("Halo Infinite","venebroguppeu@yopmail.com",9,"2021-01-08"),

("Dark Souls","jaunnureudeilla@yopmail.com",8,"2011-10-22"),
("Dark Souls II","jaunnureudeilla@yopmail.com",6,"2014-04-11"),
("Dark Souls III","jaunnureudeilla@yopmail.com",9,"2016-05-12"),

("FIFA 20","frefimeitromo@yopmail.com",7,"2019-10-27"),
("FIFA 21","frefimeitromo@yopmail.com",7,"2020-11-05"),
("FIFA 22","frefimeitromo@yopmail.com",7,"2021-10-26"),

("Sekiro: Shadows Die Twice","ceuprofraucoudi@yopmail.com",8,"2019-03-22"),

("Halo 2","ceubujotawo@yopmail.com",8,"2004-11-09"),
("Halo 3","ceubujotawo@yopmail.com",8,"2007-09-25"),
("Halo 4","ceubujotawo@yopmail.com",7,"2012-11-06"),
("Halo 5","ceubujotawo@yopmail.com",7,"2015-10.27"),
("Halo Infinite","ceubujotawo@yopmail.com",9,"2021-12-08"),

("Mario Kart 8","gralameiddauquau@yopmail.com",8,"2014-05-29"),
("The Legend of Zelda: Breath of the Wild","gralameiddauquau@yopmail.com",9,"2017-03-03"),

("Halo 4","ceubujotawo@yopmail.com",7,"2022-01-28"),
("Halo 5","ceubujotawo@yopmail.com",7,"2022-01-25"),
("Halo Infinite","ceubujotawo@yopmail.com",9,"2022-01-29");

insert into CommentoNotizia values
("zindre@yopmail.com", "Halo a me non piace...", "2022-04-15 22:58:20", 1),
("cazzare@yopmail.com", "Meglio Dark Souls 3 per me...", "2021-04-15 12:58:20", 2),
("oefo@yopmail.com", "Questo Halo è gasato...", "2022-05-15 13:58:20", 1);

insert into CommentoRecensione values
("zindre@yopmail.com", "Recensione fatta molto bene Dark Souls...", "2022-04-15 22:58:20", 1),
("cazzare@yopmail.com", "Sekiro è facile secondo me...", "2021-04-15 12:58:20", 4),
("oefo@yopmail.com", "Qualcuno mi da un mano su Sekiro?...", "2022-05-15 13:58:20", 4);

insert into Pagamento values
("Carmine", "Franca", "cazzare@yopmail.com", "123456", "2024-05-11"),
("Andrea", "Vitolo", "zindre@yopmail.com", "432442", "2025-05-11"),
("Fonz", "Cretoso", "oefo@yopmail.com", "523521", "2027-03-11");


insert into Telefono values
("cazzare@yopmail.com","3215674357"),
("oefo@yopmail.com","4212674357"),
("zindre@yopmail.com","3115644357");

insert into Prodotto (nome, descrizione, disponibilità, prezzo, immagine, sconto, mediaVoto, numeroVoti) values
("Tazza Dark Souls", "Simpatica tazza ispirata a...", 30, 15.00, null, true, 0, 0),
("Pupazzo Super Mario", "Dolce pupazzo coccoloso di...", 10, 25.00, null, false, 0, 0),
("T-shirt Zelda", "Splendida t-shirt di...", 20, 30.00, null, false, 0, 0),
("Bracciale Bloodborne", "Stiloso bracciale...", 50, 13.00, null, true, 0, 0),
("Pendente Elden Ring", "Realistico pendente...", 70, 18.00, null, false, 0, 0);

insert into Gradimento values
(1, "cazzare@yopmail.com", 8, "2022-05-12"),
(1, "oefo@yopmail.com", 6, "2022-06-12"),
(2, "cazzare@yopmail.com", 5, "2022-05-17"),
(3, "zindre@yopmail.com", 9, "2022-05-02"),
(4, "cazzare@yopmail.com", 10, "2022-03-12"),
(4, "oefo@yopmail.com", 8, "2022-02-12");


insert into Sconto values
("30%", 0.7, 1),
("22%", 0.78, 4);

insert into Categoria values
("Casa"),
("Giocattoli"),
("Abbigliamento");

insert into Prodotto_Categoria values
(1, "Casa"),
(2, "Giocattoli"),
(3, "Abbigliamento"),
(4, "Abbigliamento"),
(5, "Abbigliamento");

insert into Carrello values
(34.00, "cazzare@yopmail.com"),
(17.50, "oefo@yopmail.com"),
(12.30, "venebroguppeu@yopmail.com");

insert into Prodotto_Carrello values
(2, "venebroguppeu@yopmail.com", 1),
(4, "venebroguppeu@yopmail.com", 1),
(1, "venebroguppeu@yopmail.com", 5),
(2, "oefo@yopmail.com", 3),
(5, "oefo@yopmail.com", 6),
(3, "venebroguppeu@yopmail.com", 1),
(4, "cazzare@yopmail.com", 2);

insert into Ordine (stato, tracking, dataOrdine, totale, pagamento, utente, via, numeroCivico, città, cap) values
("in consegna", "tracking1", "2022-05-12", 31.00, "523521", "oefo@yopmail.com", "mattarella", "232", "Striano", "80040"),
("in transito", "tracking2", "2022-04-12", 24.30, "123456", "cazzare@yopmail.com", "xxiv maggio", "342", "Poggiomarino", "80040"),
("consegnato", "tracking3", "2022-05-03", 42.20, "432442", "zindre@yopmail.com", "cavour", "342", "Pontecagnano", "80020");

insert into Prodotto_Ordine values
(3, 1, 12.00, 2),
(1, 1, 9.40, 3),
(3, 3, 13.30, 1),
(5, 1, 14.50, 2),
(1, 2, 22.10, 1),
(4, 2, 11.20, 4),
(3, 2, 6.90, 1),
(4, 3, 8.80, 2);