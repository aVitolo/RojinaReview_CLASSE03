drop database if exists rojina;
create database rojina;

use rojina;

create table Videogiocatore(
   id               int auto_increment not null,
   email 			varchar(100) not null unique,
   pass 			varchar(256) not null,
   nome			    varchar(100),
   cognome			varchar(100),
   immagine         varchar(100),
   nickname 		varchar(30) not null unique,
   bannato          int(1),
   primary key(id)
);

create table Giornalista(
    id 				int auto_increment not null,
    email			varchar(100) not null unique ,
    pass			varchar(256) not null,
    nome 			varchar(100) not null,
    cognome 		varchar(100) not null,
    immagine        varchar(100),
    verificato      int(1),
    check (nome regexp '[a-zA-Z ]{1,30}'),
    check (cognome regexp '[a-zA-Z\' ]{1,30}'),
    primary key(id)
);

create table Manager(
    id 				int auto_increment not null,
    email			varchar(100) not null unique ,
    pass			varchar(256) not null,
    nome 			varchar(100) not null,
    cognome 		varchar(100) not null,
    immagine        varchar(100),
    verificato      int(1),
    check (nome regexp '[a-zA-Z ]{1,30}'),
    check (cognome regexp '[a-zA-Z\' ]{1,30}'),
    primary key(id)
);

create table Pagamento(
    nome			            varchar(100) not null,
    cognome			            varchar(100) not null,
    email_videogiocatore		varchar(100) not null,
    numeroCarta		            varchar(16) not null,
    dataScadenza	            date,
    foreign key(email_videogiocatore)
      references Videogiocatore(email),
    primary key(email_videogiocatore, numeroCarta)
);

create table Indirizzo(
    via				        varchar(100) not null,
    numeroCivico	        tinyint not null,
    cap				        varchar(5) not null,
    città			        varchar(100) not null,
    email_videogiocatore    varchar(100) not null,
    foreign key(email_videogiocatore)
      references Videogiocatore(email)
      on delete cascade
      on update cascade,
    primary key(email_videogiocatore, via, numeroCivico, città, cap)
);

create table Telefono(
    numero                 varchar(10) not null,
    email_videogiocatore   varchar(100) not null,
    foreign key(email_videogiocatore)
     references Videogiocatore(email)
     on delete cascade
     on update cascade,
    primary key(email_videogiocatore,numero)
);

create table Videogioco(
    id              int auto_increment not null,
    titolo 			varchar(100) not null unique,
    dataDiRilascio 	date,
    casaDiSviluppo	varchar(30) not null,
    mediaVoto		float(4,2) not null,
    numeroVoti		int not null,
    copertina		varchar(100),
    primary key(titolo)
);


create table Piattaforma(
    nome    varchar(30) not null,
    primary	key(nome)
);

create table Genere(
    nome    varchar(30) not null,
    primary key(nome)
);

create table Videogioco_Piattaforma(
    id_videogioco	int not null,
    piattaforma		varchar(30) not null,
    foreign key(id_videogioco)
      references Videogioco(id)
      on delete cascade
      on update cascade,
    foreign key(piattaforma)
      references Piattaforma(nome)
      on delete cascade
      on update cascade,
    primary key(id_videogioco, piattaforma)
);

create table Videogioco_Genere(
    id_videogioco	int not null,
    genere		varchar(30) not null,
    foreign key(id_videogioco)
      references Videogioco(id)
      on delete cascade
      on update cascade,
    foreign key(genere)
      references Genere(nome)
      on delete cascade
      on update cascade,
    primary key(id_videogioco, genere)
);


create table Recensione(
    id				    int auto_increment,
    titolo 			    varchar(100) not null unique ,
    testo			    text not null,
    immagine 		    varchar(100) not null,
    dataScrittura	    date not null,
    votoGiornalista	    float not null,
    email_giornalista	varchar(100) not null,
    id_videogioco   	int not null,
    foreign key(email_giornalista)
       references Giornalista(email)
       on update cascade,
    foreign key(id_videogioco )
       references Videogioco(id)
       on delete set null
       on update cascade,
    primary key(id),
    check (votoGiornalista<=10 and votoGiornalista>=1)
);

