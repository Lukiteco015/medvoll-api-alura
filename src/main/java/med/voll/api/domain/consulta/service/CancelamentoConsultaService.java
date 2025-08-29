package med.voll.api.domain.consulta.service;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.MotivoCancelamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class CancelamentoConsultaService {

    @Autowired
    private ConsultaRepository repository;


    public void cancelamento(Long id_consulta, MotivoCancelamento motivo) {
        if(!repository.existsById(id_consulta)) {
            throw new ValidationException("Id da consulta informado não existe!");
        }

        var consulta = repository.getReferenceById(id_consulta);
        var agora = LocalDateTime.now();

        var antecedencia = Duration.between(agora, consulta.getDataHora()).toHours();
        if(antecedencia < 24) {
            throw new ValidationException("A consulta somente pode ser cancelada com antecedência mínima de 24 horas!");
        }

        consulta.cancelamento(motivo);
    }
}
