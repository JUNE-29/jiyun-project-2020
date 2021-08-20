-- 회원정보
insert into book_members(member_no, name, email, password, cdt, photo)
values(1, 'user1', 'user1@test.com', password('1111'), '2021-01-01', 'user1.jpg');

insert into book_members(member_no, name, email, password, cdt, photo)
values(2, 'user2', 'user2@test.com', password('1111'), '2021-01-02', 'user2.jpg');

insert into book_members(member_no, name, email, password, cdt, photo)
values(3, 'user3', 'user3@test.com', password('1111'), '2021-03-03', 'user3.jpg');


-- 읽은 책 정보
insert into bookcase(read_book_no, book_titl, author, pub, isbn, datetime, conts, thumbnail, 
star_score, member_no)
values(1, '완전한 행복', '정유정', '은행나무', 9791167370280, '2021-06-08', 
'500여 쪽을 꽉 채운 압도적인 서사와 적재적소를 타격하는 속도감 있는 문장, 
치밀하고 ..','thumnail01.jpg', 4, 1);

insert into bookcase(read_book_no, book_titl, author, pub, isbn, datetime, conts, thumbnail, 
star_score, member_no)
values(2, '천 개의 파랑', '천선란', '허블', 9791190090261, '2021-08-19', 
'SF가 진보하는 기술 속에서 변화하고 발전하는 모습을 예견하는 장르라면, 
『천 개의 파랑』은 진보하는 기술 속에서 희미해지는 존재들을 올곧게 응시하는 소설이다.',
'thumnail02.jpg', 3, 1);


-- 읽고 싶은 책 정보
insert into bookBasket(basket_book_no, book_titl, author, pub, isbn, datetime, conts, thumbnail, 
expected_score, expectation_review, member_no)
values(1, '시선으로부터,', '정세랑', '문학동네', 9788954672214, '2020-06-05', 
'독창적인 목소리와 세계관으로 구축한 SF소설부터 우리 시대의 현실에 단단히 발 딛고 나아가는 이야기들까지, 
폭넓은 작품 세계로 우리에게 늘 새로운 놀라움을 선사했던 정세랑.',
'thumnail03.jpg', 4, '기대된다', 2);

insert into bookBasket(basket_book_no, book_titl, author, pub, isbn, datetime, conts, thumbnail, 
expected_score, expectation_review, member_no)
values(2, '지구 끝의 온실,', '김초엽', '자이언트북스', 9791191824001, '2021-08-18', 
' 김초엽 첫 장편소설, 모두가 간절히 기다려온 이야기 
이미 폭넓은 독자층을 형성하며 열렬한 사랑을 받고 있는 김초엽 작가는 
더스트로 멸망한 이후의 세계를 첫 장편소설의 무대로 삼았다. ',
'thumnail04.jpg', 5, '완전 기대된다', 1);


-- 책 기록함
insert into bookmark(bookmark_no, bookmark_titl, conts, photo, bookmark_cdt,
read_book_no, member_no)
values(1,'완전한 행복 읽고나서', '《완전한 행복》은 버스도 다니지 않는 버려진 시골집에서 
늪에 사는 오리들을 먹이기 위해 오리 먹이를 만드는 한 여자의 뒷모습에서 시작된다. 
그녀와 딸, 그리고 그 집을 찾은 한 남자의 얼굴을 비춘다. 얼굴을 맞대고 웃고 있지만 그들이 추구하는 서로 다른 행복은 서서히 불협화음을 만들어낸다. 이 기묘한 불협화음은 늪에서 들려오는 괴기한 오리 소리와 공명하며 불안의 그림자를 드리운다. 
그들은 각자 행복을 위해 노력한다. 그러나 노력할수록 더 깊이 빠져드는 늪처럼, 
그림자는 점점 더 깊은 어둠으로 가족을 이끈다.',
'photo01', '2021-08-01', 1, 1);

insert into bookmark(bookmark_no, bookmark_titl, conts, photo, bookmark_cdt,
basket_book_no, member_no)
values(2,'천 개의 파랑은...', '이렇다 저렇다...','photo02', '2021-08-02', 1, 2);

