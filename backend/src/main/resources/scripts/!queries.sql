-- Балансы всех пользователей
select u.email, (select w.sum from wallets w where w.user_id = u.id) from users u;

--Текущий баланс  пользователя с email
select sum from wallets w
where w.user_id = (select user_id from users where email='shutovna1987@gmail.com');

--Текущий баланс системы
select sum from wallets w
where w.user_id = (select user_id from users where email='system@moyserf.ru');

--Изменение баланса пользователя
update wallets
set sum=100*100
where user_id = (select user_id from users where email='shutovna1987@gmail.com');

--Изменение времени просмотра всех сайтов на 24 часа назад
update views set viewed_at = NOW() - INTERVAL '1 day';