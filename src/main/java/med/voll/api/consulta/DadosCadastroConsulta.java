package med.voll.api.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosCadastroConsulta(
        @NotNull
        Long id_paciente,
        @NotNull
        Long id_medico,
        @NotNull
        @Future
        LocalDateTime data
) {}
