package br.com.sicredi.votacao.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sicredi.votacao.controller.dto.AssociadoDTO;
import br.com.sicredi.votacao.exception.NaoEncontradoException;
import br.com.sicredi.votacao.model.Associado;
import br.com.sicredi.votacao.repository.AssociadoRepository;

@Service
public class AssociadoService extends BaseService {

	private static final Logger logger = LoggerFactory.getLogger(AssociadoService.class);
	
	@Autowired
	private AssociadoRepository associadoRepository;

	public AssociadoDTO criarAssociado(AssociadoDTO associadoDto) {
		Associado associadoSalva = associadoRepository.save(mapper.toEntity(associadoDto, Associado.class));
		
		logger.info("Associado com CPF {} foi registrado", associadoDto.getCpf());
		return mapper.toDTO(associadoSalva, AssociadoDTO.class);
	}

	public List<AssociadoDTO> listarAssociados() {
		return mapper.toDTOList(associadoRepository.findAll(), AssociadoDTO.class);
	}

	public Associado findByCpf(String cpf) {
		return associadoRepository.findByCpf(cpf).orElseThrow(() -> new NaoEncontradoException(Associado.MSG_ASSOCIAFO_NAO_ENCONTRADO + cpf));
	}
	
	public AssociadoDTO buscarPorCpf(String cpf) {
		Associado assoc = this.findByCpf(cpf);
		return mapper.toDTO(assoc, AssociadoDTO.class);
	}
}
