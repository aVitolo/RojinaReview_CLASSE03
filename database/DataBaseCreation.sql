drop database if exists rojina;
create database rojina;

use rojina;

create table Videogiocatore(
   id               int auto_increment not null,
   email 			varchar(100) not null unique,
   password 		varchar(256) not null,
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
    password		varchar(256) not null,
    nome 			varchar(100) not null,
    cognome 		varchar(100) not null,
    immagine        varchar(100),
    verificato      int(1) not null,
    check (nome regexp '[a-zA-Z ]{1,30}'),
    check (cognome regexp '[a-zA-Z\' ]{1,30}'),
    primary key(id)
);

create table Manager(
    id 				int auto_increment not null,
    email			varchar(100) not null unique ,
    password		varchar(256) not null,
    nome 			varchar(100) not null,
    cognome 		varchar(100) not null,
    immagine        varchar(100),
    verificato      int(1) not null,
    check (nome regexp '[a-zA-Z ]{1,30}'),
    check (cognome regexp '[a-zA-Z\' ]{1,30}'),
    primary key(id)
);

create table Pagamento(
    nome			            varchar(100) not null,
    cognome			            varchar(100) not null,
    numeroCarta		            varchar(16) not null,
    dataScadenza	            date,
    id_videogiocatore		    int not null,
    foreign key(id_videogiocatore)
      references Videogiocatore(id),
    primary key(id_videogiocatore, numeroCarta)
);

create table Indirizzo(
    via				        varchar(100) not null,
    numeroCivico	        smallint not null,
    cap				        varchar(5) not null,
    città			        varchar(100) not null,
    id_videogiocatore       int not null,
    foreign key(id_videogiocatore)
      references Videogiocatore(id)
      on delete cascade
      on update cascade,
    primary key(id_videogiocatore, via, numeroCivico, città, cap)
);

create table Telefono(
    numero                 varchar(10) not null,
    id_videogiocatore      int not null,
    foreign key(id_videogiocatore)
     references Videogiocatore(id)
     on delete cascade
     on update cascade,
    primary key(id_videogiocatore,numero)
);

create table Videogioco(
    id              int auto_increment not null,
    titolo 			varchar(100) not null unique,
    dataDiRilascio 	date,
    casaDiSviluppo	varchar(30) not null,
    mediaVoto		float(4,2) not null,
    numeroVoti		int not null,
    copertina		varchar(100) not null,
    primary key(id)
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
    nome 			    varchar(100) not null unique ,
    testo			    text not null,
    immagine 		    varchar(100) not null,
    dataScrittura	    date not null,
    votoGiornalista	    float not null,
    id_giornalista	    int(100) not null,
    id_videogioco   	int unique not null,
    foreign key(id_giornalista)
       references Giornalista(id)
       on update cascade,
    foreign key(id_videogioco )
       references Videogioco(id)
       on update cascade,
    primary key(id),
    check (votoGiornalista<=10 and votoGiornalista>=1)
);

