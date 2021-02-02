-- bookBoard 예제 데이터 
insert into book_board(board_id, titl, auth, pub, cate, pub_dt, conts, photo, score, book_st, cdt)
  values(1, '2030 축의 전환', '마우로 기엔', '리더스북', '경제','2020-10-16', '경제이야기', 'a.jpg', 5, 1, '2021-02-02');
insert into book_board(board_id, titl, auth, pub, cate, pub_dt, conts, photo, score, book_st, cdt)
  values(2, '파친코', '이민진', '문학사상', '소설','2018-03-09', '이민진의 장편소설', 'b.jpg', 4, 2, '2021-02-02');

-- bookmark 예제 데이터
insert into bookmark(bookmark_id, titl, book_titl, auth, pub, conts, photo, cdt)
  values(1, '진짜 공정한건가', '공정하다는 착각', '마이클 샌델', '와이즈베리', '능력주의는 모두에게 같은 기회를 제공하는걸까..', 'q.jpg', '2021-02-01');
insert into bookmark(bookmark_id, titl, book_titl, auth, pub, conts, photo, cdt)
  values(2, '나의 첫 과학소설', '우리가 빛의 속도로 갈 수 없다면', '김초엽', '허블', '하지만 마음 한켠 소소한 일탈을 꿈꾸는 존재가 아닐까 싶다.', 'w.jpg', '2021-02-01');
  
-- 회원 예제 데이터
insert into book_member(member_id, name, email, pwd, cdt, photo) 
  values(11, 'user1', 'user1@test.com', password('1111'), '2021-02-02', 'user1.jpg');
insert into book_member(member_id, name, email, pwd, cdt, photo) 
  values(12, 'user2', 'user2@test.com', password('1111'), '2021-02-02', 'user2.jpg');
insert into book_member(member_id, name, email, pwd, cdt, photo) 
  values(13, 'user3', 'user3@test.com', password('1111'), '2021-02-02', 'user3.jpg');

-- bookmark 사진 게시물 예제 데이터
insert into book_photo(photo_id, bookmark_id, titl) 
  values(1, 1, '공정하다는 착각 책 읽기 매일 인증 사진');
insert into book_photo(photo_id, bookmark_id, titl) 
  values(2, 1, '카페에서 공정하다는 착각 책 읽는 중');
insert into book_photo(photo_id, bookmark_id, titl) 
  values(3, 2, '우리가 빛의 속도로 갈 수 없다면 책 읽기 매일 인증 사진');

-- bookmark 사진 게시물 첨부 파일 예제 데이터
insert into book_photo_file(photo_file_id, photo_id, file_path)
  values(1, 1, 'a1.gif');
insert into book_photo_file(photo_file_id, photo_id, file_path)
  values(2, 1, 'a2.gif');
insert into book_photo_file(photo_file_id, photo_id, file_path)
  values(3, 1, 'a3.gif');
insert into book_photo_file(photo_file_id, photo_id, file_path)
  values(4, 2, 'b1.gif');
insert into book_photo_file(photo_file_id, photo_id, file_path)
  values(5, 3, 'c1.gif');