create table Notizia(
    id				    int auto_increment,
    titolo 			    varchar(100) not null unique ,
    testo			    text not null,
    immagine 		    varchar(100) not null,
    dataScrittura	    date not null,
    email_giornalista	varchar(100) not null,
    foreign key(email_giornalista)
        references Giornalista(email)
        on update cascade,
    primary key(id),
);

create table Videogioco_Notizia(
    id_notizia 		int not null,
    id_videogioco 	int not null,
    foreign key(id_notizia)
      references Notizia(id)
      on delete cascade
      on update cascade,
    foreign key(id_videogioco)
      references Videogioco(id)
      on delete cascade
      on update cascade,
    primary key(id_notizia, id_videogioco)
);

create table Paragrafo(
  id_paragrafo  int auto_increment not null,
  titolo 	    varchar(100) not null unique ,
  testo		    text not null,
  immagine 	    varchar(100) not null,
  id_notizia    int,
  id_recensione int,
  foreign key(id_notizia)
      references Notizia(id)
      on delete cascade
      on update cascade,
  foreign key(id_recensione)
      references Recensione(id)
      on delete cascade
      on update cascade,
  primary key(id_paragrafo)
);

create table Prodotto(
    id				int auto_increment not null,
    nome			varchar(100) not null unique,
    descrizione	    text not null,
    immagine		varchar(100) not null,
    prezzo			float not null,
    disponibilità   int not null,
    mediaVoto		float(4,2) not null,
    numeroVoti		int not null,
    nome_categoria  varchar(30) not null,
    foreign key(nome_categoria)
        references Categoria(nome)
        on update cascade,
    primary key(id)
);

create table Categoria(
    nome    varchar(30),
    primary key(nome)
);

create table Carrello(
    totale	                float not null,
    email_videogiocatore 	varchar(30) not null,
    foreign key(email_videogiocatore)
     references Videogiocatore(email),
    primary key(email_videogiocatore)
);

create table Ordine(
    id				            int auto_increment not null ,
    stato			            varchar(20) not null,
    dataOrdine		            date not null,
    totale			            float not null,
    numeroCarta_pagamento       varchar(20) not null,
    email_videogiocatore        varchar(30) not null,
    via_videogiocatore			varchar(30) not null,
    numeroCivico_videogiocatore smallint not null,
    città_videogiocatore		varchar(30) not null,
    cap_videogiocatore			varchar(6) not null,
    foreign key(email_videogiocatore, numeroCarta_pagamento)
       references Pagamento(email_videogiocatore, numeroCarta),
    foreign key(email_videogiocatore)
       references Videogiocatore(email),
    foreign key(email_videogiocatore,via_videogiocatore,numeroCivico_videogiocatore,città_videogiocatore,cap_videogiocatore)
       references Indirizzo(email_videogiocatore, via, numeroCivico, città, cap),
    primary key(id)
);

create table Prodotto_Carrello(
    id_prodotto		        int not null,
    email_videogiocatore	varchar(100) not null,
    quantità 		        int not null,
    foreign key(email_videogiocatore)
      references Carrello(email_videogiocatore),
    foreign key(id_prodotto	)
      references Prodotto(id),
    primary key(email_videogiocatore,id_prodotto)
);

create table Prodotto_Ordine(
    id_prodotto		int not null,
    id_ordine		int not null,
    prezzoAcquisto	float not null,
    quantità		tinyint not null,
    foreign key(id_prodotto)
        references Prodotto(id),
    foreign key(id_ordine)
        references Ordine(id),
    primary key(id_prodotto,id_ordine)
);

create table Parere(
    id                      int auto_increment not null,
    voto                    float not null,
    dataVotazione           date not null,
    email_videogiocatore    varchar(30) not null,
    id_videogioco           int,
    id_prodotto             int,
    foreign key(id_videogioco)
       references Videogioco(id)
        on delete cascade
        on update cascade,
    foreign key(id_prodotto)
        references Prodotto(id)
        on delete cascade
        on update cascade,
    foreign key(email_videogiocatore)
       references Videogiocatore(email)
       on update cascade,
    primary key(id),
    check (voto<=10 and voto>=1)
);

