package it.prova.web.dto.abitante;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.prova.model.Abitante;
import it.prova.web.dto.municipio.MunicipioDTO;

public class AbitanteDTO {
	private Long id;
	private String nome;
	private String cognome;
	private Integer eta;
	private String residenza;
	@JsonIgnoreProperties(value= {"abitanti"})
	private MunicipioDTO municipio;

	public AbitanteDTO() {
	}

	public static List<AbitanteDTO> buildListFromModelList(List<Abitante> input){
		List<AbitanteDTO> result = new ArrayList<>();
		for (Abitante abitanteItem : input) {
			//faccio il binding senza municipio tramite costruttore
			AbitanteDTO abitanteDTOtemp = buildAbitanteDTOFromModel(abitanteItem, true);
			//costruisco un municipio senza portarmi dietro gli abitanti con 'false' nei parametri
			abitanteDTOtemp.setMunicipio(MunicipioDTO.buildMunicipioDTOFromModel(abitanteItem.getMunicipio(), false));
			result.add(abitanteDTOtemp);
		}
		return result;
	}
	
	public static AbitanteDTO buildAbitanteDTOFromModel(Abitante source, boolean includeMunicipio) {
		AbitanteDTO result = new AbitanteDTO();
		result.setId(source.getId());
		result.setNome(source.getNome());
		result.setCognome(source.getCognome());
		result.setEta(source.getEta());
		result.setResidenza(source.getResidenza());
		if(includeMunicipio)
			result.setMunicipio(MunicipioDTO.buildMunicipioDTOFromModel(source.getMunicipio(), !includeMunicipio));
		return result;
	}
	
	public static Abitante buildAbitanteModelFromDTO(AbitanteDTO source, boolean includeMunicipio) {
		Abitante result = new Abitante();
		result.setId(source.getId());
		result.setNome(source.getNome());
		result.setCognome(source.getCognome());
		result.setEta(source.getEta());
		result.setResidenza(source.getResidenza());
		if(includeMunicipio)
			result.setMunicipio(MunicipioDTO.buildMunicipioModelFromDTO(source.getMunicipio(), !includeMunicipio));
		return result;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Integer getEta() {
		return eta;
	}

	public void setEta(Integer eta) {
		this.eta = eta;
	}

	public String getResidenza() {
		return residenza;
	}

	public void setResidenza(String residenza) {
		this.residenza = residenza;
	}

	public MunicipioDTO getMunicipio() {
		return municipio;
	}

	public void setMunicipio(MunicipioDTO municipio) {
		this.municipio = municipio;
	}
	
}
