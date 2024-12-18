package br.com.sicredi.votacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.sicredi.votacao.controller.dto.VotoDTO;
import br.com.sicredi.votacao.controller.dto.VotoResultadoDTO;
import br.com.sicredi.votacao.service.VotoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/votos")
public class VotoController {
	
	@Autowired
	private VotoService votoService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
    public VotoDTO registrar(@RequestBody @Valid VotoDTO votoDto) {
        return votoService.registrarVoto(votoDto);
    }

    @GetMapping("/resultado/{idPauta}")
    public List<VotoResultadoDTO> resultadoVotacao(@PathVariable Long idPauta) {
        return votoService.buscarResultadoVotacaoPorIdPauta(idPauta);
    }

    
}
