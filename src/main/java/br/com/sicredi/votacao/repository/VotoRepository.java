package br.com.sicredi.votacao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.sicredi.votacao.controller.dto.VotoResultadoDTO;
import br.com.sicredi.votacao.model.Associado;
import br.com.sicredi.votacao.model.Pauta;
import br.com.sicredi.votacao.model.Voto;

public interface VotoRepository extends JpaRepository<Voto, Long>{

	Optional<Voto> findByPautaAndAssociado(Pauta pauta, Associado associado);
	
    @Query( "SELECT new br.com.sicredi.votacao.controller.dto.VotoResultadoDTO(v.pauta.id, v.pauta.descricao, v.statusVoto as voto, COUNT(v)) as total "
    	  + "  FROM Voto v "
    	  + " WHERE v.pauta.id = :pautaId "
    	  + " GROUP BY v.pauta.id, v.pauta.descricao, v.statusVoto")
    List<VotoResultadoDTO> buscarResultadoVotacaoPorIdPauta(@Param("pautaId") Long pautaId);

}
