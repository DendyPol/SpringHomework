drop table if exists product;
create table product
(
  id    bigint primary key,
  name  varchar not null,
  price numeric not null
);
