package br.com.sicredi.votacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sicredi.votacao.model.Pauta;

public interface PautaRepository extends JpaRepository<Pauta, Long> {

}
