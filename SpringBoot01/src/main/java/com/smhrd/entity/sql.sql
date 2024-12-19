-- 회원정보(email, pw, tel, address)
-- 를 저장하는 테이블 생성

CREATE TABLE Member(
	email varchar(100),
	pw varchar(100) not null,
	tel varchar(100),
	address varchar(200),
	constraint member_email_pk primary key(email)
);
-- 블록지정 후 > alt + X

SELECT * FROM Member;

-- 게시글
-- 제목, 작성자, 작성일, 내용, 번호, 조회수, 이미지
CREATE TABLE Board
(
	title varchar(100) not null,
	writer varchar(100) not null,
	content varchar(1000),
	img varchar(200),
	indate datetime default now(),
	count int default 0,
	idx int auto_increment,
	
	constraint board_idx_pk primary key(idx),
	constraint board_writer_fk foreign key(writer)
	references Member(email) 
);
--관계형 데이터 베이스 / 문자만 저장함

INSERT INTO Board(title, writer, content)
VALUES(
	'DCX기반 빅데이터 분석서비스 개발자 과정(1)',
	'123',
	'게시판 만들기'
);

UPDATE Board set count = 35 where writer='123';

