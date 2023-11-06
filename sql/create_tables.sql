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
    creation_time_days int,
    coat_type text,
    img_path text,
    video_url text
);

create table pattern_data (
    id serial primary key,
    client_id int,
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
    increase_to_file_width float,
    constraint fk_pattern_data_user
        foreign key (client_id) references client(id)
);

create table "order"(
    id serial primary key,
    client_id int,
    coat_model_id int,
    pattern_data_id int,
    created_at timestamp,
    constraint fk_order_client
        foreign key (client_id) references client(id),
    constraint fk_order_coat_model
        foreign key (coat_model_id) references coat_model(id),
    constraint fk_order_pattern_data
        foreign key (pattern_data_id) references pattern_data(id)
);

insert into "user"(username, password, active)
values
    ('petro.kovalenko@exmaple.com', '$2a$10$dGlirX4TKwGREM8iBzeWCONoU8tqy0QhYY1l/jzuq./pPKUzqw2wi', true),
    ('maria.shevchenko@exmaple.com', '$2a$10$dGlirX4TKwGREM8iBzeWCONoU8tqy0QhYY1l/jzuq./pPKUzqw2wi', true),
    ('andrii.melnyk@exmaple.com', '$2a$10$dGlirX4TKwGREM8iBzeWCONoU8tqy0QhYY1l/jzuq./pPKUzqw2wi', true);

insert into user_role(user_id, role)
values
    (1, 'CLIENT'),
    (2, 'CLIENT'),
    (3, 'CLIENT');

insert into client(first_name, last_name, email, user_id)
values
    ('Petro', 'Kovalenko', 'petro.kovalenko@example.com', 1),
    ('Maria', 'Shevchenko', 'maria.shevchenko@example.com', 2),
    ('Andrii', 'Melnyk', 'andrii.melnyk@example.com', 3);

insert into coat_model(name, price, creation_time_days, img_path, coat_type, video_url)
values
    ('Model 1', 999.99, 14, 'src/main/resources/images/coat_models/jacket_coat/jacket_coat_1.png', 'JACKET_COAT', null),
    ('Model 2', 999.99, 14, 'src/main/resources/images/coat_models/jacket_coat/jacket_coat_2.png', 'JACKET_COAT', null),
    ('Model 3', 999.99, 14, 'src/main/resources/images/coat_models/jacket_coat/jacket_coat_3.png', 'JACKET_COAT', null),
    ('Model 4', 999.99, 14, 'src/main/resources/images/coat_models/jacket_coat/jacket_coat_4.png', 'JACKET_COAT', null),
    ('Model 5', 999.99, 14, 'src/main/resources/images/coat_models/maxi_coat/maxi_coat_1.png', 'MAXI_COAT', null),
    ('Model 6', 999.99, 14, 'src/main/resources/images/coat_models/maxi_coat/maxi_coat_2.png', 'MAXI_COAT', null),
    ('Model 7', 999.99, 14, 'src/main/resources/images/coat_models/maxi_coat/maxi_coat_3.png', 'MAXI_COAT', null),
    ('Model 8', 999.99, 14, 'src/main/resources/images/coat_models/maxi_coat/maxi_coat_4.png', 'MAXI_COAT', null),
    ('Model 9', 999.99, 14, 'src/main/resources/images/coat_models/midi_coat/midi_coat_1.png', 'MIDI_COAT', null),
    ('Model 10', 999.99, 14, 'src/main/resources/images/coat_models/midi_coat/midi_coat_2.png', 'MIDI_COAT', null),
    ('Model 11', 999.99, 14, 'src/main/resources/images/coat_models/midi_coat/midi_coat_3.png', 'MIDI_COAT', null),
    ('Model 12', 999.99, 14, 'src/main/resources/images/coat_models/midi_coat/midi_coat_4.png', 'MIDI_COAT', null);

