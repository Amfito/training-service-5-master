
ALTER TABLE feedback
RENAME COLUMN start_date TO date,
DROP COLUMN end_date;

DROP TABLE IF EXISTS to_periods;

insert into talent_outcome_period (talent_outcome_period_id, name, start_date, end_date) values (1, 'January - July 2008', '2008-01-01', '2008-06-30');
insert into talent_outcome_period (talent_outcome_period_id, name, start_date, end_date) values (2, 'July - December 2008', '2008-07-01', '2008-12-31');
insert into talent_outcome_period (talent_outcome_period_id, name, start_date, end_date) values (3, 'January - July 2009', '2009-01-01', '2009-06-30');
insert into talent_outcome_period (talent_outcome_period_id, name, start_date, end_date) values (4, 'July - December 2009', '2009-07-01', '2009-12-31');

insert into feedback (feedback_id, talent_outcome_period_id, employee_relation_id, description, date) values (1, 1, 1, 'very good work', '2008-03-21');
insert into feedback (feedback_id, talent_outcome_period_id, employee_relation_id, description, date) values (2, 1, 2, 'good', '2009-03-21');
insert into feedback (feedback_id, talent_outcome_period_id, employee_relation_id, description, date) values (3, 1, 3, 'could be better', '2008-03-21');
insert into feedback (feedback_id, talent_outcome_period_id, employee_relation_id, description, date) values (4, 1, 4, 'excellent', '2008-03-21');
insert into feedback (feedback_id, talent_outcome_period_id, employee_relation_id, description, date) values (5, 1, 5, 'interesnting', '2009-03-21');
insert into feedback (feedback_id, talent_outcome_period_id, employee_relation_id, description, date) values (6, 1, 6, 'nice job', '2008-03-21');
insert into feedback (feedback_id, talent_outcome_period_id, employee_relation_id, description, date) values (7, 2, 7, 'very good work', '2008-03-21');
insert into feedback (feedback_id, talent_outcome_period_id, employee_relation_id, description, date) values (8, 2, 8, 'could be better', '2009-03-21');
insert into feedback (feedback_id, talent_outcome_period_id, employee_relation_id, description, date) values (9, 2, 1, 'very good work', '2008-03-21');
insert into feedback (feedback_id, talent_outcome_period_id, employee_relation_id, description, date) values (10, 2, 2, 'way to go', '2008-03-21');
insert into feedback (feedback_id, talent_outcome_period_id, employee_relation_id, description, date) values (11, 2, 3, 'nice job', '2008-03-21');
insert into feedback (feedback_id, talent_outcome_period_id, employee_relation_id, description, date) values (12, 3, 4, 'very good work', '2008-02-21');
insert into feedback (feedback_id, talent_outcome_period_id, employee_relation_id, description, date) values (13, 3, 7, 'could be better', '2008-03-21');
insert into feedback (feedback_id, talent_outcome_period_id, employee_relation_id, description, date) values (14, 3, 2, 'way to go', '2008-03-21');
insert into feedback (feedback_id, talent_outcome_period_id, employee_relation_id, description, date) values (15, 3, 1, 'could be better', '2008-01-21');
insert into feedback (feedback_id, talent_outcome_period_id, employee_relation_id, description, date) values (16, 4, 2, 'nice job', '2008-03-21');
insert into feedback (feedback_id, talent_outcome_period_id, employee_relation_id, description, date) values (17, 4, 5, 'excellent', '2008-03-21');
insert into feedback (feedback_id, talent_outcome_period_id, employee_relation_id, description, date) values (18, 4, 8, 'very good work', '2008-02-21');
insert into feedback (feedback_id, talent_outcome_period_id, employee_relation_id, description, date) values (19, 4, 3, 'way to go', '2008-03-21');
insert into feedback (feedback_id, talent_outcome_period_id, employee_relation_id, description, date) values (20, 4, 4, 'excellent', '2008-03-21');