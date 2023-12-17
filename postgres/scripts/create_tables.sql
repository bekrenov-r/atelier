drop schema public cascade;
create schema public;

create table "user"(
    id serial primary key ,
    username text,
    password text,
    active bool
);

create table client(
    id serial primary key,
    first_name text,
    last_name text,
    email text,
    user_id int,
    constraint client_user_fk
        foreign key (user_id) references "user"(id)
);

create table employee(
    id serial primary key,
    first_name text,
    last_name text,
    email text,
    phone_number text,
    registered_at timestamp,
    user_id int,
    constraint employee_user_fk
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
    constraint client_metrics_client_fk
        foreign key (client_id) references client(id)
);

create table address(
    id serial primary key,
    city text,
    street text,
    building_number text,
    apartment_number text,
    zip_code text
);

create table client_details(
    client_id int,
    birth_date date,
    phone_number text,
    address_id int,
    constraint client_details_client_fk
        foreign key (client_id) references client(id),
    constraint client_details_address_fk
        foreign key (address_id) references address(id)
);

create table product_metrics(
    id serial primary key ,
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
    increase_to_width_by_chest_line float,
    increase_to_armhole_depth float,
    increase_to_neck_back float
);

create table user_role(
    user_id int,
    role text
);

create table registration_token(
    id bigserial,
    token text,
    user_id int
);

create table coat_model(
    id serial primary key,
    name text,
    price float,
    coat_type text,
    img_path text,
    video_url text
);

create table pattern_data (
    id serial primary key,
    basis_grid_width float,
    basis_grid_length float,
    armhole_depth float,
    back_width float,
    file_width float,
    armhole_width float,
    back_neck_width float,
    back_neck_height float,
    shoulder_cut_slope float,
    shoulder_cut_end float,
    side_slope_top float,
    back_armhole_slope float,
    product_balance float,
    file_neck_width float,
    file_neck_depth float,
    chest_dart float,
    shoulder_slope float,
    armhole float,
    total_dart_deviation_by_waist_line float,
    side_dart float,
    file_dart float,
    back_dart float,
    increase_to_width_by_chest_line float,
    increase_to_width_by_waist_line float,
    increase_to_armhole_width float,
    increase_to_armhole_depth float,
    increase_to_neck_back float,
    increase_to_back_width float,
    increase_to_file_width float
);

create table "order"(
    id serial primary key,
    client_id int,
    employee_id int,
    coat_model_id int,
    pattern_data_id int,
    product_metrics_id int,
    created_at timestamp,
    status text,
    img_path text,
    constraint fk_order_client
        foreign key (client_id) references client(id),
    constraint fk_order_employee
        foreign key (employee_id) references employee(id),
    constraint fk_order_coat_model
        foreign key (coat_model_id) references coat_model(id),
    constraint fk_order_pattern_data
        foreign key (pattern_data_id) references pattern_data(id),
    constraint fk_order_product_metrics
        foreign key (product_metrics_id) references product_metrics(id)
);

create table review(
    id serial primary key,
    content text,
    rating smallint,
    created_at timestamp,
    coat_model_id int,
    client_id int,
    reply_id int,
    constraint fk_review_coat_model
        foreign key (coat_model_id) references coat_model(id),
    constraint fk_review_client
        foreign key (client_id) references client(id)
);

create table review_reply(
    review_id int primary key,
    content text,
    created_at timestamp,
    constraint fk_review_coat_model
        foreign key (review_id) references review(id)
);

alter table review
add constraint fk_review_review_reply
    foreign key (reply_id) references review_reply(review_id);

insert into "user"(username, password, active)
values
    ('petro.kovalenko@example.com', '$2a$10$dGlirX4TKwGREM8iBzeWCONoU8tqy0QhYY1l/jzuq./pPKUzqw2wi', true),
    ('maria.shevchenko@example.com', '$2a$10$dGlirX4TKwGREM8iBzeWCONoU8tqy0QhYY1l/jzuq./pPKUzqw2wi', true),
    ('andrii.melnyk@example.com', '$2a$10$dGlirX4TKwGREM8iBzeWCONoU8tqy0QhYY1l/jzuq./pPKUzqw2wi', true),
    ('oleksandr.ivanchenko@example.com', '$2a$10$dGlirX4TKwGREM8iBzeWCONoU8tqy0QhYY1l/jzuq./pPKUzqw2wi', true),
    ('yuliya.zhuravlova@example.com', '$2a$10$dGlirX4TKwGREM8iBzeWCONoU8tqy0QhYY1l/jzuq./pPKUzqw2wi', true),
    ('viktor.pawlenko@example.com', '$2a$10$dGlirX4TKwGREM8iBzeWCONoU8tqy0QhYY1l/jzuq./pPKUzqw2wi', true);

