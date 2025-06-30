-- 길 (routes)
CREATE TABLE routes (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  idx VARCHAR(100) UNIQUE NOT NULL,
  name VARCHAR(100),
  line_msg varchar(2000),
  overview varchar(10000),
  brd_div BOOLEAN NOT NULL,
  created_at DATETIME NOT NULL,
  modified_at DATETIME NOT NULL
);

-- 테마 (themes)
CREATE TABLE themes (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  theme VARCHAR(100) NOT NULL
);

-- 코스 (courses)
CREATE TABLE courses (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  theme_id BIGINT,
  idx VARCHAR(100) UNIQUE NOT NULL,
  route_idx VARCHAR(100) NOT NULL,
  name VARCHAR(100),
  distance DOUBLE,
  req_time DOUBLE,
  level INT,
  cycle BOOLEAN NOT NULL,
  contents varchar(10000),
  summary varchar(4000),
  traveler_info varchar(2000),
  sido_code INT,
  sigungu_code INT,
  brd_div BOOLEAN NOT NULL,
  created_at DATETIME NOT NULL,
  modified_at DATETIME NOT NULL,
  reward INT,
  FOREIGN KEY (theme_id) REFERENCES themes(id),
  FOREIGN KEY (route_idx) REFERENCES routes(idx)
);

CREATE INDEX idx_sido_code_for_courses ON courses(sido_code);
CREATE INDEX idx_sigungu_code_for_courses ON courses(sigungu_code);


-- 코스경로 (paths)
CREATE TABLE path_coords (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  course_id BIGINT NOT NULL,
  latitude DOUBLE,
  longitude DOUBLE,
  ordering INT,
  FOREIGN KEY (course_id) REFERENCES courses(id)
);

-- 시도 (sidos)
CREATE TABLE sidos (
  code INT PRIMARY KEY,
  name VARCHAR(20)
);

-- 시군구 (sigungus)
CREATE TABLE sigungus (
	id int primary key,
	code INT not null,
	name VARCHAR(20),
	sido_code INT NOT NULL,
	FOREIGN KEY (sido_code) REFERENCES sidos(code)
);
CREATE INDEX idx_sigungu_code ON sigungus(code);

CREATE TABLE contenttypes (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL
);


-- 명소 (attractions)
CREATE TABLE attractions (
  id BIGINT PRIMARY KEY,
  content_id INT UNIQUE,
  title VARCHAR(500),
  first_image1 VARCHAR(500),
  first_image2 VARCHAR(500),
  map_level INT,
  latitude DOUBLE,
  longitude DOUBLE,
  tel VARCHAR(20),
  addr1 VARCHAR(1000),
  addr2 VARCHAR(1000),
  homepage VARCHAR(1000),
  overview VARCHAR(10000),
  sido_code INT,
  sigungu_code INT,
  content_type_id INT,
  FOREIGN KEY (sido_code) REFERENCES sidos(code),
  FOREIGN KEY (content_type_id) REFERENCES contenttypes(id)
);

-- 회원 (users)
CREATE TABLE users (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(30),
  nickname VARCHAR(30),
  email VARCHAR(50) UNIQUE NOT NULL,
  password VARCHAR(300) NOT NULL,
  role varchar(30)
);

