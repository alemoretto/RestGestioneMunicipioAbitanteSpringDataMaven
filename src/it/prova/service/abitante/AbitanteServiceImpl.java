package it.prova.service.abitante;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.model.Abitante;
import it.prova.repository.abitante.AbitanteRepository;

@Service
public class AbitanteServiceImpl implements AbitanteService {

	@Autowired
	private AbitanteRepository abitanteRepository;

	@Transactional(readOnly = true)
	public List<Abitante> listAllAbitanti() {
		return (List<Abitante>) abitanteRepository.findAll();
	}

	@Override
	public List<Abitante> listAllAbitantiEager() {
		return abitanteRepository.findAllEagerMunicipio();
	}
	
	@Transactional(readOnly = true)
	public Abitante caricaSingoloAbitante(Long id) {
		return abitanteRepository.findOne(id);
	}
	
	@Override
	public Abitante caricaSingoloAbitanteWithMunicipio(Long id) {
		return abitanteRepository.findOneWithMunicipio(id);
	}

	@Transactional
	public void aggiorna(Abitante abitanteInstance) {
		abitanteRepository.save(abitanteInstance);
	}

	@Transactional
	public void inserisciNuovo(Abitante abitanteInstance) {
		abitanteRepository.save(abitanteInstance);
	}

	@Transactional
	public void rimuovi(Abitante abitanteInstance) {
		abitanteRepository.delete(abitanteInstance);
	}

	@Transactional(readOnly = true)
	public List<Abitante> findByExample(Abitante abitanteExample) {
			 ExampleMatcher matcher = ExampleMatcher.matching()     	
	                 .withStringMatcher(StringMatcher.CONTAINING);   // Match string containing pattern   
	                 //.withIgnoreCase();   
			 List<Abitante> res = (List<Abitante>) abitanteRepository.findAll(Example.of(abitanteExample,matcher));
			 for (Abitante abitante : res) {
				abitante.getMunicipio().getDescrizione();
			}
			 return res;
//			return (List<Abitante>) abitanteRepository.findAll(Example.of(abitanteExample,matcher));
//			return (List<Abitante>) abitanteRepository.findAll((Example<Abitante>) Example.of(example,matcher));
		}

	@Override
	public List<Abitante> findByNome(String nameInput) {
		return abitanteRepository.findByNome(nameInput);
	}

	@Override
	public List<Abitante> cercaAbitantiConEtaMaggioreDi(Integer etaInput) {
		return abitanteRepository.findByEtaGreaterThan(etaInput);
	}

	@Override
	public List<Abitante> cercaAbitantiByEtaOrdinaPerNomeDesc(Integer eta) {
		return abitanteRepository.findByEtaOrderByNomeDesc(eta);
	}

	@Override
	public List<Abitante> cercaPerNomeCheIniziaCon(String tokenIniziale) {
		return abitanteRepository.findByNomeStartsWith(tokenIniziale);
	}

	@Override
	public List<Abitante> cercaAbitantiPerNomeAndEta(String nomeInput, Integer etaInput) {
		return abitanteRepository.findByNomeAndEta(nomeInput, etaInput);
	}


}
