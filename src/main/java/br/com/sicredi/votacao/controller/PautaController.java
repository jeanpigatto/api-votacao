package br.com.sicredi.votacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.sicredi.votacao.controller.dto.PautaDTO;
import br.com.sicredi.votacao.service.PautaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pautas")
public class PautaController {

	@Autowired
	private PautaService pautaService;
    
	@GetMapping
	public List<PautaDTO> listar(){
		return pautaService.listarPautas();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PautaDTO criar(@RequestBody @Valid PautaDTO pautaDto) {
		return pautaService.salvarPauta(pautaDto);
	}

    @GetMapping("/{id}")
    public PautaDTO buscarPorId(@PathVariable Long id) {
        return pautaService.buscarPautaPorId(id);
    }

    @PutMapping("/{id}")
    public PautaDTO atualizar(@PathVariable Long id, @RequestBody @Valid PautaDTO pautaDTO) {
    	//atualiza somente a descricao
        return pautaService.atualizarPauta(id, pautaDTO);
    }

    @PutMapping("/abrirsessao/{id}")
    public PautaDTO abrirSessaoVotacao(@PathVariable Long id, @RequestBody PautaDTO pautaDTO) {
        return pautaService.abrirSessaoVotacao(id, pautaDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        pautaService.deletarPauta(id);
    }

}
