--DROP TABLE IF EXISTS codebase.users_tmp;
--DROP SCHEMA IF EXISTS codebase;
CREATE SCHEMA IF NOT EXISTS codebase;


-- Crear la tabla de Usuarios
CREATE TABLE codebase.users_tmp (
    id SERIAL PRIMARY KEY,
    dni VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    role VARCHAR(20) CHECK (role IN ('admin', 'client')) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE SEQUENCE codebase.seq_users_tmp
START 1
INCREMENT 1
MINVALUE 1
OWNED BY codebase.users_tmp.id;

comment on table codebase.users is 'Almacena la información de los usuarios que alimentan la plataforma. Incluye un campo para el rol (admin o client).';
comment on column codebase.users.id is 'Identificador único del usuario.';
comment on column codebase.users.dni is 'DNI del usuario.';
comment on column codebase.users.name is 'Nombre del usuario.';
comment on column codebase.users.email is 'Correo electrónico del usuario.';
comment on column codebase.users.role is 'Rol del usuario (quien carga información o realiza reservas).';
comment on column codebase.users.created_at is 'Fecha de creación del registro en el sistema.';