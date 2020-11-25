create table property(
id INT(10) unsigned AUTO_INCREMENT primary key,
type VARCHAR(50) NOT NULL,
address VARCHAR(50) NOT NULL,
description TEXT);

create table information(
id INT(10) unsigned AUTO_INCREMENT primary key,
i_key VARCHAR(50) NOT NULL,
info_description VARCHAR(50) NOT NULL,
unique key uq_key1(i_key)
);

create table convenience(
id INT(10) unsigned AUTO_INCREMENT primary key,
c_key VARCHAR(50) NOT NULL,
convenience_description VARCHAR(50) NOT NULL,
unique key uq_key2(c_key)
);

create table property_information(
property_id INT(10) unsigned,
information_id INT(10) unsigned,
constraint fk_property foreign key (property_id) references property(id),
constraint fk_information foreign key (information_id) references information(id)
);

create table property_convenience(
property_id INT(10) unsigned,
convenience_id INT(10) unsigned,
constraint fk_property2 foreign key (property_id) references property(id),
constraint fk_convenience foreign key (convenience_id) references convenience(id)

);