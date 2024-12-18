package br.com.sicredi.votacao.controller.dto;

import br.com.sicredi.votacao.model.StatusVoto;

public class VotoResultadoDTO {

	private Long idPauta;
	private String pauta;
    private StatusVoto voto;
    private Long total;

    public VotoResultadoDTO(Long idPauta, String pauta, StatusVoto voto, Long total) {
    	this.idPauta = idPauta;
        this.pauta = pauta;
        this.voto = voto;
        this.total = total;
    }

	public Long getIdPauta() {
		return idPauta;
	}

	public void setIdPauta(Long idPauta) {
		this.idPauta = idPauta;
	}

	public String getPauta() {
		return pauta;
	}

	public void setPauta(String pauta) {
		this.pauta = pauta;
	}

	//retorna como string
	public String getVoto() {
		return voto.toString();
	}

	public void setVoto(StatusVoto voto) {
		this.voto = voto;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
}
