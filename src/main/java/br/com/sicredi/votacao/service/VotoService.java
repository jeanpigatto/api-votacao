package br.com.sicredi.votacao.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sicredi.votacao.controller.dto.VotoDTO;
import br.com.sicredi.votacao.controller.dto.VotoResultadoDTO;
import br.com.sicredi.votacao.exception.BussinessException;
import br.com.sicredi.votacao.model.Associado;
import br.com.sicredi.votacao.model.Pauta;
import br.com.sicredi.votacao.model.StatusVoto;
import br.com.sicredi.votacao.model.Voto;
import br.com.sicredi.votacao.repository.VotoRepository;

@Service
public class VotoService extends BaseService{

	private static final Logger logger = LoggerFactory.getLogger(VotoService.class);
	
	@Autowired
	private PautaService pautaService;

	@Autowired
	private AssociadoService associadoService;
	
	@Autowired
	private VotoRepository votoRepository;
	
	public VotoDTO registrarVoto(VotoDTO votoDto) {

    	Pauta pauta = pautaService.findById(votoDto.getIdPauta());
    	pautaService.verificarSessaoEncerrada(pauta);
    	
    	Associado associado = associadoService.findByCpf(votoDto.getCpf());
    	StatusVoto simNao = StatusVoto.valueOf(votoDto.getStatusVoto().toUpperCase()) ;

    	//verifica o voto do associado
    	verificarVotoPautaAssociado(pauta, associado);
    	
    	Voto voto = new Voto(pauta, associado, simNao);
    	Voto votoSalvo = votoRepository.save(voto);
    	
    	VotoDTO dto = mapper.toDTO(votoSalvo, VotoDTO.class);
    	dto.setCpf(associado.getCpf());
    	dto.setStatusVoto(simNao.toString());
    	
        logger.info("Associado com CPF {} registrou seu voto", associado.getCpf());
    	return dto;
	}

	private void verificarVotoPautaAssociado(Pauta pauta, Associado associado) {

		//verifica se associado já votou naquela pauta e não permite votar novamente
    	votoRepository.findByPautaAndAssociado(pauta, associado).ifPresent(v -> {
            throw new BussinessException("Associado já votou. CPF: "+ v.getAssociado().getCpf());
        });
	}

	public List<VotoResultadoDTO> buscarResultadoVotacaoPorIdPauta(Long idPauta) {
		return votoRepository.buscarResultadoVotacaoPorIdPauta(idPauta);
	}
}