create table Commento(
    id                      int auto_increment not null,
    testo			        tinytext not null,
    dataScrittura	        date not null,
    email_videogiocatore    varchar(100) not null,
    id_prodotto		        int,
    id_recensione           int,
    id_notizia              int,
    foreign key(email_videogiocatore)
     references Videogiocatore(email)
     on delete cascade
     on update cascade,
    foreign key(id_prodotto	)
     references Prodotto(id)
     on delete cascade
     on update cascade,
    foreign key(id_recensione)
        references Prodotto(id)
        on delete cascade
        on update cascade,
    foreign key(id_notizia)
        references Prodotto(id)
        on delete cascade
        on update cascade,
    primary key(id)
);

create table Segnlazione(
    id_commento             int not null,
    email_videogiocatore    varchar(100) not null,
    motivazione             varchar(50) not null,
    commentoAggiuntivo		tinytext,
    dataSegnalazione	    date not null,
    foreign key(email_videogiocatore)
        references Videogiocatore(email)
        on delete cascade
        on update cascade,
    foreign key(id_commento)
        references Commento(id)
        on delete cascade
        on update cascade,
    primary key(id_commento, email_videogiocatore)
);

insert into Amministratore (nome, cognome, email, pass) values
                                                            ("Andrea", "Vitolo", "zindrè@gmail.com", SHA2('papapa',256)),
                                                            ("Carmine", "Iemmino", "carmineiemmino@gmail.com", SHA2('lalala',256)),
                                                            ("Carlo", "Colizzi", "carletto@gmail.com", SHA2('bobbaba',256));

insert into Giornalista (nome, cognome, email, pass, immagine) values
                                                                   ("Mario", "Dell'Orca", "mario@gmail.com", SHA2('marietto',256), "./images/journalists/Dell'Orca.jpg"),
                                                                   ("Carla", "Bianchi", "carletta@gmail.com", SHA2('xarla',256), "./images/journalists/Bianchi.jpg"),
                                                                   ("Giovanni", "Verdi", "giuann@gmail.com", SHA2('johnny',256), "./images/journalists/Verdi.jpg"),
                                                                   ("Franco", "Neri", "franco@gmail.com", SHA2('francuccio',256), "./images/journalists/Neri.jpg"),
                                                                   ("Carlo", "Rossi", "carlrossi@gmail.com", SHA2('redcarl',256), "./images/journalists/Rossi.jpg"),
                                                                   ("Lorenza", "Gialli", "lorenza@gmail.com", SHA2('lorenzina',256), "./images/journalists/Gialli.jpg");


insert into Utente values
                       ("venebroguppeu@yopmail.com","GamaOnix",SHA2('oemfshif',256), "Paolo", "Dell'Orca", 20, "./images/users/GamaOnix.png"),
                       ("jaunnureudeilla@yopmail.com","AimZero",SHA2('efkmfeug',256), "Giovanna", "Bianchi", 25, "./images/users/AimZero.png"),
                       ("frefimeitromo@yopmail.com","ZeroVirus",SHA2('vwnjviecwo',256), "Pio", "Verdi", null, "./images/users/ZeroVirus.png"),
                       ("ceuprofraucoudi@yopmail.com","FratmOMisterios",SHA2('nfhuofewoj',256), "Franco", "Neri", 19, "./images/users/FratmOMisterios.png"),
                       ("ceubujotawo@yopmail.com","PhantomEagle",SHA2('obufewgou',256), null, null, null, "./images/users/PhantomEagle.png"),
                       ("gralameiddauquau@yopmail.com","GhostSteel",SHA2('nefihouefwpoj',256), "Lorenza", "Gialli", 18, "./images/users/GhostSteel.png"),
                       ("febremoulaqui@yopmail.com","PredatorBeta",SHA2('fnkebouewf',256), "Christian", "Rosati", 26, "./images/users/PredatorBeta.png"),
                       ("zindre@yopmail.com","BlackDeath",SHA2('jofhouefwpoj',256), null, null, null, "./images/users/BlackDeath.png"),
                       ("cazzare@yopmail.com","AbyssWalker",SHA2('cazzare',256), "Carmine", "Franca", 22, "./images/users/AbyssWalker.png"),
                       ("oefo@yopmail.com","Papiciacra",SHA2('piefwhouefoj',256), "Fonz", "Cretoso", 20, "./images/users/Papiciacra.png"),
                       ("utente@gmail.com","User",SHA2('password',256), "Persona", "Normale", 20, null);


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
                          ("Picchiaduro"),
                          ("RPG"),
                          ("Simulazione"),
                          ("Sport"),
                          ("Strategia"),
                          ("VR"),
                          ("Wargame");

