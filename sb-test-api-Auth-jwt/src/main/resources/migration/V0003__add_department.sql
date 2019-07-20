CREATE TABLE departement (
  id   INTEGER     NOT NULL SERIAL,
  no   CHAR(4)     NOT NULL,
  name VARCHAR(40) NOT NULL,
  date_creation VARCHAR(40) NOT NULL
);

INSERT INTO departement (no, name,date_creation) VALUES
  ('d001', 'Marketing','12-01-2019'),
  ('d002', 'Finance','12-01-2019'),
  ('d003', 'Human Resources','12-01-2019'),
  ('d004', 'Production','12-01-2019'),
  ('d005', 'Development','12-01-2019'),
  ('d006', 'Quality Management','12-01-2019'),
  ('d007', 'Sales','12-01-2019'),
  ('d008', 'Research','12-01-2019'),
  ('d009', 'Customer Service','12-01-2019'),
  ('dddd', 'Unset','12-01-2019');

ALTER TABLE users
  ADD COLUMN department_id INTEGER;
UPDATE users
SET department_id = (SELECT id
                     FROM department
                     WHERE no = 'dddd');
ALTER TABLE users MODIFY department_id INTEGER NOT NULL;
