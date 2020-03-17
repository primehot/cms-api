create table if not exists article
(
    id          uuid PRIMARY KEY,
    created_at  timestamp NOT NULL,
    modified_at timestamp NOT NULL
);

create table if not exists article_translation
(
    id          uuid PRIMARY KEY,
    created_at  timestamp NOT NULL,
    modified_at timestamp NOT NULL,

    description varchar(250),
    title       varchar(250),
    language    varchar(100),
    article_id  uuid references article,

    unique (language, article_id)
);