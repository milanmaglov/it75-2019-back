drop table if exists liga cascade;
drop table if exists nacionalnost cascade;
drop table if exists tim cascade;
drop table if exists igrac cascade;
drop sequence if exists liga_id_seq;
drop sequence if exists nacionalnost_id_seq;
drop sequence if exists tim_id_seq;
drop sequence if exists igrac_id_seq;

create table liga(
id integer primary key,
naziv varchar(100),
oznaka varchar(50)
);

create table nacionalnost(
id integer primary key,
naziv varchar(100),
skracenica varchar(50)
);

create table tim(
id integer primary key,
naziv varchar(100),
osnovan date,
sediste varchar(50),
liga integer not null,
constraint fk_tim_liga foreign key(liga) references liga(id)
);

create table igrac(
id integer primary key,
ime varchar(50),
prezime varchar(50),
broj_reg varchar (50),
datum_rodjenja date,
nacionalnost integer not null,
tim integer not null,
constraint fk_igrac_nacionalnost foreign key(nacionalnost) references nacionalnost(id),
constraint fk_igrac_tim foreign key(tim) references tim(id)
);

create index idx_pk_liga on liga(id);
create index idx_pk_nacionalnost on nacionalnost(id);
create index idx_pk_tim on tim(id);
create index idx_pk_igrac on igrac(id);

create index idx_fk_tim_liga on tim(liga);
create index idx_fk_igrac_nacionalnost on igrac(nacionalnost);
create index idx_fk_igrac_tim on igrac(tim);

create sequence liga_id_seq
minvalue 0
start with 1;

create sequence nacionalnost_id_seq
minvalue 0
start with 1;

create sequence tim_id_seq
minvalue 0
start with 1;

create sequence igrac_id_seq
minvalue 0
start with 1;