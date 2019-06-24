package it.prova.service.municipio;

import java.util.List;

import it.prova.model.Municipio;

public interface MunicipioService {
	
	public List<Municipio> listAllMunicipi() ;
	
	public List<Municipio> listAllMunicipiEager();

	public Municipio caricaSingoloMunicipio(Long id);
	
	public Municipio caricaSingoloMunicipioConAbitanti(Long id);

	public void aggiorna(Municipio municipioInstance);

	public void inserisciNuovo(Municipio municipioInstance);

	public void rimuovi(Municipio municipioInstance);

	public List<Municipio> findByExample(Municipio example);
	
	public List<Municipio> cercaByDescrizioneILike(String term);

}
