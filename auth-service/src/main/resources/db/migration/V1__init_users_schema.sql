CREATE TABLE users
(
    id       UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username VARCHAR UNIQUE NOT NULL,
    email    VARCHAR UNIQUE NOT NULL,
    password VARCHAR NOT NULL,
    role     VARCHAR NOT NULL
);