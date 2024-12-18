package br.com.sicredi.votacao.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sicredi.votacao.controller.dto.PautaDTO;
import br.com.sicredi.votacao.exception.BussinessException;
import br.com.sicredi.votacao.exception.NaoEncontradoException;
import br.com.sicredi.votacao.model.Pauta;
import br.com.sicredi.votacao.repository.PautaRepository;

@Service
public class PautaService extends BaseService{
	
	private static final Logger logger = LoggerFactory.getLogger(PautaService.class);
	
	@Autowired
	private PautaRepository pautaRepository;

	public List<PautaDTO> listarPautas() {
		return mapper.toDTOList(pautaRepository.findAll(), PautaDTO.class);
	}

	public PautaDTO salvarPauta(PautaDTO pautaDto) {
		Pauta pauta = mapper.toEntity(pautaDto, Pauta.class);	
		logger.info("Nova Pauta criada");
		return mapper.toDTO(pautaRepository.save(pauta), PautaDTO.class);
	}
	
    public PautaDTO atualizarPauta(Long id, PautaDTO pautaDTO) {
    	//atualiza somente a descricao 
    	Pauta pautaExistente = this.findById(id);
        pautaExistente.setDescricao(pautaDTO.getDescricao());
        
        logger.info("Descrição Pauta com ID {} foi atualizada", id);
        return mapper.toDTO(pautaRepository.save(pautaExistente), PautaDTO.class);
    }
    
	public Pauta findById(Long id) {
		return pautaRepository.findById(id).orElseThrow(() -> new NaoEncontradoException(Pauta.MSG_PAUTA_NAO_ENCONTRADA + id));
	}
	
	public PautaDTO buscarPautaPorId(Long id) {
		Pauta pauta = this.findById(id);
		return mapper.toDTO(pauta, PautaDTO.class);
	}

    // Deletar uma pauta por ID
    public void deletarPauta(Long id) {
        if (!pautaRepository.existsById(id)) {
            throw new NaoEncontradoException(Pauta.MSG_PAUTA_NAO_ENCONTRADA + id);
        }
        pautaRepository.deleteById(id);
        
        logger.info("Pauta com ID {} foi excluído", id);
    }
    
	public PautaDTO abrirSessaoVotacao(Long id, PautaDTO pautaDTO) {
    	//pode ser criada a pauta sem minutos informado
    	//depois em segundo momento é possivel abrir a sessao de votacao ao informar na PautaDTO a quantidade de "minutosSessao"
		
		Pauta pauta = this.findById(id);
        this.verificarSessaoEncerrada(pauta);
        this.iniciarSessaoSeMinutoInformado(pauta, pautaDTO);

        logger.info("Sessão de votação foi iniciada");
        
        return mapper.toDTO(pautaRepository.save(pauta), PautaDTO.class);
	}
	
	private void iniciarSessaoSeMinutoInformado(Pauta pauta, PautaDTO pautaDto) {
		
		if (pauta.getDataFim() == null) {
			long minutos = Pauta.MINUTOS_SESSAO;

			// se informado no frontend quantidade de minutos atribui, senao pega default 1 minuto
			if (pautaDto.getMinutosSessao() != null) {
				minutos = Math.max(Pauta.MINUTOS_SESSAO, pautaDto.getMinutosSessao());	
			}
			LocalDateTime agora = LocalDateTime.now();
			LocalDateTime fim = agora.plusMinutes(minutos);
			pauta.setDataInicial(agora);
			pauta.setDataFim(fim);				
		} else {
			//Após sessão iniciada não permite mais modificar
			throw new BussinessException(Pauta.MSG_VOTACAO_ABERTA + pauta.getId());
		}
	}
	
	public void verificarSessaoEncerrada(Pauta pauta) {
		//Verifica se a sessão já foi encerrada, não permite mais votar
        if (pauta.getDataFim() != null && LocalDateTime.now().isAfter(pauta.getDataFim()))
        	throw new BussinessException(Pauta.MSG_VOTACAO_ENCERRADA + pauta.getId());
	}
}