create table Notizia(
    id				    int auto_increment,
    nome 			    varchar(100) not null unique ,
    testo			    text not null,
    immagine 		    varchar(100) not null,
    dataScrittura	    date not null,
    id_giornalista	    int not null,
    foreign key(id_giornalista)
        references Giornalista(id)
        on update cascade,
    primary key(id)
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
  titolo 	    varchar(100),
  testo		    text not null,
  immagine 	    varchar(100),
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

create table Categoria(
    nome    varchar(30),
    primary key(nome)
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


create table Carrello(
    totale	                float not null,
    id_videogiocatore 	    int not null,
    foreign key(id_videogiocatore)
     references Videogiocatore(id),
    primary key(id_videogiocatore)
);

create table Ordine(
    id				            int auto_increment not null ,
    stato			            varchar(20) not null,
    dataOrdine		            date not null,
    totale			            float not null,
    numeroCarta_pagamento       varchar(20) not null,
    id_videogiocatore           int not null,
    via_videogiocatore			varchar(30) not null,
    numeroCivico_videogiocatore smallint not null,
    città_videogiocatore		varchar(30) not null,
    cap_videogiocatore			varchar(6) not null,
    foreign key(id_videogiocatore, numeroCarta_pagamento)
       references Pagamento(id_videogiocatore, numeroCarta),
    foreign key(id_videogiocatore)
       references Videogiocatore(id),
    foreign key(id_videogiocatore,via_videogiocatore,numeroCivico_videogiocatore,città_videogiocatore,cap_videogiocatore)
       references Indirizzo(id_videogiocatore, via, numeroCivico, città, cap),
    primary key(id)
);

create table Prodotto_Carrello(
    id_prodotto		        int not null,
    id_videogiocatore	    int not null,
    quantità 		        int not null,
    foreign key(id_videogiocatore)
      references Carrello(id_videogiocatore),
    foreign key(id_prodotto	)
      references Prodotto(id),
    primary key(id_videogiocatore,id_prodotto)
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
    id_videogiocatore       int not null,
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
    foreign key(id_videogiocatore)
       references Videogiocatore(id)
       on update cascade,
    primary key(id),
    check (voto<=10 and voto>=1)
);

create table Commento(
    id                      int auto_increment not null,
    testo			        tinytext not null,
    dataScrittura	        datetime not null,
    id_videogiocatore       int not null,
    id_prodotto		        int,
    id_recensione           int,
    id_notizia              int,
    foreign key(id_videogiocatore)
     references Videogiocatore(id)
     on delete cascade
     on update cascade,
    foreign key(id_prodotto)
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

create table Segnalazione(
    id_commento             int not null,
    id_videogiocatore       int not null,
    motivazione             varchar(50) not null,
    commentoAggiuntivo		tinytext,
    dataSegnalazione	    date not null,
    foreign key(id_videogiocatore)
        references Videogiocatore(id)
        on delete cascade
        on update cascade,
    foreign key(id_commento)
        references Commento(id)
        on delete cascade
        on update cascade,
    primary key(id_commento, id_videogiocatore)
);

insert into Manager (nome, cognome, email, password,verificato) values
    ("Andrea", "Vitolo", "zindre@gmail.com", SHA2('papapa',256),1),
    ("Carmine", "Iemmino", "carmineiemmino@gmail.com", SHA2('lalala',256),1),
    ("Carlo", "Colizzi", "carletto@gmail.com", SHA2('bobbaba',256),0);

insert into Giornalista (nome, cognome, email, password, immagine, verificato) values
    ("Mario", "Dell'Orca", "mario@gmail.com", SHA2('marietto',256), "./static/images/journalists/Dell'Orca.jpg",1),
    ("Carla", "Bianchi", "carletta@gmail.com", SHA2('xarla',256), "./static/images/journalists/Bianchi.jpg",1),
    ("Giovanni", "Verdi", "giuann@gmail.com", SHA2('johnny',256), "./static/images/journalists/Verdi.jpg",1),
    ("Franco", "Neri", "franco@gmail.com", SHA2('francuccio',256), "./static/images/journalists/Neri.jpg",1),
    ("Carlo", "Rossi", "carlrossi@gmail.com", SHA2('redcarl',256), "./static/images/journalists/Rossi.jpg",1),
    ("Lorenza", "Gialli", "lorenza@gmail.com", SHA2('lorenzina',256), "./static/images/journalists/Gialli.jpg",1),
    ("Giuseppe", "D'ambrosio", "presidente@gmail.com", SHA2('lorenzina',256), null,0);


insert into Videogiocatore(email,nickname,password,nome,cognome,immagine,bannato)  values
    ("venebroguppeu@yopmail.com","GamaOnix",SHA2('oemfshif',256), "Paolo", "Dell'Orca", "./static/images/users/GamaOnix.png",0),
    ("jaunnureudeilla@yopmail.com","AimZero",SHA2('efkmfeug',256), "Giovanna", "Bianchi", "./static/images/users/AimZero.png",0),
    ("frefimeitromo@yopmail.com","ZeroVirus",SHA2('vwnjviecwo',256), "Pio", "Verdi", "./static/images/users/ZeroVirus.png",0),
    ("ceuprofraucoudi@yopmail.com","FratmOMisterios",SHA2('nfhuofewoj',256), "Franco", "Neri",  "./static/images/users/FratmOMisterios.png",0),
    ("ceubujotawo@yopmail.com","PhantomEagle",SHA2('obufewgou',256), null, null,  "./static/images/users/PhantomEagle.png",0),
    ("gralameiddauquau@yopmail.com","GhostSteel",SHA2('nefihouefwpoj',256), "Lorenza", "Gialli",  "./static/images/users/GhostSteel.png",0),
    ("febremoulaqui@yopmail.com","PredatorBeta",SHA2('fnkebouewf',256), "Christian", "Rosati",  "./static/images/users/PredatorBeta.png",0),
    ("zindre@yopmail.com","BlackDeath",SHA2('jofhouefwpoj',256), null, null, "./static/images/users/BlackDeath.png",0),
    ("cazzare@yopmail.com","AbyssWalker",SHA2('cazzare',256), "Carmine", "Franca",  "./static/images/users/AbyssWalker.png",0),
    ("oefo@yopmail.com","Papiciacra",SHA2('piefwhouefoj',256), "Fonz", "Cretoso", "./static/images/users/Papiciacra.png",0),
    ("videogiocatore@gmail.com","User",SHA2('password',256), "Persona", "Normale", null,0);


insert into Indirizzo (id_videogiocatore,via,numeroCivico,città,cap) values
    (1, "xxiv maggio", 342, "Poggiomarino", "80040"),
    (3, "mattarella", 232, "Striano", "80040"),
    (2, "cavour", 342, "Pontecagnano", "80020");


insert into Piattaforma (nome) values
    ("Game Boy"),
    ("Game Boy Color"),
    ("Game Boy Advance"),
    ("Nintendo DS"),
    ("Nintendo 3DS"),
    ("Wii"),
    ("Wii U"),
    ("Nintendo Switch"),
    ("PlayStation"),
    ("PlayStation 2"),
    ("PlayStation 3"),
    ("PlayStation 4"),
    ("PlayStation 5"),
    ("Xbox"),
    ("Xbox 360"),
    ("Xbox One"),
    ("Xbox Series X/S");

insert into Genere (nome) values
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

insert into Videogioco (titolo,dataDiRilascio,casaDiSviluppo,mediaVoto,numeroVoti,copertina)values
    ("Dark Souls","2011-09-22","FromSoftware", 0, 0, "./static/images/games/Dark Souls.jpg"),
    ("Dark Souls II","2014-03-11","FromSoftware", 0, 0, "./static/images/games/Dark Souls II.jpg"),
    ("Dark Souls III","2016-04-12","FromSoftware", 0, 0, "./static/images/games/Dark Souls III.jpg"),
    ("Sekiro Shadows Die Twice","2019-03-22","FromSoftware", 0, 0, "./static/images/games/Sekiro Shadows Die Twice.jpg"),
    ("FIFA 20","2019-09-27","EA", 0, 0, "./static/images/games/FIFA 20.jpg"),
    ("FIFA 21","2020-10-05","EA", 0, 0, "./static/images/games/FIFA 21.jpg"),
    ("FIFA 22","2021-09-26","EA", 0, 0, "./static/images/games/FIFA 22.jpg"),
    ("Mario Kart 8","2014-05-29","Nintendo EPD", 0, 0, "./static/images/games/Mario Kart 8.jpg"),
    ("The Legend of Zelda Breath of the Wild","2017-03-03","Nintendo EPD", 0, 0, "./static/images/games/The Legend of Zelda Breath of the Wild.jpg"),
    ("Bloodborne 2", null,"FromSoftware", 0, 0, "./static/images/games/Bloodborne 2.jpg"),
    ("Halo 2","2004-11-09","Bungie Studios", 0, 0, "./static/images/games/Halo 2.jpg"),
    ("Halo 3","2007-09-25","Bungie Studios", 0, 0, "./static/images/games/Halo 3.jpg"),
    ("Halo 4","2012-11-06","343 Industries", 0, 0, "./static/images/games/Halo 4.jpg"),
    ("Halo 5","2015-10.27","343 Industries", 0, 0, "./static/images/games/Halo 5.jpg"),
    ("Halo Infinite","2021-12-08","343 Industries", 0, 0, "./static/images/games/Halo Infinite.jpg"),
    ("God of War", "2020-11-07", "Santa Monica Studio", 0, 0, "./static/images/games/God of War.jpg"),
    ("COD Black Ops 3", "2018-10-10", "Treyarch", 0, 0, "./static/images/games/COD Black Ops 3.jpg"),
    ("Rainbow Six Siege", "2017-05-11", "Ubisoft", 0, 0, "./static/images/games/Rainbow Six Siege.jpg"),
    ("Red Dead Redemption 2", "2019-03-02", "Rockstar Games", 0, 0, "./static/images/games/Red Dead Redemption 2.jpg"),
    ("GTA 5", "2015-09-15", "Rockstar Games", 0, 0, "./static/images/games/GTA 5.jpg"),
    ("Elden Ring", "2022-07-11", "From Software", 0, 0, "./static/images/games/Elden Ring.jpg");

insert into Videogioco_Piattaforma values
    (1,"Xbox 360"),
    (1,"Xbox One"),
    (1,"Nintendo Switch"),
    (2,"PlayStation 3"),
    (2,"PlayStation 4"),
    (2,"Xbox 360"),
    (2,"Xbox One"),
    (3,"PlayStation 4"),
    (3,"Xbox One"),
    (4,"PlayStation 4"),
    (4,"Xbox One"),
    (4,"PlayStation 5"),
    (5,"PlayStation 4"),
    (5,"Xbox One"),
    (5,"Nintendo Switch"),
    (6,"PlayStation 4"),
    (6,"PlayStation 5"),
    (6,"Xbox One"),
    (6,"Xbox Series X/S"),
    (6,"Nintendo Switch"),
    (7,"PlayStation 4"),
    (7,"PlayStation 5"),
    (7,"Xbox One"),
    (7,"Xbox Series X/S"),
    (7,"Nintendo Switch"),
    (8,"Nintendo Switch"),
    (8,"Wii U"),
    (9,"Nintendo Switch"),
    (9,"Wii U"),
    (11,"Xbox"),
    (12,"Xbox 360"),
    (12,"Xbox One"),
    (13,"Xbox 360"),
    (14,"Xbox One"),
    (15,"Xbox One"),
    (15,"Xbox Series X/S"),
    (16, "Xbox One"),
    (16, "Playstation 4"),
    (17, "Xbox One"),
    (17, "Playstation 4"),
    (18, "Xbox One"),
    (18, "Playstation 4"),
    (19, "Xbox One"),
    (19, "Playstation 4"),
    (19, "Nintendo Switch"),
    (20, "Playstation 4"),
    (20, "Xbox One"),
    (20, "Playstation 5"),
    (20, "Xbox Series X/S");

insert into Videogioco_Genere values
    (1,"Azione"),
    (1,"RPG"),
    (1,"Giocatore Singolo"),
    (1,"Multiplayer Online"),
    (2,"Azione"),
    (2,"RPG"),
    (2,"Giocatore Singolo"),
    (2,"Multiplayer Online"),
    (3,"Azione"),
    (3,"RPG"),
    (3,"Giocatore Singolo"),
    (3,"Multiplayer Online"),
    (4,"Avventura"),
    (4,"Giocatore Singolo"),
    (5,"Sport"),
    (5,"Giocatore Singolo"),
    (5,"Multiplayer Online"),
    (6,"Sport"),
    (6,"Giocatore Singolo"),
    (6,"Multiplayer Online"),
    (7,"Sport"),
    (7,"Giocatore Singolo"),
    (7,"Multiplayer Online"),
    (8,"Giocatore Singolo"),
    (8,"Multiplayer Online"),
    (8,"Simulazione"),
    (9,"Giocatore Singolo"),
    (9,"Avventura"),
    (11,"FPS"),
    (11,"Giocatore Singolo"),
    (11,"Multiplayer"),
    (11,"Multiplayer Online"),
    (12,"FPS"),
    (12,"Giocatore Singolo"),
    (12,"Multiplayer"),
    (12,"Multiplayer Online"),
    (13,"FPS"),
    (13,"Giocatore Singolo"),
    (13,"Multiplayer"),
    (13,"Multiplayer Online"),
    (14,"FPS"),
    (14,"Giocatore Singolo"),
    (14,"Multiplayer"),
    (14,"Multiplayer Online"),
    (15,"FPS"),
    (15,"Giocatore Singolo"),
    (15,"Multiplayer"),
    (15,"Multiplayer Online"),
    (16, "Giocatore Singolo"),
    (16, "Avventura"),
    (17, "FPS"),
    (17, "Multiplayer Online"),
    (18, "FPS"),
    (18, "Multiplayer Online"),
    (19, "Avventura"),
    (19, "Giocatore Singolo"),
    (19, "Azione"),
    (20, "Azione"),
    (20, "Multiplayer Online"),
    (20, "Giocatore Singolo"),
    (20, "VR");

insert into Recensione (testo, id_giornalista, id_videogioco, nome, votoGiornalista, dataScrittura,immagine) values
    ("Il seguito di Dark Souls I...", 2, 2,"Dark Souls torna a colpire",9,"2014-04-11", "./static/images/reviews/review-Dark Souls II.jpg"),
    ("Dark Souls 3 richiama i...", 4, 3,"Recensione Dark Souls III",9,"2016-05-12", "./static/images/reviews/review-Dark Souls III.jpg"),
    ("Sekiro nuova avventura diversa...",2, 4,"Il gioco From Software più difficile di sempre",9,"2019-04-22", "./static/images/reviews/review-Sekiro Shadows Die Twice.jpg"),
    ("Halo 2 fps...", 1, 11,"Master Chief è tornato più in forma che mai",9.5,"2004-11-16", "./static/images/reviews/review-Halo 2.jpg"),
    ("Halo 3 è indubbiamente...",  1, 12,"Il ritorno di Master Chief",8.5 ,"2007-10-01", "./static/images/reviews/review-Halo 3.jpg"),
    ("Halo 4 si avvicina a cod...", 3,13,"Recensione Halo 4",9,"2022-01-13", "./static/images/reviews/review-Halo 4.jpg"),
    ("Halo 5 non lo so...", 6,14,"Recensione Halo 5: Guardians",8.8,"2022-01-3", "./static/images/reviews/review-Halo 5.jpg"),
    ("Halo infinite figurati...", 5, 15,"Un ritorno epico",9,current_date(), "./static/images/reviews/review-Halo Infinite.jpg"),
    ("GTA 5 è un gioco molto violento...",4, 20, "La saga che non delude mai", 8, "2020-11-09", "./static/images/reviews/review-GTA 5.jpg"),
    ("In Rainbow è importante la tattica...", 2, 18, "Il miglior FPS tattico", 9, "2018-05-23", "./static/images/reviews/review-Rainbow Six Siege.jpg"),
    ("God of War è sempre una certezza...",3,16, "Il grande ritorno di Kratos", 9.5, "2021-03-11", "./static/images/reviews/review-God of War.jpg"),
    ("Black Ops 3 si prospetta come...",2,17, "La Treyarch ci lascia senza parole", 10, "2018-04-04", "./static/images/reviews/review-COD Black Ops 3.jpg"),
    ("RDR 2 è mastodontico...",6, 19, "Il GTA ambientato nell'old west", 9.3, "2021-06-11", "./static/images/reviews/review-Red Dead Redemption 2.jpg"),
    ("Zelda è ormai la nostra infanzia...", 3, 9, "Il capolavoro Nintendo", 9.5, "2022-03-11","./static/images/reviews/review-The Legend of Zelda Breath of the Wild.jpg"),
    ("Mario e corse connubio perfetto...", 5, 8, "Mario&Furious", 7.5, "2021-01-15", "./static/images/reviews/review-Mario Kart 8.jpg"),
    ("FIFA 21 si mostra come...", 4, 6, "Il solito gioco di calcio", 6, "2021-02-22", "./static/images/reviews/review-FIFA 21.jpg"),
    ("FIFA 20 non è il top...", 3, 5, "FIFA delude", 5, "2020-03-11", "./static/images/reviews/review-FIFA 20.jpg"),
    ("Dark Souls è un rpg di tutto...", 2,1, "Il miglior RPG", 9.5, "2018-01-10", "./static/images/reviews/review-Dark Souls.jpg");


insert into Notizia (testo, id_giornalista, nome, dataScrittura, immagine) values
    ("Halo Infinite è un gioco...", 1,  "Halo Infinite e la community tossica","2022-01-24", "./static/images/news/new-Halo Infinite e la community tossica.jpg"),
    ("Doppiaggio in italiano in...", 1,   "La Mod italiana per Dark Souls Remastered arriva domani","2021-10-13", "./static/images/news/new-La Mod italiana per Dark Souls Remastered arriva domani.jpg"),
    ("La nintendo...", 1, "I tesori di casa Nintendo","2020-03-03", "./static/images/news/new-I tesori di casa Nintendo.jpg"),
    ("Bloodborne 2 è molto atteso...", 2, "Bloodborne 2, rumor o verità","2019-09-12", "./static/images/news/new-Bloodborne 2, rumor o verità.jpg"),
    ("Fifa 21 fa sempre cagare...", 2, "FIFA 21 Cosa ci aspettiamo","2022-01-10", "./static/images/news/new-FIFA 21 Cosa ci aspettiamo.jpg"),
    ("Fifa 22 fa ancor più cagare...", 2,  "FIFA 22 Cosa ci aspettiamo", "2022-01-29", "./static/images/news/new-FIFA 22 Cosa ci aspettiamo.jpg");

insert into Videogioco_Notizia (id_notizia, id_videogioco) values
    (3,9),
    (3,8),
    (5,5),
    (6,6),
    (6,5);

insert into Pagamento (nome, cognome, id_videogiocatore, numeroCarta, dataScadenza) values
    ("Carmine", "Franca", 1, "1234567812345678", "2024-05-11"),
    ("Andrea", "Vitolo", 2,   "4324421243244212", "2025-05-11"),
    ("Fonz", "Cretoso", 3,      "5235213452352134", "2027-03-11");


insert into Telefono(id_videogiocatore,numero)values
     (9,"3215674357"),
     (10,"4212674357"),
     (8,"3115644357");

insert into Categoria(nome)  values
    ("Casa"),
    ("Giocattoli"),
    ("Abbigliamento");

insert into Prodotto (nome, descrizione, disponibilità, prezzo, immagine,  mediaVoto, numeroVoti,nome_categoria) values
    ("Tazza Dark Souls", "Simpatica tazza ispirata a...", 30, 15.00, "./static/images/products/Tazza Dark Souls.jpg", 0, 0,"Casa"),
    ("Pupazzo Super Mario", "Dolce pupazzo coccoloso di...", 10, 25.00, "./static/images/products/Pupazzo Super Mario.jpg", 0, 0,"Giocattoli"),
    ("T-shirt Zelda", "Splendida t-shirt di...", 20, 30.00, "./static/images/products/T-shirt Zelda.jpg", 0, 0,"Abbigliamento"),
    ("Bracciale Bloodborne", "Stiloso bracciale...", 50, 13.00, "./static/images/products/Bracciale Bloodborne.jpg", 0, 0,"Abbigliamento"),
    ("Pendente Elden Ring", "Realistico pendente...", 70, 18.00, "./static/images/products/Pendente Elden Ring.jpg", 0, 0,"Abbigliamento");

insert into Commento(id_videogiocatore,testo, dataScrittura,id_prodotto, id_recensione, id_notizia) values
    (8, "T-shirt quasi perfetta...", "2022-04-15 22:58:20",3,null,null),
    (9,  "Seguo questo link per avere sconti sui videogiochi....", "2018-01-22 12:58:20", 1,null,null),
    (10, "Tazza discreta poteva...", "2022-05-15 13:58:20", 1,null,null),
    (10, "Bracciale di bloodborne stupendo...", "2022-05-15 13:58:20",4,null,null),
    (8, "Halo a me non piace...", "2022-04-15 22:58:20",null,null, 1),
    (9, "Meglio Dark Souls 3 per me...", "2021-04-15 12:58:20",null,null, 2),
    (10, "Questo Halo è gasato...", "2022-05-15 13:58:20",null,null,1),
    (8, "Recensione fatta molto bene Dark Souls...", "2022-04-15 22:58:20", null,1,null),
    (9, "Seguo questo link per avere sconti sui videogiochi....", "2020-03-05 12:58:20", null,3,null),
    (10, "Qualcuno mi da un mano su Sekiro?...", "2022-05-15 13:58:20", null,3,null);

insert into Segnalazione(id_commento, id_videogiocatore, motivazione, commentoAggiuntivo, dataSegnalazione) values
    (2,1,"spam",null,"2018-01-22 13:58:20"),
    (2,2,"spam",null,"2018-01-22 13:58:22"),
    (9,1,"spam",null,"2020-03-05 13:58:20"),
    (9,2,"spam",null,"2018-01-22 13:58:22");

insert into Ordine (stato, dataOrdine, totale, numeroCarta_pagamento, id_videogiocatore, via_videogiocatore, numeroCivico_videogiocatore, città_videogiocatore, cap_videogiocatore) values
    ("in consegna", "2022-05-12",81.20 , "5235213452352134", 3, "mattarella", "232", "Striano", "80040"),
    ("in transito", "2022-04-12",73.80 , "1234567812345678", 1, "xxiv maggio", "342", "Poggiomarino", "80040"),
    ("consegnato", "2022-05-03",30.90 ,  "4324421243244212", 2, "cavour", "342", "Pontecagnano", "80020");

insert into Prodotto_Ordine (id_prodotto, id_ordine, prezzoAcquisto, quantità)values
    (3, 1, 12.00, 2),
    (1, 1, 9.40, 3),
    (1, 2, 22.10, 1),
    (3, 2, 6.90, 1),
    (2, 3, 8.80, 2),
    (3, 3, 13.30, 1);


