package med.voll.api.consulta;

import med.voll.api.consulta.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    boolean existsByPacienteIdAndDataHoraBetween(Long id, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

    boolean existsByMedicoIdAndDataHora(Long id, LocalDateTime dataHora);
}