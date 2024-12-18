package br.com.sicredi.votacao.controller.dto;

import java.time.LocalDateTime;

import br.com.sicredi.votacao.exception.BussinessException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class VotoDTO {

	private static final String MSG_SIM_NAO = "Voto deve conter apenas valor igual a SIM/NAO";
	@NotNull
    private Long idPauta;
    
	@NotBlank(message = MSG_SIM_NAO ) 
    private String statusVoto;
	//private StatusVoto statusVoto;
	
    @NotBlank
	private String cpf;
    private LocalDateTime dataVoto;
    
	public String getStatusVoto() {
		return statusVoto;
	}

	//como string para validar e mostrar a mensagem da exceção
	public void setStatusVoto(String statusVoto) {
		if  (!(statusVoto.equalsIgnoreCase("SIM") || statusVoto.equalsIgnoreCase("NAO"))) {
			throw new BussinessException(MSG_SIM_NAO);
		}
		this.statusVoto = statusVoto;
	}

	public Long getIdPauta() {
		return idPauta;
	}

	public void setIdPauta(Long idPauta) {
		this.idPauta = idPauta;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDateTime getDataVoto() {
		return dataVoto;
	}

	public void setDataVoto(LocalDateTime dataVoto) {
		this.dataVoto = dataVoto;
	}
}
