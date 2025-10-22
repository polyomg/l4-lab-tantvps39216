/* Thêm 2 tài khoản: 1 user thường và 1 admin */
INSERT INTO Accounts (username, password, fullname, admin) VALUES ('user1', '123', 'Nguyễn Văn User', 0);
INSERT INTO Accounts (username, password, fullname, admin) VALUES ('admin1', '123', 'Trần Thị Admin', 1);