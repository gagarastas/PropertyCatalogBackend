create table images(
id INT(10) unsigned AUTO_INCREMENT primary key,
path VARCHAR(50) NOt NULL
);

create table property_images(
property_id INT(10) unsigned,
image_id INT(10) unsigned,
constraint fk_property_id foreign key (property_id) references property(id),
constraint fk_image_id foreign key (image_id) references images(id)
)