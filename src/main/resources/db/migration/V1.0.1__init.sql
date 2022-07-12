use emsdb;

DROP TABLE IF EXISTS test;
DROP TABLE IF EXISTS employee_project_role_history;
DROP TABLE IF EXISTS project_role;
DROP TABLE IF EXISTS project;
DROP TABLE IF EXISTS hibernate_sequence;
DROP TABLE IF EXISTS employee_type_history;
DROP TABLE IF EXISTS employee_type;
DROP TABLE IF EXISTS employee_relation;
DROP TABLE IF EXISTS employee_relation_type;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS talent_outcome_period;
DROP TABLE IF EXISTS feedback;


CREATE TABLE employee (
  employee_id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  surname varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  start_date datetime NOT NULL,
  end_date datetime DEFAULT NULL,
  PRIMARY KEY (employee_id)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

  CREATE TABLE employee_project_role_history (
  idemployee_project_role_history_id bigint(20) NOT NULL,
  project_id bigint(20) NOT NULL,
  employee_id bigint(20) NOT NULL,
  project_role_id bigint(20) NOT NULL,
  start_date datetime NOT NULL,
  end_date datetime DEFAULT NULL,
  PRIMARY KEY (idemployee_project_role_history_id),
  CONSTRAINT FK_employee_id FOREIGN KEY (employee_id)
  REFERENCES employee(employee_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE employee_relation (
  employee_relation_id bigint(20) NOT NULL AUTO_INCREMENT,
  employee_relation_type_id bigint(20) NOT NULL ,
  leading_employee_id bigint(20) NOT NULL,
  employee_id bigint(20) NOT NULL,
  start_date datetime NOT NULL,
  end_date datetime DEFAULT NULL,
  PRIMARY KEY (employee_relation_id),
  CONSTRAINT FK_employee_relation_employee_id
  FOREIGN KEY (employee_id)
  REFERENCES employee(employee_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



CREATE TABLE employee_relation_type (
  employee_relation_type_id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (employee_relation_type_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


CREATE TABLE employee_type (
  employee_type_id bigint(20) NOT NULL AUTO_INCREMENT,
  type varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (employee_type_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE employee_type_history (
  idemployee_type_history_id bigint(20) NOT NULL AUTO_INCREMENT,
  employee_id bigint(20) NOT NULL,
  start_date datetime NOT NULL,
  end_date datetime DEFAULT NULL,
  employee_type_id bigint(20) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (idemployee_type_history_id),
  CONSTRAINT FK_employee_type_history_employee FOREIGN KEY (employee_id)
  REFERENCES employee(employee_id),
  CONSTRAINT FK_employee_type_id FOREIGN KEY (employee_type_id)
  REFERENCES employee_type(employee_type_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE hibernate_sequence (
  next_val bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE project (
  project_id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  start_date datetime NOT NULL,
  end_date datetime DEFAULT NULL,
  PRIMARY KEY (project_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE employee_project_role_history
ADD CONSTRAINT FK_employee_project_id
FOREIGN KEY (project_id) REFERENCES project(project_id);

CREATE TABLE project_role (
  project_role_id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (project_role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE employee_project_role_history
ADD CONSTRAINT FK_employee_project_role_id
FOREIGN KEY (project_role_id) REFERENCES project_role(project_role_id);

CREATE TABLE test (
  test_id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  surname varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (test_id)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE talent_outcome_period (
  talent_outcome_period_id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  start_date datetime NOT NULL,
  end_date datetime DEFAULT NULL,
  PRIMARY KEY (talent_outcome_period_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE feedback (
  feedback_id bigint(20) NOT NULL AUTO_INCREMENT,
  talent_outcome_period_id bigint(20) NOT NULL,
  employee_relation_id bigint(20) NOT NULL,
  description varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  start_date datetime NOT NULL,
  end_date datetime DEFAULT NULL,
  PRIMARY KEY (feedback_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE `emsdb`.`feedback`
ADD CONSTRAINT `FK_employee_relation_id`
  FOREIGN KEY (`employee_relation_id`)
  REFERENCES `emsdb`.`employee_relation` (`employee_relation_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `FK_talent_outcome_period_id`
  FOREIGN KEY (`talent_outcome_period_id`)
  REFERENCES `emsdb`.`talent_outcome_period` (`talent_outcome_period_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;