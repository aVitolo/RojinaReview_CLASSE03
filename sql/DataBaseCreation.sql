drop database if exists RojinaReview;
create database RojinaReview;

use RojinaReview;

create table Giornalista(
                            cf 				varchar(16),
                            nome 			varchar(30) not null,
                            cognome 		varchar(30) not null,
                            check (cf regexp '[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]'),
    check (nome regexp '[a-zA-Z ]{1,30}'),
    check (cognome regexp '[a-zA-Z\' ]{1,30}'),
	primary key(cf)
);

create table Gioco(
	titolo 			varchar(50),
	dataDiRilascio 	date,
	casaDiSviluppo	varchar(30) not null,
	mediaVoto		float(4,2) not null,
	numeroVoti		int not null,
	primary key(titolo)
);

create table Recensione(
	codice			int auto_increment,
    testo			varchar(100),
	giornalista		varchar(16),
	gioco   		varchar(50),
	titolo 			varchar(100) unique,
	voto 			float not null,
	dataCaricamento	date not null,
	foreign key(giornalista)
				references Giornalista(cf)
				on update cascade,
	foreign key(gioco)
				references Gioco(titolo)
				on delete cascade
				on update cascade,
	primary key(codice),
	check (voto<=10 and voto>=1)
);

create table Notizia(
	codice			int auto_increment,
    testo   		varchar(100),
	giornalista  	varchar(30),
	titolo			varchar(100) unique,
	dataCaricamento	date not null,
	foreign key(giornalista)
				references Giornalista(cf)
				on update cascade,
	primary key(codice)
);

