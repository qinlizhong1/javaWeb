# 班级表
drop table if exists class;
create table class(
	class_id int primary key,
    head_teacher varchar(255) not null default '' comment '班主任',
    stu_num int not null default 0 comment '班级学生人数'
) engine = innodb charset=utf8;
insert into class(class_id, head_teacher, stu_num) values(1, '张一', 21);
insert into class(class_id, head_teacher, stu_num) values(2, '王二', 22);
insert into class(class_id, head_teacher, stu_num) values(3, '李三', 23);
insert into class(class_id, head_teacher, stu_num) values(4, '刘四', 24);
commit;
select * from class;
