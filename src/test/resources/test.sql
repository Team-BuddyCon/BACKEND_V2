SET FOREIGN_KEY_CHECKS = 0;
truncate table coupon;
truncate table user;
SET FOREIGN_KEY_CHECKS = 1;

insert into user values(1, '2023-06-28 15:30:00', '2023-06-28 15:30:00');

insert into coupon values(1, '2023-06-28 15:30:00', '2023-06-28 15:30:00', 'barcode', '2023-07-30', 'imageurl', 'memo', 'name1', 'CU', 1);
insert into coupon values(2, '2023-06-28 15:30:00', '2023-06-28 15:30:00', 'barcode', '2023-07-29', 'imageurl', 'memo', 'name2', 'CU', 1);
insert into coupon values(3, '2023-06-28 15:30:00', '2023-06-28 15:30:00', 'barcode', '2023-07-28', 'imageurl', 'memo', 'name3', 'CU', 1);
insert into coupon values(4, '2023-06-28 15:30:00', '2023-06-28 15:30:00', 'barcode', '2023-07-27', 'imageurl', 'memo', 'name4', 'CU', 1);
insert into coupon values(5, '2023-06-28 15:30:00', '2023-06-28 15:30:00', 'barcode', '2023-07-26', 'imageurl', 'memo', 'name5', 'CU', 1);
insert into coupon values(6, '2023-06-28 15:30:00', '2023-06-28 15:30:00', 'barcode', '2023-07-25', 'imageurl', 'memo', 'name6', 'CU', 1);
insert into coupon values(7, '2023-06-28 15:30:00', '2023-06-28 15:30:00', 'barcode', '2023-07-24', 'imageurl', 'memo', 'name7', 'CU', 1);