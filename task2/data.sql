
insert into goods(name, price, amount) values ('Хлеб', 30, 100),
                                              ('Молоко', 50, 50);
insert into customers(first_name, last_name, company) values ('Иван', 'Иванов', 'Газпром'),
                                                             ('Анна', 'Петрова', 'Лукойл');
insert into shops(name, address, isOpen) values ('Магнит', 'ул. Пушкина, д.6', true),
                                                ('Пятерочка', 'ул. Пушкина, д.2', false);
insert into goods_in_shops(shop_id, goods_id) values (1, 1),
                                                     (1, 2),
                                                    (2, 1);
insert into orders(shop_id, customer_id, order_amount) values (1, 1, 20),
                                                              (2, 2, 10);
