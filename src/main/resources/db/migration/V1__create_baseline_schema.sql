create table concert (
    id bigint not null auto_increment,
    title varchar(255) not null,
    venue varchar(255) not null,
    description text,
    created_at datetime(6) not null default current_timestamp(6),
    primary key (id)
);

create table concert_schedule (
    id bigint not null auto_increment,
    concert_id bigint not null,
    show_at datetime(6) not null,
    total_seats int not null,
    primary key (id),
    constraint fk_concert_schedule_concert
        foreign key (concert_id) references concert (id)
);

create index idx_concert_schedule_concert_id_show_at
    on concert_schedule (concert_id, show_at);

create table seat (
    id bigint not null auto_increment,
    schedule_id bigint not null,
    seat_number varchar(50) not null,
    price int not null,
    status varchar(30) not null,
    primary key (id),
    constraint fk_seat_schedule
        foreign key (schedule_id) references concert_schedule (id),
    unique (schedule_id, seat_number),
    unique (schedule_id, id)
);

create index idx_seat_schedule_id_status
    on seat (schedule_id, status);

create table reservation (
    id bigint not null auto_increment,
    schedule_id bigint not null,
    seat_id bigint not null unique,
    reserver_name varchar(100) not null,
    reservation_status varchar(30) not null,
    reserved_at datetime(6) not null default current_timestamp(6),
    primary key (id),
    constraint fk_reservation_schedule_seat
        foreign key (schedule_id, seat_id) references seat (schedule_id, id)
);

create index idx_reservation_schedule_id_status
    on reservation (schedule_id, reservation_status);
