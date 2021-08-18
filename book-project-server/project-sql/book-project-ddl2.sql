-- 회원 정보
DROP TABLE IF EXISTS book_members RESTRICT;

-- 책 기록함 게시판
DROP TABLE IF EXISTS bookmark RESTRICT;

-- 읽은 책 보관함
DROP TABLE IF EXISTS bookcase RESTRICT;

-- 읽고 싶은 책 보관함
DROP TABLE IF EXISTS bookBasket RESTRICT;


-- 회원정보
CREATE TABLE book_members (
  member_no     INTEGER       NOT NULL    COMMENT '회원번호',
  name          VARCHAR(50)   NOT NULL    COMMENT '이름',
  email         VARCHAR(50)   NOT NULL    COMMENT '이메일',
  password      VARCHAR(50)   NOT NULL    COMMENT '비밀번호',
  cdt           DATETIME      NOT NULL    DEFAULT now() COMMENT '가입날짜',
  photo         VARCHAR(255)  NULL        COMMENT '프로필사진'
);

-- 회원 정보
ALTER TABLE book_members
  ADD CONSTRAINT PK_book_members -- 회원정보 기본키
    PRIMARY KEY (
  member_no -- 회원번호
);

ALTER TABLE book_members
 MODIFY COLUMN member_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '회원번호';


-- 책 기록함 게시판
CREATE TABLE bookmark (
  bookmark_no       INTEGER       NOT NULL  COMMENT '북마크번호',
  bookmark_titl     VARCHAR(255)  NOT NULL  COMMENT '게시물제목',
  conts             TEXT          NOT NULL  COMMENT '게시물내용',
  photo             VARCHAR(255)  NULL      COMMENT '사진',
  bookmark_cdt      DATETIME      NOT NULL DEFAULT now() COMMENT  '등록일',
  read_book_no      INTEGER       NULL  COMMENT '읽은책번호',
  basket_book_no    INTEGER       NULL  COMMENT '읽고싶은책번호',
  member_no         INTEGER       NOT NULL  COMMENT '회원번호'
);

-- 책 기록함 게시판
ALTER TABLE bookmark
  ADD CONSTRAINT PK_bookmark -- 책 기록함 게시판 기본키
    PRIMARY KEY (
  bookmark_no -- 북마크 번호
);

ALTER TABLE bookmark
 MODIFY COLUMN bookmark_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '북마크번호';


-- 읽은 책 보관함
CREATE TABLE bookcase (
  read_book_no    INTEGER       NOT NULL    COMMENT '책번호',
  book_titl       VARCHAR(255)  NOT NULL    COMMENT '책제목',
  author          VARCHAR(30)   NOT NULL    COMMENT '지은이',
  pub             VARCHAR(30)   NOT NULL    COMMENT '출판사',
  isbn            VARCHAR(50)   NOT NULL    COMMENT 'isbn',
  datetime        VARCHAR(30)   NOT NULL    COMMENT '출시일자',
  conts           TEXT          NULL        COMMENT '책내용',
  thumbnail       VARCHAR(255)  NOT NULL    COMMENT '썸네일',
  star_score      INTEGER       NULL        COMMENT '별점',
  member_no       INTEGER       NOT NULL    COMMENT '회원번호'
);

ALTER TABLE bookcase 
  ADD CONSTRAINT PK_BOOKCASE 
  PRIMARY KEY (
  read_book_no
);

ALTER TABLE bookcase
  MODIFY COLUMN read_book_no INTEGER NOT NULL AUTO_INCREMENT 
  COMMENT '읽은책 보관함 번호';


-- 읽고 싶은 책 보관함
CREATE TABLE bookbasket (
  basket_book_no      INTEGER         NOT NULL    COMMENT '책번호',
  book_titl           VARCHAR(255)    NOT NULL    COMMENT '책제목',
  author              VARCHAR(30)     NOT NULL    COMMENT '지은이',
  pub                 VARCHAR(30)     NOT NULL    COMMENT '출판사',
  isbn                VARCHAR(50)     NOT NULL    COMMENT 'isbn',
  datetime            VARCHAR(30)     NOT NULL    COMMENT '출시일자',
  conts               TEXT            NULL        COMMENT '책내용',
  thumbnail           VARCHAR(255)    NOT NULL    COMMENT '썸네일',
  expected_score      INTEGER         NULL        COMMENT '기대점수',
  expectation_review  TEXT            NULL        COMMENT '기대평',
  member_no           INTEGER         NOT NULL    COMMENT '회원번호'
);

ALTER TABLE bookbasket
  ADD CONSTRAINT PK_BOOKBASKET
  PRIMARY KEY (
  basket_book_no
);

ALTER TABLE bookbasket
  MODIFY COLUMN basket_book_no INTEGER NOT NULL AUTO_INCREMENT 
  COMMENT '읽고 싶은 책 보관함 번호';
