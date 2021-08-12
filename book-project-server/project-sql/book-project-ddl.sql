-- 책 기록함 게시판
DROP TABLE IF EXISTS bookmark RESTRICT;

-- 회원 정보
DROP TABLE IF EXISTS book_members RESTRICT;

-- 책 정보
DROP TABLE IF EXISTS books RESTRICT;

-- 책 보관함 게시판
DROP TABLE IF EXISTS bookboard RESTRICT;


-- 회원정보
CREATE TABLE book_members (
  member_no     INTEGER         NOT NULL    COMMENT '회원번호',
  name          VARCHAR(50)     NOT NULL    COMMENT '이름',
  email         VARCHAR(50)     NOT NULL    COMMENT '이메일',
  password      VARCHAR(50)     NOT NULL    COMMENT '비밀번호',
  cdt           DATETIME        NOT NULL    DEFAULT now() COMMENT '가입날짜',
  photo         VARCHAR(255)    NULL        COMMENT '프로필사진'
) 
COMMENT '회원 정보';

-- 회원 정보
ALTER TABLE book_members
  ADD CONSTRAINT PK_book_members -- 회원정보 기본키
    PRIMARY KEY (
  member_no -- 회원번호
);

ALTER TABLE book_members
 MODIFY COLUMN member_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '회원번호';

-- 책 정보
CREATE TABLE books (
  book_no       INTEGER         NOT NULL  COMMENT '책번호',
  book_titl     TEXT            NOT NULL  COMMENT '책제목',
  book_author   VARCHAR(30)     NOT NULL  COMMENT '지은이',
  pub           VARCHAR(30)     NOT NULL  COMMENT '출판사',
  isbn          VARCHAR(30)     NOT NULL  COMMENT 'isbn',
  datetime      VARCHAR(30)     NOT NULL  COMMENT '출시일자',
  conts         TEXT            NULL      COMMENT '책내용',
  thumbnail     VARCHAR(255)    NULL      COMMENT '썸네일',
  score         INTEGER         NULL      COMMENT '책점수',
  bookboard_no  INTEGER         NOT NULL  COMMENT '게시판번호',
  before_score  INTEGER         NULL      COMMENT '기대점수',
  member_no     INTEGER         NOT NULL  COMMENT '회원번호'
)
COMMENT '책 정보';

-- 책 정보 
ALTER TABLE books 
  ADD CONSTRAINT PK_books
  PRIMARY KEY (
  book_no
);

ALTER TABLE books
  MODIFY COLUMN book_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '책 정보 번호';

 
-- 책 기록함 게시판
CREATE TABLE bookmark (
  bookmark_no       INTEGER       NOT NULL COMMENT '북마크번호',
  bookmark_titl     VARCHAR(255)  NOT NULL COMMENT '게시물제목',
  conts             TEXT          NOT NULL COMMENT '게시물내용',
  photo             VARCHAR(255)  NULL     COMMENT '사진',
  bookmark_cdt      DATETIME      NOT NULL DEFAULT now() COMMENT  '등록일',
  member_no         INTEGER       NOT NULL COMMENT '회원번호',
  book_no           INTEGER       NOT NULL COMMENT '책번호'
);

-- 책 기록함 게시판
ALTER TABLE bookmark
  ADD CONSTRAINT PK_bookmark -- 책 기록함 게시판 기본키
    PRIMARY KEY (
  bookmark_no -- 북마크 번호
);

ALTER TABLE bookmark
 MODIFY COLUMN bookmark_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '북마크번호';

-- 책 보관함 게시판 
CREATE TABLE bookboard (
  bookboard_no    INTEGER     NOT NULL COMMENT '책게시판 번호',
  bookboard_titl  VARCHAR(50) NOT NULL COMMENT '책게시판이름',
  book_count      INTEGER     NOT NULL COMMENT '수량',
  book_no         INTEGER     NOT NULL COMMENT '책번호',
  member_no       INTEGER     NOT NULL COMMENT '회원번호'
);

ALTER TABLE bookboard ADD CONSTRAINT PK_bookboard PRIMARY KEY (
  bookboard_no
);

ALTER TABLE bookboard
 MODIFY COLUMN bookboard_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '책 보관함 번호';
