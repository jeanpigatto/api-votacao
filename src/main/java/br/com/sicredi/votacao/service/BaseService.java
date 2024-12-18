package br.com.sicredi.votacao.service;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.sicredi.votacao.mapper.GenericMapper;

public class BaseService {

	@Autowired
	protected GenericMapper mapper;
}
