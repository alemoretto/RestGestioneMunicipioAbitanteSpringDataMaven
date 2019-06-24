package it.prova.service.abitante;

import java.util.List;

import it.prova.model.Abitante;

public interface AbitanteService {
	
	public List<Abitante> listAllAbitanti();
	
	public List<Abitante> listAllAbitantiEager();

	public Abitante caricaSingoloAbitante(Long id);
	
	public Abitante caricaSingoloAbitanteWithMunicipio(Long id);

	public void aggiorna(Abitante abitanteInstance);

	public void inserisciNuovo(Abitante abitanteInstance);

	public void rimuovi(Abitante abitanteInstance);

	public List<Abitante> findByExample(Abitante example);
	
	public List<Abitante> findByNome(String nameInput);
	
	public List<Abitante> cercaAbitantiConEtaMaggioreDi(Integer etaInput);
	
	public List<Abitante> cercaAbitantiPerNomeAndEta(String nomeInput, Integer etaInput);
	
	public List<Abitante> cercaAbitantiByEtaOrdinaPerNomeDesc(Integer eta);
	
	public List<Abitante> cercaPerNomeCheIniziaCon(String tokenIniziale);
	
}
