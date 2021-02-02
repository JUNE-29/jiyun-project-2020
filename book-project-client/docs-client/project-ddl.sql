-- 책 게시판 테이블 삭제 
drop table if exists book_board;

-- 회원 테이블 삭제
drop table if exists book_member;

-- 북마크 게시판 테이블 삭제 
drop table if exists bookmark;

-- 사진 게시판 테이블 삭제
drop table if exists book_photo;

-- 사진 게시물 첨부 파일 테이블 삭제
drop table if exists book_photo_file;

-- bookBoard 테이블 생성
create table book_board (
  board_id int not null auto_increment primary key comment '도서 데이터 식별 번호', 
  titl varchar(255) not null comment '도서명',
  auth varchar(30) not null comment '지은이',
  pub varchar(30) not null comment '출판사',
  cate varchar(30) not null comment '카테고리',
  pub_dt varchar(30) null comment '출판연도',
  conts text null comment '메모',
  photo varchar(255) null comment '사진',
  score int null comment '리뷰점수',
  book_st int not null comment '상태',
  cdt datetime default now() comment '생성일'
) comment '책 게시판'; 


-- 회원 테이블 생성
create table book_member (
  member_id int not null auto_increment primary key comment '회원 데이터 식별 번호',
  name varchar(30) not null comment '이름',
  email varchar(255) not null comment '이메일',
  pwd varchar(255) not null comment '암호',
  cdt datetime default now() comment '등록일', 
  photo varchar(255) comment '사진'
) comment '회원';

create unique index UIX_book_member_email
  on book_member ( -- 회원
    email asc    -- 이메일
  );
  
-- 북마크 테이블 생성
create table bookmark (
  bookmark_id int not null auto_increment primary key comment '북마크 게시물 식별 번호',
  titl varchar(255) not null comment '게시글 제목',
  book_titl varchar(255) not null comment '책 제목',
  auth varchar(30) not null comment '지은이',
  pub varchar(30) not null comment '출판사',
  conts text not null comment '내용',
  photo varchar(255) comment '사진',
  cdt datetime default now() comment '생성일'
) comment '북마크 게시판';

-- 사진 게시물 테이블 생성
create table book_photo (
  photo_id int not null auto_increment primary key comment '사진 게시물 식별 번호',
  bookmark_id int not null comment '북마크 게시물 번호',
  titl varchar(255) not null comment '제목',
  cdt datetime default now() comment '생성일',
  vw_cnt int default 0 comment '조회수',
  -- lesson_id에 저장되는 값은 lms_lesson 테이블의 lesson_id 값으로 제한하는 조건을 추가한다.
  constraint fk_photo_to_bookmark foreign key (bookmark_id)
    references bookmark (bookmark_id)
) comment '사진게시물';

-- 사진 게시물에 첨부하는 사진 파일 테이블 생성
create table book_photo_file (
  photo_file_id int not null auto_increment primary key comment '사진 파일 식별 번호',
  photo_id int not null,
  file_path varchar(255) not null,
  constraint fk_photo_file_to_book_photo foreign key (photo_id)
    references book_photo (photo_id)
) comment '사진 게시물 첨부파일 테이블'; 






