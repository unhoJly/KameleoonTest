CREATE TABLE "users"
(
    "id"       BIGINT       NOT NULL,
    "name"     VARCHAR(100) NOT NULL,
    "email"    VARCHAR(100) NOT NULL,
    "password" VARCHAR(100) NOT NULL,
    "date"     TIMESTAMP(0) NOT NULL
);

ALTER TABLE "users"
    ADD PRIMARY KEY ("id");

CREATE TABLE "quotes"
(
    "id"      BIGINT       NOT NULL,
    "content" VARCHAR(200) NOT NULL,
    "date"    TIMESTAMP(0) NOT NULL,
    "user_id" BIGINT       NOT NULL,
    "vote_id" BIGINT       NOT NULL
);

ALTER TABLE "quotes"
    ADD PRIMARY KEY ("id");

CREATE TABLE "votes"
(
    "id"    BIGINT NOT NULL,
    "score" BIGINT NOT NULL,
    "graph" TEXT   NOT NULL
);

ALTER TABLE "votes"
    ADD PRIMARY KEY ("id");

ALTER TABLE "quotes"
    ADD CONSTRAINT "qoutes_user_id_foreign" FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "quotes"
    ADD CONSTRAINT "qoutes_vote_id_foreign" FOREIGN KEY ("vote_id") REFERENCES "votes" ("id");