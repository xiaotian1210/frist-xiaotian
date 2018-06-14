create table meta_class(
	id_ varchar(50),
	name_ varchar(100),
	table_ varchar(50),
	ormclass_ varchar(200),
	pkg_ varchar(200),
	icon_ varchar(50),
	type_ int,
	init_ char(1),
	domain_ varchar(50),
	version_ varchar(15),
	primary key(id_)
);

create table meta_field(
	id_ varchar(50),
	meta_ varchar(50),
	name_ varchar(100),
	column_ varchar(50),
	oldcolumn_ varchar(50),
	type_ int,
	define_ varchar(50),
	length_ int,
	precision_ int,
	scale_ int,
	empty_ char(1),
	persist_ char(1),
	unique_ char(1),
	dynamic_ char(1),
	defval_ varchar(100),
	sort_ int,
	serialcode_ varchar(50),
	serialmode_ int,
	primary key(id_, meta_)
);

create table meta_index(
	meta_ varchar(50),
	column_ varchar(50),
	type_ int,
	primary key(meta_, column_)
);

create table meta_class_relation(
	metafrom_ varchar(50),
	metato_ varchar(50),
	relation_ int,
	fieldfrom_ varchar(50),
	fieldto_ varchar(50)
);

create table meta_dynamic(
	meta_ varchar(50),
	column_ varchar(50),
	key_ varchar(100),
	value_ varchar(100),
	primary key(meta_, column_, key_)
);

create table meta_code(
	id_ varchar(50),
	name_ varchar(50),
	desc_  varchar(200),
	size_ int,
	start_ int,
	step_ int,
	radix_ int,
	primary key(id_)
);

create table meta_code_detail(
	id_ int,
	code_ varchar(50),
	type_ varchar(20),
	size_ int,
	value_ varchar(100),
	format_ varchar(50),
	primary key(id_, code_)
);

create table meta_code_serial(
	code_ varchar(50),
	current_ varchar(200),
	primary key(code_)
);
insert into meta_code (id_, name_, desc_, size_, start_, step_, radix_) values ('GLOBAL', 'GOLBAL', 'GOLBAL', 20, 1, 1, 36);
insert into meta_code_detail (id_, code_, type_, size_, value_, format_) values (1, 'GLOBAL', 'const', 6, '000000', null);
insert into meta_code_detail (id_, code_, type_, size_, value_, format_) values (2, 'GLOBAL', 'year', 4, null, null);
insert into meta_code_detail (id_, code_, type_, size_, value_, format_) values (3, 'GLOBAL', 'month', 2, null, null);
insert into meta_code_detail (id_, code_, type_, size_, value_, format_) values (4, 'GLOBAL', 'day', 2, null, null);
insert into meta_code_detail (id_, code_, type_, size_, value_, format_) values (5, 'GLOBAL', 'serial', 6, null, null);
