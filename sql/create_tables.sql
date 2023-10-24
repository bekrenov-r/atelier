drop schema public cascade;
create schema public;

create table "user"(
    id serial primary key ,
    username text,
    password text
);

create table client(
    id serial primary key,
    first_name text,
    last_name text,
    email text,
    phone_number text,
    user_id int,
    constraint client_user_fk
        foreign key (user_id) references "user"(id)
);

create table client_metrics(
    client_id int,
    neck_semi_circumference float,
    chest_semi_circumference_1 float,
    chest_semi_circumference_2 float,
    chest_semi_circumference_3 float,
    waist_semi_circumference float,
    shoulder_width float,
    chest_height float,
    chest_height_1 float,
    back_armhole_height float,
    back_length_till_waist float,
    shoulder_height_sidelong float,
    chest_width float,
    chest_center float,
    back_width float,
    waist_length_front float,
    neck_base_to_front_waist_line_distance float,
    constraint client_client_metrics_fk
        foreign key (client_id) references client(id)
);

create table user_role(
    user_id text,
    role text
);

insert into "user"(username, password)
values
    ('petro.kovalenko@exmaple.com', '$2a$10$dGlirX4TKwGREM8iBzeWCONoU8tqy0QhYY1l/jzuq./pPKUzqw2wi'),
    ('maria.shevchenko@exmaple.com', '$2a$10$dGlirX4TKwGREM8iBzeWCONoU8tqy0QhYY1l/jzuq./pPKUzqw2wi'),
    ('andrii.melnyk@exmaple.com', '$2a$10$dGlirX4TKwGREM8iBzeWCONoU8tqy0QhYY1l/jzuq./pPKUzqw2wi');

insert into user_role(user_id, role)
values
    (1, 'CLIENT'),
    (2, 'CLIENT'),
    (3, 'CLIENT');

insert into client(first_name, last_name, email, phone_number, user_id)
values
    ('Petro', 'Kovalenko', 'petro.kovalenko@exmaple.com', '874203146', 1),
    ('Maria', 'Shevchenko', 'maria.shevchenko@exmaple.com', '745987631', 2),
    ('Andrii', 'Melnyk', 'andrii.melnyk@exmaple.com', '634795201', 3);