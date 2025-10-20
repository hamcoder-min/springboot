use shoppy;
select database();
show tables;
select * from member;
desc member;

-- pwd 사이즈 변경
alter table member modify column pwd varchar(100) not null;
desc member;

-- mysql은 수정, 삭제 시 update mode를 변경
SET SQL_SAFE_UPDATES = 0;

delete from member
where mdate = '2025-10-17';

delete from member
where id = "test222";

select * from member;

select pwd from member where id = 'hong';

/****************************
	상품 테이블 : product
****************************/
create table product (
	pid		 	int 			auto_increment	primary key
    , name		varchar(200)	not null
    , price	 	long
    , info	 	varchar(200)
    , rate	 	double
    , image	 	varchar(100)
    , imgList	json
);
desc product;
select * from product;
insert into product(name, price, info, rate, image, imgList)
			values
            ('후드티', 15000, '검정색 후드티', 2.2, '2.webp', JSON_Array('2.webp', '2.webp', '2.webp')),
            ('원피스', 25000, '원피스', 4, '3.webp', JSON_Array('3.webp', '3.webp', '3.webp')),
            ('반바지', 12000, '반바지', 3.2, '4.webp', JSON_Array('4.webp', '4.webp', '4.webp')),
            ('티셔츠', 20000, '티셔츠', 5, '5.webp', JSON_Array('5.webp', '5.webp', '5.webp')),
            ('스트레치 비스트 드레스', 55000, '스트레치 비스트 드레스', 3, '6.webp', JSON_Array('6.webp', '6.webp', '6.webp')),
            ('자켓', 115000, '자켓', 3.5, '7.webp', JSON_Array('7.webp', '7.webp', '7.webp'));
            
select * from product;
desc product;

select pid, name, price, info, rate, image, imgList from product where pid = 1;

select pid, name, price, info, rate, trim(image) as image, imgList from product where pid = 1;