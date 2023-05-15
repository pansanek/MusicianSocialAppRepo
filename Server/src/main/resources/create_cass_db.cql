create KEYSPACE musician_social_app WITH replication = {'class':'NetworkTopologyStrategy', 'datacenter1' : 3};

CREATE TABLE User (
    id int,
    login text,
    password text,
    email text,
    about text,
    user_type text,
    type_id int,
    PRIMARY KEY(id)
);

CREATE TABLE Musician (
   id int,
   user_id int,
   band_id int,
   genres list<text>,
   instruments list<text>,
   PRIMARY KEY(id)
);

CREATE TABLE rep_adm (
  id int,
  user_id int,
  repetision_base_id int,
  PRIMARY KEY(id)
);

CREATE TABLE con_adm (
  id int,
  user_id int,
  concert_venue_id int,
  PRIMARY KEY(id)
);

CREATE TABLE rep_base (
  id int,
  admin_id int,
  address TEXT,
  base_info TEXT,
  PRIMARY KEY(id)
);

CREATE TABLE con_venue (
  id int,
  admin_id int,
  address TEXT,
  venue_info TEXT,
  PRIMARY KEY(id)
);

CREATE TABLE band (
  id int,
  member_ids list<int>,
  info TEXT,
  genres list<TEXT>,
  PRIMARY KEY(id)
);