use emsdb;

INSERT INTO employee (employee_id, name, surname, start_date, end_date) VALUES (1, 'Name1', 'Surename1', '2022-03-21', '2022-03-21');

insert into employee (employee_id, name, surname, start_date, end_date) values (2, 'Name2', 'Surename2', '2022-03-21', '2022-03-21');
insert into employee (employee_id, name, surname, start_date, end_date) values (3, 'Name3', 'Surename3', '2022-03-21', '2022-03-21');
insert into employee (employee_id, name, surname, start_date, end_date) values (4, 'Name4', 'Surename4', '2022-03-21', '2022-03-21');
insert into employee (employee_id, name, surname, start_date, end_date) values (5, 'Name5', 'Surename5', '2022-03-21', '2022-03-21');
insert into employee (employee_id, name, surname, start_date, end_date) values (6, 'Name6', 'Surename6', '2022-03-21', '2022-03-21');
insert into employee (employee_id, name, surname, start_date, end_date) values (7, 'Name7', 'Surename7', '2022-03-21', '2022-03-21');
insert into employee (employee_id, name, surname, start_date, end_date) values (8, 'Name8', 'Surename8', '2022-03-21', '2022-03-21');
insert into employee (employee_id, name, surname, start_date, end_date) values (9, 'Name9', 'Surename9', '2022-03-21', '2022-03-21');
insert into employee (employee_id, name, surname, start_date, end_date) values (10, 'Name10', 'Surename10', '2022-03-21', '2022-03-21');

insert into project (project_id, name, start_date, end_date) values (1, 'Facebook', '2010-03-21', '2030-03-21');
insert into project (project_id, name, start_date, end_date) values (2, 'Google', '2010-03-21', '2030-03-21');
insert into project (project_id, name, start_date, end_date) values (3, 'Netflix', '2010-03-21', '2030-03-21');
insert into project (project_id, name, start_date, end_date) values (4, 'Tesla', '2010-03-21', '2030-03-21');

insert into employee_relation_type (employee_relation_type_id, name) values (1, 'Team Lead');
insert into employee_relation_type (employee_relation_type_id, name) values (2, 'Mentor');
insert into employee_relation_type (employee_relation_type_id, name) values (3, 'Career Conuncelor');

insert into employee_type (employee_type_id, type) values (1, 'Java Developer');
insert into employee_type (employee_type_id, type) values (2, 'Tester');
insert into employee_type (employee_type_id, type) values (3, 'Project Manager');

insert into project_role (project_role_id, name) values (1, 'Team Member');
insert into project_role (project_role_id, name) values (2, 'Project Manager');
insert into project_role (project_role_id, name) values (3, 'Team Leader');
insert into project_role (project_role_id, name) values (4, 'Project Admin');

