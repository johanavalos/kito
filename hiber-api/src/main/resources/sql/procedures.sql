DELIMITER //

CREATE PROCEDURE InsertEmployeesAndTasks()
BEGIN
    DECLARE i INT DEFAULT 1;
    DECLARE employeeId INT;

    -- Insert 10 employees into the employee table
    WHILE i <= 10 DO
        INSERT INTO employee (name, gender, department_id, dob)
        VALUES (
            CASE i
                WHEN 1 THEN 'Ana Sánchez'
                WHEN 2 THEN 'Carlos García'
                WHEN 3 THEN 'Beatriz Fernández'
                WHEN 4 THEN 'Alejandro Martínez'
                WHEN 5 THEN 'Carla Gómez'
                WHEN 6 THEN 'Fernando López'
                WHEN 7 THEN 'Daniela Pérez'
                WHEN 8 THEN 'Javier Rodríguez'
                WHEN 9 THEN 'Sofía Torres'
                WHEN 10 THEN 'Manuel Hernández'
            END,
            CASE i MOD 2
                WHEN 0 THEN 'male'
                ELSE 'female'
            END,
            CASE i MOD 5
                WHEN 0 THEN 1
                WHEN 1 THEN 2
                WHEN 2 THEN 3
                WHEN 3 THEN 4
                ELSE 5
            END,
            DATE_ADD('1980-01-01', INTERVAL (FLOOR(RAND() * 365 * 30)) DAY)
        );

        -- Get the last inserted employee id
        SET employeeId = LAST_INSERT_ID();

        -- Insert 3 tasks for each employee into the task table
        INSERT INTO task (title, employeeId) VALUES
            (CASE i
                WHEN 1 THEN 'Develop and Optimize Backend APIs'
                WHEN 2 THEN 'Create UI Mockups'
                WHEN 3 THEN 'Plan Marketing Campaign'
                WHEN 4 THEN 'Prepare Sales Pitch'
                WHEN 5 THEN 'Define Product Roadmap'
                WHEN 6 THEN 'Implement Automated Testing Frameworks'
                WHEN 7 THEN 'Conduct User Research'
                WHEN 8 THEN 'Write Social Media Content'
                WHEN 9 THEN 'Analyze Sales Data'
                WHEN 10 THEN 'Organize Sprint Meetings'
            END, employeeId),
            (CASE i
                WHEN 1 THEN 'Refactor Legacy Code for Performance Improvements'
                WHEN 2 THEN 'Design Landing Page'
                WHEN 3 THEN 'Manage Advertising Budget'
                WHEN 4 THEN 'Follow Up with Leads'
                WHEN 5 THEN 'Review Product Feedback'
                WHEN 6 THEN 'Set Up Database Indexes'
                WHEN 7 THEN 'Create Personas'
                WHEN 8 THEN 'Design Email Campaign'
                WHEN 9 THEN 'Forecast Revenue'
                WHEN 10 THEN 'Prepare Sprint Reports'
            END, employeeId),
            (CASE i
                WHEN 1 THEN 'Document API Endpoints'
                WHEN 2 THEN 'Create Prototype'
                WHEN 3 THEN 'Conduct Market Research'
                WHEN 4 THEN 'Negotiate Contracts'
                WHEN 5 THEN 'Brainstorm Features'
                WHEN 6 THEN 'Review Database Schema'
                WHEN 7 THEN 'Design Surveys'
                WHEN 8 THEN 'Plan Content Calendar'
                WHEN 9 THEN 'Draft Sales Proposals'
                WHEN 10 THEN 'Coordinate with Stakeholders'
            END, employeeId);

        SET i = i + 1;
    END WHILE;
END //

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE deleteUser(IN p_user_id bigint)
BEGIN
    -- Delete associated records from user_roles
    DELETE FROM user_role WHERE user_id = p_user_id;
    
    -- Delete the user from users table
    DELETE FROM user WHERE id = p_user_id;
END$$

DELIMITER ;
