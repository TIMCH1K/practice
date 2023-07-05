create table goods(
    id serial primary key,
    name varchar(100),
    price integer check (price > 0),
    amount integer check (amount >= 0)
);
create table customers(
    id serial primary key,
    first_name varchar(100),
    last_name varchar(100),
    company varchar(100)
);
create table shops(
    id serial primary key,
    name varchar(100),
    address varchar(100),
    isOpen boolean default false
);
create table goods_in_shops(
    shop_id integer references shops(id),
    goods_id integer references goods(id),
    primary key (shop_id, goods_id)
);
create table orders(
    id serial primary key,
    shop_id integer references shops(id),
    customer_id integer references customers(id),
    order_amount integer check (order_amount > 0)
);