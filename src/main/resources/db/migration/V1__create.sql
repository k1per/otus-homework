create table my_users
(
    id        SERIAL,
    username  varchar,
    first_name varchar,
    last_name  varchar,
    email     varchar,
    phone     varchar
);

insert into my_users
values (DEFAULT, 'k1p3r', 'Anton', 'Korenev', 'somemail@hehe.hoho', '112233')