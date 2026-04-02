CREATE TABLE games (
                       id              BIGSERIAL PRIMARY KEY,
                       title           VARCHAR(255) NOT NULL,
                       description     TEXT,
                       genre           VARCHAR(100),
                       platform        VARCHAR(255),
                       release_year    INTEGER,
                       average_rating  DOUBLE PRECISION,
                       image_url       VARCHAR(255),
                       created_at      TIMESTAMP NOT NULL
);