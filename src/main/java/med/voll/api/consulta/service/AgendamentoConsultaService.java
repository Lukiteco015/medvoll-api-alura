package med.voll.api.consulta.service;

import jakarta.validation.ValidationException;
import med.voll.api.consulta.Consulta;
import med.voll.api.consulta.ConsultaRepository;
import med.voll.api.consulta.DadosCadastroConsulta;
import med.voll.api.medico.MedicoRepository;
import med.voll.api.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class AgendamentoConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public void agendamento(DadosCadastroConsulta dados) {
        if(!pacienteRepository.existsByIdAndAtivoTrue(dados.id_paciente())) {
            throw new ValidationException("Paciente não existe ou está inativo");
        }
        if(!medicoRepository.existsByIdAndAtivoTrue(dados.id_medico())) {
            throw new ValidationException("Medico não existe ou está inativo");
        }
        var diaSemana = dados.data().getDayOfWeek();
        var hora = dados.data().getHour();
        if(diaSemana == DayOfWeek.SUNDAY || hora < 7 || hora > 18) {
            throw new ValidationException("Consulta fora do horário de funcionamento da clínica.");
        }
        var agora = LocalDateTime.now();
        var diferençaEmMinutos = Duration.between(agora, dados.data()).toMinutes();
        if(diferençaEmMinutos < 30) {
            throw new ValidationException("A consulta deve ser agendada com no mínimo 30 minutos de antecedência.");
        }
        var primeiroHorario = dados.data().withHour(7).withMinute(0);
        var ultimoHorario = dados.data().withHour(18).withMinute(0);
        if(consultaRepository.existsByPacienteIdAndDataHoraBetween(dados.id_paciente(), primeiroHorario, ultimoHorario)) {
            throw new ValidationException("Paciente já possui outra consulta agendada neste dia.");
        }
        if(consultaRepository.existsByMedicoIdAndDataHora(dados.id_medico(), dados.data())) {
            throw new ValidationException("Médico já possui outra consulta agendada neste mesmo horário.");
        }
        var paciente = pacienteRepository.getReferenceById(dados.id_paciente());
        var medico = medicoRepository.getReferenceById(dados.id_medico());

        var consulta = new Consulta(paciente, medico, dados.data());
        consultaRepository.save(consulta);
    }

}