insert into employee_project_role_history (idemployee_project_role_history_id, project_id, employee_id, project_role_id, start_date, end_date) values (1, 1, 1, 1, '2005-10-11', '2010-10-11');
insert into employee_project_role_history (idemployee_project_role_history_id, project_id, employee_id, project_role_id, start_date, end_date) values (2, 2, 2, 2, '2005-10-11', '2010-10-11');
insert into employee_project_role_history (idemployee_project_role_history_id, project_id, employee_id, project_role_id, start_date, end_date) values (3, 3, 3, 3, '2005-10-11', '2010-10-11');
insert into employee_project_role_history (idemployee_project_role_history_id, project_id, employee_id, project_role_id, start_date, end_date) values (4, 4, 4, 4, '2005-10-11', '2010-10-11');
insert into employee_project_role_history (idemployee_project_role_history_id, project_id, employee_id, project_role_id, start_date, end_date) values (5, 1, 5, 1, '2005-10-11', '2010-10-11');
insert into employee_project_role_history (idemployee_project_role_history_id, project_id, employee_id, project_role_id, start_date, end_date) values (6, 2, 6, 2, '2005-10-11', '2010-10-11');
insert into employee_project_role_history (idemployee_project_role_history_id, project_id, employee_id, project_role_id, start_date, end_date) values (7, 3, 7, 3, '2005-10-11', '2010-10-11');
insert into employee_project_role_history (idemployee_project_role_history_id, project_id, employee_id, project_role_id, start_date, end_date) values (8, 4, 8, 4, '2005-10-11', '2010-10-11');
insert into employee_project_role_history (idemployee_project_role_history_id, project_id, employee_id, project_role_id, start_date, end_date) values (9, 1, 9, 1, '2005-10-11', '2010-10-11');
insert into employee_project_role_history (idemployee_project_role_history_id, project_id, employee_id, project_role_id, start_date, end_date) values (10, 2, 10, 2, '2005-10-11', '2015-10-11');
insert into employee_project_role_history (idemployee_project_role_history_id, project_id, employee_id, project_role_id, start_date, end_date) values (11, 3, 1, 3, '2010-10-11', '2015-10-11');
insert into employee_project_role_history (idemployee_project_role_history_id, project_id, employee_id, project_role_id, start_date, end_date) values (12, 4, 2, 4, '2010-10-11', '2015-10-11');
insert into employee_project_role_history (idemployee_project_role_history_id, project_id, employee_id, project_role_id, start_date, end_date) values (13, 1, 3, 1, '2010-10-11', '2015-10-11');
insert into employee_project_role_history (idemployee_project_role_history_id, project_id, employee_id, project_role_id, start_date, end_date) values (14, 2, 4, 2, '2010-10-11', '2015-10-11');
insert into employee_project_role_history (idemployee_project_role_history_id, project_id, employee_id, project_role_id, start_date, end_date) values (15, 3, 5, 3, '2010-10-11', '2015-10-11');

insert into employee_type_history (idemployee_type_history_id, employee_id, start_date, end_date, employee_type_id) values (1, 1, '2005-09-12', '2007-07-06', 1);
insert into employee_type_history (idemployee_type_history_id, employee_id, start_date, end_date, employee_type_id) values (2, 2, '2005-09-12', '2007-07-06', 2);
insert into employee_type_history (idemployee_type_history_id, employee_id, start_date, end_date, employee_type_id) values (3, 3, '2005-09-12', '2007-07-06', 3);
insert into employee_type_history (idemployee_type_history_id, employee_id, start_date, end_date, employee_type_id) values (4, 4, '2005-09-12', '2007-07-06', 1);
insert into employee_type_history (idemployee_type_history_id, employee_id, start_date, end_date, employee_type_id) values (5, 5, '2005-09-12', '2007-07-06', 2);
insert into employee_type_history (idemployee_type_history_id, employee_id, start_date, end_date, employee_type_id) values (6, 6, '2005-09-12', '2007-07-06', 3);
insert into employee_type_history (idemployee_type_history_id, employee_id, start_date, end_date, employee_type_id) values (7, 7, '2005-09-12', '2007-07-06', 1);
insert into employee_type_history (idemployee_type_history_id, employee_id, start_date, end_date, employee_type_id) values (8, 8, '2005-09-12', '2007-07-06', 2);
insert into employee_type_history (idemployee_type_history_id, employee_id, start_date, end_date, employee_type_id) values (9, 9, '2005-09-12', '2007-07-06', 3);
insert into employee_type_history (idemployee_type_history_id, employee_id, start_date, end_date, employee_type_id) values (10, 10, '2005-09-12', '2007-07-06', 1);
insert into employee_type_history (idemployee_type_history_id, employee_id, start_date, end_date, employee_type_id) values (11, 1, '2008-09-12', '2009-05-06', 2);
insert into employee_type_history (idemployee_type_history_id, employee_id, start_date, end_date, employee_type_id) values (12, 2, '2008-09-12', '2009-05-06', 3);
insert into employee_type_history (idemployee_type_history_id, employee_id, start_date, end_date, employee_type_id) values (13, 3, '2008-09-12', '2009-05-06', 1);
insert into employee_type_history (idemployee_type_history_id, employee_id, start_date, end_date, employee_type_id) values (14, 4, '2008-09-12', '2009-05-06', 2);
insert into employee_type_history (idemployee_type_history_id, employee_id, start_date, end_date, employee_type_id) values (15, 5, '2008-09-12', '2009-05-06', 3);

