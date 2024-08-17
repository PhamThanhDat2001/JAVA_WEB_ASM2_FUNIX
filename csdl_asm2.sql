
DROP DATABASE IF EXISTS spring_workcv;
-- CREATE DATABASE spring_workcv;
CREATE DATABASE spring_workcv CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
	
USE spring_workcv;


CREATE TABLE `role` (
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    role_name VARCHAR(255)
);

CREATE TABLE category (
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255),
    number_choose INT(11)
);

CREATE TABLE cv (
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    file_name VARCHAR(255),
    user_id INT(11)
);

CREATE TABLE `user` (
    id INT(11) NOT NULL AUTO_INCREMENT,
    address VARCHAR(255),
    `description` VARCHAR(255),
    email VARCHAR(255),
    full_name VARCHAR(255),
    image VARCHAR(500),
    `password` VARCHAR(128),
    phone_number VARCHAR(255),
    `status` INT(11),
    role_id INT(11),
    cv_id INT(11),
    PRIMARY KEY (id),
    FOREIGN KEY (role_id) REFERENCES `role`(id),
    FOREIGN KEY (cv_id) REFERENCES cv(id)
);

-- Update cv table to reference user table after user table is created
ALTER TABLE cv ADD CONSTRAINT FK_cv_user_id FOREIGN KEY (user_id) REFERENCES user(id);

CREATE TABLE company (
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    address VARCHAR(255),
    `description` VARCHAR(255),
    email VARCHAR(255),
    logo VARCHAR(255),
    name_company VARCHAR(255),
    phone_number VARCHAR(255),
   `status` INT(11),
    user_id INT(11),
    FOREIGN KEY (user_id) REFERENCES `user`(id)
);

CREATE TABLE follow_company (
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    company_id INT(11),
    user_id INT(11),
    FOREIGN KEY (company_id) REFERENCES company(id),
    FOREIGN KEY (user_id) REFERENCES `user`(id)
);

CREATE TABLE recruitment (
    id INT(11)  PRIMARY KEY NOT NULL AUTO_INCREMENT,
    address VARCHAR(255),
    created_at VARCHAR(255),
    `description` VARCHAR(255),
    experience VARCHAR(255),
    quantity INT(11),
    `rank` VARCHAR(255),
    salary VARCHAR(255),
    `status` INT(11),
    title VARCHAR(255),
    `type` VARCHAR(255),
    `view` INT(11),
    category_id INT(11),
    company_id INT(11),
    deadline VARCHAR(255),
    FOREIGN KEY (category_id) REFERENCES category(id),
    FOREIGN KEY (company_id) REFERENCES company(id)
);

CREATE TABLE save_job (
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    recruitment_id INT(11),
    user_id INT(11),
    FOREIGN KEY (recruitment_id) REFERENCES recruitment(id),
    FOREIGN KEY (user_id) REFERENCES `user`(id)
);

CREATE TABLE applypost (
    id INT(11) NOT NULL AUTO_INCREMENT,
    created_at VARCHAR(255),
    recruitment_id INT(11),
    user_id INT(11),
    name_cv VARCHAR(255),
    `status` INT(11),
    `text` VARCHAR(255),
    PRIMARY KEY (id),
    FOREIGN KEY (recruitment_id) REFERENCES recruitment(id),
    FOREIGN KEY (user_id) REFERENCES `user`(id)
);


INSERT INTO `role` (role_name) VALUES
('EMPLOYEE'),
('COMPANY'), 
('ADMIN');

INSERT INTO `category` (`name`, number_choose) VALUES
('Công nghệ thông tin', 120),
('Kinh doanh', 80),
('Y tế', 50),
('Kỹ thuật', 70),
('Nghệ thuật', 60);

INSERT INTO `cv` (file_name, user_id) VALUES
('Hoàng Anh - CV.pdf', NULL),
('Nguyễn Văn A - CV.docx', NULL),
('Trần Thị B - CV.pdf', NULL),
('Lê Văn C - CV.docx', NULL),
('Phạm Đức D - CV.pdf', NULL);

