package br.com.sicredi.votacao.controller.dto;

import jakarta.validation.constraints.NotBlank;

public class AssociadoDTO {

    private Long id;
    
    @NotBlank
	private String cpf;
    
    @NotBlank
    private String nome;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
