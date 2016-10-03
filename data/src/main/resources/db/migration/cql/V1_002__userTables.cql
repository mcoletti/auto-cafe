-- User Details Table
CREATE TABLE IF NOT EXISTS autocafe.users(
    client_id uuid,
    id uuid,
    user_name text,
    "password" text,
    last_login timestamp,
    address frozen<address>,
    created_user text,
    created timestamp,
    modified_user text,
    modified timestamp,
    PRIMARY KEY ((client_id,id),created)
)
WITH CLUSTERING ORDER BY (created DESC);

-- Client Users Table
CREATE TABLE IF NOT EXISTS autocafe.client_users(
    client_id uuid,
    id uuid,
    user_name text,
    created timeuuid,
    created_user text,
    PRIMARY KEY ((client_id,id),created)
);

-- User Sesions
CREATE TABLE IF NOT EXISTS autocafe.user_sessions(
    user_id uuid,
    session_token timeuuid,
    token_exp_dtm timestamp,
    PRIMARY KEY (user_id,session_token)
)
WITH CLUSTERING ORDER BY (session_token DESC);
