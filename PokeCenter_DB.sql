-- PokeCenter

CREATE TABLE Treinadores (
	id_treinador SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL UNIQUE,
	cidade VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE Pokemons (
    id_pokemon SERIAL PRIMARY KEY,
	fk_id_treinador INTEGER REFERENCES Treinadores(id_treinador),
    nome VARCHAR(50) NOT NULL UNIQUE,
    tipo_primario VARCHAR(30) NOT NULL,
    tipo_secundario VARCHAR(30),
    nivel INT NOT NULL CHECK (nivel >= 1 AND nivel <= 100),
    hp_maximo INT NOT NULL CHECK (hp_maximo > 0)
);