use emsdb;

DROP TABLE IF EXISTS employee_cv;

CREATE TABLE employee_cv (
  employee_cv_id bigint(20) NOT NULL AUTO_INCREMENT,
  employee_id bigint(20) NOT NULL,
  cv mediumblob,
  cv_date datetime NOT NULL,
  PRIMARY KEY (employee_cv_id),
  CONSTRAINT FK_employee_employee_cv FOREIGN KEY (employee_id)
  REFERENCES employee(employee_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;