package br.com.sicredi.votacao.model;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = {"pauta", "associado"})}) 
public class Voto {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
	@ManyToOne
	private Pauta pauta;
	
	@NotNull
	@ManyToOne
	private Associado associado;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private StatusVoto statusVoto;
	
	private LocalDateTime dataVoto = LocalDateTime.now();
	
	public Voto() {
	}

	public Voto(Pauta pauta, Associado associado, StatusVoto statusVoto) {
		super();
		this.pauta = pauta;
		this.associado = associado;
		this.statusVoto = statusVoto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pauta getPauta() {
		return pauta;
	}

	public void setPauta(Pauta pauta) {
		this.pauta = pauta;
	}

	public Associado getAssociado() {
		return associado;
	}

	public void setAssociado(Associado associado) {
		this.associado = associado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(associado, pauta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Voto other = (Voto) obj;
		return Objects.equals(associado, other.associado) && Objects.equals(pauta, other.pauta);
	}

	public LocalDateTime getDataVoto() {
		return dataVoto;
	}

	public void setDataVoto(LocalDateTime dataVoto) {
		this.dataVoto = dataVoto;
	}
}
