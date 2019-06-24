package it.prova.repository.abitante;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.model.Abitante;

public interface AbitanteRepository extends CrudRepository<Abitante, Long> {

	// la query viene costruita in automatico!!!
	List<Abitante> findByNome(String name);

	// Anche questa!!!
	List<Abitante> findByEtaGreaterThan(int etaInput);

	// Combinazione di campi!!! (Come i dynamic finders)
	List<Abitante> findByNomeAndEta(String nome, int eta);

	// Order by!!!!
	List<Abitante> findByEtaOrderByNomeDesc(int eta);

	// se ho necessità particolari
	@Query("from Abitante p where p.nome like ?1%")
	List<Abitante> findByNomeStartsWith(String token);

	@Query("from Abitante p left join fetch p.municipio")
	List<Abitante> findAllEagerMunicipio();
	
	@Query("from Abitante p left join fetch p.municipio where p.id=?1")
	Abitante findOneWithMunicipio(Long id);

}