create table Informare(
	giornalista		varchar(30),
	notizia 		varchar(100),
	gioco 			varchar(50),
	foreign key(giornalista)
				references Giornalista(cf)
				on update cascade,
	foreign key(notizia)
				references Notizia(titolo)
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

create table Giocare(
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

create table Appartenere(
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
	pass 			varchar(30) not null,
   	abbonato		boolean not null,
	nome			varchar(30),
	cognome			varchar(30),
	età				tinyint,
	primary key(email)
);

create table Commento(
	utente			varchar(30),
    testo			varchar(100),
    dataScrittura   datetime,
    codiceR			int,
    codiceN			int,
    foreign key(utente)
				references Utente(email),
	foreign key(codiceR)
				references Recensione(codice),
	foreign key(codiceN)
				references Notizia(codice),
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
				on update cascade,
	primary key(gioco,utente,dataVotazione),
	check (voto<=10 and voto>=1)
);

create table Pagamento(
	utente			varchar(30),
    numeroCarta		varchar(20),
    dataScadenza	date,
    cvv				varchar(4),
    foreign key(utente)
				references Utente(email),
	primary key(utente, numeroCarta)
);

create table Indirizzo(
	utente 			varchar(30),
	via				varchar(30),
	numeroCivico	smallint,
	città			varchar(30),
	cap				varchar(6),
    foreign key(utente)
				references Utente(email),
	primary key(utente, via, numeroCivico, città, cap)
);

create table Telefono(
	utente 			varchar(30),
	numero 			varchar(10),
	foreign key(utente)
				references Utente(email)
				on delete cascade
				on update cascade,
	primary key(utente,numero)
);

create table Ordine(
	numero			int auto_increment,
    stato			varchar(30),
    tracking		varchar(50), -- link che porta al sito tracking
    dataOrdine		date,
    pagamento    	varchar(20),
    utente			varchar(30),
    viaI			varchar(30),
    numeroCivicoI	smallint,
    cittàI 			varchar(30),
    capI			varchar(6),
    foreign key(utente, pagamento)
				references Pagamento(utente, numeroCarta),
	foreign key(utente)
				references Utente(email),
	foreign key(utente, viaI, numeroCivicoI, cittàI, capI)
				references Indirizzo(utente, via, numeroCivico, città, cap),
    primary key(numero)
);

create table Prodotto(
	id				int auto_increment,
    nome			varchar(30),
    descrizione		varchar(100), -- inteso come path al file di testo
	disponibilità  	int,
    prezzo			float,
	primary key(id)
);

create table Categoria(
	nome			varchar(30),
    descrizione		varchar(200), -- ???
    primary key(nome)
);

create table Riferire(
	prodotto		int,
    categoria		varchar(30),
    foreign key(prodotto)
				references Prodotto(id),
	foreign key(categoria)
				references Categoria(nome),
	primary key(prodotto, categoria)
);

create table StatoProdotto(
	prodotto		int,
    utente 			varchar(30),
    sconto			tinyint,
    quantità		tinyint,
    ordine			int,
    foreign key(utente)
				references Utente(email),
    foreign key(prodotto)
				references Prodotto(id),
	foreign key(ordine)
				references Ordine(numero),
	primary key(prodotto, utente)
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

insert into Giornalista values
("RFLMMV41B21G788A","Mario", "Dell'Orca"),
("RTZLHG79T12F569T","Carla", "Bianchi"),
("YPPCTD47P66H695P","Giovanni", "Verdi"),
("THTWDD89D51D869A","Franco", "Neri"),
("MYAZGB45L64M011B","Carlo", "Rossi"),
("KBZGPH54E49D411C","Lorenza", "Gialli"),
("JGRKNL55C61B376F","Paolo", "Rosati"),
("CNNZYX29B50Z119V","Marco", "Franca"),
("PRHBSV36R01F020X","Paolo", "Franca");

insert into Utente values
("venebroguppeu@yopmail.com","GamaOnix","J7uFRF8C", false, "Paolo", "Dell'Orca", 20),
("jaunnureudeilla@yopmail.com","AimZero","4W6dJAbp", false, "Giovanna", "Bianchi", 25),
("frefimeitromo@yopmail.com","ZeroVirus","g4RxfPpT", false, "Pio", "Verdi", null),
("ceuprofraucoudi@yopmail.com","ShadowWait","V3mvYtD2", false, "Franco", "Neri", 19),
("ceubujotawo@yopmail.com","PhantomEagle","9BS58XcD", true, null, null, null),
("gralameiddauquau@yopmail.com","GhostSteel","5ho2hDjG", false, "Lorenza", "Gialli", 18),
("febremoulaqui@yopmail.com","PredatorBeta","f2qXbPz8", false, "Christian", "Rosati", 26),
("zindre@yopmail.com","BlackDeath","7BSB8XcD", true,  null, null, null),
("cazzare@yopmail.com","AbyssWalker","5hoho2hDjG", true,  "Carmine", "Franca", 22),
("oefo@yopmail.com","Papiciacra","fonzqfoXbPz8", true, "Fonz", "Cretoso", 20);


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
("Dark Souls","2011-09-22","FromSoftware", 0, 0),
("Dark Souls II","2014-03-11","FromSoftware", 0, 0),
("Dark Souls III","2016-04-12","FromSoftware", 0, 0),
("Sekiro: Shadows Die Twice","2019-03-22","FromSoftware", 0, 0),
("FIFA 20","2019-09-27","EA", 0, 0),
("FIFA 21","2020-10-05","EA", 0, 0),
("FIFA 22","2021-09-26","EA", 0, 0),
("Mario Kart 8","2014-05-29","Nintendo EPD", 0, 0),
("The Legend of Zelda: Breath of the Wild","2017-03-03","Nintendo EPD", 0, 0),
("Bloodborne 2", null,"FromSoftware", 0, 0),
("Halo 2","2004-11-09","Bungie Studios", 0, 0),
("Halo 3","2007-09-25","Bungie Studios", 0, 0),
("Halo 4","2012-11-06","343 Industries", 0, 0),
("Halo 5","2015-10.27","343 Industries", 0, 0),
("Halo Infinite","2021-12-08","343 Industries", 0, 0);

insert into Giocare values
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

insert into Appartenere values
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

insert into Recensione values
("C:\Users\felin\OneDrive\Desktop\Carmine\darkSouls.txt","RFLMMV41B21G788A","Dark Souls","Recensione Dark Souls",8.7,"2011-10-22"),
("RTZLHG79T12F569T","Dark Souls II","Dark Souls torna a colpire",9,"2014-04-11"),
("YPPCTD47P66H695P","Dark Souls III","Recensione Darl Souls III",9,"2016-05-12"),
("THTWDD89D51D869A","Sekiro: Shadows Die Twice","Il gioco From Software più difficile di sempre",9,"2019-04-22"),
("MYAZGB45L64M011B","Halo 2","Master Chief è tornato più in forma che mai",9.5,"2004-11-16"),
("MYAZGB45L64M011B","Halo 3","Il ritorno di Master Chief",8.5 ,"2007-10-01"),
("MYAZGB45L64M011B","Halo 4","Recensione Halo 4",9,"2022-01-13"),
("MYAZGB45L64M011B","Halo 5","Recensione Halo 5: Guardians",8.8,"2022-01-3"),
("MYAZGB45L64M011B","Halo Infinite","Un ritorno epico",9,current_date());

insert into Notizia values
("MYAZGB45L64M011B","Halo Infinite e la community tossica","2022-01-24"),
("KBZGPH54E49D411C","La Mod italiana per Dark Souls Remastered arriva domani","2021-10-13"),
("JGRKNL55C61B376F","I tesori di casa Nintendo","2020-03-03"),
("CNNZYX29B50Z119V","Bloodborne 2, rumor o verità?","2019-09-12"),
("CNNZYX29B50Z119V","FIFA 21: Cosa ci aspettiamo","2022-01-10"),
("PRHBSV36R01F020X","FIFA 22: Cosa ci aspettiamo", "2022-01-29");

insert into Informare values
("MYAZGB45L64M011B","Halo Infinite e la community tossica","Halo Infinite"),
("KBZGPH54E49D411C","La Mod italiana per Dark Souls Remastered arriva domani","Dark Souls"),
("JGRKNL55C61B376F","I tesori di casa Nintendo","Mario Kart 8"),
("JGRKNL55C61B376F","I tesori di casa Nintendo","The Legend of Zelda: Breath of the Wild"),
("CNNZYX29B50Z119V","Bloodborne 2, rumor o verità?","Bloodborne 2"),
("CNNZYX29B50Z119V","FIFA 21: Cosa ci aspettiamo","FIFA 20"),
("CNNZYX29B50Z119V","FIFA 21: Cosa ci aspettiamo","FIFA 21"),
("PRHBSV36R01F020X","FIFA 22: Cosa ci aspettiamo","FIFA 21"),
("PRHBSV36R01F020X","FIFA 22: Cosa ci aspettiamo","FIFA 22");


insert into Voto values
("Dark Souls","venebroguppeu@yopmail.com",9,"2011-10-22"),
("Dark Souls II","venebroguppeu@yopmail.com",7,"2014-04-11"),
("Dark Souls III","venebroguppeu@yopmail.com",8,"2016-05-12"),
("Sekiro: Shadows Die Twice","venebroguppeu@yopmail.com",9,"2019-04-22"),
("FIFA 20","venebroguppeu@yopmail.com",6,"2019-10-27"),
("FIFA 21","venebroguppeu@yopmail.com",6,"2020-11-05"),
("FIFA 22","venebroguppeu@yopmail.com",6,"2021-10-26"),
("Mario Kart 8","venebroguppeu@yopmail.com",8,"2014-06-29"),
("The Legend of Zelda: Breath of the Wild","venebroguppeu@yopmail.com",9.5,"2017-03-03"),
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

("Sekiro: Shadows Die Twice","ceuprofraucoudi@yopmail.com",8.5,"2019-03-22"),

("Halo 2","ceubujotawo@yopmail.com",8,"2004-11-09"),
("Halo 3","ceubujotawo@yopmail.com",8,"2007-09-25"),
("Halo 4","ceubujotawo@yopmail.com",7,"2012-11-06"),
("Halo 5","ceubujotawo@yopmail.com",7,"2015-10.27"),
("Halo Infinite","ceubujotawo@yopmail.com",9,"2021-12-08"),

("Mario Kart 8","gralameiddauquau@yopmail.com",8.5,"2014-05-29"),
("The Legend of Zelda: Breath of the Wild","gralameiddauquau@yopmail.com",9,"2017-03-03"),

("Halo 4","ceubujotawo@yopmail.com",7,"2022-01-28"),
("Halo 5","ceubujotawo@yopmail.com",7,"2022-01-25"),
("Halo Infinite","ceubujotawo@yopmail.com",9,"2022-01-29");

insert into Commento values
("zindre@yopmail.com", "path1", "2022-04-15 22:58:20", 1, null),
("cazzare@yopmail.com", "path2", "2021-04-15 12:58:20", null, 2),
("zindre@yopmail.com", "path3", "2022-05-15 13:58:20", 1, null);

insert into Pagamento values
("cazzare@yopmail.com", "123456", "2024-05-11", "324"),
("zindre@yopmail.com", "432442", "2025-05-11", "231"),
("oefo@yopmail.com", "523521", "2027-03-11", "222");

insert into Indirizzo values
("cazzare@yopmail.com", "xxiv maggio", 342, "Poggiomarino", "80040"),
("oefo@yopmail.com", "mattarella", 232, "Striano", "80040"),
("zindre@yopmail.com", "cavour", 342, "Pontecagnano", "80020");

insert into Telefono values
("cazzare@yopmail.com","3215674357"),
("oefo@yopmail.com","4212674357"),
("zindre@yopmail.com","3115644357");

insert into Ordine values
("in consegna", "tracking1", "2022-05-12", "523521", "oefo@yopmail.com", "mattarella", "232", "Striano", "80040"),
("in transito", "tracking2", "2022-04-12", "123456", "cazzare@yopmail.com", "xxiv maggio", "342", "Poggiomarino", "80040"),
("consegnato", "tracking3", "2022-05-03", "432442", "zindre@yopmail.com", "cavour", "342", "Pontecagnano", "80020");

insert into Prodotto values
("Tazza Dark Souls", "path 1", 30, 15.00),
("Pupazzo Super Mario", "path 2", 10, 25.00),
("T-shirt Zelda", "path 3", 20, 30.00),
("Bracciale Bloodborne", "path 4", 50, 13.00),
("Pendente Elden Ring", "path 5", 70, 18.00);

insert into Categoria values
("Casa", "path 1"),
("Giocattoli", "path 2"),
("Abbigliamento", "path 3");

insert into Riferire values
(1, "Casa"),
(2, "Giocattoli"),
(3, "Abbigliamento"),
(4, "Abbigliamento"),
(5, "Abbigliamento");

insert into StatoProdotto values
("3", "cazzare@yopmail.com", 0, 3, null),
("1", "cazzare@yopmail.com", 0, 1, null),
("3", "oefo@yopmail.com", 0, 3, 1),
("5", "cazzare@yopmail.com", 0, 1, null),
("1", "zindre@yopmail.com", 0, 1, 3),
("4", "zindre@yopmail.com", 30, 2, 3),
("3", "zindre@yopmail.com", 0, 5, null),
("4", "cazzare@yopmail.com", 0, 3, 2);