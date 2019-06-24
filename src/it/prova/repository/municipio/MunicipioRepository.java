package it.prova.repository.municipio;

import java.util.List;

import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.model.Municipio;

public interface MunicipioRepository extends CrudRepository<Municipio, Long>,QueryByExampleExecutor <Municipio> {

	List<Municipio> findAllByDescrizioneContaining(String term);
	
	@Query("SELECT DISTINCT m FROM Municipio m LEFT JOIN FETCH m.abitanti")
	List<Municipio> findAllEager();


}
