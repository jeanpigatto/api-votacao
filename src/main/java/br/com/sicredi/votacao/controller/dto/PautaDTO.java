package br.com.sicredi.votacao.controller.dto;

import java.time.Duration;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;

public class PautaDTO {

    private Long id;
    
	@NotBlank(message = "Descrição da pauta não pode ser vazia") 
    private String descricao;
	
	//apenas implementado método set, sem GET para não passar para o front
    private LocalDateTime dataInicial; 
    
    private LocalDateTime dataFim;
    private Long minutosSessao;
    
    //atributo "calculado", nao salva no banco
    private Boolean votacaoAtiva;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public LocalDateTime getDataFim() {
		return dataFim;
	}
	public void setDataFim(LocalDateTime dataFim) {
		this.dataFim = dataFim;
	}
	
	public Long getMinutosSessao() {
		if (this.dataInicial != null && this.dataFim != null)
			return Duration.between(dataInicial, dataFim).toMinutes();
		
		return minutosSessao;
	}
	
	public void setMinutosSessao(Long minutosSessao) {
		this.minutosSessao = minutosSessao;
	}
	
//	public LocalDateTime getDataInicial() {
//		return dataInicial;
//	}
	
	public void setDataInicial(LocalDateTime dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Boolean getVotacaoAtiva() {
		votacaoAtiva = false;
		
		LocalDateTime now = LocalDateTime.now();
		if (this.dataInicial != null && this.dataFim != null)
			votacaoAtiva = (now.isAfter(dataInicial) && now.isBefore(dataFim));
		
		return votacaoAtiva;
	}

}
