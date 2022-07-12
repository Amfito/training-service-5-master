DROP TABLE IF EXISTS training;
DROP TABLE IF EXISTS  training_type;
DROP TABLE IF EXISTS  training_details;

CREATE TABLE training (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  training_type_id bigint(20) NOT NULL,
  name varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  start_date datetime NOT NULL,
  end_date datetime DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE training_type (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  type varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE training
ADD CONSTRAINT FK_training_type_id
FOREIGN KEY (training_type_id) REFERENCES training_type(id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
CREATE TABLE training_details (
  id BIGINT NOT NULL AUTO_INCREMENT,
  training_id BIGINT NOT NULL,
  employee_id BIGINT NOT NULL,
  mentor_relation_id BIGINT NULL,
  final_presentation_date DATETIME NULL,
  start_date DATETIME NOT NULL,
  end_date DATETIME NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_training_id FOREIGN KEY (training_id)
  REFERENCES training(id),
  CONSTRAINT FK_employee_id_training FOREIGN KEY (employee_id)
  REFERENCES employee(employee_id),
  CONSTRAINT FK_employee_relation_id_training FOREIGN KEY (mentor_relation_id)
  REFERENCES employee_relation(employee_relation_id)
  ) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8 COLLATE = utf8_unicode_ci;

insert into training_type (id, type) values (1, 'type1');
insert into training_type (id, type) values (2, 'type2');
insert into training_type (id, type) values (3, 'type3');
insert into training_type (id, type) values (4, 'type4');
insert into training_type (id, type) values (5, 'type5');
insert into training_type (id, type) values (6, 'type6');
insert into training_type (id, type) values (7, 'type7');
insert into training_type (id, type) values (8, 'type8');
insert into training_type (id, type) values (9, 'type9');
insert into training_type (id, type) values (10, 'type10');

insert into training (id, training_type_id, name, start_date, end_date) values (1, 2, 'Security Training', '2007-03-21', '2020-05-05');
insert into training (id, training_type_id, name, start_date, end_date) values (2, 2, 'Work Safety Training', '2007-03-21', '2020-05-05');
insert into training (id, training_type_id, name, start_date, end_date) values (3, 2, 'Time Management Training', '2009-04-22', '2021-01-15');
insert into training (id, training_type_id, name, start_date, end_date) values (4, 1, 'Java for beginners', '2010-05-23', '2021-02-16');
insert into training (id, training_type_id, name, start_date, end_date) values (5, 3, 'Java, Spring Boot', '2011-06-24', '2021-03-17');
insert into training (id, training_type_id, name, start_date, end_date) values (6, 2, 'Security Training', '2006-03-21', '2020-05-05');
insert into training (id, training_type_id, name, start_date, end_date) values (7, 2, 'Python', '2009-03-21', '2020-05-05');
insert into training (id, training_type_id, name, start_date, end_date) values (8, 3, 'Java Advanced Training', '2011-04-22', '2021-01-15');
insert into training (id, training_type_id, name, start_date, end_date) values (9, 1, 'HTML basics', '2010-05-23', '2021-02-16');
insert into training (id, training_type_id, name, start_date, end_date) values (10, 1, 'CSS basics', '2010-06-24', '2021-03-17');
INSERT INTO training_details (training_id, employee_id, mentor_relation_id, final_presentation_date, start_date, end_date) VALUES (1, 1, 6, '2007-02-02 00:00:00', '2007-01-01 00:00:00', '2007-02-10 00:00:00');
INSERT INTO training_details (training_id, employee_id, mentor_relation_id, final_presentation_date, start_date, end_date) VALUES (2, 2, 7, '2007-02-02 00:00:00', '2007-01-01 00:00:00', '2007-03-01 00:00:00');
INSERT INTO training_details (training_id, employee_id, mentor_relation_id, final_presentation_date, start_date, end_date) VALUES (3, 3, 13, '2012-04-22 00:00:00', '2009-04-22 00:00:00', '2012-04-22 00:00:00');
INSERT INTO training_details (training_id, employee_id, mentor_relation_id, final_presentation_date, start_date, end_date) VALUES (4, 4, 19, '2018-12-12 00:00:00', '2013-01-01 00:00:00', '2018-12-12 00:00:00');
