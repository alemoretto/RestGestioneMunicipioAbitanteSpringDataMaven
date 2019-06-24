package it.prova.web.rest.resources;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
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

	private static final Logger LOGGER = Logger.getLogger(MunicipioResource.class.getName());

	@Context
	HttpServletRequest request;

	@Autowired
	AbitanteService abitanteService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAll() {
		List<AbitanteDTO> result = abitanteService.listAllAbitanti().stream()
				.map(a -> AbitanteDTO.buildAbitanteDTOFromModel(a, false)).collect(Collectors.toList());
		return Response.status(200).entity(result).build();
	}

	@GET
	@Path("/eager")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAllEager() {
		List<AbitanteDTO> result = abitanteService.listAllAbitantiEager().stream()
				.map(a -> AbitanteDTO.buildAbitanteDTOFromModel(a, true)).collect(Collectors.toList());
		return Response.status(200).entity(result).build();
	}

	@GET
	@Path("{id : \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAbitante(@PathParam("id") Long id) {
		AbitanteDTO abitanteDTOInstance = AbitanteDTO
				.buildAbitanteDTOFromModel(abitanteService.caricaSingoloAbitante(id), false);
		return Response.status(200).entity(abitanteDTOInstance).build();
	}

	@GET
	@Path("/eager/{id : \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAbitanteEager(@PathParam("id") Long id) {
		AbitanteDTO abitanteDTOInstance = AbitanteDTO
				.buildAbitanteDTOFromModel(abitanteService.caricaSingoloAbitanteWithMunicipio(id), true);
		return Response.status(200).entity(abitanteDTOInstance).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertNuovoAbitante(AbitanteDTO abitanteDTOInput) {
		Abitante abitanteDaInserire = AbitanteDTO.buildAbitanteModelFromDTO(abitanteDTOInput, true);
		abitanteService.inserisciNuovo(abitanteDaInserire);
		abitanteDTOInput.setId(abitanteDaInserire.getId());
		return Response.status(200).entity(abitanteDTOInput).build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response aggiornaAbitante(AbitanteDTO abitanteDTOInput) {
		LOGGER.info("Verbo Http.........................." + request.getMethod());
		abitanteService.aggiorna(AbitanteDTO.buildAbitanteModelFromDTO(abitanteDTOInput, true));
		return Response.status(200).entity(abitanteDTOInput).build();
	}

	@DELETE
	@Path("{id : \\d+}")
	public Response deleteAbitante(@PathParam("id") Long id) {
		LOGGER.info("Verbo Http.........................." + request.getMethod());
		Abitante abitante = abitanteService.caricaSingoloAbitante(id);
		if (abitante != null) {
			abitanteService.rimuovi(abitante);
			return Response.status(200).entity("Rimosso Abitante con id: " + id).build();
		}

		return Response.status(Response.Status.NOT_FOUND).entity("not found").build();
	}

}