insert into Gioco values
                      ("Dark Souls","2011-09-22","FromSoftware", 0, 0, "./images/games/Dark Souls.jpg"),
                      ("Dark Souls II","2014-03-11","FromSoftware", 0, 0, "./images/games/Dark Souls II.jpg"),
                      ("Dark Souls III","2016-04-12","FromSoftware", 0, 0, "./images/games/Dark Souls III.jpg"),
                      ("Sekiro Shadows Die Twice","2019-03-22","FromSoftware", 0, 0, "./images/games/Sekiro Shadows Die Twice.jpg"),
                      ("FIFA 20","2019-09-27","EA", 0, 0, "./images/games/FIFA 20.jpg"),
                      ("FIFA 21","2020-10-05","EA", 0, 0, "./images/games/FIFA 21.jpg"),
                      ("FIFA 22","2021-09-26","EA", 0, 0, "./images/games/FIFA 22.jpg"),
                      ("Mario Kart 8","2014-05-29","Nintendo EPD", 0, 0, "./images/games/Mario Kart 8.jpg"),
                      ("The Legend of Zelda Breath of the Wild","2017-03-03","Nintendo EPD", 0, 0, "./images/games/The Legend of Zelda Breath of the Wild.jpg"),
                      ("Bloodborne 2", null,"FromSoftware", 0, 0, "./images/games/Bloodborne 2.jpg"),
                      ("Halo 2","2004-11-09","Bungie Studios", 0, 0, "./images/games/Halo 2.jpg"),
                      ("Halo 3","2007-09-25","Bungie Studios", 0, 0, "./images/games/Halo 3.jpg"),
                      ("Halo 4","2012-11-06","343 Industries", 0, 0, "./images/games/Halo 4.jpg"),
                      ("Halo 5","2015-10.27","343 Industries", 0, 0, "./images/games/Halo 5.jpg"),
                      ("Halo Infinite","2021-12-08","343 Industries", 0, 0, "./images/games/Halo Infinite.jpg"),
                      ("God of War", "2020-11-07", "Santa Monica Studio", 0, 0, "./images/games/God of War.jpg"),
                      ("COD Black Ops 3", "2018-10-10", "Treyarch", 0, 0, "./images/games/COD Black Ops 3.jpg"),
                      ("Rainbow Six Siege", "2017-05-11", "Ubisoft", 0, 0, "./images/games/Rainbow Six Siege.jpg"),
                      ("Red Dead Redemption 2", "2019-03-02", "Rockstar Games", 0, 0, "./images/games/Red Dead Redemption 2.jpg"),
                      ("GTA 5", "2015-09-15", "Rockstar Games", 0, 0, "./images/games/GTA 5.jpg");

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
                                  ("Sekiro Shadows Die Twice","PlayStation 4"),
                                  ("Sekiro Shadows Die Twice","Xbox One"),
                                  ("Sekiro Shadows Die Twice","PlayStation 5"),
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
                                  ("The Legend of Zelda Breath of the Wild","Nintendo Switch"),
                                  ("The Legend of Zelda Breath of the Wild","Wii U"),
                                  ("Halo 2","Xbox"),
                                  ("Halo 3","Xbox 360"),
                                  ("Halo 3","Xbox One"),
                                  ("Halo 4","Xbox 360"),
                                  ("Halo 5","Xbox One"),
                                  ("Halo Infinite","Xbox One"),
                                  ("Halo Infinite","Xbox Series X/S"),
                                  ("God of War", "Xbox One"),
                                  ("God of War", "Playstation 4"),
                                  ("COD Black Ops 3", "Xbox One"),
                                  ("COD Black Ops 3", "Playstation 4"),
                                  ("Rainbow Six Siege", "Xbox One"),
                                  ("Rainbow Six Siege", "Playstation 4"),
                                  ("Red Dead Redemption 2", "Xbox One"),
                                  ("Red Dead Redemption 2", "Playstation 4"),
                                  ("Red Dead Redemption 2", "Nintendo Switch"),
                                  ("GTA 5", "Playstation 4"),
                                  ("GTA 5", "Xbox One"),
                                  ("GTA 5", "Playstation 5"),
                                  ("GTA 5", "Xbox Series X/S");

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
                                ("Sekiro Shadows Die Twice","Avventura"),
                                ("Sekiro Shadows Die Twice","Giocatore Singolo"),
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
                                ("The Legend of Zelda Breath of the Wild","Giocatore Singolo"),
                                ("The Legend of Zelda Breath of the Wild","Avventura"),
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
                                ("Halo Infinite","Multiplayer Online"),
                                ("God of War", "Giocatore Singolo"),
                                ("God of War", "Avventura"),
                                ("COD Black Ops 3", "FPS"),
                                ("COD Black Ops 3", "Multiplayer Online"),
                                ("Rainbow Six Siege", "FPS"),
                                ("Rainbow Six Siege", "Multiplayer Online"),
                                ("Red Dead Redemption 2", "Avventura"),
                                ("Red Dead Redemption 2", "Giocatore Singolo"),
                                ("Red Dead Redemption 2", "Azione"),
                                ("GTA 5", "Azione"),
                                ("GTA 5", "Multiplayer Online"),
                                ("GTA 5", "Giocatore Singolo"),
                                ("GTA 5", "VR");

