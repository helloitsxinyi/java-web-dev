create table STAFF(uid integer, name varchar);
create table artefact(aid integer, name varchar);

INSERT INTO staff(uid, name) values (1,"abcd");
INSERT INTO staff(uid, name) values (2,"bcde");

INSERT INTO artefact(arte_id, name, staff_staff_id) values (1, "arte1", 1);
INSERT INTO artefact(arte_id, name, staff_staff_id) values (2, "arte2", 1);