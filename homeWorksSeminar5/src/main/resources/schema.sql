CREATE TABLE IF NOT EXISTS task_table
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    title varchar(255) NOT NULL,
    description varchar(255) NOT NULL,
    lead_time timestamp not null,
    status varchar(50) not null
);