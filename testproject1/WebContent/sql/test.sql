show databases;

drop table sy_dept;
create table SY_DEPT(
	dept_code	smallint	auto_increment primary key,
	dept_name	varchar(100)	not null
);
insert into sy_dept(dept_name) values('영아부');
insert into sy_dept(dept_name) values('유치부');
insert into sy_dept(dept_name) values('유년부');
insert into sy_dept(dept_name) values('초등부');
insert into sy_dept(dept_name) values('중등부');
insert into sy_dept(dept_name) values('고등부');
insert into sy_dept(dept_name) values('2부찬양대');
insert into sy_dept(dept_name) values('3부찬양대');
select * from sy_dept;


drop table sy_call;
create table SY_CALL(
	call_code	smallint	not null auto_increment,
	call_name	varchar(100) not null,
	primary key(call_code)
);
insert into sy_call(call_name) values('청년');
insert into sy_call(call_name) values('전도사');
insert into sy_call(call_name) values('강도사');
insert into sy_call(call_name) values('목사');
insert into sy_call(call_name) values('회장');
insert into sy_call(call_name) values('부회장');
insert into sy_call(call_name) values('서기');
insert into sy_call(call_name) values('부서기');
insert into sy_call(call_name) values('회계');
insert into sy_call(call_name) values('부회계');
insert into sy_call(call_name) values('총무');
select * from sy_call;


drop table sy_grade;
create table SY_GRADE(
	grd_code	smallint	not null auto_increment,
	grd_name	varchar(100) not null,
	primary key(grd_code)
);
insert into sy_grade(grd_name) values('준회원');
insert into sy_grade(grd_name) values('정회원');
insert into sy_grade(grd_name) values('임원');
insert into sy_grade(grd_name) values('소장');
insert into sy_grade(grd_name) values('관리자');
select * from sy_grade;


drop table sy_cust;
create table SY_CUST(
	id 			varchar(20) not null,
	password	varchar(20) not null,
	name		varchar(20) not null,
	birthday	date		not null,
	zipcode		char(6)		not null,
	job			varchar(20),
	addr1		varchar(255) not null,
	addr2		varchar(255) not null,
	major		varchar(50),
	home_phone	varchar(20),
	cell_phone	varchar(20),
	is_christ	boolean		not null,
	photo		varchar(255),
	pray		text,
	dept_code	smallint	not null,
	call_code	smallint	not null,
	grd_code	smallint	not null,
	primary key(id),
	foreign key(dept_code) references sy_dept(dept_code),
	foreign key(call_code) references sy_call(call_code),
	foreign key(grd_code) references sy_grade(grd_code)
)

insert into SY_CUST(
			id,
			password,
			name,
			birthday,
			zipcode,
			job,
			addr1,
			addr2,
			major,
			home_phone,
			cell_phone,
			is_christ,
			pray,
			dept_code,
			call_code,
			grd_code
		) values(
			'libedi',
			'libedi',
			'박상준',
			str_to_date('1983-02-07', get_format(date,'ISO')),
			'440709',
			'일',
			'주소1',
			'주소2',
			'직업',
			'집전화',
			'폰',
			true,
			'기도제목',
			1,
			2,
			3
		)
delete from sy_cust;

select str_to_date('2101-02-02',get_format(date,'ISO')) from dual