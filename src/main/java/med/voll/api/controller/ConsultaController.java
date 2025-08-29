package med.voll.api.controller;


import jakarta.validation.Valid;
import med.voll.api.domain.consulta.DadosCadastroConsulta;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import med.voll.api.domain.consulta.service.AgendamentoConsultaService;
import med.voll.api.domain.consulta.service.CancelamentoConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private AgendamentoConsultaService agendamento;

    @Autowired
    private CancelamentoConsultaService cancelamento;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarConsulta(@RequestBody @Valid DadosCadastroConsulta dados) {
        agendamento.agendamento(dados);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity cancelarConsulta(@PathVariable Long id, @RequestBody @Valid DadosCancelamentoConsulta dados) {
        cancelamento.cancelamento(id, dados.motivo());
        return ResponseEntity.ok().build();
    }
}
