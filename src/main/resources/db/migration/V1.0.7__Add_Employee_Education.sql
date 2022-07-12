DROP TABLE IF EXISTS employee_education;

CREATE TABLE employee_education (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  employee_id bigint(20) NOT NULL,
  course varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  institution varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  start_date datetime NOT NULL,
  end_date datetime DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_employee_education_employee
  FOREIGN KEY (employee_id)
  REFERENCES employee(employee_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

insert into employee_education (id, employee_id, course, institution, start_date, end_date) values (1, 1, 'Course1', 'Institution1', '2020-03-21', '2020-03-21');
insert into employee_education (id, employee_id, course, institution, start_date, end_date) values (2, 2, 'Course2', 'Institution2', '2020-03-21', '2020-03-21');
insert into employee_education (id, employee_id, course, institution, start_date, end_date) values (3, 3, 'Course3', 'Institution3', '2020-03-21', '2020-03-21');
insert into employee_education (id, employee_id, course, institution, start_date, end_date) values (4, 4, 'Course4', 'Institution4', '2020-03-21', '2020-03-21');
insert into employee_education (id, employee_id, course, institution, start_date, end_date) values (5, 5, 'Course5', 'Institution5', '2020-03-21', '2020-03-21');
insert into employee_education (id, employee_id, course, institution, start_date, end_date) values (6, 6, 'Course6', 'Institution6', '2020-03-21', '2020-03-21');
insert into employee_education (id, employee_id, course, institution, start_date, end_date) values (7, 7, 'Course7', 'Institution7', '2020-03-21', '2020-03-21');
insert into employee_education (id, employee_id, course, institution, start_date, end_date) values (8, 8, 'Course8', 'Institution8', '2020-03-21', '2020-03-21');
insert into employee_education (id, employee_id, course, institution, start_date, end_date) values (9, 9, 'Course9', 'Institution9', '2020-03-21', '2020-03-21');
insert into employee_education (id, employee_id, course, institution, start_date, end_date) values (10, 10, 'Course10', 'Institution10', '2020-03-21', '2020-03-21');