insert into user_role(user_id, role)
values
    (1, 'CLIENT'),
    (2, 'CLIENT'),
    (3, 'CLIENT'),
    (4, 'EMPLOYEE'),
    (5, 'EMPLOYEE'),
    (6, 'EMPLOYEE'),
    (6, 'ADMIN');

insert into client(first_name, last_name, email, user_id)
values
    ('Petro', 'Kovalenko', 'petro.kovalenko@example.com', 1),
    ('Maria', 'Shevchenko', 'maria.shevchenko@example.com', 2),
    ('Andrii', 'Melnyk', 'andrii.melnyk@example.com', 3);

insert into employee(first_name, last_name, email, phone_number, registered_at, user_id)
values
    ('Oleksandr', 'Ivanchenko', 'oleksandr.ivanchenko@example.com', '1234567890', '2023-11-23 19:16:14.572710', 4),
    ('Yuliya', 'Zhuravlova', 'yuliya.zhuravlova@example.com', '1234567890', '2023-11-23 19:16:14.572710', 5),
    ('Viktor', 'Pawlenko', 'viktor.pawlenko@example.com', '1234567890', '2023-11-23 19:16:14.572710', 6);

insert into coat_model(name, price, img_path, coat_type, video_url)
values
    ('Денім-стрейч 1', 999.99, 'src/main/resources/images/coat_models/jacket_coat/jacket_coat_1.png', 'JACKET_COAT', 'https://youtube.com/shorts/Oqjsgf0-OWA'),
    ('Шкіра хамелеона 3', 999.99, 'src/main/resources/images/coat_models/jacket_coat/jacket_coat_2.png', 'JACKET_COAT', 'https://youtube.com/shorts/ibm1MRLUWd0'),
    ('Льон 1', 999.99, 'src/main/resources/images/coat_models/jacket_coat/jacket_coat_3.png', 'JACKET_COAT', 'https://youtube.com/shorts/x5TbwJB6eYE'),
    ('Ялинка 1', 999.99, 'src/main/resources/images/coat_models/jacket_coat/jacket_coat_4.png', 'JACKET_COAT', 'https://youtube.com/shorts/gibgoFhrrGM'),
    ('Вельвет 1', 999.99, 'src/main/resources/images/coat_models/maxi_coat/maxi_coat_1.png', 'MAXI_COAT', 'https://youtube.com/shorts/2z7PTTESbj0'),
    ('Вельвет 3', 999.99, 'src/main/resources/images/coat_models/maxi_coat/maxi_coat_2.png', 'MAXI_COAT', 'https://youtube.com/shorts/aJT9VHKZuYc'),
    ('Вельвет 2', 999.99, 'src/main/resources/images/coat_models/maxi_coat/maxi_coat_3.png', 'MAXI_COAT', 'https://youtube.com/shorts/mmHCWOlPF0I'),
    ('Вовна сіра', 999.99, 'src/main/resources/images/coat_models/maxi_coat/maxi_coat_4.png', 'MAXI_COAT', 'https://youtube.com/shorts/6Ez2-XG1_XA'),
    ('Шкіра 5', 999.99, 'src/main/resources/images/coat_models/midi_coat/midi_coat_1.png', 'MIDI_COAT', 'https://youtube.com/shorts/zspo23P4hYo'),
    ('Шовк-сатин 1', 999.99, 'src/main/resources/images/coat_models/midi_coat/midi_coat_2.png', 'MIDI_COAT', 'https://youtube.com/shorts/JTOu0x6Yk9k'),
    ('Вовна 1', 999.99, 'src/main/resources/images/coat_models/midi_coat/midi_coat_3.png', 'MIDI_COAT', 'https://youtube.com/shorts/Vr-39Hednug'),
    ('Вовна 2', 999.99, 'src/main/resources/images/coat_models/midi_coat/midi_coat_4.png', 'MIDI_COAT', 'https://youtube.com/shorts/w9kkOvztBdU');