insert into Recensione (testo, giornalista, gioco, titolo, voto, dataCaricamento, immagine) values
("Il seguito di Dark Souls I...", 1, "Dark Souls II","Dark Souls torna a colpire",9,"2014-04-11", "./images/reviews/review-Dark Souls II.jpg"),
("Dark Souls 3 richiama i...", 3, "Dark Souls III","Recensione Dark Souls III",9,"2016-05-12", "./images/reviews/review-Dark Souls III.jpg"),
("Sekiro nuova avventura diversa...", 1, "Sekiro Shadows Die Twice","Il gioco From Software più difficile di sempre",9,"2019-04-22", "./images/reviews/review-Sekiro Shadows Die Twice.jpg"),
("Halo 2 fps...", 6, "Halo 2","Master Chief è tornato più in forma che mai",9.5,"2004-11-16", "./images/reviews/review-Halo 2.jpg"),
("Halo 3 è indubbiamente...", 5, "Halo 3","Il ritorno di Master Chief",8.5 ,"2007-10-01", "./images/reviews/review-Halo 3.jpg"),
("Halo 4 si avvicina a cod...", 2,"Halo 4","Recensione Halo 4",9,"2022-01-13", "./images/reviews/review-Halo 4.jpg"),
("Halo 5 non lo so...", 6, "Halo 5","Recensione Halo 5: Guardians",8.8,"2022-01-3", "./images/reviews/review-Halo 5.jpg"),
("Halo infinite figurati...", 4, "Halo Infinite","Un ritorno epico",9,current_date(), "./images/reviews/review-Halo Infinite.jpg"),
("GTA 5 è un gioco molto violento...", 3, "GTA 5", "La saga che non delude mai", 8, "2020-11-09", "./images/reviews/review-GTA 5.jpg"),
("In Rainbow è importante la tattica...", 1, "Rainbow Six Siege", "Il miglior FPS tattico", 9, "2018-05-23", "./images/reviews/review-Rainbow Six Siege.jpg"),
("God of War è sempre una certezza...", 2, "God of War", "Il grande ritorno di Kratos", 9.5, "2021-03-11", "./images/reviews/review-God of War.jpg"),
("Black Ops 3 si prospetta come...", 1, "COD Black Ops 3", "La Treyarch ci lascia senza parole", 10, "2018-04-04", "./images/reviews/review-COD Black Ops 3.jpg"),
("RDR 2 è mastodontico...", 5, "Red Dead Redemption 2", "Il GTA ambientato nell'old west", 9.3, "2021-06-11", "./images/reviews/review-Red Dead Redemption 2.jpg"),
("Zelda è ormai la nostra infanzia...", 2, "The Legend of Zelda Breath of the Wild", "Il capolavoro Nintendo", 9.5, "2022-03-11", "./images/reviews/review-The Legend of Zelda Breath of the Wild.jpg"),
("Mario e corse connubio perfetto...", 5, "Mario Kart 8", "Mario&Furious", 7.5, "2021-01-15", "./images/reviews/review-Mario Kart 8.jpg"),
("FIFA 21 si mostra come...", 3, "FIFA 21", "Il solito gioco di calcio", 6, "2021-02-22", "./images/reviews/review-FIFA 21.jpg"),
("FIFA 20 non è il top...", 2, "FIFA 20", "FIFA delude", 5, "2020-03-11", "./images/reviews/review-FIFA 20.jpg"),
("Dark Souls è un rpg di tutto...", 1, "Dark Souls", "Il miglior RPG", 9.5, "2018-01-10", "./images/reviews/review-Dark Souls.jpg");