-- 방문예정코스 (scheduled_courses)
CREATE TABLE scheduled_courses (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  course_id BIGINT NOT NULL,
  due_date DATETIME NOT NULL,
  user_id BIGINT NOT NULL,
  FOREIGN KEY (course_id) REFERENCES courses(id),
  FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 방문 코스 (visited_courses)
CREATE TABLE visited_courses (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  course_id BIGINT NOT NULL,
  visited_at DATETIME NOT NULL,
  user_id BIGINT NOT NULL,
  start_coords_id BIGINT,
  end_coords_id BIGINT,
  distance double default 0,
  img_url VARCHAR(500),
  stars int default 0,
  review varchar(2000),
  is_finished BOOLEAN default 0,
  FOREIGN KEY (course_id) REFERENCES courses(id),
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (start_coords_id) REFERENCES path_coords(id),
  FOREIGN KEY (end_coords_id) REFERENCES path_coords(id),
  constraint min_limitation_stars check(stars >= 0)
);

-- 방문예정명소 (scheduled_attractions)
CREATE TABLE scheduled_attractions (
  id BIGINT PRIMARY KEY,
  attraction_id BIGINT NOT NULL,
  scourse_id BIGINT NOT NULL,
  FOREIGN KEY (attraction_id) REFERENCES attractions(id),
  FOREIGN KEY (scourse_id) REFERENCES scheduled_courses(id)
);

-- 방문명소 (visited_attractions)
CREATE TABLE visited_attractions (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  attraction_id BIGINT NOT NULL,
  vcourse_id BIGINT NOT NULL,
  FOREIGN KEY (attraction_id) REFERENCES attractions(id),
  FOREIGN KEY (vcourse_id) REFERENCES visited_courses(id)
);


-- 리프레시 토큰 (user_refresh_tokens)
CREATE TABLE user_refresh_tokens (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  token varchar(500),
  expires_at DATETIME NOT NULL,
  created_at DATETIME NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 건강정보 (health_infos)
CREATE TABLE health_infos (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  gender BOOLEAN NOT NULL,
  height DOUBLE,
  weight DOUBLE,
  age INT,
  disease_mask BIGINT,
  smoking BOOLEAN,
  drink_per_week INT,
  exercise_per_week INT,
  FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 티어 (tiers)
CREATE TABLE tiers (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(100),
  level INT,
  bound INT
);

-- 아이템 (items)
CREATE TABLE items (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(100),
  price INT,
  category INT,
  on_sale BOOLEAN,
  req_tier BIGINT,
  img_url VARCHAR(500),
  overview varchar(2000),
  FOREIGN KEY (req_tier) REFERENCES tiers(id)
);

-- 업적 (achievements)
CREATE TABLE achievements (
  id INT PRIMARY KEY AUTO_INCREMENT,
  goal varchar(500),
  reward INT,
  img_url VARCHAR(500)
);

-- 업적 달성 (achievement_history)
CREATE TABLE achievement_history (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  achievement_id INT NOT NULL,
  user_id BIGINT NOT NULL,
  achieved_at DATETIME NOT NULL,
  FOREIGN KEY (achievement_id) REFERENCES achievements(id),
  FOREIGN KEY (user_id) REFERENCES users(id)
);


-- 출석 체크 (attendance_history)
CREATE TABLE attendance_history (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  attended_at DATETIME NOT NULL,
  user_id BIGINT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 질병 목록 (diseases)
CREATE TABLE diseases(
	id bigint primary key auto_increment,
	name varchar(20) not null
);

-- 게시글 (articles)
CREATE TABLE articles (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  course_id BIGINT,
  title VARCHAR(500),
  content longtext,
  created_at DATETIME NOT NULL,
  modified_at DATETIME NOT NULL,
  like_count INT DEFAULT 0,
  view_count INT DEFAULT 0,
  comment_count INT DEFAULT 0,
  board_type INT,
  notice BOOLEAN,
  disease_id BIGINT,
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (course_id) REFERENCES courses(id),
  FOREIGN KEY (disease_id) REFERENCES diseases(id)
);

-- 댓글 (comments)
CREATE TABLE comments (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  article_id BIGINT NOT NULL,
  content varchar(10000),
  created_at DATETIME NOT NULL,
  modified_at DATETIME NOT NULL,
  user_id BIGINT NOT NULL,
  FOREIGN KEY (article_id) REFERENCES articles(id),
  FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 게시글 이미지 (article_images)
CREATE TABLE article_images (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  article_id BIGINT NOT NULL,
  img_url VARCHAR(500),
  FOREIGN KEY (article_id) REFERENCES articles(id)
);

-- 좋아요 (likes)
CREATE TABLE likes (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  article_id BIGINT NOT NULL,
  user_id BIGINT NOT NULL,
  FOREIGN KEY (article_id) REFERENCES articles(id),
  FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 일지 (diaries)
CREATE TABLE diaries (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(1000),
  created_at DATETIME NOT NULL,
  content longtext,
  vcourse_id BIGINT NOT NULL,
  user_id BIGINT NOT NULL,
  FOREIGN KEY (vcourse_id) REFERENCES visited_courses(id),
  FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 일지 이미지 (diary_images)
CREATE TABLE diary_images (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  diary_id BIGINT NOT NULL,
  img_url VARCHAR(500),
  FOREIGN KEY (diary_id) REFERENCES diaries(id)
);

CREATE TABLE point_history (
	id	bigint	NOT NULL,
	point	int	NOT NULL,
	aquired_at	datetime	NULL,
	attendance_id	bigint,
	article_id	bigint,
	comment_id	bigint,
	vcourse_id	bigint,
	user_id	bigint	NOT NULL,
	FOREIGN KEY (attendance_id) REFERENCES attendance_history(id),
	FOREIGN KEY (article_id) REFERENCES articles(id),
	FOREIGN KEY (comment_id) REFERENCES comments(id),
	FOREIGN KEY (vcourse_id) REFERENCES visited_courses(id),
	FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE purchase_history (
	id	bigint	NOT NULL,
	amount	int	NULL,
	bought_at	datetime	NULL,
	cost	int	NULL,
	user_id	bigint	NOT NULL,
	item_id	bigint	NOT NULL,
	FOREIGN KEY (user_id) REFERENCES users(id),
	FOREIGN KEY (item_id) REFERENCES items(id)
);

-- 프로필 (profiles)
CREATE TABLE profiles (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  activity_point INT,
  mileage INT CHECK (mileage >= 0),
  achievement1 INT,
  achievement2 INT,
  achievement3 INT,
  img_url VARCHAR(200),
  msg_frequency VARCHAR(100),
  msg_agree BOOLEAN,
  tier_id BIGINT,
  attendance_count INT,
  sido_code INT,
  sigungu_code INT,
  diary_id bigint,
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (achievement1) REFERENCES achievements(id),
  FOREIGN KEY (achievement2) REFERENCES achievements(id),
  FOREIGN KEY (achievement3) REFERENCES achievements(id),
  FOREIGN KEY (tier_id) REFERENCES tiers(id),
  FOREIGN KEY (sido_code) REFERENCES sidos(code),
  FOREIGN KEY (diary_id) REFERENCES diaries(id)
);

-- 프로필 데코 (profile_deco)
CREATE TABLE profile_deco (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  profile_id BIGINT NOT NULL,
  item_id BIGINT NOT NULL,
  FOREIGN KEY (profile_id) REFERENCES profiles(id),
  FOREIGN KEY (item_id) REFERENCES items(id)
);

-- 인벤토리 (inventory)
CREATE TABLE inventory(
	id bigint auto_increment primary key,
    count int default 0,
    item_id bigint,
    user_id bigint,
    constraint fk_inventory_to_item foreign key(item_id) references items(id),
	constraint fk_inventory_to_user foreign key(user_id) references users(id)
);

