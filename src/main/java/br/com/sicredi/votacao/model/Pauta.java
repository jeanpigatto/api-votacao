package br.com.sicredi.votacao.model;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Pauta {

	public static final Long MINUTOS_SESSAO = 1l;
	public static final String MSG_PAUTA_NAO_ENCONTRADA = "Pauta não encontrada para o ID: "; //colocar em arquivo contendo mensagens
	public static final String MSG_VOTACAO_ENCERRADA = "Votação encerrada para pauta ID: ";
	public static final String MSG_VOTACAO_ABERTA = "Votação já foi iniciada para pauta ID: "; //colocar em arquivo contendo mensagens
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
    private String descricao;
	
    private LocalDateTime dataInicial;
	private LocalDateTime dataFim;
    private LocalDateTime dataCadastro = LocalDateTime.now();
	
    public Pauta() {
	}

	public Pauta(String descricao, LocalDateTime dataInicial, LocalDateTime dataFim) {
		this.descricao = descricao;
		this.dataInicial = dataInicial;
		this.dataFim = dataFim;
	}

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
	
	public LocalDateTime getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(LocalDateTime dataInicial) {
		this.dataInicial = dataInicial;
	}

	public LocalDateTime getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDateTime dataFim) {
		this.dataFim = dataFim;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pauta other = (Pauta) obj;
		return Objects.equals(id, other.id);
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@Override
	public String toString() {
		return "Pauta [id=" + id + ", descricao=" + descricao + ", dataInicial=" + dataInicial + ", dataFim=" + dataFim
				+ ", dataCadastro=" + dataCadastro + "]";
	}
}


