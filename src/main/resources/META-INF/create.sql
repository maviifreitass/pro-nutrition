-- -----------------------------------------------------
-- Table aliment
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS aliment (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255),
  calories DOUBLE PRECISION,
  fat_total DOUBLE PRECISION,
  fat_saturated DOUBLE PRECISION,
  protein DOUBLE PRECISION,
  sodium DOUBLE PRECISION,
  potassium DOUBLE PRECISION,
  cholesterol DOUBLE PRECISION,
  carbohydrates DOUBLE PRECISION,
  fiber DOUBLE PRECISION,
  sugar DOUBLE PRECISION
);

-- -----------------------------------------------------
-- Table role
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS role (
  id SERIAL PRIMARY KEY,
  enabled BOOLEAN,
  role_name VARCHAR(255)
);

-- -----------------------------------------------------
-- Table user
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS user (
  id SERIAL PRIMARY KEY,
  role_id INT,
  username VARCHAR(255),
  email VARCHAR(255),
  password VARCHAR(255),
  create_time TIMESTAMP,
  delete_time TIMESTAMP,
  update_time TIMESTAMP,
  FOREIGN KEY (role_id) REFERENCES role (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table nutritionist
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS nutritionist (
  id SERIAL PRIMARY KEY,
  user_id INT,
  specialization VARCHAR(45),
  create_time TIMESTAMP,
  update_time TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table diet_plan
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS diet_plan (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255),
  description VARCHAR(255),
  nutritionist_id INT,
  create_time TIMESTAMP,
  update_time TIMESTAMP,
  FOREIGN KEY (nutritionist_id) REFERENCES nutritionist (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table permissions
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS permissions (
  id SERIAL PRIMARY KEY,
  permission_name VARCHAR(255)
);

-- -----------------------------------------------------
-- Table role_permissions
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS role_permissions (
  role_id INT,
  permission_id INT,
  FOREIGN KEY (role_id) REFERENCES role (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (permission_id) REFERENCES permissions (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table customer_data
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS customer_data (
  id SERIAL PRIMARY KEY,
  user_id INT,
  height DOUBLE PRECISION,
  weight DOUBLE PRECISION,
  age INT,
  gender VARCHAR(45),
  goal VARCHAR(255),
  diet_plan_id INT,
  create_time TIMESTAMP,
  update_time TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (diet_plan_id) REFERENCES diet_plan (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table meals
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS meals (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255),
  description VARCHAR(255),
  create_time TIMESTAMP,
  delete_time TIMESTAMP
);

-- -----------------------------------------------------
-- Table meal_items
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS meal_items (
  id SERIAL PRIMARY KEY,
  meal_id INT,
  aliment_id INT,
  quantity DOUBLE PRECISION,
  create_time TIMESTAMP,
  update_time TIMESTAMP,
  FOREIGN KEY (meal_id) REFERENCES meals (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (aliment_id) REFERENCES aliment (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table meals_plan
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS meals_plan (
  diet_plan_id INT,
  meals_id INT,
  FOREIGN KEY (diet_plan_id) REFERENCES diet_plan (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (meals_id) REFERENCES meals (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);
