-- 1. Tabela de Usuários: Guarda as informações de login e permissões

CREATE TABLE IF NOT EXISTS usuario (
    id_usuario INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    email TEXT UNIQUE NOT NULL,
    senha_hash TEXT NOT NULL,
    is_adm BOOLEAN NOT NULL DEFAULT 0,
    ativo BOOLEAN NOT NULL DEFAULT 1
);


-- 2. Tabela de Unidades Físicas: Representa prédios ou núcleos esportivos

CREATE TABLE IF NOT EXISTS unidade_fisica (
    id_unidade INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    descricao TEXT,
    tipo TEXT NOT NULL CHECK (tipo IN ('Predio','Nucleo'))
);

-- 3. Tabela de Espaços (Local genérico): Contém atributos comuns a todos os tipos de local

CREATE TABLE IF NOT EXISTS espaco (
    id_espaco INTEGER PRIMARY KEY AUTOINCREMENT,
    id_unidade INTEGER NOT NULL,
    nome TEXT NOT NULL,
    status TEXT NOT NULL CHECK (status IN ('Ativo','Inativo','Em Uso')),
    capacidade INTEGER NOT NULL,
    descricao TEXT,
    FOREIGN KEY (id_unidade) REFERENCES unidade_fisica(id_unidade) ON DELETE CASCADE
);

-- 4. Tabelas Filhas de Espaço: Cada tipo específico de espaço tem seus atributos
-- Observação: A Ligação é 1-para-1 com espaco via id_espaco

-- 4.1 Sala de Aula
CREATE TABLE IF NOT EXISTS sala (
    id_espaco INTEGER PRIMARY KEY,
    quant_projetor INTEGER NOT NULL DEFAULT 0,
    quant_ar_condicionado INTEGER NOT NULL DEFAULT 0,
    FOREIGN KEY (id_espaco) REFERENCES espaco(id_espaco) ON DELETE CASCADE
);

-- 4.2 Laboratório
CREATE TABLE IF NOT EXISTS laboratorio (
    id_espaco INTEGER PRIMARY KEY,
    quant_equipamentos INTEGER NOT NULL DEFAULT 0,
    tipo TEXT NOT NULL CHECK (tipo IN ('Informatica','Quimica','Biologia','Fisica')),
    FOREIGN KEY (id_espaco) REFERENCES espaco(id_espaco) ON DELETE CASCADE
);

-- 4.3 Auditório
CREATE TABLE IF NOT EXISTS auditorio (
    id_espaco INTEGER PRIMARY KEY,
    possui_sistema_som BOOLEAN NOT NULL DEFAULT 0,
    possui_palco BOOLEAN NOT NULL DEFAULT 0,
    FOREIGN KEY (id_espaco) REFERENCES espaco(id_espaco) ON DELETE CASCADE
);

-- 4.4 Quadra
CREATE TABLE IF NOT EXISTS quadra (
    id_espaco INTEGER PRIMARY KEY,
    tipo TEXT NOT NULL CHECK (tipo IN ('Basquete','Futsal','Handbol','Volei','Tenis','Poliesportiva')),
    eh_coberta BOOLEAN NOT NULL DEFAULT 0,
    possui_iluminacao BOOLEAN NOT NULL DEFAULT 0,
    FOREIGN KEY (id_espaco) REFERENCES espaco(id_espaco) ON DELETE CASCADE
);

-- 4.5 Sala de Reunião
CREATE TABLE IF NOT EXISTS sala_reuniao (
    id_espaco INTEGER PRIMARY KEY,
    quant_projetor INTEGER NOT NULL DEFAULT 0,
    possui_videoconferencia BOOLEAN NOT NULL DEFAULT 0,
    possui_sistema_som BOOLEAN NOT NULL DEFAULT 0,
    FOREIGN KEY (id_espaco) REFERENCES espaco(id_espaco) ON DELETE CASCADE
);

-- 5. Tabela de Reservas: Liga usuário com espaço e controla horários

CREATE TABLE IF NOT EXISTS reserva (
    id_reserva INTEGER PRIMARY KEY AUTOINCREMENT,
    id_usuario INTEGER NOT NULL,
    id_espaco INTEGER NOT NULL,
    nome TEXT NOT NULL,
    data DATE NOT NULL,
    horario_inicio TIME NOT NULL,
    horario_fim TIME NOT NULL,
    status TEXT NOT NULL CHECK (status IN ('Agendada','Em Andamento','Finalizada','Cancelada')),
    descricao TEXT,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    FOREIGN KEY (id_espaco) REFERENCES espaco(id_espaco)
);



