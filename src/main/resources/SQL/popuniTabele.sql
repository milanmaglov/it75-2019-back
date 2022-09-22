insert into liga
values(nextval('liga_id_seq'), 'Srpska liga', 'SRB');
insert into liga
values(nextval('liga_id_seq'), 'Spanska liga', 'ESP');
insert into liga
values(nextval('liga_id_seq'), 'Engleska liga', 'ENG');
insert into liga
values(nextval('liga_id_seq'), 'Francuska liga', 'FRA');
insert into liga
values(nextval('liga_id_seq'), 'Nemacka liga', 'GER');
insert into liga
values(nextval('liga_id_seq'), 'Americka liga', 'USA');
insert into liga
values(nextval('liga_id_seq'), 'Australijska liga', 'AUS');
insert into liga
values(nextval('liga_id_seq'), 'Brazilska liga', 'BRA');
insert into liga
values(nextval('liga_id_seq'), 'Portugalska liga', 'POR');
insert into liga
values(nextval('liga_id_seq'), 'Hrvatska liga', 'CRO');


insert into nacionalnost
values(nextval('nacionalnost_id_seq'), 'Srbin', 'SRB');
insert into nacionalnost
values(nextval('nacionalnost_id_seq'), 'Nemac', 'GER');
insert into nacionalnost
values(nextval('nacionalnost_id_seq'), 'Amerikanac', 'USA');
insert into nacionalnost
values(nextval('nacionalnost_id_seq'), 'Portugalac', 'POR');
insert into nacionalnost
values(nextval('nacionalnost_id_seq'), 'Italijan', 'ITA');
insert into nacionalnost
values(nextval('nacionalnost_id_seq'), 'Spanac', 'ESP');
insert into nacionalnost
values(nextval('nacionalnost_id_seq'), 'Hrvat', 'CRO');
insert into nacionalnost
values(nextval('nacionalnost_id_seq'), 'Britanac', 'GRB');
insert into nacionalnost
values(nextval('nacionalnost_id_seq'), 'Poljak', 'POL');
insert into nacionalnost
values(nextval('nacionalnost_id_seq'), 'Rus', 'RUS');

insert into tim
values(nextval('tim_id_seq'), 'Barselona', to_date('1.1.1876.','dd.mm.yyyy.'), 'Barselona', 2);
insert into tim
values(nextval('tim_id_seq'), 'Bajern Minhen', to_date('14.5.1892.','dd.mm.yyyy.'), 'Minhen', 5);
insert into tim
values(nextval('tim_id_seq'), 'Crvena Zvezda', to_date('5.3.1932.','dd.mm.yyyy.'), 'Beograd', 1);
insert into tim
values(nextval('tim_id_seq'), 'Liverpul', to_date('4.4.1799.','dd.mm.yyyy.'), 'Liverpul', 3);
insert into tim
values(nextval('tim_id_seq'), 'Mancester Siti', to_date('9.4.1789.','dd.mm.yyyy.'), 'Mancester', 3);
insert into tim
values(nextval('tim_id_seq'), 'Partizan', to_date('1.1.1906.','dd.mm.yyyy.'), 'Beograd', 1);

insert into igrac
values(nextval('igrac_id_seq'), 'Marko', 'Markovic', '23', to_date('1.1.1995.','dd.mm.yyyy.'), 1, 3);
insert into igrac
values(nextval('igrac_id_seq'), 'Roberto', 'Karlos', '5', to_date('1.1.1985.','dd.mm.yyyy.'), 4, 5);
insert into igrac
values(nextval('igrac_id_seq'), 'Lionel', 'Mesi', '102', to_date('1.1.1980.','dd.mm.yyyy.'), 6, 1);
insert into igrac
values(nextval('igrac_id_seq'), 'Kristijano', 'Ronaldo', '323', to_date('1.1.1982.','dd.mm.yyyy.'), 4, 5);
insert into igrac
values(nextval('igrac_id_seq'), 'Arnold', 'Svajnstajger', '87', to_date('1.1.1976.','dd.mm.yyyy.'), 2, 2);

insert into liga(id, naziv, oznaka) values (-100, 'test naziv', 'test oznaka');
insert into nacionalnost(id, naziv, skracenica) values (-100, 'test naziv', 'test skracenica');
insert into tim(id, naziv, osnovan, sediste, liga) values (-100, 'test naziv', to_date('22.04.2022.','dd.mm.yyyy'), 1000, 1);
insert into igrac(id, ime, prezime, broj_reg, datum_rodjenja, nacionalnost, tim) values (-100, 'test ime', 'test prezime', '11', to_date('22.04.2022.','dd.mm.yyyy'), 1, 1);
