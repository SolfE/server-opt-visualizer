insert into concert (title, venue, description)
select 'Spring Live 2026', 'Seoul Dome', 'A sample concert used for the concert list flow.'
where not exists (
    select 1
    from concert
    where title = 'Spring Live 2026'
      and venue = 'Seoul Dome'
);

insert into concert (title, venue, description)
select 'Backend Nights', 'Busan Arena', 'A second concert used to populate the minimum list response.'
where not exists (
    select 1
    from concert
    where title = 'Backend Nights'
      and venue = 'Busan Arena'
);
