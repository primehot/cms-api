create table if not exists image
(
    id          uuid PRIMARY KEY,
    created_at  timestamp NOT NULL,
    modified_at timestamp NOT NULL,

    bytes       bytea     NOT NULL,
    is_linked   bool
);

create table if not exists article
(
    id           uuid PRIMARY KEY,
    created_at   timestamp NOT NULL,
    modified_at  timestamp NOT NULL,

    image_id     uuid references image,
    is_published boolean,
    published_at timestamp
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