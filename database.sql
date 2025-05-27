CREATE DATABASE cnpm;
USE cnpm;

-- Tạo bảng tblUser
CREATE TABLE tblUser (
    id INTEGER PRIMARY KEY,
    username VARCHAR(50),
    password VARCHAR(50),
    fullname VARCHAR(50),
    role VARCHAR(50)
);

-- Tạo bảng tblDriver
CREATE TABLE tblDriver (
    id INTEGER PRIMARY KEY,
    name VARCHAR(50),
    dob DATE,
    national VARCHAR(50),
    bio VARCHAR(255)
);

-- Tạo bảng tblRaceTrack
CREATE TABLE tblRaceTrack (
    id INTEGER PRIMARY KEY,
    name VARCHAR(50),
    location VARCHAR(255),
    numOfLaps INTEGER,
    time DATE,
    des VARCHAR(255)
);

-- Tạo bảng tblRaceTeam
CREATE TABLE tblRaceTeam (
    id INTEGER PRIMARY KEY,
    name VARCHAR(50),
    brand VARCHAR(50),
    des VARCHAR(255)
);

-- Tạo bảng tblMembership
CREATE TABLE tblMembership (
    id INTEGER PRIMARY KEY,
    startTime DATE,
    endTime DATE,
    idRaceTeam INTEGER,
    idDriver INTEGER,
    FOREIGN KEY (idRaceTeam) REFERENCES tblRaceTeam(id),
    FOREIGN KEY (idDriver) REFERENCES tblDriver(id)
);

-- Tạo bảng tblRaceResult
CREATE TABLE tblRaceResult (
    id INTEGER PRIMARY KEY,
    completionTime TIMESTAMP,
    completedLaps INTEGER,
    idRaceTrack INTEGER,
    idMembership INTEGER,
    FOREIGN KEY (idRaceTrack) REFERENCES tblRaceTrack(id),
    FOREIGN KEY (idMembership) REFERENCES tblMembership(id)
);
-- Dữ liệu tblUser
INSERT INTO tblUser (id, username, password, fullname, role)
VALUES (1, 'a', 'a', 'A', 'Data Entry Client');

-- Dữ liệu tblRaceTrack
INSERT INTO tblRaceTrack (id, name, location, numOfLaps, time, des)
VALUES
(1, 'Danang Race', 'Quận Hải Châu, TP Đà Nẵng', 57, '2025-04-25 14:00:00', NULL),
(2, 'Hanoi Grand Fix', 'Quận Long Biên, TP Hà Nội', 57, '2025-05-10 14:00:00', NULL),
(3, 'Ho Chi Minh City Grand Prix', 'Quận 1, TP Hồ Chí Minh', 57, '2025-06-15 08:00:00', NULL);

-- Dữ liệu tblRaceTeam
INSERT INTO tblRaceTeam (id, name, brand, des)
VALUES
(1, 'Red Velocity', 'Ferrari', NULL),
(2, 'Storm Racers', 'Mercedes', NULL),
(3, 'Thunderbolt Racing', 'Honda', NULL);

-- Dữ liệu tblDriver
INSERT INTO tblDriver (id, name, dob, national, bio)
VALUES
(1, 'Ngô Tuấn Anh', '2004-10-22', 'Việt Nam', NULL),
(2, 'Vũ Trọng Khôi', '2004-10-21', 'Việt Nam', NULL),
(3, 'Lê Hải Đăng', '1999-05-19', 'Việt Nam', NULL),
(4, 'Phạm Ngọc Long', '1991-06-24', 'Việt Nam', NULL),
(5, 'Mai Xuân Nhân', '1995-02-05', 'Việt Nam', NULL),
(6, 'Nguyễn Thế Lâm', '1997-10-10', 'Việt Nam', NULL);

-- Dữ liệu tblMembership
INSERT INTO tblMembership (id, idRaceTeam, idDriver, startTime, endTime)
VALUES
(1, 1, 4, '2025-01-01', '2025-04-30'),
(2, 1, 1, '2025-01-01', '2026-01-01'),
(3, 1, 2, '2025-01-01', '2026-01-01'),
(4, 2, 3, '2025-05-01', '2026-01-01'),
(5, 2, 4, '2025-05-01', '2026-01-01'),
(6, 3, 5, '2025-01-01', '2026-01-01'),
(7, 3, 6, '2025-01-01', '2025-04-30'),
(8, 2, 6, '2025-05-01', '2026-01-01');

-- Dữ liệu tblRaceResult
INSERT INTO tblRaceResult (id, idRaceTrack, idMembership, completionTime, completedLaps)
VALUES
(1, 1, 6, '01:34:56.942', 57),
(2, 1, 7, '01:35:56.942', 57),
(3, 1, 2, '01:35:57.942', 57),
(4, 1, 3, '01:35:55.942', 57),
(5, 2, 2, NULL, NULL),
(6, 2, 3, NULL, NULL),
(7, 2, 4, NULL, NULL),
(8, 2, 8, NULL, NULL);