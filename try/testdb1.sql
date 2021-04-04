create database testdb1 character set utf8;

create table team(team_id int primary key,
name varchar(10) not null);

create table user(id int auto_increment,
team_id int not null,
name varchar(10) not null,
primary key(id));

insert into team values
	(101, 'チームA'),
	(102, 'チームB'),
	(103, 'チームC'),
	(104, 'チームD');

insert into user(team_id, name) values
	(101, '山田'),
	(101, '鈴木'),
	(102, '中村'),
	(102, '田中'),
	(103, '加藤');


select name from team where team_id = 103;

select user.name, team.name from user join team on user.team_id = team.team_id;

select team.name from user join team on user.team_id = team.team_id where user.name = '鈴木';

select count(*) from user join team on user.team_id = team.team_id where team.name = 'チームA';

