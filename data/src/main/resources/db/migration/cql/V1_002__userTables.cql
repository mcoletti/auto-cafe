-- User Details Table
CREATE TABLE IF NOT EXISTS autocafe.users(
    client_id uuid,
    id uuid,
    user_name text,
    "password" text,
    last_login timestamp,
    location frozen<location>,
    created_by text,
    created_dtm timestamp,
    modified_by text,
    modified_dtm timestamp,
    PRIMARY KEY ((client_id,id),created_dtm)
)
WITH CLUSTERING ORDER BY (created_dtm DESC);

-- Client Users Table
CREATE TABLE IF NOT EXISTS autocafe.client_users(
    client_id uuid,
    user_id uuid,
    user_name text,
    PRIMARY KEY (client_id)
);

-- User Sesions
CREATE TABLE IF NOT EXISTS autocafe.user_sessions(
    user_id uuid,
    session_token timeuuid,
    token_exp_dtm timestamp,
    PRIMARY KEY (user_id,session_token)
)
WITH CLUSTERING ORDER BY (session_token DESC);

