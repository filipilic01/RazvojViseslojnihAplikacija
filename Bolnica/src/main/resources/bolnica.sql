INSERT INTO "bolnica"("id", "naziv", "adresa", "budzet")
values (nextval('bolnica_seq'), 'Specijalna bolnica Sveti Sava', 'Nemanjina 2, Beograd', '1400000');
INSERT INTO "bolnica"("id", "naziv", "adresa", "budzet")
values (nextval('bolnica_seq'), 'Univerzitetska decja klinika', 'Tirsova 10, Beograd', '2100000');
INSERT INTO "bolnica"("id", "naziv", "adresa", "budzet")
values (nextval('bolnica_seq'), 'Univerzitetski klinicki centar Srbije', 'Pasterova 2, Beograd', '5000000');
INSERT INTO "bolnica"("id", "naziv", "adresa", "budzet")
values (nextval('bolnica_seq'), 'KBC Dragisa Misovic', 'Heroja Milana Tepica 1, Beograd', '3500000');
INSERT INTO "bolnica"("id", "naziv", "adresa", "budzet")
values (nextval('bolnica_seq'), 'Klinika za psihijatriju', 'Hajduk Veljkova 1, Novi Sad', '1000000');

INSERT INTO "odeljenje"("id", "naziv", "lokacija", "bolnica")
values (nextval('odeljenje_seq'), 'Odeljenje za standardnu negu', 'Prizemlje', '1');
INSERT INTO "odeljenje"("id", "naziv", "lokacija", "bolnica")
values (nextval('odeljenje_seq'), 'Odeljenje za intenzivno lecenje', 'Prvi sprat', '1');
INSERT INTO "odeljenje"("id", "naziv", "lokacija", "bolnica")
values (nextval('odeljenje_seq'), 'Odeljenje A', 'Drugi sprat', '1');
INSERT INTO "odeljenje"("id", "naziv", "lokacija", "bolnica")
values (nextval('odeljenje_seq'), 'Odeljenje B', 'Drugi sprat', '1');
INSERT INTO "odeljenje"("id", "naziv", "lokacija", "bolnica")
values (nextval('odeljenje_seq'), 'Odeljenje za standardnu i poluintenzivnu negu', 'Treci sprat', '1');
INSERT INTO "odeljenje"("id", "naziv", "lokacija", "bolnica")
values (nextval('odeljenje_seq'), 'Odeljenje abdominalne hirurgije', 'Cetvrti sprat', '2');
INSERT INTO "odeljenje"("id", "naziv", "lokacija", "bolnica")
values (nextval('odeljenje_seq'), 'Endokrinologija', 'Prvi sprat', '2');
INSERT INTO "odeljenje"("id", "naziv", "lokacija", "bolnica")
values (nextval('odeljenje_seq'), 'Neonatologija', 'Treci sprat', '2');
INSERT INTO "odeljenje"("id", "naziv", "lokacija", "bolnica")
values (nextval('odeljenje_seq'), 'Urologija', 'Cetvrti sprat', '2');
INSERT INTO "odeljenje"("id", "naziv", "lokacija", "bolnica")
values (nextval('odeljenje_seq'), 'Odeljenje 1', 'Prvi sprat', '3');
INSERT INTO "odeljenje"("id", "naziv", "lokacija", "bolnica")
values (nextval('odeljenje_seq'), 'Odeljenje 2', 'Drugi sprat', '3');
INSERT INTO "odeljenje"("id", "naziv", "lokacija", "bolnica")
values (nextval('odeljenje_seq'), 'Odeljenje 3', 'Treci sprat', '3');
INSERT INTO "odeljenje"("id", "naziv", "lokacija", "bolnica")
values (nextval('odeljenje_seq'), 'Odeljenje za klinicku psihijatriju', 'Cetvrti sprat', '4');
INSERT INTO "odeljenje"("id", "naziv", "lokacija", "bolnica")
values (nextval('odeljenje_seq'), 'Odeljenje za adolescentnu psihijatriju', 'Cetvrti sprat', '4');
INSERT INTO "odeljenje"("id", "naziv", "lokacija", "bolnica")
values (nextval('odeljenje_seq'), 'Prijemno odeljenje', 'Prizemlje', '4');
INSERT INTO "odeljenje"("id", "naziv", "lokacija", "bolnica")
values (nextval('odeljenje_seq'), 'Odeljenje za sudsku psihijatriju', 'Prvi sprat', '5');
INSERT INTO "odeljenje"("id", "naziv", "lokacija", "bolnica")
values (nextval('odeljenje_seq'), 'Odeljenje za bolesti zavisnosti', 'Cetvrti sprat', '5');
INSERT INTO "odeljenje"("id", "naziv", "lokacija", "bolnica")
values (nextval('odeljenje_seq'), 'Odeljenje za anksiozne poremecaje', 'Treci sprat', '5');
INSERT INTO "odeljenje"("id", "naziv", "lokacija", "bolnica")
values (nextval('odeljenje_seq'), 'Odeljenje za poremecaje raspolozenja', 'Cetvrti sprat', '5');

