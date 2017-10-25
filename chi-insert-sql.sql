insert into cc_rest
	(rid, name, lat, lng, status)
values
	(1, 'title', 37.552320, 126.937588, '');
    
-- %중요% member table에 uid가 존재해야합니다. - uid는 바귈 수 있으니 먼저 회원가입 후 시도하세요.

insert into cc_article
	(article_id, rid, comment, status, likes, hate, write_dt, uid)
values
	(0, 1, 'comment', '', 0, 0, '171009', '3HgHeOlylIZR');
    
insert into cc_img
	(img_id, rid, article_id, path)
values
	(0, 1, 0, '../img/insert/duch.jpg'),
    (1, 1, 0, '../img/insert/jeju.jpg');
    
insert into cc_menu
	(id, rid, article_id, img_id, x, y, menu, price)
values
	(0, 1, 0, 0, 10, 10, 'img0_menu0', 1000),
	(0, 1, 0, 1, 10, 10, 'img1_menu0', 2000),
	(1, 1, 0, 1, 40, 40, 'img1_menu1', 3000);
    
insert into cc_tag
	(tag_id, rid, article_id, tag)
values
(0, 1, 0, 'tag1'),
(1, 1, 0, 'tag1');




