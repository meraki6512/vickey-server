DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Episode;
DROP TABLE IF EXISTS Videos;
DROP TABLE IF EXISTS Check_watched;
DROP TABLE IF EXISTS Likes;



-- 1. 사용자 테이블 (Users)
CREATE TABLE Users (
    user_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    profile_picture_url LONGTEXT,
    signup_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. 에피소드 테이블 (Episode)
CREATE TABLE Episode (
    episode_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    episode_count INTEGER NOT NULL,
    description TEXT,
    released_date DATE,
    thumbnail_url LONGTEXT,
    cast_list VARCHAR(255) -- cast가 예약어라 변경
);

-- 3. 비디오 테이블 (Videos)
CREATE TABLE Videos (
    video_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    episode_id INTEGER,
    video_url VARCHAR(255) NOT NULL,
    duration INTEGER NOT NULL,
    episode_num INTEGER NOT NULL,
    FOREIGN KEY (episode_id) REFERENCES Episode(episode_id) ON DELETE CASCADE
);

-- 4. 시청기록 테이블 (Check_watched)
CREATE TABLE Check_watched (
    user_id INTEGER,
    video_id INTEGER,
    watched_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    progress INTEGER NOT NULL,
    PRIMARY KEY (user_id, video_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (video_id) REFERENCES Videos(video_id) ON DELETE CASCADE
);

-- 5. 좋아요 테이블 (Likes)
CREATE TABLE Likes (
    user_id INTEGER,
    video_id INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, video_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (video_id) REFERENCES Videos(video_id) ON DELETE CASCADE
);

INSERT INTO Users (username, email, password, profile_picture_url) VALUES
('user1', 'user1@example.com', 'password123', 'https://example.com/profile1.jpg'),
('user2', 'user2@example.com', 'password123', 'https://example.com/profile2.jpg'),
('user3', 'user3@example.com', 'password123', 'https://example.com/profile3.jpg'),
('user4', 'user4@example.com', 'password123', 'https://example.com/profile4.jpg'),
('user5', 'user5@example.com', 'password123', 'https://example.com/profile5.jpg'),
('user6', 'user6@example.com', 'password123', 'https://example.com/profile6.jpg'),
('user7', 'user7@example.com', 'password123', 'https://example.com/profile7.jpg'),
('user8', 'user8@example.com', 'password123', 'https://example.com/profile8.jpg'),
('user9', 'user9@example.com', 'password123', 'https://example.com/profile9.jpg'),
('user10', 'user10@example.com', 'password123', 'https://example.com/profile10.jpg');

INSERT INTO Episode (title, episode_count, description, released_date, thumbnail_url, cast_list) VALUES
('Episode 1', 10, 'Description for Episode 1', '2023-01-01', 'https://via.placeholder.com/280x400', 'Actor A, Actor B'),
('Episode 2', 12, 'Description for Episode 2', '2023-02-01', 'https://placehold.co/280x400', 'Actor C, Actor D'),
('Episode 3', 8, 'Description for Episode 3', '2023-03-01', 'https://placehold.co/280x400', 'Actor E, Actor F'),
('Episode 4', 15, 'Description for Episode 4', '2023-04-01', 'https://placehold.co/280x400', 'Actor G, Actor H'),
('Episode 5', 10, 'Description for Episode 5', '2023-05-01', 'https://placehold.co/280x400', 'Actor I, Actor J'),
('Episode 6', 12, 'Description for Episode 6', '2023-06-01', 'https://placehold.co/280x400', 'Actor K, Actor L'),
('Episode 7', 9, 'Description for Episode 7', '2023-07-01', 'https://placehold.co/280x400', 'Actor M, Actor N'),
('Episode 8', 11, 'Description for Episode 8', '2023-08-01', 'https://placehold.co/280x400', 'Actor O, Actor P'),
('Episode 9', 7, 'Description for Episode 9', '2023-09-01', 'https://placehold.co/280x400', 'Actor Q, Actor R'),
('Episode 10', 13, 'Description for Episode 10', '2023-10-01', 'https://placehold.co/280x400', 'Actor S, Actor T');

INSERT INTO Videos (episode_id, video_url, duration, episode_num) VALUES
(1, 'https://example.com/video1.mp4', 1200, 1),
(1, 'https://example.com/video2.mp4', 1400, 2),
(2, 'https://example.com/video3.mp4', 1600, 1),
(2, 'https://example.com/video4.mp4', 1800, 2),
(3, 'https://example.com/video5.mp4', 1200, 1),
(3, 'https://example.com/video6.mp4', 1400, 2),
(4, 'https://example.com/video7.mp4', 1600, 1),
(4, 'https://example.com/video8.mp4', 1800, 2),
(5, 'https://example.com/video9.mp4', 1200, 1),
(5, 'https://example.com/video10.mp4', 1400, 2),
(6, 'https://example.com/video11.mp4', 1600, 1),
(6, 'https://example.com/video12.mp4', 1800, 2),
(7, 'https://example.com/video13.mp4', 1200, 1),
(7, 'https://example.com/video14.mp4', 1400, 2),
(8, 'https://example.com/video15.mp4', 1600, 1),
(8, 'https://example.com/video16.mp4', 1800, 2),
(9, 'https://example.com/video17.mp4', 1200, 1),
(9, 'https://example.com/video18.mp4', 1400, 2),
(10, 'https://example.com/video19.mp4', 1600, 1),
(10, 'https://example.com/video20.mp4', 1800, 2);

INSERT INTO Check_watched (user_id, video_id, progress) VALUES
(1, 1, 50),
(2, 2, 100),
(3, 3, 75),
(4, 4, 60),
(5, 5, 90),
(6, 6, 100),
(7, 7, 30),
(8, 8, 80),
(9, 9, 95),
(10, 10, 100),
(1, 11, 70),
(2, 12, 100),
(3, 13, 45),
(4, 14, 85),
(5, 15, 100);

INSERT INTO Likes (user_id, video_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10),
(1, 11),
(2, 12),
(3, 13),
(4, 14),
(5, 15);