insert into Notizia (testo, giornalista, titolo, dataCaricamento, immagine) values
("Halo Infinite è un gioco...", 2,  "Halo Infinite e la community tossica","2022-01-24", "./images/news/new-Halo Infinite e la community tossica.jpg"),
("Doppiaggio in italiano in...", 3,   "La Mod italiana per Dark Souls Remastered arriva domani","2021-10-13", "./images/news/new-La Mod italiana per Dark Souls Remastered arriva domani.jpg"),
("La nintendo...", 1, "I tesori di casa Nintendo","2020-03-03", "./images/news/new-I tesori di casa Nintendo.jpg"),
("Bloodborne 2 è molto atteso...", 6, "Bloodborne 2, rumor o verità","2019-09-12", "./images/news/new-Bloodborne 2, rumor o verità.jpg"),
("Fifa 21 fa sempre cagare...", 5, "FIFA 21 Cosa ci aspettiamo","2022-01-10", "./images/news/new-FIFA 21 Cosa ci aspettiamo.jpg"),
("Fifa 22 fa ancor più cagare...", 5,  "FIFA 22 Cosa ci aspettiamo", "2022-01-29", "./images/news/new-FIFA 22 Cosa ci aspettiamo.jpg"),
("Halo Infinite è un gioco...", 2,  "Halo Infinite e la community tossica","2022-01-24", "./images/news/new-Halo Infinite e la community tossica.jpg"),
("Doppiaggio in italiano in...", 3,   "La Mod italiana per Dark Souls Remastered arriva domani","2021-10-13", "./images/news/new-La Mod italiana per Dark Souls Remastered arriva domani.jpg"),
("La nintendo...", 1, "I tesori di casa Nintendo","2020-03-03", "./images/news/new-I tesori di casa Nintendo.jpg"),
("Bloodborne 2 è molto atteso...", 6, "Bloodborne 2, rumor o verità","2019-09-12", "./images/news/new-Bloodborne 2, rumor o verità.jpg"),
("Fifa 21 fa sempre cagare...", 5, "FIFA 21 Cosa ci aspettiamo","2022-01-10", "./images/news/new-FIFA 21 Cosa ci aspettiamo.jpg"),
("Fifa 22 fa ancor più cagare...", 5,  "FIFA 22 Cosa ci aspettiamo", "2022-01-29", "./images/news/new-FIFA 22 Cosa ci aspettiamo.jpg"),
("Halo Infinite è un gioco...", 2,  "Halo Infinite e la community tossica","2022-01-24", "./images/news/new-Halo Infinite e la community tossica.jpg"),
("Doppiaggio in italiano in...", 3,   "La Mod italiana per Dark Souls Remastered arriva domani","2021-10-13", "./images/news/new-La Mod italiana per Dark Souls Remastered arriva domani.jpg"),
("La nintendo...", 1, "I tesori di casa Nintendo","2020-03-03", "./images/news/new-I tesori di casa Nintendo.jpg"),
("Bloodborne 2 è molto atteso...", 6, "Bloodborne 2, rumor o verità","2019-09-12", "./images/news/new-Bloodborne 2, rumor o verità.jpg"),
("Fifa 21 fa sempre cagare...", 5, "FIFA 21 Cosa ci aspettiamo","2022-01-10", "./images/news/new-FIFA 21 Cosa ci aspettiamo.jpg"),
("Fifa 22 fa ancor più cagare...", 5,  "FIFA 22 Cosa ci aspettiamo", "2022-01-29", "./images/news/new-FIFA 22 Cosa ci aspettiamo.jpg"),
("Halo Infinite è un gioco...", 2,  "Halo Infinite e la community tossica","2022-01-24", "./images/news/new-Halo Infinite e la community tossica.jpg"),
("Doppiaggio in italiano in...", 3,   "La Mod italiana per Dark Souls Remastered arriva domani","2021-10-13", "./images/news/new-La Mod italiana per Dark Souls Remastered arriva domani.jpg"),
("La nintendo...", 1, "I tesori di casa Nintendo","2020-03-03", "./images/news/new-I tesori di casa Nintendo.jpg"),
("Bloodborne 2 è molto atteso...", 6, "Bloodborne 2, rumor o verità","2019-09-12", "./images/news/new-Bloodborne 2, rumor o verità.jpg"),
("Fifa 21 fa sempre cagare...", 5, "FIFA 21 Cosa ci aspettiamo","2022-01-10", "./images/news/new-FIFA 21 Cosa ci aspettiamo.jpg"),
("Fifa 22 fa ancor più cagare...", 5,  "FIFA 22 Cosa ci aspettiamo", "2022-01-29", "./images/news/new-FIFA 22 Cosa ci aspettiamo.jpg");


