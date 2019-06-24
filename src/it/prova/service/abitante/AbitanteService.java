package it.prova.service.abitante;

import java.util.List;

import it.prova.model.Abitante;

public interface AbitanteService {
	
	public List<Abitante> listAllAbitanti();
	
	public List<Abitante> listAllAbitantiEager();

	public Abitante caricaSingoloAbitante(long id);
	
	public Abitante caricaSingoloAbitanteWithMunicipio(long id);

	public void aggiorna(Abitante abitanteInstance);

	public void inserisciNuovo(Abitante abitanteInstance);

	public void rimuovi(Abitante abitanteInstance);

	public List<Abitante> findByExample(Abitante example);
	
	public List<Abitante> findByNome(String nameInput);
	
	public List<Abitante> cercaAbitantiConEtaMaggioreDi(int etaInput);
	
	public List<Abitante> cercaAbitantiPerNomeAndEta(String nomeInput, int etaInput);
	
	public List<Abitante> cercaAbitantiByEtaOrdinaPerNomeDesc(int eta);
	
	public List<Abitante> cercaPerNomeCheIniziaCon(String tokenIniziale);
	
}