INSERT INTO "dijagnoza"("id", "naziv", "opis", "oznaka")
values (nextval('dijagnoza_seq'), 'Dijagnoza 1 ', 'Ova dijagnoza se leci odredjenim lekovima', 'IK785');
INSERT INTO "dijagnoza"("id", "naziv", "opis", "oznaka")
values (nextval('dijagnoza_seq'), 'Dijagnoza 2 ', 'Ova dijagnoza se leci odredjenim lekovima', 'IS785');
INSERT INTO "dijagnoza"("id", "naziv", "opis", "oznaka")
values (nextval('dijagnoza_seq'), 'Dijagnoza 3 ', 'Ova dijagnoza se leci odredjenim lekovima', 'IF785');
INSERT INTO "dijagnoza"("id", "naziv", "opis", "oznaka")
values (nextval('dijagnoza_seq'), 'Dijagnoza 4 ', 'Ova dijagnoza se leci odredjenim lekovima', 'IKGH85');
INSERT INTO "dijagnoza"("id", "naziv", "opis", "oznaka")
values (nextval('dijagnoza_seq'), 'Dijagnoza 5 ', 'Ova dijagnoza se leci odredjenim lekovima', 'K7FFS85');
INSERT INTO "dijagnoza"("id", "naziv", "opis", "oznaka")
values (nextval('dijagnoza_seq'), 'Dijagnoza 6 ', 'Ova dijagnoza se leci odredjenim lekovima', 'IK7DA85');
INSERT INTO "dijagnoza"("id", "naziv", "opis", "oznaka")
values (nextval('dijagnoza_seq'), 'Dijagnoza 7 ', 'Ova dijagnoza se leci odredjenim lekovima', 'ISGK78');
INSERT INTO "dijagnoza"("id", "naziv", "opis", "oznaka")
values (nextval('dijagnoza_seq'), 'Dijagnoza 8 ', 'Ova dijagnoza se leci odredjenim lekovima', 'HDCK785');
INSERT INTO "dijagnoza"("id", "naziv", "opis", "oznaka")
values (nextval('dijagnoza_seq'), 'Dijagnoza 9 ', 'Ova dijagnoza se leci odredjenim lekovima', 'RTTE785');
INSERT INTO "dijagnoza"("id", "naziv", "opis", "oznaka")
values (nextval('dijagnoza_seq'), 'Dijagnoza 10 ', 'Ova dijagnoza se leci odredjenim lekovima', 'FDS695');

INSERT INTO "pacijent"("id", "ime", "prezime", "zdr_osiguranje", "datum_rodjenja", "odeljenje", "dijagnoza")
values (nextval('pacijent_seq'), 'Pera ', 'Peric', 'True', '1975-01-24', '1', '1');
INSERT INTO "pacijent"("id", "ime", "prezime", "zdr_osiguranje", "datum_rodjenja", "odeljenje", "dijagnoza")
values (nextval('pacijent_seq'), 'Milan ', 'Simic', 'True', '1954-11-04', '2', '10');
INSERT INTO "pacijent"("id", "ime", "prezime", "zdr_osiguranje", "datum_rodjenja", "odeljenje", "dijagnoza")
values (nextval('pacijent_seq'), 'Sara ', 'Maric', 'True', '2005-12-15', '6', '3');
INSERT INTO "pacijent"("id", "ime", "prezime", "zdr_osiguranje", "datum_rodjenja", "odeljenje", "dijagnoza")
values (nextval('pacijent_seq'), 'Eva ', 'Milic', 'True', '2005-01-01', '7', '4');
INSERT INTO "pacijent"("id", "ime", "prezime", "zdr_osiguranje", "datum_rodjenja", "odeljenje", "dijagnoza")
values (nextval('pacijent_seq'), 'Mirko ', 'Slavic', 'True', '1978-05-20', '10', '2');
INSERT INTO "pacijent"("id", "ime", "prezime", "zdr_osiguranje", "datum_rodjenja", "odeljenje", "dijagnoza")
values (nextval('pacijent_seq'), 'Dragan ', 'Srnic', 'True', '1955-06-11', '11', '5');
INSERT INTO "pacijent"("id", "ime", "prezime", "zdr_osiguranje", "datum_rodjenja", "odeljenje", "dijagnoza")
values (nextval('pacijent_seq'), 'Lena', 'Rosic', 'False', '1987-12-02', '13', '5');
INSERT INTO "pacijent"("id", "ime", "prezime", "zdr_osiguranje", "datum_rodjenja", "odeljenje", "dijagnoza")
values (nextval('pacijent_seq'), 'Stevan', 'Milanovic', 'True', '2002-03-17', '4', '6');
INSERT INTO "pacijent"("id", "ime", "prezime", "zdr_osiguranje", "datum_rodjenja", "odeljenje", "dijagnoza")
values (nextval('pacijent_seq'), 'Milos', 'Jovic', 'True', '1965-02-28', '16', '7');
INSERT INTO "pacijent"("id", "ime", "prezime", "zdr_osiguranje", "datum_rodjenja", "odeljenje", "dijagnoza")
values (nextval('pacijent_seq'), 'Divna ','Arsic', 'True', '1987-05-24', '18', '1');