insert into Gioco_Notizia values
                              (1, 3,"The Legend of Zelda Breath of the Wild"),
                                (1, 3, "Mario Kart 8"),
                              (5, 5,"FIFA 20"),
                              (5, 6,"FIFA 21"),
                                (5, 6, "FIFA 20");





insert into CommentoNotizia values
                                ("zindre@yopmail.com", "Halo a me non piace...", "2022-04-15 22:58:20", 1),
                                ("cazzare@yopmail.com", "Meglio Dark Souls 3 per me...", "2021-04-15 12:58:20", 2),
                                ("oefo@yopmail.com", "Questo Halo è gasato...", "2022-05-15 13:58:20", 1);

insert into CommentoRecensione values
                                   ("zindre@yopmail.com", "Recensione fatta molto bene Dark Souls...", "2022-04-15 22:58:20", 1),
                                   ("cazzare@yopmail.com", "Sekiro è facile secondo me...", "2020-03-05 12:58:20", 3),
                                   ("oefo@yopmail.com", "Qualcuno mi da un mano su Sekiro?...", "2022-05-15 13:58:20", 3);

insert into Pagamento values
                          ("Carmine", "Franca", "cazzare@yopmail.com", "123456", "2024-05-11"),
                          ("Andrea", "Vitolo", "zindre@yopmail.com", "432442", "2025-05-11"),
                          ("Fonz", "Cretoso", "oefo@yopmail.com", "523521", "2027-03-11");


insert into Telefono values
                         ("cazzare@yopmail.com","3215674357"),
                         ("oefo@yopmail.com","4212674357"),
                         ("zindre@yopmail.com","3115644357");

