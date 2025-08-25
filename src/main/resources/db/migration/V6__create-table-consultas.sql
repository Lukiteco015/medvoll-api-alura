CREATE TABLE consultas (
    id bigserial PRIMARY KEY,

    medico_id INT NOT NULL,

    paciente_id INT NOT NULL,

    data_hora TIMESTAMP NOT NULL,

    FOREIGN KEY (medico_id) REFERENCES medicos(id),
    FOREIGN KEY (paciente_id) REFERENCES pacientes(id)
);