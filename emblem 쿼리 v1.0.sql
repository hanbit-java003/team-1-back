insert emblem
	(name, img, how_achieve)
values
	('signin', '../img/emblem/confetti.png', '회원가입을 축하합니다.'),
    ('rest100', '../img/emblem/star.png', '식당 100개를 입력하셨습니다.'),
    ('article100', '../img/emblem/moon.png', '100 그릇 식사를 하셨습니다.'),
    ('noodle', '../img/emblem/noodle.png', '면 요리를 엄청 좋아하시나 봐요.');
        
insert emblem_member
	(uid)
values
	('3HgHeOlylIZR');
    
insert emblem_achive
	(uid, emblem_name)
values
	('3HgHeOlylIZR', 'signin');