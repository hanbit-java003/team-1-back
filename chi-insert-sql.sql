insert into cc_rest
	(rid, name, lat, lng, status)
values
	(1, 'title', 37.552320, 126.937588, '');
    
insert into cc_article
	(article_id, rid, comment, status, likes, hate, write_dt)
values
	(0, 1, 'comment', '', 0, 0, '171009');
    
insert into cc_img
	(img_id, rid, article_id, path)
values
	(0, 1, 0, '../img/insert/duch.jpg'),
    (1, 1, 0, '../img/insert/jeju.jpg');
    
insert into cc_menu
	(id, rid, article_id, img_id, x, y, menu)
values
	(0, 1, 0, 0, 10, 10, 'img0_menu0'),
	(0, 1, 0, 1, 10, 10, 'img1_menu0'),
	(1, 1, 0, 1, 40, 40, 'img1_menu1');
    
insert into cc_tag
	(tag_id, rid, article_id, tag)
values
(0, 1, 0, 'tag1'),
(1, 1, 0, 'tag1');
    

