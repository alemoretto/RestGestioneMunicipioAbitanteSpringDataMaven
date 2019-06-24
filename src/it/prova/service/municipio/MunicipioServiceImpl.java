package it.prova.service.municipio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.model.Municipio;
import it.prova.repository.municipio.MunicipioRepository;

@Service
public class MunicipioServiceImpl implements MunicipioService {

	@Autowired
	private MunicipioRepository municipioRepository;

	@Transactional(readOnly = true)
	public List<Municipio> listAllMunicipi() {
		return (List<Municipio>) municipioRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Municipio caricaSingoloMunicipio(long id) {
		return municipioRepository.findOne(id);
	}
	
	@Transactional(readOnly = true)
	public Municipio caricaSingoloMunicipioConAbitanti(long id) {
		Municipio result = municipioRepository.findOne(id);
		//forza il caricamento eager
		result.getAbitanti().size();
		return result;
	}

	@Transactional
	public void aggiorna(Municipio municipioInstance) {
		municipioRepository.save(municipioInstance);
	}

	@Transactional
	public void inserisciNuovo(Municipio municipioInstance) {
		municipioRepository.save(municipioInstance);
	}

	@Transactional
	public void rimuovi(Municipio municipioInstance) {
		municipioRepository.delete(municipioInstance);
	}

	@Transactional(readOnly = true)
	public List<Municipio> findByExample(Municipio municipioExample) {
		 ExampleMatcher matcher = ExampleMatcher.matching()     
                 .withStringMatcher(StringMatcher.CONTAINING);   // Match string containing pattern   
                 //.withIgnoreCase();   
		return (List<Municipio>) municipioRepository.findAll(Example.of(municipioExample,matcher));
	}

	@Transactional(readOnly = true)
	public List<Municipio> cercaByDescrizioneILike(String term) {
		return municipioRepository.findAllByDescrizioneContaining(term);
	}

}
