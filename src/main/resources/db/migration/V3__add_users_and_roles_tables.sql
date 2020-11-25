CREATE TABLE ROLE
(
    id   INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR (50) NOT NULL,
    unique key uq_name(name)
);

CREATE TABLE USERS_T
(
    id            INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT ,
    login         VARCHAR(255) NOT NULL,
    hash_password VARCHAR(255) NOT NULL,
    unique key uq_login(login)
);

CREATE TABLE EMPLOYEE_ROLE
(
    user_id INT(10) UNSIGNED NOT NULL,
    role_id INT(10) UNSIGNED NOT NULL,
    constraint fk_users_t foreign key (user_id) references USERS_T(id),
    constraint fk_roles foreign key (role_id) references ROLE(id)
);
insert into ROLE(name)
values('user'), ('admin');
