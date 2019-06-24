package it.prova.web.dto.municipio;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.prova.model.Abitante;
import it.prova.model.Municipio;
import it.prova.web.dto.abitante.AbitanteDTO;

public class MunicipioDTO {
	private Long id;
	private String descrizione;
	private String codice;
	private String ubicazione;
	@JsonIgnoreProperties(value= {"municipio"})
	private Set<AbitanteDTO> abitanti = new HashSet<>();

	public MunicipioDTO() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getUbicazione() {
		return ubicazione;
	}

	public void setUbicazione(String ubicazione) {
		this.ubicazione = ubicazione;
	}

	public Set<AbitanteDTO> getAbitanti() {
		return abitanti;
	}

	public void setAbitanti(Set<AbitanteDTO> abitanti) {
		this.abitanti = abitanti;
	}

	public static MunicipioDTO buildMunicipioDTOFromModel(Municipio source, boolean eagerAbitanti) {
		MunicipioDTO result = new MunicipioDTO();
		result.setId(source.getId());
		result.setDescrizione(source.getDescrizione());
		result.setCodice(source.getCodice());
		result.setUbicazione(source.getUbicazione());
		if (eagerAbitanti) {
			for (Abitante abitanteItem : source.getAbitanti()) {
				result.getAbitanti().add(AbitanteDTO.buildAbitanteDTOFromModel(abitanteItem, false));
			}
		}
		return result;
	}
	
	public static Municipio buildMunicipioModelFromDTO(MunicipioDTO source, boolean eagerAbitanti) {
		Municipio result = new Municipio();
		result.setId(source.getId());
		result.setDescrizione(source.getDescrizione());
		result.setCodice(source.getCodice());
		result.setUbicazione(source.getUbicazione());
		if (eagerAbitanti) {
			for (AbitanteDTO abitanteDTOItem : source.getAbitanti()) {
				result.getAbitanti().add(AbitanteDTO.buildAbitanteModelFromDTO(abitanteDTOItem, false));
			}
		}
		return result;
	}

}
