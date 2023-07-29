# 用户表,在该表的用户才有权限查看学生系统,实际系统密码一般秘文存储,这里为了方便,明文存储
drop table if exists user;
create table user(
	id int primary key,
    name varchar(255) not null default '' comment '姓名',
    password int not null default 0 comment '密码'
) engine = innodb charset=utf8;
insert into user(id, name, password) values(1, 'admin', 123456);
insert into user(id, name, password) values(2, 'qin', 123456);
commit;
select * from user;
