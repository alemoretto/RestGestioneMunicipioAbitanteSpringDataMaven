package it.prova.web.rest.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.prova.model.Abitante;
import it.prova.service.abitante.AbitanteService;
import it.prova.web.dto.abitante.AbitanteDTO;

@Component
@Path("/abitante")
public class AbitanteResource {

	@Autowired
	AbitanteService abitanteService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAll() {
		List<AbitanteDTO> result = AbitanteDTO.buildListFromModelList(abitanteService.listAllAbitantiEager());
		return Response.status(200).entity(result).build();
	}
	
	@GET
	@Path("{id : \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAbitante(@PathParam("id") Long id) {
		AbitanteDTO abitanteDTOInstance =AbitanteDTO.buildAbitanteDTOFromModel(abitanteService.caricaSingoloAbitanteWithMunicipio(id),true);
		return Response.status(200).entity(abitanteDTOInstance).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertiNuovaAutomobile(AbitanteDTO abitanteDTOInput) {
		Abitante abitanteDaInserire = AbitanteDTO.buildAbitanteModelFromDTO(abitanteDTOInput, true);
		abitanteService.inserisciNuovo(abitanteDaInserire);
		abitanteDTOInput.setId(abitanteDaInserire.getId());
		return Response.status(200).entity(abitanteDTOInput).build();
	}

}
