package med.voll.api.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.endereco.DadosCadastroEndereco;

public record DadosAtualizacaoMedico(
        @NotNull
        Long id,

        String nome,

        DadosCadastroEndereco endereco,

        String telefone
)
{}
