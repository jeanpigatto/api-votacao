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

import br.com.sicredi.votacao.controller.dto.AssociadoDTO;
import br.com.sicredi.votacao.service.AssociadoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/associados")
public class AssociadoController {
	
	@Autowired
	private AssociadoService associadoService;
	
	@PostMapping("/registrar")
	@ResponseStatus(HttpStatus.CREATED)
	public AssociadoDTO criar(@RequestBody @Valid AssociadoDTO associadoDto) {
		return associadoService.criarAssociado(associadoDto);
	}

	@GetMapping
	public List<AssociadoDTO> listar(){
		return associadoService.listarAssociados();
	}

    @GetMapping("/{cpf}")
    public AssociadoDTO buscarPorCpf(@PathVariable String cpf) {
        return associadoService.buscarPorCpf(cpf);
    }
}
