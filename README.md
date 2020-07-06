## Description
This is an example of Spring Boot Integration JDBC usage by using java configration.

## Preparation

1. Create database:
```
mysql> CREATE DATABASE IF NOT EXISTS `db_example` CHARACTER SET utf8 COLLATE utf8_general_ci;
```

2. Create database user:
```
mysql> CREATE USER 'springuser'@'localhost' IDENTIFIED BY 'ThePassword';
```

3. Grant permission to database user:
```
mysql> GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, INDEX, ALTER,REFERENCES,CREATE VIEW ON `db_example`.* TO 'springuser'@'localhost';
```

4. Create table:
```
mysql> use db_example;
mysql> CREATE TABLE foo (
     id MEDIUMINT NOT NULL AUTO_INCREMENT,
     name CHAR(30) NOT NULL COMMENT '名称',
     status TINYINT DEFAULT 0,
     PRIMARY KEY (id)
);
```

4. Load initial data:
```
mysql> INSERT INTO foo (name) VALUES
    ('dog'),('cat'),('penguin'),
    ('lax'),('whale'),('ostrich');
```
## Run the project

1. Load the project in IDE or vscode

2. Run the project in IDE

<p>The project will query foo table in every 3 seconds, and mark them as read.</>