INSERT INTO `user` (address, `description`, email, full_name, image, `password`, phone_number, `status`, role_id, cv_id) VALUES
('123 Đường ABC, Quận 1, TP.HCM', 'Quản trị viên hệ thống', 'admin@example.com', 'Nguyễn Văn Admin', 'https://solution.com.vn/upload_images/images/Logo%20Service24h.jpg', '123456', '0123456789', 1, 3, 2),
('456 Đường XYZ, Quận 2, TP.HCM', 'Công ty FPT SOFT', 'company@example.com', 'Fpt Soft', 'https://nhainonline.vn/wp-content/uploads/2023/05/y-nghia-logo-cong-ty-xay-dung.jpg', '123456', '0987654321', 1, 2, null),
('789 Đường DEF, Quận 3, TP.HCM', 'Khách hàng VIP', 'khachhang@example.com', 'Trần Thị Khách Hàng', 'https://marketplace.canva.com/EAF2NKpTznA/2/0/1600w/canva-v%C3%A0ng-m%C3%A0u-chuy%E1%BB%83n-ti%E1%BA%BFp-chuy%C3%AAn-nghi%E1%BB%87p-c%C3%B4ng-ty-ph%C3%A1t-tri%E1%BB%83n-%E1%BB%A9ng-d%E1%BB%A5ng-logo-Z0qVYgs8lcU.jpg', 'khachhang@123', '0369852147', 1, 1, 3),
('101 Đường GHI, Quận 4, TP.HCM', 'Bác sĩ chuyên khoa', 'bs1@example.com', 'Nguyễn Thanh Bác Sĩ', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSbs7msnEj0rQWqPkLiPpMuOgHmaYGie9C40w&s', 'bs1@123', '0932147856', 1, 1, 4),
('111 Đường KLM, Quận 5, TP.HCM', 'Kỹ sư thiết kế', 'kysu@example.com', 'Phạm Văn Kỹ Sư', 'https://spencil.vn/wp-content/uploads/2024/06/mau-thiet-ke-logo-cong-ty-xay-dung-SPencil-Agency-1.jpeg', 'kysu@123', '0654321987', 1, 1, 5);

UPDATE `spring_workcv`.`cv` SET `user_id` = '1' WHERE (`id` = '1');
UPDATE `spring_workcv`.`cv` SET `user_id` = '2' WHERE (`id` = '2');
UPDATE `spring_workcv`.`cv` SET `user_id` = '3' WHERE (`id` = '3');
UPDATE `spring_workcv`.`cv` SET `user_id` = '4' WHERE (`id` = '4');
UPDATE `spring_workcv`.`cv` SET `user_id` = '5' WHERE (`id` = '5');

-- select * from cv;
INSERT INTO `company` (address, `description`, email, logo, name_company, phone_number, `status`, user_id) VALUES
('456 Đường XYZ, Quận 2, TP.HCM', 'Công ty công nghệ thông tin hàng đầu', 'cty@example.com', 'https://solution.com.vn/upload_images/images/Logo%20Service24h.jpg', 'Công ty ABC', '0123456789', 1, 2),
('789 Đường DEF, Quận 3, TP.HCM', 'Công ty kinh doanh uy tín', 'cty2@example.com', 'https://nhainonline.vn/wp-content/uploads/2023/05/y-nghia-logo-cong-ty-xay-dung.jpg', 'Công ty XYZ', '0987654321', 1, 3),
('101 Đường GHI, Quận 4, TP.HCM', 'Công ty y tế lớn', 'cty3@example.com', 'https://marketplace.canva.com/EAF2NKpTznA/2/0/1600w/canva-v%C3%A0ng-m%C3%A0u-chuy%E1%BB%83n-ti%E1%BA%BFp-chuy%C3%AAn-nghi%E1%BB%87p-c%C3%B4ng-ty-ph%C3%A1t-tri%E1%BB%83n-%E1%BB%A9ng-d%E1%BB%A5ng-logo-Z0qVYgs8lcU.jpg', 'Công ty VinaTech', '0369852147', 1, 4),
('111 Đường KLM, Quận 5, TP.HCM', 'Công ty xây dựng hàng đầu', 'cty4@example.com', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSbs7msnEj0rQWqPkLiPpMuOgHmaYGie9C40w&s', 'Công ty Xây Dựng', '0932147856', 1, 5),
('123 Đường ABC, Quận 1, TP.HCM', 'Công ty nghệ thuật sáng tạo', 'cty5@example.com', 'https://spencil.vn/wp-content/uploads/2024/06/mau-thiet-ke-logo-cong-ty-xay-dung-SPencil-Agency-1.jpeg', 'Công ty Nghệ Thuật', '0654321987', 1, 1);

INSERT INTO `follow_company` (company_id, user_id) VALUES
(1, 3),
(2, 4),
(3, 5),
(4, 1),
(5, 2);

INSERT INTO `recruitment` (address, created_at, `description`, experience, quantity, `rank`, salary, `status`, title, `type`, `view`, category_id, company_id, deadline) VALUES
('456 Đường XYZ, Quận 2, TP.HCM', '2024-07-05', 'Tuyển dụng lập trình viên PHP', '1-2 năm kinh nghiệm', 5, 'Nhân viên', '10-15 triệu', 1, 'Lập trình viên PHP', 'Toàn thời gian', 100, 1, 2, '2024-07-20'),
('456 Đường XYZ, Quận 2, TP.HCM', '2024-07-05', 'Tuyển dụng lập trình viên Java', '3-4 năm kinh nghiệm', 5, 'Nhân viên', '20-25 triệu', 1, 'Lập trình viên Java ', 'Toàn thời gian', 100, 1, 2, '2024-07-20'),

('456 Đường XYZ, Quận 2, TP.HCM', '2024-07-05', 'Tuyển dụng lập trình viên PHP', '1-2 năm kinh nghiệm', 5, 'Nhân viên', '10-15 triệu', 1, 'Lập trình viên PHP 2', 'Toàn thời gian', 100, 1, 2, '2024-07-20'),
('456 Đường XYZ, Quận 2, TP.HCM', '2024-07-05', 'Tuyển dụng lập trình viên Java', '3-4 năm kinh nghiệm', 5, 'Nhân viên', '20-25 triệu', 1, 'Lập trình viên Java  2', 'Toàn thời gian', 100, 1, 2, '2024-07-20'),
('456 Đường XYZ, Quận 2, TP.HCM', '2024-07-05', 'Tuyển dụng lập trình viên PHP', '1-2 năm kinh nghiệm', 5, 'Nhân viên', '10-15 triệu', 1, 'Lập trình viên PHP 3', 'Toàn thời gian', 100, 1, 2, '2024-07-20'),
('456 Đường XYZ, Quận 2, TP.HCM', '2024-07-05', 'Tuyển dụng lập trình viên Java', '3-4 năm kinh nghiệm', 5, 'Nhân viên', '20-25 triệu', 1, 'Lập trình viên Java  3', 'Toàn thời gian', 100, 1, 2, '2024-07-20'),
('456 Đường XYZ, Quận 2, TP.HCM', '2024-07-05', 'Tuyển dụng lập trình viên PHP', '1-2 năm kinh nghiệm', 5, 'Nhân viên', '10-15 triệu', 1, 'Lập trình viên PHP 4', 'Toàn thời gian', 100, 1, 2, '2024-07-20'),
('456 Đường XYZ, Quận 2, TP.HCM', '2024-07-05', 'Tuyển dụng lập trình viên Java', '3-4 năm kinh nghiệm', 5, 'Nhân viên', '20-25 triệu', 1, 'Lập trình viên Java  4', 'Toàn thời gian', 100, 1, 2, '2024-07-20'),



('789 Đường DEF, Quận 3, TP.HCM', '2024-07-05', 'Tuyển dụng nhân viên kinh doanh', 'Không yêu cầu kinh nghiệm', 10, 'Nhân viên', '8-12 triệu', 1, 'Nhân viên kinh doanh 1', 'bán thời gian', 80, 2, 3, '2024-07-15'),
('789 Đường DEF, Quận 3, TP.HCM', '2024-07-05', 'Tuyển dụng nhân viên kinh doanh', 'Không yêu cầu kinh nghiệm', 10, 'Nhân viên', '8-12 triệu', 1, 'Nhân viên kinh doanh 2', 'Toàn thời gian', 80, 2, 3, '2024-07-15'),
('789 Đường DEF, Quận 3, TP.HCM', '2024-07-05', 'Tuyển dụng nhân viên kinh doanh', 'Không yêu cầu kinh nghiệm', 10, 'Nhân viên', '8-12 triệu', 1, 'Nhân viên kinh doanh 3', 'Toàn thời gian', 80, 2, 3, '2024-07-15'),
('101 Đường GHI, Quận 4, TP.HCM', '2024-07-05', 'Tuyển dụng bác sĩ chuyên khoa', '5 năm kinh nghiệm', 3, 'Chuyên gia', '30-50 triệu', 1, 'Bác sĩ chuyên khoa', 'Toàn thời gian', 50, 4, 4, '2024-07-25'),
('111 Đường KLM, Quận 5, TP.HCM', '2024-07-05', 'Tuyển dụng kỹ sư xây dựng', '2-3 năm kinh nghiệm', 8, 'Chuyên gia', '20-30 triệu', 1, 'Kỹ sư xây dựng', 'Toàn thời gian', 70, 5, 5, '2024-07-18'),
('123 Đường ABC, Quận 1, TP.HCM', '2024-07-05', 'Tuyển dụng nghệ sĩ đa phương tiện', 'Không yêu cầu kinh nghiệm', 6, 'Nhân viên', '12-18 triệu', 1, 'Nghệ sĩ đa phương tiện', 'Toàn thời gian', 60, 3, 1, '2024-07-22');

INSERT INTO `save_job` (recruitment_id, user_id) VALUES
(1, 3),
(2, 4),
(3, 5),
(4, 1),
(5, 2);

INSERT INTO `applypost` (created_at, recruitment_id, user_id, name_cv, `status`, `text`) VALUES
('2024-07-05', 1, 3, 'CV của Trần Thị Khách Hàng', 1, 'Xin gửi lời chào sếp! Tôi muốn ứng tuyển vị trí lập trình viên Java tại công ty ABC.'),
('2024-07-05', 2, 4, 'CV của Nguyễn Thanh Bác Sĩ', 1, 'Xin chào nhà tuyển dụng, tôi mong muốn được làm việc tại công ty XYZ với vị trí nhân viên kinh doanh.'),
('2024-07-05', 3, 5, 'CV của Phạm Văn Kỹ Sư', 2, 'Tôi mong muốn đóng góp công sức và kỹ năng của mình vào công ty YTE với vị trí bác sĩ chuyên khoa.'),
('2024-07-05', 4, 4, 'CV của Lê Văn C', 3, 'Xin gửi lời chào sếp! Tôi muốn ứng tuyển vị trí kỹ sư xây dựng tại công ty Xây Dựng.'),
('2024-07-05', 4, 3, 'CV của Lê C', 3, 'Xin gửi lời chào sếp! Tôi muốn ứng tuyển vị trí kỹ sư xây dựng tại công ty Xây Dựng.'),
('2024-07-05', 2, 3, 'CV của Nguyễn Văn E', 1, 'Xin gửi lời chào sếp! Tôi mong muốn được làm việc tại công ty Nghệ Thuật với vị trí nghệ sĩ đa phương tiện.');
