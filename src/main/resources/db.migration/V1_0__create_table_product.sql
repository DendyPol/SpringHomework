drop table if exists product;
create table product
(
  id    bigint primary key,
  name  varchar(255) not null,
  price numeric(38)  not null
);