insert into Prodotto (nome, descrizione, disponibilità, prezzo, immagine, sconto, mediaVoto, numeroVoti) values
("Tazza Dark Souls", "Simpatica tazza ispirata a...", 30, 15.00, "./images/products/Tazza Dark Souls.jpg", true, 0, 0),
("Pupazzo Super Mario", "Dolce pupazzo coccoloso di...", 10, 25.00, "./images/products/Pupazzo Super Mario.jpg", false, 0, 0),
("T-shirt Zelda", "Splendida t-shirt di...", 20, 30.00, "./images/products/T-shirt Zelda.jpg", false, 0, 0),
("Bracciale Bloodborne", "Stiloso bracciale...", 50, 13.00, "./images/products/Bracciale Bloodborne.jpg", true, 0, 0),
("Pendente Elden Ring", "Realistico pendente...", 70, 18.00, "./images/products/Pendente Elden Ring.jpg", false, 0, 0),
("Tazza Dark Souls", "Simpatica tazza ispirata a...", 30, 15.00, "./images/products/Tazza Dark Souls.jpg", true, 0, 0),
("Pupazzo Super Mario", "Dolce pupazzo coccoloso di...", 10, 25.00, "./images/products/Pupazzo Super Mario.jpg", false, 0, 0),
("T-shirt Zelda", "Splendida t-shirt di...", 20, 30.00, "./images/products/T-shirt Zelda.jpg", false, 0, 0),
("Bracciale Bloodborne", "Stiloso bracciale...", 50, 13.00, "./images/products/Bracciale Bloodborne.jpg", true, 0, 0),
("Pendente Elden Ring", "Realistico pendente...", 70, 18.00, "./images/products/Pendente Elden Ring.jpg", false, 0, 0),
("Tazza Dark Souls", "Simpatica tazza ispirata a...", 30, 15.00, "./images/products/Tazza Dark Souls.jpg", true, 0, 0),
("Pupazzo Super Mario", "Dolce pupazzo coccoloso di...", 10, 25.00, "./images/products/Pupazzo Super Mario.jpg", false, 0, 0),
("T-shirt Zelda", "Splendida t-shirt di...", 20, 30.00, "./images/products/T-shirt Zelda.jpg", false, 0, 0),
("Bracciale Bloodborne", "Stiloso bracciale...", 50, 13.00, "./images/products/Bracciale Bloodborne.jpg", true, 0, 0),
("Pendente Elden Ring", "Realistico pendente...", 70, 18.00, "./images/products/Pendente Elden Ring.jpg", false, 0, 0),
("Tazza Dark Souls", "Simpatica tazza ispirata a...", 30, 15.00, "./images/products/Tazza Dark Souls.jpg", true, 0, 0),
("Pupazzo Super Mario", "Dolce pupazzo coccoloso di...", 10, 25.00, "./images/products/Pupazzo Super Mario.jpg", false, 0, 0),
("T-shirt Zelda", "Splendida t-shirt di...", 20, 30.00, "./images/products/T-shirt Zelda.jpg", false, 0, 0),
("Bracciale Bloodborne", "Stiloso bracciale...", 50, 13.00, "./images/products/Bracciale Bloodborne.jpg", true, 0, 0),
("Pendente Elden Ring", "Realistico pendente...", 70, 18.00, "./images/products/Pendente Elden Ring.jpg", false, 0, 0),
("Tazza Dark Souls", "Simpatica tazza ispirata a...", 30, 15.00, "./images/products/Tazza Dark Souls.jpg", true, 0, 0),
("Pupazzo Super Mario", "Dolce pupazzo coccoloso di...", 10, 25.00, "./images/products/Pupazzo Super Mario.jpg", false, 0, 0),
("T-shirt Zelda", "Splendida t-shirt di...", 20, 30.00, "./images/products/T-shirt Zelda.jpg", false, 0, 0),
("Bracciale Bloodborne", "Stiloso bracciale...", 50, 13.00, "./images/products/Bracciale Bloodborne.jpg", true, 0, 0),
("Pendente Elden Ring", "Realistico pendente...", 70, 18.00, "./images/products/Pendente Elden Ring.jpg", false, 0, 0);



insert into CommentoProdotto values
                                 ("zindre@yopmail.com", "T-shirt quasi perfetta...", "2022-04-15 22:58:20", 3),
                                 ("cazzare@yopmail.com", "Tazza di Dark Souls molto carina...", "2018-01-22 12:58:20", 1),
                                 ("oefo@yopmail.com", "Tazza discreta poteva...", "2022-05-15 13:58:20", 1),
                                 ("oefo@yopmail.com", "Bracciale di bloodborne stupendo...", "2022-05-15 13:58:20", 4);


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