insert into employee_relation (employee_relation_id, employee_relation_type_id, leading_employee_id, employee_id, start_date, end_date) values (1, 3, 1, 10, '2005-03-21', '2007-03-21');
insert into employee_relation (employee_relation_id, employee_relation_type_id, leading_employee_id, employee_id, start_date, end_date) values (2, 2, 2, 9, '2005-03-21', '2007-03-21');
insert into employee_relation (employee_relation_id, employee_relation_type_id, leading_employee_id, employee_id, start_date, end_date) values (3, 1, 3, 8, '2005-03-21', '2007-03-21');
insert into employee_relation (employee_relation_id, employee_relation_type_id, leading_employee_id, employee_id, start_date, end_date) values (4, 3, 4, 7, '2005-03-21', '2007-03-21');
insert into employee_relation (employee_relation_id, employee_relation_type_id, leading_employee_id, employee_id, start_date, end_date) values (5, 2, 5, 6, '2005-03-21', '2007-03-21');
insert into employee_relation (employee_relation_id, employee_relation_type_id, leading_employee_id, employee_id, start_date, end_date) values (6, 1, 6, 5, '2005-03-21', '2007-03-21');
insert into employee_relation (employee_relation_id, employee_relation_type_id, leading_employee_id, employee_id, start_date, end_date) values (7, 3, 7, 4, '2005-03-21', '2007-03-21');
insert into employee_relation (employee_relation_id, employee_relation_type_id, leading_employee_id, employee_id, start_date, end_date) values (8, 2, 8, 3, '2005-03-21', '2007-03-21');
insert into employee_relation (employee_relation_id, employee_relation_type_id, leading_employee_id, employee_id, start_date, end_date) values (9, 1, 9, 2, '2005-03-21', '2007-03-21');
insert into employee_relation (employee_relation_id, employee_relation_type_id, leading_employee_id, employee_id, start_date, end_date) values (10, 3, 10, 1, '2005-03-21', '2007-03-21');
insert into employee_relation (employee_relation_id, employee_relation_type_id, leading_employee_id, employee_id, start_date, end_date) values (11, 2, 1, 6, '2007-03-21', '2008-03-21');
insert into employee_relation (employee_relation_id, employee_relation_type_id, leading_employee_id, employee_id, start_date, end_date) values (12, 1, 2, 7, '2007-03-21', '2008-03-21');
insert into employee_relation (employee_relation_id, employee_relation_type_id, leading_employee_id, employee_id, start_date, end_date) values (13, 3, 1, 8, '2007-03-21', '2008-03-21');
insert into employee_relation (employee_relation_id, employee_relation_type_id, leading_employee_id, employee_id, start_date, end_date) values (14, 2, 2, 9, '2007-03-21', '2008-03-21');
insert into employee_relation (employee_relation_id, employee_relation_type_id, leading_employee_id, employee_id, start_date, end_date) values (15, 1, 3, 10, '2007-03-21', '2008-03-21');
insert into employee_relation (employee_relation_id, employee_relation_type_id, leading_employee_id, employee_id, start_date, end_date) values (16, 3, 4, 1, '2007-03-21', '2008-03-21');
insert into employee_relation (employee_relation_id, employee_relation_type_id, leading_employee_id, employee_id, start_date, end_date) values (17, 2, 3, 2, '2007-03-21', '2008-03-21');
insert into employee_relation (employee_relation_id, employee_relation_type_id, leading_employee_id, employee_id, start_date, end_date) values (18, 1, 4, 3, '2007-03-21', '2008-03-21');
insert into employee_relation (employee_relation_id, employee_relation_type_id, leading_employee_id, employee_id, start_date, end_date) values (19, 3, 3, 6, '2007-03-21', '2008-03-21');
insert into employee_relation (employee_relation_id, employee_relation_type_id, leading_employee_id, employee_id, start_date, end_date) values (20, 2, 4, 7, '2007-03-21', '2008-03-21');


