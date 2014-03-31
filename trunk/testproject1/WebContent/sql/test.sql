show databases;

drop table sy_dept;
create table SY_DEPT(
	dept_code	smallint	auto_increment primary key,
	dept_name	varchar(100)	not null
);
insert into sy_dept(dept_name) values('���ƺ�');
insert into sy_dept(dept_name) values('��ġ��');
insert into sy_dept(dept_name) values('�����');
insert into sy_dept(dept_name) values('�ʵ��');
insert into sy_dept(dept_name) values('�ߵ��');
insert into sy_dept(dept_name) values('����');
insert into sy_dept(dept_name) values('2������');
insert into sy_dept(dept_name) values('3������');
select * from sy_dept;


drop table sy_call;
create table SY_CALL(
	call_code	smallint	not null auto_increment,
	call_name	varchar(100) not null,
	primary key(call_code)
);
insert into sy_call(call_name) values('û��');
insert into sy_call(call_name) values('���');
insert into sy_call(call_name) values('������');
insert into sy_call(call_name) values('���');
insert into sy_call(call_name) values('ȸ��');
insert into sy_call(call_name) values('��ȸ��');
insert into sy_call(call_name) values('����');
insert into sy_call(call_name) values('�μ���');
insert into sy_call(call_name) values('ȸ��');
insert into sy_call(call_name) values('��ȸ��');
insert into sy_call(call_name) values('�ѹ�');
select * from sy_call;


drop table sy_grade;
create table SY_GRADE(
	grd_code	smallint	not null auto_increment,
	grd_name	varchar(100) not null,
	primary key(grd_code)
);
insert into sy_grade(grd_name) values('��ȸ��');
insert into sy_grade(grd_name) values('��ȸ��');
insert into sy_grade(grd_name) values('�ӿ�');
insert into sy_grade(grd_name) values('����');
insert into sy_grade(grd_name) values('����');
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
			'�ڻ���',
			str_to_date('1983-02-07', get_format(date,'ISO')),
			'440709',
			'��',
			'�ּ�1',
			'�ּ�2',
			'����',
			'����ȭ',
			'��',
			true,
			'�⵵����',
			1,
			2,
			3
		)
delete from sy_cust;

select str_to_date('2101-02-02',get_format(date,'ISO')) from dual;


-- FILE UPLOAD

drop table SY_FILE;

create table SY_FILE(
	file_id		int				not null,
	file_path	varchar(255)	not null,
	upload_date	date,
	primary key(file_id)
);

-- Sequence 생성
drop table FILE_SEQ_GEN;
create table FILE_SEQ_GEN(
	seq_val		int		not null
);
insert into FILE_SEQ_GEN values(0);
update FILE_SEQ_GEN set seq_val = 1;
select seq_val from FILE_SEQ_GEN;

select * from SY_FILE;

select file_id as fileId, file_path as filePath from SY_FILE

-- 게시판 테이블
drop table SY_BOARD;
create table SY_BOARD(
	list_id		int				not null	auto_increment,
	title		varchar(255)	not null,
	text_area	text			not null,
	writer		varchar(100)	not null,
	crt_date	date			not null,
	mdf_date	date			not null,
	click_view	int				unsigned	default 0,
	primary key(list_id)
);

select * from sy_board;











