-- Script para criação da tabela no PostgreSQL
CREATE TABLE pessoas (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    documento VARCHAR(20),
    tipo VARCHAR(2)
);