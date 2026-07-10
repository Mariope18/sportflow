CREATE TABLE players
(
    id       UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name     VARCHAR,
    surname  VARCHAR,
    username VARCHAR UNIQUE,
    email    VARCHAR
);

CREATE TABLE courts
(
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name        VARCHAR,
    sport       VARCHAR,
    hourly_rate NUMERIC,
    street      VARCHAR,
    zip_code    VARCHAR,
    city        VARCHAR
);

CREATE TABLE bookings
(
    id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    court_id   UUID,
    player_id  UUID,
    date       DATE,
    start_time TIME,
    end_time   TIME,
    status     VARCHAR,
    CONSTRAINT fk_court FOREIGN KEY (court_id) REFERENCES courts (id),
    CONSTRAINT fk_player FOREIGN KEY (player_id) REFERENCES players (id)
);