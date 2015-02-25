

drop table if EXISTS bostader; # Om det finns en tidigare databas

create table bostader (
lan varchar(64),
objekttyp varchar(64),
adress varchar(64),
area float,
rum int,
pris float,
avgift float
);


INSERT INTO `bostader` (`lan`, `objekttyp`, `adress`, `area`, `rum`, `pris`, `avgift`) VALUES
('Stockholm', 'Bostadsrätt', 'Polhemsgatan 1', 30, 1, 1000000, 1234),
('Stockholm', 'Bostadsrätt', 'Polhemsgatan 2', 60, 2, 2000000, 2345),
('Stockholm', 'Villa', 'Storgatan 1', 130, 5, 1000000, 3456),
('Stockholm', 'Villa', 'Storgatan 2', 160, 6, 1000000, 3456),
('Uppsala', 'Bostadsrätt', 'Gröna gatan 1', 30, 1, 500000, 1234),
('Uppsala', 'Bostadsrätt', 'Gröna gatan 2', 60, 2, 1000000, 2345),
('Uppsala', 'Villa', 'Kungsängsvägen 1', 130, 5, 1000000, 3456),
('Uppsala', 'Villa', 'Kungsängsvägen 2', 160, 6, 1000000, 3456),
('Dalarna', 'Hyresrätt', '123 Lagergatan', 132, 4, 0, 9000),
('Blekinge', 'Hyresrätt', 'Drottninggatan 234', 51, 2, 0, 12000),
('Dalarna', 'Bostadsrätt', 'Dalarnavägen 32', 80, 3, 2500000, 4500),
('Dalarna', 'Villa', 'Tullegatan 21', 200, 6, 6000000, 8000),
('Stockholm', 'Hyresrätt', 'Birger Jarlsgatan 110', 1, 22, 0, 15000),
('Uppsala', 'Bostadsrätt', 'Järnvägsgatan 23', 70, 2, 1700000, 4000),
('Stockholm', 'Villa', 'Solnavägen 42', 140, 6, 10000000, 6000),
('Blekinge', 'Villa', 'Husgatan 20', 100, 4, 4000000, 3220),
('Stockholm', 'Bostadsrätt', 'Toppgatan 54', 3, 70, 2300000, 4200),
('Stockholm', 'Bostadsrätt', 'Opelgatan 243', 114, 4, 2300000, 5000);



